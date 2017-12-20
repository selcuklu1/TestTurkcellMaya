package tests.YazismaKurallariYonetimi;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.YazismaKurallariYonetimiPage;


public class YazismaKurallariYonetimiTest extends BaseTest {

   YazismaKurallariYonetimiPage yazismaKurallariYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        yazismaKurallariYonetimiPage = new YazismaKurallariYonetimiPage();
        login();
    }

    @Test(enabled = true, description = "TC01957_A : Yazışma Kuralı Ekle")
    public void TC01957_A() {

        String basariMesaj = "İşlem başarılıdır!";
        String birimAdi = "Diğer Birimler";
        Boolean sinirsizYazilabilir = true;
        Boolean vekaletSeviyesi = true;
        Boolean sonImzaSeviyesi = false;

        yazismaKurallariYonetimiPage
                .open()
                .yazismaKurallariEkle()
                .birimTipiSec(birimAdi)
                .tiklaSinirsizYazilabilir(sinirsizYazilabilir)
                .tiklaVekaletSeviyesi(vekaletSeviyesi)
                .tiklaSonImzaSeviyesi(sonImzaSeviyesi)
                .grupBirimTipleriKaydet()
                .yazismakurallariKontrolEt(birimAdi, true, sinirsizYazilabilir, vekaletSeviyesi, sonImzaSeviyesi)
                .islemMesaji().basariliOlmali(basariMesaj);

    }

    @Test(enabled = true, description = "TC01957_B : Yazışma Kuralı Sil")
    public void TC01957_B() {

        String basariMesaj = "İşlem başarılıdır!";
        String birimAdi = "Belde Belediyesi";

        yazismaKurallariYonetimiPage
                .open()
                .yazismaKuraliSil(birimAdi)
                .islemMesaji().basariliOlmali(basariMesaj);

    }

    @Test(enabled = true, description = "TC01957_C : Yazışma Kuralı Güncelle")
    public void TC01957_C() {

        String basariMesaj = "İşlem başarılıdır!";
        String guncellenecekBirimadi = "Diğer Birimler";
        String yeniBirimAdi = "Belde Belediyesi";
        Boolean sinirsizYazilabilir = true;
        Boolean vekaletSeviyesi = true;
        Boolean sonImzaSeviyesi = false;

        yazismaKurallariYonetimiPage
                .open()
                .yazismaKuraliGuncelle(guncellenecekBirimadi)
                .birimTipiSec(yeniBirimAdi)
                .tiklaSinirsizYazilabilir(sinirsizYazilabilir)
                .tiklaVekaletSeviyesi(vekaletSeviyesi)
                .tiklaSonImzaSeviyesi(sonImzaSeviyesi)
                .grupBirimTipleriKaydet()
                .yazismakurallariKontrolEt(guncellenecekBirimadi, true, sinirsizYazilabilir, vekaletSeviyesi, sonImzaSeviyesi)
                .islemMesaji().basariliOlmali(basariMesaj);

    }






}