import controler.Controler;
import models.Cititor;

import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {

        Cititor user= null;
        Controler.init();


        while(true) {

            meniu(user);

            Scanner sc = new Scanner(System.in);

            String comand = sc.next();

            switch (comand) {
                case "a":
                    Controler.adaugaCarte();
                    break;
                case "d":
                    Controler.adaugaCategorie();
                    break;
                case "e":
                    Controler.adaugaEditura();
                    break;
                case "f":
                    Controler.adaugaAutor();
                    break;
                case "g":
                    Controler.adaugaCititor();
                    break;


                case "h":
                    Controler.afisareCarti();
                    break;
                case "i":
                    Controler.afisareAdresa();
                    break;
                case "j":
                    Controler.afisareAbonamente();
                    break;
                case "k":
                    Controler.afisareEditura();
                    break;
                case "l":
                    Controler.afisareAutori();
                    break;
                case "m":
                    Controler.afisareCititori();
                    break;
                case "n":
                    Controler.afisareCategorie();
                    break;


                case "o":
                    if(user!=null) {
                        System.out.println("Introduceti numele cartii:");
                        String carte = sc.nextLine();
                        Controler.ReturneazaCarte(user,carte );
                    }else
                        System.out.println("nu sunteti autentificat, va rugam autentificati-va");
                    break;
                case "p":
                    if(user!=null) {
                        System.out.println("Introduceti numele cartii:");
                        String carte = sc.nextLine();
                        Controler.ImprumutaCarte(user,carte );
                    }else
                        System.out.println("nu sunteti autentificat, va rugam autentificati-va");
                    break;


                case "s":
                    if(user!=null)
                        Controler.anuleazaAbonament(user);
                    else
                        System.out.println("nu sunteti autentificat, va rugam autentificati-va");
                    break;

                case "v":
                    return;
                default:
                    System.out.println("nu ati introdus o comanda valida");
                    break;
            }

            System.out.println("Apasati oricetatasa pentru a continua");
            String op=sc.next();

        }

    }

    private static void meniu(Cititor user){

        if(user!=null) {
            System.out.println("*************User**************");
            System.out.println(user.getUserName());

        }
        System.out.println("************Meniu**************");


        System.out.println("a. Adauga carte");
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


        System.out.println("t. LogIn");
        System.out.println("u. LogOut");
        System.out.println("v. Exit");

        System.out.println("Introduceti o comanda:");

    }


}
