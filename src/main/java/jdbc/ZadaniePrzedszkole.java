package jdbc;

import java.sql.*;

public class ZadaniePrzedszkole {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://dram.pl:3306/sda_jdbc?user=sda_academy&password=KrzysiuKrawczyk&useLegacyDatetimeCode=false&serverTimezone=UTC")) {

            howManyChildrenInDifferentAgeAreInPreSchool(connection);

            howManyChildrenDifferentGender(connection);

            whereIsTheMostChildren3Yrs(connection);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void howManyChildrenInDifferentAgeAreInPreSchool(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT dzieci.Wiek, COUNT(dzieci.Pesel)\n" +
                    "FROM zad1_dzieci AS dzieci\n" +
                    "GROUP BY dzieci.Wiek");
            while (resultSet.next()) {
                System.out.println("Wiek dzieci: " + resultSet.getString("dzieci.Wiek") +
                        " Ilość: " + resultSet.getString("COUNT(dzieci.Pesel)"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void howManyChildrenDifferentGender(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT p.Nazwa_przedszkola,\n" +
                    "       (SELECT COUNT(*) FROM zad1_dzieci d WHERE d.Id_przedszkola = p.Id_przedszkola) AS count,\n" +
                    "       (SELECT COUNT(*) FROM zad1_dzieci d WHERE d.Id_przedszkola = p.Id_przedszkola AND d.Plec = 'chlopiec') AS count_boys,\n" +
                    "       (SELECT COUNT(*) FROM zad1_dzieci d WHERE d.Id_przedszkola = p.Id_przedszkola AND d.Plec = 'dziewczynka') AS count_girls\n" +
                    "FROM zad1_przedszkola p\n" +
                    "WHERE p.Id_przedszkola = 22 OR p.Id_przedszkola = 13 OR p.Id_przedszkola = 3\n" +
                    "GROUP BY p.Nazwa_przedszkola");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Nazwa_przedszkola") + " ogółem dzieci: " +
                        resultSet.getString("count") + " dziewczynek: " + resultSet.getString("count_girls") +
                        " chłopców: " + resultSet.getString("count_boys"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void whereIsTheMostChildren3Yrs(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT p.Nazwa_przedszkola,\n" +
                    "       (SELECT COUNT(*)\n" +
                    "        FROM zad1_dzieci d\n" +
                    "        WHERE p.Id_przedszkola = d.Id_przedszkola\n" +
                    "          AND d.Wiek = 3) AS ile_trzylatkow\n" +
                    "FROM zad1_przedszkola p,\n" +
                    "     zad1_dzieci d\n" +
                    "WHERE p.Id_przedszkola = d.Id_przedszkola\n" +
                    "GROUP BY ile_trzylatkow DESC\n" +
                    "LIMIT 1;\n");

            while (resultSet.next()) {
                System.out.println("W " + resultSet.getString("Nazwa_przedszkola") + " jest " +
                        resultSet.getString("ile_trzylatkow") + " trzylatków");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
