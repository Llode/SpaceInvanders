/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

/**
 * Pelaajan alus
 *
 * @author Larppa
 */
public class Pelaaja extends Objekti implements Asetukset {

    private final int aloitus_x = 250;
    private final int aloitus_y = 400;
    private final String pelaaja = "/res/alus.png";
    public int leveys = PelaajanLeveys;
    Kuti kuti;

/**
 * Luo pelaajan aluksen.
 */
    public Pelaaja() {
        setX(aloitus_x);
        setY(aloitus_y);
    }

    /**
     * Liikuttaa pelaajaa sekä estää sitä pääsemästä koordinaatiston (ruudun)
     * ulkopuolelle.
     */
    public void pelaajaLiikkuu() {
        x += liike;
        if (x <= 2) {
            x = 2;
        }
        if (x >= RuudunLeveys - (2 * leveys)) {
            x = RuudunLeveys - (2 * leveys);
        }
    }

    /**
     * Metodia kutsutaan, kun pelaaja ampuu.
     */
//    public void Ammu() {
//        int kutiX = this.x;
//        int kutiY = this.y;
//        if (!kuti.isVisible()) {
//            kuti = new Kuti(kutiX, kutiY);
//        }
//    }

    /**
     * pelaaja liikesuunta muuttuu vasempaan.
     */
    public void pelaajaLiikkuuVasempaan() {
        liike = -2;
    }

    /**
     * Pelaajan liikesuunta muuttuu oikeaan
     */
    public void pelaajaLiikkuuOikeaan() {
        liike = 2;
    }

    /**
     * Pelaaja pysähtyy.
     */
    public void pelaajaPysahtyy() {
        liike = 0;
    }
}