package muistipeli.logiikka;

/**
 * Luokka ylläpitää tilastoa arvausyritysten määrästä pelin aikana.
 *
 * @author Heikki Leinonen
 */
public class Pisteet {

    private int yritykset;

    /**
     * Konstruktori nollaa yritysmäärälaskurin.
     */
    public Pisteet() {

        this.yritykset = 0;
    }

    /**
     * Metodi lisää pelaajalle yhden yrityksen lisää.
     */
    public void lisaaYritys() {

        this.yritykset++;
    }

    public int getYritykset() {

        return this.yritykset;
    }

    public String getYrityksetTekstina() {

        return "Yrityksiä: " + this.getYritykset();
    }
}
