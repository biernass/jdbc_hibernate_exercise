package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Zad1 {

    public static void main(String[] args) {

        String query = "CREATE TABLE Employee (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name VARCHAR(14),\n" +
                "  age int" +
                ")";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:BazaSqlite.sqlite");
            Statement statement = connection.createStatement()){

            statement.executeUpdate(query);
            statement.executeUpdate("INSERT INTO Employee (name, age) \n" +
                    "VALUES ('Adam', 22);");
            statement.executeUpdate("INSERT INTO Employee (name, age) \n" +
                    "VALUES ('Adam', 23);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}