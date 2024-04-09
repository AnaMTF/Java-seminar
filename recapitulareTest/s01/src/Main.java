import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File fisier = new File("parcare.txt");
        Vehicul vehicul;
        List<Vehicul> listaVehicule = new ArrayList<>();

        int nrTotalPasageri = 0;
        int nrTotalPasageriMasiniDeLux = 0;
        final float taxa = 11.0f;
        Map<String, Float> raportTaxe = new HashMap<>();


        try {

            Scanner scanner = new Scanner(fisier);

            while (scanner.hasNextLine()){
                float taxaVehicul = taxa;
                String[] linieCitita = scanner.nextLine().split(",");  //citim o linie din fisier text

                String nrInmatriculare = linieCitita[0];
                String marca = linieCitita[1];
                int nrPasageri = Integer.parseInt(linieCitita[2]);

                vehicul = new Vehicul(nrInmatriculare,marca,nrPasageri);
                listaVehicule.add(vehicul);

                nrTotalPasageri += nrPasageri;

                if (vehicul.esteDeLux()){
                    nrTotalPasageriMasiniDeLux += nrPasageri;
                    taxaVehicul = taxaVehicul* 1.2f ;
                }

                String judet = nrInmatriculare.split("-")[0];
                if (raportTaxe.containsKey(judet)){
                    raportTaxe.put(judet, raportTaxe.get(judet) + taxaVehicul );
                }else {
                    raportTaxe.putIfAbsent(judet,taxaVehicul);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (FileOutputStream fisierOut = new FileOutputStream("raportParcare.txt");
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(fisierOut))){

            for (String judet : raportTaxe.keySet()){
                bfw.write(String.format("%s,%.2f\n", judet, raportTaxe.get(judet)));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=== Cerinta 1 ===");
        for (Vehicul vehiculDinLista : listaVehicule){
            System.out.println(vehiculDinLista);
        }
        System.out.println("=== Cerinta 2 ===");
        System.out.println( listaVehicule.size() + " vehicule cu "+ nrTotalPasageri + " pasageri");
        System.out.println("=== Cerinta 3 ===");
        System.out.println("Vehicule de lux: " + nrTotalPasageriMasiniDeLux + " pasageri");
        System.out.println("Alte vehicule: " + nrTotalPasageriMasiniDeLux + " pasageri");
        System.out.println("=== Cerinta 3 ===");
        System.out.println(raportTaxe);
    }
}