
/*
Să se construiască o aplicație în care:
- programul principal pornește 10 fire de execuție noi
- fiecare fir de execuție:
	afișează în ordine numerele de la 1 la 100 și identificatorul firului de execuție
	așteaptă 1-100 milisecunde între afișări
- programul principal afișează identificatorul firului de execuție care a câștigat (a ajuns primul la 100)
- când un fir de execuție câștigă celelalte fire de execuție trebuie să se oprească și să afișeze numărul la care s-au oprit
*/

import java.util.ArrayList;
import java.util.List;

class FirNumarare extends Thread {
    private int n = 0;
    static volatile long idCastigator = 0;

    @Override
    public void run() {

        for (int n = 1; n <= 100; n++) {
            try {
                Thread.sleep(1 + (int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
            if (idCastigator > 0) {
                System.out.println(getId() + " - am pierdut - am ajuns până la " + n);
                return;
            }
            System.out.printf("#%2d: %d%n", getId(), n);
        }
        idCastigator = getId();
    }
}

public class Seminar01_AfisareNumere {
    public static void main(String[] args) throws InterruptedException {

        // 1. Pornim firele de execuție și reținem referințele într-o listă
        List<Thread> fire = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var fir = new FirNumarare();
            fire.add(fir);
            fir.start();
        }

        // 2. Așteptăm până când toate firele de execuție s-au terminat
        while (fire.stream().anyMatch(fir -> fir.isAlive())) {
        }

        // 3. Afișăm câștigătorul
        System.out.println("Câștigătorul este " + FirNumarare.idCastigator);
    }
}
