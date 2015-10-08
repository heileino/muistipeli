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
 * @author Heikki
 */
public class PelimoottoriTest {

    Pelimoottori pelimoottori;
    Pelipoyta poyta;

    public PelimoottoriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pelimoottori = new Pelimoottori();
        poyta = pelimoottori.getPelipoyta();
        poyta.taytaPoyta();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void konstruktoriAsettaaKorttejaJaljellaOikein() {
        assertEquals(16, pelimoottori.getKorttejaJaljella());
    }

    @Test
    public void konstruktoriLuoUudenLoydetytKortitListan() {
        Kortti kortti1 = poyta.getTaulukko()[0];
        Kortti kortti2 = poyta.getTaulukko()[15];
        pelimoottori.lisaaKortitLoytyneeksi(kortti1, kortti2);
        assertEquals("kortti_1", pelimoottori.getLoydetytKortit().get(0).toString());
    }

    @Test
    public void konstruktoriLuoUudenValitutIndeksitListan() {
        pelimoottori.lisaaValittuihin(2);
        assertEquals((Integer) 2, pelimoottori.getValitutIndeksit().get(0));
    }

    @Test
    public void vahennaKorttejaJaljellaToimiiOikein() {
        pelimoottori.vahennaKorttejaJaljella();
        assertEquals(14, pelimoottori.getKorttejaJaljella());
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiEiOleLoytynyt() {
        Kortti testikortti = poyta.getTaulukko()[0];
        assertFalse(pelimoottori.onkoKorttiJoLoytyneissa(testikortti));
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiOnLoytynyt() {
        Kortti testikortti1 = poyta.getTaulukko()[15];
        Kortti testikortti2 = poyta.getTaulukko()[0];
        pelimoottori.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertTrue(pelimoottori.onkoKorttiJoLoytyneissa(testikortti1));
    }

    @Test
    public void lisaaKorttiLoytyneisiinToimiiOikein() {
        Kortti testikortti1 = poyta.getTaulukko()[7];
        Kortti testikortti2 = poyta.getTaulukko()[5];
        pelimoottori.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertEquals(testikortti1, pelimoottori.getLoydetytKortit().get(0));
    }

    @Test
    public void alustaKorttejaJaljellaToimiiOikein() {
        pelimoottori.vahennaKorttejaJaljella();
        pelimoottori.alustaKorttejaJaljella();
        assertEquals(16, pelimoottori.getKorttejaJaljella());
    }

    @Test
    public void getKorttejaJaljellaTekstinaToimiiOikein() {
        assertEquals("Kortteja jäljellä: 16", pelimoottori.getKorttejaJaljellaTekstina());
    }

    @Test
    public void tyhjaaValitutIndeksitToimiiOikein() {
        pelimoottori.lisaaValittuihin(0);
        pelimoottori.lisaaValittuihin(1);
        pelimoottori.tyhjaaValitutIndeksit();
        assertTrue(pelimoottori.getValitutIndeksit().isEmpty());
    }

    @Test
    public void onkoKorttiValittavissaToimiiOikein() {
        Kortti kortti1 = poyta.getKorttiTaulukosta(0);
        kortti1.naytaKuvapuoli();
        assertFalse(pelimoottori.onkoKorttiValittavissa(0));
    }

    @Test
    public void valitseKorttiToimiiOikein() {
        pelimoottori.valitseKortti(4);
        assertTrue(pelimoottori.getValitutIndeksit().get(0) == 4 && poyta.getTaulukko()[4].nakyykoKuvapuoli());
    }

    @Test
    public void montakoValittuToimiiOikeinKunEiValittuYhtaan() {
        assertTrue(pelimoottori.montakoValittu() == 0);
    }

    @Test
    public void montakoValittuToimiiOikeinKunValittuYksi() {
        pelimoottori.valitseKortti(0);
        assertTrue(pelimoottori.montakoValittu() == 1);
    }

    @Test
    public void montakoValittuToimiiOikeinKunValittuKaksi() {
        pelimoottori.valitseKortti(0);
        pelimoottori.valitseKortti(15);
        assertTrue(pelimoottori.montakoValittu() == 2);
    }

    @Test
    public void getYritystenMaaraLukunaToimiiOikein() {
        pelimoottori.lisaaValintayritys();
        assertTrue(pelimoottori.getYritystenMaaraLukuna() == 1);
    }

    @Test
    public void lisaaValintayritysToimiiOikeinYhdellaYrityksella() {
        pelimoottori.lisaaValintayritys();
        assertTrue(pelimoottori.getYritystenMaaraLukuna() == 1);
    }

    @Test
    public void lisaaValintayritysToimiiOikeinKolmellakymmenellaYrityksella() {
        for (int i = 0; i < 30; i++) {
            pelimoottori.lisaaValintayritys();
        }
        assertTrue(pelimoottori.getYritystenMaaraLukuna() == 30);
    }

    @Test
    public void getYritystenMaaraTekstinaToimiiOikeinAlussa() {
        assertEquals("Yrityksiä: 0", pelimoottori.getYritystenMaaraTekstina());
    }

    @Test
    public void getYritystenMaaraTekstinaToimiiOikeinKahdella() {
        pelimoottori.lisaaValintayritys();
        pelimoottori.lisaaValintayritys();

        assertEquals("Yrityksiä: 2", pelimoottori.getYritystenMaaraTekstina());
    }

    @Test
    public void jatketaankoPeliaToimiiJosTulisiJatkaa() {

        pelimoottori.vahennaKorttejaJaljella();
        assertTrue(pelimoottori.jatketaankoPelia());
    }

    @Test
    public void jatketaankoPeliaToimiiJosEiTulisiJatkaa() {
        while (pelimoottori.getKorttejaJaljella() > 1) {
            pelimoottori.vahennaKorttejaJaljella();
        }
        assertFalse(pelimoottori.jatketaankoPelia());
    }

    @Test
    public void loytyikoPariPalauttaaTodenKunPariLoytyy() {

        pelimoottori.lisaaValittuihin(0);
        pelimoottori.lisaaValittuihin(8);

        assertTrue(pelimoottori.loytyikoPari());
    }

    @Test
    public void loytyikoPariPalautteeEpatodenKunPariaEiLoydy() {
        pelimoottori.lisaaValittuihin(1);
        pelimoottori.lisaaValittuihin(10);

        assertFalse(pelimoottori.loytyikoPari());
    }

}
