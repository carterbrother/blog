package com.zengame.platweb.service;

import cn.hutool.core.date.StopWatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  20:03
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class DbBatchService {

    //批量插入数据 10000条耗时

    private final JdbcTemplate jdbcTemplate;

    public void db() {
        jdbcTemplate.execute("create TABLE IF NOT EXISTS  `user1` (\n" + " `id` bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + " `name` varchar(255) NOT NULL,\n"
                + " PRIMARY KEY (`id`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

        log.info("clean and create table success ！");

        StopWatch stopWatch = new StopWatch("batchInsert");
        stopWatch.start();

        String sql = "INSERT INTO `user1` (`name`) VALUES (?)";
        //使用JDBC批量更新
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, "zenGame" + i);
            }
            @Override
            public int getBatchSize() {
                return 10000;
            }
        });
        stopWatch.stop();
        log.info("batch insert 1w records took : {} ms", stopWatch.getLastTaskTimeMillis());
    }
}
