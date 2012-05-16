/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;
import javax.swing.JFrame;
/**
 *
 * @author Larppa
 */
public class spaceinv extends JFrame implements Asetukset{
    public spaceinv(){    
    add(new Kentta());
        setTitle("Space Invanders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(RuudunLeveys, RuudunKorkeus);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
}
}