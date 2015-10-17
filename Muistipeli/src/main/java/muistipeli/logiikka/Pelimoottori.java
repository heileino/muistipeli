package muistipeli.logiikka;

/**
 * Luokka toimii linkkinä käyttöliittymäluokkien ja muiden logiikka-luokkien
 * välillä. Luokka tarjoaa metodeita, joilla peli etenee alusta loppuun.
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private final int KORTTIEN_MAARA;
    private String parasTulosTiedostonNimi;
    private Pelipoyta pelipoyta;
    private LoytyneetKortit loytyneetKortit;
    private ParhaanTuloksenTilasto parasTilasto;
    private ValittujenPaikkaindeksienSailio valittujenIndeksienSailio;
    private Yritysmaaralaskuri yrityslaskuri;

    /**
     * Konstruktori luo apuvälineet muistipelin pelaamiseen. Näitä ovat uusi
     * pelipöytä sekä löytyneiden korttien, parhaan tuloksen, valittujen
     * paikkojen ja yritysten laskemiseen käytetyt tilastointioliot.
     */
    public Pelimoottori() {

        KORTTIEN_MAARA = 16;
        parasTulosTiedostonNimi = "parastulos.txt";
        pelipoyta = new Pelipoyta(KORTTIEN_MAARA);
        loytyneetKortit = new LoytyneetKortit();
        parasTilasto = new ParhaanTuloksenTilasto(parasTulosTiedostonNimi);
        valittujenIndeksienSailio = new ValittujenPaikkaindeksienSailio();
        yrityslaskuri = new Yritysmaaralaskuri();
    }

    /**
     * Metodi valitsee taulukon indeksiä vastaavan kortin valituksi, mikäli se
     * on valintakelpoinen.
     *
     * @see muistipeli.logiikka.Pelimoottori#onkoKorttiValittavissa(int)
     * @see muistipeli.logiikka.Pelimoottori#valitseKortti(int)
     * @param i korttitaulukon indeksi
     * @return tosi jos valinta onnistuu
     */
    public boolean ValitaanKorttiJosMahdollista(int i) {
        if (!onkoKorttiValittavissa(i)) {
            return false;
        }
        valitseKortti(i);
        return true;
    }

    /**
     * Metodi lisää valitun kortin valittujen korttien säiliöön ja muuttaa
     * valitun kortin näkyvyyden kuvaksi.
     *
     * @see
     * muistipeli.logiikka.ValittujenPaikkaindeksienSailio#lisaaValittuihin(int)
     * @see muistipeli.logiikka.Pelipoyta#paljastaKortinKuva(int)
     * @param i korttitaulukon indeksi
     */
    public void valitseKortti(int i) {
        this.valittujenIndeksienSailio.lisaaValittuihin(i);
        this.pelipoyta.paljastaKortinKuva(i);
    }

    /**
     * Kertoo taulukon tietyssä paikassa olevan kortin asennon. Kortin täytyy olla selkäpuoli ylöspäin, jotta 
     *
     * @param i korttitaulukon indeksi
     * @return totuusarvo kortin kuvapuolen näkyvyydestä.
     */
    public boolean onkoKorttiValittavissa(int i) {

        return !pelipoyta.nakyykoKortinKuvapuoli(i);
    }

    /**
     * Metodi tarkistaa pelitilanteen yhden yrityskerran sisällä. Vaihtoehtoina
     * ovat toisen kortin valinta, mikäli kortteja on valittu vain yksi tai
     * jatko korttien vertailuun.
     *
     * @return totuusarvo siitä, onko toisen kortin valinnalle tarvetta.
     */
    public boolean valitaankoToinenKortti() {

        if (this.valittujenIndeksienSailio.montakoValittu() > 1) {
            this.yrityslaskuri.lisaaValintayritys();
            return false;
        } else {
            return true;
        }
    }

    public String getKortinTunniste(int i) {
        return this.pelipoyta.getKorttiTaulukosta(i).toString();
    }

    /**
     * Metodi tarkistaa ovatko valittujen korttien listalla olevat kortit samaa
     * paria. Mikäli kortit ovat samat,
     *
     * @return tosi jos pari löytyi.
     */
    public boolean ovatkoValinnatPareja() {
        Kortti kortti1 = getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = getKorttiValittujenKorttienJoukosta(1);

        if (pelipoyta.onkoKorteillaSamaTunnus(kortti1, kortti2)) {
            pariLoytynyt(kortti1, kortti2);
            return true;
        }

        kaannaKortitNurin(kortti1, kortti2);

        return false;
    }

    public Kortti getKorttiValittujenKorttienJoukosta(int valinnanIndeksi) {
        return this.pelipoyta.getKorttiTaulukosta(this.valittujenIndeksienSailio.getValitutIndeksit().get(valinnanIndeksi));
    }

    /**
     * Metodin tehtävä on kutsua parin löytymisestä seuraavia pelisääntöjen
     * mukaisia toimenpiteitä.
     *
     * @param kortti1
     * @param kortti2
     */
    public void pariLoytynyt(Kortti kortti1, Kortti kortti2) {
        this.loytyneetKortit.lisaaKortitLoytyneeksi(kortti1, kortti2);
    }

    public void kaannaKortitNurin(Kortti kortti1, Kortti kortti2) {
        this.pelipoyta.piilotaKortinKuva(this.pelipoyta.getKortinIndeksi(kortti1));
        this.pelipoyta.piilotaKortinKuva(this.pelipoyta.getKortinIndeksi(kortti2));
    }

    /**
     * Palauttaa kyseisellä pelin hetkellä löytämättä olevien parien lukumäärän.
     *
     * @return parien määrä.
     */
    public int getLoytymattomienParienLukumaara() {
        return (KORTTIEN_MAARA - getLoytyneet().getLoydetytKortit().size()) / 2;
    }

    /**
     * Metodi selvittää, jatketaanko peliä vielä päättyneen yrityksen jälkeen.
     * Peli jatkuu, mikä korttipareja on vielä löytymättä.
     *
     *
     * @return tosi jos peli jatkuu
     */
    public boolean jatkuukoPeli() {
        nollaaValitutKortit();
        return getLoytymattomienParienLukumaara() > 0;
    }

    /**
     * Metodi tekee pelin aloittamiseen tarvittavat alkutoimet. Näitä ovat
     * pelipöydän täyttäminen korteilla, niiden sekoittaminen satunnaiseen
     * järjestykseen ja tähän asti saadun parhaan tuloksen lataaminen ohjelmaan.
     * käyttöön.
     */
    public void pelaaPeli() {
        this.pelipoyta.asetaKortitTaulukkoon();
        this.pelipoyta.sekoitaTaulukonKortit();
        this.parasTilasto.lataaParasTulos();
    }

    public boolean onUusiParasTulos() {
        if (this.yrityslaskuri.getYritysmaara() < this.parasTilasto.getParasTulos() || this.parasTilasto.getParasTulos() == 0) {
            this.parasTilasto.setParasTulos(this.yrityslaskuri.getYritysmaara());
            this.parasTilasto.lataaParasTulos();
            return true;
        } else {
            return false;
        }
    }

    public boolean aloitetaankoUusiPeli(int jatkohalu) {
        if (jatkohalu == 0) {
            return true;
        }
        return false;
    }

    public void nollaaValitutKortit() {
        this.valittujenIndeksienSailio = new ValittujenPaikkaindeksienSailio();
    }

    public String getTiedostonimi() {
        return this.parasTulosTiedostonNimi;
    }

    public String getYritystenMaaraTekstina() {
        return this.yrityslaskuri.toString();
    }

    public String getLoytamattomienKorttiparienMaaraTekstina() {
        return "Pareja jäljellä: " + getLoytymattomienParienLukumaara();
    }

    public String getParasTulosTekstina() {
        return this.parasTilasto.toString();
    }

    public int getYritysmaaraLukuna() {
        return this.yrityslaskuri.getYritysmaara();
    }

    public int getValinnanIndeksi(int jarjestysnro) {
        return this.valittujenIndeksienSailio.getValitutIndeksit().get(jarjestysnro);
    }

    public Pelipoyta getPelipoyta() {
        return this.pelipoyta;
    }

    public LoytyneetKortit getLoytyneet() {
        return this.loytyneetKortit;
    }

    public ParhaanTuloksenTilasto getParhaanTuloksenTilasto() {
        return this.parasTilasto;
    }

    public ValittujenPaikkaindeksienSailio getValittujenPaikkaindeksienSailio() {
        return this.valittujenIndeksienSailio;
    }

    public Yritysmaaralaskuri getYritysmaaraLaskuri() {
        return this.yrityslaskuri;
    }

}
