/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka huolehtii pelin kulusta. Se toimii linkkinä käyttöliittymäluokkien ja
 * Pelipoyta-luokan välillä.
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private Pelipoyta pelipoyta;
    private Pelaaja pelaaja;
    private List<Kortti> loydetytKortit;
    private List<Integer> valitutIndeksit;
    private int korttejaJaljella;

    /**
     * Konstruktori luo uuden pelipöydän, löydettyjen korttien listan,
     * valittujen taulukkoindeksien listan ja pelaajan. Lisäksi se alustaa
     * jäljellä olevien kortteja laskevan muuttujan vastaamaan taulukon kokoa.
     */
    public Pelimoottori() {
        pelipoyta = new Pelipoyta();
        loydetytKortit = new ArrayList<>();
        valitutIndeksit = new ArrayList<>();
        pelaaja = new Pelaaja();
        korttejaJaljella = pelipoyta.getTaulukko().length;
    }

    /**
     * Metodi täyttää pelipöydän korteilla ja sekoittaa ne satunnaiseen
     * järjestykseen
     */
    public void alustaPoytaPelikuntoon() {
        pelipoyta.taytaPoyta();
        pelipoyta.sekoitaKortit();
    }

    /**
     * Metodi testaa, onko parametrina annettu kortti löydettyjen korttien
     * listassa
     *
     * @param kortti Pelitaulukossa oleva Kortti-luokan ilmentymä
     * @return boolean-totuusarvo siitä, onko parametrina annettu kortti jo
     * löydettyjen korttien listassa
     */
    public boolean onkoJoLoydetty(Kortti kortti) {
        return (this.loydetytKortit.contains(kortti));
    }

    /**
     * Metodi lisaa valitut kortit löytyneiden korttien listalle
     *
     * @param kortti1 ensimmäisenä valittu kortti
     * @param kortti2 toisena valittu kortti
     */
    public void lisaaKortitLoytyneeksi(Kortti kortti1, Kortti kortti2) {
        this.loydetytKortit.add(kortti1);
        this.loydetytKortit.add(kortti2);
    }

    /**
     * Metodi vähentää taulukosta löydetyn parin korttejaJaljella
     */
    public void vahennaKorttejaJaljella() {
        this.korttejaJaljella = this.korttejaJaljella - 2;
    }

    /**
     * Metodi vaihtaa jäljellä olevien korttien määrää laskevan muuttujan arvon
     * takaisin alkuarvoonsa, eli korttitaulukon koon mukaiseksi arvoksi.
     */
    public void alustaKorttejaJaljella() {
        this.korttejaJaljella = pelipoyta.getTaulukko().length;
    }

    public void lisaaValittuihin(int indeksi) {
        this.valitutIndeksit.add(indeksi);
    }

    public void tyhjaaValitutIndeksit() {
        this.valitutIndeksit = new ArrayList<>();
    }

    public boolean onkoKorttiValittavissa(int i) {
        Kortti valittuKortti = pelipoyta.getTaulukko()[i];
        return !valittuKortti.nakyykoKuvapuoli();
    }

    public void valitseKortti(int i) {
        lisaaValittuihin(i);
        pelipoyta.paljastaKortti(i);
    }

    /**
     * Metodi kertoo, kuinka monta korttia on valittu kyseisellä yrityskerralla.
     * @return int-tyypin luku, joka kertoo valittujen indeksien määrän.
     */
    public int montakoValittu() {
        return getValitutIndeksit().size();
    }

    public void lisaaValintayritys() {
        this.pelaaja.lisaaYritys();
    }

    /**
     * Tutkitaan valittuja kortteja ja tehdään pelin sääntöjen mukaisia
     * toimenpiteitä.
     *
     * @return totuusarvo siitä, löytyikö pari vai ei.
     */
    public boolean loytyikoPari() {
        Kortti kortti1 = pelipoyta.getKorttiTaulukosta(getValitutIndeksit().get(0));
        Kortti kortti2 = pelipoyta.getKorttiTaulukosta(getValitutIndeksit().get(1));

        if (pelipoyta.onkoSamaKortti(kortti1, kortti2)) {
            lisaaKortitLoytyneeksi(kortti1, kortti2);
            vahennaKorttejaJaljella();
            return true;

        } else {
            pelipoyta.piilotaKortti(pelipoyta.getKortinIndeksi(kortti1));
            pelipoyta.piilotaKortti(pelipoyta.getKortinIndeksi(kortti2));
            return false;
        }
    }

    /**
     * Metodi selvittää, jatketaanko peliä vielä. Se tapahtuu tarkistamalla
     * jäljellä olevien korttien määrän.
     *
     * @return palauttaa boolean totuusarvon pelin jatkumisesta
     */
    public boolean jatketaankoPelia() {
        return getKorttejaJaljella() > 1;
    }

    public Kortti getKortti(int indeksi) {
        return pelipoyta.getKorttiTaulukosta(indeksi);
    }

    public Pelipoyta getPelipoyta() {
        return this.pelipoyta;
    }

    public String getYritystenMaaraTekstina() {
        return pelaaja.getYrityksetTekstina();
    }

    public int getYritystenMaaraLukuna() {
        return pelaaja.getYritykset();
    }

    public int getKorttejaJaljella() {
        return this.korttejaJaljella;
    }

    public String getKorttejaJaljellaTekstina() {
        return "Kortteja jäljellä: " + getKorttejaJaljella();
    }

    public List<Kortti> getLoydetytKortit() {
        return this.loydetytKortit;
    }

    public List<Integer> getValitutIndeksit() {
        return this.valitutIndeksit;
    }
}
