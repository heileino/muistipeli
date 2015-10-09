
package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class YritysmaaraLaskuriTest {

    YritysmaaraLaskuri laskuri;

    @Before
    public void setUp() {
        laskuri = new YritysmaaraLaskuri();
    }

    @Test
    public void kostruktoriAsettaaYritystenMaaranOikein() {
        assertEquals(0, laskuri.getYritysmaara());
    }

    @Test
    public void lisaaYritysToimiiOikein() {
        laskuri.lisaaYritys();
        assertEquals(1, laskuri.getYritysmaara());
    }

    @Test
    public void toStringToimiiOikein() {
        assertEquals("Yrityksiä: " + laskuri.getYritysmaara(), laskuri.toString());
    }
}
