/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Larppa
 */
public class Pelaaja extends Objekti implements Asetukset {

    private final int aloitus_x = 250;
    private final int aloitus_y = 100;
    private final String pelaaja = "SpaceInvanders/res/alus.png";
    private int leveys;

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

    public void asetaKuva() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(pelaaja));
        leveys = ii.getImage().getWidth(null);
        setImage(ii.getImage());
    }

    /**
     * Liikuttaa pelaajan alusta. Alus ei pääse koordinaatiston (ruudun)
     * ulkopuolelle.
     */
    public void liikkuu() {
        x += liike;
        if (x <= 2) {
            x = 2;
        }
        if (x >= RuudunLeveys - 2 * leveys) {
            x = RuudunLeveys - 2 * leveys;
        }
    }

    /**
     * Alus liikkuu, kun nuolinäppäimiä painetaan.
     *
     * @param e hakee painettavan näppäimen tunnuksen
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            liike = -2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            liike = 2;
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
            liike = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            liike = 0;
        }
    }
}
