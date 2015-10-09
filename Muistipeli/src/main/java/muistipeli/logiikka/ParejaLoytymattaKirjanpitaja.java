package muistipeli.logiikka;

/**
 * Luokka vastaa jäljellä olevien korttien laskemisesta pelissä.
 *
 * @author Heikki Leinonen
 */
public class ParejaLoytymattaKirjanpitaja {

    private int parejaJaljella;

    /**
     * Konstruktori asettaa jäljellä olevien parien määräksi kaikkien pelissä
     * olevien korttiparien lukumäärän
     *
     * @param maksimi kaikkien pelissä olevien korttiparien lukumäärä.
     */
    public ParejaLoytymattaKirjanpitaja(int maksimi) {
        if (maksimi > 0) {
            parejaJaljella = maksimi;
        } else {
            parejaJaljella = 0;
        }

    }

    /**
     * Metodi vähentää pelissä löydetyn parin verran jäljellä olevien korttien
     * määrää.
     */
    public void vahennaParejaLoytymatta() {

        parejaJaljella--;

    }

    public void setParejaLoytymatta(int arvo) {
        if (arvo >= 0) {
            this.parejaJaljella = arvo;
        }

    }

    public int getParejaLoytymatta() {

        return parejaJaljella;
    }

    @Override
    public String toString() {

        return "Pareja jäljellä: " + getParejaLoytymatta();
    }
}
