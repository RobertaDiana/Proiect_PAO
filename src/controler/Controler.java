package controler;

import models.*;


import java.sql.Array;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Controler {
    public static List<Carte> carti= new ArrayList<>();
    public static List<Cititor> cititori =new ArrayList<>();
    public static List<Autor> autori =new ArrayList<>();
    public static List<Categorie> categorii =new ArrayList<>();
    public static List<Editura> edituri =new ArrayList<>();
    public static List<Abonament> abonamente = new ArrayList<>();
    public static List<Adresa> adrese = new ArrayList<>();


    public static void init(){
        abonamente.add(new Abonament(1,"tip 1",LocalDate.now(),"acctiv"));
        abonamente.add(new Abonament(2,"tip 2",LocalDate.now(),"acctiv"));

        adrese.add(new Adresa(2,"test adresa 2"));
        adrese.add(new Adresa(1,"test adresa"));

        categorii.add(new Categorie(2,"test2"));
        categorii.add(new Categorie(1,"test"));

        edituri.add(new Editura(2,"test2"));
        edituri.add(new Editura(1,"test"));

        carti.add(new Carte());

        cititori.add(new Cititor());

        autori.add(new Autor(1,"test","test",LocalDate.now(),"fem",0));
    }

    private static int CitireInt(String info){
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

    private static  LocalDate CitireData(String info){
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

    public static Autor  FindByNameA(String denumire){
        for (Autor aut:autori) {
            String s=(aut.getNume() + " " + aut.getPrenume()).toUpperCase();
            if(s.contentEquals(denumire))
                return aut;
        }

        return new Autor(-1,null,null,null,null,-1);
    }

    public static Categorie  FindByNameC(String denumire){
        for (Categorie cat:categorii) {
            String s=(cat.getNume()).toUpperCase();
            if(s.contentEquals(denumire))
                return cat;
        }
        return new Categorie(-1,null);
    }

    public static Carte getCarte(int id){
        List<Carte> carte = carti.stream().filter(c->c.getIdCarte()==id).toList();
        if(carte.isEmpty())
            return null;

        return carte.get(0);

    }
    public static Cititor getCititor(int id){
        List<Cititor> cititor = cititori.stream().filter(c->c.getId()==id).toList();
        if(cititor.isEmpty())
            return null;

        return cititor.get(0);

    }

    public static void adaugaCategorie(){

        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul categoriei este: ");
        int idCategorie = CitireInt("Introduceti Id");

        System.out.println("Numele categoriei este: ");
        String categorieNume= reader.nextLine().toUpperCase();

        categorii.add(new Categorie(idCategorie,categorieNume));
    }
    public static void afisareCategorie(){
        for ( Categorie categorie:categorii) {
            System.out.println(categorie);
            System.out.println();
        }
    }

    public static void adaugaCititor(){

        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul cititorului este: ");
        int idCititor = CitireInt("Id cititor");


        System.out.println("Numele cititorului este: ");
        String cititorNume= reader.nextLine().toUpperCase();

        System.out.println("Prenumele cititorului este: ");
        String cititorPrenume=reader.nextLine().toUpperCase();

        System.out.println("Data nasterii este: ");
        LocalDate cititorDataNasterii= CitireData("Data nasterii:");


        System.out.println("Gen:  ");
        String gen = reader.nextLine().toUpperCase();

        System.out.println("Nr. carti citite: ");
        int nrCartiCitite=CitireInt("Nr. cati citite");

        Adresa adresa=adaugaAdresa();
        Abonament abonament=adaugaAbonament();

        cititori.add(new Cititor(idCititor,cititorNume,cititorPrenume,cititorDataNasterii,gen,nrCartiCitite,adresa,new ArrayList<Carte>(),abonament));
    }
    public static void afisareCititori(){
        for (Cititor c:cititori){
            System.out.println(c);
            System.out.println();
        }
    }

    public static void afisareAutori(){
        for (Autor a:autori){
            System.out.println(a);
            System.out.println();
        }
    }
    public static void adaugaAutor(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul autorului este: ");
        int id= CitireInt( "Id autor");

        System.out.println("Numele Autorului este: ");
        String Nume= reader.nextLine().toUpperCase();

        System.out.println("Prenumele Autorului este: ");
        String Prenume=reader.nextLine().toUpperCase();

        System.out.println("Data nasterii este: ");
        LocalDate DataNasterii= CitireData("Data nasterii:");

        System.out.println("Gen:  ");
        String gen = reader.nextLine().toUpperCase();

        System.out.println("Nr. volume scrise: ");
        int nrCartiScrise = CitireInt("Nr. volume scrise:");

        autori.add(new Autor(id,Nume,Prenume,DataNasterii,gen,nrCartiScrise));
    }

    public static Abonament adaugaAbonament(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul abonamentului este: ");
        int idAbonament=CitireInt("Id abonament");

        System.out.println("Tipul abonamentului este: ");
        String tipAbonament= reader.nextLine().toUpperCase();

        String status= "activ";
        Abonament abon=new Abonament(idAbonament,tipAbonament,LocalDate.now(),status);
        abonamente.add(abon);
        return abon;
    }

    public static void afisareAbonamente(){
        for ( Abonament abonament:abonamente) {
            System.out.println(abonament);
            System.out.println();
        }
    }
    public static Adresa adaugaAdresa(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul locatiei este: ");
        int idAdresa = CitireInt("Id locatie");

        System.out.println("Locatia este: ");
        String adresalocatie= reader.nextLine().toUpperCase();
        Adresa adresa=new Adresa(idAdresa, adresalocatie);
        adrese.add(adresa);
        return adresa;

    }

    public static void afisareAdresa(){
        for ( Adresa adresa:adrese) {
            System.out.println(adresa);
            System.out.println();
        }
    }

    public static void adaugaCarte() {
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul cartii este: ");
        int idCarte =CitireInt("Id carte");

        System.out.println("Numele cartii este: ");
        String carteNume= reader.nextLine().toUpperCase();

        System.out.println("Intorduceti numele si prenumele autorilor separati prin virgula: ");

        String[] autoriStr= reader.nextLine().toUpperCase().toUpperCase().split(",");
        Set<Autor> autoriCarte= new HashSet<Autor>();

        for (String denumire:autoriStr){
            Autor a=FindByNameA(denumire);
            if(a.getId()>0)
                autoriCarte.add(a);
        }

        System.out.println("Intorduceti denumirile categoriilor separate prin virgula:");
        String[] CategorieStr= reader.nextLine().toUpperCase().toUpperCase().split(",");
        List<Categorie> categorieList= new ArrayList<Categorie>();

        for (String denumire:CategorieStr){
            Categorie c=FindByNameC(denumire);
            if(c.getIdCategorie()>0)
                categorieList.add(c);
        }


        System.out.println("Intorduceti anul publicarii:  ");
        int anPublicare = CitireInt("Intorduceti an publicare");

        System.out.println("Statusul cartii este(imprumutat/neimprumutat)? ");
        boolean imp = (reader.nextLine().toLowerCase()=="imprumutat");


        System.out.println("Introduceti denumirea editurii: ");
        String EdituraStr = reader.nextLine().toLowerCase();

        Editura edit= new Editura();

        for (Editura e: edituri) {
            if (e.getDenumire().toLowerCase().contentEquals(EdituraStr)) {
                edit=e;
                break;
            }
        }


        carti.add(new Carte(idCarte,carteNume,autoriCarte,categorieList,anPublicare,edit,imp));

    }

    public  static void afisareCarti(){
        SortCarti();
        for (Carte c:carti) {
            System.out.println(c);
            System.out.println("");
        }
    }

    public static void adaugaEditura(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul editurii este: ");
        int idEditura=CitireInt("Id editura");


        System.out.println("Numele editurii este: ");
        String edituraNume= reader.nextLine().toUpperCase();

        edituri.add(new Editura(idEditura, edituraNume));
    }
    public static void afisareEditura(){
        for ( Editura editura:edituri) {
            System.out.println(editura);
            System.out.println();
        }
    }

    public static void ImprumutaCarte(){
        System.out.println("Introduceti id-ul cartii");
        int idCarte = CitireInt("Introduceti id-ul cartii");

        System.out.println("Introduceti id-ul cititorului");
        int idCititor = CitireInt("Introduceti id-ul cititorului");

        Carte carte = getCarte(idCarte);
        if(carte==null){
            System.out.println("nu exista cartea aceasta");
            return;
        }

        if(carte.getImprumut()==true){
            System.out.println("carte nu e disponibila");
            return;
        }

        Cititor cititor = getCititor(idCititor);

        if(cititor==null) {
            System.out.println("nu exista acest cititor");
            return;
        }
        cititor.ImprumutaCarte(carte);
        carte.setImprumut(true);

    }

    public static  void ReturneazaCarte(){
        System.out.println("Introduceti id-ul cartii");
        int idCarte = CitireInt("Introduceti id-ul cartii");

        System.out.println("Introduceti id-ul cititorului");
        int idCititor = CitireInt("Introduceti id-ul cititorului");

        Carte carte = getCarte(idCarte);
        if(carte==null){
            System.out.println("nu exista cartea aceasta");
            return;
        }


        Cititor cititor = getCititor(idCititor);

        if(cititor==null) {
            System.out.println("nu exista acest cititor");
            return;
        }
        cititor.ReturneazaCarte(carte);
        carte.setImprumut(false);
    }

    public static void anuleazaAbonament(Cititor user){
        Abonament a = user.getAbonament();
        user.setAbonament(null);
        abonamente.remove(a);
    }

    public static void SortCarti(){
        Collections.sort(carti, new CarteComparator());
    }

}
