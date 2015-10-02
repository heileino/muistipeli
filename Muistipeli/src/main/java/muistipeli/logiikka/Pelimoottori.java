/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private Pelipoyta pelipoyta;
    private Pelaaja pelaaja;
    private List<Kortti> loydetytKortit;
    private List<Integer> valitutIndeksit;
    private int korttejaJaljella;

    public Pelimoottori() {
        pelipoyta = new Pelipoyta();
        loydetytKortit = new ArrayList<>();
        valitutIndeksit = new ArrayList<>();
        pelaaja = new Pelaaja();
        korttejaJaljella = pelipoyta.getTaulukko().length;
    }

    public void alustaPoytaPelikuntoon() {
        pelipoyta.taytaPoyta();
        pelipoyta.sekoitaKortit();
    }

    public boolean onkoJoLoydetty(Kortti kortti) {
        return (this.loydetytKortit.contains(kortti));
    }

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

    public int getKorttejaJaljella() {
        return this.korttejaJaljella;
    }

    public String getKorttejaJaljellaTekstina() {
        return "Kortteja jäljellä: " + getKorttejaJaljella();
    }

    public List<Kortti> getLoydetytKortit() {
        return this.loydetytKortit;
    }

    public void lisaaValittuihin(int indeksi) {
        this.valitutIndeksit.add(indeksi);
    }

    public List<Integer> getValitutIndeksit() {
        return this.valitutIndeksit;
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

    public int montakoValittu() {
        return getValitutIndeksit().size();
    }

    public void lisaaValintayritys() {
        this.pelaaja.lisaaYritys();
    }

    public String getYritystenMaaraTekstina() {
        return pelaaja.getYrityksetTekstina();
    }

    public int getYritystenMaaraLukuna() {
        return pelaaja.getYritykset();
    }

    /**
     * Tutkitaan valittuja kortteja ja tehdään pelin sääntöjen mukaisia
     * toimenpiteitä.
     *
     * @return totuusarvo siitä, löytyikö pari vai ei
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

    public Kortti getKortti(int indeksi) {
        return pelipoyta.getKorttiTaulukosta(indeksi);
    }

    public boolean jatketaankoPelia() {
        return getKorttejaJaljella() > 1;
    }

    public Pelipoyta getPelipoyta() {
        return this.pelipoyta;
    }

}
