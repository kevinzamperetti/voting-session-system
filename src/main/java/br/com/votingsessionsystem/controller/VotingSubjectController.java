package br.com.votingsessionsystem.controller;

import br.com.votingsessionsystem.entity.VotingSubject;
import br.com.votingsessionsystem.entity.dto.VotingSubjectTotalVoteDto;
import br.com.votingsessionsystem.service.VotingSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${spring.data.rest.base-path}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingSubjectController {

    private final VotingSubjectService service;

    @GetMapping(path = "/v1/voting_subject")
    public ResponseEntity<List<VotingSubject>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping(path = "/v1/voting_subject/{id}/votes")
    public ResponseEntity<VotingSubjectTotalVoteDto> findTotalVote(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findTotalVote(id));
    }

    @PostMapping(path = "/v1/voting_subject")
    public ResponseEntity<VotingSubject> save(@RequestBody VotingSubject votingSubject) {
        return ResponseEntity.status(CREATED).body(service.save(votingSubject));
    }

}
