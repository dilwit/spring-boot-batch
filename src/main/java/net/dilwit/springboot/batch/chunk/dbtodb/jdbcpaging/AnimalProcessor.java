package net.dilwit.springboot.batch.chunk.dbtodb.jdbcpaging;

import net.dilwit.springboot.batch.domain.Animal;
import net.dilwit.springboot.batch.domain.AnimalCaps;
import net.dilwit.springboot.batch.domain.Student;
import net.dilwit.springboot.batch.domain.StudentConcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AnimalProcessor implements ItemProcessor<Animal, AnimalCaps> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalProcessor.class);

    @Override
    public AnimalCaps process(Animal animal) {

        LOGGER.info("Start processing...");
        return new AnimalCaps(animal.toString());
    }
}
