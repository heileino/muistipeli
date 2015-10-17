package muistipeli.main;

import javax.swing.SwingUtilities;
import muistipeli.kayttoliittyma.GraafinenKayttoliittyma;

public class Main {

    public static void main(String[] args) {
        GraafinenKayttoliittyma gui = new GraafinenKayttoliittyma();
        SwingUtilities.invokeLater(gui);
    }
}
