/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

import Pelimoottori.Asetukset;
import Pelimoottori.Objekti;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Larppa
 */
public class Pelaaja extends Objekti implements Asetukset {

    private final int aloitus_x = 250;
    private final int aloitus_y = 100;
    private final String pelaaja = "/res/alus.png";
    public int leveys;
    Kuti kuti;
    private boolean ingame;

    /**
     * Luo pelaajan aluksen kuvan
     *
     * @param aloitus_x pelaajan aloituspiste x-akselilla
     * @param aloitus_y pelaajan aloituspiste y-akselilla
     * @param leveys pelaajan aluksen leveys
     */
    public Pelaaja() {
        setX(aloitus_x);
        setY(aloitus_y);

//        ImageIcon ii = new ImageIcon(this.getClass().getResource(pelaaja));
//        leveys = ii.getImage().getWidth(null);
//        setImage(ii.getImage());
    }

    public void asetaKuva() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(pelaaja));
        leveys = ii.getImage().getWidth(null);
        setImage(ii.getImage());
    }

    /**
     * Liikuttaa pelaajan alusta. Alus ei pääse koordinaatiston (ruudun)
     * ulkopuolelle.
     */
    public void pelaajaLiikkuu() {
        x += liike;
        if (x <= 2) {
            x = 2;
        }
        if (x >= RuudunLeveys - 2 * leveys) {
            x = RuudunLeveys - 2 * leveys;
        }
    }

    /**
     * Metodia kutsutaan, kun pelaaja ampuu.
     */
    public boolean pelaajaAmpuu() {
        if (kuti.isVisible() == false) {
            kuti = new Kuti(x, y);
            return true;
        } else {
            return false;
        }
    }

<<<<<<< HEAD:SpaceInvanders/src/Pelimoottori/Pelaaja.java
    public void pelaajaLiikkuuVasempaan() {
        liike = -2;
    }

    public void pelaajaLiikkaaOikeaan() {
        liike = 2;
    }

    public void pelaajaPysahtyy() {
        liike = 0;
    }

    /**
     * Alus liikkuu, kun nuolinäppäimiä painetaan.
     *
     * @param e hakee painettavan näppäimen tunnuksen
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pelaajaLiikkuuVasempaan();
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaajaLiikkaaOikeaan();
        }
=======
//    /**
//     * Alus liikkuu, kun nuolinäppäimiä painetaan.
//     *
//     * @param e hakee painettavan näppäimen tunnuksen
//     */
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if (key == KeyEvent.VK_LEFT) {
//            liike = -2;
//        }
//        if (key == KeyEvent.VK_RIGHT) {
//            liike = 2;
//        }
//    }
//
//    /**
//     * Alus pysähtyy, kun liikenapeista päästetään irti.
//     *
//     * @param e
//     */
//    public void keyReleased(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if (key == KeyEvent.VK_LEFT) {
//            liike = 0;
//        }
//        if (key == KeyEvent.VK_RIGHT) {
//            liike = 0;
//        }
    
        public void keyReleased(KeyEvent e) {
        keyReleased(e);
>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9:SpaceInvanders/src/spaceinvanders/Pelaaja.java
    }

    public void keyPressed(KeyEvent e) {
        keyPressed(e);

<<<<<<< HEAD:SpaceInvanders/src/Pelimoottori/Pelaaja.java
        if (key == KeyEvent.VK_LEFT) {
            pelaajaPysahtyy();
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaajaPysahtyy();
=======
        int x = getX();
        int y = getY();

        if (ingame) {
            if (e.isAltDown()) {
                ammu();
            }
>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9:SpaceInvanders/src/spaceinvanders/Pelaaja.java
        }
    }
    }

