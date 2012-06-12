/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.*;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Sisältää peligrafiikat.
 *
 * @author Larppa
 */
public class Grafiikka extends JPanel implements Asetukset {

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
//        this.moottori = moottori;
//        kuti = this.moottori.kuti;
//        pelaaja = this.moottori.pelaaja;
//        ufo = this.moottori.ufo;
//        ufokuti = this.moottori.ufokuti;
    }

    /**
     * Piirtää ufon kentälle.
     *
     * @param g
     */
    public void piirraUfo(Graphics2D g2d, Ufo ufo) {
        g2d.drawImage(ufo.getImage(), ufo.getX(), ufo.getY(), this);
    }

    /**
     * Piirtää pelaajan kentälle.
     *
     * @param g
     */
    public void piirraPelaaja(Graphics2D g2d, Pelaaja pelaaja) {
        g2d.drawImage(pelaaja.getImage(), pelaaja.getX(), pelaaja.getY(), this);
    }

    /**
     * Piirtää kuudin kentälle.
     *
     * @param g
     */
    public void piirraKuti(Graphics2D g2d, Kuti kuti) {
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
    public void piirraUfoKuti(Graphics2D g2d, UfoKuti ufokuti) {
        g2d.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this);
        if (g2d.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this)) {
            System.out.println("ufokuti toimii");
        }
    }

    /**
     * Asettaa kuvan pelaajalle.
     */
    public void asetaKuvaPelaajalle(ImageIcon ii, Pelaaja pelaaja) {
        pelaaja.setImage(ii.getImage());

    }

    /**
     * Asettaa kuvan pelaajan ammuksille.
     */
    public void asetaKuvaAmmukselle(ImageIcon ii, Kuti kuti) {
        kuti.setImage(ii.getImage());

    }

    /**
     * Asettaa kuvan ufolle.
     */
    public void asetaKuvaUfolle(ImageIcon ii, Ufo ufo) {
        ufo.setImage(ii.getImage());

    }

    /**
     * Ette ikinä arvaa.
     */
    public void asetaKuvaUfoKudille(ImageIcon ii, UfoKuti ufokuti) {
        ufokuti.setImage(ii.getImage());
    }

    /**
     * Asettaa kuvan räjähdykselle.
     */
    public void asetaKuvaRajahdykselle() {
        this.objektiKuolee = new ImageIcon(rajahdys);
    }

    /**
     * @return Noutaa räjähdyksen tuhoutuvia objekteja varten.
     */
    public ImageIcon getRajahdys() {
        return objektiKuolee;
    }

    public ImageIcon ufoIcon(Ufo ufo) {
        ImageIcon ii = new ImageIcon(UfoKuva);
        return ii;
    }

    public ImageIcon pelaajaIcon(Pelaaja pelaaja) {
        ImageIcon ii = new ImageIcon(pelaajakuva);
        return ii;
    }

    public ImageIcon kutiIcon(Kuti kuti) {
        ImageIcon ii = new ImageIcon(ammus);
        return ii;
    }

    public ImageIcon ufoKutiIcon(UfoKuti ufokuti) {
        ImageIcon ii = new ImageIcon(ammus);
        return ii;
    }
}
