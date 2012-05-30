/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Kayttoliittymat.Grafiikka;
import Kayttoliittymat.Spaceinv;
import Pelimoottori.Asetukset;
import Pelimoottori.Pelimoottori;
import javax.swing.JFrame;

/**
 *
 * @author lode
 */
public class SpaceInvanders extends JFrame implements Asetukset {

    /**
     * Peli-ikkunan asetukset.
     */
    Pelimoottori moottori = new Pelimoottori();
    public SpaceInvanders() {
        add(new Grafiikka(moottori));
        setTitle("Space Invanders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(RuudunLeveys, RuudunKorkeus);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new SpaceInvanders();
    }
}
