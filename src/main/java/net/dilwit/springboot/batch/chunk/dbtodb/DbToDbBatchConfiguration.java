package net.dilwit.springboot.batch.chunk.dbtodb;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbToDbBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StudentConcatWriter studentConcatWriter;

    @Autowired
    private StudentReader studentReader;

    @Autowired
    private StudentToStudentConcatProcessor studentToStudentConcatProcessor;

    @Autowired
    private DbToDbBatchListener dbToDbBatchListener;


    @Bean(name = "db-to-db-job")
    public Job dbToDbJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Student, StudentConcat> chunk(3) // Determines how reading, processing is done but writing is done only once per chunk. Ref console logs.
                .reader(studentReader)
                .processor(studentToStudentConcatProcessor)
                .writer(studentConcatWriter)
                .build();

        Job job = jobBuilderFactory.get("db-to-db-job")
                .incrementer(new RunIdIncrementer())
                .listener(dbToDbBatchListener)
                .start(step)
                .build();

        return job;
    }
}
