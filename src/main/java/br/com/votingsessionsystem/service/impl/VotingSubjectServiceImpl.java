package br.com.votingsessionsystem.service.impl;

import br.com.votingsessionsystem.entity.VotingSubject;
import br.com.votingsessionsystem.entity.dto.VotingSubjectTotalVoteDto;
import br.com.votingsessionsystem.entity.dto.TotalVoteDto;
import br.com.votingsessionsystem.exception.VotingSubjectIdNotFoundException;
import br.com.votingsessionsystem.exception.VotingSubjectSubjectNotProvidedException;
import br.com.votingsessionsystem.exception.VotingSubjectIdMustNotBeProvidedException;
import br.com.votingsessionsystem.repository.AssociatedVoteRepository;
import br.com.votingsessionsystem.repository.VotingSubjectRepository;
import br.com.votingsessionsystem.service.VotingSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.*;
import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class VotingSubjectServiceImpl implements VotingSubjectService {

    private final VotingSubjectRepository repository;

    private final AssociatedVoteRepository associatedVoteRepository;

    @Override
    public List<VotingSubject> listAll() {
        return repository.listAll();
    }

    @Override
    public VotingSubject save(VotingSubject votingSubject){
        verifyFieldsForSave(votingSubject);
        return repository.save(votingSubject);
    }

    private void verifyFieldsForSave(VotingSubject votingSubject) {
        if (nonNull(votingSubject.getId())) {
            throw new VotingSubjectIdMustNotBeProvidedException("Id of Voting Subject must not be provided.");
        }
        if (!hasText(votingSubject.getSubject())) {
            throw new VotingSubjectSubjectNotProvidedException("Subject not provided.");
        }
    }

    @Override
    public VotingSubject findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new VotingSubjectIdNotFoundException("Voting Subject not found."));
    }

    @Override
    public VotingSubjectTotalVoteDto findTotalVote(Long id) {
        VotingSubject votingSubject = findById(id);
        Integer totalVoteYes = associatedVoteRepository.countVoteYesByVotingSubject(id);
        Integer totalVoteNo = associatedVoteRepository.countVoteNoByVotingSubject(id);
        TotalVoteDto totalVoteDto = TotalVoteDto.builder().no(totalVoteNo).yes(totalVoteYes).build();
        return VotingSubjectTotalVoteDto.builder()
                .votingSubject(votingSubject)
                .totalVote(totalVoteDto)
                .build();
    }

}
