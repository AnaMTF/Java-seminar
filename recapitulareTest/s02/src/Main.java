import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Student stud = new Student("Ana", "Java", 10);
        System.out.println("=== Cerinta 1 ===");
        System.out.println(stud);

        List<Student> listaStudenti = new ArrayList<>();

        FileInputStream fis = new FileInputStream("studenti.txt");

        Map<String, Integer> raportNote = new HashMap<>();
        try (Scanner scanner = new Scanner(fis)){
            while (scanner.hasNextLine()){
                String[] lineSplit = scanner.nextLine().split(",");
                String nume = lineSplit[0];
                String materie = lineSplit[1];
                int nota = Integer.parseInt(lineSplit[2]);

                Student studentCitit =new Student(nume, materie, nota);
                listaStudenti.add(studentCitit);
                if (raportNote.containsKey(materie)){
                    raportNote.put(materie, raportNote.get(materie) +1);
                }else{
                    raportNote.putIfAbsent(materie, 1);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("=== Cerinta 2 ===");
        System.out.println(listaStudenti.size());
        System.out.println("=== Cerinta 3 ===");
        listaStudenti.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getNota() == o2.getNota()) {
                    return o1.getNota();
                }
                if (o1.getNota()< o2.getNota()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });

        System.out.println(listaStudenti);

        System.out.println("Scrieti materia: ");
        Scanner scannerTastatura = new Scanner(System.in);

        String materieCitita = scannerTastatura.nextLine();

        for (Student s : listaStudenti){
            if (s.getMaterie().equals(materieCitita) ){
                System.out.println(s);
            }
        }
        System.out.println("=== Cerinta 4 ===");

        try(FileOutputStream fous = new FileOutputStream("raportStudenti.txt");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fous))){

            writer.write("Raport discipline si numar de note:\n");
            for (String materie : raportNote.keySet()){
                writer.write(String.format("%s : %d\n", materie, raportNote.get(materie)));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(raportNote);
    }
}