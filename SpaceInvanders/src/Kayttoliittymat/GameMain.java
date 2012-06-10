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
 * Käyttöliittymä ja pelin käynnistävät toiminnot.
 *
 * @author Larppa
 */
public class GameMain extends JFrame implements Asetukset {

    private Pelimoottori moottori;
    private GameCanvas canvas;

    public GameMain() {
        moottori = new Pelimoottori();
        moottori.GameInit();
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(RuudunLeveys, RuudunKorkeus));
        this.setContentPane(canvas);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("SPACE INVANDELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gameStart();
    }

    /**
     * Käynnistää pelin.
     */
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

    /**
     * Pelin logiikan suoritusjärjestys.
     */
    private void gameLoop() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (moottori.ingame) {
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
        repaint();
    }
}

/**
 * Pelialueen luonti.
 *
 * @author Larppa
 */
class GameCanvas extends JPanel implements Asetukset, KeyListener {

    private Pelimoottori moottori;
    private Grafiikka grafiikka;
    private String PeliLoppui;

    public GameCanvas() {

        setFocusable(true);
        requestFocus();
        addKeyListener(new TAdapter());
        moottori = new Pelimoottori();
        grafiikka = new Grafiikka(moottori);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        setBackground(Color.red);
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

    /**
     * Piirtää pelin tapahtumat kentälle.
     *
     * @param g2d
     */
    private void gameDraw(Graphics2D g2d) {

        grafiikka.piirraPelaaja(g2d);
        System.out.println("pelaaja");
        grafiikka.piirraUfo(g2d);
        System.out.println("ufo");
        grafiikka.piirraKuti(g2d);
        System.out.println("kuti");
        grafiikka.piirraUfoKuti(g2d);
        System.out.println("ufokuti");
        g2d.drawLine(0, UfojenMaaliViiva, RuudunLeveys, UfojenMaaliViiva);
        
        peliLoppuu(g2d);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /**
     * Piirtää game over -ruudun.
     */
    public void peliLoppuu(Graphics g) {

        if (!moottori.ingame) {
            System.out.println("GAME OVER");
            PeliLoppui = moottori.Loppusanat;
            g = this.getGraphics();

            System.out.println("lol");
            g.setColor(Color.green);
            g.fillRect(0, 0, RuudunLeveys, RuudunKorkeus);

            g.setColor(new Color(0, 32, 48));
            g.fillRect(50, RuudunLeveys / 2 - 30, RuudunLeveys - 100, 50);
            g.setColor(Color.green);
            g.drawRect(50, RuudunLeveys / 2 - 30, RuudunLeveys - 100, 50);

            Font small = new Font("Comic sans", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(PeliLoppui, (RuudunLeveys - metr.stringWidth(PeliLoppui)) / 2, RuudunLeveys / 2);
        }
    }
}
