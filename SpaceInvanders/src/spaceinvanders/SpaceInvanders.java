/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvanders;

import Kayttoliittymat.Kentta;
import Kayttoliittymat.Spaceinv;

/**
 *
 * @author lode
 */
public class SpaceInvanders {


    public static void main(String[] args) {
        Kentta pelikentta = new Kentta();
        Spaceinv kayttoliittyma = new Spaceinv(pelikentta);
        kayttoliittyma.run();

    }
}
