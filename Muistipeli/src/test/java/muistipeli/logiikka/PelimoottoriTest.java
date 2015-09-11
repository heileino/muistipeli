/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PelimoottoriTest {

    Pelimoottori pelimoottori;

    public PelimoottoriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pelimoottori = new Pelimoottori();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaPisteetOikein() {
        assertTrue(pelimoottori.getPisteet() == 0);
    }

    @Test
    public void konstruktoriLuoPoytaOlionOikein() {
        assertEquals(Pelipoyta.class, pelimoottori.getPoyta().getClass());
    }
       
    
    @Test
    public void lisaaPisteToimiiOikein() {
        pelimoottori.lisaaPiste();
        assertTrue(pelimoottori.getPisteet() == 1);
    }
}
