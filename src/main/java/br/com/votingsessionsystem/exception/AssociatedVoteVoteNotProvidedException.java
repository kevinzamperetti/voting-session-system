package br.com.votingsessionsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class AssociatedVoteVoteNotProvidedException extends RuntimeException {

    public AssociatedVoteVoteNotProvidedException(String message) {
        super(message);
    }

}
