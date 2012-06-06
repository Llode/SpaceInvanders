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
 *
 * @author Larppa
 */
public class Pelimoottori extends JPanel implements Asetukset {

    //private Dimension d;
    private Pelaaja pelaaja;
    private Kuti kuti;
    private ArrayList ufot;
//    private Ufo ufo;
    private int ufoX;
    private int ufoY;
    private int suunta;
    public int tuhotut;
    boolean ingame;
    private String UfoKuva;
    private Thread animator;
    Grafiikka grafiikka;
    Graphics g;

    public void Pelimoottori() {
        tuhotut = 0;
        suunta = -1;
        ufoY = 5;
        ufoX = 10;
        UfoKuva = "ufo.png";
        ingame = true;
    }

    /**
     * ASettaa ufot riveihin, luo pelaajan, asettaa spritet objekteille.
     */
    public void SetUp() {
        ufot = new ArrayList();

        ImageIcon ii = new ImageIcon(UfoKuva);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Ufo ufo = new Ufo(ufoX + 18 * j, ufoY + 18 * j);
                ufo.setImage(ii.getImage());
//                grafiikka.asetaKuvaUfolle();
                ufot.add(ufo);
            }
        }

        pelaaja = new Pelaaja();

        grafiikka.asetaKuvaPelaajalle();
        kuti = new Kuti();
        grafiikka.asetaKuvaAmmukselle();

//        if (animator == null || !ingame) {
//            animator = new Thread(this);
//            animator.start();
//        }
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
            ufoKatoaaKuollessaan(ufo);
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
        peliLoppuuPelaajanKuollessa();
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
        pelaajanAmmuksetAnimaatioSyklissa();

        //Ufot
        Iterator it1 = ufot.iterator();

        while (it1.hasNext()) {
            Ufo ufo1 = (Ufo) it1.next();
            int x = ufo1.getX();
            ufotAlemmasOikeassaReunassa(x);
            ufotAlemmasVasReunassa(x);
        }
        Iterator it = ufot.iterator();

        while (it.hasNext()) {
            Ufo ufo = (Ufo) it.next();
            ufotSaavuttavatMaan(ufo);
        }
        ufonAmmuksetToimintasyklissa();
    }

    private void pelaajanAmmuksetAnimaatioSyklissa() {
        if (kuti.isVisible()) {
            Iterator it = ufot.iterator();
            int kutiX = kuti.getX();
            int kutiY = kuti.getY();

            while (it.hasNext()) {
                Ufo ufo = (Ufo) it.next();
                int ufoX = ufo.getX();
                int ufoY = ufo.getY();

                if (ufo.isVisible() && kuti.isVisible()) {
                    if (ufonOsumaTunnistus(kutiX, ufoX, kutiY, ufoY)) {
                        ufoTuhoutuuOsumasta(ufo);
                    }
                }
            }
            pelaajanAmmusLiikkuu();
        }
    }

    /**
     * Liikuttaa pelaajan ampumaa ammusta. Ammus katoaa, mikäli se menee yli
     * kentän reunan.
     */
    public void pelaajanAmmusLiikkuu() {
        int y = kuti.getY();
        y -= 4;
        if (y < 0) {
            kuti.die();
        } else {
            kuti.setY(y);
        }
    }

    /**
     * Ohjaa ufokutien toimintaa.
     */
    private void ufonAmmuksetToimintasyklissa() {
        Iterator i3 = ufot.iterator();
        Random rng = new Random();

        while (i3.hasNext()) {
            int ampuu = rng.nextInt(15);
            Ufo ufo = (Ufo) i3.next();
            UfoKuti ufokuti = ufo.getUfoKuti();
            ufoAmpuuJosArpaSuosii(ampuu, ufo, ufokuti);

            int ufokutiX = ufokuti.getX();
            int ufokutiY = ufokuti.getY();
            int pelaajaX = pelaaja.getX();
            int pelaajaY = pelaaja.getY();

            if (pelaaja.isVisible() && !ufokuti.kutiTuhoutuu()) {
                if (pelaajanOsumatunnistus(ufokutiX, pelaajaX, ufokutiY, pelaajaY)) {
                    pelaajaTuhoutuuOsumasta(ufokuti);
                }
            }
        }
    }

    /**
     * Ufot ampuvat, mikäli todennäköisyydet osuvat kohdalleen.
     *
     * @param ampuu RNGn tuottama numero.
     * @param ufo ampuva ufo.
     * @param ufokuti ufon ampuma ammus.
     */
    private void ufoAmpuuJosArpaSuosii(int ampuu, Ufo ufo, UfoKuti ufokuti) {
        if (ampuu == UfonAmpumaTodNak && ufo.isVisible() && ufokuti.kutiTuhoutuu()) {
            ufokuti.setKutiTuhoutuu(false);
            ufokuti.setX(ufo.getX());
            ufokuti.setY(ufo.getY());
        }
    }

    /**
     * Peli hävitään, mikäli ufot pääsevät laskeutumaan liian alas.
     *
     * @param ufo
     */
    protected void ufotSaavuttavatMaan(Ufo ufo) {
        if (ufo.isVisible()) {
            int y = ufo.getY();

            if (y > UfojenMaaliViiva - UfonKorkeus) {
                ingame = false;
                //Peli loppuu invaasioon
            }
            ufo.ufoLiikkuu(suunta);
        }
    }

    /**
     * Ufot laskeutuvat rivin alemmas, kun ruudun vasen reuna saavutetaan.
     *
     * @param x Ufojen koordinaatti
     */
    private void ufotAlemmasVasReunassa(int x) {
        if (x <= RuudunVasenReuna && suunta != 1) {
            suunta = 1;

            Iterator i2 = ufot.iterator();
            while (i2.hasNext()) {
                Ufo ufo = (Ufo) i2.next();
                ufo.setY(ufo.getY() + UfotLiikkuvatRivinALas);
            }
        }
    }

    /**
     * Ufot laskeutuvat rivin alemmas, kun ruudun oikea reuna saavutetaan.
     *
     * @param x Ufojen koordinaatti
     */
    private void ufotAlemmasOikeassaReunassa(int x) {
        if (x >= RuudunLeveys - RuudunOikeaReuna && suunta != -1) {
            suunta = -1;
            Iterator i1 = ufot.iterator();
            while (i1.hasNext()) {
                Ufo ufo2 = (Ufo) i1.next();
                ufo2.setY(ufo2.getY() + UfotLiikkuvatRivinALas);
            }
        }
    }

    /**
     * Pelaajan alus tuhoutuu, kun ufojen ammukset osuvat siihen.
     *
     * @param ufokuti
     */
    private void pelaajaTuhoutuuOsumasta(UfoKuti ufokuti) {
        pelaaja.setImage(grafiikka.getRajahdys().getImage());
        pelaaja.setKuolee(true);
        ufokuti.setKutiTuhoutuu(true);
    }

    /**
     * Tutkii pelaajan aluksen ja ufojen ammusten törmäystä.
     *
     * @param ufokutiX
     * @param pelaajaX
     * @param ufokutiY
     * @param pelaajaY
     * @return jos tosi, pelaaja tuhoutuu.
     */
    public boolean pelaajanOsumatunnistus(int ufokutiX, int pelaajaX, int ufokutiY, int pelaajaY) {
        return ufokutiX >= pelaajaX
                && ufokutiX <= (pelaajaX + PelaajanLeveys)
                && ufokutiY >= pelaajaY
                && ufokutiY <= (pelaajaY + PelaajanKorkeus);
    }

    /**
     * Ufo tuhoutuu, kun pelaaja osuu siihen ammuksellaan.
     *
     * @param ufo
     */
    public void ufoTuhoutuuOsumasta(Ufo ufo) {
//        grafiikka.asetaKuvaRajahdykselle();
//        ufo.setImage(grafiikka.getRajahdys().getImage());
        ufo.setKuolee(true);
        tuhotut++;
        kuti.die();
    }

    /**
     * Tutkii pelaajan ammusten ja ufojen törmäyksiä.
     *
     * @param kutiX
     * @param ufoX
     * @param kutiY
     * @param ufoY
     * @return jos tosi, ufo tuhoutuu.
     */
    public boolean ufonOsumaTunnistus(int kutiX, int ufoX, int kutiY, int ufoY) {
        return kutiX >= ufoX
                && kutiX <= (ufoX + UfonLeveys)
                && kutiY >= ufoY
                && kutiY <= (ufoY + UfonKorkeus);
    }

    private void peliLoppuuPelaajanKuollessa() {
        if (pelaaja.Kuoleeko()) {
            pelaaja.die();
            ingame = false;
        }
    }

    public void ufoKatoaaKuollessaan(Ufo ufo) {
        if (ufo.Kuoleeko()) {
            ufo.die();
        }
    }
}