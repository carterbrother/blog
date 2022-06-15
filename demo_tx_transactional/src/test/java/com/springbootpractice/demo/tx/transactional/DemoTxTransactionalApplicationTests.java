package com.springbootpractice.demo.tx.transactional;

import com.springbootpractice.demo.tx.transactional.biz.BatchUserLoginBiz;
import com.springbootpractice.demo.tx.transactional.biz.UserLoginBiz;
import com.springbootpractice.demo.tx.transactional.dao.entity.UserLoginEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class DemoTxTransactionalApplicationTests {


	@Autowired
	private UserLoginBiz userLoginBiz;

	@Autowired
	private BatchUserLoginBiz batchUserLoginBiz;

	@Test
	void getUserLoginByIdTest() {

		final UserLoginEntity userLoginEntity = userLoginBiz.getUserById(1);
		Assert.isTrue(Objects.equals(1,userLoginEntity.getId()),"查询失败");

	}

	@Test
	void insertUserLoginTest() {
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		userLoginEntity.setUserName("testMybatis");
		userLoginEntity.setPassword("abc123");
		userLoginEntity.setSex((byte) 1);
		userLoginEntity.setNote("xxxx");

		final Integer i = userLoginBiz.insertUser(userLoginEntity);

		System.out.println("i="+i);
		Assert.isTrue(i>0,"插入用户失败");
	}


	@Test
	void requiredTest() {
		List<UserLoginEntity> userLoginEntityList = Arrays.asList(
				UserLoginEntity.builder().userName("xxx").password("abc123").sex((byte) 1).note("xxx").build(),
				UserLoginEntity.builder().userName("yyy").password("abc123").sex((byte) 1).note("xxx").build(),
				UserLoginEntity.builder().userName("zzz").password("abc123").sex((byte) 1).note("xxx").build()
		);
		final int i = batchUserLoginBiz.insertUserLoginListRequired(userLoginEntityList);

		System.out.println("requiredTest: "+ i);
		Assert.isTrue(i>=3,"插入失败");
	}

	@Test
	void requiredNewTest() {
		List<UserLoginEntity> userLoginEntityList = Arrays.asList(
				UserLoginEntity.builder().userName("xxx").password("abc123").sex((byte) 1).note("xxx").build(),
				UserLoginEntity.builder().userName("yyy").password("abc123").sex((byte) 1).note("xxx").build(),
				UserLoginEntity.builder().userName("zzz").password("abc123").sex((byte) 1).note("xxx").build()
		);
		final int i = batchUserLoginBiz.insertUserLoginListRequired(userLoginEntityList);

		System.out.println("requiredTest: "+ i);
		Assert.isTrue(i>=3,"插入失败");
	}

	@Test
	void nestTest() {
		List<UserLoginEntity> userLoginEntityList = Arrays.asList(
				UserLoginEntity.builder().userName("xxx").password("abc123").sex((byte) 1).note("xxx").build(),
				UserLoginEntity.builder().userName("yyy").password("abc123").sex((byte) 1).note("xxx").build(),
				UserLoginEntity.builder().userName("zzz").password("abc123").sex((byte) 1).note("xxx").build()
		);
		final int i = batchUserLoginBiz.insertUserLoginListRequired(userLoginEntityList);

		System.out.println("requiredTest: "+ i);
		Assert.isTrue(i>=3,"插入失败");
	}
}
