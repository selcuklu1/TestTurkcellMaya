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
    }

    @Test(enabled = true, description = "TS1957A : Yazışma Kuralı Ekle")
    public void TS1957A() {

        String basariMesaj = "İşlem başarılıdır!";
        String birimAdi = "Yürütme Organları";
        Boolean sinirsizYazilabilir = true;
        Boolean vekaletSeviyesi = true;
        Boolean sonImzaSeviyesi = false;

        login("mbozdemir", "123");

        yazismaKurallariYonetimiPage
                .openPage()
                .yazismaKuraliSilEgerVarsa(birimAdi)
                .yazismaKurallariEkle()
                .birimTipiSec(birimAdi)
                .tiklaSinirsizYazilabilir(sinirsizYazilabilir)
                .tiklaVekaletSeviyesi(vekaletSeviyesi)
                .tiklaSonImzaSeviyesi(sonImzaSeviyesi)
                .grupBirimTipleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaj)
                .yazismakurallariKontrolEt(birimAdi, true, sinirsizYazilabilir, vekaletSeviyesi, sonImzaSeviyesi);

    }

    @Test(enabled = true, description = "TS1957B : Yazışma Kuralı Sil")
    public void TS1957B() {

        String basariMesaj = "İşlem başarılıdır!";
        String birimAdi = "Yürütme Organları";

        login("mbozdemir", "123");
        yazismaKurallariYonetimiPage
                .openPage()
                .yazismaKuraliSil(birimAdi)
                .islemMesaji().basariliOlmali(basariMesaj);

    }

    @Test(enabled = true, description = "TS1957C : Yazışma Kuralı Güncelle")
    public void TS1957C() {

        String birimAdi = "Yürütme Organları";
        String basariMesaj = "İşlem başarılıdır!";
        String yeniBirimAdi = "testgkc";
        boolean sinirsizYazilabilir = false;
        boolean vekaletSeviyesi = false;
        boolean sonImzaSeviyesi = false;

        login("mbozdemir", "123");

        yazismaKurallariYonetimiPage
                .openPage()
                .yazismaKuraliSilEgerVarsa(birimAdi)
                .yazismaKurallariEkle()
                .birimTipiSec(birimAdi)
                .tiklaSinirsizYazilabilir(sinirsizYazilabilir)
                .tiklaVekaletSeviyesi(vekaletSeviyesi)
                .tiklaSonImzaSeviyesi(sonImzaSeviyesi)
                .grupBirimTipleriKaydet()
                .islemMesaji().basariliOlmali();

        yazismaKurallariYonetimiPage
                .yazismakurallariKontrolEt(birimAdi, true, sinirsizYazilabilir, vekaletSeviyesi, sonImzaSeviyesi);

        sinirsizYazilabilir = true;
        vekaletSeviyesi = true;
        sonImzaSeviyesi = true;
        yazismaKurallariYonetimiPage
                .yazismaKuraliGuncelle(birimAdi)
                .birimTipiSec(yeniBirimAdi)
                .tiklaSinirsizYazilabilir(sinirsizYazilabilir)
                .tiklaVekaletSeviyesi(vekaletSeviyesi)
                .tiklaSonImzaSeviyesi(sonImzaSeviyesi)
                .grupBirimTipleriKaydet()
                .islemMesaji().basariliOlmali();
        yazismaKurallariYonetimiPage
                        .yazismakurallariKontrolEt(yeniBirimAdi, true, sinirsizYazilabilir, vekaletSeviyesi, sonImzaSeviyesi);


    }


}