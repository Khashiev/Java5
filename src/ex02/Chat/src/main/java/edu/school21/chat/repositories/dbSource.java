package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class dbSource {
    private static final String PROPERTIES = "db.properties";
    private final HikariDataSource dataSource;

    public dbSource() {
        try {
            Properties properties = new Properties();
            properties.load(dbSource.class.getClassLoader().getResourceAsStream(PROPERTIES));
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(properties.getProperty("URL"));
            config.setUsername(properties.getProperty("USERNAME"));
            config.setPassword(properties.getProperty("PASSWORD"));

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}