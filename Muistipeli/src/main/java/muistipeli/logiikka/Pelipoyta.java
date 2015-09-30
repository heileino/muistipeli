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
 * Luokka luo pelialustan. Lisäksi se tarjoaa metodeja, joilla pelialusta
 * täytetään korteilla.
 *
 * @author Heikki Leinonen
 */
public class Pelipoyta {

    private Kortti[] korttitaulukko;
    private int korttejaJaljella;
    private List<Kortti> loydetytKortit;
    private List<Integer> valitutKorttiIndeksit;

    /**
     * Konstruktori luo uuden korttitaulukon ja uuden löydettyjen korttien
     * listan. Lisäksi se alustaa korttien määrää laskevan ilmentymämuuttujan
     * korttitaulukon pituuden arvolla.
     */
    public Pelipoyta() {
        korttitaulukko = new Kortti[16];
        korttejaJaljella = korttitaulukko.length;
        loydetytKortit = new ArrayList<>();
        valitutKorttiIndeksit = new ArrayList<>();
    }

    /**
     * Metodi täyttää pelialustaa kuvaavan korttitaulukon Kortti-luokan
     * ilmentymillä.
     *
     * @see muistipeli.logiikka.Kortti
     */
    public void taytaPoyta() {
        // Täytetään korttitaulukko kahdessa osassa siten, että saman tunnuksen sisältäviä kortteja tulee taulukkoon kaksi.
        for (int i = 0; i < korttitaulukko.length / 2; i++) {
            korttitaulukko[i] = new Kortti(i + 1);
        }
        for (int i = korttitaulukko.length / 2; i < korttitaulukko.length; i++) {
            korttitaulukko[i] = new Kortti((i - korttitaulukko.length / 2) + 1);
        }
    }

    /**
     * Metodi sekoittaa taulukon sisältämät Kortti-luokan ilmentymät
     * satunnaiseen järjestykseen
     */
    public void sekoitaKortit() {
        Collections.shuffle(Arrays.asList(korttitaulukko));
    }

    /**
     *
     * Metodi testaa sisältävätkö parametrina saadut taulukkopaikat saman
     * korttitunnuksen.
     *
     * @param paikka1 ensimmäisen valitun paikan numero
     * @param paikka2 toisen valitun paikan numero
     * @return totuusarvo kahden kortin tunnuksen samuusvertailusta.
     */
    public boolean onkoSama(int paikka1, int paikka2) {
        return this.korttitaulukko[paikka1 - 1].tunnus == this.korttitaulukko[paikka2 - 1].tunnus;
    }

    public boolean onkoSamaKortti(Kortti kortti1, Kortti kortti2) {
        return kortti1.getTunnus() == kortti2.getTunnus();
    }

    /**
     * Metodi kääntää valitussa paikassa olevan kortin kuvapuolen esiin
     *
     * @param numero valittu taulukon paikkanumero
     */
    public void paljastaKortti(int numero) {
        korttitaulukko[numero - 1].naytaKuvapuoli();
    }

    /**
     * Metodi kääntää valitussa paikassa olevan kortin selkäpuolen esiin.
     *
     * @param numero valittu taulukon paikkanumero
     */
    public void piilotaKortti(int numero) {
        korttitaulukko[numero - 1].naytaSelkapuoli();
    }

    /**
     * Metodi vähentää taulukosta löydetyn parin korttejaJaljella
     */
    public void vahennaKorttejaJaljella() {
        this.korttejaJaljella = this.korttejaJaljella - 2;
    }

    public boolean onkoJoLoydetty(Kortti kortti) {
        return (this.loydetytKortit.contains(kortti));
    }

    public void lisaaKorttiLoytyneisiin(Kortti kortti) {
        this.loydetytKortit.add(kortti);
    }

    /**
     * Metodi vaihtaa jäljellä olevien korttien määrää laskevan muuttujan arvon
     * takaisin alkuarvoonsa, eli korttitaulukon koon mukaiseksi arvoksi.
     */
    public void alustaKorttejaJaljella() {
        this.korttejaJaljella = this.korttitaulukko.length;
    }

    public int getKorttejaJaljella() {
        return this.korttejaJaljella;
    }

    public String getKorttejaJaljellaTekstina() {
        return "Kortteja jäljellä: " + getKorttejaJaljella();
    }

    public Kortti[] getTaulukko() {
        return this.korttitaulukko;
    }

    public List<Kortti> getLoydetytKortit() {
        return this.loydetytKortit;
    }

    public void valitseKortti(int indeksi) {
        Kortti valittuKortti = this.korttitaulukko[indeksi];
        if (onkoJoLoydetty(valittuKortti)) {

        }
    }

    public void lisaaKorttiValittuihin(int indeksi) {
        this.valitutKorttiIndeksit.add(indeksi);
    }

    public List<Integer> getKorttejaValittu() {
        return this.valitutKorttiIndeksit;
    }

    public void tyhjaaValitutKortit() {
        this.valitutKorttiIndeksit = new ArrayList<>();
    }

    public int getKortinIndeksi(Kortti kortti) {
        for (int i = 0; i < this.korttitaulukko.length; i++) {
            if (this.korttitaulukko[i].equals(kortti)) {
                return i;
            }
        }
        return -1;
    }
}
