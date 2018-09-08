package jdbc;

import java.sql.*;
import java.util.Random;

public class BatchProcessingZad1 {
    public static void main(String[] args) {
        int[] przedzialWieku = {20, 40};
        String[] imiona = {"Jan", "Tomek", "Sebastian", "Agata", "Ryszard", "Pawel"};
        Random random = new Random();

        String createTableQuery = "create table if not exists Employee2(id INTEGER PRIMARY KEY AUTOINCREMENT, age int, name VARCHAR(64))";
        String insertEmployeQuery = "INSERT INTO Employee2 VALUES(NULL, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:BazaSqlite.sqlite");
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(createTableQuery);

            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeQuery)) {

                for (int i = 0; i < 100; i++) {
                    preparedStatement.setInt(1, random.nextInt(przedzialWieku[1] - przedzialWieku[0] + 1) + przedzialWieku[0]);
                    preparedStatement.setString(2, imiona[random.nextInt(imiona.length)]);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
