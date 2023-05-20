package repository;

import database.DatabaseConfiguration;
import models.Autor;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class AutorRepository {
    private static AutorRepository autorRepository;
    private AutorRepository() {}
    public static AutorRepository getInstance()
    {
        if (autorRepository == null ) autorRepository = new AutorRepository();
        return autorRepository;
    }

    public Set<Autor> findRange(List<Integer> ids){
        String idsAsString=String.join(",", ids.stream().map(Object::toString).toArray(String[]::new));//separa fiecare id sin lista folosind ,
        String selectSql = "select * from autor where id in ("+ idsAsString+");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        Set<Autor> autori= new HashSet<Autor>();


        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {

                int id= resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String gen= resultSet.getString(5);
                int nr= resultSet.getInt(6);

                Autor a=new Autor(id,nume,prenume,date,gen,nr);
                autori.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
        return autori;
    }

    public int findByName (String autorStr){
        String selectIdSQL = "select ifnull(id,-1) from autor " +
                "where upper((nume , ' ' , prenume))  =\""+ autorStr +"\";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();


        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectIdSQL);

            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS AUTOR " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "nume varchar(100), " +
                "prenume varchar(100), " +
                "dataNastere date, " +
                "gen varchar(100), " +
                "nrCartiScrise int); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxId(){

        String selectMaxIdSQL = "select ifnull(max(id),0) from autor;";
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
    public boolean addAutor(int id, String nume, String prenume, LocalDate dataNastere, String gen, int nrCartiScrise) {
        String insertAutorSql = "INSERT INTO AUTOR(id,nume, prenume, dataNastere, gen, nrCartiScrise) VALUES("+
                id+ ",\"" + nume + "\" , \"" + prenume + "\" , \"" + dataNastere + "\" , \"" + gen + "\" , " + nrCartiScrise + ");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertAutorSql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
                int id= resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String gen= resultSet.getString(5);
                int nr= resultSet.getInt(6);

                Autor a=new Autor(id,nume,prenume,date,gen,nr);
                System.out.println(a);
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