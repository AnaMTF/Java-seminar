import java.util.Arrays;

public class Student {
    int idStudent;
    String nume;
    int grupa;
    int anul;
    Nota[] note;

    public Student(int idStudent, String nume, int grupa, int anul) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.anul = anul;
        this.note = new Nota[0];
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public int getAnul() {
        return anul;
    }

    public void setAnul(int anul) {
        this.anul = anul;
    }

    public Nota[] getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Studentul are " +
                "idStudent = " + idStudent +
                ", numele = '" + nume + '\'' +
                ", grupa = " + grupa +
                ", anul = " + anul +
                ", note = " + Arrays.toString(note) +
                ' ';
    }

    public void add(Nota notaNoua){
        for (Nota notaExistenta : note){
            if (notaExistenta.getNumeDisciplina().equals(notaNoua.getNumeDisciplina())){
                notaExistenta.setNota(notaNoua.getNota());
                return;
            }
        }
        note = Arrays.copyOf(note, note.length + 1);
        note[note.length -1] = notaNoua;
    }
}