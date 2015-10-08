package muistipeli.logiikka;

import java.io.File;
import java.io.FileWriter;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Scanner;

/**
 * Luokka pitää kirjaa parhaista pelisuorituksista, eli pienimmistä
 * yritysmääristä, joilla peli on pelattu läpi.
 *
 * @author Heikki Leinonen
 */
public class ParasTulos {

    private int parasTulos;
    private File tiedosto;
    private String tiedostonimi;

    public ParasTulos() {

        this.parasTulos = 0;
        tiedostonimi = "parasTulos.txt";

        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                String polku
                        = System.getProperty("user.home")
                        + File.separator
                        + "." + tiedostonimi;

                tiedosto = new File(polku);
                return null;
            }
        });
    }

    public void luoTiedosto() {
        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("" + 0);
            kirjoittaja.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setParasTulos(int tulos) {
        if (this.parasTulos <= tulos) {
            return;
        }
        this.parasTulos = tulos;

        try {
            FileWriter kirjoittaja = new FileWriter(tiedostonimi);
            kirjoittaja.write("" + this.parasTulos);
            kirjoittaja.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lataaParasTulosMuuttujaan() {

        try {
            File tiedosto = new File(tiedostonimi);
            Scanner lukija = new Scanner(tiedosto);
            if (!tiedosto.isFile()) {
                luoTiedosto();
            }
            if (lukija.hasNext()) {
                this.parasTulos = Integer.parseInt(lukija.next());
            }

            lukija.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getParasTulos() {
        return this.parasTulos;
    }

}
