public class Nota {
    String numeDisciplina;
    int nota;

    public String getNumeDisciplina() {
        return numeDisciplina;
    }

    public void setNumeDisciplina(String numeDisciplina) {
        this.numeDisciplina = numeDisciplina;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        if (nota < 1 || nota > 10) {
            throw new IllegalArgumentException("Nota trebuie sa fie intre 1 si 10");
        }
        this.nota = nota;
    }

    public Nota(String numeDisciplina, int nota) {
        this.numeDisciplina = numeDisciplina;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return String.format("Disciplina: %s, Nota: %d", numeDisciplina, nota);
    }
}