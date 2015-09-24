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

/**
 *
 * @author Heikki Leinonen
 */
public class GraafinenKayttoliittyma implements Runnable {
    
    private JFrame frame;
    
    @Override
    public void run() {
        frame = new JFrame("Muistipeli");
        frame.setPreferredSize(new Dimension(700, 400));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container) {
        JPanel peliPaneeli = new JPanel(new GridLayout(4, 4));
        container.add(peliPaneeli);
        piirraPeliruudut(peliPaneeli);
        
        JPanel hallintaPaneeli = new JPanel();
        hallintaPaneeli.setLayout(new BoxLayout(hallintaPaneeli, BoxLayout.PAGE_AXIS));
        container.add(hallintaPaneeli, BorderLayout.EAST);
        piirraHallintaruutu(hallintaPaneeli);
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    public void piirraPeliruudut(JPanel paneeli) {
        JButton nappi;
        for (int i = 0; i < 16; i++) {
            nappi = new JButton("" + (i + 1));
            paneeli.add(nappi);
        }
    }
    
    public void piirraHallintaruutu(JPanel paneeli) {
        JButton aloitaNappi;
        aloitaNappi = new JButton("Aloita");
        aloitaNappi.setBackground(Color.GREEN);
        paneeli.add(aloitaNappi);
        
        JButton lopetaNappi;
        lopetaNappi = new JButton("Lopeta");
        lopetaNappi.setBackground(Color.RED);
        paneeli.add(lopetaNappi);
        
        JLabel yritykset;
        yritykset = new JLabel("Yrityksiä: ");
        paneeli.add(yritykset);
        
        JLabel korttejaJaljella;
        korttejaJaljella = new JLabel("Kortteja jäljellä: ");
        paneeli.add(korttejaJaljella);
    }
}
