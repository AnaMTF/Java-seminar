import java.util.Arrays;

public class Student {
    int idStudent;
    String nume;
    String grupa;
    int anul;
    Nota[] note;

    public Student(int idStudent, String nume, String grupa, int anul) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.anul = anul;
        this.note = new Nota[0]; //asa facem pt clase care contin vectori
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

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
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

    public void add(Nota nota){
        for (var notaExistenta : note){
            if (nota.getNumeDisciplina().equals(notaExistenta.getNumeDisciplina())){
                notaExistenta.setNota(nota.getNota());
                return;

            }
        }
        note = Arrays.copyOf(note, note.length + 1); // adaugam notele in vector
        note[note.length - 1] = nota; // sa zicem len e 13 si o sa fie 14 ptc avem +1 mai sus, -1 pt a ajunge pe ultima pozitie
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", nume='" + nume + '\'' +
                ", grupa='" + grupa + '\'' +
                ", anul=" + anul +
                ", note=" + Arrays.toString(note) +
                '}';
    }
}
