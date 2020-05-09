package br.com.votingsessionsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@AttributeOverrides({@AttributeOverride(name="id", column=@Column(name="id_associated"))})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Associated extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "associated", fetch = LAZY)
    private Set<AssociatedVote> votes;
}
