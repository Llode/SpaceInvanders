/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.Pelaaja;
import Pelimoottori.Pelimoottori;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Tulkkaa näppäimistöä ja pelaajan inputia.
 * @author Larppa
 */
public class TAdapter extends KeyAdapter {

    Pelaaja pelaaja;
    Pelimoottori moottori;

    /**
     * Alus liikkuu, kun nuolinäppäimiä painetaan.
     *
     * @param e hakee painettavan näppäimen tunnuksen
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pelaaja.pelaajaLiikkuuVasempaan();
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaaja.pelaajaLiikkuuOikeaan();
        }
        if (e.isAltDown()) {
            moottori.pelaajaAmpuu();
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
            pelaaja.pelaajaPysahtyy();
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaaja.pelaajaPysahtyy();
        }

//    public void keyReleased(KeyEvent e) {
//        pelaaja.keyReleased(e);
//    }
//
//    public void keyPressed(KeyEvent e) {
//        pelaaja.keyPressed(e);
//
//        int x = pelaaja.getX();
//        int y = pelaaja.getY();
//
//        if (ingame) {
//            if (e.isAltDown()) {
//                pelaaja.ammu();
//            }
//        }
//    }
    }
}
