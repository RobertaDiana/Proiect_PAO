package repository;

import database.DatabaseConfiguration;
import models.Autor;

import java.sql.*;
import java.time.LocalDate;

public class AutorRepository {
    private static AutorRepository autorRepository;
    private AutorRepository() {}
    public static AutorRepository getInstance()
    {
        if (autorRepository == null ) autorRepository = new AutorRepository();
        return autorRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS AUTOR " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "nume varchar(100), " +
                "prenume varchar(100), " +
                "dataNastere date, " +
                "gen varchar(100), " +
                "nrCartiScrise int; ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM AUTOR;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addAutor(String nume, String prenume, LocalDate dataNastere, String gen, int nrCartiScrise) {
        String insertAutorSql = "INSERT INTO PERSOANA(nume, prenume, dataNastere, gen, nrCartiScrise) VALUES(\""
                + nume + "\"" + prenume + "\"" + dataNastere + "\"" + gen + "\"" + nrCartiScrise + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertAutorSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAutor()
    {
        String selectSql = "SELECT * FROM AUTOR;";

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
                System.out.println("Numarul de carti scrise: " + resultSet.getInt(6));
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


