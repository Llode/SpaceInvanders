/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

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

import spaceinvanders.Pelimoottori;
import Pelimoottori.Asetukset;
import Pelimoottori.Kuti;
import Pelimoottori.Pelaaja;
import spaceinvanders.TAdapter;
import Pelimoottori.Ufo;

/**
 *
 * @author Larppa
 */
public class Grafiikka extends JPanel implements Asetukset {

    private final String peliLoppui = "Game Over";
    private final String ammus = "/res/ammus.png";
    private final String pelaajakuva = "/res/pelaaja.png";
    private final String UfoKuva = "/res/ufo.png";
    private Dimension d;
    Kuti kuti;
    Pelaaja pelaaja;
    Ufo ufo;
    Pelimoottori moottori = new Pelimoottori();
    TAdapter TAdapter;

    /**
     * Luo pelikentän.
     */
    public Grafiikka() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(RuudunLeveys, RuudunKorkeus);
        setBackground(Color.black);

        moottori.SetUp();
        setDoubleBuffered(true);

    }

    public void addNotify() {
        super.addNotify();
        moottori.SetUp();
    }

    /**
     * Piirtää game over -ruudun.
     */
    public void peliLoppuu() {
        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, RuudunLeveys, RuudunKorkeus);

        Font small = new Font("Comic sans", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(peliLoppui, (RuudunLeveys - metr.stringWidth(peliLoppui)) / 2, RuudunLeveys / 2);
    }

    public void asetaKuvaAmmukselle() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(ammus));
        kuti.setImage(ii.getImage());
    }

    public void asetaKuvaPelaajalle() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(pelaajakuva));
        pelaaja.leveys = ii.getImage().getWidth(null);
        pelaaja.setImage(ii.getImage());
    }
    public void asetaKuvaUfolle(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(UfoKuva));
        ufo.setImage(ii.getImage());
    }
}
