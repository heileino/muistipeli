package muistipeli.logiikka;

import java.util.Arrays;
import java.util.Collections;

/**
 * Luokka kuvaa muistipelin pelialustaa. Pelipoytaa vastaa korteista koostuva
 * taulukko. Luokka tarjoaa erilaisia tähän taulukkoon ja sen sisältöön
 * liittyviä metodeja.
 *
 * @author Heikki Leinonen
 */
public class Pelipoyta {

    private final Kortti[] korttitaulukko;
    private int TAULUKON_KOKO=16;
    /**
     * Konstruktori luo uuden korttitaulukon.
     */
    public Pelipoyta() {

        korttitaulukko = new Kortti[TAULUKON_KOKO];
    }

    /**
     * Metodi täyttää pelialustaa kuvaavan korttitaulukon Kortti-luokan
     * ilmentymillä. Korttitaulukko täytetään kahdessa osassa siten, että saman
     * tunnuksen sisältäviä kortteja tulee taulukkoon kaksi.
     *
     * @see muistipeli.logiikka.Kortti
     */
    public void asetaKortitTaulukkoon() {
        int puolikasTaulukonPituus = korttitaulukko.length / 2;
        for (int i = 0; i < puolikasTaulukonPituus; i++) {
            korttitaulukko[i] = new Kortti("" + (i + 1));
        }

        for (int i = puolikasTaulukonPituus; i < korttitaulukko.length; i++) {
            korttitaulukko[i] = new Kortti("" + ((i - korttitaulukko.length / 2) + 1));
        }
    }

    /**
     * Metodi sekoittaa taulukon sisältämät Kortti-luokan ilmentymät
     * satunnaiseen järjestykseen
     */
    public void sekoitaTaulukonKortit() {

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
    public boolean onkoKorteillaSamaTunnus(int indeksi1, int indeksi2) {

        return this.korttitaulukko[indeksi1].getTunnus().equals(this.korttitaulukko[indeksi2].getTunnus());
    }

    /**
     * Metodi testaa sisältävätkö parametrina saadut kortit saman
     * korttitunnuksen
     *
     * @param kortti1 ensimmäinen vertailtava kortti
     * @param kortti2 toinen vertailtava kortti
     * @return totuusarvo kahden kortin tunnuksen samuusvertailusta.
     */
    public boolean onkoKorteillaSamaTunnus(Kortti kortti1, Kortti kortti2) {

        return kortti1.getTunnus().equals(kortti2.getTunnus());
    }

    /**
     * Metodi kääntää valitussa paikassa taulukkoa olevan kortin kuvapuolen
     * esiin
     *
     * @param indeksi valittu taulukon indeksi
     */
    public void paljastaKortinKuva(int indeksi) {

        korttitaulukko[indeksi].naytaKuvapuoli();
    }

    /**
     * Metodi kääntää valitussa paikassa olevan kortin selkäpuolen esiin.
     *
     * @param indeksi valittu taulukon paikkanumero
     */
    public void piilotaKortinKuva(int indeksi) {

        korttitaulukko[indeksi].naytaSelkapuoli();
    }

    public Kortti[] getTaulukko() {

        return this.korttitaulukko;
    }

    /**
     * Metodi kertoo kortin sijainnin taulukossa.
     *
     * @param kortti
     * @return palauttaa parametrina olevan kortin indeksin taulukossa
     */
    public int getKortinIndeksi(Kortti kortti) {

        int indeksi = -1;

        for (int i = 0; i < this.korttitaulukko.length; i++) {
            if (this.korttitaulukko[i].equals(kortti)) {
                indeksi = i;
            }
        }
        return indeksi;
    }

    public Kortti getKorttiTaulukosta(int indeksi) {
    
        return this.getTaulukko()[indeksi];
    }
    

    public void kaannaKaikkiKortitSelkapuolelle() {

        for (int i = 0; i < getTaulukko().length; i++) {
            getTaulukko()[i].naytaSelkapuoli();
        }
    }
}
