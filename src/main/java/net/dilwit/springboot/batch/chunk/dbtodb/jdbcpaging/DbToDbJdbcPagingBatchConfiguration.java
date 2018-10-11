package net.dilwit.springboot.batch.chunk.dbtodb.jdbcpaging;

import net.dilwit.springboot.batch.domain.Animal;
import net.dilwit.springboot.batch.domain.AnimalCaps;
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
DbToDbJdbcPagingBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private AnimalCapsWriter animalCapsWriter;

    @Autowired
    private AnimalJdbcPagingReader animalJdbcPagingReader;

    @Autowired
    private AnimalProcessor animalProcessor;

    @Autowired
    private DbToDbPagingBatchListener dbToDbPagingBatchListener;


    @Bean(name = "db-to-db-jdbc-paging-job")
    public Job dbToDbJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Animal, AnimalCaps> chunk(2) // Determines how (reading is determined by pageSize) processing is done but writing is done only once per chunk. Ref console logs.
                .reader(animalJdbcPagingReader)
                .processor(animalProcessor)
                .writer(animalCapsWriter)
                .build();

        Job job = jobBuilderFactory.get("db-to-db-jdbc-paging-job")
                .incrementer(new RunIdIncrementer())
                .listener(dbToDbPagingBatchListener)
                .start(step)
                .build();

        return job;
    }
}
