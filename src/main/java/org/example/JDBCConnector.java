package org.example;
import java.sql.*;
public class JDBCConnector {

    public static Connection getConnection() throws SQLException {

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/course?user=root&password=oglu2001");
        System.out.println(connection);
        return connection;
    }
}
