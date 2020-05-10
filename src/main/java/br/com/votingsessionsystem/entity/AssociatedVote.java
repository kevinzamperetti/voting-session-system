package br.com.votingsessionsystem.entity;

import br.com.votingsessionsystem.enums.AssociatedVoteType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="id_associated_vote"))})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociatedVote extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JsonIgnoreProperties({"votingSubject", "votes"})
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_voting_session", referencedColumnName = "id_voting_session")
    private VotingSession votingSession;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_associated", referencedColumnName = "id_associated")
    private Associated associated;

    @Column
    @Enumerated(EnumType.STRING)
    private AssociatedVoteType vote;

}
