package net.dilwit.springboot.batch.chunk.dbtodb.jdbcpaging;

import net.dilwit.springboot.batch.repo.StudentConcatRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbToDbPagingBatchListener extends JobExecutionListenerSupport {

    private static Logger LOGGER = LoggerFactory.getLogger(DbToDbPagingBatchListener.class);

    @Autowired
    private StudentConcatRepo studentConcatRepo;

    @Override
    public void beforeJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            LOGGER.info("BATCH JOB (db-to-db-jdbc-paging-job) STARTED SUCCESSFULLY");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        if (studentConcatRepo.count() > 0 && jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("BATCH JOB (db-to-db-jdbc-paging-job) COMPLETED SUCCESSFULLY");
        }
    }
}
