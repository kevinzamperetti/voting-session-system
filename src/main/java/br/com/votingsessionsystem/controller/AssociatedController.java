package br.com.votingsessionsystem.controller;

import br.com.votingsessionsystem.entity.Associated;
import br.com.votingsessionsystem.service.AssociatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociatedController {

    private final AssociatedService service;

    @GetMapping(path = "/v1/associated")
    public ResponseEntity<List<Associated>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

}
