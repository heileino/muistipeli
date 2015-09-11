/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

/**
 * Main-luokka toimii alkuvaiheessa lähinnä testaamisen apuna
 *
 * @author Heikki Leinonen
 */
public class Main {

    public static void main(String[] args) {
        Pelimoottori moottori = new Pelimoottori();
        moottori.pelaa();

    }

}
