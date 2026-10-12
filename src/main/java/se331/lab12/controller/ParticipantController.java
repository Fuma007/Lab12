package se331.lab12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab12.entity.Participant;
import se331.lab12.service.ParticipantService;
import se331.lab12.util.LabMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    final ParticipantService participantService;

    @GetMapping("/participants")
    public ResponseEntity<?> getParticipantLists() {
        List<Participant> participants = participantService.getAllParticipants();
        return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDto(participants));
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<?> getParticipant(@PathVariable("id") Long id) {
        Participant output = participantService.getParticipant(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}