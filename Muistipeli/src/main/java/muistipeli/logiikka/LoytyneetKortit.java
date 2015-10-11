
package muistipeli.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka huolehtii loytyneiden korttiparien kirjauksesta
 * @author Heikki Leinonen
 */
public class LoytyneetKortit {
    
    private List<Kortti> loydetytKortit;
    
    public LoytyneetKortit(){
        this.loydetytKortit=new ArrayList<>();
    }
    
    /**
     * Metodi testaa, onko parametrina annettu kortti löydettyjen korttien
     * listassa.
     *
     * @param kortti Pelitaulukossa oleva Kortti-luokan ilmentymä.
     *
     * @return onko parametrina annettu kortti jo
     * löydettyjen korttien listassa.
     */
    public boolean onkoKorttiJoLoytyneissa(Kortti kortti) {
        return (this.loydetytKortit.contains(kortti));
    }
    
    /**
     * Metodi lisää valitut kortit löytyneiden korttien listalle.
     *
     * @param kortti1 ensimmäisenä valittu kortti
     * @param kortti2 toisena valittu kortti
     */
    public void lisaaKortitLoytyneeksi(Kortti kortti1, Kortti kortti2) {
        this.loydetytKortit.add(kortti1);
        this.loydetytKortit.add(kortti2);
    }
    
    public List<Kortti> getLoydetytKortit() {
        return this.loydetytKortit;
    }
}
