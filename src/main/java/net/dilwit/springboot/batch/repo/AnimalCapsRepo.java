package net.dilwit.springboot.batch.repo;

import net.dilwit.springboot.batch.domain.AnimalCaps;
import net.dilwit.springboot.batch.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalCapsRepo extends JpaRepository<AnimalCaps, Long> {
}
