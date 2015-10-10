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
public class LoytamattomatKorttiparitTest {

    LoytamattomatKorttiparit laskuri;

    @Before
    public void setUp() {
        laskuri = new LoytamattomatKorttiparit(8);
    }

    @Test
    public void konstruktoriAsettaaKorttejaLoytamattaOikeinKunParametriLaillinen() {
        assertEquals(8, laskuri.getParejaLoytymatta());
    }

    @Test
    public void konstruktoriAsettaaKorttejaLoytamattaOikeinKunParametriLaitonNolla() {
        LoytamattomatKorttiparit uusiLaskuri = new LoytamattomatKorttiparit(0);
        assertEquals(0, uusiLaskuri.getParejaLoytymatta());
    }

    @Test
    public void konstruktoriAsettaaKorttejaLoytamattaOikeinKunParametriLaitonMiinusYksi() {
        LoytamattomatKorttiparit uusiLaskuri = new LoytamattomatKorttiparit(-1);
        assertEquals(0, uusiLaskuri.getParejaLoytymatta());
    }

    @Test
    public void vahennaKorttejaLoytamattaToimiiOikein() {
        laskuri.vahennaParejaLoytymatta();
        assertEquals(7, laskuri.getParejaLoytymatta());
    }

//    @Test
//    public void vahennaKorttejaLoytamattaToimiiOikeinKunEiOleVahennettavaa() {
//        laskuri.setParejaLoytymatta(0);
//        laskuri.vahennaParejaLoytymatta();
//        assertTrue(laskuri.getParejaLoytymatta() == 0);
//    }

    @Test
    public void setKorttejaLoytamattaToimiiOikeinKunLaillinenParametri() {
        laskuri.setParejaLoytymatta(20);
        assertTrue(laskuri.getParejaLoytymatta() == 20);
    }

    public void setKorttejaLoytamattaToimiiOikeinKunLaitonParametri() {
        laskuri.setParejaLoytymatta(20);
        laskuri.setParejaLoytymatta(-1);
        assertTrue(laskuri.getParejaLoytymatta() == 20);
    }

    @Test
    public void toStringToimiiOikein() {
        assertEquals("Pareja jäljellä: 8", laskuri.toString());
    }
}
