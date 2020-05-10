package br.com.votingsessionsystem;

import br.com.votingsessionsystem.entity.VotingSubject;
import br.com.votingsessionsystem.exception.VotingSubjectIdMustNotBeProvidedException;
import br.com.votingsessionsystem.exception.VotingSubjectSubjectNotProvidedException;
import br.com.votingsessionsystem.repository.VotingSubjectRepository;
import br.com.votingsessionsystem.service.impl.VotingSubjectServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VotingSubjectServiceImplTest {

    @InjectMocks
    private VotingSubjectServiceImpl service;

    @Mock
    private VotingSubjectRepository repository;

    @Test(expected = VotingSubjectIdMustNotBeProvidedException.class)
    public void shouldThowExceptionVotingSubjectIdMustNotBeProvided() {

        VotingSubject votingSubject = VotingSubject.builder().build();
        votingSubject.setId(1L);

        service.save(votingSubject);

        verify(service, times(0)).save(votingSubject);
    }

    @Test(expected = VotingSubjectSubjectNotProvidedException.class)
    public void shouldThowExceptionVotingSubjectSubjectNotProvided() {

        VotingSubject votingSubject = VotingSubject.builder().build();

        service.save(votingSubject);

        verify(service, times(0)).save(votingSubject);
    }

    @Test
    public void whenSaveVotingSubjectSuccessfully() {

        VotingSubject votingSubject = VotingSubject.builder().subject("Pauta 1").build();
        service.save(votingSubject);

        verify(repository, times(1)).save(votingSubject);
    }

}
