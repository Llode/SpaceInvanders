/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import spaceinvanders.Asetukset;

public class Kentta extends JPanel implements Asetukset, Runnable {

    private Dimension d;

    public Kentta() {
        setFocusable(true);
        d = new Dimension(RuudunLeveys, RuudunKorkeus);
        setBackground(Color.black);

    }

    public void run() {
    }
}
