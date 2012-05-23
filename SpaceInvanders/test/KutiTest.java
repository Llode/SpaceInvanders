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
import spaceinvanders.Kuti;

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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void ammuksenKeskitysTOimiiX(){
        assertEquals(62, kuti.getX(), vertailutarkkuus);
    }
    @Test
    public void ammuksenKeskitysToimiiY() {
        assertEquals(49, kuti.getY(), vertailutarkkuus);
    }
}
