import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in); //ptc il folosim de mai multe ori si nu poate sa fie partajat

    static Student[] citireStudenti(){
        var numarStudenti = Integer.parseInt(scanner.nextLine());
        var studenti = new Student[numarStudenti];
        for ( var i = 0; i< numarStudenti; i++){
            var linie = scanner.nextLine().split(",");
            var student = new Student(
                Integer.parseInt(linie[0]),
                linie[1],
                linie[2],
                Integer.parseInt(linie[3])
            );
            studenti[i]= student;
            linie = scanner.nextLine().split(",");
            for(int j = 0; j< linie.length; j+=2){ //parcurgem din 2 in 2 pentru fiecare nota
                var denumimre = linie[j];
                var valoare = Integer.parseInt(linie[j+1]);
                student.add(new Nota(denumimre, valoare));
            }
        }
        return studenti;
    }

    static void afisare(Student[] studenti, String mesaj){
        System.out.println(mesaj + " ");
        for (var student : studenti){
            System.out.println(student);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        var studenti = citireStudenti();
        afisare(studenti, "Studenti cititi. DUPA CITIRE");
    }
}
