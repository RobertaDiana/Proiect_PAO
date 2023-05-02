package repository;

import database.DatabaseConfiguration;
import models.Autor;
import models.Categorie;
import models.Editura;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CarteRepository {
    private  static CarteRepository carteRepository;
    private CarteRepository() {}
    public static CarteRepository getInstance()
    {
        if (carteRepository == null)
            carteRepository = new CarteRepository();
        return carteRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS CARTE " +
                "(idCarte int PRIMARY KEY AUTO_INCREMENT, " +
                "title varchar(100), " +
                "autor varchar(100), " +
                "categorie varchar(100), " +
                "dataPublicarii int, " +
                "editura varchar(100), " +
                "imprumut Boolean; ";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM CARTE;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addCarte(String title, Autor autor, Categorie categorie, int dataPublicarii, Editura editura, Boolean imprumut) {
        String insertCarteSql = "INSERT INTO CARTE(title, autor, categorie, dataPublicarii, editura, imprumut) VALUES(\""
                + title + "\"" + autor + "\"" + categorie + "\"" + dataPublicarii + "\"" + editura + "\"" + imprumut + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertCarteSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayCarte()
    {
        String selectSql = "SELECT * FROM CARTE;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Title: " + resultSet.getString(2));
                System.out.println("Autor : " + resultSet.getString(3));
                System.out.println("Categorie: " + resultSet.getString(4));
                System.out.println("Data Publicarii: " + resultSet.getInt(5));
                System.out.println("Editura: " + resultSet.getString(6));
                System.out.println("Imprumut: " + resultSet.getBoolean(7));
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
