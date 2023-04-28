package repository;
import database.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class PersoanaRepository {
    private  static PersoanaRepository persoanaRepository;
    private PersoanaRepository() {}
    public static PersoanaRepository getInstance()
    {
        if (persoanaRepository == null)
            persoanaRepository = new PersoanaRepository();
        return persoanaRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS PERSOANA " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "nume varchar(100), " +
                "prenume varchar(100), " +
                "dataNastere date, " +
                "gen varchar(100); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM PERSOANA;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addPersoana(String nume, String prenume, LocalDate dataNastere, String gen) {
        String insertEdituraSql = "INSERT INTO EDITURA(nume, prenume, dataNastere, gen) VALUES(\""
                + nume + "\"" + prenume + "\"" + dataNastere + "\"" + gen + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertEdituraSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayPersoana()
    {
        String selectSql = "SELECT * FROM PERSOANA;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Nume: " + resultSet.getString(2));
                System.out.println("Prenume : " + resultSet.getString(3));
                System.out.println("Data nastere : " + resultSet.getDate(4));
                System.out.println("Gen: " + resultSet.getString(5));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("Nu exista!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
