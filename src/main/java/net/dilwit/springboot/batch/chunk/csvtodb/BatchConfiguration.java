package net.dilwit.springboot.batch.chunk.csvtodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    Reader reader;

    @Autowired
    Processor processor;

    @Autowired
    Writer writer;

    @Autowired
    JobListener listener;

    @Bean(name = "csv-to-db-job")
    public Job csvToDbJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Person, Person> chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

        Job job = jobBuilderFactory.get("csv-to-db-job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step)
                .build();

        return job;
    }
}
