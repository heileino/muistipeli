/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki
 */
public class PelimoottoriTest {

    Pelimoottori pelimoottori;

    @Before
    public void setUp() {
        pelimoottori = new Pelimoottori();
        pelimoottori.getPelipoyta().asetaKortitTaulukkoon();
    }

    @Test
    public void konstruktoriluoUudenParejaJaljellaKirjanpitajanOikein() {
        assertEquals(8, pelimoottori.getParejaLoytymatta());
    }

    @Test
    public void konstruktoriLuoUudenLoydetytKortitListan() {
        Kortti kortti1 = pelimoottori.getPelipoyta().getTaulukko()[0];
        Kortti kortti2 = pelimoottori.getPelipoyta().getTaulukko()[15];
        pelimoottori.lisaaKortitLoytyneeksi(kortti1, kortti2);
        assertEquals("kortti_1", pelimoottori.getLoydetytKortit().get(0).toString());
    }

    @Test
    public void konstruktoriLuoUudenValitutIndeksitListan() {
        pelimoottori.lisaaValittuihin(2);
        assertEquals((Integer) 2, pelimoottori.getValitutIndeksit().get(0));
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiEiOleLoytynyt() {
        Kortti testikortti = pelimoottori.getPelipoyta().getTaulukko()[0];
        assertFalse(pelimoottori.onkoKorttiJoLoytyneissa(testikortti));
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiOnLoytynyt() {
        Kortti testikortti1 = pelimoottori.getPelipoyta().getTaulukko()[15];
        Kortti testikortti2 = pelimoottori.getPelipoyta().getTaulukko()[0];
        pelimoottori.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertTrue(pelimoottori.onkoKorttiJoLoytyneissa(testikortti1));
    }

    @Test
    public void lisaaKorttiLoytyneisiinToimiiOikein() {
        Kortti testikortti1 = pelimoottori.getPelipoyta().getTaulukko()[7];
        Kortti testikortti2 = pelimoottori.getPelipoyta().getTaulukko()[5];
        pelimoottori.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertEquals(testikortti1, pelimoottori.getLoydetytKortit().get(0));
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
        Kortti kortti1 = pelimoottori.getPelipoyta().getKorttiTaulukosta(0);
        kortti1.naytaKuvapuoli();
        assertFalse(pelimoottori.onkoKorttiValittavissa(0));
    }

    @Test
    public void valitseKorttiToimiiOikein() {
        pelimoottori.valitseKortti(4);
        assertTrue(pelimoottori.getValitutIndeksit().get(0) == 4 && pelimoottori.getPelipoyta().getTaulukko()[4].nakyykoKuvapuoli());
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

        pelimoottori.vahennaLoytamattomienKorttienMaaraa();
        assertTrue(pelimoottori.jatketaankoPelia());
    }

    @Test
    public void jatketaankoPeliaToimiiJosEiTulisiJatkaa() {

        while (pelimoottori.getParejaLoytymatta() > 1) {
            pelimoottori.vahennaLoytamattomienKorttienMaaraa();
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

    @Test
    public void loytyikoPariLisaaKortitLoytyneiksiOikein() {

        pelimoottori.lisaaValittuihin(1);
        pelimoottori.lisaaValittuihin(10);

        pelimoottori.lisaaKortitLoytyneeksi(pelimoottori.getKortti(pelimoottori.getValitutIndeksit().get(0)), pelimoottori.getKortti(pelimoottori.getValitutIndeksit().get(1)));

        assertTrue(pelimoottori.getKortti(1).equals(pelimoottori.getLoydetytKortit().get(0)) && pelimoottori.getKortti(10).equals(pelimoottori.getLoydetytKortit().get(1)));

    }

    @Test
    public void kaannaKaikkiKortitSelkapuolipuoliYlosToimiiOikein() {

        boolean ok = true;
        pelimoottori.kaannaKaikkiKortitSelkapuoliYlos();

        for (int i = 0; i < pelimoottori.getPelipoyta().getTaulukko().length; i++) {
            if (pelimoottori.getPelipoyta().getTaulukko()[i].nakyykoKuvapuoli()) {
                ok = false;
            }
        }

        assertTrue(ok);
    }

}
