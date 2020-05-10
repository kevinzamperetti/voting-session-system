package br.com.votingsessionsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class VotingSessionIdMustNotBeProvidedException extends RuntimeException {

    public VotingSessionIdMustNotBeProvidedException(String message) {
        super(message);
    }

}
