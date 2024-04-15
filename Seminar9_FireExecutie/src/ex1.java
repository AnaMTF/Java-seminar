import java.util.ArrayList;
//Problema 1:
//Să se construiască o aplicație în care:
//
//    programul principal pornește 10 fire de execuție noi în paralel
//    fiecare fir de execuție:
//        afișează în ordine numerele de la 1 la 100 și identificatorul firului de execuție
//        așteaptă 1-100 milisecunde între afișări
//    programul principal afișează identificatorul firului de execuție care a câștigat (a ajuns primul la 100)
//    când un fir de execuție câștigă celelalte fire de execuție trebuie să se oprească și să afișeze numărul la care s-au oprit

class FirExecutie extends Thread{
    static volatile long idFirCastigator = 0; //ca sa stearga la final din memorie
    int i;
    @Override
    public void run() {
        for ( i = 1; i <= 100; i++) {
            try {
                Thread.sleep((long) (1+ Math.random()*100));
            } catch (InterruptedException e) {

            }

            if (idFirCastigator > 0){
                System.out.printf("T#%-3d - Am pierdut (am ajuns la %3d)%n" , getId(), i);
                return;
            }
            System.out.printf("T#%-3d - %3d%n" , getId(), i);
        }
        idFirCastigator = getId();
    }
}

public class ex1 {
    public static void main(String[] args) throws InterruptedException {
        var fire = new ArrayList<FirExecutie>(); //le salvam pe toate ca sa putem face join la final pe toate
        for (int i = 0; i< 10;i++){ //facem 10 fire de executie (obiecte)
            var fir = new FirExecutie();
            //fir.run(); // nu se face fir de executie, se foloseste main
            fir.start();//porneste un nou fir de executie unde se apeleaza automat run
            fire.add(fir);
        }
        for (var fir : fire){
            fir.join();
        }

        System.out.printf("GATA si id fir castigator este %-3d",FirExecutie.idFirCastigator);

    }
}
