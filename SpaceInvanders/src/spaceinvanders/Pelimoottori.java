/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

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

public class Pelimoottori extends JPanel implements Asetukset, Runnable {

    //private Dimension d;
    private Pelaaja pelaaja;
    private Kuti kuti;
    private ArrayList ufot;
    private int ufoX = 150;
    private int ufoY = 5;
    private int suunta = -1;
    private int tuhotut = 0;
    private boolean ingame = true;
    private final String UfoKuva = "SpaceInvanders/res/ufo.ong";
    private String peliLoppui = "Game Over";
    private Thread animator;

//    private class TAdapter extends KeyAdapter {
//
//        public void keyReleased(KeyEvent e) {
//            pelaaja.keyReleased(e);
//        }
//
//        public void keyPressed(KeyEvent e) {
//            pelaaja.keyPressed(e);
//
//            int x = pelaaja.getX();
//            int y = pelaaja.getY();
//
//            if (ingame) {
//                if (e.isAltDown()) {
//                    if (!kuti.nakyvissa()) {
//                        kuti = new Kuti(x, y);
//                    }
//                }
//            }
//        }
//    }
//    public Pelimoottori() {
//        addKeyListener(new TAdapter());
//        setFocusable(true);
//        d = new Dimension(RuudunLeveys, RuudunKorkeus);
//        setBackground(Color.black);
//
//        Logiikka();
//        setDoubleBuffered(true);
//    }
    public void addNotify() {
        super.addNotify();
        Logiikka();
    }
    
    public void Logiikka() {
        
        ufot = new ArrayList();
        
        ImageIcon ii = new ImageIcon(this.getClass().getResource(UfoKuva));
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Ufo ufo = new Ufo(ufoX + 18 * j, ufoY + 18 * j);
                ufo.setImage(ii.getImage());
                ufot.add(ufo);
                
            }
            
        }
        
        pelaaja = new Pelaaja();
        pelaaja.asetaKuva();
        kuti = new Kuti();
        
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }
    
    public void ufotKentalle(Graphics g) {
        Iterator it = ufot.iterator();
        
        while (it.hasNext()) {
            Ufo ufo = (Ufo) it.next();
            
            if (ufo.nakyvissa()) {
                g.drawImage(ufo.getSprite(), ufo.getX(), ufo.getY(), this);
            }
            
            if (ufo.kuolee()) {
                ufo.die();
            }
        }
    }
    
    public void pelaajaKentalle(Graphics g) {
        
        if (pelaaja.nakyvissa()) {
            g.drawImage(pelaaja.getSprite(), pelaaja.getX(), pelaaja.getY(), this);
        }
        if (pelaaja.kuolee()) {
            pelaaja.die();
            ingame = false;
        }
    }
    
    public void ammusKentalle(Graphics g) {
        if (kuti.nakyvissa()) {
            g.drawImage(kuti.getSprite(), kuti.getX(), kuti.getY(), this);
        }
    }
    
    public void ufotPommittaa(Graphics g) {
        Iterator i3 = ufot.iterator();
        
        while (i3.hasNext()) {
            Ufo ufo = (Ufo) i3.next();
            
            UfoKuti ufokuti = ufo.getUfoKuti();
            
            if (!ufokuti.kutiTuhoutuu()) {
                g.drawImage(ufokuti.getSprite(), ufokuti.getX(), ufokuti.getY(), this);
            }
        }
    }

    public void run() {
    }
}
