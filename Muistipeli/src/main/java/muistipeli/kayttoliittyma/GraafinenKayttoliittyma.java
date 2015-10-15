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
    private Pelimoottori pelimoottori;
    private JButton[] peliruudukko;
    private JButton aloitaNappi, lopetaNappi;
    private JPanel peliPaneeli, hallintaPaneeli;
    private JLabel yritysLabel, korttejaJaljellaLabel, parasTulosLabel;
    private int ikkunanLeveys;
    private int ikkunanKorkeus;
    private Kuva kuvat;

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
        ikkunanLeveys = 900;
        ikkunanKorkeus = 500;
        kuvat = new Kuva();
    }

    @Override
    public void run() {
        pelimoottori.pelaaPeli();

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
     * Metodi luo muistikortteja vastaavan ruudukon JButton-olioita.
     *
     * @param paneeli määrittää mihin paikkaan peliruudut piirretään.
     */
    public void piirraPeliruudut(JPanel paneeli) {

        for (int i = 0; i < peliruudukko.length; i++) {
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

        yritysLabel.setText(pelimoottori.getYritystenMaaraTekstina());
        paneeli.add(yritysLabel);

        korttejaJaljellaLabel.setText(pelimoottori.getLoytamattomienKorttiparienMaaraTekstina());
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
            uudelleenAloitus();
        }

        for (int i = 0; i < peliruudukko.length; i++) {

            if (e.getSource() == peliruudukko[i]) {

                if (!pelimoottori.valintaOk(i)) {
                    JOptionPane.showMessageDialog(getFrame(), "Virheellinen valinta!");
                    return;
                } else {
                    peliruudukko[i].setIcon(kuvat.getKuva(pelimoottori.getKortinTunniste(i)));
                }

                if (pelimoottori.valitaankoToinenKortti()) {
                    return;
                }

                if (pelimoottori.ovatkoValinnatPareja()) {
                    JOptionPane.showMessageDialog(getFrame(), "Pari löytyi!");
                } else {
                    JOptionPane.showMessageDialog(getFrame(), "Paria ei löytynyt");

                    peliruudukko[pelimoottori.getValinnanIndeksi(0)].setIcon(kuvat.getKuva("selkakortti"));
                    peliruudukko[pelimoottori.getValinnanIndeksi(1)].setIcon(kuvat.getKuva("selkakortti"));

                }

                this.yritysLabel.setText(pelimoottori.getYritystenMaaraTekstina());
                this.korttejaJaljellaLabel.setText("Pareja jäljellä: " + pelimoottori.getLoytymattomienParienLukumaara());
                break;
            }
        }

        if (!pelimoottori.jatkuukoPeli()) {

            if (pelimoottori.onUusiParasTulos()) {
                this.parasTulosLabel.setText(pelimoottori.getParasTulosTekstina());
                JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Teit uuden ennätyksen, " + pelimoottori.getYritysmaaraLukuna() + " yritystä");
            } else {
                JOptionPane.showMessageDialog(getFrame(), "Peli päättyi. Käytit " + pelimoottori.getYritysmaaraLukuna() + " yritystä.");
            }

            int jatkohalu = JOptionPane.showConfirmDialog(getFrame(), "Haluatko pelata uudelleen?", null, JOptionPane.YES_NO_OPTION);

            if (pelimoottori.aloitetaankoUusiPeli(jatkohalu)) {
                uudelleenAloitus();
            }
        }
    }

    protected void uudelleenAloitus() {
        pakotaKaikkiKortitNurin();
        pelimoottori = new Pelimoottori();
        this.yritysLabel.setText(pelimoottori.getYritystenMaaraTekstina());
        this.korttejaJaljellaLabel.setText("Pareja jäljellä: " + pelimoottori.getLoytymattomienParienLukumaara());
        pelimoottori.pelaaPeli();
    }

    private void pakotaKaikkiKortitNurin() {

        for (int i = 0; i < peliruudukko.length; i++) {

            peliruudukko[i].setIcon(kuvat.getKuva("selkakuva"));
        }
    }
}
