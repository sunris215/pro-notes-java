package Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

class Keyboardlistener implements KeyListener {
    ListModels okno;
//    WatekOknaGry gra;
    Thread a;
    public Keyboardlistener()
    {
        System.out.println("Konstruktor");
//        this.okno = okno;
//        this.gra = gra;

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
//                    if(gra.gracz.hitBox.x >645) gra.gracz.hitBox.x-=5;
//                    if (gra.isReady)gra.getPlayer().przesun(-1);
                    break;
            case KeyEvent.VK_RIGHT :
//                    if(gra.gracz.hitBox.x < 0) gra.gracz.hitBox.x+=5;
//                    if (gra.isReady)gra.gracz.przesun(1);
                    break;
            case KeyEvent.VK_ESCAPE :
//                System.exit(0);
//                okno.out.println("KONIEC");
                break;
            case KeyEvent.VK_SPACE :
//                if (!gra.wygrana || !gra.przegrana){
//                    gra.isReady = true;
//                    a = new Thread(gra);
//                    new Thread(a).start();
//                    break;
//                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
