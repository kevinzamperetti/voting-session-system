package br.com.votingsessionsystem.repository;

import br.com.votingsessionsystem.entity.VotingSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingSubjectRepository extends JpaRepository<VotingSubject, Long> {

    @Query("SELECT vs " +
            " FROM VotingSubject vs" +
            " LEFT JOIN FETCH vs.votingSession vsession " +
            "ORDER BY vs.subject")
    List<VotingSubject> listAll();

}
