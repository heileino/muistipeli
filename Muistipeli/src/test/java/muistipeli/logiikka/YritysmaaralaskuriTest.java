package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class YritysmaaralaskuriTest {

    Yritysmaaralaskuri laskuri;

    @Before
    public void setUp() {
        laskuri = new Yritysmaaralaskuri();
    }

    @Test
    public void kostruktoriAsettaaYritystenMaaranOikein() {
        assertEquals(0, laskuri.getYritysmaara());
    }

    @Test
    public void lisaaYritysToimiiOikein() {
        laskuri.lisaaValintayritys();
        assertEquals(1, laskuri.getYritysmaara());
    }

    @Test
    public void toStringToimiiOikein() {
        assertEquals("Yrityksiä: " + laskuri.getYritysmaara(), laskuri.toString());
    }

}
