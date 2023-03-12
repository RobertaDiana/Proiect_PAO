package controler;

import models.*;


import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        abonamente.add(new Abonament(1,"tip 3",LocalDate.now(),"acctiv"));

        adrese.add(new Adresa());
        adrese.add(new Adresa(1,"kdnwefnwfef eromfe cednencenec"));

    }

    public static void adaugaCategorie(){

        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul categoriei este: ");
        int idCategorie;

        while(true)
        {
            try {
                idCategorie = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id categorie ");
            }
        }

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
        int idCititor;
        while(true)
        {
            try {
                idCititor = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id cititor ");
            }
        }

        System.out.println("Numele cititorului este: ");
        String cititorNume= reader.nextLine().toUpperCase();

        System.out.println("Prenumele cititorului este: ");
        String cititorPrenume=reader.nextLine().toUpperCase();

        System.out.println("Data nasterii este: ");
        LocalDate cititorDataNasterii;
        while(true)
        {
            try {
                cititorDataNasterii=LocalDate.parse(reader.nextLine());
                break;
            }
            catch(DateTimeParseException e)
            {
                System.out.println("Trebuie o data valida! Incearca din nou ");
                System.out.println("Data nasterii este: ");
            }
        }

        System.out.println("Gen:  ");
        String gen = reader.nextLine().toUpperCase();

        System.out.println("Nr. carti citite: ");
        int nrCartiCitite;
        while(true)
        {
            try {
                nrCartiCitite = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Nr. carti citite: ");
            }
        }

        System.out.println("Adresa , veti introduce id-ul adresei: ");
        afisareAdresa();

        System.out.println("Id Adresa ");
        int idAdresa;
        while(true)
        {
            try {
                idAdresa = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id Adresa: ");
            }
        }
        Adresa adresa=null;
        for (Adresa a:adrese) {
            if(a.getIdAdresa()==idAdresa){
                adresa=a;
                break;
            }
        }


        System.out.println("Abonamnet , veti introduce id-ul abonamentului: ");
        afisareAdresa();

        System.out.println("Id Abonamnet ");
        int idAbonamnet;
        while(true)
        {
            try {
                idAbonamnet = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id Abonamnet: ");
            }
        }
        Abonament abonament=null;
        for (Abonament a:abonamente) {
            if(a.getIdAbonament()==idAbonamnet){
                abonament=a;
                break;
            }
        }



        cititori.add(new Cititor(idCititor,cititorNume,cititorPrenume,cititorDataNasterii,gen,nrCartiCitite,adresa,new ArrayList<Carte>(),abonament));
    }

    public static void adaugaAutor(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul cititorului este: ");
        int id;
        while(true)
        {
            try {
                id = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id cititor ");
            }
        }

        System.out.println("Numele cititorului este: ");
        String Nume= reader.nextLine().toUpperCase();

        System.out.println("Prenumele cititorului este: ");
        String Prenume=reader.nextLine().toUpperCase();

        System.out.println("Data nasterii este: ");
        LocalDate DataNasterii;
        while(true)
        {
            try {
                DataNasterii=LocalDate.parse(reader.nextLine());
                break;
            }
            catch(DateTimeParseException e)
            {
                System.out.println("Trebuie o data valida! Incearca din nou ");
                System.out.println("Data nasterii este: ");
            }
        }

        System.out.println("Gen:  ");
        String gen = reader.nextLine().toUpperCase();

        System.out.println("Nr. carti scrise: ");
        int nrCartiScrise;
        while(true)
        {
            try {
                nrCartiScrise = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Nr. carti scrise: ");
            }
        }

        autori.add(new Autor(id,Nume,Prenume,DataNasterii,gen,nrCartiScrise));
    }

    public static void adaugaAbonament(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul abonamentului este: ");
        int idAbonament;
        while(true)
        {
            try {
                idAbonament = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id abonament ");
            }
        }

        System.out.println("Tipul abonamentului este: ");
        String tipAbonament= reader.nextLine().toUpperCase();
    }

    public static void adaugaAdresa(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul locatiei este: ");
        int idAdresa;
        while(true)
        {
            try {
                idAdresa = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id adresa ");
            }
        }

        System.out.println("Locatia este: ");
        String adresalocatie= reader.nextLine().toUpperCase();
        adrese.add(new Adresa(idAdresa, adresalocatie));

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
        int idCarte;
        while(true)
        {
            try {
                idCarte = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id carte ");
            }
        }

        System.out.println("Numele cartii este: ");
        String carteNume= reader.nextLine().toUpperCase();
    }


    public static void adaugaEditura(){
        Scanner reader= new Scanner(System.in);
        System.out.println("Id-ul editurii este: ");
        int idEditura;
        while(true)
        {
            try {
                idEditura = Integer.parseInt(reader.nextLine());
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Trebuie un numar intreg! Incearca din nou ");
                System.out.println("Id editura ");
            }
        }

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

    public static void SortCarti(){
        Collections.sort(carti, new CarteComparator());
    }

}
