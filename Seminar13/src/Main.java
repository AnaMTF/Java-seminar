import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

enum TipTranzactie {
    INTRARE(1),
    IESIRE(-1);

    int semn;

    TipTranzactie(int semn) {
        this.semn = semn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TipTranzactie{");
        sb.append("semn=").append(semn);
        sb.append('}');
        return sb.toString();
    }
}

class Tranzactie{
    private final TipTranzactie tip;
    private final int cantitate;

    public Tranzactie(TipTranzactie tip, int cantitate) {
        this.tip = tip;
        this.cantitate = cantitate;
    }

    public TipTranzactie getTip() {
        return tip;
    }

    public int getCantitate() {
        return cantitate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tranzactie{");
        sb.append("tip=").append(tip);
        sb.append(", cantitate=").append(cantitate);
        sb.append('}');
        return sb.toString();
    }
}

class Produs{
    private final int cod;
    private final String denumire;
    private final List<Tranzactie> tranzactii;

    public Produs(int cod, String denumire, List<Tranzactie> tranzactii) {
        this.cod = cod;
        this.denumire = denumire;
        this.tranzactii = tranzactii;
    }

    public int getCod() {
        return cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Produs{");
        sb.append("cod=").append(cod);
        sb.append(", denumire='").append(denumire).append('\'');
        sb.append(", tranzactii=").append(tranzactii);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static List<Produs> citireXml(String caleFisierXml) throws Exception{
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();

        var produse = new ArrayList<Produs>();
        try(var fisier = new FileInputStream(caleFisierXml)){
            var document = builder.parse(fisier);
            var nodProduse = document.getDocumentElement();
            var noduriProduse = nodProduse.getElementsByTagName("produs");
            for (var i = 0; i< noduriProduse.getLength(); i++) {
                var nodProdus = (Element) noduriProduse.item(i);
                var cod = Integer.parseInt(nodProdus.getElementsByTagName("cod").item(0).getTextContent());
                // denumirile produselor
                var denumire = nodProdus.getElementsByTagName("denumire").item(0).getTextContent();
                var tranzactii = new ArrayList<Tranzactie>();
                var nodTranzactii = (Element)nodProdus.getElementsByTagName("tranzactii").item(0);
                var noduriTranzactie = nodTranzactii.getElementsByTagName("tranzactie"); //colectie de noduri de tip tranzactie
                for (var j = 0; j< noduriTranzactie.getLength(); j++){
                    var nodTranzactie = (Element)noduriTranzactie.item(j); //referinta la un singur nod de tip tranzactie
                    tranzactii.add(new Tranzactie(
                            TipTranzactie.valueOf(nodTranzactie.getAttribute("tip").toUpperCase()),
                                    Integer.parseInt(nodTranzactie.getAttribute("cantitate"))
                    ));
                }
                produse.add(new Produs(cod, denumire, tranzactii));
            }
        }
        return produse;
    }

    static void salvareJson(String calefisierJson, List<Produs> produse) throws Exception{
        var jsonProduse = new JSONArray();
        for (var produs : produse){
            var jsonProdus = new JSONObject();
            jsonProdus.put("cod", produs.getCod());
            jsonProdus.put("denumire", produs.getDenumire());
            var jsonTranzactii = new JSONArray();
            jsonProdus.put("tranzactii", jsonTranzactii);

            for(var tranzactie : produs.getTranzactii()){
                var jsonTranzactie = new JSONObject();
                jsonTranzactie.put("tip", tranzactie.getTip());
                jsonTranzactie.put("cantitate", tranzactie.getCantitate());
                jsonTranzactii.put(jsonTranzactie);
            }
            jsonProduse.put(jsonProdus);
        }
        try(var fisier = new FileWriter(calefisierJson)){
            jsonProduse.write(fisier,3,0);
        }
    }
    public static void main(String[] args) throws Exception{
        var produse = citireXml("Seminar13\\date\\produse.xml");
        produse.stream().forEach(System.out::println);

        salvareJson("Seminar13\\date\\produse.json", produse);
    }
}
