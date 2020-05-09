package br.com.votingsessionsystem.entity.dto;

import br.com.votingsessionsystem.enums.AssociatedVoteType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociatedVoteDto {

    private Long idAssociated;

    private Long idVotingSession;

    private AssociatedVoteType vote;

}
