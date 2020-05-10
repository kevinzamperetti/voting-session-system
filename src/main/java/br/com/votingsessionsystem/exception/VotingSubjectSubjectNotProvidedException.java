package br.com.votingsessionsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class VotingSubjectSubjectNotProvidedException extends RuntimeException {

    public VotingSubjectSubjectNotProvidedException(String message) {
        super(message);
    }
}
