/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Larppa
 */
public class Grafiikka extends JPanel implements Asetukset {

    private String PeliLoppui;
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
}
