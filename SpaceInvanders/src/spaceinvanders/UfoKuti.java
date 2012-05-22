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
public class UfoKuti extends Objekti {

    private final String ufokuti = "SpaceInvanders/res/ammus.png";
    private boolean tuhoutuu;
    
    public UfoKuti (int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon ii = new ImageIcon(this.getClass().getResource(ufokuti));
        setImage(ii.getImage());
    }
    
    public void setKutiTuhoutuu(boolean tuhoutuu) {
        this.tuhoutuu = tuhoutuu;
    }
    
    public boolean kutiTuhoutuu() {
        return tuhoutuu;
    }
}
