/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

/**
 * Luokka kuvaa yksittäisen muistikortin ominaisuuksia.
 *
 * @author Heikki Leinonen
 */
public class Kortti {

    String tunnus;
    boolean nakyy; // kertoo onko kortti käännetty kuvapuolelle vai ei

    /**
     * Konstruktori asettaa kortin näkyvyydeksi epätoden ja asettaa kortin
     * tunnukseksi parametrina saadun arvon.
     *
     * @param tunnus korttiparin yksilöivä tunnus
     */
    public Kortti(String tunnus){
        nakyy = false;
        this.tunnus = tunnus;
    }

    /**
     * Metodi asettaa kyseisen kortin kuvapuolen näkyvyyden todeksi.
     */
    public void naytaKuvapuoli() {
        nakyy = true;
    }

    /**
     * Metodi asettaa kyseisen kortin selkäpuolen näkyvyyden epätodeksi;
     */
    public void naytaSelkapuoli() {

        if (nakyy) {
            nakyy = false;
        }
    }

    public boolean nakyykoKuvapuoli() {
        return this.nakyy;
    }

    public String getTunnus() {
        return tunnus;
    }

    public String toString() {
        return "kortti_" + tunnus;
    }
}
