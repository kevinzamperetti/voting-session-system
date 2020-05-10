package br.com.votingsessionsystem;

import br.com.votingsessionsystem.entity.Associated;
import br.com.votingsessionsystem.entity.dto.AssociatedVoteDto;
import br.com.votingsessionsystem.enums.AssociatedVoteType;
import br.com.votingsessionsystem.exception.AssociatedIdNotFoundException;
import br.com.votingsessionsystem.exception.AssociatedVoteIdAssociatedNotProvidedException;
import br.com.votingsessionsystem.exception.AssociatedVoteIdVotingSessionNotProvidedException;
import br.com.votingsessionsystem.exception.VotingSessionIdNotFoundException;
import br.com.votingsessionsystem.repository.AssociatedRepository;
import br.com.votingsessionsystem.repository.AssociatedVoteRepository;
import br.com.votingsessionsystem.repository.VotingSessionRepository;
import br.com.votingsessionsystem.service.impl.AssociatedVoteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssociatedVoteServiceImplTest {

    @InjectMocks
    private AssociatedVoteServiceImpl service;

    @Mock
    private AssociatedVoteRepository repository;

    @Mock
    private AssociatedRepository associatedRepository;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Test(expected = AssociatedVoteIdAssociatedNotProvidedException.class)
    public void shouldThowExceptionIdAssociatedNotProvided() throws JsonProcessingException {

        AssociatedVoteDto associatedVoteDto = AssociatedVoteDto.builder()
                .idAssociated(null).build();

        service.save(associatedVoteDto);

        verify(service, times(0)).save(associatedVoteDto);
    }

    @Test(expected = AssociatedVoteIdVotingSessionNotProvidedException.class)
    public void shouldThowExceptionIdVotingSessionNotProvided() throws JsonProcessingException {

        AssociatedVoteDto associatedVoteDto = AssociatedVoteDto.builder()
                .idAssociated(1L).idVotingSession(null).build();

        service.save(associatedVoteDto);

        verify(service, times(0)).save(associatedVoteDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThowExceptionVoteNotProvided() throws JsonProcessingException {

        AssociatedVoteDto associatedVoteDto = AssociatedVoteDto.builder()
                .idAssociated(1L).idVotingSession(1L).vote(AssociatedVoteType.valueOf("TESTE")).build();

        service.save(associatedVoteDto);

        verify(service, times(0)).save(associatedVoteDto);
    }

    @Test(expected = AssociatedIdNotFoundException.class)
    public void shouldThowExceptionAssociatedIdNotFound() throws JsonProcessingException {

        AssociatedVoteDto associatedVoteDto = AssociatedVoteDto.builder()
                .idAssociated(1L).idVotingSession(1L).vote(AssociatedVoteType.YES).build();

        service.save(associatedVoteDto);

        verify(service, times(0)).save(associatedVoteDto);
    }

    @Test(expected = VotingSessionIdNotFoundException.class)
    public void shouldThowExceptionVotingSessionIdNotFound() throws JsonProcessingException {

        AssociatedVoteDto associatedVoteDto = AssociatedVoteDto.builder()
                .idAssociated(1L).idVotingSession(1L).vote(AssociatedVoteType.YES).build();

        when(associatedRepository.findById(1L)).thenReturn(Optional.of(Associated.builder().build()));

        service.save(associatedVoteDto);

        verify(service, times(0)).save(associatedVoteDto);
    }

}
