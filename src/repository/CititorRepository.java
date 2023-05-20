package repository;

import database.DatabaseConfiguration;
import models.Abonament;
import models.Adresa;
import models.Carte;
import models.Cititor;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        String selectMaxIdSQL = "select ifnull(max(id),0) from CITITOR;";

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

    public void update(int idCititor, Integer idAbonament){
        String updateSql = "update cititor set abonamentId="+idAbonament+" where id="+idCititor+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(updateSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCarti(int idCititor, String carti){
        String updateSql = "update cititor set carti=\""+carti+"\" where id="+idCititor+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(updateSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCititor(int id,String nume, String prenume, LocalDate dataNastere, String gen, int nrCartiCitite, int adresaId, String carti, int abonamentId) {
        String insertCititorSql ="INSERT INTO CITITOR(id,nume, prenume, dataNastere, gen, nrCartiCitite, adresaId, carti, abonamentId) VALUES("+
                                    id+ ",\"" + nume + "\" , \"" + prenume + "\" , \"" + dataNastere + "\" , \"" + gen + "\" , " + nrCartiCitite +","+ adresaId +",\""+carti+"\","+abonamentId+");";
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
            e.printStackTrace();
        }
        return null;
    }

}
