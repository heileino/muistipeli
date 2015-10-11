package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka huolehtii pelin kulusta. Luokka toimii linkkinä käyttöliittymäluokkien
 * ja muiden logiikka-luokkien välillä.
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private Pelipoyta pelipoyta;
    private YritysmaaraLaskuri yrityslaskuri;
    private LoytyneetKortit loytyneetKortit;
    private ValitutPaikat valitutPaikat;
    private LoytamattomatKorttiparit parejaLoytamatta;
    private ParasTulos parasTulos;
    private final int PARIENMAARA = 8;
    private String tiedostonimi;

    /**
     * Konstruktori
     */
    public Pelimoottori() {

        pelipoyta = new Pelipoyta();
        this.tiedostonimi = "parastulos.txt";
        loytyneetKortit = new LoytyneetKortit();
        valitutPaikat = new ValitutPaikat();
        parejaLoytamatta = new LoytamattomatKorttiparit(PARIENMAARA);
        parasTulos = new ParasTulos(tiedostonimi);
        yrityslaskuri = new YritysmaaraLaskuri();

    }

    public boolean onkoKorttiValittavissa(int i) {
        Kortti valittuKortti = pelipoyta.getKorttiTaulukosta(i);
        return !valittuKortti.nakyykoKuvapuoli();
    }

    
    /**
     * Tutkitaan valittuja kortteja ja tehdään pelin sääntöjen mukaisia
     * toimenpiteitä.
     *
     * @return totuusarvo siitä, löytyikö pari vai ei.
     */
    public boolean loytyikoPari() {
        Kortti kortti1 = pelipoyta.getKorttiTaulukosta(this.valitutPaikat.getValitutIndeksit().get(0));
        Kortti kortti2 = pelipoyta.getKorttiTaulukosta(this.valitutPaikat.getValitutIndeksit().get(1));

        return pelipoyta.onkoKorteillaSamaTunnus(kortti1, kortti2);
    }

    public void kaannaKortitNurin(Kortti kortti1, Kortti kortti2) {
        pelipoyta.piilotaKortinKuva(pelipoyta.getKortinIndeksi(kortti1));
        pelipoyta.piilotaKortinKuva(pelipoyta.getKortinIndeksi(kortti2));
    }

    /**
     * Metodi selvittää, jatketaanko peliä vielä. Se tapahtuu tarkistamalla
     * jäljellä olevien korttien määrän.
     *
     * @return palauttaa boolean totuusarvon pelin jatkumisesta
     */
    public boolean jatketaankoPelia() {
        return parejaLoytamatta.getParejaLoytymatta() > 0;
    }

    public void vahennaLoytamattomienParienMaaraa() {
        parejaLoytamatta.vahennaParejaLoytymatta();
    }

    /**
     * Metodi testaa, onko pelin tulos parempi kuin nykyinen paras tulos.
     *
     * @return
     */
    public boolean onUusiParasTulos() {
        if (this.parasTulos.getParasTulos() == 0) {
            return true;
        } else {
            return this.yrityslaskuri.getYritysmaara() < parasTulos.getParasTulos();
        }

    }

    public void asetaParasTulos() {
        parasTulos.setParasTulos(this.yrityslaskuri.getYritysmaara());

    }

    public void lataaParasTulos() {
        parasTulos.lataaParasTulos();
    }

    public LoytyneetKortit getLoytyneetKortit() {
        return this.loytyneetKortit;
    }

    public Kortti getKortti(int indeksi) {
        return pelipoyta.getKorttiTaulukosta(indeksi);
    }


    public void kaannaKaikkiKortitSelkapuoliYlos() {

        for (int i = 0; i < pelipoyta.getTaulukko().length; i++) {
            pelipoyta.getTaulukko()[i].naytaSelkapuoli();
        }
    }

    public String getTiedostonimi() {
        return this.tiedostonimi;
    }

    /*
     Merkkijonoja palauttavia gettereitä käyttöliittymien käyttöön.
     */
    public String getParasTulosTekstina() {
        return this.parasTulos.toString();
    }

    public String getParejaLoytamattaTekstina() {
        return this.parejaLoytamatta.toString();
    }

    public String getYritystenMaaraTekstina() {
        return this.yrityslaskuri.toString();
    }

    /*
     Lukuja palauttavia gettereitä
     */
    public int getParejaLoytymatta() {
        return this.parejaLoytamatta.getParejaLoytymatta();
    }

    /**
     * Olioita palauttavia gettereitä käyttöliittymien käyttöön.
     */
    public ParasTulos getParasTulos() {
        return this.parasTulos;
    }

    public Pelipoyta getPelipoyta() {
        return this.pelipoyta;
    }
    
    public ValitutPaikat getValitutPaikat() {
        return this.valitutPaikat;
    }
    
    public YritysmaaraLaskuri getYritysmaaraLaskuri(){
        return this.yrityslaskuri;
    }
}
