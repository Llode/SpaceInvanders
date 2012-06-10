/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *Sisältää peligrafiikat.
 * @author Larppa
 */
public class Grafiikka extends JPanel implements Asetukset {


    Pelimoottori moottori;
    UfoKuti ufokuti;
    Pelaaja pelaaja;
    Ufo ufo;
    Kuti kuti;
    private final String ammus = "ammus.png";
    private final String pelaajakuva = "pelaaja.png";
    private final String UfoKuva = "ufo.png";
    private final String rajahdys = "rajahdys.png";
    private ImageIcon objektiKuolee;

    public Grafiikka(Pelimoottori moottori) {
        kuti = new Kuti();
        pelaaja = new Pelaaja();
        ufo = new Ufo();
        ufokuti = new UfoKuti();
    }

    /**
     * Piirtää ufon kentälle.
     *
     * @param g
     */
    public void piirraUfo(Graphics2D g2d) {
        g2d.drawImage(ufo.getImage(), ufo.getX(), ufo.getY(), this);
        if (g2d.drawImage(ufo.getImage(), ufo.getX(), ufo.getY(), this)) {
            System.out.println("ufo toimii");
        }
    }

    /**
     * Piirtää pelaajan kentälle.
     *
     * @param g
     */
    public void piirraPelaaja(Graphics2D g2d) {
        g2d.drawImage(pelaaja.getImage(), pelaaja.getX(), pelaaja.getY(), this);
        if (g2d.drawImage(pelaaja.getImage(), pelaaja.getX(), pelaaja.getY(), this)) {
            System.out.println("pelaaja toimii");
        }
    }

    /**
     * Piirtää kuudin kentälle.
     *
     * @param g
     */
    public void piirraKuti(Graphics2D g2d) {
        g2d.drawImage(kuti.getImage(), kuti.getX(), kuti.getY(), this);
        if (g2d.drawImage(kuti.getImage(), kuti.getX(), kuti.getY(), this)) {
            System.out.println("kuti toimii");
        }
    }

    /**
     * Piirtää Ufokudin kentälle
     *
     * @param g
     */
    public void piirraUfoKuti(Graphics2D g2d) {
        g2d.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this);
        if (g2d.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this)) {
            System.out.println("ufokuti toimii");
        }
    }

    /**
     * Piirtää (ja 'animoi') peligrafiikan.
     *
     * @param g
     */
    public void piirraTavaratKentalle(Graphics2D g) {
        if (moottori.ingame) {
            g.drawLine(0, UfojenMaaliViiva, RuudunLeveys, UfojenMaaliViiva);
            moottori.ufotKentalle(g);
            moottori.pelaajaKentalle(g);
            moottori.ammusKentalle(g);
            moottori.ufotAmpuu(g);
        }
    }



    /**
     * Asettaa kuvan pelaajalle.
     */
    public void asetaKuvaPelaajalle() {
 
        ImageIcon ii = new ImageIcon(pelaajakuva);
        pelaaja.leveys = ii.getImage().getWidth(null);
        pelaaja.setImage(ii.getImage());
    }

    /**
     * Asettaa kuvan pelaajan ammuksille.
     */
    public void asetaKuvaAmmukselle() {
        ImageIcon ii = new ImageIcon(ammus);
        kuti.setImage(ii.getImage());
    }

    /**
     * Asettaa kuvan ufolle.
     */
    public void asetaKuvaUfolle() {
        ImageIcon ii = new ImageIcon(UfoKuva);
        ufo.setImage(ii.getImage());

    }

    /**
     * Asettaa kuvan räjähdykselle.
     */
    public void asetaKuvaRajahdykselle() {
        objektiKuolee = new ImageIcon(rajahdys);
    }

    /**
     * Ette ikinä arvaa.
     */
    public void asetaKuvaUfoKudille() {
        ImageIcon ii = new ImageIcon(ammus);
        ufokuti.setImage(ii.getImage());
    }

    /**
     * @return Noutaa räjähdyksen tuhoutuvia objekteja varten.
     */
    public ImageIcon getRajahdys() {
        return objektiKuolee;
    }
}
