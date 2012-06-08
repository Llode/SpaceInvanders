package Pelimoottori;

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


/**
 *
 * @author Larppa
 */
public class UfoTest {
    
    Ufo ufo;
    UfoKuti ufokuti;
    double vertailutarkkuus = 0.0001;

    public UfoTest() {

    }

    @BeforeClass
    public static void setUpClass() throws NullPointerException {
        
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
        ufo.ufoLiikkuu(-1);
        assertEquals(49, ufo.getX(), vertailutarkkuus);
    }
    @Test
    public void UfoLiikkuuKunSuuntaOnOikeaan(){
        ufo.ufoLiikkuu(1);
        assertEquals(51, ufo.getX(), vertailutarkkuus);
    }

}
