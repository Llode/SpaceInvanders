/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

<<<<<<< HEAD
import Kayttoliittymat.Grafiikka;
=======
import Kayttoliittymat.Kentta;
>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9
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
<<<<<<< HEAD
        new SpaceInvanders();
=======
        Kentta pelikentta = new Kentta();
        Spaceinv kayttoliittyma = new Spaceinv(pelikentta);
        kayttoliittyma.run();
>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9

    }
}
