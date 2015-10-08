package muistipeli.logiikka;

/**
 * Luokan ilmentymät esittävät Muistipelin pelaajaa ja ylläpitävät tilastoa
 * arvausyritysten määrästä.
 *
 * @author Heikki Leinonen
 */
public class Pisteet implements Comparable<Pisteet> {

    private int yritykset;

    /**
     * Konstruktori asettaa pelaajan yritysmäärään arvoon 0.
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

    @Override
    public int compareTo(Pisteet t) {
        return ((Integer) (getYritykset())).compareTo(t.getYritykset());
    }
}
