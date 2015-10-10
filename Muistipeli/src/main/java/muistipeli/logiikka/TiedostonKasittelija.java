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
    private String tiedostopolku;

    public TiedostonKasittelija() {

        AccessController.doPrivileged(new PrivilegedAction() {

            @Override
            public Object run() {
                tiedostopolku = System.getProperty("user.home")
                        + File.separator
                        + ".";

                return null;
            }

        });

    }

    public void luoTiedosto(String tiedostonimi) {

        try {
            FileWriter kirjoittaja = new FileWriter(tiedostopolku + tiedostonimi);
            kirjoittaja.write("" + 9999);
            kirjoittaja.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kirjoitaTiedostoon(String tiedostonimi, String teksti) {

        try {
            FileWriter kirjoittaja = new FileWriter(tiedostopolku + tiedostonimi);
            kirjoittaja.write(teksti);
            kirjoittaja.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lueTiedostosta(String tiedostonimi) {

        Scanner lukija;
        String luettu = "";
        File tiedosto = new File(tiedostopolku+tiedostonimi);

        if (!tiedosto.isFile()) {
            luoTiedosto(tiedostopolku+tiedostonimi);
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
