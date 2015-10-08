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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import muistipeli.logiikka.*;

/**
 * Luokka tarjoaa muistipelille graafisen käyttöliittymän.
 *
 * @author Heikki Leinonen
 */
public class GraafinenKayttoliittyma implements Runnable, ActionListener {

    private JFrame frame;
    private Pelimoottori pelimoottori;
    private JButton[] peliruudukko;
    private JButton aloitaNappi, lopetaNappi;
    private JButton valinta1, valinta2;
    private JPanel peliPaneeli, hallintaPaneeli;
    private JLabel yritysLabel, korttejaJaljellaLabel, parasTulosLabel;
    private int ikkunanLeveys;
    private int ikkunanKorkeus;

    /**
     * Konstruktori luo uuden pelimoottorin ja peliruudukon.
     */
    public GraafinenKayttoliittyma() {
        pelimoottori = new Pelimoottori();
        peliruudukko = new JButton[16];
        lopetaNappi = new JButton();
        aloitaNappi = new JButton();
        peliPaneeli = new JPanel();
        hallintaPaneeli = new JPanel();
        yritysLabel = new JLabel();
        korttejaJaljellaLabel = new JLabel();
        parasTulosLabel = new JLabel();
        ikkunanLeveys = 700;
        ikkunanKorkeus = 450;
    }

    @Override
    public void run() {

        pelimoottori.alustaPoytaPelikuntoon();

        frame = new JFrame("Muistipeli");
        frame.setPreferredSize(new Dimension(ikkunanLeveys, ikkunanKorkeus));

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

        yritysLabel.setText(pelimoottori.getYritystenMaaraTekstina());
        paneeli.add(yritysLabel);

        korttejaJaljellaLabel.setText(pelimoottori.getKorttejaJaljellaTekstina());
        paneeli.add(korttejaJaljellaLabel);

        parasTulosLabel.setText(pelimoottori.getParasTulosTekstina());
        paneeli.add(parasTulosLabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lopetaNappi) {
            System.exit(0);
        }

        if (e.getSource() == aloitaNappi) {
            // ei toimi vielä
            JOptionPane.showMessageDialog(getFrame(), "Valitettavasti tämä toiminto ei ole vielä käytössä :(");

        }

        for (int i = 0; i < peliruudukko.length; i++) {

            if (e.getSource() == peliruudukko[i]) {

                if (!pelimoottori.onkoKorttiValittavissa(i)) {
                    JOptionPane.showMessageDialog(getFrame(), "Virheellinen valinta! Valitse toinen kortti");
                } else {
                    pelimoottori.valitseKortti(i);
                    peliruudukko[i].setText(pelimoottori.getKortti(i).toString());

                    if (pelimoottori.montakoValittu() > 1) {

                        pelimoottori.lisaaValintayritys();

                        valinta1 = peliruudukko[pelimoottori.getValitutIndeksit().get(0)];
                        valinta2 = peliruudukko[pelimoottori.getValitutIndeksit().get(1)];

                        if (pelimoottori.loytyikoPari()) {
                            JOptionPane.showMessageDialog(getFrame(), "Pari löytyi!");
                            lisaaTekstiaNappiin(valinta1, "");
                            lisaaTekstiaNappiin(valinta2, "");

                        } else {
                            JOptionPane.showMessageDialog(getFrame(), "Paria ei löytynyt. Jatka painamalla OK");
                            
                            lisaaTekstiaNappiin(valinta1, "" + (pelimoottori.getValitutIndeksit().get(0) + 1));
                            lisaaTekstiaNappiin(valinta2, "" + (pelimoottori.getValitutIndeksit().get(1) + 1));

                             
                        }

                        pelimoottori.tyhjaaValitutIndeksit();
                    }
                }
            }

        }

        this.yritysLabel.setText(pelimoottori.getYritystenMaaraTekstina());
        this.korttejaJaljellaLabel.setText(pelimoottori.getKorttejaJaljellaTekstina());

        if (!pelimoottori.jatketaankoPelia()) {
            if (pelimoottori.onUusiParasTulos()) {
                JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Teit uuden ennätyksen, " + pelimoottori.getYritystenMaaraLukuna() + " yritystä");
                pelimoottori.lopetaPeli();
                this.parasTulosLabel.setText(pelimoottori.getParasTulosTekstina());
            } else {
                JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Käytit " + pelimoottori.getYritystenMaaraLukuna() + " yritystä.");
            }

            
           
        }

        return;
    }

    /**
     * Metodi lisää parametrina annettuun JButton-olioon toisena paremetrina
     * saadun tekstin.
     *
     * @param nappi
     * @param teksti
     */
    public void lisaaTekstiaNappiin(JButton nappi, String teksti) {
        nappi.setText(teksti);
    }
}
