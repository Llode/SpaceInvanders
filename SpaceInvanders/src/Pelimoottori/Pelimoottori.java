/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

import Kayttoliittymat.Grafiikka;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Pelin toimintalogiikka löytyy täältä.
 *
 * @author Larppa
 */
public class Pelimoottori implements Asetukset {

    public Pelaaja pelaaja;
    public Kuti kuti;
    public Ufo ufo;
    public UfoKuti ufokuti;
    private ArrayList ufot;
    private int ufoX;
    private int ufoY;
    private int suunta;
    public int tuhotut;
    public boolean ingame;
    public boolean PiirraUfo;
    public boolean PiirraPelaaja;
    public boolean PiirraKuti;
    public boolean PiirraUfokuti;
    Grafiikka grafiikka;
    public String Loppusanat = "";

    public Pelimoottori() {

        tuhotut = 0;
        suunta = -1;
        ufoY = 5;
        ufoX = 50;
        ingame = true;
        PiirraUfo = false;
        PiirraPelaaja = false;
        PiirraKuti = false;
        PiirraUfokuti = false;


    }

    /**
     * ASettaa Luo ufot ja pelaajan, asettaa spritet objekteille.
     */
    public void GameInit() {
        ufot = new ArrayList();
        kuti = new Kuti();
        pelaaja = new Pelaaja();
        grafiikka = new Grafiikka(this);
        grafiikka.asetaKuvaPelaajalle(pelaaja);
        grafiikka.asetaKuvaAmmukselle(kuti);
        asetaUfotRiveihin();
    }

    /**
     * Piirtää ufot kentälle. Jos ufo on näkyvissä, se saa kuvan. Jos ufo on
     * kuollut, se katoaa kentältä.
     *
     * @param g grafiikkamoottorin parametri
     */
    public void ufotKentalle(Graphics2D g) {
        Iterator it = ufot.iterator();

        while (it.hasNext()) {
            ufo = (Ufo) it.next();

            if (ufo.isVisible()) {
                PiirraUfo = true;
            }
            ufoKatoaaKuollessaan(ufo);
        }
    }

    /**
     * Piirtää pelaajan kentälle. Pelaajan tuhoutuessa peli loppuu.
     *
     * @param g grafiikkamoottorin parametri
     */
    public void pelaajaKentalle(Graphics2D g) {

        if (pelaaja.isVisible()) {
            PiirraPelaaja = true;
        }
        peliLoppuuPelaajanKuollessa();
    }

    /**
     * Piirtää pelaajan ammukset kentälle
     *
     * @param g
     */
    public void ammusKentalle(Graphics2D g) {
        grafiikka.asetaKuvaAmmukselle(kuti);
        if (kuti.isVisible()) {
            PiirraUfo = true;
        }
    }

    /**
     * piirtää ufojen ammukset kentälle.
     *
     * @param g
     */
    public void ufotAmpuu(Graphics2D g) {
        grafiikka.asetaKuvaUfoKudille(ufokuti);

        Iterator i3 = ufot.iterator();
        while (i3.hasNext()) {
            ufo = (Ufo) i3.next();
            ufokuti = ufo.getUfoKuti();
            if (!ufokuti.kutiTuhoutuu()) {
                PiirraUfokuti = true;
            }
        }
    }

//    public Kuti pelaajaAmpuu() {
//        int pelaajaX = this.pelaaja.getX();
//        int pelaajaY = this.pelaaja.getY();
//        if (!kuti.isVisible()) {
//            kuti = new Kuti(pelaajaX, pelaajaY);
//            return kuti;
//        }
//        return null;
//    }
    /**
     * Sisältää pelin logiikan.
     */
    public void toiminta() {
        if (ingame == true) {
            toimitaVarmistus();
            peliLoppuuUfojenTuhoamiseen();

            //Pelaaja
            pelaaja.pelaajaLiikkuu();
            pelaajanAmmuksetToimintaSyklissa();

            //Ufot
            Iterator it1 = ufot.iterator();
            ufotLiikkuvatKentalla(it1);
            System.out.println("ufot liikkuu");
            Iterator it = ufot.iterator();

            while (it.hasNext()) {
                ufo = (Ufo) it.next();
                ufotSaavuttavatMaan(ufo);
            }
            ufonAmmuksetToimintasyklissa();
        }
        toimitaVarmistus();
    }

    private void toimitaVarmistus() {
        if (ingame) {
            System.out.println("ingame");
        } else {
            System.out.println("outgame");
        }
    }
    //Tästä alaspäin vain apumetodeja.

    /**
     * Asettaa ufot neljään riviin.
     *
     * @param ii ufon imageicon
     */
    private void asetaUfotRiveihin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                ufo = new Ufo(ufoX + 18 * j, ufoY + 18 * i);
                grafiikka.asetaKuvaUfolle(ufo);
//                ufo.setImage(ii.getImage());
                ufot.add(ufo);
            }
        }
    }

    /**
     * Liikuttaa ufoja ruudulla. Rivin saavuttaessa ruudun reunan ufot
     * laskeutuvat alaspäin.
     *
     * @param it1
     */
    private void ufotLiikkuvatKentalla(Iterator it1) {
        while (it1.hasNext()) {
            Ufo ufo1 = (Ufo) it1.next();
            int x = ufo1.getX();
            ufotAlemmasOikeassaReunassa(x);
            ufotAlemmasVasReunassa(x);
            System.out.println("ufoliikkuu");
        }
    }

    /**
     * Pelaaja voittaa pelin kaikkien ufojen tuhoutuessa.
     */
    private void peliLoppuuUfojenTuhoamiseen() {
        if (tuhotut == Ufolkm) {
            ingame = false;
            Loppusanat = "Voitit pelin!";
        }
    }

    /**
     * Ohjaa pelaajan ammuksia. Sisältää liikkumisen sekä osumatunnistuksen.
     */
    private void pelaajanAmmuksetToimintaSyklissa() {
        if (kuti.isVisible()) {
            Iterator it = ufot.iterator();
            int kutiX = kuti.getX();
            int kutiY = kuti.getY();

            while (it.hasNext()) {
                ufo = (Ufo) it.next();
                int ufox = ufo.getX();
                int ufoy = ufo.getY();

                if (ufo.isVisible() && kuti.isVisible()) {
                    if (ufonOsumaTunnistus(kutiX, ufox, kutiY, ufoy)) {
                        ufoTuhoutuuOsumasta(ufo, kuti);
                    }
                }
            }
            pelaajanAmmusLiikkuu(kuti);
        }
    }

    /**
     * Liikuttaa pelaajan ampumaa ammusta. Ammus katoaa, mikäli se menee yli
     * kentän reunan.
     */
    protected void pelaajanAmmusLiikkuu(Kuti kuti) {
        int y = kuti.getY();
        y -= 4;
        if (y < 0) {
            kuti.die();
        } else {
            kuti.setY(y);
        }
    }

    /**
     * Ohjaa ufokutien toimintaa. SIsältää liikkeen, ampumisen sekä
     * osumatunnistuksen.
     */
    private void ufonAmmuksetToimintasyklissa() {
        Iterator i3 = ufot.iterator();
        Random rng = new Random();

        while (i3.hasNext()) {
            int ampuu = rng.nextInt(15);
            ufo = (Ufo) i3.next();
            ufokuti = ufo.getUfoKuti();
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
            System.out.println("Ufo ampuu");
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
                Loppusanat = "Ufot ovat valloittaneet maailman!";
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
                ufo = (Ufo) i2.next();
                ufo.setY(ufo.getY() + UfotLiikkuvatRivinALas);
            }
            System.out.println("rivi alas");
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
            System.out.println("rivi alas");
        }
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
     * Tutkii pelaajan ammusten ja ufojen törmäyksiä.
     *
     * @param kutiX
     * @param ufoX
     * @param kutiY
     * @param ufoY
     * @return jos tosi, ufo tuhoutuu.
     */
    protected boolean ufonOsumaTunnistus(int kutiX, int ufoX, int kutiY, int ufoY) {
        return kutiX >= ufoX
                && kutiX <= (ufoX + UfonLeveys)
                && kutiY >= ufoY
                && kutiY <= (ufoY + UfonKorkeus);
    }

    /**
     * Ufo tuhoutuu, kun pelaaja osuu siihen ammuksellaan.
     *
     * @param ufo ufo, jota tarkastellaan.
     */
    protected void ufoTuhoutuuOsumasta(Ufo ufo, Kuti kuti) {
        grafiikka.asetaKuvaRajahdykselle();
        ufo.setImage(grafiikka.getRajahdys().getImage());
        ufo.setKuolee(true);
        tuhotut++;
        kuti.die();
    }

    /**
     * pelaaja.setKuolee() johtaa pelaajan katoamiseen kentältä sekä pelin
     * loppumiseen.
     */
    private void peliLoppuuPelaajanKuollessa() {
        if (pelaaja.Kuoleeko()) {
            pelaaja.die();
            ingame = false;
            Loppusanat = "Hävisit pelin!";
        }
    }

    /**
     * ufo.setKuolee() johtaa ufon katoamiseen kentältä.
     *
     * @param ufo
     */
    protected void ufoKatoaaKuollessaan(Ufo ufo) {
        if (ufo.Kuoleeko()) {
            ufo.die();
        }
    }
}