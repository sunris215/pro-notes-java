////package Client;
////import javax.swing.*;
////import java.awt.*;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import java.io.*;
////
////
////class Window extends JFrame implements ActionListener {
////    private JTextArea mainTextArea;
////    private JTextArea mainTextArea1;
////    private JTextArea mainTextArea2;
////    private JTextArea mainTextArea3;
////    private JTextArea mainTextArea4;
////    private JTextArea mainTextArea5;
////    private JTextArea mainTextArea6;
////    private JTextField userPathField;
////    private JButton readButton;
//////    private JButton saveButton;
////    private JRadioButton binaryButton;
////    private JRadioButton tesxtsButton;
////
////    public Window() {
////        super("Note pro");
////    }
////
////    public void init() {
////        setSize(500, 400);
////        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
////        setLayout(new FlowLayout());
////
////        mainTextArea = new JTextArea(10, 40);
////        mainTextArea1 = new JTextArea(10, 40);
////        mainTextArea2 = new JTextArea(10, 40);
////        mainTextArea3 = new JTextArea(10, 40);
////        mainTextArea4 = new JTextArea(10, 40);
////        mainTextArea5 = new JTextArea(10, 40);
////        userPathField = new JTextField(13);
////        readButton = new JButton("Zapisz");
//////        saveButton = new JButton("Czytaj");
////        binaryButton = new JRadioButton("To do");
////        tesxtsButton = new JRadioButton("Done");
////
////        ButtonGroup group = new ButtonGroup();
////        group.add(binaryButton);
////        group.add(tesxtsButton);
////
////        JScrollPane trescScroll = new JScrollPane(mainTextArea);
////        mainTextArea.setText("notatka 1");
////
////        JScrollPane trescScroll1 = new JScrollPane(mainTextArea1);
////        mainTextArea1.setText("notatka 2");
////
////        JScrollPane trescScroll2 = new JScrollPane(mainTextArea2);
////        mainTextArea2.setText("notatka 3");
////
////        JScrollPane trescScroll3 = new JScrollPane(mainTextArea3);
////        mainTextArea3.setText("notatka 1");
////
////        JScrollPane trescScroll4 = new JScrollPane(mainTextArea4);
////        mainTextArea4.setText("notatka 1");
////        JScrollPane trescScroll5 = new JScrollPane(mainTextArea5);
////        mainTextArea5.setText("notatka ostatnia");
////
////
////
////        JScrollPane trescScrollAll = new JScrollPane(mainTextArea);
////        trescScroll.add(trescScroll1);
////        trescScroll.add(trescScroll2);
////        trescScroll.add(trescScroll3);
////        trescScroll.add(trescScroll4);
////        trescScroll.add(trescScroll5);
////
////
////
////
////
////        add(userPathField);
////        add(readButton);
////        add(binaryButton);
////        add(tesxtsButton);
////
////        add(trescScrollAll);
////
////
////
////        readButton.addActionListener(this);
////
////        setVisible(true);
////    }
////
////    @Override
////    public void actionPerformed(ActionEvent e) {
////        Object obiektKlikniety = e.getSource();
////        String usersPatch = userPathField.getText();
////
////        mainTextArea.setText("");
////
////        if (obiektKlikniety == readButton) {
////            if (binaryButton.isSelected()) {
////                try {
////                    DataInputStream in = new DataInputStream(
////                            new FileInputStream("daneZadanie3_6.txt"));
////
////                    int znak;
////                    while ((znak = in.read()) != -1)
////                        mainTextArea.append(String.valueOf( znak));
////                    mainTextArea.append(" ");
////                    in.close();
////                } catch (Exception blad) {
////                    System.out.println(blad);
////                }
////            } else if (tesxtsButton.isSelected()) {
////                File file = new File(usersPatch);
////                if (file.exists() && !file.isDirectory()) {
////                    try {
////                        BufferedReader in = new BufferedReader(
////                                new InputStreamReader(
////                                        new FileInputStream(usersPatch)));
////
////                        String line;
////                        while (true) {
////                            line = in.readLine();
////                            if (line != null) {
////                                mainTextArea.append(line + "\n");
////                            } else {
////                                break;
////                            }
////                        }
////                    } catch (Exception error) {
////                        System.out.println(error);
////                    }
////                } else {
////                    mainTextArea.setText("Brak pliku lub folderu o nazwie " + usersPatch);
////                }
////            }
////        }
////
////    }
////}
////
////public class Base {
////    public static void main(String[] args) {
////        Window p = new Window ();
////        p.init();
////    }
////}
//
//
//package Client;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import static javax.swing.GroupLayout.Alignment.CENTER;
//
//public class ListModels_copy extends JFrame {
//
//    private DefaultListModel model;
//    private JList list;
//    private JButton remallbtn;
//    private JButton addbtn;
//    private JButton renbtn;
//    private JButton delbtn;
//
//    public ListModels_copy() {
//        initUI();
//    }
//
//    private void createList() {
//        model = new DefaultListModel();
//        model.addElement("Notatka");
//        model.addElement("Zakupy");
//        model.addElement("Lista to do");
//        model.addElement("Dochody");
//        model.addElement("Moj lista");
//
//        list = new JList(model);
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        list.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                if (e.getClickCount() == 2) {
//                    int index = list.locationToIndex(e.getPoint());
//                    Object item = model.getElementAt(index);
//                    String text = JOptionPane.showInputDialog("Rename item", item);
//                    String newitem = null;
//                    if (text != null) {
//                        newitem = text.trim();
//                    } else {
//                        return;
//                    }
//
//                    if (!newitem.isEmpty()) {
//                        model.remove(index);
//                        model.add(index, newitem);
//                        ListSelectionModel selmodel = list.getSelectionModel();
//                        selmodel.setLeadSelectionIndex(index);
//                    }
//                }
//            }
//        });
//    }
//
//    private void createButtons() {
//
//        remallbtn = new JButton("Remove All");
//        addbtn = new JButton("Add");
//        renbtn = new JButton("Rename");
//        delbtn = new JButton("Delete");
//
//        addbtn.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                String text = JOptionPane.showInputDialog("Add a new item");
//                String item = null;
//
//                if (text != null) {
//                    item = text.trim();
//                } else {
//                    return;
//                }
//
//                if (!item.isEmpty()) {
//                    model.addElement(item);
//                }
//            }
//        });
//
//        delbtn.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent event) {
//
//                ListSelectionModel selmodel = list.getSelectionModel();
//                int index = selmodel.getMinSelectionIndex();
//                if (index >= 0) {
//                    model.remove(index);
//                }
//            }
//
//        });
//
//        renbtn.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                ListSelectionModel selmodel = list.getSelectionModel();
//                int index = selmodel.getMinSelectionIndex();
//                if (index == -1) {
//                    return;
//                }
//
//                Object item = model.getElementAt(index);
//                String text = JOptionPane.showInputDialog("Rename item", item);
//                String newitem = null;
//
//                if (text != null) {
//                    newitem = text.trim();
//                } else {
//                    return;
//                }
//
//                if (!newitem.isEmpty()) {
//                    model.remove(index);
//                    model.add(index, newitem);
//                }
//            }
//        });
//
//        remallbtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                model.clear();
//            }
//        });
//    }
//
//    private void initUI() {
//
//        //Tworze liste
//        createList();
//        createButtons();
//        JScrollPane scrollpane = new JScrollPane(list);
//
//        //
//        Container pane = getContentPane();
//        GroupLayout gl = new GroupLayout(pane);
//        pane.setLayout(gl);
//
//        gl.setAutoCreateContainerGaps(true);
//        gl.setAutoCreateGaps(true);
//
//        gl.setHorizontalGroup(gl.createSequentialGroup()
//                .addComponent(scrollpane)
//                .addGroup(gl.createParallelGroup()
//                        .addComponent(addbtn)
//                        .addComponent(renbtn)
//                        .addComponent(delbtn)
//                        .addComponent(remallbtn))
//        );
//
//        gl.setVerticalGroup(gl.createParallelGroup(CENTER)
//                .addComponent(scrollpane)
//                .addGroup(gl.createSequentialGroup()
//                        .addComponent(addbtn)
//                        .addComponent(renbtn)
//                        .addComponent(delbtn)
//                        .addComponent(remallbtn))
//        );
//
//        gl.linkSize(addbtn, renbtn, delbtn, remallbtn);
//
//        pack();
//
//        setTitle("Pro notes");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                ListModels_copy ex = new ListModels_copy();
//                ex.setVisible(true);
//            }
//        });
//    }
//}