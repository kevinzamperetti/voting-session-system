package br.com.votingsessionsystem.entity.dto;

import br.com.votingsessionsystem.entity.VotingSubject;
import br.com.votingsessionsystem.enums.AssociatedVoteType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotingSubjectTotalVoteDto {

    private VotingSubject votingSubject;

    private TotalVoteDto totalVote;

}
