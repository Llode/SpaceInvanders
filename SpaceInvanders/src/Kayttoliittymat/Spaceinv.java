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
    public Spaceinv(Grafiikka kentta) {
        add(new Pelimoottori());

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