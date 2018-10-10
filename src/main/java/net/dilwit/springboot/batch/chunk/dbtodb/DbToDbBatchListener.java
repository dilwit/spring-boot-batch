package net.dilwit.springboot.batch.chunk.dbtodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbToDbBatchListener extends JobExecutionListenerSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(DbToDbBatchListener.class);

    @Autowired
    private StudentConcatRepo studentConcatRepo;

    @Override
    public void beforeJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            LOGGER.info("BATCH JOB (db-to-db-job) STARTED SUCCESSFULLY");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        if (studentConcatRepo.count() > 0 && jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("BATCH JOB (db-to-db-job) COMPLETED SUCCESSFULLY");
        }
    }
}
