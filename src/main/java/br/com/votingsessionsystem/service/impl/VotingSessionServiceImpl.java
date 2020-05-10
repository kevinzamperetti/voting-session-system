package br.com.votingsessionsystem.service.impl;

import br.com.votingsessionsystem.entity.VotingSession;
import br.com.votingsessionsystem.entity.VotingSubject;
import br.com.votingsessionsystem.exception.*;
import br.com.votingsessionsystem.repository.VotingSessionRepository;
import br.com.votingsessionsystem.repository.VotingSubjectRepository;
import br.com.votingsessionsystem.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository repository;

    private final VotingSubjectRepository votingSubjectRepository;

    @Override
    public List<VotingSession> listAll() {
        return repository.listAll();
    }

    @Override
    public VotingSession save(VotingSession votingSession) {

        verifyFieldsForSave(votingSession);
        setVotingSubject(votingSession);
        if (isNull(votingSession.getClosingDate())) {
            votingSession.setClosingDate(LocalDateTime.now().plusMinutes(1));
        }
        votingSession.setCreationDate(LocalDateTime.now());
        return repository.save(votingSession);
    }

    private void setVotingSubject(VotingSession votingSession) {
        Long idVotingSubject = votingSession.getVotingSubject().getId();
        VotingSubject votingSubject = votingSubjectRepository.findById(idVotingSubject)
                .orElseThrow(() -> new VotingSubjectIdNotFoundException("Voting Subject not found."));
        if (repository.findVotingSessionByVotingSubject_Id(idVotingSubject).isPresent()) {
            throw new VotingSubjectAlreadyHasAVotingSessionException("Voting Subject already has a Voting Session.");
        }
        votingSession.setVotingSubject(votingSubject);
    }

    private void verifyFieldsForSave(VotingSession votingSession) {
        if (nonNull(votingSession.getId())) {
            throw new VotingSessionIdMustNotBeProvidedException("Id of Voting Session must not be provided.");
        }
        if (!hasText(votingSession.getName())) {
            throw new VotingSessionNameNotProvidedException("Name not provided.");
        }
        if (isNull(votingSession.getVotingSubject().getId())) {
            throw new VotingSessionVotingSubjectNotProvidedException("Voting Subject not provided.");
        }
    }

}
