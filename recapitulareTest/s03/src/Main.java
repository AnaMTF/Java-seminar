import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Angajat angajat = new Angajat("Gigi", "IT", 1234);
        List<Angajat> listaAngajati = new ArrayList<>();
        Map<String, Integer> nrAngajatiDep = new HashMap<>();
        Map<String, Integer> sumaSalariilorDep = new HashMap<>();
        try(FileReader fisier = new FileReader("angajati.txt");
            Scanner scannerFisier = new Scanner(fisier)){

            while (scannerFisier.hasNextLine()){
                String[] linie = scannerFisier.nextLine().split(";");
                String nume = linie[0];
                String departament = linie[1];
                int salariu = Integer.parseInt(linie[2]);
                Angajat angajatCitit = new Angajat(nume,departament,salariu);
                listaAngajati.add(angajatCitit);

                nrAngajatiDep.put(departament, nrAngajatiDep.getOrDefault(departament, 0)+1);
                sumaSalariilorDep.put(departament, sumaSalariilorDep.getOrDefault(salariu,0)+salariu);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("=== Cerinta 1 ===");
        System.out.println(angajat);
        System.out.println("=== Cerinta 2 ===");
        System.out.println(listaAngajati.size());


        listaAngajati.sort(new Comparator<Angajat>() {
            @Override
            public int compare(Angajat o1, Angajat o2) {
                if (o1.getSalariu() == o2.getSalariu()){
                    return 0;
                } else if (o1.getSalariu() < o2.getSalariu()) {
                    return 1;
                }else{
                    return -1;
                }

            }
        });

        System.out.println(listaAngajati);

        System.out.println("=== Cerinta 3 ===");
        System.out.println("Introduceti un departament");
        Scanner scannerTastatura = new Scanner(System.in);
        String departamentConsola = scannerTastatura.nextLine();

        for (Angajat a : listaAngajati){
            if (a.getNumeDepartament().equals(departamentConsola)){
                System.out.println(a.getNumeAngajat() + " " + a.getSalariu());
            }
        }
        System.out.println(sumaSalariilorDep);
        System.out.println(nrAngajatiDep);

        try(FileOutputStream fous = new FileOutputStream("raportAngajati.txt");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fous))) {
            writer.write("Raport departamente si medii salariale:\n");
            for (String depart : sumaSalariilorDep.keySet()){
                int suma = sumaSalariilorDep.get(depart);
                int count = nrAngajatiDep.get(depart);
                int medie = suma / count;

                writer.write(String.format("%s : %d\n", depart, medie));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}