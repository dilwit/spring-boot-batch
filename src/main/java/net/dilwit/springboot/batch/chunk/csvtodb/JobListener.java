package net.dilwit.springboot.batch.chunk.csvtodb;

import net.dilwit.springboot.batch.repo.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobListener extends JobExecutionListenerSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(JobListener.class);

    @Autowired
    private PersonRepo personRepo;

    @Override
    public void beforeJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            LOGGER.info("BATCH JOB (csv-to-db-job) STARTED SUCCESSFULLY");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        long count = personRepo.count();

        if (jobExecution.getStatus() == BatchStatus.COMPLETED && count == 5) {
            LOGGER.info("BATCH JOB (csv-to-db-job) COMPLETED SUCCESSFULLY");
        }
    }
}
