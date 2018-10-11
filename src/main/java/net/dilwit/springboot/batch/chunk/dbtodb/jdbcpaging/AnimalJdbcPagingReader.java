package net.dilwit.springboot.batch.chunk.dbtodb.jdbcpaging;

import net.dilwit.springboot.batch.domain.Animal;
import net.dilwit.springboot.batch.domain.Student;
import net.dilwit.springboot.batch.repo.AnimalRepo;
import net.dilwit.springboot.batch.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.H2PagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class AnimalJdbcPagingReader extends JdbcPagingItemReader<Animal> implements StepExecutionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(AnimalJdbcPagingReader.class);

    @Autowired
    private AnimalRepo animalRepo;

    @Autowired
    AnimalJdbcPagingReader(DataSource dataSource) {

        super();

        setDataSource(dataSource);
        setRowMapper(new BeanPropertyRowMapper<>(Animal.class));

        H2PagingQueryProvider h2PagingQueryProvider = new H2PagingQueryProvider();
        h2PagingQueryProvider.setFromClause("from animal");
        h2PagingQueryProvider.setSelectClause("select id, name ");

        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("name", Order.ASCENDING);
        h2PagingQueryProvider.setSortKeys(sortKeys);

        setQueryProvider(h2PagingQueryProvider);
        setPageSize(3);
    }

    @Override
    protected void doReadPage() {
        LOGGER.info("Start reading page...");
        super.doReadPage();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        if(animalRepo.count() <= 0) {
            stepExecution.setStatus(BatchStatus.FAILED);
            LOGGER.error("No animal records found in the database");
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
