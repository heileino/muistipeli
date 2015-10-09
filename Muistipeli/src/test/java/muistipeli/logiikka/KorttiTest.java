package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class KorttiTest {

    Kortti kortti;

    @Before
    public void setUp() {
        kortti = new Kortti("5");
    }

    @Test
    public void konstruktoriAsettaaTunnuksenOikein() {
        assertEquals("5", kortti.getTunnus());
    }

    @Test
    public void konstruktoriAsettaaNakyvyydenOikein() {
        assertTrue(!kortti.nakyykoKuvapuoli());
    }

    @Test
    public void nakyykoKuvapuoliToimiiOikeinKunKuvapuoliNakyy() {
        kortti.naytaKuvapuoli();
        assertTrue(kortti.nakyykoKuvapuoli());
    }

    @Test
    public void nakyykoKuvapuoliToimiiOikeinKunSelkapuoliNakyy() {
        assertFalse(kortti.nakyykoKuvapuoli());
    }

    @Test
    public void naytaKuvapuoliToimiiOikein() {
        kortti.naytaKuvapuoli();
        assertTrue(kortti.nakyykoKuvapuoli());
    }

    @Test
    public void naytaSelkapuoliToimiiOikein() {
        kortti.naytaKuvapuoli();
        kortti.naytaSelkapuoli();
        assertFalse(kortti.nakyykoKuvapuoli());

    }

    @Test
    public void toStringToimiiOikein() {
        assertEquals("kortti_" + kortti.getTunnus(), kortti.toString());
    }
}
