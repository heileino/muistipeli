package muistipeli.logiikka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Scanner;

/**
 * Luokka pitää kirjaa parhaista pelisuorituksista, eli pienimmistä
 * yritysmääristä, joilla peli on pelattu onnistuneesti läpi.
 *
 * @author Heikki Leinonen
 */
public class ParasTulosKirjanpitaja {

    private int parasTulos;
    private String tiedostonimi;
    private File tiedosto;

    /**
     * Konstruktori valmistelee parhaan tuloksen käsittelyä tiedostosta.
     */
    public ParasTulosKirjanpitaja() {

        this.parasTulos = 9999;
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

    /**
     * Metodi luo uuden parasta tulosta ylläpitävän tiedoston.
     */
    public void luoTiedosto() {

        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("" + this.parasTulos);
            kirjoittaja.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi asettaa parametrina saadun tuloksen parhaimmaksi tulokseksi,
     * mikäli se on parempi kuin olemassa oleva paras tulos.
     *
     * @param tulos kuinka monella yrityksellä peli on pelattu läpi.
     */
    public void setParasTulos(int tulos) {

        if (this.parasTulos <= tulos) {
            return;
        }

        this.parasTulos = tulos;

        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            kirjoittaja.write("" + this.parasTulos);
            kirjoittaja.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodin tarkoituksena on ladata paras tulos sitä tallentavasta
     * tiedostosta ohjelman käyttöön.
     */
    public void lataaParasTulosMuuttujaan() {

        try {

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

    @Override
    public String toString() {
        if(getParasTulos()==9999){
            return "Paras tulos: - ";
        }
        return "Paras tulos: " + getParasTulos();
    }
}
