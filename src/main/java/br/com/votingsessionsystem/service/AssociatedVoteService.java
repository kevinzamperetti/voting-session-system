package br.com.votingsessionsystem.service;

import br.com.votingsessionsystem.entity.AssociatedVote;
import br.com.votingsessionsystem.entity.dto.AssociatedVoteDto;

import java.util.List;

public interface AssociatedVoteService {

    List<AssociatedVote> listAll();

    AssociatedVote save(AssociatedVoteDto associatedVoteDto);

}
