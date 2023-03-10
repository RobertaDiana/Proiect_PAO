import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {


        while(true) {

            meniu();

            Scanner sc = new Scanner(System.in);

            String comand = sc.next();

            switch (comand) {
                case "a":
                    System.out.println("dgwuwbjfbewfbifbrfbrbferufbrufberfberufebubrebrbrbfrbrbfrfrbfefe");
                    break;
                case "b":
                    return;
            }
        }

    }

    private static void meniu(){
        System.out.println("*********************************");


        System.out.println("**************My book ************");
        System.out.println("*********************************");


        System.out.println("");


        System.out.println("a. Login");
        System.out.println("b. Logout");
        System.out.println("da mi comanda");

    }

}
