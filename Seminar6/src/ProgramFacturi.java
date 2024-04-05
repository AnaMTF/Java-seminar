import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Factura {

    private final String denumireClient;
    private final LocalDate dataEmitere;
    private final List<Linie> linii;

    // Observație:
    //
    // Deși are toate câmpurile marcate ca final, clasa Factură NU este imutabilă.
    // Câmpul linii conține o referință la un obiect (în cazul de față un ArrayList)
    // care poate fi modificat - vezi metoda adaugaLinie. În cazul de față marcajul
    // final pentru linii specifică doar că obiectul listă nu poate fi înlocuit cu totul
    // după ce obiectul Factura a fost creat, dar conținutul lui poate fi modificat.

    public Factura(String denumireClient, LocalDate dataEmitere) {
        this.denumireClient = denumireClient;
        this.dataEmitere = dataEmitere;
        this.linii = new ArrayList<>();
    }

    public String getDenumireClient() {
        return denumireClient;
    }

    public LocalDate getDataEmitere() {
        return dataEmitere;
    }

    public double getValoare() {
        double valoare = 0;
        for (var linie : linii) {
            valoare += linie.getValoare();
        }
        return valoare;
    }

    public int getNumarLinii() {
        return linii.size();
    }

    public Linie getLinie(int index) {
        return linii.get(index);
    }

    public void adaugaLinie(Linie linie) {
        linii.add(linie);
    }

    public void adaugaLinie(String produs, double pret, int cantitate) {
        adaugaLinie(new Linie(produs, pret, cantitate));
    }

    @Override
    public String toString() {
        var dateFormat = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s, Client: %s%n",
                dateFormat.format(dataEmitere), denumireClient));
        for (var linie : linii) {
            sb.append(linie.toString() + System.lineSeparator());
        }
        return sb.toString();
    }

    static class Linie {
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

        public double getValoare() {
            return pret * cantitate;
        }

        @Override
        public String toString() {
            return String.format("%-25s %3d x %5.2f RON = %6.2f RON",
                    produs, cantitate, pret, getValoare());
        }
    }
}

public class ProgramFacturi {

    public final static int NUMAR_MAXIM_PRODUSE = 10;

    public static List<Factura> generareListaFacturi(
            int numarFacturi, LocalDate dataMinima) {

        // 1. Datele fixe folosite la generarea facturilor
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

        // 2. Inițializare generare
        Random rand = new Random(42); // vezi https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html
        int numarMaximZile = (int) ChronoUnit.DAYS.between(dataMinima, LocalDate.now());
        List<Factura> facturi = new ArrayList<>();

        // 3. Generare facturi
        for (int indexFactura = 0; indexFactura < numarFacturi; indexFactura++) {

            var denumireClient = denumiriClienti[rand.nextInt(denumiriClienti.length)];
            var data = dataMinima.plusDays(rand.nextInt(numarMaximZile));   // maxim data curentă

            var factura = new Factura(denumireClient, data);

            // Adăugăm cel puțin un rând
            var numarProduse = 1 + rand.nextInt(NUMAR_MAXIM_PRODUSE - 1);
            for (int indexProdus = 0; indexProdus < numarProduse; indexProdus++) {

                // Atenție: produsul și prețul trebuie să fie corelate (aceeași poziție)
                var produsSelectat = rand.nextInt(denumiriProduse.length);
                var produs = denumiriProduse[produsSelectat];
                var pret = preturiProduse[produsSelectat];

                var cantitate = 1 + rand.nextInt(19);

                factura.adaugaLinie(produs, pret, cantitate);
            }

            facturi.add(factura);
        }

        return facturi;
    }

    static void afisareFacturi(List<Factura> facturi) {
        for (var factura : facturi) {
            System.out.println(factura);
        }
    }

    static void salvareFacturi(String caleFisier, List<Factura> facturi)
            throws IOException {

        // Dacă a fost specificat și un folder
        if (new File(caleFisier).getParentFile() != null) {
            // ne asigurăm că acesta există
            new File(caleFisier).getParentFile().mkdirs();
        }

        try (var fisier = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(caleFisier)))) {
            for (var factura : facturi) {

                // Scriere inforații globale pentru factură
                fisier.writeUTF(factura.getDenumireClient());
                fisier.writeInt(factura.getDataEmitere().getYear());
                fisier.writeInt(factura.getDataEmitere().getMonthValue());
                fisier.writeInt(factura.getDataEmitere().getDayOfMonth());
                fisier.writeInt(factura.getNumarLinii());

                for (var indexLinie = 0; indexLinie < factura.getNumarLinii(); indexLinie++) {
                    var linie = factura.getLinie(indexLinie);

                    // Scriem datele pentru linia curentă
                    fisier.writeUTF(linie.getProdus());
                    fisier.writeDouble(linie.getPret());
                    fisier.writeInt(linie.getCantitate());
                }
            }
        }
    }

    static List<Factura> incarcareFacturi(String caleFisier)
            throws IOException {

        List<Factura> facturi = new ArrayList<>();
        try (var fisier = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(caleFisier)))) {

            // Citim din fișier până la producerea excepției EOFException
            while (true) {

                // Citim datele globale pentru factură
                var denumireClient = fisier.readUTF();
                int an = fisier.readInt(), luna = fisier.readInt(), zi = fisier.readInt();
                int numarLinii = fisier.readInt();

                var factura = new Factura(denumireClient, LocalDate.of(an, luna, zi));

                for (var indexLinie = 0; indexLinie < numarLinii; indexLinie++) {

                    // Citim datele pentru linia curentă
                    var denumire = fisier.readUTF();
                    var pret = fisier.readDouble();
                    var cantitate = fisier.readInt();
                    factura.adaugaLinie(denumire, pret, cantitate);
                }

                facturi.add(factura);
            }
        } catch (EOFException e) {
            // nu facem nimic
        }

        return facturi;
    }
    
    public static void main(String[] args)
            throws IOException {
        final String caleFisier = "date\\facturi.dat";

        var facturi = generareListaFacturi(
                30,
                LocalDate.of(2020, 1, 1));

        salvareFacturi(caleFisier, facturi);
        facturi = incarcareFacturi(caleFisier);
        System.out.println("Facturi incarcate:");
        afisareFacturi(facturi);

        System.out.println();
        System.out.println("=============================================");
        System.out.println();

        generareRaport("raport.txt", facturi);
    }

    static void generareRaport(String caleFisier, List<Factura> facturi){
        class DateClient{
            final String client; // nu avem de ce sa modificam numele clientului
            int numarFacturi;  // numarul de facturi emise pe care l putem actualiza, au valoarea 0
            double valoareTotala;

            public DateClient(String client) {
                this.client = client;
            }

            @Override
            public String toString() {
                return String.format("%-30s %2d facturi, TOTAL %8.2f RON",
                        client, numarFacturi, valoareTotala);
            }
        }
//        var test = new DateClient("test SRL");
//        test.numarFacturi = 3;
//        test.valoareTotala = 1000.13;
//        System.out.println(test);

        Map<String, DateClient> clienti = new HashMap<>();
        for (var factura : facturi){
            var client = factura.getDenumireClient();
            if (!clienti.containsKey(client)){
                clienti.put(client, new DateClient(client));
            }
            var dateClient = clienti.get(client); //intoarce adresa obiectului din dictionar
            dateClient.numarFacturi++; //crestem nr de facturi emise de client
            dateClient.valoareTotala += factura.getValoare();
        }

        System.out.println(clienti);

        //parcurgem dictionarul
        for (var dateClient : clienti.values()){
            System.out.println(dateClient);
        }
        //clienti.keySet() // intoarce multime cu cheile din dictionar

//        for (var pereche : clienti.entrySet()){
//            System.out.println(pereche.getValue());  //intoarce un collection view cu toate valorile din dictionar
//        }

        //sortam datele din dictionar
        var dateClienti = new ArrayList<DateClient>(clienti.values());
        Collections.sort(dateClienti, (c1, c2) -> Double.compare(c1.valoareTotala, c2.valoareTotala)); //ordonare descrescatoare
        System.out.println("Dupa sortare:");
        for (var dateClient : dateClienti){
            System.out.println(dateClient);
        }

        try (var fisier = new PrintWriter(caleFisier);){ //punem fisierul ca resursa in try with resources

            for (var date : dateClienti){
                fisier.println(date);
            }

        } catch (FileNotFoundException e){
            System.err.println("Nu putem sa scriem in fisierul  " + caleFisier + " --- " +e.getMessage());
        }
    }
}