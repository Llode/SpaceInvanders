/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;
import javax.swing.ImageIcon;
/**
 *
 * @author Larppa
 */
public class Ufo extends Objekti {

    private UfoKuti kuti;
    private final String ammus = "SpaceInvanders/res/kuti.png";
    
    public Ufo(int x, int y){
        this.x = x;
        this.y = y;
        
        kuti = new UfoKuti(x, y);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(ammus));
        setImage(ii.getImage());
    }
    /**
     * Liikuttaa ufoa.
     * @param suunta negatiiviset luvut liikuttavat ufoa vasemmalle,
     * positiiviset oikealle.
     */
    public void liikkuu(int suunta){
        this.x += suunta;
    }
    /**
     * Metodia kutsutaan, kun ufo ampuu pelaajaa.
     */
    public UfoKuti getUfoKuti() {
        return kuti;
    }
    
}