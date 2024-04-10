public final class Angajat {
    private final String numeAngajat;
    private final String numeDepartament;
    private final int salariu;

    public Angajat(String numeAngajat, String numeDepartament, int salariu) {
        this.numeAngajat = numeAngajat;
        this.numeDepartament = numeDepartament;
        if (salariu >= 1000 && salariu <=20000){
            this.salariu = salariu;
        }
        else {
            throw new RuntimeException("Salariul trebuie sa fie intre 1000 si 20000");
        }
    }

    @Override
    public String toString() {
        return String.format("Angajatul %s din departamentul %s are salariul %d lei.\n",this.numeAngajat, this.numeDepartament, this.salariu);
    }

    public String getNumeAngajat() {
        return numeAngajat;
    }

    public String getNumeDepartament() {
        return numeDepartament;
    }

    public int getSalariu() {
        return salariu;
    }
}
