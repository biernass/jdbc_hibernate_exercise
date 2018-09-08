package jdbc;

import java.sql.*;

public class Zad7_other {
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://dram.pl:3306/sda_jdbc?user=sda_academy&password=KrzysiuKrawczyk&useLegacyDatetimeCode=false&serverTimezone=UTC");) {
            //zad71_printColumnNames(connection);
            //zad73_printDistinctNames(connection);
//            zad74_printStateMazowieckie(connection);
//            zad75_printJanAnd22(connection);
//            zad76_correctPeselCount(connection);
            zad77_studentsCountAndAcademy(connection);
            zad78_studentsAcadamyInfo(connection);
            zad79_printDetailsInfo(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void zad79_stateAndRyszard(Connection connection){
        String query = "select birth_state, count(*) from zad6_student where name = 'Ryszard' and age between 22 and 30 group by birth_state";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void zad79_printDetailsInfo(Connection connection){
        String query = "select zad6_student.id, zad6_student.name, sur_name, album_nr, pesel from zad6_student JOIN zad6_academy ON zad6_academy.id = zad6_student.academy_id where zad6_student.name = 'Tomek' and age between 22 and 23 and zad6_academy.name = 'Politechnika Warszawska'";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4)+ " " + resultSet.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    private static void zad78_studentsAcadamyInfo(Connection connection){
        String query = "select zad6_academy.name, " +
                "MAX(student.age), " +
                "MIN(student.age), " +
                "AVG(student.age) " +
                "FROM zad6_academy, zad6_student as student WHERE student.academy_id = zad6_academy.id group by zad6_academy.name";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    private static void zad77_studentsCountAndAcademy(Connection connection){
        System.out.println("Zad 77: ");
        String query = "SELECT zad6_academy.name, COUNT(student.id) AS count\n" +
                "FROM zad6_academy\n" +
                "      JOIN zad6_student student on zad6_academy.id = student.academy_id\n" +
                "GROUP BY zad6_academy.id;";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void zad76_correctPeselCount(Connection connection){
        System.out.println("Zad 76: ");
        String query = "SELECT pesel FROM zad6_student";
        int correct = 0;
        int notCorrect = 0;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                if(verifyPesel(resultSet.getString(1))){
                    correct++;
                } else {
                    notCorrect++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(correct);
        System.out.println(notCorrect);
    }
    private static boolean verifyPesel(String pesel){
        if (pesel.isEmpty() || pesel.length()!=11) return false;

        String[] p = pesel.split("");

        int sum = 9 * Integer.parseInt(p[0])
                + 7 * Integer.parseInt(p[1])
                + 3 * Integer.parseInt(p[2])
                + 1 * Integer.parseInt(p[3])
                + 9 * Integer.parseInt(p[4])
                + 7 * Integer.parseInt(p[5])
                + 3 * Integer.parseInt(p[6])
                + 1 * Integer.parseInt(p[7])
                + 9 * Integer.parseInt(p[8])
                + 7 * Integer.parseInt(p[9]);

        return (Integer.parseInt(p[10]) == sum % (sum / 10)) ? true : false;
    }
    private static void zad75_printJanAnd22(Connection connection) {
        System.out.println("Zad 75: ");
        String query = "SELECT COUNT(*) from zad6_student WHERE name = 'Jan' and age = 22";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void zad74_printStateMazowieckie(Connection connection) {
        System.out.println("Zad 74: ");
        String query = "SELECT COUNT(*) from zad6_student WHERE birth_state = 'Mazowieckie'";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void zad73_printDistinctNames(Connection connection) {
        System.out.println("Zad 73: ");
        String query = "SELECT distinct name from zad6_student;";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void zad71_printColumnNames(Connection connection){
        String query = "select * from zad6_student LIMIT 1";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();

            for(int i = 1; i<=metaData.getColumnCount(); i++){
                System.out.println(metaData.getColumnName(i) + " " + metaData.getColumnTypeName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
