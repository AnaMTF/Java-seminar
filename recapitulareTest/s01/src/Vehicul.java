import java.util.ArrayList;
import java.util.List;

class Vehicul{
    private String nrInmatriculare;
    private String marca;
    private int nrPasageri;


    public Vehicul(String nrInmatriculare, String marca, int nrPasageri) {
        this.nrInmatriculare = nrInmatriculare;
        this.marca = marca;
        this.nrPasageri = nrPasageri;
    }

    @Override
    public String toString() {
        return String.format(" Vehiculul cu nr de inmatriculare: %s , marca %s , si %d pasageri ", getNrInmatriculare(),getMarca(),getNrPasageri());
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getNrPasageri() {
        return nrPasageri;
    }

    public void setNrPasageri(int nrPasageri) {
        this.nrPasageri = nrPasageri;
    }

    public boolean esteDeLux(){
        List<String> listaMasiniDeLux= new ArrayList<String>();
        listaMasiniDeLux.add("Audi");
        listaMasiniDeLux.add("Mercedes");
        listaMasiniDeLux.add("BMW");
        return listaMasiniDeLux.contains(this.marca);
    }
}