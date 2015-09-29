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
        int korttejaValittu=1;
        int korttipaikka1 = -1;
        int korttipaikka2 =-2;
        
        for (int i = 0; i < peliruudukko.length; i++) {
            if (peliruudukko[i] == ae.getSource()) {
                pelipoyta.paljastaKortti(i+1);
                korttejaValittu++;
                if(korttejaValittu==1){
                    korttipaikka1=i;
                } else{
                    korttipaikka2=i;
                }
                peliruudukko[i].setText(pelipoyta.getTaulukko()[i].toString());
                if(korttejaValittu>1){
                    if(pelipoyta.onkoSama(korttipaikka1, korttipaikka2)){
                        pelipoyta.lisaaKorttiLoytyneisiin(pelipoyta.getTaulukko(korttipaikka1));
                        pelipoyta.lisaaKorttiLoytyneisiin(korttipaikka2);
                    }
                }
            }
        }
    }
}
