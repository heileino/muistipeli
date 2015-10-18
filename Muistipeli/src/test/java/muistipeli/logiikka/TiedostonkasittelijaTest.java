package muistipeli.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class TiedostonkasittelijaTest {

    Tiedostonkasittelija tkasittelija;

    @Test
    public void luoTiedostoToimiiOikein() {
        tkasittelija = new Tiedostonkasittelija("testi.txt");
        tkasittelija.luoTiedosto();
        assertEquals("9999", tkasittelija.lueTiedostosta());
    }

    @Test
    public void kirjoitaTiedostoonToimiiOikein() {
        tkasittelija = new Tiedostonkasittelija("testi.txt");
        tkasittelija.kirjoitaTiedostoon("10");
        assertEquals("10", tkasittelija.lueTiedostosta());
    }
}
