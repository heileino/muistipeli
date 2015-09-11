/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

/**
 * Kortti-luokkaa kuvaa yksittäisen muistikortin ominaisuuksia
 *
 * @author Heikki Leinonen
 */
public class Kortti {

    int tunnus;
    boolean nakyy; // kertoo onko kortti käännetty kuvapuolelle vai ei

    public Kortti(int tunnus) {
        nakyy = false;
        this.tunnus = tunnus;
    }

    public void naytaKuvapuoli() {

        if (nakyy) {
            // virhe, tämä kortti on jo käännetty

        } else {
            nakyy = true;
        }

    }

    public void naytaSelkapuoli() {

        if (nakyy) {
            nakyy = false;
        }
    }

    public boolean getNakyvyys() {
        return this.nakyy;
    }

    public void setTunnus(int tunnus) {
        this.tunnus = tunnus;
    }

    public int getTunnus() {
        return tunnus;
    }

    public String toString() {
        return "kortti" + tunnus;
    }
}
