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
    public ArrayList ufot;
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

    /**
     * konstruktori
     */
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
        ufokuti = new UfoKuti();
        ufokuti.setVisible(false);
        kuti.setVisible(false);
        grafiikka = new Grafiikka(this);
        grafiikka.asetaKuvaRajahdykselle();
        grafiikka.asetaKuvaPelaajalle(grafiikka.pelaajaIcon(pelaaja), pelaaja);
        asetaUfotRiveihin();
    }

    /**
     * Käskee piirtämään ufot kentälle. Jos ufo on näkyvissä, se saa kuvan. Jos
     * ufo on kuollut, se katoaa kentältä.
     */
    public void ufotKentalle() {
        Iterator it = ufot.iterator();

        while (it.hasNext()) {
            ufo = (Ufo) it.next();

            if (!ufo.Kuoleeko()) {
                PiirraUfo = true;
            }
            ufoKatoaaKuollessaan(ufo);
        }
    }

    /**
     * Käskee piirtämään pelaajan kentälle. Pelaajan tuhoutuessa peli loppuu.
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
     * Käskee piirtämään pelaajan ammuksen kentäle.
     *
     * @param g
     */
    public void ammusKentalle() {
        grafiikka.asetaKuvaAmmukselle(grafiikka.kutiIcon(kuti), kuti);
        kutiPiirretaanJosNakyvissa();
    }

    /**
     * Käskee piirtämään ufojen ammukset kentälle.
     *
     * @param g
     */
    public void ufotAmpuu(UfoKuti ufokuti) {
        grafiikka.asetaKuvaUfoKudille(grafiikka.ufoKutiIcon(ufokuti), ufokuti);

        Iterator i3 = ufot.iterator();
        while (i3.hasNext()) {
            ufo = (Ufo) i3.next();
            ufokuti = ufo.getUfoKuti();
            ufoKutiPiirretaanJosNakyvissa(ufokuti);
        }
    }

    /**
     * Metodia kutsutaan, kun pelaaja ampuu.
     *
     * @param pelaaja
     * @param kuti
     */
    public void pelaajaAmpuu(Pelaaja pelaaja, Kuti kuti) {
        pelaaja = this.pelaaja;
        if (!this.kuti.isVisible()) {
            kuti = new Kuti(pelaaja.getX(), pelaaja.getY());
            this.kuti = kuti;
        }
    }

    /**
     * Sisältää pelin logiikan.
     */
    public void toiminta() {
        if (ingame == true) {
            peliLoppuuUfojenTuhoamiseen();
            ufotKentalle();
            ammusKentalle();

            //Pelaaja
            pelaaja.pelaajaLiikkuu();
            pelaajanAmmuksetToimintaSyklissa(kuti);

            //Ufot
            Iterator it1 = ufot.iterator();
            ufotAmpuu(ufokuti);
            ufotLiikkuvatKentalla(it1);
            Iterator it = ufot.iterator();
            maaliviivaTarkastus(it);
            ufonAmmuksetToimintasyklissa(ufokuti);
        }
    }

    private void maaliviivaTarkastus(Iterator it) {
        while (it.hasNext()) {
            ufo = (Ufo) it.next();
            ufotSaavuttavatMaan(ufo);
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
                ufo = new Ufo(ufoX + 30 * j, ufoY + 25 * i);
                grafiikka.asetaKuvaUfolle(grafiikka.ufoIcon(ufo), ufo);
                ufot.add(ufo);
            }
        }
    }
/**
 * ufokuti katoaa, jos se tuhoutuu.
 * @param ufokuti 
 */
    private void ufokutiKatoaaJosTuhoutuu(UfoKuti ufokuti) {
        if (ufokuti.kutiTuhoutuu()) {
            ufokuti.die();
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
    private void pelaajanAmmuksetToimintaSyklissa(Kuti kuti) {
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
    private void ufonAmmuksetToimintasyklissa(UfoKuti ufokuti) {
        Iterator i3 = ufot.iterator();
        Random rng = new Random();

        while (i3.hasNext()) {
            int ampuu = rng.nextInt(30);
            ufo = (Ufo) i3.next();
            ufokuti = ufo.getUfoKuti();
            if (!ufokuti.isVisible()) {
                ufoAmpuuJosArpaSuosii(ampuu, ufo, ufokuti);
            }
            ufonAmmusLiikkuu(ufokuti);
            
            int ufokutiX = ufokuti.getX();
            int ufokutiY = ufokuti.getY();
            int pelaajaX = pelaaja.getX();
            int pelaajaY = pelaaja.getY();


            if (pelaaja.isVisible() && !ufokuti.kutiTuhoutuu()) {
                if (pelaajanOsumatunnistus(ufokutiX, pelaajaX, ufokutiY, pelaajaY)) {
                    pelaajaTuhoutuuOsumasta(ufokuti);
                }
            }
            ufokutiKatoaaJosTuhoutuu(ufokuti);
        }
    }

    /**
     * liikuttaa ufokutia kentällä
     *
     * @param ufokuti
     */
    protected void ufonAmmusLiikkuu(UfoKuti ufokuti) {
        if (ufokuti.isVisible()) {
            int y = ufokuti.getY();
            y += 2;
            if (y > 500) {
//            ufokuti.die();
                ufokuti.setKutiTuhoutuu(true);
            } else {
                ufokuti.setY(y);
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
        this.ufokuti = ufokuti;
        this.ufo = ufo;
        System.out.println("arpa");
        if (ampuu == UfonAmpumaTodNak && ufo.isVisible() && ufokuti.kutiTuhoutuu()) {
            ufokuti.setKutiTuhoutuu(false);
            ufokuti.setVisible(true);
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
                Loppusanat = "Ufot ovat valloittaneet maailman!";
                System.out.println("maali");
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
     * Tutkii pelaajan aluksen ja ufojen ammusten törmäystä.
     *
     * @param ufokutiX
     * @param pelaajaX
     * @param ufokutiY
     * @param pelaajaY
     * @return jos tosi, pelaaja tuhoutuu.
     */
    protected boolean pelaajanOsumatunnistus(int ufokutiX, int pelaajaX, int ufokutiY, int pelaajaY) {
        boolean osuma = (ufokutiX >= pelaajaX
                && ufokutiX <= (pelaajaX + PelaajanLeveys)
                && ufokutiY >= pelaajaY
                && ufokutiY <= (pelaajaY - PelaajanKorkeus));
        return osuma;
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
        System.out.println("pelaaja kuolee");
        peliLoppuuPelaajanKuollessa();
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
        kuti.die();
        grafiikka.asetaKuvaRajahdykselle();
        ufo.setImage(grafiikka.getRajahdys().getImage());
        ufo.setKuolee(true);
        tuhotut++;
    }

    /**
     * pelaaja.setKuolee() johtaa pelaajan katoamiseen kentältä sekä pelin
     * loppumiseen.
     */
    private void peliLoppuuPelaajanKuollessa() {
        if (pelaaja.Kuoleeko()) {
            pelaaja.die();
            PiirraPelaaja = false;
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
/**
 * Grafiikkamoottori piirtää ufon ammuksen kentälle, jos se on näkyvissä.
 * @param ufokuti 
 */
    private void ufoKutiPiirretaanJosNakyvissa(UfoKuti ufokuti) {
        if (!ufokuti.kutiTuhoutuu()) {
            PiirraUfokuti = true;
        } else {
            PiirraUfokuti = false;
        }
    }
/**
 * Grafiikkamoottori piirtää ammuksen kentälle, jos se on näkyvissä.
 */
    private void kutiPiirretaanJosNakyvissa() {
        if (kuti.isVisible()) {
            PiirraKuti = true;
        } else {
            PiirraKuti = false;
        }
    }
}