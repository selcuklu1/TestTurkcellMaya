package tests.HitaptaBuyukKucukHarf;

import com.codeborne.selenide.ElementsCollection;
import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.SistemSabitleriPage;

/****************************************************
 * Tarih: 2017-12-28
 * Proje: Türksat Functional Test Automation
 * Class: "Hitapta Büyük Küçük Harf" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class HitaptaBuyukKucukHarfTest extends BaseTest {

    EvrakOlusturPage evrakOlustur;
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        evrakOlustur = new EvrakOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2064: Tüzel kişi- Hitapta büyük/küçük harf kontrolü")
    public void TS2064() throws InterruptedException {

        String geregiSecimTipi = "Tüzel Kişi";

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE TÜZEL KİŞİ";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE TÜZEL KİŞİYE";

        String geregiHepsiKucuk = "hepsi küçük harflerle tüzel kişi";
        String beklenenHepsiKucukHitap = "HEPSİ KÜÇÜK HARFLERLE TÜZEL KİŞİYE";

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Tüzel Kişi";
        String beklenenBuyukKucukHitap = "BÜYÜK KÜÇÜK HARFLERLE TÜZEL KİŞİYE";

        evrakOlustur
                .openPage();

        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2156: Kurum- Hitapta büyük/küçük harf kontrolü")
    public void TS2156() throws InterruptedException {

        String geregiSecimTipi = "Kurum";

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE KURUM";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE KURUMA";

        String geregiHepsiKucuk = "hepsi küçük harflerle kurum";
        String beklenenHepsiKucukHitap = "hepsi küçük harflerle kuruma";

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Kurum";
        String beklenenBuyukKucukHitap = "Büyük Küçük Harflerle Kuruma";

        evrakOlustur
                .openPage();

        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2157: Birim- Hitapta büyük/küçük harf kontrolü")
    public void TS2157() throws InterruptedException {

        String geregiSecimTipi = "Birim";

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE BİRİM";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE BİRİME";

        String geregiHepsiKucuk = "hepsi küçük harflerle birim";
        String beklenenHepsiKucukHitap = "hepsi küçük harflerle birime";

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Birim";
        String beklenenBuyukKucukHitap = "Büyük Küçük Harflerle Birime";

        evrakOlustur
                .openPage();

        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

    }

    @Test(enabled = false, description = "TS2090: Dağıtım planına ve kullanıcıya hitap")
    public void TS2090() throws Exception {

        //Dağıtım planına ve kullanıcıya hitap


        //sistemde kayıtlı dağıtım planı olmalı, dağıtım planının içeriğinde küçük harfli birim, büyük harfli kurum olmalı

//        DAĞITIM YERLERİNE

        login(user1);
        SistemSabitleriPage sistemSabitleriPage = new SistemSabitleriPage().openPage();
        sistemSabitleriPage.sorgulamaVeFiltreleme()
                .alanDoldur("Ad", "Dağıtım Planı Hitap")
                .butonaTikla("Ara");
        ElementsCollection c = sistemSabitleriPage.aramaSonucuBul("Dağıtım Planı Hitap");

    }

    public void hitapKontrol(String geregiSecimTipi, String geregi, String beklenenHitap) throws InterruptedException {

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiDoldur(geregi, "");

        evrakOlustur
                .editorTabAc()
                .editorHitapKontrol(beklenenHitap);

        windowHandleBefore();

        evrakOlustur
                .pdfOnIzleme();

        Thread.sleep(6000);
        switchToNewWindow();

        evrakOlustur
                .pdfKontrol
                .PDFHitapKontrol(beklenenHitap);

        switchToDefaultWindow();

        evrakOlustur
                .bilgilerTabiAc();
    }
}
