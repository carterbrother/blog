package com.springboottest.singledatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@Slf4j
public class SingleDatasourceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SingleDatasourceApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {

		log.info(dataSource.toString());

		final Connection connection = dataSource.getConnection();

		log.info(connection.toString());


	}
}
