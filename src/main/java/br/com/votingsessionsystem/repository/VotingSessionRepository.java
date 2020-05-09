package br.com.votingsessionsystem.repository;

import br.com.votingsessionsystem.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

    Optional<VotingSession> findVotingSessionByVotingSubject_Id(@Param("id") Long id);

    @Query("SELECT vs " +
            " FROM VotingSession vs" +
            " LEFT JOIN FETCH vs.votingSubject vsubject " +
            " LEFT JOIN FETCH vs.votes votes " +
            "ORDER BY vs.closingDate DESC")
     List<VotingSession> listAll();

}
