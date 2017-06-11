package Client;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
    private int serverPort = 2040;
    private InetAddress serverAddress;
    private Socket socket;
    private BufferedReader in;
    private BufferedReader klawiatura;
    private PrintWriter out;

    Connection() {
        try {
            serverAddress = InetAddress.getByName("localhost");
            socket = new Socket(serverAddress, serverPort);
            System.out.println("Klient dziala");

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error connection with server");
            System.exit(1);
        }
    }

    public String getData(String login) throws Exception {
        out.println("GetNotes");
        out.println(login);
        String getDataDataFromServer = in.readLine();
        return getDataDataFromServer;
    }

    public void setData(String dataToSend, String login) {
        out.println("SetNotes");
        out.println(login);
        out.println(dataToSend);
    }

    public String register(String login, String name, String surname, String password) {
        out.println("Register");
        out.println(login);
        out.println(name);
        out.println(surname);
        out.println(password);
        try {
            String komnikat = in.readLine();
            return komnikat;
        } catch (Exception e) {
            System.out.println("Blad rejestracji " + e);
        }
        return "";
    }

    public String login(String login, String password) {
        out.println("Login");
        out.println(login);
        out.println(password);
        try {
            String loginOutput = in.readLine();
            return loginOutput;
        } catch (Exception e) {
            System.out.println("Login erros " + e);
        }
        return "";
    }

}
