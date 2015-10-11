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
        pelimoottori.getParasTulos().setTiedostonimi("testiparas.txt");
    }

    @Test
    public void konstruktoriluoUudenParejaJaljellaKirjanpitajanOikein() {
        assertEquals(8, pelimoottori.getParejaLoytymatta());
    }

    @Test
    public void konstruktoriLuoUudenLoydetytKortitOlion() {
        assertTrue(pelimoottori.getLoytyneetKortit().getLoydetytKortit().isEmpty());
    }

    @Test
    public void konstruktoriLuoUudenValitutIndeksitOlion() {

        assertTrue((pelimoottori.getValitutPaikat().montakoValittu() == 0));
    }

    @Test
    public void onkoKorttiValittavissaToimiiOikein() {
        Kortti kortti1 = pelimoottori.getPelipoyta().getKorttiTaulukosta(0);
        kortti1.naytaKuvapuoli();
        assertFalse(pelimoottori.onkoKorttiValittavissa(0));
    }

    @Test
    public void getYritystenMaaraTekstinaToimiiOikeinAlussa() {
        assertEquals("Yrityksiä: 0", pelimoottori.getYritystenMaaraTekstina());
    }

    @Test
    public void getYritystenMaaraTekstinaToimiiOikeinKahdella() {
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();

        assertEquals("Yrityksiä: 2", pelimoottori.getYritystenMaaraTekstina());
    }

    @Test
    public void jatketaankoPeliaToimiiJosTulisiJatkaa() {

        pelimoottori.vahennaLoytamattomienParienMaaraa();
        assertTrue(pelimoottori.jatketaankoPelia());
    }

    @Test
    public void jatketaankoPeliaToimiiJosEiTulisiJatkaa() {

        while (pelimoottori.getParejaLoytymatta() > 0) {
            pelimoottori.vahennaLoytamattomienParienMaaraa();
        }

        assertFalse(pelimoottori.jatketaankoPelia());
    }

    @Test
    public void loytyikoPariPalauttaaTodenKunPariLoytyy() {

        pelimoottori.getValitutPaikat().lisaaValittuihin(0);
        pelimoottori.getValitutPaikat().lisaaValittuihin(8);

        assertTrue(pelimoottori.loytyikoPari());
    }

    @Test
    public void loytyikoPariPalautteeEpatodenKunPariaEiLoydy() {

        pelimoottori.getValitutPaikat().lisaaValittuihin(1);
        pelimoottori.getValitutPaikat().lisaaValittuihin(10);

        assertFalse(pelimoottori.loytyikoPari());
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

    @Test
    public void getParasTulosTekstinaToimiiOikeinKunTulosAlkuarvo() {

        assertEquals("Paras tulos: - ", pelimoottori.getParasTulosTekstina());
    }

    @Test
    public void getParasTulosTekstinaToimiiAsetetullaArvolla() {

        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.asetaParasTulos();

        assertEquals("Paras tulos: 2", pelimoottori.getParasTulosTekstina());
    }

    @Test
    public void getParejaLoytamattaTekstina() {

        assertEquals("Pareja jäljellä: 8", pelimoottori.getParejaLoytamattaTekstina());
    }

    @Test
    public void onUusiParasTulosToimiiOikeinKunTulosOnParas() {

        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();

        assertTrue(pelimoottori.onUusiParasTulos());
    }

    @Test
    public void onUusiParasTulosToimiiOikeinKunTulosEiOleParas() {

        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.asetaParasTulos();

        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();

        assertFalse(pelimoottori.onUusiParasTulos());
    }

    @Test
    public void onUusiParasTulosToimiiOikeinKunTulosOnSamaKuinParas() {
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();
        pelimoottori.asetaParasTulos();

        assertFalse(pelimoottori.onUusiParasTulos());
    }

    @Test
    public void kaannaKortitNurinToimiiOikein() {

        Kortti kortti1 = pelimoottori.getKortti(0);
        Kortti kortti2 = pelimoottori.getKortti(15);

        kortti1.naytaKuvapuoli();
        kortti2.naytaKuvapuoli();

        pelimoottori.kaannaKortitNurin(kortti1, kortti2);

        assertFalse(kortti1.nakyykoKuvapuoli() || kortti2.nakyykoKuvapuoli());
    }

}
