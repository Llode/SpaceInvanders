/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Kayttoliittymat.Spaceinv;

/**
 *
 * @author lode
 */
public class SpaceInvanders implements Runnable{

    public static void main(String[] args) {
        Pelimoottori moottori = new Pelimoottori();
        Spaceinv kayttoliittyma = new Spaceinv(moottori);
        kayttoliittyma.run();
        Spaceinv spainv = new Spaceinv();
    }
}
