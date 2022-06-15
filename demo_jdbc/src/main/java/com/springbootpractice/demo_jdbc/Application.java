package com.springbootpractice.demo_jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String ... args) throws Exception {
		showConnection();
	}

	private void showConnection() throws SQLException {

		System.out.println(dataSource);

		Connection connection = dataSource.getConnection();

		System.out.println(connection);

		connection.close();

	}


}
