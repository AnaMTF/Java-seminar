import java.util.ArrayList;
//Problema 2:
//Fie o clasă Cont cu un câmp sold de tip întreg și două metode: depune(suma) și getSold.
//Să se scrie un program care lansează 10 de fire de execuție. Fiecare fir de execuție va depune câte 1 leu de 1000 de ori. Firele de execuție trebuie să ruleze în paralel și să folosească același obiect cont.
//Să se afișeze soldul final din cont.
class Cont2 {
        private int sold = 0;
        private final Object mirel = new Object();
        public void depunde(int suma){
            synchronized (mirel) { //garantam ca modificarile astea sunt atomice
                sold = sold + suma;
            }
        }

        public int getSold() {
            return sold;
        }
    }

public class ex2 {
    public static void main(String[] args) throws InterruptedException {
        var cont2 = new Cont2();
        var fire = new ArrayList<Thread>(); //le salvam pe toate ca sa putem face join la final pe toate
        for (int i = 0; i< 10;i++){ //facem 10 fire de executie (obiecte)
            var fir = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                cont2.depunde(1);
            }
            });
            //fir.run(); // nu se face fir de executie, se foloseste main


            fir.start();//porneste un nou fir de executie unde se apeleaza automat run
            fire.add(fir);
        }
        for (var fir : fire){
            fir.join();
        }

        System.out.println("Soldul final este  "+ cont2.getSold());

    }
}
