import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
final class Factura{
    private final String denumireClient;
    private final LocalDate dataEmitere;

    private final List<Linie> linii;
    static final class Linie{
        private final String produs;
        private final double pret;
        private final int cantitate;

        public Linie(String produs, double pret, int cantitate) {
            this.produs = produs;
            this.pret = pret;
            this.cantitate = cantitate;
        }

        public String getProdus() {
            return produs;
        }

        public double getPret() {
            return pret;
        }

        public int getCantitate() {
            return cantitate;
        }

        @Override
        public String toString() {
            return "Linie{" +
                    "produs='" + produs + '\'' +
                    ", pret=" + pret +
                    ", cantitate=" + cantitate +
                    '}';
        }
    }

    public Factura(String denumireClient, LocalDate dataEmitere) {
        this.denumireClient = denumireClient;
        this.dataEmitere = dataEmitere;
        this.linii=new ArrayList<>();
    }

    public String getDenumireClient() {
        return denumireClient;
    }

    public LocalDate getDataEmitere() {
        return dataEmitere;
    }
    public int getNrLinii(){
        return linii.size();
    }
    public Linie getLinie(int index){
        return linii.get(index);
    }
    public void addLinie(Linie linie){
        linii.add(linie);
    }

    @Override
    public String toString() {
        var rezultat= new StringBuilder();
        rezultat.append(String.format("%s din %s%n", denumireClient, dataEmitere));
        for(var linie:linii){
            rezultat.append(" " + linie + "\n");
        }
        return rezultat.toString();
    }
}

public class Main {
    static  List<Factura> generareListaFacturi( int nrFacturi, LocalDate dataMinima){
        String[] denumiriClienti = new String[]{
                "ALCOR CONSTRUCT SRL",
                "SC DOMINO COSTI SRL",
                "SC TRANSCRIPT SRL",
                "SIBLANY SRL",
                "INTERFLOOR SYSTEM SRL",
                "MERCURY  IMPEX  2000  SRL",
                "ALEXANDER SRL",
                "METAL INOX IMPORT EXPOSRT SRL",
                "EURIAL BROKER DE ASIGURARE SRL"
        };

        String[] denumiriProduse = new String[]{
                "Stafide 200g",
                "Seminte de pin 300g",
                "Bulion Topoloveana 190g",
                "Paine neagra Frontera",
                "Ceai verde Lipton"

        };

        double[] preturiProduse = new double[]{
                5.20,
                12.99,
                6.29,
                4.08,
                8.99
        };
        var facturi = new ArrayList<Factura>();
        var random = new Random(42);
        for(var indexFactura = 0; indexFactura<nrFacturi;indexFactura++){
            var factura = new Factura(denumiriClienti[random.nextInt(denumiriClienti.length)],dataMinima.plusDays(random.nextInt(100)));
            var nrLinii = 1 + random.nextInt(10);
            for(var indexLinie = 0; indexLinie< nrLinii; indexLinie++) {
                int indexProdus = random.nextInt(denumiriProduse.length);

                factura.addLinie(new Factura.Linie(denumiriProduse[indexProdus],preturiProduse[indexProdus],1+random.nextInt(20)));
            }
            facturi.add(factura);
        }
        return facturi;
    }
    static void salvareFacturi(String caleFisier, List<Factura> facturi){
        try(var fisier= new DataOutputStream(new FileOutputStream(caleFisier))){
            for(var factura: facturi){
                fisier.writeUTF(factura.getDenumireClient());
                fisier.writeInt(factura.getDataEmitere().getYear());
                fisier.writeInt(factura.getDataEmitere().getMonthValue());
                fisier.writeInt(factura.getDataEmitere().getDayOfMonth());
                fisier.writeInt(factura.getNrLinii());
                for(int i=0;i< factura.getNrLinii();i++){
                    var linie = factura.getLinie(i);
                    fisier.writeUTF(linie.getProdus());
                    fisier.writeDouble(linie.getPret());
                    fisier.writeInt(linie.getCantitate());
                }
            }
        }catch(IOException exception){
            System.out.println("EROARE: " + exception.getMessage());
        }
    }
    static List<Factura> incarcareFacturi(String caleFisier){
        var facturi= new ArrayList<Factura>();
        try( var fisier= new DataInputStream(new FileInputStream(caleFisier))){
            while(fisier.available() > 0){
                var factura= new Factura(fisier.readUTF(),
                        LocalDate.of(fisier.readInt(), fisier.readInt(),fisier.readInt()));
                var nrLinii = fisier.readInt();
                for(int i=0;i<nrLinii;i++){
                    factura.addLinie(new Factura.Linie(fisier.readUTF(), fisier.readDouble(), fisier.readInt()));
                }
                facturi.add(factura);
            }
        }catch(IOException exception){
            System.out.println("EROARE: " + exception.getMessage());
        }
        return facturi;
    }
    public static void main(String[] args) {
        var facturi= generareListaFacturi(7, LocalDate.of(2024,1,1));
        salvareFacturi("facturi.dat", facturi);
        facturi = incarcareFacturi("facturi.dat");
        for(var factura:facturi){
            System.out.println(factura);
        }
    }
}