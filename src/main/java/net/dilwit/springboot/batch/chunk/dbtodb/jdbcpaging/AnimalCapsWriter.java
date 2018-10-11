package net.dilwit.springboot.batch.chunk.dbtodb.jdbcpaging;

import net.dilwit.springboot.batch.domain.AnimalCaps;
import net.dilwit.springboot.batch.domain.StudentConcat;
import net.dilwit.springboot.batch.repo.AnimalCapsRepo;
import net.dilwit.springboot.batch.repo.StudentConcatRepo;
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
public class AnimalCapsWriter implements ItemWriter<AnimalCaps>, StepExecutionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(AnimalCapsWriter.class);

    @Autowired
    private AnimalCapsRepo animalCapsRepo;

    @Override
    @Transactional
    public void write(List<? extends AnimalCaps> list) {

        LOGGER.info("Start writing...");
        animalCapsRepo.saveAll(list);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
