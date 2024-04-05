public enum TipTranzactie {
    INTRARE(1),  //ptc constructorul primeste param, trebuie sa dam si noi aici
    IESIRE(-1);

    private int semn;

    TipTranzactie(int semn) {
        this.semn = semn;
    }

    public int semn() { return semn;}
}
