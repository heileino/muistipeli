package muistipeli.logiikka;

/**
 * Luokka pitää kirjaa parhaasta pelisuorituksesta, eli pienimmistä
 * yritysmääristä, joilla peli on pelattu onnistuneesti läpi.
 *
 * @author Heikki Leinonen
 */
public class ParasTulosKirjanpito {

    private int parasTulos;

    private TiedostonKasittelija tiedostonkasittelija;

    /**
     * Konstruktori alustaa parhaan tuloksen.
     *
     * @param tiedostonimi
     */
    public ParasTulosKirjanpito(String tiedostonimi) {

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

        this.parasTulos = tulos;

        tiedostonkasittelija.kirjoitaTiedostoon("" + this.parasTulos);
    }

    /**
     * Metodin lataa tiedostosta parhaan tuloksen luokan ilmentymän käyttöön
     *
     */
    public void lataaParasTulos() {
        
        this.parasTulos = Integer.parseInt(tiedostonkasittelija.lueTiedostosta());
    }

    public int getParasTulos() {

        return this.parasTulos;
    }

    @Override
    public String toString() {
        if (getParasTulos() == 9999) {
            return "Paras tulos: - ";
        }
        return "Paras tulos: " + getParasTulos();
    }
}