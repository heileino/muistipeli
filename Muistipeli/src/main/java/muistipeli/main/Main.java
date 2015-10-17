/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.main;

import javax.swing.SwingUtilities;
import muistipeli.kayttoliittyma.GraafinenKayttoliittyma;
//import muistipeli.kayttoliittyma.Tekstikayttoliittyma;
import muistipeli.logiikka.Pelipoyta;

/**
 * Main-luokka toimii alkuvaiheessa lähinnä testaamisen apuna
 *
 * @author Heikki Leinonen
 */
public class Main {

    public static void main(String[] args) {
        GraafinenKayttoliittyma gui = new GraafinenKayttoliittyma();
        SwingUtilities.invokeLater(gui);
    }

}
