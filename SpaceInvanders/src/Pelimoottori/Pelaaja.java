/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

import Pelimoottori.Asetukset;
import Pelimoottori.Objekti;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import Kayttoliittymat.Grafiikka;

/**
 * Pelaajan alus
 * @author Larppa
 */
public class Pelaaja extends Objekti implements Asetukset {

    private final int aloitus_x = 250;
    private final int aloitus_y = 100;
    private final String pelaaja = "/res/alus.png";
    Grafiikka grafiikka;
    public int leveys = PelaajanLeveys;
    Kuti kuti = new Kuti();
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

    }

    /**
     * Liikuttaa pelaajaa sekä estää sitä pääsemästä koordinaatiston (ruudun)
     * ulkopuolelle.
     */

    
    public void pelaajaLiikkuu() {
        x += liike;
        if (x <= 2) {
            x = 2;
        }
        if (x >= RuudunLeveys - (2 * leveys)) {
            x = RuudunLeveys - (2 * leveys);
        }
    }

    /**
     * Metodia kutsutaan, kun pelaaja ampuu.
     */
    public boolean pelaajaAmpuu() {
        if (!kuti.isVisible()) {
            kuti = new Kuti(x, y);
            return true;
        } else {
            return false;
        }
    }

    /**
     * pelaaja liikesuunta muuttuu vasempaan.
     */
    public void pelaajaLiikkuuVasempaan() {
        liike = -2;
    }

    /**
     * Pelaajan liikesuunta muuttuu oikeaan
     */
    public void pelaajaLiikkuuOikeaan() {
        liike = 2;
    }
/**
     * Pelaaja pysähtyy.
     */
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
            pelaajaLiikkuuOikeaan();
        }
    }

    /**
     * Alus pysähtyy, kun liikenapeista päästetään irti.
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pelaajaPysahtyy();
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaajaPysahtyy();
        }
    }
}
