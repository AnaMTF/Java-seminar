import java.time.LocalDate;

public final class Tranzactie {
    private final TipTranzactie tip;
    private final LocalDate data;
    private final int codProdus;
    private final int cantitate;

    public Tranzactie(TipTranzactie tip, LocalDate data, int codProdus, int cantitate) {
        this.tip = tip;
        this.data = data;
        this.codProdus = codProdus;
        this.cantitate = cantitate;
    }

    public TipTranzactie getTip() {
        return tip;
    }

    public LocalDate getData() {
        return data;
    }

    public int getCodProdus() {
        return codProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "tip=" + tip +
                ", data=" + data +
                ", codProdus=" + codProdus +
                ", cantitate=" + cantitate +
                '}';
    }
}
