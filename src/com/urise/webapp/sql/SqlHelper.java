package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, statement -> statement.execute());
    }

    public <T> T execute(String sql, SqlPrepare<T> sqlPrepare) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            return sqlPrepare.execute(preparedStatement);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) throw new ExistStorageException(null);
            else throw new StorageException(e);
        }
    }

    public interface SqlPrepare<T> {
        T execute(PreparedStatement statement) throws SQLException;
    }
}
