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
        Kortti kortti1 = poyta.getTaulukko()[0];
        Kortti kortti2 = poyta.getTaulukko()[15];
        poyta.lisaaKortitLoytyneeksi(kortti1, kortti2);
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
        assertTrue(poyta.getTaulukko()[0].nakyykoKuvapuoli());
    }

    @Test
    public void piilotaKorttiToimiiOikein() {
        poyta.paljastaKortti(15);
        poyta.piilotaKortti(15);
        assertFalse(poyta.getTaulukko()[15].nakyykoKuvapuoli());
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
        Kortti testikortti1 = poyta.getTaulukko()[15];
        Kortti testikortti2 = poyta.getTaulukko()[0];
        poyta.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertTrue(poyta.onkoJoLoydetty(testikortti1));
    }

    @Test
    public void lisaaKorttiLoytyneisiinToimiiOikein() {
        Kortti testikortti1 = poyta.getTaulukko()[7];
        Kortti testikortti2 = poyta.getTaulukko()[5];
        poyta.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertEquals(testikortti1, poyta.getLoydetytKortit().get(0));
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
    public void tyhjaaValitutIndeksitToimiiOikein() {
        poyta.lisaaTaulukonIndeksiValittuihin(0);
        poyta.lisaaTaulukonIndeksiValittuihin(1);
        poyta.tyhjaaValitutIndeksit();
        assertTrue(poyta.getValitutIndeksit().isEmpty());
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

    @Test
    public void onkoKorttiValittavissaToimiiOikein() {
        Kortti kortti1 = poyta.getKorttiTaulukosta(0);
        kortti1.naytaKuvapuoli();
        assertFalse(poyta.onkoKorttiValittavissa(0));
    }

    @Test
    public void valitseKorttiToimiiOikein() {
        poyta.valitseKortti(4);
        assertTrue(poyta.getValitutIndeksit().get(0) == 4 && poyta.getTaulukko()[4].nakyykoKuvapuoli());
    }

    @Test
    public void montakoValittuToimiiOikeinKunEiValittuYhtaan() {
        assertTrue(poyta.montakoValittu() == 0);
    }

    @Test
    public void montakoValittuToimiiOikeinKunValittuYksi() {
        poyta.valitseKortti(0);
        assertTrue(poyta.montakoValittu() == 1);
    }

    @Test
    public void montakoValittuToimiiOikeinKunValittuKaksi() {
        poyta.valitseKortti(0);
        poyta.valitseKortti(15);
        assertTrue(poyta.montakoValittu() == 2);
    }

    @Test
    public void getYritystenMaaraLukunaToimiiOikein() {
        poyta.lisaaValintayritys();
        assertTrue(poyta.getYritystenMaaraLukuna() == 1);
    }

    @Test
    public void lisaaValintayritysToimiiOikeinYhdellaYrityksella() {
        poyta.lisaaValintayritys();
        assertTrue(poyta.getYritystenMaaraLukuna() == 1);
    }

    @Test
    public void lisaaValintayritysToimiiOikeinKolmellakymmenellaYrityksella() {
        for (int i = 0; i < 30; i++) {
            poyta.lisaaValintayritys();
        }
        assertTrue(poyta.getYritystenMaaraLukuna() == 30);
    }

    @Test
    public void getYritystenMaaraTekstinaToimiiOikeinAlussa() {
        assertEquals("Yrityksiä: 0", poyta.getYritystenMaaraTekstina());
    }

    @Test
    public void getYritystenMaaraTekstinaToimiiOikeinKahdella() {
        poyta.lisaaValintayritys();
        poyta.lisaaValintayritys();

        assertEquals("Yrityksiä: 2", poyta.getYritystenMaaraTekstina());
    }

    @Test
    public void jatketaankoPeliaToimiiJosTulisiJatkaa() {

        poyta.vahennaKorttejaJaljella();
        assertTrue(poyta.jatketaankoPelia());
    }

    @Test
    public void jatketaankoPeliaToimiiJosEiTulisiJatkaa() {
        while (poyta.getKorttejaJaljella() > 2) {
            poyta.vahennaKorttejaJaljella();
        }
        assertFalse(poyta.jatketaankoPelia());
    }

    @Test
    public void loytyikoPariPalauttaaTodenKunPariLoytyy() {

        poyta.lisaaTaulukonIndeksiValittuihin(0);
        poyta.lisaaTaulukonIndeksiValittuihin(8);

        assertTrue(poyta.loytyikoPari());
    }

    @Test
    public void loytyikoPariPalautteeEpatodenKunPariaEiLoydy() {
        poyta.lisaaTaulukonIndeksiValittuihin(1);
        poyta.lisaaTaulukonIndeksiValittuihin(10);

        assertFalse(poyta.loytyikoPari());
    }
}
