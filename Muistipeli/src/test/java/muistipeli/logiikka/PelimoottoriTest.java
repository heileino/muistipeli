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
        // pelimoottori.getParasTulos().setTiedostonimi("testiparas.txt");
    }

    @Test
    public void konstruktoriAsettaaKorttienMaaranJaLuoPelipoydanTaulukonOikein() {
        assertTrue(pelimoottori.getPelipoyta().getTaulukko().length == 16);
    }

    @Test
    public void konstruktoriAsettaaTiedostonNimenOikein() {
        assertEquals("parastulos.txt", pelimoottori.getTiedostonimi());
    }

    @Test
    public void konstruktoriLuoUudenLoytyneetKortitOlion() {
        assertTrue(pelimoottori.getLoytyneet().getLoydetytKortit().isEmpty());
    }

    @Test
    public void konstruktoriLuoUudenParasTulosOlion() {
        assertEquals("parastulos.txt", pelimoottori.getParhaanTuloksenTilasto().getTiedostonimi());
    }

    @Test
    public void konstruktoriLuoUudenValittujenPaikkaindeksienSailioOlion() {
        assertTrue(pelimoottori.getValittujenPaikkaindeksienSailio().getValitutIndeksit().isEmpty());
    }

    @Test
    public void konstruktoriLuoUudenYritysmaaralaskurin() {
        assertTrue(pelimoottori.getYritysmaaraLukuna() == 0);
    }

    @Test
    public void valintaOkToimiiKunValintaOnLaillinen() {
        pelimoottori.valitseKortti(0);
        assertTrue(pelimoottori.valintaOk(1));
    }

    @Test
    public void valintaOkToimiiKunValintaOnLaiton() {
        pelimoottori.valitseKortti(0);
        assertFalse(pelimoottori.valintaOk(0));
    }

    @Test
    public void valintaOkValitseeKortinOikein() {
        pelimoottori.valintaOk(0);
        assertTrue(pelimoottori.getValinnanIndeksi(0) == 0 && pelimoottori.getPelipoyta().getKortinKuvapuolenNakyvyys(0));
    }

    @Test
    public void onkoKorttiValittavissaToimiiOikeinKunKorttiOnLaillinen() {
        pelimoottori.valitseKortti(15);
        assertTrue(pelimoottori.onkoKorttiValittavissa(14));
    }

    @Test
    public void onkoKorttiValittavissaToimiiOikeinKunKorttiOnLaiton() {
        pelimoottori.valitseKortti(15);
        assertFalse(pelimoottori.onkoKorttiValittavissa(15));
    }

    @Test
    public void valitseKorttiToimiiOikein() {
        pelimoottori.valitseKortti(10);
        assertTrue(pelimoottori.getValinnanIndeksi(0) == 10 && pelimoottori.getPelipoyta().getKorttiTaulukosta(10).nakyykoKuvapuoli());
    }

    @Test
    public void valintaankoToinenKorttiToimiiOikeinKunValittavaToinen() {
        pelimoottori.valitseKortti(15);
        assertTrue(pelimoottori.valitaankoToinenKortti());
    }

    @Test
    public void valintaankoToinenKorttiToimiiOikeinKunEiValitaToista() {
        pelimoottori.valitseKortti(0);
        pelimoottori.valitseKortti(15);
        assertFalse(pelimoottori.valitaankoToinenKortti());
    }

    @Test
    public void valintaankoToinenKorttiLisaaValintaYrityksenJosEiValitaToista() {
        pelimoottori.valitseKortti(0);
        pelimoottori.valitseKortti(15);
        pelimoottori.valitaankoToinenKortti();
        assertTrue(pelimoottori.getYritysmaaraLukuna() == 1);
    }

    @Test
    public void getKortinTunnisteToimiiOikein() {
        assertEquals("kortti_1", pelimoottori.getKortinTunniste(0));
    }

    @Test
    public void ovatkoValinnatParejaToimiiOikeinKunValinnatOvatPareja() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(8);

        Kortti kortti1 = pelimoottori.getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = pelimoottori.getKorttiValittujenKorttienJoukosta(1);

        assertTrue(pelimoottori.ovatkoValinnatPareja());

    }

    @Test
    public void ovatkoValinnatParejaToimiiOikeinKunValinnatEivatOlePareja() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(15);

        Kortti kortti1 = pelimoottori.getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = pelimoottori.getKorttiValittujenKorttienJoukosta(1);

        assertFalse(pelimoottori.ovatkoValinnatPareja());
    }

    @Test
    public void ovatkoValinnatParejaKaantaaKortitNurin() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(15);

        Kortti kortti1 = pelimoottori.getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = pelimoottori.getKorttiValittujenKorttienJoukosta(1);

        pelimoottori.ovatkoValinnatPareja();

        assertTrue(!kortti1.nakyykoKuvapuoli() && !kortti2.nakyykoKuvapuoli());
    }

    @Test
    public void ovatkoValinnatParejakutsuuPariLoytynyt() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(8);

        Kortti kortti1 = pelimoottori.getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = pelimoottori.getKorttiValittujenKorttienJoukosta(1);

        pelimoottori.ovatkoValinnatPareja();
        assertTrue(pelimoottori.getLoytyneet().onkoKorttiJoLoytyneissa(kortti1)&&pelimoottori.getLoytyneet().onkoKorttiJoLoytyneissa(kortti2));

    }

    @Test
    public void onkoKorteillaSamaTunnusToimiiOikeinKunSama() {

    }

    @Test
    public void onkoKorteillaSamaTunnusToimiiOikeinKunEiSama() {

    }

    @Test
    public void getLoytamattomienKorttiparienLukumaaraToimiiOikein() {
        assertNotNull(pelimoottori.getLoytymattomienParienLukumaara());
    }

    @Test
    public void getLoytamattomienKorttiparienMaaraTekstinaToimiiOikein() {
        assertEquals("Pareja jäljellä: " + 8, pelimoottori.getLoytamattomienKorttiparienMaaraTekstina());
    }

    @Test
    public void getLoytyneetKortitToimiiOikein() {
        assertNotNull(pelimoottori.getLoytyneet());
    }

    @Test
    public void getParhaanTuloksenTilastoToimiiOikein() {
        assertNotNull(pelimoottori.getParhaanTuloksenTilasto());
    }

    @Test
    public void getValittujenPaikkaindeksienSailioToimiiOikein() {
        assertNotNull(pelimoottori.getValittujenPaikkaindeksienSailio());
    }

    @Test
    public void getYritysmaaralaskuriToimiiOikein() {
        assertNotNull(pelimoottori.getYritysmaaraLaskuri());
    }

    @Test
    public void getTiedostonimiToimiiOikein() {
        assertEquals("parastulos.txt", pelimoottori.getTiedostonimi());
    }
}
