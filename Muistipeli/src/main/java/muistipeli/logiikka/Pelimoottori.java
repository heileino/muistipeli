package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka toimii linkkinä käyttöliittymäluokkien ja muiden logiikka-luokkien
 * välillä tarjoamalla eri luokkien ilmentymiä palauttavia get-metodeita.
 *
 * @author Heikki Leinonen
 */
public class Pelimoottori {

    private Pelipoyta pelipoyta;
    private Yritysmaaralaskuri yrityslaskuri;
    private LoytyneetKortit loytyneetKortit;
    private ValitutPaikat valitutPaikat;
    private LoytamattomatKorttiparit parejaLoytamatta;
    private ParasTulos parasTulos;
    private final int PARIENMAARA = 8;
    private String tiedostonimi = "parastulos.txt";

    /**
     * Konstruktori
     */
    public Pelimoottori() {

        pelipoyta = new Pelipoyta();
        loytyneetKortit = new LoytyneetKortit();
        valitutPaikat = new ValitutPaikat();
        parejaLoytamatta = new LoytamattomatKorttiparit(PARIENMAARA);
        parasTulos = new ParasTulos(tiedostonimi);
        yrityslaskuri = new Yritysmaaralaskuri();

    }

 
    public LoytamattomatKorttiparit getLoytamattomatKorttiparit() {
        return this.parejaLoytamatta;
    }

    public LoytyneetKortit getLoytyneetKortit() {
        return this.loytyneetKortit;
    }

    public ParasTulos getParasTulos() {
        return this.parasTulos;
    }

    public Pelipoyta getPelipoyta() {
        return this.pelipoyta;
    }

    public ValitutPaikat getValitutPaikat() {
        return this.valitutPaikat;
    }

    public Yritysmaaralaskuri getYritysmaaraLaskuri() {
        return this.yrityslaskuri;
    }
    
    public String getTiedostonimi() {
        return this.tiedostonimi;
    }
}
