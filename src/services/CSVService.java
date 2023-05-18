package services;
import audit.AuditService;
import csv.ReadWrite;
import database.DatabaseConfiguration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import models.*;
import repository.*;

public class CSVService {
    public static List<Editura> edituri = new ArrayList<>();
    public static List<Categorie> categorii = new ArrayList<>();
    public static List<Autor> autori = new ArrayList<>();
    public static List<Adresa> adrese = new ArrayList<>();


    EdituraRepository edituraRepository = EdituraRepository.getInstance();
    CategorieRepository categorieRepository = CategorieRepository.getInstance();
    AutorRepository autorRepository = AutorRepository.getInstance();
    AdresaRepository adresaRepository = AdresaRepository.getInstance();


    AuditService auditService = AuditService.getInstance();

    public void loadData()
    {
        // salveaza din CSV in liste de obiecte
        edituri = ReadWrite.readEditura();
        categorii = ReadWrite.readCategorie();
        autori = ReadWrite.readAutor();
        adrese = ReadWrite.readAdresa();


        // salveaza din CSV in baza de date
        // daca aceasta este goala
        edituraRepository.addData();
        categorieRepository.addData();
        autorRepository.addData();
        adresaRepository.addData();

        try
        {
            auditService.logAction("load data");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void configureTables()
    {
        edituraRepository.createTable();
        categorieRepository.createTable();
        autorRepository.createTable();
        adresaRepository.createTable();

        try
        {
            auditService.logAction("configure tables");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void addEditura()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Id editura: ");
        int idEditura;

        while(true)
        {
            try
            {
                idEditura = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Id editura: ");
            }
        }

        System.out.print("Numele editurii: ");
        String denumire = reader.nextLine().toUpperCase();





        edituri.add(new Editura(idEditura, denumire));
        ReadWrite.writeEditura(idEditura, denumire);
        edituraRepository.addEditura(idEditura, denumire);

        try
        {
            auditService.logAction("adauga editura");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printEditura()
    {
        edituraRepository.displayEditura();

        try
        {
            auditService.logAction("scrie editura");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addCategorie()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Id categorie: ");
        int idCategorie;

        while(true)
        {
            try
            {
                idCategorie = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Id categorie: ");
            }
        }

        System.out.print("Numele categorii: ");
        String nume = reader.nextLine().toUpperCase();


        categorii.add(new Categorie(idCategorie, nume));
        ReadWrite.writeCategorie(idCategorie, nume);
        categorieRepository.addCategorie(idCategorie, nume);

        try
        {
            auditService.logAction("adauga categorie");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printCategorie()
    {
        categorieRepository.displayCategorie();

        try
        {
            auditService.logAction("scrie categoria");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addAdresa()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Id adresa: ");
        int idAdresa;

        while(true)
        {
            try
            {
                idAdresa = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Id adresa: ");
            }
        }

        System.out.print("Adresa este: ");
        String adresa = reader.nextLine().toUpperCase();



        adrese.add(new Adresa(idAdresa, adresa));
        ReadWrite.writeAdresa(idAdresa, adresa);
        adresaRepository.addAdresa(idAdresa, adresa);

        try
        {
            auditService.logAction("adauga adresa");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printAdresa()
    {
        adresaRepository.displayAdresa();

        try
        {
            auditService.logAction("scrie adresa");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addAutor()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Id autor: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Id autor: ");
            }
        }

        System.out.print("Numele autorului este: ");
        String nume = reader.nextLine().toUpperCase();

        System.out.print("Prenumele autorului este: ");
        String prenume = reader.nextLine().toUpperCase();

        System.out.print("Data nasterii este: ");
        String dataNasteriiText = reader.nextLine().toUpperCase();
        LocalDate dataNasterii = LocalDate.parse(dataNasteriiText);

        System.out.println("Genul este: ");
        String gen = reader.nextLine().toUpperCase();

        System.out.print("Numarul de carti scrise : ");
        int nrCartiScrise;

        while(true)
        {
            try
            {
                nrCartiScrise = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Nr carti scrise: ");
            }
        }




        autori.add(new Autor(id, nume, prenume, dataNasterii, gen, nrCartiScrise));
        ReadWrite.writeAutor(id, nume, prenume, dataNasterii, gen, nrCartiScrise);
        autorRepository.addAutor(id, nume, prenume, dataNasterii, gen, nrCartiScrise);

        try
        {
            auditService.logAction("adauga autor");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printAutor()
    {
        autorRepository.displayAutor();

        try
        {
            auditService.logAction("scrie autor");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
