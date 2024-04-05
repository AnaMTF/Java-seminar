import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Student[] studenti = new Student[0];

    static void citireStudenti(){
        //citim cati studenti sunt
        int numarStudenti = Integer.parseInt(scanner.nextLine());
        studenti = new Student[numarStudenti]; //alocare memorie pt referint pt studenti
        for (int i =0; i<numarStudenti;i++){
            var linieCitita = scanner.nextLine().split(",");
            Student studentAdaugat = new Student(
                Integer.parseInt(linieCitita[0]), //id
                linieCitita[1], //nume student
                Integer.parseInt(linieCitita[2]), //grupa
                Integer.parseInt(linieCitita[3]) //anul
            );
            linieCitita = scanner.nextLine().split(",");
            for(int j=0;j<linieCitita.length;j+=2){
                studentAdaugat.add(new Nota(linieCitita[j],Integer.parseInt(linieCitita[j+1])));
            }
            studenti[i]= studentAdaugat;
        }
    }

    static void afisareStudenti(String mesaj){
        System.out.println(mesaj);
        for(var student:studenti){
            System.out.println(student.toString());
        }
    }

    static void citireCatalog(){
        while (scanner.hasNextLine()) {
            String denumireDisciplina = scanner.nextLine();
            int numarNote = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numarNote; i++) {
                var linieCitita = scanner.nextLine().split(",");
                int idStudent = Integer.parseInt(linieCitita[0]);
                int nota = Integer.parseInt(linieCitita[1]);
                for (Student student : studenti) {
                    if (student.getIdStudent() == idStudent) {
                        student.add(new Nota(denumireDisciplina, nota));
                    }
                }
            }
        }
    }

    static void afisareCatalog(String numeDisciplina){
        //sortat descrescator dupa nota
        System.out.println("\nCatalogul pentru disciplina " + numeDisciplina);
        int numarNoteCatalog = 0;
        for (Student student : studenti) {
            for (Nota nota : student.getNote()) {
                if (nota.getNumeDisciplina().equals(numeDisciplina)) {
                    numarNoteCatalog++;
                }
            }
        }
        ElementCatalog[] catalog = new ElementCatalog[numarNoteCatalog];
        int index = 0;
        for (Student student : studenti) {
            for (Nota nota : student.getNote()) {
                if (nota.getNumeDisciplina().equals(numeDisciplina)) {
                    catalog[index] = new ElementCatalog(student.getNume(), nota.getNota());
                    index++;
                }
            }
        }
        Arrays.sort(catalog, new Comparator<ElementCatalog>() {
            @Override
            public int compare(ElementCatalog element1, ElementCatalog element2) {
                return -Integer.compare(element1.getNota(), element2.getNota());
            }
        });

        for (ElementCatalog element : catalog) {
            System.out.println(element.toString());
        }
    }

    public static void main(String[] args) {
        citireStudenti();
        afisareStudenti("Studentii cititi sunt:");
        citireCatalog();
        afisareStudenti("Studentii dupa adaugarea notelor sunt:" );
        afisareCatalog("Programare Java");
        afisareCatalog("POO");
        afisareCatalog("Structuri de date");

    }
}