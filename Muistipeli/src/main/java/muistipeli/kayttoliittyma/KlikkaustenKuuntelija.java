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
 *
 * @author Heikki
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
            if (peliruudukko[i] == ae.getSource()) {
                peliruudukko[i].setText(pelipoyta.getTaulukko()[i].toString());
            }
        }
    }

}
