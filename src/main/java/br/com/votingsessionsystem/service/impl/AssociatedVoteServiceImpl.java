package br.com.votingsessionsystem.service.impl;

import br.com.votingsessionsystem.entity.Associated;
import br.com.votingsessionsystem.entity.AssociatedVote;
import br.com.votingsessionsystem.entity.VotingSession;
import br.com.votingsessionsystem.entity.dto.AssociatedVoteDto;
import br.com.votingsessionsystem.enums.AssociatedVoteType;
import br.com.votingsessionsystem.exception.*;
import br.com.votingsessionsystem.repository.AssociatedRepository;
import br.com.votingsessionsystem.repository.AssociatedVoteRepository;
import br.com.votingsessionsystem.repository.VotingSessionRepository;
import br.com.votingsessionsystem.service.AssociatedVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class AssociatedVoteServiceImpl implements AssociatedVoteService {

    private final AssociatedVoteRepository repository;

    private final AssociatedRepository associatedRepository;

    private final VotingSessionRepository votingSessionRepository;

    @Override
    public List<AssociatedVote> listAll() {
        return repository.listAll();
    }

    @Override
    public AssociatedVote save(AssociatedVoteDto associatedVoteDto) {

        verifyFieldsForSave(associatedVoteDto);
        Associated associated = associatedRepository.findById(associatedVoteDto.getIdAssociated())
                .orElseThrow(() -> new AssociatedIdNotFoundException("Associated not found."));
        VotingSession votingSession = votingSessionRepository.findById(associatedVoteDto.getIdVotingSession())
                .orElseThrow(() -> new VotingSubjectIdNotFoundException("Voting Session not found."));

        checkIfCanVote(votingSession, associated);

        return repository.save(AssociatedVote.builder()
                .associated(associated)
                .votingSession(votingSession)
                .vote(associatedVoteDto.getVote())
                .build());
    }

    private void checkIfCanVote(VotingSession votingSession, Associated associated) {
        if (LocalDateTime.now().isAfter(votingSession.getClosingDate())) {
            throw new AssociatedVoteDeniedVotingSessionClosedException("Associated Vote denied Voting Session closed.");
        }
        if (repository.findByIdAssociated(associated.getId(), votingSession.getId()).isPresent()) {
            throw new AssociatedVoteDeniedVotingSessionClosedException("Associated Vote already taken for this Voting Session.");
        }
    }

    private void verifyFieldsForSave(AssociatedVoteDto associatedVoteDto) {
        if (isNull(associatedVoteDto.getIdAssociated())) {
            throw new AssociatedVoteIdAssociatedNotProvidedException("Id Associated not be provided.");
        }
        if (isNull(associatedVoteDto.getIdVotingSession())) {
            throw new AssociatedVoteIdVotingSessionNotProvidedException("Id Voting Session not provided.");
        }
        if (!hasText(associatedVoteDto.getVote().name())) {
            throw new AssociatedVoteVoteNotProvidedException("Vote not provided.");
        }
        if (!AssociatedVoteType.YES.equals(associatedVoteDto.getVote()) &&
            !AssociatedVoteType.NO.equals(associatedVoteDto.getVote())) {
            throw new AssociatedVoteVoteNotProvidedException("Invalid vote. Valid options: YES or NO.");
        }
    }

}
