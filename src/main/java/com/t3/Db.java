package com.t3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Db {
    private String url;
    private Connection connection;

    public Db(String url) {
        this.url = url;
    }

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url);
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        connect();
        return connection.prepareStatement(query);
    }

    public Connection getConnection() {
        return connection;
    }

    public void executeSqlFromFile(String filePath) throws SQLException, IOException {
        connect();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sqlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line).append("\n");
            }
            String sql = sqlBuilder.toString();
            System.out.println(sql);
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
        }
    }
}