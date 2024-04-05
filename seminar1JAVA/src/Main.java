import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String nume = scanner.nextLine(); // numele cu tot cu spatiu, daca e doar next() se opreste la primul spatiu
        System.out.printf("Hello and welcome %s! %n", nume); //%n separator de linie noua pt platforma in care ruleaza e
        System.out.println();

        System.out.print(" numar1 = ");
        int numar1 = Integer.parseInt(scanner.nextLine()); //parseInt pt convertire din string in int (arunca eroare pt altceva decat int ex numar1= ana)

        System.out.print(" numar2 = ");
        int numar2 = scanner.nextInt();

//        System.out.println(numar1);
//        System.out.println(numar2);
        System.out.printf("%d + %d = %d%n ", numar1, numar2, numar1+numar2);
// daca avem de citit doar nr putem folosi doar nextInt, altfel nextLine

        System.out.println(String.format("%d + %d = %d%n", numar1, numar2, numar1+numar2));
    }
}