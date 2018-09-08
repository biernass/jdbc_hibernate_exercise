package jdbc;

import com.mysql.cj.xdevapi.SqlDataResult;

import java.sql.*;
import java.util.Arrays;

public class Zad7 {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://dram.pl:3306/sda_jdbc?user=sda_academy&password=KrzysiuKrawczyk&useLegacyDatetimeCode=false&serverTimezone=UTC")) {

            System.out.println("wypisz wszystkie nazwy kolumn");
            printAllColumnName(connection);

            System.out.println("wypisz wszystkie typy kolumn");
            printAllColumnType(connection);

            System.out.println("wyswietl wsZytkie różne imiona, które są w bazie");
            showAllDifferentNameFromDataBase(connection);

            System.out.println("Ile osób urodziło się w województwie Mazowieckim.");
            System.out.println(getHowManyPeopleFromMazowieckieState(connection));

            System.out.println("Ile osób na ma imię Jan i ma 22 lata");
            System.out.println(getHowManyJanHave22(connection));

            System.out.println("Wyświetl ile numerów pesel jest nieprawidłowych a ile prawidłowych:");
            printHowManyPESELareCorrectAndIncorrect(connection);

            System.out.println("Wyświetl ile studentów w każdej uczelni");
            howManyStudentInAcadamy(connection);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printAllColumnName(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM zad6_student");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.println(resultSetMetaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printAllColumnType(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM zad6_student");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.println(resultSetMetaData.getColumnTypeName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //dokonczyz zadanie3
    public static void showAllDifferentNameFromDataBase(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT name FROM zad6_student");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getHowManyPeopleFromMazowieckieState(Connection connection) {

        String query = "SELECT COUNT(*) AS how_many_people_from_mazowieckie\n" +
                "FROM zad6_student\n" +
                "WHERE birth_state = \"Mazowieckie\"";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                return resultSet.getInt("how_many_people_from_mazowieckie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getHowManyJanHave22(Connection connection) {

        String query = "SELECT COUNT(*) AS how_many_jan_22yr\n" +
                "FROM zad6_student\n" +
                "WHERE name = \"Jan\" AND age = 22";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                return resultSet.getInt("how_many_jan_22yr");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void printHowManyPESELareCorrectAndIncorrect(Connection connection) {
        int[] peselNumberAsIntArray;
        int correctPesel = 0;
        int incorrectPesel = 0;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT pesel FROM zad6_student");

            while (resultSet.next()) {
                peselNumberAsIntArray = resultSet.getString("pesel").chars().map(x -> x - '0').toArray();

                int result = peselNumberAsIntArray[0] * 9 + peselNumberAsIntArray[1] * 7 +
                        peselNumberAsIntArray[2] * 3 + peselNumberAsIntArray[3] * 1 +
                        peselNumberAsIntArray[4] * 9 + peselNumberAsIntArray[5] * 7 +
                        peselNumberAsIntArray[6] * 3 + peselNumberAsIntArray[7] * 1 +
                        peselNumberAsIntArray[8] * 9 + peselNumberAsIntArray[9] * 7 +
                        peselNumberAsIntArray[10] * 3;

                if (result % 10 == peselNumberAsIntArray[10]) {
                    correctPesel++;
                } else {
                    incorrectPesel++;
                }
            }
            System.out.println("Poprawnych: " + correctPesel + " Niepoprawnych: " + incorrectPesel);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void howManyStudentInAcadamy(Connection connection){
        String query = "SELECT  zad6_academy.name, COUNT(student.id) AS count  FROM zad6_academy\n" +
                "JOIN zad6_student student on zad6_academy.id = student.academy_id\n" +
                "GROUP BY zad6_academy.id";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.print(resultSet.getString("name"));
                System.out.print(" : ");
                System.out.println(resultSet.getString("count"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }








}
