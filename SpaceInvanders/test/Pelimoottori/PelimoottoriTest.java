package Pelimoottori;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import Kayttoliittymat.Grafiikka;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

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
    Grafiikka grafiikka;
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
        grafiikka = new Grafiikka(moottori);
        moottori.GameInit();
        pelaaja = new Pelaaja();
        ufo = new Ufo(10, 331);
        kuti = new Kuti();
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void SetUpLuoObjektit(){
//        moottori.SetUp();
//    assertTrue(pelaaja.isVisible());
//}
    @Test
    public void PelaajanOsumaTunnistusToimii() {
        ufokuti = new UfoKuti(255, 401);
        int ufokutiX = ufokuti.getX();
        int ufokutiY = ufokuti.getY();
        int pelaajaX = pelaaja.getX();
        int pelaajaY = pelaaja.getY();
        assertTrue(moottori.pelaajanOsumatunnistus(ufokutiX, pelaajaX, ufokutiY, pelaajaY));
        ufokuti = new UfoKuti(240, 399);
        ufokutiX = ufokuti.getX();
        ufokutiY = ufokuti.getY();
        pelaajaX = pelaaja.getX();
        pelaajaY = pelaaja.getY();
        assertFalse(moottori.pelaajanOsumatunnistus(ufokutiX, pelaajaX, ufokutiY, pelaajaY));
    }

    @Test
    public void UfonOsumatunnistusToimii() {
        kuti = new Kuti(50, 12);
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
        kuti = new Kuti(50, 50);
        assertTrue(kuti.isVisible());
        moottori.ufoTuhoutuuOsumasta(ufo, kuti);
        assertFalse(kuti.isVisible());
        kuti = new Kuti(100, 100);
        assertEquals(1, moottori.tuhotut);
        moottori.ufoTuhoutuuOsumasta(ufo, kuti);
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
        kuti = new Kuti(50, 12);
        moottori.pelaajanAmmusLiikkuu(kuti);
        moottori.pelaajanAmmusLiikkuu(kuti);

        assertTrue(kuti.isVisible());
        moottori.pelaajanAmmusLiikkuu(kuti);
//        assertEquals(4, kuti.getY(), vertailutarkkuus);
        assertFalse(kuti.isVisible());
    }

    @Test
    public void UfotSaavuttavatMaan() {
        moottori.ingame = true;
        moottori.ufotSaavuttavatMaan(ufo);
        assertFalse(moottori.ingame);
    }

    @Test
    public void ToimintaSykliTOimii() {
        assertTrue(moottori.ingame);
        moottori.tuhotut = 40;
        moottori.toiminta();
        assertFalse(moottori.ingame);
        
        moottori.ingame = true;
        moottori.toiminta();
        pelaaja.setKuolee(true);
        assertFalse(moottori.ingame);
        
        moottori.ingame = true;
        ufokuti = new UfoKuti(11, 11);
        pelaaja.setX(10);
        pelaaja.setY(10);
        moottori.toiminta();
        assertTrue(pelaaja.Kuoleeko());
        assertFalse(moottori.ingame);
    }
    @Test
    public void UfokutiLiikkuu(){
        ufokuti = ufo.getUfoKuti();
        assertEquals(ufo.getX(), ufokuti.getX(), vertailutarkkuus);
        assertEquals(ufo.getY(), ufokuti.getY(), vertailutarkkuus);
        moottori.ufonAmmusLiikkuu(ufokuti);
        assertEquals(ufo.getY()+1, ufokuti.getY(), vertailutarkkuus);
    }
    @Test
    public void PelaajaTUhoutuuOsumasta(){
        
    }
}
