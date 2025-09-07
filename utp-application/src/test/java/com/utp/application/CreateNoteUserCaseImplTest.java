
package com.utp.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.application.dto.CreateNoteRequest;
import com.utp.application.dto.NoteResponse;
import com.utp.application.event.NoteEventPublisher;
import com.utp.application.mapper.NoteMapper;
import com.utp.application.services.TagService;
import com.utp.application.services.UserService;
import com.utp.application.usecase.note.CreateNoteUserCaseImpl;
import com.utp.domain.entity.Note;
import com.utp.domain.entity.Tag;
import com.utp.domain.entity.User;
import com.utp.domain.exception.BusinessException;
import com.utp.domain.exception.EntityNotFoundException;
import com.utp.domain.repository.NoteRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateNoteUserCaseImplTest {

    private static final String PATH_DIR_BASE = "src/test/resources";
    private static final String CREATE_NOTE_REQUEST_JSON = "/dto/create_note_request.json";
    private static final String NOTE_RESPONSE_REQUEST_JSON = "/dto/note_response.json";
    private static final String TAGS_DB_REQUEST_JSON = "/entity/tags_db.json";
    private static final String NOTE_JSON = "/entity/note.json";
    private static final String USER_JSON = "/entity/user.json";
    private static final String NOTE_DB_JSON = "/entity/note_db.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private CreateNoteUserCaseImpl createNoteUserCaseImpl;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteMapper noteMapper;
    @Mock
    private UserService userService;
    @Mock
    private TagService tagService;
    @Mock
    private NoteEventPublisher noteEventPublisher;

    private Note defaultNote;
    private CreateNoteRequest defaultCreateNoteRequest;
    private User defaultUser;
    private Tag defaultTag;

    @BeforeEach
    void setUp() {
        defaultNote = mock(Note.class);
        defaultCreateNoteRequest = mock(CreateNoteRequest.class);
        defaultUser = mock(User.class);
        defaultTag = mock(Tag.class);
    }

    @Test
    void shouldCreateNoteSuccessfulAndExecuteServicesInCorrectOrder() throws Exception {
        CreateNoteRequest createNoteRequest = readJson(StringUtils.join(PATH_DIR_BASE, CREATE_NOTE_REQUEST_JSON), CreateNoteRequest.class);
        List<Tag> resolvedTags = readJsonList(StringUtils.join(PATH_DIR_BASE, TAGS_DB_REQUEST_JSON), Tag.class);
        NoteResponse noteResponse = readJson(StringUtils.join(PATH_DIR_BASE, NOTE_RESPONSE_REQUEST_JSON), NoteResponse.class);
        Note note = readJson(StringUtils.join(PATH_DIR_BASE, NOTE_JSON), Note.class);
        User user = readJson(StringUtils.join(PATH_DIR_BASE, USER_JSON), User.class);
        Note noteSave = readJson(StringUtils.join(PATH_DIR_BASE, NOTE_DB_JSON), Note.class);

        when(userService.getUserAuthenticated()).thenReturn(user);
        when(noteMapper.asNote(createNoteRequest)).thenReturn(note);
        when(tagService.saveAll(createNoteRequest.getTags())).thenReturn(resolvedTags);
        when(noteRepository.save(note)).thenReturn(noteSave);
        when(noteMapper.asNoteResponse(noteSave)).thenReturn(noteResponse);

        NoteResponse response = createNoteUserCaseImpl.execute(createNoteRequest);

        ArgumentCaptor<Note> captor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(captor.capture());
        Note capturedNote = captor.getValue();

        assertEquals(1, capturedNote.getTags().get(0).getId());
        assertNotNull(capturedNote.getUser());
        assertEquals("maria", capturedNote.getUser().getUsername());
        assertEquals(createNoteRequest.getContent(), response.getContent());
        assertEquals(createNoteRequest.getTags().size(), noteResponse.getTags().size());

        InOrder inOrder = inOrder(userService, noteMapper, tagService, noteRepository, noteEventPublisher);
        inOrder.verify(userService).getUserAuthenticated();
        inOrder.verify(noteMapper).asNote(createNoteRequest);
        inOrder.verify(tagService).saveAll(createNoteRequest.getTags());
        inOrder.verify(noteRepository).save(note);
        inOrder.verify(noteEventPublisher).publishNoteCreated(noteSave);
        inOrder.verify(noteMapper).asNoteResponse(noteSave);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userService.getUserAuthenticated()).thenThrow(new EntityNotFoundException());

        assertThrows(RuntimeException.class, () -> createNoteUserCaseImpl.execute(defaultCreateNoteRequest));

        verifyNoInteractions(noteMapper, tagService, noteRepository, noteEventPublisher);
    }

    @Test
    void shouldThrowExceptionWhenTagsInvalid() {
        when(userService.getUserAuthenticated()).thenReturn(defaultUser);
        when(noteMapper.asNote(any(CreateNoteRequest.class))).thenThrow(new BusinessException());

        assertThrows(BusinessException.class, () -> createNoteUserCaseImpl.execute(defaultCreateNoteRequest));

        verifyNoInteractions(tagService, noteRepository, noteEventPublisher);
    }

    @Test
    void shouldExecuteEventPublishedOne() {
        CreateNoteRequest request = new CreateNoteRequest();

        when(userService.getUserAuthenticated()).thenReturn(defaultUser);
        when(noteMapper.asNote(request)).thenReturn(defaultNote);
        when(tagService.saveAll(request.getTags())).thenReturn(List.of(defaultTag));
        when(noteRepository.save(any())).thenReturn(defaultNote);
        when(noteMapper.asNoteResponse(defaultNote)).thenReturn(new NoteResponse());

        createNoteUserCaseImpl.execute(request);

        verify(noteEventPublisher, times(1)).publishNoteCreated(defaultNote);
    }

    private <T> T readJson(String path, Class<T> clazz) throws Exception {
        return objectMapper.readValue(new File(path), clazz);
    }

    private <T> List<T> readJsonList(String path, Class<T> clazz) throws Exception {
        return objectMapper.readValue(new File(path), objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
