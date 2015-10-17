package muistipeli.logiikka;

/**
 * Luokka pitää kirjaa parhaasta pelisuorituksesta, eli pienimmistä
 * yritysmääristä, jolla peli on pelattu onnistuneesti läpi.
 *
 * @author Heikki Leinonen
 */
public class ParhaanTuloksenTilasto {

    private int parasTulos;

    private Tiedostonkasittelija tiedostonkasittelija;
    private String tiedostonimi;

    /**
     * Konstruktori alustaa parhaan tuloksen ja asettaa paremetrina saadun nimen
     * parasta tulosta kirjaavan tiedoston nimeksi.
     *
     * @param tiedostonimi parhaan tuloksen kirjaavan tiedoston nimi
     */
    public ParhaanTuloksenTilasto(String tiedostonimi) {

        this.tiedostonimi = tiedostonimi;
        this.parasTulos = 9999;
        tiedostonkasittelija = new Tiedostonkasittelija(tiedostonimi);

    }

    /**
     * Metodi asettaa parametrina saadun tuloksen parhaimmaksi tulokseksi,
     * mikäli se on parempi kuin olemassa oleva paras tulos.
     *
     * @param tulos kuinka monella yrityksellä peli on pelattu läpi.
     */
    public void setParasTulos(int tulos) {

        if (tulos < this.parasTulos) {
            this.parasTulos = tulos;
        }

        tiedostonkasittelija.kirjoitaTiedostoon("" + this.parasTulos);
    }

    /**
     * Metodin lataa tiedostosta siellä olevan tuloksen luokan ilmentymän käyttöön.
     *
     */
    public void lataaParasTulos() {
        this.parasTulos = Integer.parseInt(tiedostonkasittelija.lueTiedostosta());
    }

    public int getParasTulos() {
        return this.parasTulos;
    }

    public String getTiedostonimi() {
        return this.tiedostonimi;
    }

    public void setTiedostonimi(String nimi) {
        this.tiedostonimi = nimi;
    }

    @Override
    public String toString() {
        if (getParasTulos() == 9999) {
            return "Paras tulos: -";
        }
        return "Paras tulos: " + getParasTulos();
    }
}
