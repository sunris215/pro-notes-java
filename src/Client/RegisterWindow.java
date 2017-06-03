package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mariuszborkowski on 27.05.2017.
 */
public class RegisterWindow {
    public int resultNumber;
    public String name;
    public String login;
    public String surname;
    public String password;
    public String password1;


    public RegisterWindow(String message) {
        JTextField nameField = new JTextField(5);
        JTextField loginField = new JTextField(5);
        JTextField surnameField = new JTextField(5);
//        JTextField passwordField = new JTextField(5);
//        JTextField passwor2dField = new JTextField(5);
        JPasswordField passwordField = new JPasswordField(10);
        JPasswordField passwor2dField = new JPasswordField(10);

        JLabel label = new JLabel(message);

//        label.setForeground(new Color(255, 255, 0));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 11));
        label.setBounds(0, 0, 250, 32);


        JPanel myPanel = new JPanel();
        myPanel.setSize(300, 300);
        myPanel.setLayout(new GridLayout(12, 1));

        if (message.length() > 0){
            myPanel.add(label);
        }
        myPanel.add(new JLabel("Login:"));
        myPanel.add(loginField);
        myPanel.add(new JLabel("Imie:"));
        myPanel.add(nameField);
//        myPanel.add(Box.createHorizontalStrut(15)); //
        myPanel.add(new JLabel("Nazwisko:"));
        myPanel.add(surnameField);
        myPanel.add(new JLabel("Haslo:"));
        myPanel.add(passwordField);
        myPanel.add(new JLabel("Powtorz haslo:"));
        myPanel.add(passwor2dField);




        resultNumber = JOptionPane.showConfirmDialog(null, myPanel,
                "Rejestracja", JOptionPane.OK_CANCEL_OPTION);
        name = nameField.getText().trim();
        login = loginField.getText().trim();
        surname = surnameField.getText().trim();
        password = String.valueOf(passwordField.getPassword());
        password1 = String.valueOf(passwor2dField.getPassword());


        System.out.println("1 "+ resultNumber);
        System.out.println("2 "+ name);
        System.out.println("3 "+ surname);
        System.out.println("4 "+ password);
        System.out.println("5 "+ password1);
    }

}
