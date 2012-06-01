/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pelimoottori.Pelaaja;
import Pelimoottori.Ufo;
import Pelimoottori.UfoKuti;
import Pelimoottori.Pelimoottori;
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
    public void SetUpLuoObjektit(){
        moottori.SetUp();
    assertTrue(pelaaja.isVisible());
}
}
