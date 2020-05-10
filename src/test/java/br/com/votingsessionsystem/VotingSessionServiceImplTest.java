package br.com.votingsessionsystem;

import br.com.votingsessionsystem.entity.VotingSession;
import br.com.votingsessionsystem.entity.VotingSubject;
import br.com.votingsessionsystem.exception.VotingSessionNameNotProvidedException;
import br.com.votingsessionsystem.exception.VotingSessionVotingSubjectNotProvidedException;
import br.com.votingsessionsystem.exception.VotingSubjectAlreadyHasAVotingSessionException;
import br.com.votingsessionsystem.exception.VotingSubjectIdNotFoundException;
import br.com.votingsessionsystem.repository.VotingSessionRepository;
import br.com.votingsessionsystem.repository.VotingSubjectRepository;
import br.com.votingsessionsystem.service.impl.VotingSessionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionServiceImplTest {

    @InjectMocks
    private VotingSessionServiceImpl service;

    @Mock
    private VotingSessionRepository repository;

    @Mock
    private VotingSubjectRepository votingSubjectRepository;

    @Test(expected = VotingSessionNameNotProvidedException.class)
    public void shouldThowExceptionVotingSessionNameNotProvided() {

        VotingSession votingSession = VotingSession.builder().name("  ").build();

        service.save(votingSession);

        verify(service, times(0)).save(votingSession);
    }

    @Test(expected = VotingSessionVotingSubjectNotProvidedException.class)
    public void shouldThowExceptionVotingSessionVotingSubjectNotProvided() {

        VotingSession votingSession = VotingSession.builder().name("Sessão 1").votingSubject(VotingSubject.builder().build()).build();

        service.save(votingSession);

        verify(service, times(0)).save(votingSession);
    }

    @Test(expected = VotingSubjectIdNotFoundException.class)
    public void shouldThowExceptionVotingSubjectIdNotFound() {

        VotingSession votingSession =
                VotingSession.builder().name("Sessão 1").votingSubject(VotingSubject.builder().subject("Pauta").build()).build();
        votingSession.getVotingSubject().setId(1L);

        service.save(votingSession);

        verify(service, times(0)).save(votingSession);
    }

    @Test(expected = VotingSubjectAlreadyHasAVotingSessionException.class)
    public void shouldThowExceptionVotingSubjectAlreadyHasAVotingSession() {

        VotingSession votingSession =
                VotingSession.builder().name("Sessão 1").votingSubject(VotingSubject.builder().subject("Pauta").build()).build();
        votingSession.getVotingSubject().setId(1L);

        when(votingSubjectRepository.findById(1L)).thenReturn(Optional.of(VotingSubject.builder().build()));
        when(repository.findVotingSessionByVotingSubject_Id(1L)).thenReturn(Optional.of(VotingSession.builder().build()));

        service.save(votingSession);

        verify(service, times(0)).save(votingSession);
    }

    @Test
    public void whenSaveVotingSessionSuccessfully() {

        VotingSession votingSession =
                VotingSession.builder().name("Sessão 1").votingSubject(VotingSubject.builder().subject("Pauta").build()).build();
        votingSession.getVotingSubject().setId(1L);

        when(votingSubjectRepository.findById(1L)).thenReturn(Optional.of(VotingSubject.builder().build()));
        when(repository.findVotingSessionByVotingSubject_Id(1L)).thenReturn(Optional.empty());

        service.save(votingSession);

        verify(repository, times(1)).save(votingSession);
    }

}
