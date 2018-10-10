package net.dilwit.springboot.batch.chunk.dbtocsv;

import net.dilwit.springboot.batch.chunk.dbtodb.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentProcessor implements ItemProcessor<Student, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);

    @Override
    public String process(Student student) {

        LOGGER.info("Start processing...");
        return student.toString();
    }
}
