package net.dilwit.springboot.batch.chunk.dbtodb.jdbccursor;

import net.dilwit.springboot.batch.domain.Student;
import net.dilwit.springboot.batch.domain.StudentConcat;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class
DbToDbJdbcCursorBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StudentConcatWriter studentConcatWriter;

    @Autowired
    private StudentJdbcCursorReader studentJdbcCursorReader;

    @Autowired
    private StudentProcessor studentProcessor;

    @Autowired
    private DbToDbJdbcCursorBatchListener dbToDbJdbcCursorBatchListener;


    @Bean(name = "db-to-db-jdbc-cursor-job")
    public Job dbToDbJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Student, StudentConcat> chunk(3) // Determines how reading, processing is done but writing is done only once per chunk. Ref console logs.
                .reader(studentJdbcCursorReader)
                .processor(studentProcessor)
                .writer(studentConcatWriter)
                .build();

        Job job = jobBuilderFactory.get("db-to-db-jdbc-cursor-job")
                .incrementer(new RunIdIncrementer())
                .listener(dbToDbJdbcCursorBatchListener)
                .start(step)
                .build();

        return job;
    }
}
