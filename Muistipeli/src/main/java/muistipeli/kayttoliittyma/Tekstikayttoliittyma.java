/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.kayttoliittyma;

import java.util.Scanner;
import muistipeli.logiikka.Pelaaja;
import muistipeli.logiikka.Pelipoyta;

/**
 * Muistipelin konsolipohjainen käyttöliittymä. Toimii tällä hetkellä ainoastaan
 * sovelluksen ominaisuuksien testaamisen apuvälineenä, eikä ole tässä vaiheessa
 * varautunut poikkeuksiin tai kaikkiin peliskenaarioihin.
 *
 * @author Heikki Leinonen
 */
public class Tekstikayttoliittyma {

    Scanner lukija;
    Pelipoyta pelipoyta;
    Pelaaja pelaaja;

    public Tekstikayttoliittyma(Pelipoyta poyta) {
        lukija = new Scanner(System.in);
        this.pelipoyta = poyta;
        pelaaja = new Pelaaja();
        kaynnistaPeli();
    }

    public void kaynnistaPeli() {
        pelipoyta.taytaPoyta();
        pelipoyta.taytaPoyta();

        tulostaPoytaKonsoliin();

        do {
            // Ensimmäinen valinta. Varmistetaan, ettei jo löydettyjä kortteja valita.
            int ensimmainenValinta = -1;
            boolean hyvaksyttyValinta = false;
            while (!hyvaksyttyValinta) {
                System.out.print("Valitse ensimmäisen kortin numero [1-16] (tai lopeta 0:lla): ");
                ensimmainenValinta = Integer.parseInt(lukija.nextLine());
                if (ensimmainenValinta == 0) {
                    System.out.println("Peli keskeytyi. Kiitos ja näkemiin!");
                    break;
                }
                if (pelipoyta.onkoJoLoydetty(pelipoyta.getTaulukko()[ensimmainenValinta - 1])) {
                    System.out.println("Valitsemasi kortti on jo löytyneiden parien joukossa!");
                } else {
                    hyvaksyttyValinta = true;
                }
            }

            pelipoyta.paljastaKortti(ensimmainenValinta);
            tulostaPoytaKonsoliin();

            // Toinen valinta. Varmistetaan, ettei samaa tai jo löydettyjä kortteja valita uudelleen.
            int toinenValinta = -1;
            hyvaksyttyValinta = false;
            while (!hyvaksyttyValinta) {
                System.out.print("Valitse toisen kortin numero [1-16]: ");

                toinenValinta = Integer.parseInt(lukija.nextLine());
                if (toinenValinta == ensimmainenValinta) {
                    System.out.println("Et voi valita samaa korttia uudelleen!");
                } else if (pelipoyta.onkoJoLoydetty(pelipoyta.getTaulukko()[toinenValinta - 1])) {
                    System.out.println("Valitsemasi kortti on jo löytyneiden parien joukossa!");
                } else {
                    hyvaksyttyValinta = true;
                }
            }

            pelipoyta.paljastaKortti(toinenValinta);
            tulostaPoytaKonsoliin();
            if (pelipoyta.onkoSama(ensimmainenValinta, toinenValinta)) {
                pelipoyta.vahennaKorttejaJaljella();
                pelipoyta.lisaaKortitLoytyneeksi(pelipoyta.getTaulukko()[ensimmainenValinta - 1], pelipoyta.getTaulukko()[toinenValinta - 1]);
                System.out.println("Hyvä! Pari löytyi!");
            } else {
                System.out.println("Valitettavasti paria ei löytynyt");
                pelipoyta.piilotaKortti(ensimmainenValinta);
                pelipoyta.piilotaKortti(toinenValinta);
            }
            pelaaja.lisaaYritys();
            System.out.println("");
            System.out.println("Tehtyjä yrityksiä " + pelaaja.getYritykset());
            System.out.println("Kortteja jäljellä: " + pelipoyta.getKorttejaJaljella());
        } while (this.pelipoyta.getKorttejaJaljella() > 0); //toistetaan niin kauan, että kaikki parit on löydetty

        System.out.println("Peli on päättynyt! Selvitit muistipelin " + pelaaja.getYritykset() + ":lla yrityksellä");

    }

    public void tulostaPoytaKonsoliin() {
        // piirtää korttien paikat taulukoksi numerojärjestyksessä
        int laskuri = 1; // laskuri ilmaisee numeropaikan 1-16 taulukossa
        System.out.println("");
        for (int i = 0; i < this.pelipoyta.getTaulukko().length / 4; i++) {
            for (int j = 0; j < this.pelipoyta.getTaulukko().length / 4; j++) {
                if (this.pelipoyta.getTaulukko()[laskuri - 1].nakyykoKuvapuoli()) { // tarkistetaan täytyykö kortti kääntää
                    System.out.print(" " + this.pelipoyta.getTaulukko()[laskuri - 1] + " ");
                } else {
                    System.out.print(" " + laskuri + " ");
                }
                laskuri++;
            }
            System.out.println("");
        }
    }
}
