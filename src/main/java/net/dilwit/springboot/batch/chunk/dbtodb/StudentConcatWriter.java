package net.dilwit.springboot.batch.chunk.dbtodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class StudentConcatWriter implements ItemWriter<StudentConcat>, StepExecutionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentConcatWriter.class);

    @Autowired
    private StudentConcatRepo studentConcatRepo;

    @Override
    @Transactional
    public void write(List<? extends StudentConcat> list) {

        LOGGER.info("Start writing...");
        studentConcatRepo.saveAll(list);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
