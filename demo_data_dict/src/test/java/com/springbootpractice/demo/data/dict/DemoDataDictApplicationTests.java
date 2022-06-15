package com.springbootpractice.demo.data.dict;

import com.springbootpractice.demo.data.dict.dao.MysqlDao;
import com.springbootpractice.demo.data.dict.param.bo.ColumnBo;
import com.springbootpractice.demo.data.dict.param.bo.IndexBo;
import com.springbootpractice.demo.data.dict.param.bo.TableBo;
import com.springbootpractice.demo.data.dict.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class DemoDataDictApplicationTests {


	@Autowired
	private MysqlDao mysqlDao;
	private String databaseName = "cloudpivot_ynjt";
	String tableName = "base_security_client";

	@BeforeEach
	void setUp() {
		mysqlDao.rebuildDataSource("jdbc:mysql://127.0.0.1:3306/cloudpivot_ynjt", "root","test123456");
	}

	@Test
	void testTableCount() {

		Map<String, TableBo> tableBoMap = mysqlDao.getTableBoMap(databaseName);

		log.info("数据库:{}的表数量是：{}",databaseName,tableBoMap.keySet().size());

	}

	@Test
	void testTableDataCount() {

		Long tableDataCount = mysqlDao.getTableDataCount(databaseName, tableName);

		log.info("表:{}的数据量是：{}", tableName, tableDataCount);
	}

	@Test
	void testFirstRow() {
		Map<String, Object> firstRow = mysqlDao.getRowData(databaseName, tableName,true);
		log.info("表:{} 的第一行数据是：{}", tableName, JsonUtil.toJson(firstRow));
	}

	@Test
	void testColumn() {
		List<ColumnBo> columnBoList = mysqlDao.getTableNameDataDictBoListMap(databaseName).get(tableName);

		log.info("表:{} 的字段信息是：{}",tableName,JsonUtil.toJson(columnBoList));
	}

	@Test
	void testIndexs() {
		Map<String, List<IndexBo>> nameIndexBoListMap = mysqlDao.getIndexNameIndexBoListMap(databaseName, tableName);

		log.info("表:{} 的索引是：{}",tableName,JsonUtil.toJson(nameIndexBoListMap));
	}

	@AfterEach
	void tearDown() {
		mysqlDao.autoCloseDataSource();
	}
}
