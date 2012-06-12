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
public class KutiTest {
    Kuti kuti;
    double vertailutarkkuus = 0.0001;
    
    public KutiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        kuti = new Kuti(50, 50);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ammuksenKeskitysTOimiiX(){
        assertEquals(65, kuti.getX(), vertailutarkkuus);
    }
    @Test
    public void ammuksenKeskitysToimiiY() {
        assertEquals(49, kuti.getY(), vertailutarkkuus);
    }
}
