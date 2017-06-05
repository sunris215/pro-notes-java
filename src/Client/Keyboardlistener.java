package Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

class Keyboardlistener implements KeyListener {
    Thread a;
    public Keyboardlistener()
    {
        addKedyListener(this);
    }

    private void addKedyListener(Keyboardlistener keyboardlistener) {
        System.out.println("Klawisz zostal wcisniety ");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Klawisz zostal wcisniety ");
    }

    public void keyPressed(KeyEvent e)
     {
         System.out.println("Klawisz zostal wcisniety ");
        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_LEFT :
                    break;
            case KeyEvent.VK_RIGHT :
                    break;
            case KeyEvent.VK_ESCAPE :
                break;
            case KeyEvent.VK_SPACE :
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
