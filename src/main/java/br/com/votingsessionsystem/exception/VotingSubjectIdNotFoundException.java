package br.com.votingsessionsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class VotingSubjectIdNotFoundException extends RuntimeException {

    public VotingSubjectIdNotFoundException(String message) {
        super(message);
    }

}
