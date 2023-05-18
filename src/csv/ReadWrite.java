package csv;

import models.Adresa;
import models.Autor;
import models.Categorie;
import models.Editura;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite {

    public static void writeEditura(int idEditura, String denumire)
    {
        String file = "src\\main\\java\\com\\files\\editura.csv\\";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String edituraString = idEditura + "," + denumire ;
            pw.println(edituraString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Editura> readEditura()
    {
        List<Editura> edituri = new ArrayList<>();
        String file = "src\\main\\java\\com\\files\\editura.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                int idEditura = Integer.parseInt(row[0]);
                String denumire = row[1];

                edituri.add(new Editura(idEditura, denumire));
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return edituri;
    }

    public static void writeAdresa(int idAdresa, String adresa)
    {
        String file = "src\\main\\java\\com\\files\\adresa.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String adresaString = idAdresa + "," + adresa;
            pw.println(adresaString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Adresa> readAdresa()
    {
        List<Adresa> adrese = new ArrayList<>();
        String file = "src\\main\\java\\com\\files\\adresa.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                int idAdresa = Integer.parseInt(row[0]);
                String adresa = row[1];

                adrese.add(new Adresa(idAdresa, adresa));
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return adrese;
    }

    public static void writeCategorie(int idCategorie, String nume)
    {
        String file = "src\\main\\java\\com\\files\\categorie.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String categorieString = idCategorie + "," + nume;
            pw.println(categorieString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static List<Categorie> readCategorie()
    {
        List<Categorie> categorii = new ArrayList<>();
        String file = "src\\main\\java\\com\\files\\categorie.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                int idCategorie = Integer.parseInt(row[0]);
                String nume = row[1];

                Categorie categoriile = new Categorie(idCategorie, nume);
                categorii.add(categoriile);
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return categorii;
    }

    public static void writeAutor(int id, String nume, String prenume, LocalDate dataNasterii, String gen, int nrCartiScrise)
    {
        String file = "src\\main\\java\\com\\files\\autor.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String autorString = id + "," + nume + "," + prenume + "," + dataNasterii + "," + gen + "," + nrCartiScrise  ;
            pw.println(autorString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Autor> readAutor()
    {
        List<Autor> autori = new ArrayList<>();
        String file = "src\\main\\java\\com\\files\\autor.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                int id = Integer.parseInt(row[0]);
                String nume = row[1];
                String prenume = row[2];
                LocalDate dataNasterii = LocalDate.parse(row[3]);
                String gen = row[4];
                int nrCartiScrise = Integer.parseInt(row[5]);

                Autor autorii = new Autor(id, nume, prenume, dataNasterii, gen, nrCartiScrise);
                autori.add(autorii);
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return autori;
    }

}

