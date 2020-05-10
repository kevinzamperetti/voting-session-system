package br.com.votingsessionsystem.service;

import br.com.votingsessionsystem.entity.VotingSubject;
import br.com.votingsessionsystem.entity.dto.VotingSubjectTotalVoteDto;

import java.util.List;

public interface VotingSubjectService {

    List<VotingSubject> listAll();

    VotingSubject save(VotingSubject votingSubject);

    VotingSubject findById(Long id);

    VotingSubjectTotalVoteDto findTotalVote(Long id);

}
