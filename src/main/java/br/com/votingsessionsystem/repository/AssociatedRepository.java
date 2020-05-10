package br.com.votingsessionsystem.repository;

import br.com.votingsessionsystem.entity.Associated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, Long> {

}
