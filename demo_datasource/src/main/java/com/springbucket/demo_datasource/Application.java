package com.springbucket.demo_datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * Callback used to run the bean.
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    private void showData() {

        jdbcTemplate.queryForList("select * from FOO ").forEach((key) -> {
            log.info("{}", key.toString());
        });

    }

    private void showConnection() throws SQLException {

        log.info(dataSource.toString());

        Connection connection = dataSource.getConnection();

        log.info(connection.toString());

        connection.close();


    }
}
