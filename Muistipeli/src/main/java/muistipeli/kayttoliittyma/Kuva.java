package muistipeli.kayttoliittyma;

import javax.swing.ImageIcon;

/**
 * Luokka tarjoaa graafiselle käyttöliittymälle kuvia kortteihin
 *
 * @author Heikki Leinonen
 */
public class Kuva {

    private ImageIcon kuva0;
    private ImageIcon kuva1;
    private ImageIcon kuva2;
    private ImageIcon kuva3;
    private ImageIcon kuva4;
    private ImageIcon kuva5;
    private ImageIcon kuva6;
    private ImageIcon kuva7;
    private ImageIcon selkakuva;

    /**
     * Konstruktori luo muistipelikorttien kuvista ImageIcon-olioita
     */
    public Kuva() {

        kuva0 = luoImageIcon("/images/hai.jpg");
        kuva1 = luoImageIcon("/images/hevonen.jpg");
        kuva2 = luoImageIcon("/images/janis.jpg");
        kuva3 = luoImageIcon("/images/kaarme.jpg");
        kuva4 = luoImageIcon("/images/kettu.jpg");
        kuva5 = luoImageIcon("/images/kimalainen.jpg");
        kuva6 = luoImageIcon("/images/lammas.jpg");
        kuva7 = luoImageIcon("/images/siili.jpg");
        selkakuva = luoImageIcon("/images/kortin_selka.jpg");

    }

    protected ImageIcon getKuva(String korttitunnus) {

        switch (korttitunnus) {

            case "kortti_1":
                return kuva0;
            case "kortti_2":
                return kuva1;
            case "kortti_3":
                return kuva2;
            case "kortti_4":
                return kuva3;
            case "kortti_5":
                return kuva4;
            case "kortti_6":
                return kuva5;
            case "kortti_7":
                return kuva6;
            case "kortti_8":
                return kuva7;
            case "selkakuva":
                return selkakuva;
            default:
                return selkakuva;
        }

    }

    protected static ImageIcon luoImageIcon(String polku) {
        ImageIcon kuva = new ImageIcon(Kuva.class.getResource(polku));
        return kuva;
    }

}
