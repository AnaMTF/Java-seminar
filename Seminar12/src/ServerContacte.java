import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerContacte {
    static final int PORT_NUMBER = 7654;

    private static List<Contact> contacte = new ArrayList<>(
            Arrays.asList(new Contact[]{
            new Contact(1,"Ion", "32345"),
            new Contact(2,"Maria", "123123"),
    }));

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try(var serverSocket = new ServerSocket(PORT_NUMBER);) {
            while (true) {
                System.out.println("Asteptam o conexiune");
                var socket = serverSocket.accept();
                procesareConexiune(socket);
            }
        }
    }

    static void procesareConexiune(Socket paramSocket){
        try (var socket = paramSocket;
        var in = new ObjectInputStream(socket.getInputStream());
            var out = new ObjectOutputStream(socket.getOutputStream());) {

            System.out.println("Am deschis conexiunea");
            while(true) {
                var comanda = (Comanda) in.readObject();
                System.out.println("Am primit comanda " + comanda);
                if(comanda.getDenumire().equals("lista")){
                    out.writeObject(new ArrayList<>(contacte));
                } else if (comanda.getDenumire().equals("adauga")){
                    var contact = (Contact)comanda.getParametru();
                    contacte.add(contact);
                }else {
                    break;
                }

                System.out.println("Am trimis raspunsul");
            }
            System.out.println("Am inchis conexiunea");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

