package br.com.votingsessionsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="id_voting_session"))})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotingSession extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "closing_date", nullable = false)
    private LocalDateTime closingDate;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "id_voting_subject", referencedColumnName = "id_voting_subject")
    private VotingSubject votingSubject;

    @OneToMany(mappedBy = "associated", fetch = LAZY)
    private Set<AssociatedVote> votes;

}
