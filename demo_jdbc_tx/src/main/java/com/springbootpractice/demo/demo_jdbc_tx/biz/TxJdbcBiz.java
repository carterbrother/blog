package com.springbootpractice.demo.demo_jdbc_tx.biz;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 * 说明：代码方式事务编程 VS 申明式事物编程
 * @author carter
 * 创建时间： 2020年01月08日 11:02 上午
 **/
@Service
public class TxJdbcBiz {

    private final JdbcTemplate jdbcTemplate;


    public TxJdbcBiz(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @SneakyThrows
    public int insertUserLogin(String username, String note) {
        Connection connection = null;
        int result = 0;
        try {
            connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_login(user_name,password,sex,note)  VALUES(?,?,?,?)");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, "abc123");
            preparedStatement.setInt(3, 1);
            preparedStatement.setString(4, note);

            result = preparedStatement.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            Optional.ofNullable(connection)
                    .ifPresent(item -> {
                        try {
                            item.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
            e.printStackTrace();
        } finally {
            Optional.ofNullable(connection)
                    .filter(this::closeConnection)
                    .ifPresent(item -> {
                        try {
                            item.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
        }
        return result;
    }

    private boolean closeConnection(Connection item) {
        try {
            return !item.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Transactional
    public int insertUserLoginTransaction(String username, String note) {
        String sql = "INSERT INTO user_login(user_name,password,sex,note)  VALUES(?,?,?,?)";
        Object[] params = {username, "abc123", 1, note};
        return jdbcTemplate.update(sql, params);
    }

}
