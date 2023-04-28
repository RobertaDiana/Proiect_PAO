package repository;

import database.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AbonamentRepository {
    private  static AbonamentRepository abonamentRepository;
    private AbonamentRepository() {}
    public static AbonamentRepository getInstance()
    {
        if (abonamentRepository == null)
            abonamentRepository = new AbonamentRepository();
        return abonamentRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS ABONAMENT " +
                "(idAbonament int PRIMARY KEY AUTO_INCREMENT, " +
                "tipAbonament varchar(100), " +
                "dataCreare date, " +
                "status varchar(100); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM ABONAMENT;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addAbonament(String tipAbonament, LocalDate dataCreare, String status) {
        String insertEdituraSql = "INSERT INTO EDITURA(tipAbonament, dataCreare, status) VALUES(\""
                + tipAbonament + "\"" + dataCreare + "\"" + status + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertEdituraSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAbonament()
    {
        String selectSql = "SELECT * FROM ABONAMENT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Abonament: " + resultSet.getString(2));
                System.out.println("Data creare : " + resultSet.getDate(3));
                System.out.println("Status: " + resultSet.getString(4));
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
