package repository;

import database.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AdresaRepository {
    private  static AdresaRepository adresaRepository;
    private AdresaRepository() {}
    public static AdresaRepository getInstance()
    {
        if (adresaRepository == null)
            adresaRepository = new AdresaRepository();
        return adresaRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS ADRESA " +
                "(idAdresa int PRIMARY KEY AUTO_INCREMENT, " +
                "adresa varchar(100); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM ADRESA;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addDomain(String adresa) {
        String insertAdresaSql = "INSERT INTO ADRESA(adresa) VALUES(\""
                + adresa + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertAdresaSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAdresa()
    {
        String selectSql = "SELECT * FROM ADRESA;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Adresa: " + resultSet.getString(2));
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

