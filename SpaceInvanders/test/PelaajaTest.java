/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import Pelimoottori.Kuti;
import Pelimoottori.Pelaaja;

/**
 *
 * @author lode
 */
public class PelaajaTest {

    Pelaaja pelaaja;
    int liike;
    double vertailutarkkuus = 0.0001;
    Kuti kuti;
<<<<<<< HEAD
    
=======

>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void alusOnAloituskoordinaateissaX() {
        assertEquals(250, pelaaja.getX(), vertailutarkkuus);
    }

    @Test
    public void alusOnAloituskoordinaateissaY() {
        assertEquals(100, pelaaja.getY(), vertailutarkkuus);
    }

    @Test
<<<<<<< HEAD
    public void pelaajaAmpuu(){
        kuti.die();
        pelaaja.pelaajaAmpuu();
//        assertEquals(pelaaja.getX()+12, kuti.getX(), vertailutarkkuus);
        assertTrue(pelaaja.pelaajaAmpuu());
=======
    public void pelaajaAmpuu() {
        pelaaja.ammu();
        assertTrue(kuti.isVisible()==true);
>>>>>>> fa9c1da41c835fba8b47cb5a4af911f0bd1455f9
    }
//    @Test
//    public void alusLiikkuuKunLiikeMuutuu(){
//        pelaaja.liiku();
//        assertEquals(252, pelaaja.getX(), vertailutarkkuus);
//    }
}
