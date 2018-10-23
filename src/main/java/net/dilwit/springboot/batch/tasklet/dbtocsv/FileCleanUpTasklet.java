package net.dilwit.springboot.batch.tasklet.dbtocsv;

import net.dilwit.springboot.batch.chunk.csvtodb.CsvToDbBatchConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileCleanUpTasklet implements Tasklet {

    private static Logger LOGGER = LoggerFactory.getLogger(CsvToDbBatchConfiguration.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        LOGGER.info("Start file clean up task...");

        try{
            File file = new File("/tmp/output-vehicle-data.csv");
            file.delete();
        } catch(Exception e){
            LOGGER.error(e.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
}
