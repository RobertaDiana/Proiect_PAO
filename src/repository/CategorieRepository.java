package repository;

import audit.AuditService;
import database.DatabaseConfiguration;
import models.Autor;
import models.Categorie;

import java.io.IOException;
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
            try{
                AuditService.getInstance().logAction("CATEGORIE","S-a creat tabela categorie");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CATEGORIE","Eroare la crearea tabelei categorie");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    public List<Categorie> findRange(List<Integer> ids){
        String idsAsString=String.join(",", ids.stream().map(Object::toString).toArray(String[]::new));//separa fiecare id sin lista folosind ,
        String selectSql = "select * from categorie where idCategorie in ("+ idsAsString+");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Categorie> categori= new ArrayList<Categorie>();


        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            try{
                AuditService.getInstance().logAction("CATEGORIE","S-a returnat o lista cu categorii");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            while (resultSet.next())
            {

                int id= resultSet.getInt(1);
                String denumire = resultSet.getString(2);

                Categorie c=new Categorie(id,denumire);
                categori.add(c);
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CATEGORIE","Eroare la returnarea listelor cu categorii");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("CATEGORIE","S-a gasit categoria dupa numele respectiv");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CATEGORIE","Eroare la gasirea categoriei");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }

        return -1;
    }

    public int getMaxId(){



        String selectMaxIdSQL = "select ifnull(max(idCategorie),0) from categorie;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectMaxIdSQL);
            try{
                AuditService.getInstance().logAction("CATEGORIE","S-a gasit id-ul maxim din tabela Categorie");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CATEGORIE","Eroare la gasirea id-ul maxim din tabela Categorie");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }

        return -1;

    }
    public boolean addCategorie(int idCategorie,String nume) {
        String insertCategorieSql = "INSERT INTO CATEGORIE(idCategorie,nume) VALUES("+
                idCategorie + ",\"" + nume + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertCategorieSql);
            try{
                AuditService.getInstance().logAction("CATEGORIE","S-a adaugat o categorie");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            return  true;
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CATEGORIE","Eroare la adaugarea unei categorii");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("CATEGORIE","S-au afisat toate categoriile");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("CATEGORIE","Eroare la afisarea categoriilor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}