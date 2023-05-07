package repository;

import database.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Categorie;
import models.Editura;

public class EdituraRepository {

    private  static EdituraRepository edituraRepository;
    private EdituraRepository() {}
    public static EdituraRepository getInstance()
    {
        if (edituraRepository == null)
            edituraRepository = new EdituraRepository();
        return edituraRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS EDITURA " +
                "(idEditura int PRIMARY KEY AUTO_INCREMENT, " +
                "denumire varchar(100)); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Editura find(int id){

        String selectSql = "select * from editura where idEditura ="+id+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            if (resultSet.next())
            {
                String denumire = resultSet.getString(2);
                Editura e=new Editura(id,denumire);
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int findByName (String str){
        String selectIdSQL = "select ifnull(idEditura,-1) from editura " +
                "where upper(denumire)  =\""+ str.toUpperCase() +"\";";
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

        String selectMaxIdSQL = "select ifnull(max(idEditura),0) from editura;";
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


    public boolean addEditura(int id ,String denumire) {
        String insertEdituraSql = "INSERT INTO EDITURA(idEditura,denumire) VALUES("
                + id + " , \"" + denumire + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertEdituraSql);
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void displayEditura()
    {
        String selectSql = "SELECT * FROM EDITURA;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println(new Editura(resultSet.getInt(1),resultSet.getString(2)));
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