/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

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
    private Pelimoottori moottori = new Pelimoottori();
    private Thread animator;

    public Kuti kuti;
    public Pelaaja pelaaja;
    public Ufo ufo;
    public TAdapter TAdapter;
    public UfoKuti ufokuti;

    /**
     * Luo pelikentän.
     */
    public Grafiikka(Pelimoottori pelimoottori) {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(RuudunLeveys, RuudunKorkeus);
        setBackground(Color.black);
        Kaynnistys(pelimoottori);
        setDoubleBuffered(true);

    }

    /**
     * Käynnistää animaattorin (ja toivottavasti myös pelin).
     *
     * @param pelimoottori
     */
    private void Kaynnistys(Pelimoottori pelimoottori) {
        moottori.SetUp();
        if (animator == null || !pelimoottori.ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void addNotify() {
        super.addNotify();
        moottori.SetUp();
    }

    /**
     * Piirtää taustan sekä objektit kentälle.
     *
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);
        piirraTavaratKentalle(g);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * Piirtää game over -ruudun.
     */
    public void peliLoppuu() {
        if(!moottori.ingame){
        PeliLoppui = moottori.Loppusanat;
        Graphics g = this.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, RuudunLeveys, RuudunKorkeus);
        
        g.setColor(new Color (0, 32, 48));
        g.fillRect(50, RuudunLeveys/2 -30, RuudunLeveys-100, 50);
        g.setColor(Color.white);
        g.drawRect(50, RuudunLeveys/2 -30, RuudunLeveys-100, 50);

        Font small = new Font("Comic sans", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(PeliLoppui, (RuudunLeveys - metr.stringWidth(PeliLoppui)) / 2, RuudunLeveys / 2);
        }
    }

    /**
     * Asettaa kuvan pelaajan ammuksille.
     */
//    public void asetaKuvaAmmukselle() {
//        ImageIcon ii = new ImageIcon(ammus);
//        kuti.setImage(ii.getImage());
//    }

    /**
     * Asettaa kuvan pelaajalle.
     */
//    public void asetaKuvaPelaajalle() {
//        ImageIcon ii = new ImageIcon(pelaajakuva);
//        pelaaja.leveys = ii.getImage().getWidth(null);
//        pelaaja.setImage(ii.getImage());
//    }

    /**
     * Asettaa kuvan ufolle.
     */
//    public void asetaKuvaUfolle() {
//        ImageIcon ii = new ImageIcon(UfoKuva);
//        ufo.setImage(ii.getImage());
//    }

    /**
     * Asettaa kuvan räjähdykselle.
     */
//    public void asetaKuvaRajahdykselle() {
//        objektiKuolee = new ImageIcon(rajahdys);
//    }

    /**
     * Ette ikinä arvaa.
     */
//    public void asetaKuvaUfoKudille() {
//        ImageIcon ii = new ImageIcon(ammus);
//        ufokuti.setImage(ii.getImage());
//    }

    /**
     * @return Noutaa räjähdyksen tuhoutuvia objekteja varten.
     */
//    public ImageIcon getRajahdys() {
//        return objektiKuolee;
//    }
    
    
/**
 * Piirtää ufon kentälle.
 * @param g 
 */
    public void piirraUfo(Graphics g) {
        g.drawImage(ufo.getImage(), ufo.getX(), ufo.getY(), this);
    }
/**
 * Piirtää pelaajan kentälle.
 * @param g 
 */
    public void piirraPelaaja(Graphics g) {
        g.drawImage(pelaaja.getImage(), pelaaja.getX(), pelaaja.getY(), this);
    }
/**
 * Piirtää kuudin kentälle.
 * @param g 
 */
    public void piirraKuti(Graphics g) {
        g.drawImage(kuti.getImage(), kuti.getX(), kuti.getY(), this);
    }
/**
 * Piirtää Ufokudin kentälle
 * @param g 
 */
    public void piirraUfoKuti(Graphics g) {
        g.drawImage(ufokuti.getImage(), ufokuti.getX(), ufokuti.getY(), this);
    }
/**
 * Piirtää (ja 'animoi') peligrafiikan.
 * @param g 
 */
    private void piirraTavaratKentalle(Graphics g) {
        if (moottori.ingame) {
            g.drawLine(0, UfojenMaaliViiva, RuudunLeveys, UfojenMaaliViiva);
            moottori.ufotKentalle(g);
            moottori.pelaajaKentalle(g);
            moottori.ammusKentalle(g);
            moottori.ufotAmpuu(g);
        }
    }

    public void run() {
        
        long beforeTime, timeDiff, sleep;
        
        beforeTime = System.currentTimeMillis();
        
        while (moottori.ingame) {
            repaint();
            moottori.toiminta();
            
            timeDiff = (System.currentTimeMillis() - beforeTime);
            sleep = Delay - timeDiff;
            
            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
        peliLoppuu();
    }
}
