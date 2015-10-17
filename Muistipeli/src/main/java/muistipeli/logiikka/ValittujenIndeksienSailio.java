package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka pitää kirjaa yhden yrityskerran aikana valituista
 * korttitaulukkopaikoista.
 *
 * @author Heikki Leinonen
 */
public class ValittujenIndeksienSailio {

    private List<Integer> valitutIndeksit;

    /**
     * Konstruktori luo uuden valittuja indeksejä säilövän luettelon.
     */
    public ValittujenIndeksienSailio() {
        this.valitutIndeksit = new ArrayList<>();
    }

    /**
     * Metodi lisää yhdellä valintakerralla valitun kortin valittujen korttien
     * listalle.
     *
     * @param indeksi valitun kortin sijainti taulukossa
     */
    public void lisaaValittuihin(int indeksi) {
        this.valitutIndeksit.add(indeksi);
    }

    /**
     * Metodi tyhjentää valittujen korttien indeksejä säilövän listan sisällön.
     */
    public void tyhjaaValitutIndeksit() {
        this.valitutIndeksit = new ArrayList<>();
    }

    /**
     * Metodi kertoo, kuinka monta korttia on valittu kyseisellä yrityskerralla.
     *
     * @return valittujen indeksien määrän kertova luku
     */
    public int montakoValittu() {
        return getValitutIndeksit().size();
    }

    public List<Integer> getValitutIndeksit() {
        return this.valitutIndeksit;
    }
}
