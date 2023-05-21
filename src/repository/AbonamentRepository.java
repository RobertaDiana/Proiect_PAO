package repository;

import audit.AuditService;
import database.DatabaseConfiguration;
import models.Abonament;
import models.Editura;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AbonamentRepository {
    private  static AbonamentRepository abonamentRepository;
    private AbonamentRepository() {}
    public static AbonamentRepository getInstance()
    {
        if (abonamentRepository == null)
            abonamentRepository = new AbonamentRepository();
        return abonamentRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS ABONAMENT " +
                "(idAbonament int PRIMARY KEY AUTO_INCREMENT, " +
                "tipAbonament varchar(100), " +
                "dataCreare date, " +
                "status varchar(100)); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
            try{
                AuditService.getInstance().logAction("ABONAMENT","S-a creat tabela abonament");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ABONAMENT","Eroare la crearea tabelei abonament");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Abonament find(int id){

        String selectSql = "select * from abonament where idAbonament ="+id+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            try{
                AuditService.getInstance().logAction("ABONAMENT","S-a selectat abonamentul cu id="+id);
            }catch (IOException ioE){
                ioE.printStackTrace();
            }

            if (resultSet.next())
            {
                String tip=  resultSet.getString(2);
                LocalDate date = resultSet.getDate(3).toLocalDate();
                String status= resultSet.getString(4);
                Abonament a= new Abonament(id,tip,date,status);
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                AuditService.getInstance().logAction("ABONAMENT","Eroare la cautare de abonamente");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
        }
        return null;
    }


    public void delete(int id){
        String deleteSql = "delete from abonament where idAbonament ="+id+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(deleteSql);
            try{
                AuditService.getInstance().logAction("ABONAMENT","S-a sters abonamentul cu id="+id);
            }catch (IOException ioE){
                ioE.printStackTrace();
            }

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ABONAMENT","Eroare la stergerea de abonament");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public int getMaxId(){

        String selectMaxIdSQL = "select ifnull(max(idAbonament),0) from abonament;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectMaxIdSQL);
            try{
                AuditService.getInstance().logAction("ABONAMENT","S-a gasit id-ul maxim");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }

            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ABONAMENT","Eroare la gasirea id-ului maxim");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }

        return -1;

    }

    public boolean addAbonament(int id,String tipAbonament, LocalDate dataCreare, String status) {
        String insertAbonamentSql = "INSERT INTO ABONAMENT(idAbonament,tipAbonament, dataCreare, status) VALUES("
                + id +",\""+ tipAbonament + "\",\"" + dataCreare + "\",\"" + status + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertAbonamentSql);
            try{
                AuditService.getInstance().logAction("ABONAMENT","S-a adaugat un abonament");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("ABONAMENT","Eroare la adaugarea un abonament");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }
    public void displayAbonament()
    {
        String selectSql = "SELECT * FROM ABONAMENT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            try{
                AuditService.getInstance().logAction("ABONAMENT","S-au afisat toate abonamentele");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            while (resultSet.next())
            {
                empty = false;
                int id=resultSet.getInt(1);
                String tip=  resultSet.getString(2);
                LocalDate date = resultSet.getDate(3).toLocalDate();
                String status= resultSet.getString(4);
                Abonament a= new Abonament(id,tip,date,status);
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
                AuditService.getInstance().logAction("ABONAMENT","Eroare la afisarea tuturor abonamentelor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}