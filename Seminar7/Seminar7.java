import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Programare {
    private final String ziua;
    private final String interval;
    private final Profesor profesor;
    private final String disciplina;
    private final String sala;
    private final boolean esteCurs;
    private final String formatie;

    public Programare(String ziua, String interval, Profesor profesor, String disciplina, String sala, boolean esteCurs, String formatie) {
        this.ziua = ziua;
        this.interval = interval;
        this.profesor = profesor;
        this.disciplina = disciplina;
        this.sala = sala;
        this.esteCurs = esteCurs;
        this.formatie = formatie;
    }

    public String getZiua() {
        return ziua;
    }

    public String getInterval() {
        return interval;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getSala() {
        return sala;
    }

    public boolean isEsteCurs() {
        return esteCurs;
    }

    public String getFormatie() {
        return formatie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Programare{");
        sb.append("ziua='").append(ziua).append('\'');
        sb.append(", interval='").append(interval).append('\'');
        sb.append(", profesor=").append(profesor);
        sb.append(", disciplina='").append(disciplina).append('\'');
        sb.append(", sala='").append(sala).append('\'');
        sb.append(", esteCurs=").append(esteCurs);
        sb.append(", formatie='").append(formatie).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
class Profesor {
    private final int idProfesor;
    private final String prenume;
    private final String nume;
    private final String departament;

    public Profesor(int idProfesor, String prenume, String nume, String departament) {
            this.idProfesor = idProfesor;
            this.prenume = prenume;
            this.nume = nume;
            this.departament = departament;
    }

    public int getIdProfesor() {
            return idProfesor;
    }

        public String getPrenume() {
            return prenume;
        }

        public String getNume() {
            return nume;
        }

        public String getDepartament() {
            return departament;
        }

        public Profesor(String linie){
            var componente = linie.split("\t");
            idProfesor = Integer.parseInt(componente[0]);
            prenume = componente[1];
            nume = componente[2];
            departament = componente[3];
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Profesor{");
            sb.append("idProfesor=").append(idProfesor);
            sb.append(", prenume='").append(prenume).append('\'');
            sb.append(", nume='").append(nume).append('\'');
            sb.append(", departament='").append(departament).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

public class Seminar7 {

    public static void main(String[] args) throws IOException {
        Map<Integer,Profesor> profesori;
        try(var fisier = new BufferedReader(new FileReader("profesori.txt"));){ //citire din fisier in try cu resurse

            profesori = fisier.lines().map(Profesor::new).collect(Collectors.toMap(Profesor::getIdProfesor, p -> p)); //p -> p este echivalent cu Function.identity() si ne returneaza obiectul p Profesor

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<Programare> programari;
        try(var fisier = new BufferedReader(new FileReader("programari.txt"));) { //citire din fisier in try cu resurse

            programari = fisier.lines().map(linie -> new Programare(
                    linie.split("\t")[0],
                    linie.split("\t")[1],
                    profesori.get(Integer.parseInt(linie.split("\t")[2])),
                    linie.split("\t")[3],
                    linie.split("\t")[4],
                    Boolean.parseBoolean(linie.split("\t")[5]),
                    linie.split("\t")[6])).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("----------------------------------------Profesori:");
//        profesori.values().stream().forEach(System.out::println);
        System.out.println("----------------------------------------Programari:");
        programari.stream().forEach(System.out::println);
        System.out.println("----------------------------------------Discipline ordonate alfabetic:");
        programari.stream().filter(Programare::isEsteCurs)
                .map(Programare::getDisciplina)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        programari.stream()
                .collect(Collectors.groupingBy(Programare::getProfesor))
                .entrySet().stream()
                .forEach(e ->{
                    var profesor = e.getKey();
                    var listaProgramari = e.getValue();
                    System.out.printf("%-30s %2d cursuri %2d seminarii %n",
                            profesor.getNume() + " " + profesor.getPrenume(),
                            listaProgramari.stream().filter(Programare::isEsteCurs).count(),
                            listaProgramari.stream().filter(p -> !p.isEsteCurs()).count());
                });
    }
}
