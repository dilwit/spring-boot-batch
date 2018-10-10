package net.dilwit.springboot.batch.chunk.dbtodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class StudentReader extends JdbcCursorItemReader<Student>  implements StepExecutionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentReader.class);

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    StudentReader(DataSource dataSource) {

        super();

        setDataSource(dataSource);
        setRowMapper(new BeanPropertyRowMapper<>(Student.class));
        setSql("select id, name, passport_number from student");
    }

    @Override
    public Student read() throws Exception {
        LOGGER.info("Start reading...");
        return super.read();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        if(studentRepo.count() <= 0) {
            stepExecution.setStatus(BatchStatus.FAILED);
            LOGGER.error("No students records found in the database");
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
