package muistipeli.logiikka;

/**
 * Luokka ylläpitää tilastoa arvausyritysten määrästä pelin aikana.
 *
 * @author Heikki Leinonen
 */
public class YritysmaaraLaskuri {

    private int yrityksia;

    /**
     * Konstruktori nollaa yritysmäärälaskurin.
     */
    public YritysmaaraLaskuri() {
        this.yrityksia = 0;
    }

    /**
     * Metodi lisää pelaajalle yhden yrityksen lisää.
     */
    public void lisaaValintayritys() {

        this.yrityksia++;
    }

    public int getYritysmaara() {
        return this.yrityksia;
    }

    @Override
    public String toString() {
        return "Yrityksiä: " + this.getYritysmaara();
    }
}
