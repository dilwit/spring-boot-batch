package net.dilwit.springboot.batch.repo;

import net.dilwit.springboot.batch.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo  extends JpaRepository<Person, Long> {
}
