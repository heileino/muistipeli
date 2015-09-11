/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import java.util.Arrays;
import java.util.Collections;

/**
 * Pelipoyta-luokka luo pelialustan, jossa muistikortit sijaitsevat
 *
 * @author Heikki Leinonen
 */
public class Pelipoyta {

    private Kortti[] taulukko;

    public Pelipoyta() {
        taulukko = new Kortti[20]; // Luodaan pöydälle tilaa 20:lle kortille
    }

    public void taytaPoyta() {
        for (int i = 0; i < taulukko.length / 2; i++) {
            taulukko[i] = new Kortti(i);
        }

        for (int i = taulukko.length / 2; i < taulukko.length; i++) {

            taulukko[i] = new Kortti(i - taulukko.length / 2);

        }
    }

    public void sekoitaPoyta() {
        Collections.shuffle(Arrays.asList(taulukko));
    }

    
    public boolean onkoSama(int paikka1, int paikka2){
        return this.taulukko[paikka1].tunnus==this.taulukko[paikka2].tunnus;
    }
    
    
    public Kortti[] getTaulukko() {
        return this.taulukko;
    }

    @Override
    public String toString() {
        return Arrays.toString(taulukko);
    }

}
