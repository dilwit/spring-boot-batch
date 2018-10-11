package net.dilwit.springboot.batch.repo;

import net.dilwit.springboot.batch.domain.StudentConcat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentConcatRepo extends JpaRepository<StudentConcat, Long> {
}
