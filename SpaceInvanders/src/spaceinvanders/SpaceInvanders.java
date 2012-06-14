/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Kayttoliittymat.GameMain;
import Pelimoottori.Asetukset;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Maini. Kaiken pitäisi startata täältä.
 *
 * @author Lauri Lode
 */
public class SpaceInvanders extends JFrame implements Asetukset {

    /**
     * Peli-ikkunan asetukset.
     */

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameMain gameMain = new GameMain();
            }
        });
    }
}
