/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

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

/**
 * Pelin toimintalogiikka löytyy täältä.
 * @author Larppa
 */
public class Pelimoottori extends JPanel implements Asetukset, Runnable {

    //private Dimension d;
    private Pelaaja pelaaja;
    private Kuti kuti;
    private ArrayList ufot;
//    private Ufo ufo;
    private int ufoX = 150;
    private int ufoY = 5;
    private int suunta = -1;
    private int tuhotut = 0;
    boolean ingame = true;
    private final String UfoKuva = "/res/ufo.png";
    private Thread animator;
    Grafiikka grafiikka;
    Graphics g;

    public void addNotify() {
        super.addNotify();
        SetUp();
    }

    /**
     * ASettaa ufot riveihin, luo pelaajan, asettaa spritet objekteille.
     */
    public void SetUp() {
        ufot = new ArrayList();

//        ImageIcon ii = new ImageIcon(this.getClass().getResource(UfoKuva));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Ufo ufo = new Ufo(ufoX + 18 * j, ufoY + 18 * j);
//                ufo.setImage(ii.getImage());
                grafiikka.asetaKuvaUfolle();
                ufot.add(ufo);
            }
        }

        pelaaja = new Pelaaja();

        grafiikka.asetaKuvaPelaajalle();
        kuti = new Kuti();
        grafiikka.asetaKuvaAmmukselle();

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

            if (ufo.Kuoleeko()) {
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
        if (pelaaja.Kuoleeko()) {
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
        grafiikka.asetaKuvaAmmukselle();
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
        grafiikka.asetaKuvaUfoKudille();

        Iterator i3 = ufot.iterator();

        while (i3.hasNext()) {
            Ufo ufo = (Ufo) i3.next();
            UfoKuti ufokuti = ufo.getUfoKuti();
            if (!ufokuti.kutiTuhoutuu()) {
                g.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this);
            }
        }
    }

    /**
     * Sisältää pelin logiikan.
     */
    public void toiminta() {

        if (tuhotut == Ufolkm) {
            ingame = false;
//            peliVoitettu = true;
        }

        //Pelaaja
        pelaaja.pelaajaLiikkuu();

        //Pelaajan ammukset
        if (kuti.isVisible()) {
            Iterator it = ufot.iterator();
            int kutiX = kuti.getX();
            int kutiY = kuti.getY();

            while (it.hasNext()) {
                Ufo ufo = (Ufo) it.next();
                int ufoX = ufo.getX();
                int ufoY = ufo.getY();

                if (ufo.isVisible() && kuti.isVisible()) {
                    if (kutiX >= ufoX
                            && kutiX <= (ufoX + UfonLeveys)
                            && kutiY >= ufoY
                            && kutiY <= (ufoY + UfonKorkeus)) {
                        grafiikka.asetaKuvaRajahdykselle();
                        ufo.setImage(grafiikka.getRajahdys().getImage());
                        ufo.setKuolee(true);
                        tuhotut++;
                        kuti.die();
                    }
                }
            }
            int y = kuti.getY();
            y -= 4;
            if (y < 0) {
                kuti.die();
            } else {
                kuti.setY(y);
            }
        }

        //Ufot
        Iterator it1 = ufot.iterator();

        while (it1.hasNext()) {
            Ufo ufo1 = (Ufo) it1.next();
            int x = ufo1.getX();

            if (x >= RuudunLeveys - RuudunOikeaReuna && suunta != -1) {
                suunta = -1;
                Iterator i1 = ufot.iterator();
                while (i1.hasNext()) {
                    Ufo ufo2 = (Ufo) i1.next();
                    ufo2.setY(ufo2.getY() + UfotLiikkuvatRivinALas);
                }
            }
            if (x <= RuudunVasenReuna && suunta != 1) {
                suunta = 1;

                Iterator i2 = ufot.iterator();
                while (i2.hasNext()) {
                    Ufo ufo = (Ufo) i2.next();
                    ufo.setY(ufo.getY() + UfotLiikkuvatRivinALas);
                }
            }
        }
        Iterator it = ufot.iterator();

        while (it.hasNext()) {
            Ufo ufo = (Ufo) it.next();
            if (ufo.isVisible()) {
                int y = ufo.getY();

                if (y > UfojenMaaliViiva - UfonKorkeus) {
                    ingame = false;
                    //Peli löppuu invaasioon
                }
                ufo.ufoLiikkuu(suunta);
            }
        }

        //Ufojen ammukset        
        Iterator i3 = ufot.iterator();
        Random rng = new Random();

        while (i3.hasNext()) {
            int ampuu = rng.nextInt(15);
            Ufo ufo = (Ufo) i3.next();
            UfoKuti ufokuti = ufo.getUfoKuti();
            if (ampuu == UfonAmpumaTodNak && ufo.isVisible() && ufokuti.kutiTuhoutuu()) {
                ufokuti.setKutiTuhoutuu(false);
                ufokuti.setX(ufo.getX());
                ufokuti.setY(ufo.getY());
            }

            int ufokutiX = ufokuti.getX();
            int ufokutiY = ufokuti.getY();
            int pelaajaX = pelaaja.getX();
            int pelaajaY = pelaaja.getY();

            if (pelaaja.isVisible() && !ufokuti.kutiTuhoutuu()) {
                if (ufokutiX >= pelaajaX
                        && ufokutiX <= (pelaajaX + PelaajanLeveys)
                        && ufokutiY >= pelaajaY
                        && ufokutiY <= (pelaajaY + PelaajanKorkeus)) {
                    pelaaja.setImage(grafiikka.getRajahdys().getImage());
                    pelaaja.setKuolee(true);
                    ufokuti.setKutiTuhoutuu(true);
                }
            }
        }
    }

    public void run() {
    }
}
