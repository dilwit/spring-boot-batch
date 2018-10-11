package net.dilwit.springboot.batch.repo;

import net.dilwit.springboot.batch.domain.Animal;
import net.dilwit.springboot.batch.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
}
