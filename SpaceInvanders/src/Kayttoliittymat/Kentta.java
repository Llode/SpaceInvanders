/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import spaceinvanders.Pelimoottori;
import spaceinvanders.Asetukset;

/**
 *
 * @author Larppa
 */
public class Kentta extends JPanel implements Asetukset{
    private Dimension d;
    
    public Kentta(){
        setFocusable(true);
        d = new Dimension(RuudunLeveys, RuudunKorkeus);
        setBackground(Color.black);
        
        Pelimoottori.Logiikka();
    }
}
