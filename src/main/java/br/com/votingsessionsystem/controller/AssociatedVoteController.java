package br.com.votingsessionsystem.controller;

import br.com.votingsessionsystem.entity.AssociatedVote;
import br.com.votingsessionsystem.entity.dto.AssociatedVoteDto;
import br.com.votingsessionsystem.service.AssociatedVoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${spring.data.rest.base-path}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociatedVoteController {

    private final AssociatedVoteService service;

    @GetMapping(path = "/v1/associated_vote")
    public ResponseEntity<List<AssociatedVote>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping(path = "/v1/associated_vote")
    public ResponseEntity<AssociatedVote> save(@RequestBody AssociatedVoteDto associatedVoteDto) throws JsonProcessingException {
        return ResponseEntity.status(CREATED).body(service.save(associatedVoteDto));
    }

}
