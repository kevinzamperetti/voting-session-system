package br.com.votingsessionsystem.service.impl;

import br.com.votingsessionsystem.entity.Associated;
import br.com.votingsessionsystem.repository.AssociatedRepository;
import br.com.votingsessionsystem.service.AssociatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociatedServiceImpl implements AssociatedService {

    private final AssociatedRepository repository;

    @Override
    public List<Associated> listAll() {
        return repository.findAll();
    }

}
