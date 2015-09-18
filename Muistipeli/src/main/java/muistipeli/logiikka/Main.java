/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import kayttoliittyma.Tekstikayttoliittyma;

/**
 * Main-luokka toimii alkuvaiheessa lähinnä testaamisen apuna
 *
 * @author Heikki Leinonen
 */
public class Main {

    public static void main(String[] args) {
        Pelipoyta poyta = new Pelipoyta();
        Tekstikayttoliittyma tkayttis = new Tekstikayttoliittyma(poyta);
//        Pelimoottori moottori = new Pelimoottori();
//        moottori.pelaa();

    }

}
