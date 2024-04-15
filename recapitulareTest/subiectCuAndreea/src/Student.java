public final class Student {
    private final String nume;
    private final String materie;
    private final int nota;

    public Student(String nume, String materie, int nota) {
        this.nume = nume;
        this.materie = materie;
        if (nota <=10 && nota>=1){
            this.nota = nota;
        }else {
            throw new RuntimeException("Nota trebuie sa fie intre 1 si 10");
        }
    }

    public String getNume() {
        return nume;
    }

    public String getMaterie() {
        return materie;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", this.nume,this.materie, this.nota);
    }
}
