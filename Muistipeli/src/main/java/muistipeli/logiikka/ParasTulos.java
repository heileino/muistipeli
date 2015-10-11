package muistipeli.logiikka;

/**
 * Luokka pitää kirjaa parhaasta pelisuorituksesta, eli pienimmistä
 * yritysmääristä, joilla peli on pelattu onnistuneesti läpi.
 *
 * @author Heikki Leinonen
 */
public class ParasTulos {

    private int parasTulos;

    private TiedostonKasittelija tiedostonkasittelija;
    private String tiedostonimi;

    /**
     * Konstruktori alustaa parhaan tuloksen.
     *
     * @param tiedostonimi
     */
    public ParasTulos(String tiedostonimi) {

        this.tiedostonimi = tiedostonimi;
        this.parasTulos = 9999;
        tiedostonkasittelija = new TiedostonKasittelija(tiedostonimi);

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
     * Metodin lataa tiedostosta parhaan tuloksen luokan ilmentymän käyttöön
     *
     * @param tiedostonimi
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
            return "Paras tulos: - ";
        }
        return "Paras tulos: " + getParasTulos();
    }
}
