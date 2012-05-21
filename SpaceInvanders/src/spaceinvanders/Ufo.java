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
        
    }
    /**
     * Liikuttaa ufoa.
     * @param suunta negatiiviset luvut liikuttavat ufoa vasemmalle,
     * positiiviset oikealle.
     */
    public void liikkuu(int suunta){
        this.x += suunta;
    }
    
}