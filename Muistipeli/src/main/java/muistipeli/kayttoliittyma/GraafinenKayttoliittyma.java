/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import muistipeli.logiikka.*;

/**
 * Luokka tarjoaa muistipelille graafisen käyttöliittymän.
 *
 * @author Heikki Leinonen
 */
public class GraafinenKayttoliittyma implements Runnable, ActionListener {

    private JFrame frame;
    private Pelipoyta pelipoyta;
    private Pelaaja pelaaja;
    private JButton[] peliruudukko;
    private JButton aloitaNappi, lopetaNappi;
    private JPanel peliPaneeli, hallintaPaneeli;
    private JLabel yritysLabel, korttejaJaljellaLabel;

    /**
     * Konstruktori luo uuden pelipöydän, pelaajan ja peliruudukon.
     */
    public GraafinenKayttoliittyma() {
        pelipoyta = new Pelipoyta();
        pelaaja = new Pelaaja();
        peliruudukko = new JButton[16];
        lopetaNappi = new JButton();
        aloitaNappi = new JButton();
        peliPaneeli = new JPanel();
        hallintaPaneeli = new JPanel();
        yritysLabel = new JLabel();
        korttejaJaljellaLabel = new JLabel();

    }

    @Override
    public void run() {

        pelipoyta.taytaPoyta();
        pelipoyta.sekoitaKortit();

        frame = new JFrame("Muistipeli");
        frame.setPreferredSize(new Dimension(500, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {

        this.peliPaneeli.setLayout(new GridLayout(4, 4));
        piirraPeliruudut(peliPaneeli);

        hallintaPaneeli.setLayout(new BoxLayout(hallintaPaneeli, BoxLayout.PAGE_AXIS));
        piirraHallintapaneeli(hallintaPaneeli);

        container.add(peliPaneeli);
        container.add(hallintaPaneeli, BorderLayout.EAST);

    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Metodi luo muistikortteja vastaavan ruudukon JButton-olioita
     *
     * @param paneeli määrittää sen, mihin paikkaan peliruudut piirretään.
     */
    public void piirraPeliruudut(JPanel paneeli) {

        for (int i = 0; i < peliruudukko.length; i++) {
            this.peliruudukko[i] = new JButton("" + (i + 1));
            paneeli.add(peliruudukko[i]);
            peliruudukko[i].addActionListener(this);
        }
    }

    public void piirraHallintapaneeli(JPanel paneeli) {

        aloitaNappi.setText("Aloita uusi peli");
        aloitaNappi.setBackground(Color.GREEN);
        paneeli.add(aloitaNappi);
        aloitaNappi.addActionListener(this);

        lopetaNappi.setText("Lopeta");
        lopetaNappi.setBackground(Color.RED);
        paneeli.add(lopetaNappi);

        lopetaNappi.addActionListener(this);

        yritysLabel.setText(pelaaja.getYrityksetTekstina());
        paneeli.add(yritysLabel);

        korttejaJaljellaLabel.setText(pelipoyta.getKorttejaJaljellaTekstina());
        paneeli.add(korttejaJaljellaLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lopetaNappi) {
            System.exit(0);
        }

        if (e.getSource() == aloitaNappi) {
            JOptionPane.showMessageDialog(getFrame(), "Valitettavasti tämä toiminto ei ole vielä käytössä :(");
            // ei toimi vielä

        }

        for (int i = 0; i < peliruudukko.length; i++) {

            if (e.getSource() == peliruudukko[i]) {

                if (!pelipoyta.onkoKorttiValittavissa(i)) {
                    JOptionPane.showMessageDialog(getFrame(), "Virheellinen valinta! Valitse toinen kortti");
                } else {
                    pelipoyta.valitseKortti(i);
                    peliruudukko[i].setText(pelipoyta.getKorttiTaulukosta(i).toString());
                }

                if (pelipoyta.montakoValittu() > 1) {
                    pelipoyta.lisaaValintayritys();

                    JButton valittuNappi1 = peliruudukko[pelipoyta.getValitutIndeksit().get(0)];
                    JButton valittuNappi2 = peliruudukko[pelipoyta.getValitutIndeksit().get(1)];

                    if (pelipoyta.loytyikoPari()) {
                        JOptionPane.showMessageDialog(getFrame(), "Pari löytyi!");
                        lisaaTekstiaNappiin(valittuNappi1, "");
                        lisaaTekstiaNappiin(valittuNappi2, "");
                    } else {
                        JOptionPane.showMessageDialog(getFrame(), "Paria ei löytynyt. Jatka painamalla OK");

                        lisaaTekstiaNappiin(valittuNappi1, "" + (pelipoyta.getValitutIndeksit().get(0) + 1));
                        lisaaTekstiaNappiin(valittuNappi2, "" + (pelipoyta.getValitutIndeksit().get(1) + 1));
                    }

                    pelipoyta.tyhjaaValitutIndeksit();
                    break;
                }
            }
        }
        this.yritysLabel.setText(pelipoyta.getYritystenMaaraTekstina());
        this.korttejaJaljellaLabel.setText(this.pelipoyta.getKorttejaJaljellaTekstina());

        if (!pelipoyta.jatketaankoPelia()) {
            JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Käytit " + pelipoyta.getYritystenMaaraLukuna() + " yritystä.");
        }

    }

    public void lisaaTekstiaNappiin(JButton nappi, String teksti) {
        nappi.setText(teksti);
    }
}
