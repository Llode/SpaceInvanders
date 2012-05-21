/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spaceinvanders.Ufo;

/**
 *
 * @author Larppa
 */
public class UfoTest {
    
    Ufo ufo;
    double vertailutarkkuus = 0.0001;

    public UfoTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        ufo = new Ufo(50, 50);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void UfoLiikkuuKunSuuntaOnVasempaan(){
        ufo.liikkuu(-1);
        assertEquals(49, ufo.getX(), vertailutarkkuus);
    }
    @Test
    public void UfoLiikkuuKunSuuntaOnOikeaan(){
        ufo.liikkuu(1);
        assertEquals(51, ufo.getX(), vertailutarkkuus);
    }
}
