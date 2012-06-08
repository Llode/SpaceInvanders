/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.*;
import java.awt.*;
import javax.swing.JPanel;

/**
 * Pelin sisäinen grafiikka.
 *
 * @author Larppa
 */
public class Kayttoliittyma extends JPanel implements Asetukset, Runnable {

    private String PeliLoppui;
    private final String ammus = "ammus.png";
    private final String pelaajakuva = "pelaaja.png";
    private final String UfoKuva = "ufo.png";
    private final String rajahdys = "rajahdys.png";
    private Dimension d;
    private Pelimoottori moottori = new Pelimoottori();
    private Thread animator;
    public Kuti kuti;
    public Pelaaja pelaaja;
    public Ufo ufo;
    public TAdapter TAdapter;
    public UfoKuti ufokuti;
    Grafiikka grafiikka;

    /**
     * Luo pelikentän.
     */
    public Kayttoliittyma(Pelimoottori pelimoottori) {

        addKeyListener(new TAdapter());
        setFocusable(true);
        requestFocus();
        d = new Dimension(RuudunLeveys, RuudunKorkeus);
        setBackground(Color.black);
        Kaynnistys(pelimoottori);
        setDoubleBuffered(true);
    }

    /**
     * Käynnistää animaattorin (ja toivottavasti myös pelin).
     *
     * @param pelimoottori
     */
    private void Kaynnistys(Pelimoottori pelimoottori) {
        moottori.GameInit();
        if (animator == null || !pelimoottori.ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        moottori.GameInit();
    }

    /**
     * Piirtää taustan sekä objektit kentälle.
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.green);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.red);
        grafiikka.piirraTavaratKentalle(g);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * Piirtää game over -ruudun.
     */
    public void peliLoppuu() {
        if (!moottori.ingame) {
            PeliLoppui = moottori.Loppusanat;
            Graphics g = this.getGraphics();

            System.out.println("lol");
            g.setColor(Color.white);
            g.fillRect(0, 0, RuudunLeveys, RuudunKorkeus);

            g.setColor(new Color(0, 32, 48));
            g.fillRect(50, RuudunLeveys / 2 - 30, RuudunLeveys - 100, 50);
            g.setColor(Color.white);
            g.drawRect(50, RuudunLeveys / 2 - 30, RuudunLeveys - 100, 50);

            Font small = new Font("Comic sans", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(PeliLoppui, (RuudunLeveys - metr.stringWidth(PeliLoppui)) / 2, RuudunLeveys / 2);
        }
    }

    @Override
    public void run() {

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
        peliLoppuu();
    }
}
