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
public class PelaajaTest {

    Pelaaja pelaaja;

    public PelaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pelaaja = new Pelaaja();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kostruktoriAsettaaYritystenMaaranOikein() {
        assertEquals(0, pelaaja.getYritykset());
    }

    @Test
    public void lisaaYritysToimiiOikein() {
        pelaaja.lisaaYritys();
        assertEquals(1, pelaaja.getYritykset());
    }

    @Test
    public void getYrityksetTekstinaToimiiOikein() {
        assertEquals("Yrityksiä: " + pelaaja.getYritykset(), pelaaja.getYrityksetTekstina());
    }
}
