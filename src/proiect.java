import Models.*;

import java.time.LocalDate;

public class proiect {
    public static void main(String[] args) {
        Carte carte=new Carte(1, "Verity" ,2023-03-02, 1);
        System.out.println(carte);

        Editura editura= new Editura(1, "Aramis");
        System.out.println(editura);

        Cititor cititor=new Cititor(1, 1);
        System.out.println(cititor);

        Imprumut imprumut=new Imprumut(1);
        System.out.println(imprumut);

        Abonament abonament=new Abonament(1, "lunar", null, "valabil");
        System.out.println(abonament);

        Categorie categorie= new Categorie(1, "Romane", 10);
        System.out.println(categorie);

        Autor autor=new Autor(15);
        System.out.println(autor);

        Persoana persoana=new Persoana(1, "Ion", "Andrei", null, "F");
        System.out.println(persoana);
    }
}
