package net.dilwit.springboot.batch.chunk.dbtocsv;

import net.dilwit.springboot.batch.chunk.dbtodb.Student;
import net.dilwit.springboot.batch.chunk.dbtodb.StudentReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbToCsvBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StudentWriter studentWriter;

    @Autowired
    private StudentReader studentReader;

    @Autowired
    private StudentProcessor studentProcessor;

    @Autowired
    private DbToCsvBatchListener dbToCsvBatchListener;


    @Bean(name = "db-to-csv-job")
    public Job dbToDbJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Student, String> chunk(4) // Determines how reading, processing is done but writing is done only once per chunk. Ref console logs.
                .reader(studentReader)
                .processor(studentProcessor)
                .writer(studentWriter)
                .build();

        Job job = jobBuilderFactory.get("db-to-csv-job")
                .incrementer(new RunIdIncrementer())
                .listener(dbToCsvBatchListener)
                .start(step)
                .build();

        return job;
    }
}
