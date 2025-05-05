package com.t3;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        Db db = new Db("jdbc:sqlite:sample.db");
        try {
            db.connect();
            // db.executeSqlFromFile("sql\\User.sql");
            var rs = db.executeQuery("SELECT * FROM user");
            var name = rs.getString("name");
            var email = rs.getString("email");
            System.out.println(name + " " + email);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
