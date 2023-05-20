package repository;

import database.DatabaseConfiguration;
import models.Adresa;
import models.Editura;

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
                "adresa varchar(100)); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxId(){

        String selectMaxIdSQL = "select ifnull(max(idAdresa),0) from adresa";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectMaxIdSQL);

            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }


    public Adresa find(int id){

        String selectSql = "select * from adresa where idAdresa ="+id+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            if (resultSet.next())
            {
                String denumire = resultSet.getString(2);
                Adresa a=new Adresa(id,denumire);
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addAdresa(int id,String adresa) {
        String insertAdresaSql = "INSERT INTO ADRESA(idAdresa,adresa) VALUES(" +
                id + ",\"" + adresa + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertAdresaSql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                Adresa a=new Adresa(resultSet.getInt(1),resultSet.getString(2));
                System.out.println(a);
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