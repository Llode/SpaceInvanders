/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Kayttoliittymat.Grafiikka;
import Kayttoliittymat.Spaceinv;
import Pelimoottori.Asetukset;
import javax.swing.JFrame;

/**
 *
 * @author lode
 */
public class SpaceInvanders extends JFrame implements Asetukset {

    /**
     * Peli-ikkunan asetukset.
     */
    public SpaceInvanders() {
        add(new Grafiikka());
        setTitle("Space Invanders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(RuudunLeveys, RuudunKorkeus);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        SpaceInvanders spaceInvanders = new SpaceInvanders();
    }
}
