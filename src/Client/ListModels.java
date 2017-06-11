package Client;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLType;
import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.CENTER;

class UserInterface extends JFrame implements  KeyListener{
    private DefaultListModel model;
    private JScrollPane scrollpane;
    private JList list;
    private JButton refreshbtn;
    private JButton remallbtn;
    private JButton addbtn;
    private JButton renbtn;
    private JButton delbtn;
    private JButton savebtn;
    private Connection connection;
    private String userName, userSurname, login, password;

    JTextArea lista = new JTextArea();



    public UserInterface() {
        connection = new Connection();
        login("", "");


    }

    private void getData() {
        model = new DefaultListModel();
        model.clear();
        try {
            String outputData = connection.getData(login);
            JSONObject jsonObject = new JSONObject(outputData);

            userName = jsonObject.getString("name");
            userSurname = jsonObject.getString("surname");
            JSONArray notesArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < notesArray.length(); ++i) {
                JSONObject rec = notesArray.getJSONObject(i);
                model.addElement(rec.getString("note"));
            }
        } catch (Exception e) {
            System.out.println("Error initialize connection");
            System.out.println(e);
        }

        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    Object item = model.getElementAt(index);
                    String text = JOptionPane.showInputDialog("Edytuj", item);
                    String newitem = null;
                    if (text != null) {
                        newitem = text.trim();
                    } else {
                        return;
                    }
                    if (!newitem.isEmpty()) {
                        model.remove(index);
                        model.add(index, newitem);
                        ListSelectionModel selmodel = list.getSelectionModel();
                        selmodel.setLeadSelectionIndex(index);
                    }
                }
            }
        });
    }

    private void refreshData() {
        try {
            String outputDataRefresh = connection.getData(login);
            JSONObject jsonObject = new JSONObject(outputDataRefresh);
            model.clear();
            userName = jsonObject.getString("name");
            userSurname = jsonObject.getString("surname");
            JSONArray notesArray = jsonObject.getJSONArray("data");

            System.out.println("Outup refresh");
            System.out.println(notesArray.toString());
            for (int i = 0; i < notesArray.length(); ++i) {
                JSONObject rec = notesArray.getJSONObject(i);
                model.addElement(rec.getString("note"));
            }
        } catch (Exception e) {
            System.out.println("Error initialize connection");
            System.out.println(e);
        }
    }

    private void setData() {
        try {
            JSONObject mainJsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < model.toArray().length; i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("note", model.toArray()[i]);
                jsonArray.put(jsonObject);
            }
            mainJsonObject.put("data", jsonArray);
            connection.setData(jsonArray.toString(), login);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private void createList() {
        this.getData();
    }

    private void createButtons() {
        remallbtn = new JButton("Wyczysc");
        addbtn = new JButton("Dodaj");
        renbtn = new JButton("Edytuj");
        delbtn = new JButton("Usun");
        savebtn = new JButton("Zapisz");
        refreshbtn = new JButton("Odswiez");



        addbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String text = JOptionPane.showInputDialog("Dodaj nowa notatke");
                String item = null;

                if (text != null) {
                    item = text.trim();
                } else {
                    return;
                }
                if (!item.isEmpty()) {
                    model.addElement(item);
                }
            }
        });

        delbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                ListSelectionModel selmodel = list.getSelectionModel();
                int index = selmodel.getMinSelectionIndex();
                if (index >= 0) {
                    model.remove(index);
                }
            }
        });

        renbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListSelectionModel selmodel = list.getSelectionModel();
                int index = selmodel.getMinSelectionIndex();
                if (index == -1) {
                    return;
                }

                Object item = model.getElementAt(index);
                String text = JOptionPane.showInputDialog("Edytuj", item);
                String newitem = null;

                if (text != null) {
                    newitem = text.trim();
                } else {
                    return;
                }

                if (!newitem.isEmpty()) {
                    model.remove(index);
                    model.add(index, newitem);
                }
            }
        });

        remallbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
            }
        });

        savebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setData();
            }
        });

        refreshbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Odswierz action");
                refreshData();
            }
        });
    }

    private void login(String chossedActionLoginOrRegister, String message) {
        if (chossedActionLoginOrRegister == "") {
            JFrame frameOptions = new JFrame("Input Dialog Example 3");
            String[] options = {"Logowanie", "Rejestracja"};
            chossedActionLoginOrRegister = (String) JOptionPane.showInputDialog(frameOptions,
                    "Witami w aplikacji pro notes",
                    "Co chcesz zrobic?",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }

        switch (chossedActionLoginOrRegister) {
            case "Rejestracja":
                RegisterWindow registerWindow = new RegisterWindow(message);
                if (
                        registerWindow.resultNumber == JOptionPane.OK_OPTION
                                && registerWindow.name.length() > 0
                                && registerWindow.surname.length() > 0
                                && registerWindow.password.length() > 0
                                && registerWindow.password1.length() > 0
                        ) {
                    if (!registerWindow.password.equals(registerWindow.password1)) {
                        login("Rejestracja", "Hasla nie sa takie same");
                        return;
                    } else {
                        String connectionOutput = connection.register(registerWindow.login, registerWindow.name, registerWindow.surname, registerWindow.password1);
                        try {
                            System.out.println("try");
                            JSONObject jsonObject = new JSONObject(connectionOutput);
                            boolean success = jsonObject.getBoolean("success");
                            String messageFromServer = jsonObject.getString("message");
                            if (success) {
                                login("Login", "Rejestracja przebiegla prawidlowo, mozesz sie teraz zalogowac.");
                            } else {
                                login("Rejestracja", messageFromServer);
                            }
                        } catch (Exception e) {
                            System.out.println("Blad rejestracji");
                        }
                        return;
                    }
                } else {
                    login("Rejestracja", "Uzupelnij wszystkie pola");
                    return;
                }
            default:
                String[] options = new String[]{"OK", "Cancel"};

                JPanel panelLogon = new JPanel();
                JLabel labelLogin = new JLabel("Podaj login:");
                JTextField loginField = new JTextField(10);

                if (message != null) {
                    JLabel labelMessageLogin = new JLabel(message);
                    panelLogon.add(labelMessageLogin);
                }
                panelLogon.add(labelLogin);
                panelLogon.add(loginField);

                int optionLogin = JOptionPane.showOptionDialog(null, panelLogon, "Node pro - login",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if(optionLogin == 0) {
                    login = loginField.getText();
                    if (login != null) {
                        JPanel panel = new JPanel();
                        JLabel label = new JLabel("Podaj haslo:");
                        JPasswordField pass = new JPasswordField(10);
                        panel.add(label);
                        panel.add(pass);
                        int option = JOptionPane.showOptionDialog(null, panel, "Node pro - login",
                                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, options, options[0]);
                        if (option == 0) {
                            char[] password = pass.getPassword();

                            String outputLogin = connection.login(login, String.valueOf(password));
                            try {
                                JSONObject jsonObjectLogin = new JSONObject(outputLogin);
                                boolean successLogin = jsonObjectLogin.getBoolean("success");
                                String messageFromServerLogin = jsonObjectLogin.getString("message");

                                if (successLogin) {
                                    init();
                                } else {
                                    login = null;
                                    password = null;
                                    login("Login", messageFromServerLogin);
                                }
                            } catch (Exception e) {
                                System.out.println("Login error swith case " + e);
                            }
                            return;
                        } else {
                            login("Login", "");
                        }
                    } else {
                        return;
                    }
                } else {
                    login("Login", "");
                }
                break;
        }
    }

    private void init() {
        createList();
        createButtons();
        scrollpane = new JScrollPane(list);

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(scrollpane)
                .addGroup(gl.createParallelGroup()
                        .addComponent(refreshbtn)
                        .addComponent(addbtn)
                        .addComponent(renbtn)
                        .addComponent(delbtn)
                        .addComponent(remallbtn)
                        .addComponent(savebtn))
        );

        gl.setVerticalGroup(gl.createParallelGroup(CENTER)
                .addComponent(scrollpane)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(refreshbtn)
                        .addComponent(addbtn)
                        .addComponent(renbtn)
                        .addComponent(delbtn)
                        .addComponent(remallbtn)
                        .addComponent(savebtn))
        );

        gl.linkSize(refreshbtn, addbtn, renbtn, delbtn, remallbtn, savebtn);

        pack();

        setTitle("Pro notes " + userName + " " + userSurname);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Klawisz zostal wcisniety ");
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_F4:
                setData();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}


public class ListModels {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.setVisible(true);
        ui.addKeyListener(ui);

    }
}