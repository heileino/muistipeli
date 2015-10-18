package muistipeli.logiikka;

/**
 * Luokka kuvaa yksittäistä muistikorttia. Luokan kaksi keskeistä ominaisuutta
 * ovat kortin tunnus ja asento. Asennolla tarkoitetaan sitä, onko kortin kuva-
 * vai selkäpuoli esillä
 *
 * @author Heikki Leinonen
 */
public class Kortti {

    private final String tunnus;
    private boolean kuvapuoliNakyy;

    /**
     * Konstruktori asettaa kortin näkyvyydeksi epätoden ja asettaa kortin
     * tunnukseksi parametrina saadun arvon.
     *
     * @param tunnus korttiparin yksilöivä merkkijono
     */
    public Kortti(String tunnus) {
        kuvapuoliNakyy = false;
        this.tunnus = tunnus;
    }

    /**
     * Metodi asettaa kortin kuvapuolen näkyvyyden todeksi.
     */
    public void naytaKuvapuoli() {
        if (!kuvapuoliNakyy) {
            kuvapuoliNakyy = true;
        }
    }

    /**
     * Metodi asettaa kyseisen kortin selkäpuolen näkyvyyden epätodeksi;
     */
    public void naytaSelkapuoli() {
        if (kuvapuoliNakyy) {
            kuvapuoliNakyy = false;
        }
    }

    /**
     * Metodi kertoo kortin tämän hetkisen asennon
     *
     * @return boolean-totuusarvo kuvapuolen näkyvyydestä
     */
    public boolean nakyykoKuvapuoli() {
        return this.kuvapuoliNakyy;
    }

    public String getTunnus() {

        return tunnus;
    }

    @Override
    public String toString() {
        return "kortti_" + tunnus;
    }
}
