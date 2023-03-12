import controler.Controler;
import models.Persoana;

import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {


        Controler.init();
        while(true) {

            meniu();

            Scanner sc = new Scanner(System.in);

            String comand = sc.next();

            switch (comand) {
                case "a":
                    Controler.adaugaCarte();
                    System.out.println(Controler.carti);
                    break;
                case "b":
                    Controler.adaugaAdresa();
                    System.out.println(Controler.adrese);
                    break;
                case "c":
                    Controler.adaugaAbonament();
                    System.out.println(Controler.abonamente);
                    break;
                case "d":
                    Controler.adaugaCategorie();
                    System.out.println(Controler.categorii);
                    break;
                case "e":
                    Controler.adaugaEditura();
                    System.out.println(Controler.edituri);
                    break;
                case "f":
                    Controler.adaugaAutor();
                    System.out.println(Controler.autori);
                    break;
                case "g":
                    Controler.adaugaCititor();
                    System.out.println(Controler.cititori);
                case "h":

                    break;
                case "i":
                    Controler.afisareAdresa();
                    System.out.println(Controler.adrese);
                    break;
                case "j":

                    break;
                case "k":
                    Controler.afisareEditura();
                    System.out.println(Controler.edituri);
                    break;
                case "l":

                    break;
                case "m":

                    break;
                case "n":
                    Controler.afisareCategorie();
                    System.out.println(Controler.categorii);
                    break;
                case "o":

                    break;
                case "p":

                    break;
                case "q":

                    break;
                case "r":

                    break;
                case "s":

                    break;
                case "t":
                    return;
                default:
                    System.out.println("nu ati introdus o comanda valida");
                    break;
            }
        }

    }

    private static void meniu(){

        System.out.println("************Meniu**************");



        System.out.println("a. Adauga carte");
        System.out.println("b. Adauga adresa");
        System.out.println("c. Adauga abonament");
        System.out.println("d. Adauga categorie");
        System.out.println("e. Adauga editura");
        System.out.println("f. Adauga autor");
        System.out.println("g. Adauga cititor");

        System.out.println("h. Afisare carti");
        System.out.println("i. Afisare adresa");
        System.out.println("j. Afisare abonament");
        System.out.println("k. Afisare editura");
        System.out.println("l. Afisare autor");
        System.out.println("m. Afisare cititor");
        System.out.println("n. Afisare categorie");

        System.out.println("o. Returneaza carte");
        System.out.println("p. Imprumuta carte");

        System.out.println("q. Modifica carte");
        System.out.println("r. Modifica categorie");
        System.out.println("s. Anuleaza abonament");
        System.out.println("t. Exit");

        System.out.println("Introduceti o comanda:");

    }


}
