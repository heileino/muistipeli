/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

/**
 * Pelimoottori-luokka huolehtii pelin etenemisestä
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private Pelipoyta poyta;
    private int pisteet;

    public Pelimoottori() {
        // valmistelee peliä
        pisteet = 0;
        poyta = new Pelipoyta();
        poyta.taytaPoyta();
        poyta.sekoitaPoyta();

    }

    public void pelaa() {
        /*PELIN KULKUSUUNNITELMA    

         odotetaan pelaajan siirtoa - toteutetaan myöhemmin
         pelaajan valitseman kortin selkäpuoli vaihdetaan kuvapuoleen
         poyta.getTaulukko()[valittuPaikka1].naytaKuvapuoli();
        
         toistetaan sama toisen kerran
        
         lisätään yksi yrityspiste
         lisaaPiste();
        
         tarkastetaan, ovatko pelaajan valitsemat kaksi korttia pareja
         poyta.onkoSama(paikka1, paikka2)
         
         - jos ovat, kortit poistetaan (tai jätetään kuvapuoli ylöspäin)
         -- tarkastetaan, onko pöydällä vielä kortteja - toteutetaan myöhemmin
         --- jos ei niin peli päättyy - ilmoitetaan lopulliset pisteet
         - muuten valitut kortit käännetään takaisin selkäpuolelle
         poyta.getTaulukko()[valittuPaikka1].naytaSelkapuoli();
         poyta.getTaulukko()[valittuPaikka2].naytaSelkapuoli();
        
         toistetaan alusta
         */
    }

    public void lisaaPiste() {
        this.pisteet++;
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public Pelipoyta getPoyta() {
        return poyta;
    }

    

}
