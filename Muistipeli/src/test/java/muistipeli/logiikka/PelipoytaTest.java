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
        assertEquals(16, poyta.getTaulukko().length);
    }
    
    @Test
    public void konstruktoriAsettaaKorttejaJaljellaOikein() {
        assertEquals(16, poyta.getKorttejaJaljella());
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
        assertTrue(poyta.onkoSama(1, 9));
    }
    
    @Test
    public void onkoSamaAntaaEripareillaEpatoden() {
        assertFalse(poyta.onkoSama(2, 3));
    }
    
    @Test
    public void paljastaKorttiToimiiOikein() {
        poyta.paljastaKortti(1);
        assertTrue(poyta.getTaulukko()[0].getNakyvyys());
    }
    
    @Test
    public void piilotaKorttiToimiiOikein() {
        poyta.paljastaKortti(16);
        poyta.piilotaKortti(16);
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
}
