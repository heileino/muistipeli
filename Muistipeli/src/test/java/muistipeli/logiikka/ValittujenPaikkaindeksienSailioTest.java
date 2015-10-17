package muistipeli.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class ValittujenPaikkaindeksienSailioTest {

    ValittujenPaikkaindeksienSailio valinnat;

    public ValittujenPaikkaindeksienSailioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        valinnat = new ValittujenPaikkaindeksienSailio();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tyhjaaValitutIndeksitToimiiOikein() {
        valinnat.lisaaValittuihin(0);
        valinnat.lisaaValittuihin(1);
        valinnat.tyhjaaValitutIndeksit();
        assertTrue(valinnat.getValitutIndeksit().isEmpty());
    }

    @Test
    public void montakoValittuToimiiOikeinKunEiValittuYhtaan() {
        assertTrue(valinnat.montakoValittu() == 0);
    }

    @Test
    public void montakoValittuToimiiOikeinKunValittuYksi() {
        valinnat.lisaaValittuihin(0);
        assertTrue(valinnat.montakoValittu() == 1);
    }

    @Test
    public void montakoValittuToimiiOikeinKunValittuKaksi() {
        valinnat.lisaaValittuihin(0);
        valinnat.lisaaValittuihin(15);
        assertTrue(valinnat.montakoValittu() == 2);
    }
}
