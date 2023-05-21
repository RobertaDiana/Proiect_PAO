package repository;

import audit.AuditService;
import database.DatabaseConfiguration;

import java.io.IOException;
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
            try{
                AuditService.getInstance().logAction("EDITURA","S-a creat tabela Editura");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("EDITURA","Eroare la crearea tabelei Editura");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    public Editura find(int id){

        String selectSql = "select * from editura where idEditura ="+id+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            try{
                AuditService.getInstance().logAction("EDITURA","S-a gasit editura cu id-ul" + id);
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
            {
                String denumire = resultSet.getString(2);
                Editura e=new Editura(id,denumire);
                return e;
            }


        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("EDITURA","Eroare la gasirea unei edituri");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("EDITURA","S-a gasit editura cu numele respectiv");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("EDITURA","Eroare la gasirea editurii cu numele respectiv");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("EDITURA","S-a gasit id-ul maxim din tabela Editura");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("EDITURA","Eroare la gasirea id-ul maxim din tabela Editura");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("EDITURA","S-a adaugat o editura");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            return  true;
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("EDITURA","Eroare la adaugarea unei edituri");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("EDITURA","Afisarea tuturor editurilor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            { try{
                AuditService.getInstance().logAction("EDITURA","Eroare la afisarea tuturor editurilor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}