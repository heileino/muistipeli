package muistipeli.logiikka;

/**
 * Luokka toimii linkkinä käyttöliittymäluokkien ja muiden logiikka-luokkien
 * välillä. Luokka tarjoaa metodeita, joilla peli etenee alusta loppuun.
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private final int korttienMaara;
    private String parasTulosTiedostonNimi;
    private Pelipoyta pelipoyta;
    private LoytyneetKortit loytyneetKortit;
    private ParhaanTuloksenTilasto parasTilasto;
    private ValittujenIndeksienSailio valittujenIndeksienSailio;
    private Yritysmaaralaskuri yrityslaskuri;

    /**
     * Konstruktori luo tarvittavat tiedot ja apuvälineet pelin pelaamiseen.
     */
    public Pelimoottori() {
        korttienMaara = 16;
        parasTulosTiedostonNimi = "parastulos.txt";
        pelipoyta = new Pelipoyta(korttienMaara);
        loytyneetKortit = new LoytyneetKortit();
        parasTilasto = new ParhaanTuloksenTilasto(parasTulosTiedostonNimi);
        valittujenIndeksienSailio = new ValittujenIndeksienSailio();
        yrityslaskuri = new Yritysmaaralaskuri();
    }

    /**
     * Metodi lisää taulukon indeksiä vastaavan kortin valittujen joukkoon ja
     * kääntää sen asennoksi kuvapuolen, mikäli kortti on valintakelpoinen.
     * Kortti on valintakelpoinen, mikäli sen asento on selkäpuoli.
     *
     * @param i korttitaulukon indeksi
     *
     * @return tosi jos valinta onnistuu
     */
    public boolean valitaanKorttiJosMahdollista(int i) {
        if (pelipoyta.nakyykoKortinKuvapuoli(i)) {
            return false;
        }
        valittujenIndeksienSailio.lisaaValittuihin(i);
        pelipoyta.paljastaKortinKuva(i);
        return true;
    }

    /**
     * Metodi tarkistaa pelitilanteen yrityskerran sisällä. Vaihtoehtoina ovat
     * toisen kortin valinta tai jatkaminen korttien vertailuun.
     *
     * @return tosi, jos toisen kortin valinnalle on tarvetta
     */
    public boolean valitaankoToinenKortti() {
        if (valittujenIndeksienSailio.montakoValittu() > 1) {
            yrityslaskuri.lisaaValintayritys();
            return false;
        }
        return true;
    }

    /**
     * Metodi välittää kutsujalle taulukon indeksiä vastaavan kortin tunnisteen.
     *
     * @param i taulukon indeksi
     *
     * @return kortin tunniste
     */
    public String getKortinTunniste(int i) {
        return pelipoyta.getKorttiTaulukosta(i).toString();
    }

    /**
     * Metodi lisää valittujen korttien listalla olevat kortit löydettyjen
     * korttien listalle, mikäli ne ovat pari tai kääntää korttien selkäpuolet
     * esiin, mikäli valitut kortit eivät ole pareja.
     *
     * @return tosi, jos pari löytyy
     */
    public boolean ovatkoValinnatPareja() {
        Kortti kortti1 = getKorttiValittujenKorttienJoukosta(0);
        Kortti kortti2 = getKorttiValittujenKorttienJoukosta(1);
        if (pelipoyta.onkoKorteillaSamaTunnus(kortti1, kortti2)) {
            loytyneetKortit.lisaaKortitLoytyneeksi(kortti1, kortti2);
            return true;
        }
        pelipoyta.piilotaKortinKuva(pelipoyta.getKortinIndeksi(kortti1));
        pelipoyta.piilotaKortinKuva(pelipoyta.getKortinIndeksi(kortti2));
        return false;
    }

    /**
     * Metodi päättää yhden yrityskerran nollaamalla valitut taulukkoindeksit ja
     * selvittää, jatketaanko peliä vielä päättyneen yrityksen jälkeen. Peli
     * jatkuu, mikäli korttipareja on vielä löytymättä.
     *
     * @return tosi, jos peli jatkuu
     */
    public boolean jatkuukoPeli() {
        valittujenIndeksienSailio = new ValittujenIndeksienSailio();
        return getLoytymattomienParienLukumaara() > 0;
    }

    /**
     * Metodi täyttää pelipöydän korteilla, sekoittaa ne satunnaiseen
     * järjestykseen ja tähän asti saadun parhaan tuloksen lataaminen ohjelmaan.
     */
    public void pelaaPeli() {
        pelipoyta.asetaKortitTaulukkoon();
        pelipoyta.sekoitaTaulukonKortit();
        parasTilasto.lataaParasTulos();
    }

    /**
     * Metodi tarkastaa onko pelattu tulos paras. Jos tulos on parempi kuin
     * voimassa oleva paras tulos, metodi korvaa vanhan tuloksen uudella.
     *
     * @return tosi, jos pelin tulos on parempi kuin voimassa oleva paras tulos
     */
    public boolean onUusiParasTulos() {
        if (yrityslaskuri.getYritysmaara() < parasTilasto.getParasTulos() || parasTilasto.getParasTulos() == 0) {
            parasTilasto.setParasTulos(yrityslaskuri.getYritysmaara());
            parasTilasto.lataaParasTulos();
            return true;
        }
        return false;
    }

    /**
     * Metodi joko käynnistää tai lopettaa pelin.
     *
     * @param jatkohalu käyttäjän toivettava vastaava numero
     *
     * @return tosi, jos parametri on 0
     */
    public boolean aloitetaankoUusiPeli(int jatkohalu) {
        return jatkohalu == 0;
    }

    /**
     * Metodi laskee ja palauttaa löytämättä olevien korttien lukumäärän
     *
     * @return löytämättömien korttien lukumäärä
     */
    public int getLoytymattomienParienLukumaara() {
        return (korttienMaara - getLoytyneet().getLoydetytKortit().size()) / 2;
    }

    public Kortti getKorttiValittujenKorttienJoukosta(int valinnanIndeksi) {
        return this.pelipoyta.getKorttiTaulukosta(this.valittujenIndeksienSailio.getValitutIndeksit().get(valinnanIndeksi));
    }

    public String getLoytamattomienKorttiparienMaaraTekstina() {
        return "Pareja jäljellä: " + getLoytymattomienParienLukumaara();
    }

    public String getParasTulosTekstina() {
        return this.parasTilasto.toString();
    }

    public String getTiedostonimi() {
        return this.parasTulosTiedostonNimi;
    }

    public String getYritystenMaaraTekstina() {
        return this.yrityslaskuri.toString();
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

    public ValittujenIndeksienSailio getValittujenPaikkaindeksienSailio() {
        return this.valittujenIndeksienSailio;
    }

    public Yritysmaaralaskuri getYritysmaaraLaskuri() {
        return this.yrityslaskuri;
    }
}
