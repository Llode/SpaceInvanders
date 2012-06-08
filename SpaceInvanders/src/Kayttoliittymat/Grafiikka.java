/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.*;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Larppa
 */
public class Grafiikka extends JPanel implements Asetukset {


    Pelimoottori moottori;
    UfoKuti ufokuti;
    Pelaaja pelaaja;
    Ufo ufo;
    Kuti kuti;

    /**
     * Piirtää ufon kentälle.
     *
     * @param g
     */
    public void piirraUfo(Graphics g) {
        g.drawImage(ufo.getImage(), ufo.getX(), ufo.getY(), this);
    }

    /**
     * Piirtää pelaajan kentälle.
     *
     * @param g
     */
    public void piirraPelaaja(Graphics g) {
        g.drawImage(pelaaja.getImage(), pelaaja.getX(), pelaaja.getY(), this);
    }

    /**
     * Piirtää kuudin kentälle.
     *
     * @param g
     */
    public void piirraKuti(Graphics g) {
        g.drawImage(kuti.getImage(), kuti.getX(), kuti.getY(), this);
    }

    /**
     * Piirtää Ufokudin kentälle
     *
     * @param g
     */
    public void piirraUfoKuti(Graphics g) {
        g.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this);
    }

    /**
     * Piirtää (ja 'animoi') peligrafiikan.
     *
     * @param g
     */
    public void piirraTavaratKentalle(Graphics g) {
        if (moottori.ingame) {
            g.drawLine(0, UfojenMaaliViiva, RuudunLeveys, UfojenMaaliViiva);
            moottori.ufotKentalle(g);
            moottori.pelaajaKentalle(g);
            moottori.ammusKentalle(g);
            moottori.ufotAmpuu(g);
        }
    }
}
