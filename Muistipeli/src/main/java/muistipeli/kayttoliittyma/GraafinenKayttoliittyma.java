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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import muistipeli.logiikka.*;

/**
 * Luokka tarjoaa muistipelille graafisen käyttöliittymän.
 *
 * @author Heikki Leinonen
 */
public class GraafinenKayttoliittyma implements Runnable {

    private JFrame frame;
    private Pelipoyta pelipoyta;
    private Pelaaja pelaaja;
    private JButton[] peliruudukko;
    private JButton lopetaNappi;
    private JButton aloitaNappi;

    /**
     * Konstruktori luo uuden pelipöydän, pelaajan ja peliruudukon.
     */
    public GraafinenKayttoliittyma() {
        pelipoyta = new Pelipoyta();
        pelaaja = new Pelaaja();
        peliruudukko = new JButton[16];
        lopetaNappi = new JButton();
        aloitaNappi = new JButton();
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
        JPanel peliPaneeli = new JPanel(new GridLayout(4, 4));
        piirraPeliruudut(peliPaneeli);

        JPanel hallintaPaneeli = new JPanel();
        hallintaPaneeli.setLayout(new BoxLayout(hallintaPaneeli, BoxLayout.PAGE_AXIS));

        piirraHallintaruutu(hallintaPaneeli);

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
            peliruudukko[i].addActionListener(new KlikkaustenKuuntelija(this.pelipoyta, this.peliruudukko));
        }
    }

    public void piirraHallintaruutu(JPanel paneeli) {

        aloitaNappi.setText("Aloita uusi peli");
        aloitaNappi.setBackground(Color.GREEN);
        paneeli.add(aloitaNappi);
        aloitaNappi.addActionListener(null);

        lopetaNappi.setText("Lopeta");
        lopetaNappi.setBackground(Color.RED);
        paneeli.add(lopetaNappi);

        lopetaNappi.addActionListener(new LopetaKuuntelija());

        JLabel yritykset;
        yritykset = new JLabel("Yrityksiä: " + pelaaja.getYritykset());
        paneeli.add(yritykset);

        JLabel korttejaJaljella;
        korttejaJaljella = new JLabel("Kortteja jäljellä: " + pelipoyta.getKorttejaJaljella());
        paneeli.add(korttejaJaljella);
    }

    /**
     * Metodi luo uuden pelipöydän, täyttää sen ja sekoittaa kortit eli
     * valmistelee pelipöydän uutta peliä varten.
     */
    public void aloitaUudelleen() {
        this.pelipoyta = new Pelipoyta();
        this.pelipoyta.taytaPoyta();
        this.pelipoyta.sekoitaKortit();

    }
}
