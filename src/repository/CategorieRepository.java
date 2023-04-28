package repository;

import database.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategorieRepository {

    private  static CategorieRepository categorieRepository;
    private CategorieRepository() {}
    public static CategorieRepository getInstance()
    {
        if (categorieRepository == null)
            categorieRepository = new CategorieRepository();
        return categorieRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS CATEGORIE " +
                "(idCategorie int PRIMARY KEY AUTO_INCREMENT, " +
                "nume varchar(100); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM CATEGORIE;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addCategorie(String nume) {
        String insertEdituraSql = "INSERT INTO CATEGORIE(nume) VALUES(\""
                + nume + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertEdituraSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayCategorie()
    {
        String selectSql = "SELECT * FROM CATEGORIE;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Categorie: " + resultSet.getString(2));
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

