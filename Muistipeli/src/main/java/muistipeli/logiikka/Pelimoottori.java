package muistipeli.logiikka;

/**
 * Luokka toimii linkkinä käyttöliittymäluokkien ja muiden logiikka-luokkien
 * välillä tarjoamalla eri luokkien ilmentymiä palauttavia get-metodeita.
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private Pelipoyta pelipoyta;
    private Yritysmaaralaskuri yrityslaskuri;
    private LoytyneetKortit loytyneetKortit;
    private ValitutPaikat valitutPaikat;
    private ParasTulos parasTulos;
    private final int KORTTIEN_MAARA;
    private String tiedostonimi = "parastulos.txt";

    /**
     * Konstruktori
     */
    public Pelimoottori() {
        this.KORTTIEN_MAARA = 16;
        pelipoyta = new Pelipoyta(KORTTIEN_MAARA);
        loytyneetKortit = new LoytyneetKortit();
        valitutPaikat = new ValitutPaikat();
        parasTulos = new ParasTulos(tiedostonimi);
        yrityslaskuri = new Yritysmaaralaskuri();

    }

    public boolean valintaOk(int i) {

        if (!onkoKorttiValittavissa(i)) {
            return false;
        }
        this.valitutPaikat.lisaaValittuihin(i);
        this.pelipoyta.paljastaKortinKuva(i);

        return true;
    }

    /**
     * Kertoo taulukon tietyssä paikassa olevan kortin asennon.
     *
     * @param i
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

        if (this.valitutPaikat.montakoValittu() > 1) {
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

        Kortti kortti1 = this.pelipoyta.getKorttiTaulukosta(this.valitutPaikat.getValitutIndeksit().get(0));
        Kortti kortti2 = this.pelipoyta.getKorttiTaulukosta(this.valitutPaikat.getValitutIndeksit().get(1));

        if (this.pelipoyta.onkoKorteillaSamaTunnus(kortti1, kortti2)) {
            pariLoytynyt(kortti1, kortti2);
            return true;
        }

        kaannaKortitNurin(kortti1, kortti2);

        return false;
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

    public int getLoytymattomienParienLukumaara() {
        return (this.KORTTIEN_MAARA - this.loytyneetKortit.getLoydetytKortit().size()) / 2;
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

    public void pelaaPeli() {
        this.pelipoyta.asetaKortitTaulukkoon();
        this.pelipoyta.sekoitaTaulukonKortit();
        this.parasTulos.lataaParasTulos();
    }

    public boolean onUusiParasTulos() {
        if (this.yrityslaskuri.getYritysmaara() < this.parasTulos.getParasTulos() || this.parasTulos.getParasTulos() == 0) {
            this.parasTulos.setParasTulos(this.yrityslaskuri.getYritysmaara());
            this.parasTulos.lataaParasTulos();
            return true;
        } else {
            return false;
        }
    }

    public boolean aloitetaankoUusiPeli(int jatkohalu) {
        if (jatkohalu == 0) {
            return true;
        } else {
            System.exit(0);
        }
        return false;
    }

    public void nollaaValitutKortit() {
        this.valitutPaikat = new ValitutPaikat();
    }

    public String getTiedostonimi() {
        return this.tiedostonimi;
    }

    public String getYritystenMaaraTekstina() {
        return this.yrityslaskuri.toString();
    }

    public String getLoytamattomienKorttiparienMaaraTekstina() {
        return "Pareja jäljellä: " + getLoytymattomienParienLukumaara();
    }

    public String getParasTulosTekstina() {
        return this.parasTulos.toString();
    }

    public int getYritysmaaraLukuna() {
        return this.yrityslaskuri.getYritysmaara();
    }

    public int getValinnanIndeksi(int jarjestysnro) {
        return this.valitutPaikat.getValitutIndeksit().get(jarjestysnro);
    }

}
