import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientContacte {
    static final int PORT_NUMBER = 7654;
    public static void main(String[] args) throws Exception {
        try (var socket = new Socket("localhost", PORT_NUMBER);
             var out = new ObjectOutputStream(socket.getOutputStream());
             var in = new ObjectInputStream(socket.getInputStream());) {

            System.out.println("Am deschis conexiunea");

            out.writeObject(new Comanda("lista", null));
            var lista = (List<Contact>)in.readObject();
            lista.stream().forEach(System.out::println);

            out.writeObject(new Comanda("adauga", new Contact(3, "Test", "909090")));

            System.out.println("Lista dupa adaugare");

            out.writeObject(new Comanda("lista", null));
            lista = (List<Contact>)in.readObject();
            lista.stream().forEach(System.out::println);

             out.writeObject(new Comanda("iesire", null));
        }
    }
}
