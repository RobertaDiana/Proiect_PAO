package repository;

import database.DatabaseConfiguration;
import models.Abonament;
import models.Adresa;
import models.Carte;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class CititorRepository {

    private static CititorRepository cititorRepository;
    private CititorRepository() {}
    public static CititorRepository getInstance()
    {
        if (cititorRepository == null ) cititorRepository = new CititorRepository();
        return cititorRepository;
    }

    private int nrCartiCitite;
    private Adresa adresa;
    private List<Carte> carti;
    private Abonament abonament;
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS CITITOR " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "nume varchar(100), " +
                "prenume varchar(100), " +
                "dataNastere date, " +
                "gen varchar(100), " +
                "nrCartiCitite int, " +
                "adresa varchar(100), " +
                "carti varchar(100), " +
                "abonament varchar(100); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addData()
    {
        String selectSQL = "SELECT * FROM CITITOR;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

    }

    public void addCititor(String nume, String prenume, LocalDate dataNastere, String gen, int nrCartiCitite, Adresa adresa, Carte carti, Abonament abonament) {
        String insertCititorSql = "INSERT INTO CITITOR(nume, prenume, dataNastere, gen, nrCartiCitite, adresa, carti, abonament) VALUES(\""
                + nume + "\"" + prenume + "\"" + dataNastere + "\"" + gen + "\"" + nrCartiCitite + "\"" + adresa + "\"" + carti + "\"" + abonament + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertCititorSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayCititor()
    {
        String selectSql = "SELECT * FROM CITITOR;";

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
                System.out.println("Numarul de carti citite: " + resultSet.getInt(6));
                System.out.println("Adresa este: " + resultSet.getString(7));
                System.out.println("Cartile sunt: " + resultSet.getString(8));
                System.out.println("Abonamentul este: " + resultSet.getString(9));
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
