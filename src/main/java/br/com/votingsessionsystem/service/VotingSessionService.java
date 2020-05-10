package br.com.votingsessionsystem.service;

import br.com.votingsessionsystem.entity.VotingSession;

import java.util.List;

public interface VotingSessionService {

    List<VotingSession> listAll();

    VotingSession save(VotingSession votingSession);

}
