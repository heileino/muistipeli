//package muistipeli.logiikka;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Heikki Leinonen
// */
//public class ParasTulosTest {
//
//    ParasTulos parasTulos;
//
//    @Before
//    public void setUp() {
//
//        parasTulos = new ParasTulos("testiparas.txt");
//        
//    }
//
//    @Test
//    public void konstruktoriAsettaaParasTulosMuuttujaanOikeanArvon() {
//
//        assertTrue(this.parasTulos.getParasTulos() == 9999);
//    }
//
//    @Test
//    public void toStringToimiiOikeinAlkuarvolla() {
//
//        assertEquals("Paras tulos: - ", parasTulos.toString());
//    }
//
//    @Test
//    public void toStringToimiiOikeinAsetetullaArvolla() {
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 40);
//        assertEquals("Paras tulos: 40", parasTulos.toString());
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
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 10);
//        parasTulos.lataaParasTulos(parasTulos.getTiedostonimi());
//        assertTrue(parasTulos.getParasTulos() == 10);
//    }
//
////    @Test
////    public void setParasTulosToimiiOikeinLaittomallaParametrilla() {
////        parasTulos.setParasTulos(30);
////        parasTulos.setParasTulos(-1);
////        assertTrue(parasTulos.getParasTulos() == 30);
////    }
//    @Test
//    public void setParasTulosToimiiOikeinKunParametriOnParas() {
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 50);
//        assertTrue(parasTulos.getParasTulos() == 50);
//    }
//
//    @Test
//    public void setParasTulosToimiiOikeinKunParametriEiOleParas() {
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 30);
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 31);
//        assertTrue(parasTulos.getParasTulos() == 30);
//    }
//
//    @Test
//    public void setParasTulosToimiiOikeinKunParametriOnSamaKuinParas() {
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 30);
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 30);
//        assertTrue(parasTulos.getParasTulos() == 30);
//    }
//
//    public void setParasTulosToimiiOikeinKunParametriOnYhdenHuonompiKuinParas() {
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 30);
//        parasTulos.setParasTulos(parasTulos.getTiedostonimi(), 31);
//        assertFalse(parasTulos.getParasTulos() == 31);
//    }
//}
