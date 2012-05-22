/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import spaceinvanders.Pelimoottori;
import spaceinvanders.Asetukset;
import spaceinvanders.Kuti;
import spaceinvanders.Pelaaja;
import spaceinvanders.TAdapter;

/**
 *
 * @author Larppa
 */
public class Kentta extends JPanel implements Asetukset{
    private Dimension d;
    Pelimoottori moottori = new Pelimoottori();
    TAdapter TAdapter;

    

    
    public Kentta(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(RuudunLeveys, RuudunKorkeus);
        setBackground(Color.black);

        moottori.Logiikka();
        setDoubleBuffered(true);
        
    }
}
