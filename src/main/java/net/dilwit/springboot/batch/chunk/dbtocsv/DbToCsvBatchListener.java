package net.dilwit.springboot.batch.chunk.dbtocsv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbToCsvBatchListener extends JobExecutionListenerSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(DbToCsvBatchListener.class);


    @Override
    public void beforeJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            LOGGER.info("BATCH JOB (db-to-csv-job) STARTED SUCCESSFULLY");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("BATCH JOB (db-to-csv-job) COMPLETED SUCCESSFULLY");
        }
    }
}
