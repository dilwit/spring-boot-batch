package net.dilwit.springboot.batch.chunk.csvtodb;

import net.dilwit.springboot.batch.domain.Person;
import net.dilwit.springboot.batch.repo.PersonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class Writer implements ItemWriter<Person> {

    private static Logger LOGGER = LoggerFactory.getLogger(Writer.class);

    @Autowired
    private PersonRepo personRepo;

    @Override
    @Transactional
    public void write(List<? extends Person> list) {

        LOGGER.info("Start writing...");
        personRepo.saveAll(list);
    }
}
