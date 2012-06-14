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
public class UfoKutiTest {
    UfoKuti ufokuti;
    Double vertailutarkkuus = 0.0001;
    public UfoKutiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        ufokuti = new UfoKuti(50, 50);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void UfoKutiOnNakyvilla(){
        assertTrue(ufokuti.isVisible());
    }
    @Test public void UfoKutiKatoaaKuollessaan(){
        ufokuti.die();
        assertFalse(ufokuti.isVisible());
    }
    @Test
    public void UfokutiTuhoutuu(){
        ufokuti.setDestroyed(true);
        assertTrue(ufokuti.isDestroyed());
    }
}
