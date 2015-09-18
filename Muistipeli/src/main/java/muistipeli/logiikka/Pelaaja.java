/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

/**
 * Pelaaja-luokan ilmentymät esittävät Muistipelin pelaajaa ja ylläpitävät
 * tilastoa arvausyritysten määrästä.
 * @author Heikki Leinonen
 */
public class Pelaaja {

    private int yritykset;

    public Pelaaja() {
        this.yritykset = 0;
    }

    public void lisaaYritys() {
        this.yritykset++;
    }

    public int getYritykset() {
        return this.yritykset;
    }
}
