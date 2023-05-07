package repository;

import database.DatabaseConfiguration;
import models.Autor;
import models.Categorie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                "nume varchar(100)); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Categorie> findRange(List<Integer> ids){
        String idsAsString=String.join(",", ids.stream().map(Object::toString).toArray(String[]::new));//separa fiecare id sin lista folosind ,
        String selectSql = "select * from autor where id in ("+ idsAsString+");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Categorie> categori= new ArrayList<Categorie>();


        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {

                int id= resultSet.getInt(1);
                String denumire = resultSet.getString(2);

                Categorie c=new Categorie(id,denumire);
                categori.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
        return categori;
    }

    public int findByName (String str){
        String selectIdSQL = "select ifnull(idCategorie,-1) from categorie " +
                "where upper(nume)  =\""+ str.toUpperCase() +"\";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectIdSQL);

            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int getMaxId(){



        String selectMaxIdSQL = "select ifnull(max(idCategorie),0) from abonament;";
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
    public boolean addCategorie(int id,String nume) {
        String insertCategorieSql = "INSERT INTO CATEGORIE(idCategorie,nume) VALUES("+
                id + ",\"" + nume + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertCategorieSql);
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                System.out.println(new Categorie(resultSet.getInt(1), resultSet.getString(2)));
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

