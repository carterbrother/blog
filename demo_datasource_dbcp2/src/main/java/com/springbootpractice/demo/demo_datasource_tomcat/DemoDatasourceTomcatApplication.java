package com.springbootpractice.demo.demo_datasource_tomcat;

import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.UserLoginEntity;
import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.enums.SexEnum;
import com.springbootpractice.demo.demo_datasource_tomcat.dao.jdbc.IUserJdbcBiz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication()
public class DemoDatasourceTomcatApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoDatasourceTomcatApplication.class, args);

		final DataSource dataSource = applicationContext.getBean(DataSource.class);

		System.out.println(dataSource.getClass().getName());


		final IUserJdbcBiz userJdbcBiz = applicationContext.getBean(IUserJdbcBiz.class);


		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}



}
