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
import javax.swing.ImageIcon;
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
    private Pelimoottori pelimoottori;
    private JButton[] peliruudukko;
    private JButton aloitaNappi, lopetaNappi;
    private JButton valinta1, valinta2;
    private JPanel peliPaneeli, hallintaPaneeli;
    private JLabel yritysLabel, korttejaJaljellaLabel, parasTulosLabel;
    private int ikkunanLeveys;
    private int ikkunanKorkeus;
    private Kuva kuvat;

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
        this.kuvat = new Kuva();
    }

    @Override
    public void run() {
        pelaaPeli();

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
//            this.peliruudukko[i] = new JButton("" + (i + 1));
//            this.peliruudukko[i] = new JButton(kuvat.getKuva(pelimoottori.getPelipoyta().getKorttiTaulukosta(i).toString()));
            this.peliruudukko[i] = new JButton(kuvat.getKuva("selkapuoli"));
            paneeli.add(peliruudukko[i]);
            peliruudukko[i].addActionListener(this);
        }
    }

    public void piirraHallintapaneeli(JPanel paneeli) {

        aloitaNappi.setText("Aloita uudestaan");
        aloitaNappi.setBackground(Color.GREEN);
        paneeli.add(aloitaNappi);
        aloitaNappi.addActionListener(this);

        lopetaNappi.setText("Lopeta");

        lopetaNappi.setBackground(Color.RED);
        paneeli.add(lopetaNappi);

        lopetaNappi.addActionListener(this);

        yritysLabel.setText(pelimoottori.getYritysmaaraLaskuri().toString());
        paneeli.add(yritysLabel);

        korttejaJaljellaLabel.setText(pelimoottori.getLoytamattomatKorttiparit().toString());
        paneeli.add(korttejaJaljellaLabel);

        parasTulosLabel.setText(pelimoottori.getParasTulos().toString());
        paneeli.add(parasTulosLabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lopetaNappi) {
            System.exit(0);
        }

        if (e.getSource() == aloitaNappi) {

            aloitaUudelleen();

        }

        for (int i = 0; i < peliruudukko.length; i++) {

            if (e.getSource() == peliruudukko[i]) {

                if (!onkoKorttiValittavissa(i)) {
                    JOptionPane.showMessageDialog(getFrame(), "Virheellinen valinta! Valitse toinen kortti");
                } else {
                    pelimoottori.getValitutPaikat().lisaaValittuihin(i);
                    pelimoottori.getPelipoyta().paljastaKortinKuva(i);
//                    peliruudukko[i].setText(pelimoottori.getPelipoyta().getKorttiTaulukosta(i).toString());
                    peliruudukko[i].setIcon(kuvat.getKuva(pelimoottori.getPelipoyta().getKorttiTaulukosta(i).toString()));

                    if (pelimoottori.getValitutPaikat().montakoValittu() > 1) {

                        pelimoottori.getYritysmaaraLaskuri().lisaaValintayritys();

                        valinta1 = peliruudukko[pelimoottori.getValitutPaikat().getValitutIndeksit().get(0)];
                        valinta2 = peliruudukko[pelimoottori.getValitutPaikat().getValitutIndeksit().get(1)];

                        Kortti kortti1 = pelimoottori.getPelipoyta().getKorttiTaulukosta(pelimoottori.getValitutPaikat().getValitutIndeksit().get(0));
                        Kortti kortti2 = pelimoottori.getPelipoyta().getKorttiTaulukosta(pelimoottori.getValitutPaikat().getValitutIndeksit().get(1));

                        if (loytyikoPari()) {
                            pelimoottori.getLoytyneetKortit().lisaaKortitLoytyneeksi(kortti1, kortti2);
                            pelimoottori.getLoytamattomatKorttiparit().vahennaParejaLoytymatta();
                            JOptionPane.showMessageDialog(getFrame(), "Pari löytyi!");

//                            lisaaTekstiNappiin(valinta1, "");
//                            lisaaTekstiNappiin(valinta2, "");
                        } else {
                            kaannaKortitNurin(kortti1, kortti2);

                            JOptionPane.showMessageDialog(getFrame(), "Paria ei löytynyt. Jatka painamalla OK");

                            peliruudukko[pelimoottori.getValitutPaikat().getValitutIndeksit().get(0)].setIcon(new ImageIcon("C:\\Users\\Heikki\\Documents\\GitHub\\muistipeli\\Muistipeli\\src\\main\\java\\muistipeli\\kayttoliittyma\\small_icon.gif"));
                            peliruudukko[pelimoottori.getValitutPaikat().getValitutIndeksit().get(1)].setIcon(new ImageIcon("C:\\Users\\Heikki\\Documents\\GitHub\\muistipeli\\Muistipeli\\src\\main\\java\\muistipeli\\kayttoliittyma\\small_icon.gif"));

//                            lisaaTekstiNappiin(valinta1, "" + (pelimoottori.getValitutPaikat().getValitutIndeksit().get(0) + 1));
//                            lisaaTekstiNappiin(valinta2, "" + (pelimoottori.getValitutPaikat().getValitutIndeksit().get(1) + 1));
                        }

                        pelimoottori.getValitutPaikat().tyhjaaValitutIndeksit();
                    }
                }
            }
        }

        this.yritysLabel.setText(pelimoottori.getYritysmaaraLaskuri().toString());
        this.korttejaJaljellaLabel.setText(pelimoottori.getLoytamattomatKorttiparit().toString());

        if (!jatketaankoPelia()) {
            if (onUusiParasTulos()) {
                JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Teit uuden ennätyksen, " + pelimoottori.getYritysmaaraLaskuri().getYritysmaara() + " yritystä");
                lopetaPeli();
                this.parasTulosLabel.setText(pelimoottori.getParasTulos().toString());
            } else {
                JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Käytit " + pelimoottori.getYritysmaaraLaskuri().getYritysmaara() + " yritystä.");
            }

        }

    }

    private void aloitaUudelleen() {
        this.pelimoottori = new Pelimoottori();
        pelaaPeli();
        pelimoottori.getPelipoyta().kaannaKaikkiKortitSelkapuolelle();
        kaannaKaikkiKortitNurin();
    }

    /**
     * Metodi lisää parametrina annettuun JButton-olioon toisena paremetrina
     * saadun tekstin.
     *
     * @param nappi
     * @param teksti
     */
//    public void lisaaTekstiNappiin(JButton nappi, String teksti) {
//        nappi.setText(teksti);
//    }
    public void kaannaKortitNurin(Kortti kortti1, Kortti kortti2) {
        pelimoottori.getPelipoyta().piilotaKortinKuva(pelimoottori.getPelipoyta().getKortinIndeksi(kortti1));
        pelimoottori.getPelipoyta().piilotaKortinKuva(pelimoottori.getPelipoyta().getKortinIndeksi(kortti2));
    }

    private void kaannaKaikkiKortitNurin() {

        for (int i = 0; i < peliruudukko.length; i++) {
            //lisaaTekstiNappiin(peliruudukko[i], "" + (i + 1));

            peliruudukko[i].setIcon(kuvat.getKuva("selkakuva"));
        }
    }

    /**
     * Metodi täyttää pelipöydän korteilla ja sekoittaa ne satunnaiseen
     * järjestykseen
     */
    private void pelaaPeli() {
        pelimoottori.getPelipoyta().asetaKortitTaulukkoon();
        pelimoottori.getPelipoyta().sekoitaTaulukonKortit();
        pelimoottori.getParasTulos().lataaParasTulos();
    }

    private void lopetaPeli() {

        if (onUusiParasTulos()) {
            pelimoottori.getParasTulos().setParasTulos(pelimoottori.getYritysmaaraLaskuri().getYritysmaara());
            pelimoottori.getParasTulos().lataaParasTulos();
        }

    }

    /**
     * Yksityinen metodi testaa, onko pelin tulos parempi kuin nykyinen paras
     * tulos.
     *
     * @return
     */
    private boolean onUusiParasTulos() {
        if (pelimoottori.getParasTulos().getParasTulos() == 0) {
            return true;
        } else {
            return pelimoottori.getYritysmaaraLaskuri().getYritysmaara() < pelimoottori.getParasTulos().getParasTulos();
        }

    }

    private boolean onkoKorttiValittavissa(int i) {
        Kortti valittuKortti = pelimoottori.getPelipoyta().getKorttiTaulukosta(i);
        return !valittuKortti.nakyykoKuvapuoli();
    }

    public boolean loytyikoPari() {
        Kortti kortti1 = pelimoottori.getPelipoyta().getKorttiTaulukosta(pelimoottori.getValitutPaikat().getValitutIndeksit().get(0));
        Kortti kortti2 = pelimoottori.getPelipoyta().getKorttiTaulukosta(pelimoottori.getValitutPaikat().getValitutIndeksit().get(1));

        return pelimoottori.getPelipoyta().onkoKorteillaSamaTunnus(kortti1, kortti2);
    }

    /**
     * Yksityinen metodi selvittää, jatketaanko peliä vielä. Se tapahtuu
     * tarkistamalla jäljellä olevien korttien määrän.
     *
     * @return palauttaa boolean totuusarvon pelin jatkumisesta
     */
    private boolean jatketaankoPelia() {
        return pelimoottori.getLoytamattomatKorttiparit().getParejaLoytymatta() > 0;
    }
}
