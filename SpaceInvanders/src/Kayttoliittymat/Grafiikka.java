/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import spaceinvanders.TAdapter;

/**
 * Pelin sisäinen grafiikka.
 *
 * @author Larppa
 */
public class Grafiikka extends JPanel implements Asetukset, Runnable {

    private String PeliLoppui;
    private final String ammus = "ammus.png";
    private final String pelaajakuva = "pelaaja.png";
    private final String UfoKuva = "ufo.png";
    private final String rajahdys = "rajahdys.png";
    private Dimension d;
    ImageIcon objektiKuolee;
    Kuti kuti;
    Pelaaja pelaaja;
    Ufo ufo;
    Pelimoottori moottori = new Pelimoottori();
    TAdapter TAdapter;
    UfoKuti ufokuti;
    private Thread animator;

    /**
     * Luo pelikentän.
     */
    public Grafiikka(Pelimoottori pelimoottori) {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(RuudunLeveys, RuudunKorkeus);
        setBackground(Color.black);

        moottori.SetUp();
        if (animator == null || !pelimoottori.ingame) {
            animator = new Thread(this);
            animator.start();
        }
        setDoubleBuffered(true);

    }

    public void addNotify() {
        super.addNotify();
        moottori.SetUp();
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.red);

        if (moottori.ingame) {
            g.drawLine(0, UfojenMaaliViiva, RuudunLeveys, UfojenMaaliViiva);
            moottori.ufotKentalle(g);
            moottori.pelaajaKentalle(g);
            moottori.ammusKentalle(g);
            moottori.ufotAmpuu(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * Piirtää game over -ruudun.
     */
    public void peliLoppuu() {
        PeliLoppui = moottori.Loppusanat;
        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, RuudunLeveys, RuudunKorkeus);

        Font small = new Font("Comic sans", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(PeliLoppui, (RuudunLeveys - metr.stringWidth(PeliLoppui)) / 2, RuudunLeveys / 2);
    }

    /**
     * Asettaa kuvan pelaajan ammuksille.
     */
    public void asetaKuvaAmmukselle() {
        ImageIcon ii = new ImageIcon(ammus);
        kuti.setImage(ii.getImage());
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

    public void run() {
        while (moottori.ingame) {
            repaint();
            moottori.toiminta();
        }
        peliLoppuu();
    }
}
