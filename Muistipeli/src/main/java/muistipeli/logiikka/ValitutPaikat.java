package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka pitää kirjaa yhden yrityskerran aikana valituista
 * korttitaulukkopaikoista.
 *
 * @author Heikki Leinonen
 */
public class ValitutPaikat {

    private List<Integer> valitutIndeksit;

    public ValitutPaikat() {

        this.valitutIndeksit = new ArrayList<>();
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
    
    /**
     * Metodi kertoo, kuinka monta korttia on valittu kyseisellä yrityskerralla.
     *
     * @return int-tyypin luku, joka kertoo valittujen indeksien määrän.
     */
    public int montakoValittu() {
        return getValitutIndeksit().size();
    }

    public List<Integer> getValitutIndeksit() {
        return this.valitutIndeksit;
    }
}
