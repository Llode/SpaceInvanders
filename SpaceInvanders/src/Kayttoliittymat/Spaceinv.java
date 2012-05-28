/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import spaceinvanders.Pelimoottori;
import javax.swing.JFrame;
import Pelimoottori.Asetukset;

/**
 *
 * @author Larppa
 */
public class Spaceinv extends JFrame implements Asetukset, Runnable {

    /**
     * Peli-ikkunan asetukset.
     */
<<<<<<< HEAD
    public Spaceinv(Grafiikka kentta) {
        add(new Pelimoottori());
=======
    public Spaceinv(Kentta kentta) {
//        add(new Pelimoottori());
>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9
        setTitle("Space Invanders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(RuudunLeveys, RuudunKorkeus);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void run() {
    }
}