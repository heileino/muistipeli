package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class PelimoottoriTest {

    Pelimoottori pelimoottori;

    @Before
    public void setUp() {
        pelimoottori = new Pelimoottori();
        pelimoottori.getPelipoyta().asetaKortitTaulukkoon();
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
    public void valitaanJosMahdollistaToimiiKunValintaOnLaillinen() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        assertTrue(pelimoottori.valitaanKorttiJosMahdollista(1));
    }

    @Test
    public void valitaanJosMahdollistaToimiiKunValintaOnLaiton() {
        pelimoottori.valitaanKorttiJosMahdollista(0);
        assertFalse(pelimoottori.valitaanKorttiJosMahdollista(0));
    }

    @Test
    public void valitaanJosMahdollistaValitseeKortinOikein() {
        pelimoottori.valitaanKorttiJosMahdollista(0);
        assertTrue(pelimoottori.getValinnanIndeksi(0) == 0 && pelimoottori.getPelipoyta().getKortinKuvapuolenNakyvyys(0));
    }

    @Test
    public void valintaankoToinenKorttiToimiiOikeinKunValittavaToinen() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(15);
        assertTrue(pelimoottori.valitaankoToinenKortti());
    }

    @Test
    public void valintaankoToinenKorttiToimiiOikeinKunEiValitaToista() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(15);
        assertFalse(pelimoottori.valitaankoToinenKortti());
    }

    @Test
    public void valintaankoToinenKorttiLisaaValintaYrityksenJosEiValitaToista() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(15);
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

        assertTrue(pelimoottori.ovatkoValinnatPareja());

    }

    @Test
    public void ovatkoValinnatParejaToimiiOikeinKunValinnatEivatOlePareja() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(15);

        assertFalse(pelimoottori.ovatkoValinnatPareja());
    }

    @Test
    public void ovatkoValinnatParejaKaantaaKortitNurin() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(15);

        pelimoottori.getPelipoyta().getKorttiTaulukosta(0).naytaKuvapuoli();
        pelimoottori.getPelipoyta().getKorttiTaulukosta(15).naytaKuvapuoli();

        Kortti kortti1 = pelimoottori.getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = pelimoottori.getKorttiValittujenKorttienJoukosta(1);

        assertTrue(!pelimoottori.ovatkoValinnatPareja() && !kortti1.nakyykoKuvapuoli() && !kortti2.nakyykoKuvapuoli());
    }

    @Test
    public void ovatkoValinnatParejakutsuuPariLoytynyt() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(0);
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(8);

        Kortti kortti1 = pelimoottori.getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = pelimoottori.getKorttiValittujenKorttienJoukosta(1);

        pelimoottori.ovatkoValinnatPareja();
        assertTrue(pelimoottori.getLoytyneet().onkoKorttiJoLoytyneissa(kortti1) && pelimoottori.getLoytyneet().onkoKorttiJoLoytyneissa(kortti2));

    }

    @Test
    public void kaannaKortitNurinToimiiOikein() {
        pelimoottori.getPelipoyta().paljastaKortinKuva(0);
        pelimoottori.getPelipoyta().paljastaKortinKuva(15);

        pelimoottori.getPelipoyta().piilotaKortinKuva(0);
        pelimoottori.getPelipoyta().piilotaKortinKuva(15);
        assertTrue(!pelimoottori.getPelipoyta().getKortinKuvapuolenNakyvyys(0) && !pelimoottori.getPelipoyta().getKortinKuvapuolenNakyvyys(15));
    }

    @Test
    public void jatkuukoPeliToimiiOikeinKunJatketaan() {
        pelimoottori.getValittujenPaikkaindeksienSailio().lisaaValittuihin(4);
        pelimoottori.getLoytyneet().lisaaKortitLoytyneeksi(pelimoottori.getPelipoyta().getKorttiTaulukosta(0), pelimoottori.getPelipoyta().getKorttiTaulukosta(1));
        assertTrue(pelimoottori.jatkuukoPeli() && pelimoottori.getValittujenPaikkaindeksienSailio().getValitutIndeksit().isEmpty());
    }

    @Test
    public void jatkuukoPeliToimiiOikeinKunEiJatketa() {
        for (int i = 0; i < 8; i++) {
            pelimoottori.getLoytyneet().lisaaKortitLoytyneeksi(pelimoottori.getPelipoyta().getKorttiTaulukosta(i), pelimoottori.getPelipoyta().getKorttiTaulukosta(i));
        }
        assertTrue(!pelimoottori.jatkuukoPeli());
    }

    @Test
    public void pelaaPeliToimiiOikeinAsemmattamallaKortitTaulukkoon() {
        Pelimoottori pelimoottori2 = new Pelimoottori();
        pelimoottori2.pelaaPeli();
        assertTrue(pelimoottori2.getPelipoyta().getKorttiTaulukosta(0) instanceof Kortti && pelimoottori2.getPelipoyta().getKorttiTaulukosta(15) instanceof Kortti);
    }

    @Test
    public void getYritystenMaaraTekstinaToimiiOikein() {
        assertEquals("Yrityksiä: 0", pelimoottori.getYritystenMaaraTekstina());
    }

    @Test
    public void getParasTulosTekstinaToimiiOikein() {
        assertEquals("Paras tulos: -", pelimoottori.getParasTulosTekstina());
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
    public void aloitetaankoUusiPeliToimiiOikeinKunKayttajaHaluaaJatkaa() {
        assertTrue(pelimoottori.aloitetaankoUusiPeli(0));
    }

    @Test
    public void aloitetaankoUusiPeliToimiiOikeinKunKayttajaEiHaluaJatkaa() {
        assertFalse(pelimoottori.aloitetaankoUusiPeli(1));
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
