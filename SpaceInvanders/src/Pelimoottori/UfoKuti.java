/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

import Pelimoottori.Objekti;
import javax.swing.ImageIcon;
import Kayttoliittymat.Grafiikka;

/**
 * Ufojen ampumat ammukset.
 * @author Larppa
 */
public class UfoKuti extends Objekti {

    private final String ufokuti = "/res/ammus.png";
    private boolean tuhoutuu;
/**
     * Luo ufon ampuman ammuksen.
     * @param x koordinaatit, joista ammus lähtee
     * @param y koordinaatit, joista ammus lähtee
     */

    
    public UfoKuti(int x, int y) {
        this.x = x;
        this.y = y;
//        ImageIcon ii = new ImageIcon(this.getClass().getResource(ufokuti));
//        setImage(ii.getImage());
        
    }
/**
     * Ammuttu ammus tuhoutuu, kun se osuu johonkin. Käytetään osumantunnistukseen.
     */
    public void setKutiTuhoutuu(boolean tuhoutuu) {
        this.tuhoutuu = tuhoutuu;
    }
/**
     * Kertoo, onko ammus tuhoutunut.
     */
    public boolean kutiTuhoutuu() {
        return tuhoutuu;
    }
}
