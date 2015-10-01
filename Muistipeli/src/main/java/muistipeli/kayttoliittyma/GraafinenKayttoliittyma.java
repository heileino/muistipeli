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
import java.util.*;
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
    private JButton lopetaNappi;
    private JButton aloitaNappi;
    private JPanel peliPaneeli;
    private JPanel hallintaPaneeli;
    private int valintaIndeksi1, valintaIndeksi2;
    private int jarjestysNumero;
    private JLabel yritysLabel;
    private JLabel korttejaJaljellaLabel;
    private List<Kortti> valitutKortit;

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
        valintaIndeksi1 = -1;
        valintaIndeksi2 = -2;
        jarjestysNumero = 1;
        yritysLabel = new JLabel();
        korttejaJaljellaLabel = new JLabel();
        this.valitutKortit = new ArrayList<>();
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

    /**
     * Metodi luo uuden pelipöydän, täyttää sen ja sekoittaa kortit eli
     * valmistelee pelipöydän uutta peliä varten. EI TOIMI VIELÄ!
     */
    public void aloitaUudelleen() {
//        this.pelipoyta = new Pelipoyta();
//        this.pelipoyta.taytaPoyta();
        this.pelipoyta.sekoitaKortit();
        this.peliPaneeli = new JPanel(new GridLayout(4, 4));
        piirraPeliruudut(this.peliPaneeli);

        this.hallintaPaneeli = new JPanel(new BoxLayout(hallintaPaneeli, BoxLayout.PAGE_AXIS));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lopetaNappi) {
            System.exit(0);
        }

        if (e.getSource() == aloitaNappi) {
            aloitaUudelleen(); // ei toimi vielä
        }

        for (int i = 0; i < peliruudukko.length; i++) {

            if (e.getSource() == peliruudukko[i]) {

                Kortti valinta = pelipoyta.getTaulukko()[i];

                if (valinta.getNakyvyys()) {
                    JOptionPane.showMessageDialog(getFrame(), "Virhe! Valitse jokin toinen kortti");
                } else {
                    this.valitutKortit.add(valinta);
                    this.pelipoyta.paljastaKortti(i);
                    peliruudukko[i].setText(valinta.toString());
                }

                if (this.valitutKortit.size() > 1) {
                    break;
                }
            }
        }

        if (this.valitutKortit.size() > 1) {

            this.pelaaja.lisaaYritys();
            this.yritysLabel.setText(pelaaja.getYrityksetTekstina());

            Kortti kortti1 = this.valitutKortit.get(0);
            Kortti kortti2 = this.valitutKortit.get(1);

            if (pelipoyta.onkoSamaKortti(kortti1, kortti2)) {
                pelipoyta.lisaaKorttiLoytyneisiin(kortti1);
                pelipoyta.lisaaKorttiLoytyneisiin(kortti2);

                pelipoyta.vahennaKorttejaJaljella();
                this.korttejaJaljellaLabel.setText(this.pelipoyta.getKorttejaJaljellaTekstina());
                JOptionPane.showMessageDialog(getFrame(), "Pari löytyi!");
                this.valitutKortit = new ArrayList<>();

            } else {
                JOptionPane.showMessageDialog(getFrame(), "Paria ei löytynyt. Jatka painamalla OK");
                peliruudukko[pelipoyta.getKortinIndeksi(kortti1)].setText("" + (pelipoyta.getKortinIndeksi(kortti1) + 1));
                peliruudukko[pelipoyta.getKortinIndeksi(kortti2)].setText("" + (pelipoyta.getKortinIndeksi(kortti2) + 1));
                pelipoyta.piilotaKortti(pelipoyta.getKortinIndeksi(kortti1));
                pelipoyta.piilotaKortti(pelipoyta.getKortinIndeksi(kortti2));
                this.valitutKortit = new ArrayList<>();
            }

            if (pelipoyta.getKorttejaJaljella() < 2) {
                JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Käytit " + pelaaja.getYritykset() + " yritystä.");
            }
        }
    }
}
