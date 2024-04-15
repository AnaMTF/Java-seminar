import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Student student = new Student("Ana", "Java", 10);
        System.out.println("=== Cerinta 1 ===");
        System.out.println(student);

        List<Student> listaStud = new ArrayList<>();

        FileInputStream fis =new FileInputStream("studenti.txt");
        try(Scanner scanner = new Scanner(fis)){
            while (scanner.hasNextLine()){
                String[] linieSplit = scanner.nextLine().split(",");
                String nume = linieSplit[0];
                String materie = linieSplit[1];
                int nota = Integer.parseInt(linieSplit[2]);

                Student studentCitit = new Student(nume, materie, nota);
                listaStud.add(studentCitit);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("=== Cerinta 2 ===");
        System.out.println(listaStud.size());

        listaStud.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getNota() == o2.getNota()){
                    return 0;
                }
                if (o1.getNota() < o2.getNota()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });

        System.out.println(listaStud);

        System.out.println("=== Cerinta 3 === ");
        System.out.println("Introduceti materia: ");
        Scanner scannerTastatura = new Scanner(System.in);
        String materiaCitita = scannerTastatura.nextLine();

        for (Student s: listaStud){
            if (s.getMaterie().equals(materiaCitita)){
                System.out.println(s);
            }
        }



    }
}