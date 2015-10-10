package muistipeli.logiikka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Scanner;

/**
 * Luokka hoitaa tiedoston kirjoittamiseen liittyvät toiminnot
 *
 * @author Heikki Leinonen
 */
public class TiedostonKasittelija {

    //private String tiedostopolku;
    private File tiedosto;

    public TiedostonKasittelija(final String tiedostonimi) {

        AccessController.doPrivileged(new PrivilegedAction() {

            @Override
            public Object run() {
                String tiedostopolku = System.getProperty("user.home")
                        + File.separator
                        + "." + tiedostonimi;
                tiedosto = new File(tiedostopolku);
                return null;
            }

        });

    }

    public void luoTiedosto() {

        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("" + 0);
            kirjoittaja.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kirjoitaTiedostoon(String teksti) {

        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write(teksti);
            kirjoittaja.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }

        return luettu;
    }

}
