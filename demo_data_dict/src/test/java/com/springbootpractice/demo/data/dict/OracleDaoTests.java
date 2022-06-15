package com.springbootpractice.demo.data.dict;

import com.springbootpractice.demo.data.dict.dao.OracleDao;
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
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class OracleDaoTests {


	@Autowired
	private OracleDao oracleDao;
	private String databaseName = "cloudpivot_ynjt";
	String tableName = "base_security_client";

	@BeforeEach
	void setUp() {
		oracleDao.rebuildDataSource("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "DEV3","H3password");
	}

	@Test
	void testTableCount() {

		Map<String, TableBo> tableBoMap = oracleDao.getTableBoMap(databaseName);
		Assert.notEmpty(tableBoMap, "数据库一定有表");
		log.info("数据库:{}的表数量是：{}",databaseName,tableBoMap.keySet().size());

	}

	@Test
	void testTableDataCount() {

		Long tableDataCount = oracleDao.getTableDataCount(databaseName, tableName);

		Assert.isTrue(tableDataCount>=0, "表的数据量应该大于等于0");
		log.info("表:{}的数据量是：{}", tableName, tableDataCount);
	}

	@Test
	void testFirstRow() {
		Map<String, Object> firstRow = oracleDao.getRowData(databaseName, tableName,true);
		Assert.notNull(firstRow, "第一行数据");
		log.info("表:{} 的第一行数据是：{}", tableName, JsonUtil.toJson(firstRow));
	}

	@Test
	void testColumn() {
		List<ColumnBo> columnBoList = oracleDao.getTableNameDataDictBoListMap(databaseName,tableName).get(tableName);

		Assert.notEmpty(columnBoList, "列不可能为空");
		log.info("表:{} 的字段信息是：{}",tableName,JsonUtil.toJson(columnBoList));
	}

	@Test
	void testIndexs() {
		Map<String, List<IndexBo>> nameIndexBoListMap = oracleDao.getIndexNameIndexBoListMap(databaseName, tableName);

		Assert.notNull(nameIndexBoListMap,"索引可能没有");
		log.info("表:{} 的索引是：{}",tableName,JsonUtil.toJson(nameIndexBoListMap));
	}


	@Test
	void testExecuteDDL() {

		String createTableSql="create table biz_workitem2(id varchar2(36) not null, workflowCode varchar2(200), workflowVersion number(11), originator varchar2(200), participant varchar2(200), approval varchar2(200), sheetCode varchar2(200), instanceId varchar2(36), startTime date, finishTime date default CURRENT_TIMESTAMP, state varchar2(36), receiveTime date, activityCode varchar2(200), usedTime number(20), remark varchar2(200), ownerId varchar2(200), activityName varchar2(200), departmentId varchar2(200), departmentName varchar2(200), instanceName varchar2(200), originatorName varchar2(200), participantName varchar2(200), sourceId varchar2(200), sourceName varchar2(200), workItemType varchar2(20), workflowTokenId varchar2(200), stateValue number(11), workItemTypeValue number(11), approvalValue number(11), expireTime1 date, expireTime2 date, appCode varchar2(200), allowedTime date, timeoutWarn1 date, timeoutWarn2 date, timeoutStrategy varchar2(20),  primary key(id))";

		int i1 = oracleDao.executeDDLSql(createTableSql).getRes();
		Assert.isTrue(i1>=0, "执行创建表语句失败");


		String createCommentSql = "comment on column biz_workitem2.id  is '主键'";
		int i2 = oracleDao.executeDDLSql(createCommentSql).getRes();

		Assert.isTrue(i2>=0, "执行建立注释语句失败");

		String createIndexSql = "create index I_ReceiveTime_bw2 on biz_workitem2(receiveTime)";
		int i3 = oracleDao.executeDDLSql(createIndexSql).getRes();

		Assert.isTrue(i3>=0, "执行建立索引语句失败");

		int i4 = oracleDao.executeDDLSql("drop table biz_workitem2 ").getRes();

		Assert.isTrue(i4>=0, "执行删除表语句失败");

	}


	@Test
	void testExecuteDDL2() {

		String createTableSql="create table aaa(id varchar2(50) not null primary key ,date date not null ,level number(2))";

		int i1 = oracleDao.executeDDLSql(createTableSql).getRes();
		Assert.isTrue(i1>=0, "执行创建表语句失败");




		int i4 = oracleDao.executeDDLSql("drop table aaa ").getRes();

		Assert.isTrue(i4>=0, "执行删除表语句失败");

	}

	@AfterEach
	void tearDown() {
		oracleDao.autoCloseDataSource();
	}
}
