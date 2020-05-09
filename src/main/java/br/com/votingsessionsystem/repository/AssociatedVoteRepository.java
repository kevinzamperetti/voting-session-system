package br.com.votingsessionsystem.repository;

import br.com.votingsessionsystem.entity.AssociatedVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociatedVoteRepository extends JpaRepository<AssociatedVote, Long> {

    @Query("SELECT av " +
            " FROM AssociatedVote av " +
            " LEFT JOIN FETCH av.votingSession vsession " +
            " LEFT JOIN FETCH av.associated associated")
    List<AssociatedVote> listAll();

    @Query("SELECT av " +
            " FROM AssociatedVote av " +
            " LEFT JOIN FETCH av.votingSession vsession " +
            " LEFT JOIN FETCH av.associated associated" +
            " WHERE associated.id = :idAssociated" +
            "   AND vsession.id = :idVotingSession ")
    Optional<AssociatedVote> findByIdAssociated(@Param("idAssociated") Long idAssociated,
                                                @Param("idVotingSession") Long idVotingSession);

    @Query("SELECT count(av) " +
            " FROM AssociatedVote av " +
            " WHERE av.votingSession.votingSubject.id = :idVotingSubject" +
            "   AND av.vote = 'YES' ")
    int countVoteYesByVotingSubject(Long idVotingSubject);

    @Query("SELECT count(av) " +
            " FROM AssociatedVote av " +
            " WHERE av.votingSession.votingSubject.id = :idVotingSubject" +
            "   AND av.vote = 'NO' ")
    int countVoteNoByVotingSubject(Long idVotingSubject);

}
