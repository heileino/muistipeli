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
 * @author heikki
 */
public class PelipoytaTest {

    Pelipoyta poyta;

    public PelipoytaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        poyta = new Pelipoyta();
        poyta.taytaPoyta();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoOikeanKokoisenTaulukon() {
        assertEquals(20, poyta.getTaulukko().length);
    }

    @Test
    public void taulukonEnsimmainenAlkioTaytettyOikein() {
        assertEquals("kortti0", poyta.getTaulukko()[0].toString());
    }

    @Test
    public void taulukonViimeinenAlkioTaytettyOikein() {

        assertEquals("kortti9", poyta.getTaulukko()[19].toString());
    }

    @Test
    public void taulukonEnsimmaisellaJAYhdennellatoistaKortillaSamaTunnus() {

        assertTrue(poyta.getTaulukko()[0].toString().equals(poyta.getTaulukko()[10].toString()));
    }

    @Test
    public void taulukonKymmenennellaJaKahdennellakymmenellaKortillaSamaTunnus() {
        assertTrue(poyta.getTaulukko()[9].toString().equals(poyta.getTaulukko()[19].toString()));
    }

    @Test
    public void onkoSamaAntaaSamoillaKorteillaToden() {
        assertTrue(poyta.onkoSama(0, 10));
    }

    @Test
    public void onkoSamaAntaaEripareillaEpatoden() {
        assertFalse(poyta.onkoSama(4, 10));
    }

}
