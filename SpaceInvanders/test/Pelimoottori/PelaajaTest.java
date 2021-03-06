package Pelimoottori;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;



/**
 *
 * @author lode
 */
public class PelaajaTest {

    Pelaaja pelaaja;
    int liike;
    double vertailutarkkuus = 0.0001;
    Kuti kuti;

    public PelaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        pelaaja = new Pelaaja();
        kuti = new Kuti();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void alusOnAloituskoordinaateissaX() {
        assertEquals(250, pelaaja.getX(), vertailutarkkuus);
    }

    @Test
    public void alusOnAloituskoordinaateissaY() {
        assertEquals(400, pelaaja.getY(), vertailutarkkuus);
    }


    @Test
    public void pelaajaEiPaaseUlosReunoista(){
        pelaaja.liike = -250;
        pelaaja.pelaajaLiikkuu();
        assertEquals(2, pelaaja.getX(), vertailutarkkuus);
        pelaaja.liike = 500;
        pelaaja.pelaajaLiikkuu();
        assertEquals(440, pelaaja.getX(), vertailutarkkuus);
    }

}
