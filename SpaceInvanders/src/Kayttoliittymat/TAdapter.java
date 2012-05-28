/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Pelimoottori.Pelaaja;

/**
 *
 * @author Larppa
 */
public class TAdapter extends KeyAdapter {

    private Pelaaja pelaaja;
    private boolean ingame;

    /**
     * Alus liikkuu, kun nuolinäppäimiä painetaan.
     *
     * @param e hakee painettavan näppäimen tunnuksen
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pelaaja.liike = -2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaaja.liike = 2;
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
            pelaaja.liike = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaaja.liike = 0;
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
