import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Seminar05_ProducatorConsumatorBlockingQueue {

    static final int CAPACITATE = 5;
    static final BlockingQueue<Integer> coada = new ArrayBlockingQueue(CAPACITATE);

    static void producator() {
        while(true) {
            var numar = (int)(Math.random() * 100);
            try {
                System.out.println("Producator: Adaugam " + numar);
                coada.put(numar);
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("Producator: GATA!");
                break;
            }
        }
    }

    static void consumator() {
        while(true) {
            try {
                var numar = coada.take();
                System.out.println("Am procesat numarul " + numar + "; coada rămasă este: " + coada);
                Thread.sleep((int)(Math.random() * 3000));
            } catch (InterruptedException e) {
                System.out.println("Consumator: GATA!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        var producator1 = new Thread(
                Seminar05_ProducatorConsumatorBlockingQueue::producator);
        producator1.start();

        var consumator1 = new Thread(
                Seminar05_ProducatorConsumatorBlockingQueue::consumator);
        consumator1.start();

        var consumator2 = new Thread(
                Seminar05_ProducatorConsumatorBlockingQueue::consumator);
        consumator2.start();

        new Scanner(System.in).nextLine();
        consumator1.interrupt();
        producator1.interrupt();
        consumator2.interrupt();
    }
}
