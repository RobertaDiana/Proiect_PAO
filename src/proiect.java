import models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Proiect {
    public static void main(String[] args) {
        Autor autor=new Autor(1, "Ion", "Andrei", null, "F",15);
        // System.out.println(autor);

        Editura editura= new Editura(1, "Aramis");
        // System.out.println(editura);

        List<Autor> Autori=new ArrayList<Autor>();
        Autori.add(autor);


        Categorie categorie= new Categorie(1, "Romane", 10);
        // System.out.println(categorie);

        List<Categorie> categories = new ArrayList<Categorie>();
        categories.add(categorie);

        Carte carte=new Carte(1, "Verity" ,Autori,categories, LocalDate.now().getYear(),20,editura,false );
        // System.out.println(carte);


        Abonament abonament=new Abonament(1, "lunar", null, "valabil");
        // System.out.println(abonament);

        
        Adresa adresa = new Adresa(1,"test","7B","nu am detalii");
        // System.out.println(adresa);

        List<Carte> carti = new ArrayList<Carte>();
        carti.add(carte);

        Cititor cititor=new Cititor(1, "Ion", "Andrei", null, "F",12,adresa,carti,abonament);
        // System.out.println(cititor);

        Cititor c1=new Cititor();
        c1.ImprumutaCarte(carte);
        // System.out.println(c1.ReturneazaCarte(carte));
        // System.out.println(c1.getCarti());

        // Persoana persoana=new Persoana(1, "Ion", "Andrei", null, "F");
        // System.out.println(persoana);

        Biblioteca B=new Biblioteca();

        B.adaugaAutor(autor);
        B.AdaugaCarte(carte);

        System.out.println(B.getAutori());

        System.out.println(adresa);

    }
}
