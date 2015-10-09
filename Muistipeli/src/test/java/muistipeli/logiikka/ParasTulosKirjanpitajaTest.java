package muistipeli.logiikka;

import java.io.File;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class ParasTulosKirjanpitajaTest {

    ParasTulosKirjanpitaja parasTulos;
    File testitiedosto;
    URL url;

    @Before
    public void setUp() {
        parasTulos = new ParasTulosKirjanpitaja();
//        URL = this.getClass().getResource("/test.wsdl");
//        testitiedosto = new File(url.getFile());
    }

    @Test
    public void konstruktoriAsettaaParasTulosMuuttujaanOikeanArvon() {
        assertTrue(this.parasTulos.getParasTulos() == 9999);
    }

    @Test
    public void toStringToimiiOikein() {
        assertEquals("Paras tulos: - ", parasTulos.toString());
    }
}
