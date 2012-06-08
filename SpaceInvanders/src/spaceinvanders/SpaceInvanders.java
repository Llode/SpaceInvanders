/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Kayttoliittymat.Kayttoliittyma;
import Pelimoottori.Asetukset;
import Pelimoottori.Pelimoottori;
import javax.swing.JFrame;

/**
 * Maini. Kaiken pitäisi startata täältä.
 * @author Lauri Lode
 */
public class SpaceInvanders extends JFrame implements Asetukset {

    /**
     * Peli-ikkunan asetukset.
     */
    Pelimoottori moottori = new Pelimoottori();
    public SpaceInvanders() {
        
        setTitle("Space Invanders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(RuudunLeveys, RuudunKorkeus);
        setLocationRelativeTo(null);
        setVisible(true);
        add(new Kayttoliittyma(moottori));
        setResizable(false);
    }

    public static void main(String[] args) {
        SpaceInvanders spaceInvanders = new SpaceInvanders();
    }
}
