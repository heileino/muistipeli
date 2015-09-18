/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Pelipoyta-luokka luo pelialustan, jossa muistikortit sijaitsevat
 *
 * @author Heikki Leinonen
 */
public class Pelipoyta {

    private Kortti[] taulukko;
    private int korttejaJaljella;
    private List<Kortti> loydetytKortit;

    public Pelipoyta() {
        taulukko = new Kortti[16]; // Luodaan pöydälle tilaa 16:lle kortille
        korttejaJaljella = 16;
        loydetytKortit = new ArrayList<>();
        taytaPoyta(); // täytetään pöytä ensin korttien numerojärjestyksessä
        sekoitaPoyta(); // sekoitetaan korttien paikat

    }

    public void taytaPoyta() {
        // Täytetään taulukko kahdessa osassa siten, että saman tunnuksen sisältäviä kortteja tulee taulukkoon kaksi.
        for (int i = 0; i < taulukko.length / 2; i++) {
            taulukko[i] = new Kortti(i + 1);
        }
        for (int i = taulukko.length / 2; i < taulukko.length; i++) {
            taulukko[i] = new Kortti((i - taulukko.length / 2) + 1);
        }
    }

    public void sekoitaPoyta() {
        Collections.shuffle(Arrays.asList(taulukko));
    }

    public boolean onkoSama(int paikka1, int paikka2) {
        return this.taulukko[paikka1 - 1].tunnus == this.taulukko[paikka2 - 1].tunnus;
    }

    public void paljastaKortti(int numero) {
        taulukko[numero - 1].naytaKuvapuoli();
    }

    public void piilotaKortti(int numero) {
        taulukko[numero - 1].naytaSelkapuoli();
    }

    public void vahennaKorttejaJaljella() {
        this.korttejaJaljella = this.korttejaJaljella - 2;
    }

    public boolean onkoJoLoydetty(Kortti kortti) {
        return (this.loydetytKortit.contains(kortti));
    }

    public void lisaaKorttiLoytyneisiin(Kortti kortti) {
        this.loydetytKortit.add(kortti);
    }

    public int getKorttejaJaljella() {
        return this.korttejaJaljella;
    }

    public Kortti[] getTaulukko() {
        return this.taulukko;
    }

    public List<Kortti> getLoydetytKortit() {
        return this.loydetytKortit;
    }

}
