/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

/**
 * Luokan ilmentymät esittävät Muistipelin pelaajaa ja ylläpitävät tilastoa
 * arvausyritysten määrästä.
 *
 * @author Heikki Leinonen
 */
public class Pelaaja {

    private int yritykset;

    /**
     * Konstruktori asettaa pelaajan yritysmäärään arvoon 0.
     */
    public Pelaaja() {
        this.yritykset = 0;
    }

    /**
     * Metodi lisää pelaajalle yhden yrityksen lisää.
     */
    public void lisaaYritys() {
        this.yritykset++;
    }

    public int getYritykset() {
        return this.yritykset;
    }

    public String getYrityksetTekstina() {
        return "Yrityksiä: " + this.getYritykset();
    }
}
