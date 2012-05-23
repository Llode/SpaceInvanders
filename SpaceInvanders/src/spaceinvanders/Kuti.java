/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import javax.swing.ImageIcon;

public class Kuti extends Objekti {

    private String ammus = "/res/ammus.png";
    private final int keskitysLeveys = 12;
    private final int keskitysKorkeus = 1;

    public Kuti() {
    }
/**
     * Luo ammuksen. 
     * @param keskitysKorkeus Ammus lähtee pelaajan aluksen keskeltä
     * @param keskitysLeveys Ammus lähtee pelaajan aluksen keskeltä
     */
    public Kuti(int x, int y) {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(ammus));
        setImage(ii.getImage());
        setX(x + keskitysLeveys);
        setY(y - keskitysKorkeus);

    }
}
