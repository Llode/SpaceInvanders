/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.Asetukset;
import Pelimoottori.Pelimoottori;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Larppa
 */
public class GameMain extends JFrame implements Asetukset {

    private Pelimoottori moottori;
    private GameCanvas canvas;
    private Grafiikka grafiikka;

    public GameMain() {
        moottori = new Pelimoottori();
        moottori.GameInit();
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(RuudunLeveys, RuudunKorkeus));
        this.setContentPane(canvas);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("SPACE INVADELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public void gameStart() {
        Thread gameThread = new Thread() {

            @Override
            public void run() {
                //loop
                gameLoop();
            }
        };
        gameThread.start();
    }

    private void gameLoop() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            moottori.toiminta();
            repaint();


            timeDiff = (System.currentTimeMillis() - beforeTime);
            sleep = Delay - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
//        grafiikka.peliLoppuu();
    }
//    public void gameDraw(Graphics2D g2d) {
//        if (moottori.ingame) {
//            grafiikka.piirraPelaaja(g2d);
//            System.out.println("pelaaja");
//            grafiikka.piirraUfo(g2d);
//            System.out.println("ufo");
//            grafiikka.piirraKuti(g2d);
//            System.out.println("kuti");
//            grafiikka.piirraUfoKuti(g2d);
//            System.out.println("ufokuti");
//            g2d.drawLine(0, 100, RuudunLeveys, 150);
//        } else {
//            grafiikka.peliLoppuu();
//        };
//    }
}

class GameCanvas extends JPanel implements Asetukset, KeyListener {


    private Pelimoottori moottori;
    private Grafiikka grafiikka;

    public GameCanvas() {

        setFocusable(true);
        requestFocus();
        addKeyListener(new TAdapter());
        grafiikka = new Grafiikka();
        moottori = new Pelimoottori();

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        setBackground(Color.black);
        gameDraw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyReleased(e);
    }

    private void gameDraw(Graphics2D g2d) {
        if (moottori.ingame) {
            grafiikka.piirraPelaaja(g2d);
            System.out.println("pelaaja");
            grafiikka.piirraUfo(g2d);
            System.out.println("ufo");
            grafiikka.piirraKuti(g2d);
            System.out.println("kuti");
            grafiikka.piirraUfoKuti(g2d);
            System.out.println("ufokuti");
            g2d.drawLine(0, UfojenMaaliViiva, RuudunLeveys, UfojenMaaliViiva);
            Toolkit.getDefaultToolkit().sync();
            g2d.dispose();
        } else {
            grafiikka.peliLoppuu();
        };
    }
}
