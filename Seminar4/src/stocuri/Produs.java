package stocuri;

import java.util.Objects;

public final class Produs {
    //implicit sa facem clasa imutabila, unless it says otherwise
    private final int  cod;
    private final String denumire;

    public Produs(int cod, String denumire) {
        this.cod = cod;
        this.denumire = denumire;
    }

    public Produs(int cod) {
        this(cod, "-");
    }

    public int getCod() {
        return cod;
    }

    public String getDenumire() {
        return denumire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return cod == produs.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod); //lista cu toti parametri pe baza carora am generat equals
    }

    @Override
    public String toString() {
        return "Produs{" +
                "cod=" + cod +
                ", denumire='" + denumire + '\'' +
                '}';
    }
}
