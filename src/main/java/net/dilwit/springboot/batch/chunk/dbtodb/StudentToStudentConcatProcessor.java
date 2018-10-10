package net.dilwit.springboot.batch.chunk.dbtodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentToStudentConcatProcessor implements ItemProcessor<Student, StudentConcat> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentToStudentConcatProcessor.class);

    @Override
    public StudentConcat process(Student student) {

        LOGGER.info("Start processing...");
        return new StudentConcat(student.toString());
    }
}
