package Pelimoottori;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pelimoottori.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Toolkit;

/**
 *
 * @author Larppa
 */
public class PelimoottoriTest {

    Pelimoottori moottori;
    Pelaaja pelaaja;
    Ufo ufo;
    UfoKuti ufokuti;
    Kuti kuti;
    double vertailutarkkuus = 0.0001;

    public PelimoottoriTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        moottori = new Pelimoottori();
        pelaaja = new Pelaaja();
        kuti = new Kuti(50, 12);
        ufo = new Ufo(10, 375);
        ufokuti = new UfoKuti(279, 114);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    @Test
//    public void SetUpLuoObjektit(){
//        moottori.SetUp();
//    assertTrue(pelaaja.isVisible());
//}
    @Test
    public void PelaajanOsumaTunnistusToimii() {
        int ufokutiX = ufokuti.getX();
        int ufokutiY = ufokuti.getY();
        int pelaajaX = pelaaja.getX();
        int pelaajaY = pelaaja.getY();
        assertTrue(moottori.pelaajanOsumatunnistus(ufokutiX, pelaajaX, ufokutiY, pelaajaY));
    }

    @Test
    public void UfonOsumatunnistusToimii() {
        int kutiX = kuti.getX();
        int kutiY = kuti.getY();
        int ufoX = ufo.getX();
        int ufoY = ufo.getY();
        assertFalse(moottori.ufonOsumaTunnistus(kutiX, ufoX, kutiY, ufoY));
        kutiX = 100;
        kutiY = 100;
        ufoX = 95;
        ufoY = 95;
        assertTrue(moottori.ufonOsumaTunnistus(kutiX, ufoX, kutiY, ufoY));
    }

    @Test
    public void UfoTuhoutuuKunSaaOsuman() {
        assertTrue(kuti.isVisible());
        moottori.ufoTuhoutuuOsumasta(ufo);
        assertTrue(kuti.isVisible());
//        kuti = new Kuti(100, 100);
        assertEquals(1, moottori.tuhotut);
        moottori.ufoTuhoutuuOsumasta(ufo);
        assertEquals(2, moottori.tuhotut);
        assertFalse(kuti.isVisible());
    }

    @Test
    public void UfoKatoaaKuollessa() {
        ufo.setKuolee(true);
        moottori.ufoKatoaaKuollessaan(ufo);
        assertFalse(ufo.isVisible());
    }

    @Test
    public void PelaajanAmmusKatoaaRuudunUlkopuolella() {
        moottori.pelaajaAmpuu();
        moottori.pelaajanAmmusLiikkuu();
        moottori.pelaajanAmmusLiikkuu();
        assertTrue(kuti.isVisible());
        moottori.pelaajanAmmusLiikkuu();
        assertFalse(kuti.isVisible());
    }
    @Test
    public void UfotOsuuMaaha(){

    }
        @Test
    public void pelaajaEiAmmuKunAmmusKentalla(){
        assertTrue(kuti.isVisible());
        kuti.die();
        assertFalse(kuti.isVisible());
        moottori.pelaajaAmpuu();
        //pelaajan koordinaatti oikein:
        assertEquals(250, pelaaja.getX(), vertailutarkkuus);
        //kuti ei saa oikeita koordinaatteja.
        assertEquals(pelaaja.getX() + 15, kuti.getX(), vertailutarkkuus);

    }
}
