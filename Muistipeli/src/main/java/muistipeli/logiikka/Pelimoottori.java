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
    private List<Kortti> loydetytKortit;
    private List<Integer> valitutIndeksit;
    private LoytamattomatKorttiparit parejaLoytamatta;
    private ParasTulos parasTulos;
    private final int PARIENMAARA = 8;
    private String tiedostonimi;

    /**
     * Konstruktori luo uuden pelipöydän, löydettyjen korttien listan,
     * valittujen taulukkoindeksien listan, pistelaskurin ja parasta tulosta
     * säilyttävän muuttujan.
     */
    public Pelimoottori() {

        pelipoyta = new Pelipoyta();
        this.tiedostonimi = "parastulos.txt";
        loydetytKortit = new ArrayList<>();
        valitutIndeksit = new ArrayList<>();
        parejaLoytamatta = new LoytamattomatKorttiparit(PARIENMAARA);
        parasTulos = new ParasTulos(tiedostonimi);
        yrityslaskuri = new YritysmaaraLaskuri();

    }

    /**
     * Metodi täyttää pelipöydän korteilla ja sekoittaa ne satunnaiseen
     * järjestykseen
     */
    public void alustaPoytaPelikuntoon() {

        pelipoyta.asetaKortitTaulukkoon();
        pelipoyta.sekoitaTaulukonKortit();
        parasTulos.lataaParasTulos(tiedostonimi);
    }

    /**
     * Metodi testaa, onko parametrina annettu kortti löydettyjen korttien
     * listassa.
     *
     * @param kortti Pelitaulukossa oleva Kortti-luokan ilmentymä.
     *
     * @return boolean-totuusarvo siitä, onko parametrina annettu kortti jo
     * löydettyjen korttien listassa.
     */
    public boolean onkoKorttiJoLoytyneissa(Kortti kortti) {
        return (this.loydetytKortit.contains(kortti));
    }

    /**
     * Metodi lisää valitut kortit löytyneiden korttien listalle.
     *
     * @param kortti1 ensimmäisenä valittu kortti
     * @param kortti2 toisena valittu kortti
     */
    public void lisaaKortitLoytyneeksi(Kortti kortti1, Kortti kortti2) {
        this.loydetytKortit.add(kortti1);
        this.loydetytKortit.add(kortti2);
    }

    /**
     * Metodi lisää yhdellä valintakerralla valitun kortin valittujen korttien
     * listalle.
     *
     * @param indeksi valitun kortin sijainti taulukossa.
     */
    public void lisaaValittuihin(int indeksi) {
        this.valitutIndeksit.add(indeksi);
    }

    public void tyhjaaValitutIndeksit() {
        this.valitutIndeksit = new ArrayList<>();
    }

    public boolean onkoKorttiValittavissa(int i) {
        Kortti valittuKortti = pelipoyta.getKorttiTaulukosta(i);
        return !valittuKortti.nakyykoKuvapuoli();
    }

    public void valitseKortti(int i) {
        lisaaValittuihin(i);
        pelipoyta.paljastaKortinKuva(i);
    }

    /**
     * Metodi kertoo, kuinka monta korttia on valittu kyseisellä yrityskerralla.
     *
     * @return int-tyypin luku, joka kertoo valittujen indeksien määrän.
     */
    public int montakoValittu() {
        return getValitutIndeksit().size();
    }

    public void lisaaValintayritys() {
        yrityslaskuri.lisaaYritys();
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

        if (pelipoyta.onkoKorteillaSamaTunnus(kortti1, kortti2)) {
//            lisaaKortitLoytyneeksi(kortti1, kortti2);
//            vahennaLoytamattomienParienMaaraa();
            return true;

        } else {
//            kaannaKortitNurin(kortti1, kortti2);
            return false;
        }
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
            return getYritystenMaaraLukuna() < parasTulos.getParasTulos();
        }

    }

    public void asetaParasTulos() {
        parasTulos.setParasTulos(this.tiedostonimi, getYritystenMaaraLukuna());

    }

    public void lataaParasTulos() {
        parasTulos.lataaParasTulos(this.tiedostonimi);
    }

    public Kortti getKortti(int indeksi) {
        return pelipoyta.getKorttiTaulukosta(indeksi);
    }

    public Pelipoyta getPelipoyta() {
        return this.pelipoyta;
    }

    public int getYritystenMaaraLukuna() {
        return yrityslaskuri.getYritysmaara();
    }

    public List<Kortti> getLoydetytKortit() {
        return this.loydetytKortit;
    }

    public List<Integer> getValitutIndeksit() {
        return this.valitutIndeksit;
    }

    public void kaannaKaikkiKortitSelkapuoliYlos() {

        for (int i = 0; i < pelipoyta.getTaulukko().length; i++) {
            pelipoyta.getTaulukko()[i].naytaSelkapuoli();
        }
    }
    
    public ParasTulos getParasTulos(){
        return this.parasTulos;
    }

    /*
     Merkkijonoja palauttavia Gettereitä käyttöliittymiä varten.
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

}
