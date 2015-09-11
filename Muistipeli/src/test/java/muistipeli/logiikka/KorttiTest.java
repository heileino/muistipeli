/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class KorttiTest {

    Kortti kortti;

    public KorttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kortti = new Kortti(5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaTunnuksenOikein() {
        assertTrue(kortti.tunnus == 5);
    }

    @Test
    public void konstruktoriAsettaaNakyvyydenOikein() {
        assertTrue(!kortti.nakyy);
    }

    @Test
    public void naytaKuvapuoliMetodiKaantaaKuvapuolen() {
        kortti.naytaKuvapuoli();
        assertTrue(kortti.nakyy);
    }

    @Test
    public void naytaSelkapuoliMetodiKaantaaSelkapuolen() {
        kortti.naytaKuvapuoli();
        kortti.naytaSelkapuoli();
        assertFalse(kortti.nakyy);

    }
}
