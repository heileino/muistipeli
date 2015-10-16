/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki
 */
public class PelimoottoriTest {

    Pelimoottori pelimoottori;
    
    
    @Before
    public void setUp() {
        pelimoottori = new Pelimoottori();
        pelimoottori.getPelipoyta().asetaKortitTaulukkoon();
        // pelimoottori.getParasTulos().setTiedostonimi("testiparas.txt");
    }

    @Test
    public void getLoytamattomatKorttiparitToimiiOikein() {
        assertNotNull(pelimoottori.getLoytamattomatKorttiparit());
    }

    @Test
    public void getLoytyneetKortitToimiiOikein() {
        assertNotNull(pelimoottori.getLoytyneetKortit());
    }

    @Test
    public void getParasTulosToimiiOikein() {
        assertNotNull(pelimoottori.getParasTulos());
    }

    @Test
    public void getValitutPaikatToimiiOikein() {
        assertNotNull(pelimoottori.getValitutPaikat());
    }

    @Test
    public void getYritysmaaralaskuriToimiiOikein() {
        assertNotNull(pelimoottori.getYritysmaaraLaskuri());
    }

    @Test
    public void getTiedostonimiToimiiOikein() {
        assertEquals("parastulos.txt", pelimoottori.getTiedostonimi());
    }
}
