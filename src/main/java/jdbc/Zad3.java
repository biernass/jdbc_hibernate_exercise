package jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class Zad3 {
    public static void main(String[] args) {

        String query = "CREATE TABLE Employee (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name VARCHAR(14),\n" +
                "  age int" +
                ")";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:BazaSqlite.sqlite");
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS Employee");

            statement.executeUpdate(query);
            statement.executeUpdate("INSERT INTO Employee (name, age) \n" +
                    "VALUES ('Adam', 22);");
            statement.executeUpdate("INSERT INTO Employee (name, age) \n" +
                    "VALUES ('Michal', 23);");

            ResultSet resultset = statement.executeQuery("select * from Employee");
            ResultSetMetaData metaData = resultset.getMetaData();

            for (int i = 1; i < metaData.getColumnCount(); i++) {
                System.out.println(metaData.getCatalogName(i));
                System.out.println(metaData.getColumnTypeName(i));
            }

            while (resultset.next()) {
                System.out.println(resultset.getString("name") + " " + resultset.getInt("age"));
            }

            System.out.println(getAverageAgeOfEmployee(connection));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getAverageAgeOfEmployee(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select AVG(age) as sredniaWieku FROM Employee");
            while (resultSet.next()) {
                return resultSet.getInt("sredniaWieku");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
