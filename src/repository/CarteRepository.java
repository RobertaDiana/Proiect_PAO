package repository;

import audit.AuditService;
import database.DatabaseConfiguration;
import models.Autor;
import models.Carte;
import models.Categorie;
import models.Editura;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CarteRepository {
    private  static CarteRepository carteRepository;
    private CarteRepository() {}
    public static CarteRepository getInstance()
    {
        if (carteRepository == null)
            carteRepository = new CarteRepository();
        return carteRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS CARTE " +
                "(idCarte int PRIMARY KEY AUTO_INCREMENT, " +
                "title varchar(100), " +
                "autor varchar(100), " +
                "categorie varchar(100), " +
                "dataPublicarii int, " +
                "editura int, " +
                "imprumut Boolean); ";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
            try{
                AuditService.getInstance().logAction("CARTE","S-a creat tabela Carte");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CARTE","Eroare la crearea tabelei Carte");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    public int getMaxId(){

        String selectMaxIdSQL = "select ifnull(max(idCarte),0) from carte;";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectMaxIdSQL);
            try{
                AuditService.getInstance().logAction("CARTE","S-a gasit id-ul maxim");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            if (resultSet.next())
                return resultSet.getInt(1) ;

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CARTE","Eroare la gasirea id-ului maxim");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }

        return -1;

    }
    public boolean addCarte(int id,String title, List<Integer> idsAutor, List<Integer> idsEdituri, int anPublicare, int idEditura, Boolean imprumut) {
        String autoriStr = String.join(",", idsAutor.stream().map(Object::toString).toArray(String[]::new));
        String categoriStr = String.join(",", idsEdituri.stream().map(Object::toString).toArray(String[]::new)) ;
        String insertCarteSql = "INSERT INTO CARTE(idCarte,title, autor, categorie, dataPublicarii, editura, imprumut) VALUES("
                +id +",\"" + title + "\", \"" + autoriStr + "\",\"" + categoriStr + "\", " + anPublicare + "," + idEditura + "," + imprumut + ");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertCarteSql);
            try{
                AuditService.getInstance().logAction("CARTE","S-a introdus o noua carte");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            return true;
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CARTE","Eroare la introducerea unei noi carti");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }
    public void displayCarte()
    {
        String selectSql = "SELECT * FROM CARTE;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            try{
                AuditService.getInstance().logAction("CARTE","S-au afisat toate cartile");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            while (resultSet.next())
            {
                SortedSet<Carte> carti = new TreeSet<Carte>();
                empty = false;
                int id= resultSet.getInt(1);
                String titlu = resultSet.getString(2);
                String idsAutoriStr= resultSet.getString(3);
                String idsCategoriStr= resultSet.getString(4);
                int anPublicare = resultSet.getInt(5);
                int idEditura = resultSet.getInt(6);
                boolean imprumut = resultSet.getBoolean(7);

                List<Integer> idsAutoriInt= new ArrayList<>();
                Set<Autor> autori = null;

                if(!idsAutoriStr.contentEquals("")){
                    idsAutoriInt= Arrays.stream(idsAutoriStr.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                    autori = AutorRepository.getInstance().findRange(idsAutoriInt);
                }

                List<Integer> idsCategoriInt= new ArrayList<>();
                List<Categorie> categori = null;

                if(!idsCategoriStr.contentEquals("")) {
                    idsCategoriInt = Arrays.stream(idsCategoriStr.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    categori = CategorieRepository.getInstance().findRange(idsCategoriInt);
                }

                Editura editura = EdituraRepository.getInstance().find(idEditura);

                Carte c = new Carte(id,titlu,autori,categori,anPublicare,editura,imprumut);
                carti.add(c);

                for (Carte carte:carti){
                    System.out.println(carte);
                }
            }

            if (empty)
            {
                System.out.println("Nu exista!");
            }
        }
        catch (SQLException e)
        {
            try{
                AuditService.getInstance().logAction("CARTE","Eroare la afisarea cartilor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void update(int idCarte, Boolean imprumut){
        String updateSql = "update carte set imprumut="+imprumut+" where idCarte="+idCarte+";";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(updateSql);
            try{
                AuditService.getInstance().logAction("CARTE","S-a actualizat starea unei carti");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }

        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CARTE","Eroare la actualizarea starii unei carti");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Carte> getRange(String idsAsString){

        String selectSql = "select * from Carte where idCarte in ("+ idsAsString+");";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Carte> carti= new ArrayList<Carte>();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            try{
                AuditService.getInstance().logAction("CARTE","Se afiseaza cartile");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            while (resultSet.next())
            {
                int id= resultSet.getInt(1);
                String titlu = resultSet.getString(2);
                String idsAutoriStr= resultSet.getString(3);
                String idsCategoriStr= resultSet.getString(4);
                int anPublicare = resultSet.getInt(5);
                int idEditura = resultSet.getInt(6);
                boolean imprumut = resultSet.getBoolean(7);

                List<Integer> idsAutoriInt= new ArrayList<>();
                Set<Autor> autori = null;

                if(!idsAutoriStr.contentEquals("")){
                    idsAutoriInt= Arrays.stream(idsAutoriStr.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    autori = AutorRepository.getInstance().findRange(idsAutoriInt);
                }

                List<Integer> idsCategoriInt= new ArrayList<>();
                List<Categorie> categori = null;

                if(!idsCategoriStr.contentEquals("")) {
                    idsCategoriInt = Arrays.stream(idsCategoriStr.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    categori = CategorieRepository.getInstance().findRange(idsCategoriInt);
                }

                Editura editura = EdituraRepository.getInstance().find(idEditura);

                Carte c = new Carte(id,titlu,autori,categori,anPublicare,editura,imprumut);
                carti.add(c);

            }
        } catch (SQLException e) {
            try{
                AuditService.getInstance().logAction("CARTE","Eroare la afisarea cartilor");
            }catch (IOException ioE){
                ioE.printStackTrace();
            }
            e.printStackTrace();
            return  null;
        }
        return carti;
    }
}