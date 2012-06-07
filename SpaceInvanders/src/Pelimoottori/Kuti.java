/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelimoottori;

/**
 * Pelaajan ammus.
 *
 * @author Larppa
 */
public class Kuti extends Objekti {

    private final int keskitysLeveys = 15;
    private final int keskitysKorkeus = 1;

    public Kuti() {
    }

    /**
     * Luo ammuksen.
     *
     * @param keskitysKorkeus Ammus lähtee pelaajan aluksen keskeltä
     * @param keskitysLeveys Ammus lähtee pelaajan aluksen keskeltä
     */
    public Kuti(int x, int y) {
        setX(x + keskitysLeveys);
        setY(y - keskitysKorkeus);
    }
}
