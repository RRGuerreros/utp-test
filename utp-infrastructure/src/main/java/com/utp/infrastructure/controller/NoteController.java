package com.utp.infrastructure.controller;

import com.utp.application.dto.NoteResponse;
import com.utp.infrastructure.dto.CreateNoteApiRequest;
import com.utp.infrastructure.facade.NoteFacade;
import com.utp.infrastructure.mapper.NoteRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteFacade noteFacade;
    private final NoteRestMapper noteRestMapper;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody CreateNoteApiRequest createNoteApiRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                noteFacade.createNote(
                        noteRestMapper.asCreateNoteRequest(createNoteApiRequest)
                ));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<NoteResponse>> listNotes(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(noteFacade.listNotesByUsernameAuth(userDetails.getUsername()));
    }
}
