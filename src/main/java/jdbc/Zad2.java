package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Zad2 {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:BazaSqlite.sqlite")) {


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
