package br.com.votingsessionsystem.controller;

import br.com.votingsessionsystem.entity.VotingSession;
import br.com.votingsessionsystem.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${spring.data.rest.base-path}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingSessionController {

    private final VotingSessionService service;

    @GetMapping(path = "/v1/voting_session")
    public ResponseEntity<List<VotingSession>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping(path = "/v1/voting_session")
    public ResponseEntity<VotingSession> save(@RequestBody VotingSession votingSession) {
        return ResponseEntity.status(CREATED).body(service.save(votingSession));
    }

}
