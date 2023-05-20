import controler.Controler;
import controler.ControlerDB;
import repository.*;


import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        ControlerDB controlerDB = ControlerDB.getInstance();

        while(true) {
            meniu();
            String comand = sc.next();

            switch (comand) {
                case "a":
                    controlerDB.adaugaCarte();
                    break;
                case "b":
                    controlerDB.adaugaCategorie();
                    break;
                case "c":
                    controlerDB.adaugaEditura();
                    break;
                case "d":
                    controlerDB.adaugaAutor();
                    break;
                case "e":
                    controlerDB.adaugaCititor();
                    break;


                case "f":
                    controlerDB.afisareCarti();
                    break;
                case "g":
                    controlerDB.afisareAdresa();
                    break;
                case "h":
                    controlerDB.afisareAbonamente();
                    break;
                case "i":
                    controlerDB.afisareEditura();
                    break;
                case "j":
                    controlerDB.afisareAutori();
                    break;
                case "k":
                    controlerDB.afisareCititori();
                    break;
                case "l":
                    controlerDB.afisareCategorie();
                    break;

                case "m":
                    controlerDB.ReturneazaCarte();
                    break;
                case "n":
                    controlerDB.ImprumutaCarte();
                    break;

                case "o":
                    controlerDB.anuleazaAbonament();
                    break;

                case "p":
                    return;
                default:
                    System.out.println("nu ati introdus o comanda valida");
                    break;
            }

            System.out.println("Apasati orice tasta pentru a continua");
            String op=sc.next();

        }




    }
    private static void meniu(){


        System.out.println("************Meniu**************");


        System.out.println("a. Adauga carte");
        System.out.println("b. Adauga categorie");
        System.out.println("c. Adauga editura");
        System.out.println("d. Adauga autor");
        System.out.println("e. Adauga cititor");

        System.out.println("f. Afisare carti");
        System.out.println("g. Afisare adresa");
        System.out.println("h. Afisare abonament");
        System.out.println("i. Afisare editura");
        System.out.println("j. Afisare autor");
        System.out.println("k. Afisare cititor");
        System.out.println("l. Afisare categorie");

        System.out.println("m. Returneaza carte");
        System.out.println("n. Imprumuta carte");

        System.out.println("o. Anuleaza abonament");

        System.out.println("p. Exit");

        System.out.println("Introduceti o comanda:");

    }

}
