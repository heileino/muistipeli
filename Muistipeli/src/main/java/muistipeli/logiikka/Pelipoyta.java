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
    private final int TAULUKON_KOKO;

    /**
     * Konstruktori luo uuden korttitaulukon.
     *
     * @param koko pelipöytää kuvaavan korttitaulukon koko
     */
    public Pelipoyta(int koko) {
        this.TAULUKON_KOKO = koko;
        korttitaulukko = new Kortti[TAULUKON_KOKO];
    }

    /**
     * Metodi täyttää pelialustaa kuvaavan korttitaulukon Kortti-luokan
     * ilmentymillä. Korttitaulukko täytetään kahdessa osassa siten, että saman
     * tunnuksen sisältäviä kortteja tulee taulukkoon kaksi.
     *
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
     * satunnaiseen järjestykseen.
     */
    public void sekoitaTaulukonKortit() {
        Collections.shuffle(Arrays.asList(korttitaulukko));
    }

    /**
     *
     * Metodi testaa näkyy parametrin indeksiä vastaavan korttitaulukon kortin
     * kuvapuoli pelaajalle.
     *
     * @param i indeksi korttitaulukossa
     *
     * @return tosi, jos kortti on asennossa kuvapuoli ylöspäin
     */
    public boolean nakyykoKortinKuvapuoli(int i) {
        return getKorttiTaulukosta(i).nakyykoKuvapuoli();
    }

    /**
     *
     * Metodi testaa sisältävätkö parametrina saadut taulukkoindeksit keskenään
     * saman korttitunnuksen.
     *
     * @param indeksi1 ensimmäisen kortin indeksipaikka korttitaulukossa
     * @param indeksi2 toisen kortin indeksipaikka taulukossa
     *
     * @return totuusarvo kahden kortin tunnuksen samuusvertailusta
     */
    public boolean onkoKorteillaSamaTunnus(int indeksi1, int indeksi2) {
        return korttitaulukko[indeksi1].getTunnus().equals(korttitaulukko[indeksi2].getTunnus());
    }

    /**
     * Metodi testaa sisältävätkö parametrina saadut kortit saman
     * korttitunnuksen
     *
     * @param kortti1 ensimmäinen vertailtava kortti
     * @param kortti2 toinen vertailtava kortti
     *
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
     * @param indeksi valittu taulukon indeksi
     */
    public void piilotaKortinKuva(int indeksi) {
        korttitaulukko[indeksi].naytaSelkapuoli();
    }

    public Kortti[] getTaulukko() {

        return korttitaulukko;
    }

    /**
     * Metodi kertoo kortin sijainnin taulukossa indeksinä.
     *
     * @param kortti korttitaulukossa oleva muistipelikortti
     *
     * @return parametrina olevan kortin taulukkoindeksi
     */
    public int getKortinIndeksi(Kortti kortti) {

        int indeksi = -1;

        for (int i = 0; i < korttitaulukko.length; i++) {
            if (korttitaulukko[i].equals(kortti)) {
                indeksi = i;
            }
        }
        return indeksi;
    }

    /**
     * Metodi palauttaa indeksiä vastaavan kortin korttitaulukosta
     *
     * @param indeksi paikka korttitaulukossa
     *
     * @return korttitaulukossa parametrin määrittämässä indeksissä oleva kortti
     */
    public Kortti getKorttiTaulukosta(int indeksi) {
        return getTaulukko()[indeksi];
    }

    /**
     * Metodi asettaa kaikkien korttitaulukossa olevien korttien näkyvyydeksi
     * selkäpuolen.
     */
    public void kaannaKaikkiKortitSelkapuolelle() {
        for (int i = 0; i < getTaulukko().length; i++) {
            getTaulukko()[i].naytaSelkapuoli();
        }
    }

    /**
     * Metodi kertoo onko parametrina annetussa paikassa oleva kortti kuvapuoli
     * ylöspäin.
     *
     * @param indeksi paikka korttitaulukossa
     * @return tosi, jos kortin kuvapuoli on näkyvissä
     */
    public boolean getKortinKuvapuolenNakyvyys(int indeksi) {
        return getTaulukko()[indeksi].nakyykoKuvapuoli();
    }
}
