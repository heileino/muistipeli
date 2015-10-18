package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Leinonen
 */
public class ParhaanTuloksenTilastoTest {

    ParhaanTuloksenTilasto parasTulos;

    @Before
    public void setUp() {
        parasTulos = new ParhaanTuloksenTilasto("testiparas.txt");
    }

    @Test
    public void konstruktoriAsettaaParasTulosMuuttujaanOikeanArvon() {
        assertTrue(this.parasTulos.getParasTulos() == 9999);
    }

    @Test
    public void toStringToimiiOikeinAlkuarvolla() {
        assertEquals("Paras tulos: -", parasTulos.toString());
    }

    @Test
    public void toStringToimiiOikeinAsetetullaArvolla() {
        parasTulos.setParasTulos(40);
        assertEquals("Paras tulos: 40", parasTulos.toString());
    }

    @Test
    public void setTiedostonimiToimiiOikein() {
        parasTulos.setTiedostonimi("testaus.txt");
        assertEquals("testaus.txt", parasTulos.getTiedostonimi());
    }

    @Test
    public void getTiedostonimiToimiiOikein() {
        parasTulos.setTiedostonimi("testaus2.txt");
        assertEquals("testaus2.txt", parasTulos.getTiedostonimi());
    }
}
