/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import spaceinvanders.Pelimoottori;
import javax.swing.JFrame;
import spaceinvanders.Asetukset;

/**
 *
 * @author Larppa
 */
public class Spaceinv extends JFrame implements Asetukset {

    /**
     * Peli-ikkunan asetukset.
     */
    public Spaceinv() {
        add(new Pelimoottori());
        setTitle("Space Invanders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(RuudunLeveys, RuudunKorkeus);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}