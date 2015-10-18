package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class LoytyneetKortitTest {

    LoytyneetKortit loytyneet;
    Pelimoottori pelimoottori;

    @Before
    public void setUp() {
        loytyneet = new LoytyneetKortit();
        pelimoottori = new Pelimoottori();
    }

    @Test
    public void konstruktoriLuoLoydettyjenKorttienListanOikein() {
        assertTrue(loytyneet.getLoydetytKortit().isEmpty());
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiEiOleLoytynyt() {
        Kortti testikortti = pelimoottori.getPelipoyta().getTaulukko()[0];
        assertFalse(loytyneet.onkoKorttiJoLoytyneissa(testikortti));
    }

    @Test
    public void onkoJoLoydettyToimiiKunKorttiOnLoytynyt() {
        Kortti testikortti1 = pelimoottori.getPelipoyta().getTaulukko()[15];
        Kortti testikortti2 = pelimoottori.getPelipoyta().getTaulukko()[0];
        loytyneet.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertTrue(loytyneet.onkoKorttiJoLoytyneissa(testikortti1));
    }

    @Test
    public void lisaaKorttiLoytyneisiinToimiiOikein() {
        Kortti testikortti1 = pelimoottori.getPelipoyta().getTaulukko()[7];
        Kortti testikortti2 = pelimoottori.getPelipoyta().getTaulukko()[5];
        loytyneet.lisaaKortitLoytyneeksi(testikortti1, testikortti2);
        assertEquals(testikortti1, loytyneet.getLoydetytKortit().get(0));
    }
}
