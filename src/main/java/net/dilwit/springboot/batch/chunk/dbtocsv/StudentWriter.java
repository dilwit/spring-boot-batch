package net.dilwit.springboot.batch.chunk.dbtocsv;

import net.dilwit.springboot.batch.chunk.csvtodb.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class StudentWriter extends FlatFileItemWriter<String> implements StepExecutionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentWriter.class);

    public StudentWriter() {
        setLineAggregator((s) -> s);
        setResource(new FileSystemResource("/tmp/output-student-data.csv"));
    }

    @Override
    public void write(List<? extends String> items) throws Exception{
        LOGGER.info("Start writing...");
        super.write(items);
    }


    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
