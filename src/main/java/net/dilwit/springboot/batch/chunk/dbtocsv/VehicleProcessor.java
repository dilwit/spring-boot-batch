package net.dilwit.springboot.batch.chunk.dbtocsv;

import net.dilwit.springboot.batch.domain.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class VehicleProcessor implements ItemProcessor<Vehicle, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleProcessor.class);

    @Override
    public String process(Vehicle  vehicle) {

        LOGGER.info("Start processing...");
        return vehicle.toString();
    }
}
