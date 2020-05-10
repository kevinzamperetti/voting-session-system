package br.com.votingsessionsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class AssociatedDocumentNumberUnableToVote extends RuntimeException {

    public AssociatedDocumentNumberUnableToVote(String message) {
        super(message);
    }

}
