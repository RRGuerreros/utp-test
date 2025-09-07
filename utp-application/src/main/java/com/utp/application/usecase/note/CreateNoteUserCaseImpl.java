package com.utp.application.usecase.note;

import com.utp.application.annotation.UseCase;
import com.utp.application.dto.CreateNoteRequest;
import com.utp.application.dto.NoteResponse;
import com.utp.application.event.NoteEventPublisher;
import com.utp.application.mapper.NoteMapper;
import com.utp.application.services.TagService;
import com.utp.application.services.UserService;
import com.utp.domain.entity.Note;
import com.utp.domain.entity.Tag;
import com.utp.domain.entity.User;
import com.utp.domain.exception.BusinessException;
import com.utp.domain.exception.EntityNotFoundException;
import com.utp.domain.repository.NoteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateNoteUserCaseImpl implements CreateNoteUseCase{

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final UserService userService;
    private final TagService tagService;
    private final NoteEventPublisher noteEventPublisher;

    @Override
    public NoteResponse execute(CreateNoteRequest createNoteRequest)
            throws BusinessException, EntityNotFoundException {
        User user = userService.getUserAuthenticated();
        Note note = noteMapper.asNote(createNoteRequest);
        List<Tag> resolvedTags = tagService.saveAll(createNoteRequest.getTags());
        note.setTags(resolvedTags);
        note.setUser(user);
        Note noteSave = noteRepository.save(note);
        noteEventPublisher.publishNoteCreated(noteSave);
        return noteMapper.asNoteResponse(noteSave);
    }
}
