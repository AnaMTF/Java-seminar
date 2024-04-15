/*
Fie o clasă Cont cu un câmp sold de tip întreg și două metode: depune(suma) și getSold.
Să se scrie un program care lansează 10 de fire de execuție. Fiecare fir de execuție va depune câte 1 leu de 1000 de ori.
Să se afișeze soldul final din cont.
*/

import java.util.ArrayList;
import java.util.List;

class Cont {
    int sold = 0;

    public synchronized void depune(int suma) {
        sold += suma;
    }

    public int getSold() {
        return sold;
    }
}

public class Seminar02_SoldCont {
    public static void main(String[] args) throws InterruptedException {
        var cont = new Cont();

        List<Thread> fire = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var fir = new Thread(() -> {
                for (int index = 0; index < 1000; index++) {
                    cont.depune(1);
                }
            });
            fire.add(fir);
            fir.start();
        }

        for (var fir : fire) {
            fir.join();
        }

        System.out.println("Suma este " + cont.getSold());
    }
}
