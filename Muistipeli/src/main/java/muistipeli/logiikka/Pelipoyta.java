/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import java.util.Arrays;
import java.util.Collections;

/**
 * Luokka kuvaa muistipelin pelialustaa. Pelipoytaa vastaa korteista koostuva
 * taulukko. Luokka tarjoaa erilaisia taulukkoon ja sen sisältöön liittyviä
 * metodeja.
 *
 * @author Heikki Leinonen
 */
public class Pelipoyta {

    private final Kortti[] korttitaulukko;

    /**
     * Konstruktori luo uuden korttitaulukon ja uuden löydettyjen korttien
     * listan. Lisäksi se alustaa korttien määrää laskevan ilmentymämuuttujan
     * korttitaulukon pituuden arvolla.
     */
    public Pelipoyta() {
        korttitaulukko = new Kortti[16];
    }

    /**
     * Metodi täyttää pelialustaa kuvaavan korttitaulukon Kortti-luokan
     * ilmentymillä. Korttitaulukko täytetään kahdessa osassa siten, että saman
     * tunnuksen sisältäviä kortteja tulee taulukkoon kaksi.
     *
     * @see muistipeli.logiikka.Kortti
     */
    public void taytaPoyta() {
        // 
        for (int i = 0; i < korttitaulukko.length / 2; i++) {
            korttitaulukko[i] = new Kortti("" + (i + 1));
        }
        for (int i = korttitaulukko.length / 2; i < korttitaulukko.length; i++) {
            korttitaulukko[i] = new Kortti("" + ((i - korttitaulukko.length / 2) + 1));
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
     * Metodi testaa sisältävätkö parametrina saadut taulukkoindeksit saman
     * korttitunnuksen.
     *
     * @param indeksi1 ensimmäisen valitun paikan numero
     * @param indeksi2 toisen valitun paikan numero
     * @return totuusarvo kahden kortin tunnuksen samuusvertailusta.
     */
    public boolean onkoSama(int indeksi1, int indeksi2) {
        return this.korttitaulukko[indeksi1].tunnus.equals(this.korttitaulukko[indeksi2].tunnus);
    }

    /**
     * Metodi testaa sisältävätkö parametrina saadut kortit saman
     * korttitunnuksen
     *
     * @param kortti1 ensimmäinen vertailtava kortti
     * @param kortti2 toinen vertailtava kortti
     * @return totuusarvo kahden kortin tunnuksen samuusvertailusta.
     */
    public boolean onkoSamaKortti(Kortti kortti1, Kortti kortti2) {
        return kortti1.getTunnus().equals(kortti2.getTunnus());
    }

    /**
     * Metodi kääntää valitussa paikassa olevan kortin kuvapuolen esiin
     *
     * @param indeksi valittu taulukon indeksi
     */
    public void paljastaKortti(int indeksi) {
        korttitaulukko[indeksi].naytaKuvapuoli();
    }

    /**
     * Metodi kääntää valitussa paikassa olevan kortin selkäpuolen esiin.
     *
     * @param indeksi valittu taulukon paikkanumero
     */
    public void piilotaKortti(int indeksi) {
        korttitaulukko[indeksi].naytaSelkapuoli();
    }

    public Kortti[] getTaulukko() {
        return this.korttitaulukko;
    }

    public int getKortinIndeksi(Kortti kortti) {
        for (int i = 0; i < this.korttitaulukko.length; i++) {
            if (this.korttitaulukko[i].equals(kortti)) {
                return i;
            }
        }
        return -1;
    }

    public Kortti getKorttiTaulukosta(int indeksi) {
        return this.getTaulukko()[indeksi];
    }
}
