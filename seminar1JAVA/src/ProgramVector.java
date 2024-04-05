public class ProgramVector {
    //ca sa fie apelata in main o fct trebuie sa fie statica
    private static void afisare(int[] vector, String mesaj){
        System.out.printf("%s (%d elemente: ", mesaj, vector.length);
        for (int valoare : vector){
            System.out.printf(valoare + " ");
        }
        System.out.println();
    }

    static int[] inserare( int valoare, int[] vector) {
        //!!!!!!TEMA!!!!!!
        // o functie de inserare, cream vector nou de dimensiune +1, pus elementul care trebuie inserat
        int lungime = vector.length;
        int[] vectorNou = new int[lungime + 1];
        for (int i = 0; i < lungime; i++) {
            vectorNou[i] = vector[i];
        }
        vectorNou[vector.length] = valoare;
        
        return vectorNou;
    }

    static void incrementare(int[] vector){
        for (int i = 0; i < vector.length; i++){
            int valoare = vector[i];
            vector[i] = valoare + 1;
        }

    }

    public static void main(String[] args) { //click dr modify si adaugam parametrii separati prin spatiu
        System.out.printf("Numar parametrii: %d%n", args.length);
        for (String valoareArgument : args) {
            System.out.println(valoareArgument);
        }

        var valori = args[0].split(",");
        int[] v = new int[valori.length];
        for (int i = 0; i < valori.length; i++) {
            v[i] = Integer.parseInt(valori[i]);

        }

        afisare(v, "Initial");
        incrementare(v);
        afisare(v, "Dupa incrementare");
        v = inserare(13, v);
        afisare(v, "Dupa inserare");

    }
}



