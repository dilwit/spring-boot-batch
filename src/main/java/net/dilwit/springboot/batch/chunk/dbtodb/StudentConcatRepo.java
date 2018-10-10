package net.dilwit.springboot.batch.chunk.dbtodb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentConcatRepo extends JpaRepository<StudentConcat, Long> {
}
