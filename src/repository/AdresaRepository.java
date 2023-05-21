package repository;

import audit.AuditService;
import database.DatabaseConfiguration;
import models.Adresa;
import models.Editura;

import java.io.IOException;
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
            try{
                AuditService.getInstance().logAction("ADRESA","S-a creat tabela Adresa");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ADRESA","Eroare la crearea tabelei adresa");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public int getMaxId(){

        String selectMaxIdSQL = "select ifnull(max(idAdresa),0) from adresa";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectMaxIdSQL);
            try{
                AuditService.getInstance().logAction("ADRESA","S-a gasit id-ul maxim din tabela Adresa");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
                return resultSet.getInt(1) ;


        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ADRESA","Eroare la gasirea id-ul maxim din tabela Adresa");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("ADRESA","S-a selectat abonamentul cu id-ul" +id);
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
            {
                String denumire = resultSet.getString(2);
                Adresa a=new Adresa(id,denumire);
                return a;
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ADRESA","Eroare la cautare de adresa");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("ADRESA","S-a introdus o adresa noua");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ADRESA","Eroare la introducerea unei adrese noi");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("ADRESA","S-au afisat toate adresele");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("ADRESA","Eroare la afisarea adreselor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}