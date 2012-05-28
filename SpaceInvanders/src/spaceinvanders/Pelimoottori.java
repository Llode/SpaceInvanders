/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Pelimoottori.UfoKuti;
import Pelimoottori.Kuti;
import Pelimoottori.Pelaaja;
import Pelimoottori.Asetukset;
import Pelimoottori.Ufo;
import Kayttoliittymat.Grafiikka;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pelimoottori extends JPanel implements Asetukset, Runnable {

    //private Dimension d;
    private Pelaaja pelaaja;
    private Kuti kuti;
    private ArrayList ufot;
    private int ufoX = 150;
    private int ufoY = 5;
    private int suunta = -1;
    private int tuhotut = 0;
    private boolean ingame = true;
<<<<<<< HEAD
    private final String UfoKuva = "/res/ufo.png";

    private Thread animator;

    Grafiikka grafiikka;
    Graphics g;
=======
    private final String UfoKuva = "res/ufo.png";
    private String peliLoppui = "Game Over";
    private Thread animator;

    public void addNotify() {
        super.addNotify();
        SetUp();
    }
>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9

    /**
     * ASettaa ufot riveihin, luo pelaajan, asettaa spritet objekteille.
     */
    public void SetUp() {
        ufot = new ArrayList();
<<<<<<< HEAD
//        ImageIcon ii = new ImageIcon(this.getClass().getResource(UfoKuva));
        
=======
        ImageIcon ii = new ImageIcon(this.getClass().getResource(UfoKuva));

>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Ufo ufo = new Ufo(ufoX + 18 * j, ufoY + 18 * j);
//                ufo.setImage(ii.getImage());
                grafiikka.asetaKuvaUfolle();
                ufot.add(ufo);
            }
        }

        pelaaja = new Pelaaja();
<<<<<<< HEAD
        grafiikka.asetaKuvaPelaajalle();
        kuti = new Kuti();
        grafiikka.asetaKuvaAmmukselle();
        
=======
        pelaaja.asetaKuva();
        kuti = new Kuti();

>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    /**
     * Piirtää ufot kentälle. Jos ufo on näkyvissä, se saa kuvan. Jos ufo on
     * kuollut, se katoaa kentältä.
     *
     * @param g grafiikkamoottorin parametri
     */
    public void ufotKentalle(Graphics g) {
        Iterator it = ufot.iterator();

        while (it.hasNext()) {
            Ufo ufo = (Ufo) it.next();

            if (ufo.isVisible()) {
                g.drawImage(ufo.getImage(), ufo.getX(), ufo.getY(), this);
            }

            if (ufo.Kuolee()) {
                ufo.die();
            }
        }
    }

    /**
     * Piirtää pelaajan kentälle. Pelaajan tuhoutuessa peli loppuu.
     *
     * @param g grafiikkamoottorin parametri
     */
    public void pelaajaKentalle(Graphics g) {

        if (pelaaja.isVisible()) {
            g.drawImage(pelaaja.getImage(), pelaaja.getX(), pelaaja.getY(), this);
        }
        if (pelaaja.Kuolee()) {
            pelaaja.die();
            ingame = false;
        }
    }

    /**
     * Piirtää pelaajan ammukset kentälle
     *
     * @param g
     */
    public void ammusKentalle(Graphics g) {
        if (kuti.isVisible()) {
            g.drawImage(kuti.getImage(), kuti.getX(), kuti.getY(), this);
        }
    }

    /**
     * piirtää ufojen ammukset kentälle.
     *
     * @param g
     */
    public void ufotAmpuu(Graphics g) {
        Iterator i3 = ufot.iterator();

        while (i3.hasNext()) {
            Ufo ufo = (Ufo) i3.next();

            UfoKuti ufokuti = ufo.getUfoKuti();

            if (!ufokuti.kutiTuhoutuu()) {
                g.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this);
            }
        }
    }

<<<<<<< HEAD
    
=======
    /**
     * Piirtää game over -ruudun.
     */
    public void peliLoppuu() {
        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, RuudunLeveys, RuudunKorkeus);

        Font small = new Font("Comic sans", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(peliLoppui, (RuudunLeveys - metr.stringWidth(peliLoppui)) / 2, RuudunLeveys / 2);
    }

>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9
    public void run() {
    }
}
