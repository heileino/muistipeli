/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import muistipeli.logiikka.*;

/**
 * Luokka toteuttaa käyttäjän klikkaamien painikkeiden toiminnot.
 *
 * @author Heikki Leinonen
 */
public class KlikkaustenKuuntelija implements ActionListener {

    private Pelipoyta pelipoyta;
    private JButton[] peliruudukko;

    public KlikkaustenKuuntelija(Pelipoyta pelipoyta, JButton[] peliruudukko) {
        this.pelipoyta = pelipoyta;
        this.peliruudukko = peliruudukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        for (int i = 0; i < peliruudukko.length; i++) {
            if (ae.getSource() == peliruudukko[i]) {

                Kortti valittuKortti = pelipoyta.getTaulukko()[i];

                if (pelipoyta.onkoJoLoydetty(valittuKortti)) {
                    return;
                }

                pelipoyta.lisaaKorttiValittuihin(i);

                peliruudukko[i].setText(pelipoyta.getTaulukko()[i].toString());

                if (pelipoyta.getKorttejaValittu().size() == 2) {
                    int korttiIndeksi1 = pelipoyta.getKorttejaValittu().get(0);
                    int korttiIndeksi2 = pelipoyta.getKorttejaValittu().get(1);

                    if (pelipoyta.onkoSama(korttiIndeksi1 + 1, korttiIndeksi2 + 1)) {
                        pelipoyta.lisaaKorttiLoytyneisiin(pelipoyta.getTaulukko()[korttiIndeksi1]);
                        pelipoyta.lisaaKorttiLoytyneisiin(pelipoyta.getTaulukko()[korttiIndeksi2]);

                        pelipoyta.vahennaKorttejaJaljella();

                    }

                    peliruudukko[korttiIndeksi1].setText("" + (korttiIndeksi1 + 1));
                    peliruudukko[korttiIndeksi2].setText("" + (korttiIndeksi2 + 1));
                }

                pelipoyta.tyhjaaValitutKortit();

                return;
            }
        }
    }
}
