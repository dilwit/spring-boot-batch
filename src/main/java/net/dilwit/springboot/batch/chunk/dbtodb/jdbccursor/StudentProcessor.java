package net.dilwit.springboot.batch.chunk.dbtodb.jdbccursor;

import net.dilwit.springboot.batch.domain.Student;
import net.dilwit.springboot.batch.domain.StudentConcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentProcessor implements ItemProcessor<Student, StudentConcat> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);

    @Override
    public StudentConcat process(Student student) {

        LOGGER.info("Start processing...");
        return new StudentConcat(student.toString());
    }
}
