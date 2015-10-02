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
        assertEquals(16, poyta.getTaulukko().length);
    }

    @Test
    public void taulukonEnsimmainenAlkioTaytettyOikein() {
        assertEquals("kortti_1", poyta.getTaulukko()[0].toString());
    }

    @Test
    public void taulukonViimeinenAlkioTaytettyOikein() {
        assertEquals("kortti_8", poyta.getTaulukko()[15].toString());
    }

    @Test
    public void taulukonEnsimmaisellaJAYhdeksannellaKortillaSamaTunnus() {
        assertTrue(poyta.getTaulukko()[0].toString().equals(poyta.getTaulukko()[8].toString()));
    }

    @Test
    public void taulukonKahdeksannellaJaKuudennellatoistaKortillaSamaTunnus() {
        assertTrue(poyta.getTaulukko()[7].toString().equals(poyta.getTaulukko()[15].toString()));
    }

    @Test
    public void onkoSamaAntaaSamoillaKorteillaToden() {
        assertTrue(poyta.onkoSama(0, 8));
    }

    @Test
    public void onkoSamaAntaaEripareillaEpatoden() {
        assertFalse(poyta.onkoSama(2, 3));
    }

    @Test
    public void onkoSamaKorttiAntaaSamoillaKorteillaToden() {
        Kortti kortti1 = poyta.getTaulukko()[2];
        Kortti kortti2 = poyta.getTaulukko()[10];

        assertTrue(poyta.onkoSamaKortti(kortti1, kortti2));
    }

    @Test
    public void onkoSamaKorttiAntaaEripareillaEpätoden() {
        Kortti kortti1 = poyta.getTaulukko()[0];
        Kortti kortti2 = poyta.getTaulukko()[1];
        assertFalse(poyta.onkoSamaKortti(kortti1, kortti2));
    }

    @Test
    public void paljastaKorttiToimiiOikein() {
        poyta.paljastaKortti(0);
        assertTrue(poyta.getTaulukko()[0].nakyykoKuvapuoli());
    }

    @Test
    public void piilotaKorttiToimiiOikein() {
        poyta.paljastaKortti(15);
        poyta.piilotaKortti(15);
        assertFalse(poyta.getTaulukko()[15].nakyykoKuvapuoli());
    }

    @Test
    public void getKortinIndeksiToimiiOikeinKunKorttiLoytyy() {
        Kortti kortti = poyta.getTaulukko()[2];
        assertTrue(poyta.getKortinIndeksi(kortti) == 2);
    }

    @Test
    public void getKortinIndeksiToimiiOikeinKunEiLoydaMitaan() {
        assertTrue(poyta.getKortinIndeksi(null) == -1);
    }

}
