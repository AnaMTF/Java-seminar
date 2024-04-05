public class ElementCatalog {
    String numeStudent;
    int nota;


    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public ElementCatalog(String numeStudent, int nota) {
        this.numeStudent = numeStudent;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return String.format("Student: %s, Nota: %d", numeStudent, nota);
    }
}
