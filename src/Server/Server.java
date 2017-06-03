package Server;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;


public class Server {

    private int serverPort = 2040;
    private ServerSocket s;
    private BufferedReader in;
    private PrintWriter out;
    int nrKomunikatu = 0;
    String userNotesOutput;

    Server() {
        try {
            s = new ServerSocket(serverPort);
            System.out.println("Server dziala");
        } catch (Exception e) {
            System.out.println("Nie można utworzyć gniazda Servera");
            System.exit(1);
        }
    }

    void dzialaj() throws Exception {
        Socket socket = s.accept();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        String komunikat;

        while ((komunikat = in.readLine()) != null) {
            switch (komunikat) {
                case "GetNotes":
                    try {
                        String userLoginGerNotes = in.readLine();

                        Scanner scanner = new Scanner(new File("notes/" + userLoginGerNotes + ".json"));
                        userNotesOutput = scanner.useDelimiter("\\A").next();
                        scanner.close();

                        out.println(userNotesOutput);
                    } catch (Exception error) {
                        System.out.println("ERROR SERVER READ DATA" + error); //todo change
                    }
                    break;
                case "SetNotes":
                    String userLoginGerNotes = in.readLine();
                    String dataToSave = in.readLine();

                    Scanner scannerSetNotes = new Scanner(new File("notes/" + userLoginGerNotes + ".json"));
                    userNotesOutput = scannerSetNotes.useDelimiter("\\A").next();
                    scannerSetNotes.close();

                    JSONObject jsonObjectSetData = new JSONObject(userNotesOutput);
                    JSONArray userArray = new JSONArray(dataToSave);
                    jsonObjectSetData.put("data", userArray);

                    //todo sprawdzic czy plik istnieje
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("notes/"+userLoginGerNotes+".json"));
                    bufferedWriter.write(jsonObjectSetData.toString());
                    bufferedWriter.close();
                    break;
                case "Register":
                    String login = in.readLine();
                    String name = in.readLine();
                    String surname = in.readLine();
                    String password = in.readLine();
                    JSONObject mainJsonObject = new JSONObject();

                    File userFile = new File("notes/" + login + ".json");
                    if (userFile.exists() && !userFile.isDirectory()) {
                        mainJsonObject.put("success", false);
                        mainJsonObject.put("message", "Login jest zajety");
                        out.println(mainJsonObject);
                    } else {
                        try {
                            JSONObject newJsonObject = new JSONObject();
                            newJsonObject.put("login", login);
                            newJsonObject.put("name", name);
                            newJsonObject.put("surname", surname);

                            byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
                            String str = new String(encodedBytes, StandardCharsets.UTF_8);

                            newJsonObject.put("password", str);
                            JSONArray jsonArray = new JSONArray();
                            newJsonObject.put("data", jsonArray);

                            BufferedWriter bufferedWriterNewUser = new BufferedWriter(new FileWriter("notes/" + login + ".json"));
                            bufferedWriterNewUser.write(newJsonObject.toString());
                            bufferedWriterNewUser.close();

                            mainJsonObject.put("success", true);
                            mainJsonObject.put("message", "Rejestracja przebiegla prawidlowo");
                            out.println(mainJsonObject);

                            System.out.println(mainJsonObject);

                        } catch (Exception e) {
                            //todo set
                        }


                    }
                    break;
                case "Login":
                    String loginLogin = in.readLine();

                    System.out.println("Login pierwszy parametr " + loginLogin);

                    String passwordLogin = in.readLine();
                    JSONObject loginJsonObject = new JSONObject();

                    System.out.println("haslo co przychodzi " + passwordLogin);

                    File userFileLogin = new File("notes/" + loginLogin + ".json");
                    if (userFileLogin.exists() && !userFileLogin.isDirectory()) {
                        Scanner scanner = new Scanner(new File("notes/" + loginLogin + ".json"));
                        String userData = scanner.useDelimiter("\\A").next();

                        JSONObject jsonObject = new JSONObject(userData);

//                        String loginFromJson = jsonObject.getString("login");
                        String passwordFromJson = jsonObject.getString("password");

                        byte[] passwordLoginHash = Base64.getEncoder().encode(passwordLogin.getBytes());
                        String passwordLoginHashString = new String(passwordLoginHash, StandardCharsets.UTF_8);


                        if (passwordFromJson.equals(passwordLoginHashString)) {
                            loginJsonObject.put("success", true);
                            loginJsonObject.put("message", "Logowanie udalo sie.");
                            out.println(loginJsonObject);
                        } else {
                            loginJsonObject.put("success", false);
                            loginJsonObject.put("message", "Logowanie nie udalo sie.");
                            out.println(loginJsonObject);
                        }

                    } else {
                        loginJsonObject.put("success", false);
                        loginJsonObject.put("message", "Nie ma takiego uzytkownika.");
                        out.println(loginJsonObject);
                    }

                    break;
                default:
                    break;
            }
        }
        System.out.println( "Koniec komunikacji z klientem”+ “- koncze prace");
        socket.close();
    }

    public static void main(String args[]) throws Exception {
        Server server = new Server();
        server.dzialaj();
    }
}