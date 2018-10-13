package net.dilwit.springboot.batch.chunk.dbtocsv;

import net.dilwit.springboot.batch.domain.Vehicle;
import net.dilwit.springboot.batch.domain.Student;
import net.dilwit.springboot.batch.repo.StudentRepo;
import net.dilwit.springboot.batch.repo.VehicleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class VehicleJdbcCursorReader extends JdbcCursorItemReader<Vehicle>  implements StepExecutionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(VehicleJdbcCursorReader.class);

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    VehicleJdbcCursorReader(DataSource dataSource) {

        super();

        setDataSource(dataSource);
        setRowMapper(new BeanPropertyRowMapper<>(Vehicle.class));
        setSql("select id, name from vehicle");
    }

    @Override
    protected Vehicle readCursor(ResultSet rs, int currentRow) throws SQLException {
        LOGGER.info("Start cursor reading...");
        return super.readCursor(rs, currentRow);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        if(vehicleRepo.count() <= 0) {
            stepExecution.setStatus(BatchStatus.FAILED);
            LOGGER.error("No vehicle records found in the database");
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
