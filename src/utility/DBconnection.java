package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

private static final String URL =
    "jdbc:mariadb://127.0.0.1:3306/rpms_db";

private static final String USER = "root";
private static final String PASSWORD = "";

public static Connection getConnection() {

    Connection con = null;

    try {
        con = DriverManager.getConnection(
            URL, USER, PASSWORD
        );

        System.out.println("Database Connected Successfully!");

    } catch (SQLException e) {

        System.out.println("Database Connection Failed!");
        System.out.println(e.getMessage());

    }

    return con;
}

}