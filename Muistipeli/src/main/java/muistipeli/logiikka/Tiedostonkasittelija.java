package muistipeli.logiikka;

import java.io.File;
import java.io.FileWriter;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luokka toteuttaa tiedoston luomiseen, kirjoittamiseen ja lukemiseen liittyvät
 * toiminnot
 *
 * @author Heikki Leinonen
 */
public class Tiedostonkasittelija {

    private File tiedosto;
    private static Logger loki = Logger.getLogger("muistipeli.logiikka.Tiedostonkasittelija");

    /**
     * Konstruktori luo parametrina annetusta tiedostonimestä tiedostopolun ja
     * luo näistä File-luokan ilmentymän.
     *
     * @param tiedostonimi haluttu tiedostonimi
     */
    public Tiedostonkasittelija(final String tiedostonimi) {
        AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                String tiedostopolku = System.getProperty("user.home")
                        + File.separator
                        + "."
                        + tiedostonimi;
                tiedosto = new File(tiedostopolku);
                return null;
            }
        });
    }

    /**
     * Metodi luo tiedoston. Metodia tarvitaan varmistamaan, että
     * lueTiedostosta-metodilla on aina jotakin luettavaa.
     */
    public void luoTiedosto() {

        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("" + 9999);
            kirjoittaja.close();
        } catch (Exception e) {
            loki.log(Level.OFF, "epaonnistunut tiedostonkäsittely", e);
        }
    }

    /**
     * Metodi kirjoittaa parametrina saadun merkkijonon tiedostoon
     *
     * @param teksti tiedostoon talletettavaksi haluttu teksti
     */
    public void kirjoitaTiedostoon(String teksti) {

        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write(teksti);
            kirjoittaja.close();
        } catch (Exception e) {
            loki.log(Level.OFF, "epaonnistunut tiedostoonkirjoitus", e);
        }
    }

    /**
     * Metodi lukee ilmentymämuuttujana olevan tiedoston sisällön ja palauttaa
     * sen merkkijonona.
     *
     * @return tiedostosta luettu sisältö
     */
    public String lueTiedostosta() {

        Scanner lukija;
        String luettu = "";

        if (!tiedosto.isFile()) {
            luoTiedosto();
        }

        try {
            lukija = new Scanner(tiedosto);
            if (lukija.hasNext()) {
                luettu = lukija.next();
            }
            lukija.close();
        } catch (Exception e) {
            loki.log(Level.OFF, "epaonnistunut tiedostonluku", e);
        }
        return luettu;
    }
}
