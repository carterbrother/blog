package com.springbootpractice.demo.demo_datasource_tomcat;

import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.UserLoginEntity;
import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.enums.SexEnum;
import com.springbootpractice.demo.demo_datasource_tomcat.dao.jdbc.IUserJdbcBiz;
import com.springbootpractice.demo.demo_datasource_tomcat.dao.jdbc.impl.UserJdbcBiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@SpringBootTest
class DemoDatasourceTomcatApplicationTests {

	@Autowired
	private IUserJdbcBiz userJdbcBiz;

	@Test
	void crateUserLoginTest() {

		final long id = userJdbcBiz.createUserLogin(UserLoginEntity.builder()
				.userName("carter.li")
				.password("abc123")
				.sex(SexEnum.MALE)
				.note("第一个账号")
				.build());

		final UserLoginEntity userLogin = userJdbcBiz.getUserLogin(id);

		Assert.isTrue(Objects.equals(id,userLogin.getId()),"插入失败");

	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void twoOperationOneConnectionTest() {

		final UserLoginEntity result = jdbcTemplate.execute((StatementCallback<UserLoginEntity>) statement -> {

			//先插入

			final int i = statement.executeUpdate("INSERT INTO user_login(user_name, password, sex, note) VALUES ('gemini.he','abc123456',2,'我是美女！')");

			//然后查询
			final ResultSet resultSet = statement.executeQuery("SELECT id,user_name,password,sex,note FROM user_login WHERE user_name='gemini.he'");

			 UserLoginEntity userLoginEntity =null;
			while (resultSet.next()){
			userLoginEntity = UserJdbcBiz.getUserLoginMapper().mapRow(resultSet, resultSet.getRow());
			}

			return userLoginEntity;
		});

		Assert.isTrue(Objects.equals("gemini.he",result.getUserName()),"先插入后查询失败");

	}
}
