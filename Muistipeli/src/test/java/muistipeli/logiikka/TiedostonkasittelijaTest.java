/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki
 */
public class TiedostonkasittelijaTest {

    Tiedostonkasittelija tkasittelija;

    public TiedostonkasittelijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

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
