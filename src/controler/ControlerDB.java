package controler;

import models.*;
import repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class  ControlerDB {
    private static ControlerDB controler;
    private static AbonamentRepository abonamentRepository;
    private  static AdresaRepository adresaRepository;
    private static AutorRepository autorRepository;
    private static EdituraRepository edituraRepository;
    private static CategorieRepository categorieRepository;
    private static CarteRepository carteRepository;
    private static CititorRepository cititorRepository;
    private ControlerDB(){
        abonamentRepository=AbonamentRepository.getInstance();
        abonamentRepository.createTable();//creez tabelul

        adresaRepository = AdresaRepository.getInstance();
        adresaRepository.createTable();//creez tabelul

        autorRepository=AutorRepository.getInstance();
        autorRepository.createTable();

        edituraRepository=EdituraRepository.getInstance();
        edituraRepository.createTable();

        categorieRepository=CategorieRepository.getInstance();
        categorieRepository.createTable();

        carteRepository=CarteRepository.getInstance();
        carteRepository.createTable();

        cititorRepository=CititorRepository.getInstance();
        cititorRepository.createTable();
    }

    public static ControlerDB getInstance(){
        if (controler==null)
            controler=new ControlerDB();
        return  controler;
    }

    public  Abonament adaugaAbonament(){
        Scanner reader= new Scanner(System.in);

        System.out.println("Tipul abonamentului este: ");

        String tipAbonament= reader.nextLine().toUpperCase();

        String status= "activ";

        int id= abonamentRepository.getMaxId()+1;
        boolean result= abonamentRepository.addAbonament(id,tipAbonament, LocalDate.now(),status);

        if (!result)
            return  null;

        Abonament abon=new Abonament(id,tipAbonament, LocalDate.now(),status);
        return abon;
    }
    public  Adresa adaugaAdresa(){
        Scanner reader= new Scanner(System.in);

        int idAdresa = adresaRepository.getMaxId()+1;

        System.out.println("Locatia este: ");
        String adresalocatie= reader.nextLine().toUpperCase();
        Adresa adresa=new Adresa(idAdresa, adresalocatie);
        boolean result = adresaRepository.addAdresa(idAdresa, adresalocatie);
        if (!result)
            return null;
        return adresa;

    }
    public  Autor adaugaAutor(){
        Scanner reader= new Scanner(System.in);

        int id= autorRepository.getMaxId()+1;

        System.out.println("Numele Autorului este: ");
        String Nume= citireStr("Numele Autorului este: ").toUpperCase();

        System.out.println("Prenumele Autorului este: ");
        String Prenume=citireStr("Prenumele Autorului este: ").toUpperCase();

        System.out.println("Data nasterii este: ");
        LocalDate DataNasterii= CitireData("Data nasterii:");

        System.out.println("Gen:  ");
        String gen = reader.nextLine().toUpperCase();

        System.out.println("Nr. volume scrise: ");
        int nrCartiScrise = CitireInt("Nr. volume scrise:");

        boolean result = autorRepository.addAutor(id,Nume,Prenume,DataNasterii,gen,nrCartiScrise);
        if (!result)
            return null;

        return new Autor(id,Nume,Prenume,DataNasterii,gen,nrCartiScrise) ;
    }
    public  Editura adaugaEditura(){
        Scanner reader= new Scanner(System.in);
        int idEditura= edituraRepository.getMaxId();

        System.out.println("Numele editurii este: ");
        String edituraNume= reader.nextLine().toUpperCase();


        boolean result = edituraRepository.addEditura(idEditura, edituraNume);
        if (!result)
            return null;
        return new Editura(idEditura, edituraNume);
    }
    public  Categorie adaugaCategorie(){

        Scanner reader= new Scanner(System.in);

        int idCategorie = categorieRepository.getMaxId();

        System.out.println("Numele categoriei este: ");
        String categorieNume= reader.nextLine().toUpperCase();

        boolean result = categorieRepository.addCategorie(idCategorie,categorieNume);
        if (!result)
            return null;

        return new Categorie(idCategorie,categorieNume);
    }
    public  void adaugaCarte() {
        Scanner reader= new Scanner(System.in);

        int idCarte = carteRepository.getMaxId();

        System.out.println("Numele cartii este: ");
        String carteNume= reader.nextLine().toUpperCase();

        System.out.println("Introduceti numele si prenumele autorilor separati prin virgula: ");

        String[] autoriStr= reader.nextLine().toUpperCase().toUpperCase().split(",");
        List<Integer> idsAutori = new ArrayList<>();

        //adaug salvez id-ul autorilor intr-o lista, daca autorul nu exista ofer obtinuea de a adauga autorul
        for (String denumire:autoriStr){
            int id = autorRepository.findByName(denumire);
            if( id<1)
            {
                System.out.println("Autorul " + denumire + " nu exista! Doriti sa il adaugati? Raspundeti cu da sau nu");
                String op = reader.nextLine().toUpperCase();
                if (!op.contentEquals("DA"))
                    continue;

                Autor a = controler.adaugaAutor();
                if (a!=null)
                {
                    id=a.getId();
                    idsAutori.add(id);
                }
            }else {
                idsAutori.add(id);
            }
        }


        //categoriiile unei carti
        System.out.println("Introduceti denumirile categoriilor separate prin virgula:");
        String[] CategorieStr= reader.nextLine().toUpperCase().toUpperCase().split(",");
        List<Integer> idsCategori= new ArrayList<Integer>();

        for (String denumire:CategorieStr){
            int id = categorieRepository.findByName(denumire);
            if(id<1){
                id=carteRepository.getMaxId()+1;
                categorieRepository.addCategorie(id,denumire);
            }
            idsCategori.add(id);
        }


        System.out.println("Introduceti anul publicarii:  ");
        int anPublicare = CitireInt("Intorduceti an publicare");

        System.out.println("Statusul cartii este(imprumutat/neimprumutat)? ");
        boolean imp = (reader.nextLine().toLowerCase()=="imprumutat");

        System.out.println("Introduceti denumirea editurii: ");
        String EdituraStr = reader.nextLine().toLowerCase();


        //editura
        int idEditura = edituraRepository.findByName(EdituraStr);
        if(idEditura<1){
            idEditura=edituraRepository.getMaxId()+1;
            edituraRepository.addEditura(idEditura,EdituraStr);
        }

        carteRepository.addCarte(idCarte,carteNume,idsAutori,idsCategori,anPublicare,idEditura,imp);

    }
    public  void adaugaCititor(){

        Scanner reader= new Scanner(System.in);

        int idCititor = cititorRepository.getMaxId();


        System.out.println("Numele cititorului este: ");
        String cititorNume= citireStr("Numele cititorului este: ").toUpperCase();

        System.out.println("Prenumele cititorului este: ");
        String cititorPrenume=citireStr("Prenumele cititorului este: ").toUpperCase();

        System.out.println("Data nasterii este: ");
        LocalDate cititorDataNasterii= CitireData("Data nasterii:");

        System.out.println("Gen:  ");
        String gen = reader.nextLine().toUpperCase();

        int nrCartiCitite=0;

        Adresa adresa=controler.adaugaAdresa();
        int idAdresa=adresa.getIdAdresa();
        Abonament abonament=controler.adaugaAbonament();
        int idAbonament=abonament.getIdAbonament();

        cititorRepository.addCititor(idCititor,cititorNume,cititorPrenume,cititorDataNasterii,gen,nrCartiCitite,idAdresa,"",idAbonament);
    }
    public  void afisareCititori(){
        cititorRepository.displayCititor();
    }



    public   void afisareCarti(){
        carteRepository.displayCarte();
    }
    public  void afisareCategorie(){
        categorieRepository.displayCategorie();
    }
    public  void afisareEditura(){
        edituraRepository.displayEditura();
    }
    public  void afisareAutori(){
        autorRepository.displayAutor();
    }
    public  void afisareAbonamente(){
        abonamentRepository.displayAbonament();
    }
    public  void  afisareAdresa(){
        adresaRepository.displayAdresa();
    }


    public  void anuleazaAbonament(){
        System.out.println("Introduceti id-ul cititorului");
        int idCititor = CitireInt("Introduceti id-ul cititorului");
        Cititor cititor = cititorRepository.find(idCititor);

        if(cititor==null) {
            System.out.println("nu exista acest cititor");
            return;
        }
        Abonament a= cititor.getAbonament();
        cititorRepository.update(idCititor,null);
        if (a==null)
        {
            System.out.println("acest cititor nu are abonament");
            return;
        }
        abonamentRepository.delete(a.getIdAbonament());

    }



    public void ImprumutaCarte(){
        System.out.println("Introduceti id-ul cartii");
        int idCarte = CitireInt("Introduceti id-ul cartii");

        System.out.println("Introduceti id-ul cititorului");
        int idCititor = CitireInt("Introduceti id-ul cititorului");

        List<Carte> carti= carteRepository.getRange(Integer.toString(idCarte));
        if(carti.isEmpty()){
            System.out.println("nu exista cartea aceasta");
            return;
        }
        Carte carte=carti.get(0);
        if(carte.getImprumut()==true){
            System.out.println("carte nu e disponibila");
            return;
        }

        Cititor cititor = cititorRepository.find(idCititor);

        if(cititor==null) {
            System.out.println("nu exista acest cititor");
            return;
        }
        cititor.ImprumutaCarte(carte);
        carte.setImprumut(true);

        carteRepository.update(carte.getIdCarte(),true);

        String ids = "";
        for (Carte c:cititor.getCarti()) {
            if (ids.contentEquals(""))
                ids=ids+c.getIdCarte();
            else{
                ids=ids+","+c.getIdCarte();
            }
        }
        cititorRepository.updateCarti(idCititor,ids);
    }


    public   void ReturneazaCarte(){
        System.out.println("Introduceti id-ul cartii");
        int idCarte = CitireInt("Introduceti id-ul cartii");

        System.out.println("Introduceti id-ul cititorului");
        int idCititor = CitireInt("Introduceti id-ul cititorului");

        List<Carte> carti= carteRepository.getRange(Integer.toString(idCarte));
        if(carti.isEmpty()){
            System.out.println("nu exista cartea aceasta");
            return;
        }
        Carte carte=carti.get(0);


        Cititor cititor = cititorRepository.find(idCititor);
        if(cititor==null) {
            System.out.println("nu exista acest cititor");
            return;
        }
        cititor.ReturneazaCarte(carte);
        carte.setImprumut(false);


        carteRepository.update(carte.getIdCarte(),false);

        String ids = "";
        for (Carte c:cititor.getCarti()) {
            if (ids.contentEquals(""))
                ids=ids+c.getIdCarte();
            else{
                ids=ids+","+c.getIdCarte();
            }
        }
        cititorRepository.updateCarti(idCititor,ids);

    }

    private   LocalDate CitireData(String info){
        LocalDate date;
        Scanner reader= new Scanner(System.in);
        while(true)
        {
            try {
                date=LocalDate.parse(reader.nextLine());
                break;
            }
            catch(DateTimeParseException e)
            {
                System.out.println("Trebuie o data valida! Incearca din nou ");
                System.out.println(info);
            }
        }

        return  date;
    }


    private  int CitireInt(String info){
        int nr=0;
        Scanner reader= new Scanner(System.in);
        while(true)
        {
            try {
                nr = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println(info);
            }
        }

        return nr;
    }

    private  String citireStr(String info){
        String  str;
        Scanner reader= new Scanner(System.in);
        while(true)
        {
            str = reader.nextLine();

            if (!str.equals("")){
                break;
            } else {
                System.out.println("Acest camp nu poate fi gol! Incearca din nou ");
                System.out.println(info);
            }
        }

        return str;
    }

}