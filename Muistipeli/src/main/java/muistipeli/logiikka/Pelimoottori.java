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
     * Konstruktori luo apuvälineet muistipelin pelaamiseen. Näitä ovat uusi
     * pelipöytä sekä löytyneiden korttien, parhaan tuloksen, valittujen
     * paikkojen ja yritysten laskemiseen käytetyt tilastointioliot.
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
     * Metodi valitsee taulukon indeksiä vastaavan kortin valituksi, mikäli se
     * on valintakelpoinen.
     *
     * @param i korttitaulukon indeksi
     *
     * @see muistipeli.logiikka.Pelimoottori#onkoKorttiValittavissa(int)
     * @see muistipeli.logiikka.Pelimoottori#valitseKortti(int)
     *
     * @return tosi jos valinta onnistuu
     */
    public boolean valitaanKorttiJosMahdollista(int i) {
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
     * @param i korttitaulukon indeksi
     *
     * @see
     * muistipeli.logiikka.ValittujenPaikkaindeksienSailio#lisaaValittuihin(int)
     * @see muistipeli.logiikka.Pelipoyta#paljastaKortinKuva(int)
     *
     */
    public void valitseKortti(int i) {
        valittujenIndeksienSailio.lisaaValittuihin(i);
        pelipoyta.paljastaKortinKuva(i);
    }

    /**
     * Kertoo taulukon tietyssä paikassa oleva kortti valittavissa. Kortin on
     * valittavissa, mikäli sen asento on selkäpuoli ylöspäin.
     *
     * @param i korttitaulukon indeksi
     *
     * @see muistipeli.logiikka.Pelipoyta#nakyykoKortinKuvapuoli(int)
     *
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
     * @see muistipeli.logiikka.ValittujenPaikkaindeksienSailio#montakoValittu()
     * @see muistipeli.logiikka.Yritysmaaralaskuri#lisaaValintayritys()
     *
     * @return tosi, jos toisen kortin valinnalle on tarvetta
     */
    public boolean valitaankoToinenKortti() {

        if (this.valittujenIndeksienSailio.montakoValittu() > 1) {
            this.yrityslaskuri.lisaaValintayritys();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Metodi välittää kutsujalle parametrina annettua taulukon indeksiä
     * vastaavan kortin tunnisteen.
     *
     * @param i taulukon indeksi
     *
     * @see muistipeli.logiikka.Pelipoyta#getKorttiTaulukosta(int)
     *
     * @return kortin tunniste
     */
    public String getKortinTunniste(int i) {
        return this.pelipoyta.getKorttiTaulukosta(i).toString();
    }

    /**
     * Metodi lisaa yhdellä yrityskerralla valitut kortit löydettyjen korttien
     * listalle, mikäli ne ovat pari tai kääntää korttien selkäpuolet esiin,
     * mikäli valitut kortit eivät ole pareja.
     *
     * @see
     * muistipeli.logiikka.Pelipoyta#onkoKorteillaSamaTunnus(muistipeli.logiikka.Kortti,
     * muistipeli.logiikka.Kortti)
     * @see
     * muistipeli.logiikka.Pelimoottori#pariLoytynyt(muistipeli.logiikka.Kortti,
     * muistipeli.logiikka.Kortti)
     * @see
     * muistipeli.logiikka.Pelimoottori#kaannaKortitNurin(muistipeli.logiikka.Kortti,
     * muistipeli.logiikka.Kortti)
     *
     * @return tosi, jos pari löytyy
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

    /**
     * Metodi palauttaa kortin yhdellä yrityskerralla valittujen kahden kortin
     * joukosta.
     *
     * @param valinnanIndeksi
     * @return valintaindeksiä vastaava kortti
     */
    public Kortti getKorttiValittujenKorttienJoukosta(int valinnanIndeksi) {
        return this.pelipoyta.getKorttiTaulukosta(this.valittujenIndeksienSailio.getValitutIndeksit().get(valinnanIndeksi));
    }

    /**
     * Metodi lisaa löytyneet parit löytyneitä pareja ylläpitävään listaan
     *
     * @see
     * muistipeli.logiikka.LoytyneetKortit#lisaaKortitLoytyneeksi(muistipeli.logiikka.Kortti,
     * muistipeli.logiikka.Kortti)
     *
     * @param kortti1 ensimmäinen löydetty muistikortti
     * @param kortti2 toinen löydetty muistikortti
     */
    public void pariLoytynyt(Kortti kortti1, Kortti kortti2) {
        this.loytyneetKortit.lisaaKortitLoytyneeksi(kortti1, kortti2);
    }

    /**
     * Metodi kaantaa parametrina saamansa kortit selkäpuolelle.
     *
     * @see muistipeli.logiikka.Pelipoyta#piilotaKortinKuva(int)
     *
     * @param kortti1 muistikortti
     * @param kortti2 muistikortti
     */
    public void kaannaKortitNurin(Kortti kortti1, Kortti kortti2) {
        this.pelipoyta.piilotaKortinKuva(this.pelipoyta.getKortinIndeksi(kortti1));
        this.pelipoyta.piilotaKortinKuva(this.pelipoyta.getKortinIndeksi(kortti2));
    }

    /**
     * Metodi palauttaa kyseisellä pelin hetkellä löytämättä olevien parien
     * lukumäärän.
     *
     * @return parien määrä.
     */
    public int getLoytymattomienParienLukumaara() {
        return (korttienMaara - getLoytyneet().getLoydetytKortit().size()) / 2;
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
     *
     * @see muistipeli.logiikka.Pelipoyta#asetaKortitTaulukkoon()
     * @see muistipeli.logiikka.Pelipoyta#sekoitaTaulukonKortit()
     * @see muistipeli.logiikka.ParhaanTuloksenTilasto#lataaParasTulos()
     */
    public void pelaaPeli() {
        this.pelipoyta.asetaKortitTaulukkoon();
        this.pelipoyta.sekoitaTaulukonKortit();
        this.parasTilasto.lataaParasTulos();
    }

    /**
     * Metodi tarkastaa onko pelattu tulos paras. Jos tulos on parempi kuin
     * voimassa oleva paras tulos, metodi korvaa vanhan tuloksen uudella.
     *
     * @return tosi, jos pelin tulos on parempi kuin voimassa oleva paras tulos
     */
    public boolean onUusiParasTulos() {
        if (this.yrityslaskuri.getYritysmaara() < this.parasTilasto.getParasTulos() || this.parasTilasto.getParasTulos() == 0) {
            this.parasTilasto.setParasTulos(this.yrityslaskuri.getYritysmaara());
            this.parasTilasto.lataaParasTulos();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodi joko käynnistää tai lopettaa pelin. Parametrilla 0 käynnistetään uusi peli. Muilla numeroilla palautetaan epätosi.
     * @param jatkohalu käyttäjän toivettava vastaava numero
     * @return tosi, jos parametri on 0
     */
    public boolean aloitetaankoUusiPeli(int jatkohalu) {
        if (jatkohalu == 0) {
            return true;
        }
        return false;
    }
   
    /**
     * Metodi tyhjentää valittuja kortteja säilövän listan uutta yrityskierrosta varten.
     */
    public void nollaaValitutKortit() {
        this.valittujenIndeksienSailio = new ValittujenIndeksienSailio();
    }

    /**
     * Metodi palauttaa vielä löytämättä olevien korttien määrän
     * käyttöliittymien käytettäväksi sanallisessa muodossa.
     *
     * @return sanallinen ilmoitus löytämättä olevien korttien määrästä
     */
    public String getLoytamattomienKorttiparienMaaraTekstina() {
        return "Pareja jäljellä: " + getLoytymattomienParienLukumaara();
    }

    /**
     * Metodi palauttaa parhaan tuloksen käyttöliittymien käytettäväksi
     * sanallisessa muodossa.
     *
     * @return sanallinen ilmoitus parhaasta tuloksesta
     */
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
