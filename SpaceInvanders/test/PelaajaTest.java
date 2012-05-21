/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import spaceinvanders.Pelaaja;

/**
 *
 * @author lode
 */
public class PelaajaTest {
    Pelaaja pelaaja;
    int liike;
    double vertailutarkkuus = 0.0001;
    
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
        liike = 2;
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
    public void alusOnAloituskoordinaateissaX(){
        assertEquals(250, pelaaja.getX(), vertailutarkkuus);
    }
    @Test
    public void alusOnAloituskoordinaateissaY(){
        assertEquals(100, pelaaja.getY(), vertailutarkkuus);
    }
    @Test
    public void alusLiikkuuKunLiikeMuutuu(){
        pelaaja.liikkuu();
        assertEquals(252, pelaaja.getX(), vertailutarkkuus);
    }
}
