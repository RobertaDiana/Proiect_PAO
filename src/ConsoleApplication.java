import models.Persoana;

import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {

        Persoana user=null;
        while(true) {
            conectare(user);
            meniu();

            Scanner sc = new Scanner(System.in);

            String comand = sc.next();

            switch (comand) {
                case "a":
                    System.out.println("dgwuwbjfbewfbifbrfbrbferufbrufberfberufebubrebrbrbfrbrbfrfrbfefe");
                    break;
                case "m":
                    return;
            }
        }

    }

    private static void meniu(){
        System.out.println("*********************************");


        System.out.println("************My book**************");
        System.out.println("*********************************");


        System.out.println("");


        System.out.println("a. Login");
        System.out.println("b. Logout");
        System.out.println("c. Creare cont");
        System.out.println("d. Returneaza carete");
        System.out.println("e. Imprumuta carte");
        System.out.println("f. Adauga caret");
        System.out.println("g. Adauga categorie in lista de categorii");
        System.out.println("h. Modifica cont");
        System.out.println("i. Modifica carte");
        System.out.println("j. Modifica categorie");
        System.out.println("k. ");
        System.out.println("l. ");
        System.out.println("m. Exit");

        System.out.println("Introduceti o comanda:");

    }

    private static void conectare(Persoana user){
        System.out.println("*********************************");
        if(user!=null){
            System.out.println("Utilizator : " + user.getNume() + " " + user.getPrenume());
        }else{
            System.out.println("Utilizator : neconectat" );
        }

    }
}
