package muistipeli.logiikka;

import java.io.File;
import java.net.URL;
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
public class ParasTulosTest {

    ParasTulos parasTulos;
    File testitiedosto;
    URL url;

    public ParasTulosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        parasTulos = new ParasTulos();
//        URL = this.getClass().getResource("/test.wsdl");
//        testitiedosto = new File(url.getFile());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaParasTulosMuuttujaanOikeanArvon() {
        assertTrue(this.parasTulos.getParasTulos() == 9999);
    }
}
