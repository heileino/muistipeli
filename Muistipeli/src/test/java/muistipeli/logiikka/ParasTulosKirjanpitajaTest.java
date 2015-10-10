//package muistipeli.logiikka;
//
//import java.io.File;
//import java.net.URL;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Heikki Leinonen
// */
//public class ParasTulosKirjanpitajaTest {
//
//    ParasTulosKirjanpito parasTulosKp;
//    File testitiedosto;
//    String tiedostonimi;
//    int paras;
//
//    @Before
//    public void setUp() {
//        paras = 9999;
//        tiedostonimi = "testi.txt";
//
//        parasTulosKp = new ParasTulosKirjanpito(tiedostonimi);
//    }
////        URL url = this.getClass().getResource("/testi.txt");
////        testitiedosto = new File(url.getFile());
////        parasTulos = new ParasTulosKirjanpito("testi.txt");
//
//    @Test
//    public void konstruktoriAsettaaParasTulosMuuttujaanOikeanArvon() {
//
//        assertTrue(this.parasTulosKp.getParasTulos() == 9999);
//    }
//
//    @Test
//    public void toStringToimiiOikeinAlkuarvolla() {
//
//        assertEquals("Paras tulos: - ", parasTulosKp.toString());
//    }
//
//    @Test
//    public void toStringToimiiOikeinAsetetullaArvolla() {
//        parasTulosKp.setParasTulos(40);
//        assertEquals("Paras tulos: 40", parasTulosKp.toString());
//    }
//
////    @Test
////    public void luoTiedostoToimiiOikein() {
////
////        parasTulos.setParasTulos(15);
////        parasTulos.luoTiedosto();
////
////        assertTrue(parasTulos.getParasTulos() == 15);
////    }
//    @Test
//    public void lataaParasTulosToimiiOikeinLaillisellaParametrilla() {
//        parasTulosKp.setParasTulos(10);
//        parasTulosKp.lataaParasTulos();
//        assertTrue(parasTulosKp.getParasTulos() == 10);
//    }
//
//    @Test
//    public void setParasTulosToimiiOikeinLaittomallaParametrilla() {
//        parasTulosKp.setParasTulos(30);
//        parasTulosKp.setParasTulos(-1);
//        assertTrue(parasTulosKp.getParasTulos() == 30);
//    }
//
//    @Test
//    public void setParasTulosToimiiOikeinKunParametriOnParas() {
//        parasTulosKp.setParasTulos(50);
//        assertTrue(parasTulosKp.getParasTulos() == 50);
//    }
//
//    @Test
//    public void setParasTulosToimiiOikeinKunParametriEiOleParas() {
//        parasTulosKp.setParasTulos(30);
//        parasTulosKp.setParasTulos(31);
//        assertTrue(parasTulosKp.getParasTulos() == 30);
//    }
//
//    @Test
//    public void setParasTulosToimiiOikeinKunParametriOnSamaKuinParas() {
//        parasTulosKp.setParasTulos(30);
//        parasTulosKp.setParasTulos(30);
//        assertTrue(parasTulosKp.getParasTulos() == 30);
//    }
//
//    public void setParasTulosToimiiOikeinKunParametriOnYhdenHuonompiKuinParas() {
//        parasTulosKp.setParasTulos(30);
//        parasTulosKp.setParasTulos(31);
//        assertFalse(parasTulosKp.getParasTulos() == 31);
//    }
//}
