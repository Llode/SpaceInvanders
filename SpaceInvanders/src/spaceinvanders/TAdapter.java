/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Pelimoottori.Pelaaja;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Larppa
 */
public class TAdapter extends KeyAdapter {

    Pelaaja pelaaja;
    boolean ingame;

    public void keyReleased(KeyEvent e) {
        pelaaja.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        pelaaja.keyPressed(e);

        int x = pelaaja.getX();
        int y = pelaaja.getY();

        if (ingame) {
            if (e.isAltDown()) {
                pelaaja.pelaajaAmpuu();
            }
        }
    }
}
