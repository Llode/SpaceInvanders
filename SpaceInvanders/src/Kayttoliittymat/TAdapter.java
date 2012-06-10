/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittymat;

import Pelimoottori.Kuti;
import Pelimoottori.Pelaaja;
import Pelimoottori.Pelimoottori;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Tulkkaa näppäimistöä ja pelaajan inputia.
 * @author Larppa
 */
public class TAdapter extends KeyAdapter {

    Pelaaja pelaaja;
    Pelimoottori moottori;
    Kuti kuti;
    
    public TAdapter(){
        pelaaja = new Pelaaja();
        moottori = new Pelimoottori();
        kuti = new Kuti();
    }

    /**
     * Alus liikkuu, kun nuolinäppäimiä painetaan.
     *
     * @param e hakee painettavan näppäimen tunnuksen
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pelaaja.pelaajaLiikkuuVasempaan();
            System.out.println("vasenoaab");
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaaja.pelaajaLiikkuuOikeaan();
            System.out.println("oikeaan");
        }
        if (e.isAltDown()) {
            if(kuti.isVisible()){
                kuti = new Kuti(pelaaja.getX(), pelaaja.getY());
            }
            System.out.println("piupiut");
        }
    }

    /**
     * Alus pysähtyy, kun liikenapeista päästetään irti.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            pelaaja.pelaajaPysahtyy();
            System.out.println("HALT");
        }
        if (key == KeyEvent.VK_RIGHT) {
            pelaaja.pelaajaPysahtyy();
            System.out.println("HAMMERZEIT");
        }

//    public void keyReleased(KeyEvent e) {
//        pelaaja.keyReleased(e);
//    }
//
//    public void keyPressed(KeyEvent e) {
//        pelaaja.keyPressed(e);
//
//        int x = pelaaja.getX();
//        int y = pelaaja.getY();
//
//        if (ingame) {
//            if (e.isAltDown()) {
//                pelaaja.ammu();
//            }
//        }
//    }
    }
}
