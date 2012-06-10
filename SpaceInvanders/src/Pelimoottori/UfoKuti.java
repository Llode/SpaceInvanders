/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

/**
 * Ufojen ampumat ammukset.
 *
 * @author Larppa
 */
public class UfoKuti extends Objekti {

    private boolean tuhoutuu;

    public UfoKuti(){
        
    }
    /**
     * Luo ufon ampuman ammuksen.
     *
     * @param x koordinaatit, joista ammus lähtee
     * @param y koordinaatit, joista ammus lähtee
     */
    public UfoKuti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Ammuttu ammus tuhoutuu, kun se osuu johonkin. Käytetään
     * osumantunnistukseen.
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
