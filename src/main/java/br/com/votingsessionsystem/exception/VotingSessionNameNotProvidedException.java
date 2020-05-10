package br.com.votingsessionsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class VotingSessionNameNotProvidedException extends RuntimeException {

    public VotingSessionNameNotProvidedException(String message) {
        super(message);
    }

}
