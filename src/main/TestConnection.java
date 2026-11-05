package main;

import utility.DBconnection;
import java.sql.Connection;

public class TestConnection {

public static void main(String[] args) {

    Connection con = DBconnection.getConnection();

    if (con != null) {

        System.out.println("RPMS Database is Ready!");

        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

}