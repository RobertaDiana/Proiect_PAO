package repository;

import audit.AuditService;
import database.DatabaseConfiguration;
import models.Abonament;
import models.Adresa;
import models.Carte;
import models.Cititor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
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
                "adresaId int, " +
                "carti varchar(100), " +
                "abonamentId int ); ";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
            try{
                AuditService.getInstance().logAction("CITITOR","S-a creat tabela Cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CITITOR","Eroare la crearea tabelei Cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        String selectMaxIdSQL = "select ifnull(max(id),0) from CITITOR;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectMaxIdSQL);
            try{
                AuditService.getInstance().logAction("CITITOR","S-a gasit id-ul maxim din tabela Cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CITITOR","Eroare la gasirea id-ul maxim din tabela Cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }

        return -1;

    }

    public void update(int idCititor, Integer idAbonament){
        String updateSql = "update cititor set abonamentId="+idAbonament+" where id="+idCititor+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(updateSql);
            try{
                AuditService.getInstance().logAction("CITITOR","S-a actualizat abonamentul unui cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CITIOR","Eroare la actualizarea abonamentului unui cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void updateCarti(int idCititor, String carti){
        String updateSql = "update cititor set carti=\""+carti+"\" where id="+idCititor+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(updateSql);
            try{
                AuditService.getInstance().logAction("CITITOR","S-au actualizat cartile unui cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CITITOR","Eroare la actualizarea cartilor unui cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    public void addCititor(int id,String nume, String prenume, LocalDate dataNastere, String gen, int nrCartiCitite, int adresaId, String carti, int abonamentId) {
        String insertCititorSql ="INSERT INTO CITITOR(id,nume, prenume, dataNastere, gen, nrCartiCitite, adresaId, carti, abonamentId) VALUES("+
                                    id+ ",\"" + nume + "\" , \"" + prenume + "\" , \"" + dataNastere + "\" , \"" + gen + "\" , " + nrCartiCitite +","+ adresaId +",\""+carti+"\","+abonamentId+");";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertCititorSql);
            try{
                AuditService.getInstance().logAction("CITITOR","S-a adaugat un nou cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CITITOR","Eroare la adaugarea unui nou cititor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
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
            try{
                AuditService.getInstance().logAction("CITITOR","Se afiseaza toti cititorii");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            while (resultSet.next())
            {
                empty = false;
                int id= resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String gen= resultSet.getString(5);
                int nr= resultSet.getInt(6);
                int idAdresa=resultSet.getInt(7);
                String idsCarti=resultSet.getString(8);
                int idAbonament = resultSet.getInt(9);


                Adresa adresa = AdresaRepository.getInstance().find(idAdresa);
                Abonament abonament=AbonamentRepository.getInstance().find(idAbonament);
                List<Carte> carti =new ArrayList<Carte>();
                if (!idsCarti.contentEquals(""))
                    carti = CarteRepository.getInstance().getRange(idsCarti);
                Cititor c= new Cititor(id,nume,prenume,date,gen,nr,adresa,carti,abonament);
                System.out.println(c);
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
                AuditService.getInstance().logAction("CITITOR","Eroare la afisarea cititorilor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Cititor find(int id){
        String selectSql = "SELECT * FROM CITITOR where id="+id+";";
//        System.out.println(selectSql);

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            try{
                AuditService.getInstance().logAction("CITITOR","Se selecteaza cititorul cu id-ul" + id);
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
            {
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String gen= resultSet.getString(5);
                int nr= resultSet.getInt(6);
                int idAdresa=resultSet.getInt(7);
                String idsCarti=resultSet.getString(8);
                int idAbonament = resultSet.getInt(9);


                Adresa adresa = AdresaRepository.getInstance().find(idAdresa);
                Abonament abonament=AbonamentRepository.getInstance().find(idAbonament);
                List<Carte> carti =new ArrayList<Carte>();
                if (!idsCarti.contentEquals(""))
                    carti = CarteRepository.getInstance().getRange(idsCarti);
                Cititor c= new Cititor(id,nume,prenume,date,gen,nr,adresa,carti,abonament);
                return c;
            }


        }
        catch (SQLException e)

        {
            try{
                AuditService.getInstance().logAction("CITITOR","Eroare la selectarea cititorului");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

}
