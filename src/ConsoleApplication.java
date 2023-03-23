import controler.Controler;


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
                    break;
                case "b":
                    Controler.adaugaCategorie();
                    break;
                case "c":
                    Controler.adaugaEditura();
                    break;
                case "d":
                    Controler.adaugaAutor();
                    break;
                case "e":
                    Controler.adaugaCititor();
                    break;


                case "f":
                    Controler.afisareCarti();
                    break;
                case "g":
                    Controler.afisareAdresa();
                    break;
                case "h":
                    Controler.afisareAbonamente();
                    break;
                case "i":
                    Controler.afisareEditura();
                    break;
                case "j":
                    Controler.afisareAutori();
                    break;
                case "k":
                    Controler.afisareCititori();
                    break;
                case "l":
                    Controler.afisareCategorie();
                    break;


                case "m":
                    Controler.ReturneazaCarte();
                    break;
                case "n":
                    Controler.ImprumutaCarte();
                    break;

                case "o":
//                    if(user!=null)
//                        Controler.anuleazaAbonament(user);
//                    else
//                        System.out.println("nu sunteti autentificat, va rugam autentificati-va");
//                    break;

                case "p":
                    return;
                default:
                    System.out.println("nu ati introdus o comanda valida");
                    break;
            }

            System.out.println("Apasati oricetatasa pentru a continua");
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
