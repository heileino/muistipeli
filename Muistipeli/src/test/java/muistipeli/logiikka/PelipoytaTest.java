package muistipeli.logiikka;

import org.junit.Before;
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

    @Before
    public void setUp() {

        poyta = new Pelipoyta(16);
        poyta.asetaKortitTaulukkoon();
    }

    @Test
    public void konstruktoriLuoOikeanKokoisenTaulukon() {
        assertEquals(16, poyta.getTaulukko().length);
    }

    @Test
    public void asetaKortitTaulukkoonToimiiOikeinAlkuosassa() {
        boolean ok = true;

        int puolikasTaulukonPituus = poyta.getTaulukko().length / 2;

        for (int i = 0; i < puolikasTaulukonPituus; i++) {

            if (poyta.getTaulukko()[i].toString().equals("kortti_9")) {
                ok = false;
            }
        }

        assertTrue(ok);
    }

    @Test
    public void asetaKortitTaulukkoonToimiiOikeinIndeksienOsalta() {
        boolean ok = true;

        int puolikasTaulukonPituus = poyta.getTaulukko().length / 2;

        for (int i = 0; i < puolikasTaulukonPituus; i++) {

            if (i >= puolikasTaulukonPituus) {
                ok = false;
            }
        }
        assertTrue(ok);
    }

    @Test
    public void taulukonPituudenPuolitusToimiiOikein() {
        assertEquals(8, poyta.getTaulukko().length / 2);
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
    public void taulukonEnsimmaisellaJaYhdeksannellaKortillaSamaTunnus() {
        assertTrue(poyta.getTaulukko()[0].toString().equals(poyta.getTaulukko()[8].toString()));
    }

    @Test
    public void taulukonKahdeksannellaJaKuudennellatoistaKortillaSamaTunnus() {
        assertTrue(poyta.getTaulukko()[7].toString().equals(poyta.getTaulukko()[15].toString()));
    }

    @Test
    public void taulukonYhdeksannellaJaKuudennellatoistaOnEriTunnus() {
        assertFalse(poyta.getTaulukko()[8].toString().equals(poyta.getTaulukko()[15]));
    }

    @Test
    public void taulukonEnsimmaisellaJaKymmenennellaOnEriTunnus() {
        assertFalse(poyta.getTaulukko()[0].toString().equals(poyta.getTaulukko()[9]));
    }

    @Test
    public void taulukkoEiLuoKorttiaJonkaTunnusSisaltaaLuvunYhdeksan() {
        boolean ok = true;
        for (int i = 0; i < poyta.getTaulukko().length; i++) {
            if (poyta.getTaulukko()[i].toString().equals("kortti_9")) {
                ok = false;
            }
        }
        assertTrue(ok);
    }

    @Test
    public void onkoKorteillaSamaTunnusAntaaSamoillaKorteillaTodenKunParametreinaIndeksit() {
        assertTrue(poyta.onkoKorteillaSamaTunnus(0, 8));
    }

    @Test
    public void onkoKorteillaSamaTunnusAntaaEripareillaEpatodenKunParametreinaIndeksit() {
        assertFalse(poyta.onkoKorteillaSamaTunnus(2, 3));
    }

    @Test
    public void onkoKorteillaSamaTunnusAntaaSamoillaKorteillaTodenKunParametreinaKortit() {
        Kortti kortti1 = poyta.getTaulukko()[2];
        Kortti kortti2 = poyta.getTaulukko()[10];

        assertTrue(poyta.onkoKorteillaSamaTunnus(kortti1, kortti2));
    }

    @Test
    public void onkoKorteillaSamaTunnusAntaaEripareillaEpätodenKunParametreinaKortit() {
        Kortti kortti1 = poyta.getTaulukko()[0];
        Kortti kortti2 = poyta.getTaulukko()[1];

        assertFalse(poyta.onkoKorteillaSamaTunnus(kortti1, kortti2));
    }

    @Test
    public void paljastaKorttiToimiiOikein() {
        poyta.paljastaKortinKuva(0);

        assertTrue(poyta.getTaulukko()[0].nakyykoKuvapuoli());
    }

    @Test
    public void piilotaKorttiToimiiOikein() {
        poyta.paljastaKortinKuva(15);
        poyta.piilotaKortinKuva(15);

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

    @Test
    public void getKorttiTaulukostaToimiiOikein() {
        assertEquals("kortti_1", poyta.getKorttiTaulukosta(0).toString());
    }

    @Test
    public void kaannaKaikkiKortitSelkapuolelleToimiiOikein() {
        boolean ok = true;

        poyta.getKorttiTaulukosta(0).naytaKuvapuoli();
        poyta.getKorttiTaulukosta(15).naytaKuvapuoli();

        poyta.kaannaKaikkiKortitSelkapuolelle();

        for (int i = 0; i < poyta.getTaulukko().length; i++) {
            if (poyta.getKorttiTaulukosta(i).nakyykoKuvapuoli()) {
                ok = false;
            }
        }
        assertTrue(ok);
    }
}
