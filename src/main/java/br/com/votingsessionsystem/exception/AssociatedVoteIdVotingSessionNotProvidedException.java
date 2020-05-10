package br.com.votingsessionsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class AssociatedVoteIdVotingSessionNotProvidedException extends RuntimeException {

    public AssociatedVoteIdVotingSessionNotProvidedException(String message) {
        super(message);
    }

}
