public final class Student {
    private final String nume;
    private final String materie;
    private final int nota;

    public Student(String nume, String materie, int nota) {
        this.nume = nume;
        this.materie = materie;
        this.nota = nota;
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
        return String.format("%s %s %d", this.nume, this.materie, this.nota);
    }
}
