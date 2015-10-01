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
    public void konstruktoriAsettaaKorttejaJaljellaOikein() {
        assertEquals(16, poyta.getKorttejaJaljella());
    }

    @Test
    public void konstruktoriLuoUudenLoydetytKortitListan() {
        Kortti kortti = poyta.getTaulukko()[0];
        poyta.lisaaKorttiLoytyneisiin(kortti);
        assertEquals("kortti_1", poyta.getLoydetytKortit().get(0).toString());
    }

    @Test
    public void konstruktoriLuoUudenValitutIndeksitListan() {
        poyta.lisaaTaulukonIndeksiValittuihin(2);
        assertEquals((Integer) 2, poyta.getValitutIndeksit().get(0));
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
        assertTrue(poyta.getTaulukko()[0].getNakyvyys());
    }

    @Test
    public void piilotaKorttiToimiiOikein() {
        poyta.paljastaKortti(15);
        poyta.piilotaKortti(15);
        assertFalse(poyta.getTaulukko()[15].getNakyvyys());
    }

    @Test
    public void vahennaKorttejaJaljellaToimiiOikein() {
        poyta.vahennaKorttejaJaljella();
        assertEquals(14, poyta.getKorttejaJaljella());
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiEiOleLoytynyt() {
        Kortti testikortti = poyta.getTaulukko()[0];
        assertFalse(poyta.onkoJoLoydetty(testikortti));
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiOnLoytynyt() {
        Kortti testikortti = poyta.getTaulukko()[15];
        poyta.lisaaKorttiLoytyneisiin(testikortti);
        assertTrue(poyta.onkoJoLoydetty(testikortti));
    }

    @Test
    public void lisaaKorttiLoytyneisiinToimiiOikein() {
        Kortti testikortti = poyta.getTaulukko()[7];
        poyta.lisaaKorttiLoytyneisiin(testikortti);
        assertEquals(testikortti, poyta.getLoydetytKortit().get(0));
    }

    @Test
    public void alustaKorttejaJaljellaToimiiOikein() {
        poyta.vahennaKorttejaJaljella();
        poyta.alustaKorttejaJaljella();
        assertEquals(16, poyta.getKorttejaJaljella());
    }

    @Test
    public void getKorttejaJaljellaTekstinaToimiiOikein() {
        assertEquals("Kortteja jäljellä: 16", poyta.getKorttejaJaljellaTekstina());
    }
    
    @Test
    public void tyhjaaValitutIndeksitToimiiOikein(){        
        poyta.lisaaTaulukonIndeksiValittuihin(0);
        poyta.lisaaTaulukonIndeksiValittuihin(1);
        poyta.tyhjaaValitutIndeksit();
        assertTrue(poyta.getValitutIndeksit().isEmpty());
    }
    
}
