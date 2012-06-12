/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

/**
 * Ufot, joita pelaaja ampuu.
 * @author Larppa
 */
public class Ufo extends Objekti {

    UfoKuti ufokuti;

    
    public Ufo(){
        setVisible(false);
    }
    /**
     * Luo uuden ufon. Jokaisella ufolla on oma ammuksensa.
     * @param x ufon sijainti
     * @param y ufon sijainti
     */
    public Ufo(int x, int y) {
        this.x = x;
        this.y = y;
        ufokuti = new UfoKuti(x, y);
    }

    /**
     * Liikuttaa ufoa.
     * @param suunta negatiiviset luvut liikuttavat ufoa vasemmalle,
     * positiiviset oikealle.
     */
    public void ufoLiikkuu(int suunta) {
        this.x += suunta;
    }

    /**
     * Metodia kutsutaan, kun ufo ampuu pelaajaa.
     */
    public UfoKuti getUfoKuti() {
        ufokuti.setVisible(true);
        return ufokuti;
    }
}