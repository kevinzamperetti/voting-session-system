package br.com.votingsessionsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="id_voting_subject"))})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotingSubject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String subject;

    @JsonIgnoreProperties({"votingSubject", "votes"})
    @OneToOne(mappedBy = "votingSubject", fetch = LAZY)
    private VotingSession votingSession;

}
