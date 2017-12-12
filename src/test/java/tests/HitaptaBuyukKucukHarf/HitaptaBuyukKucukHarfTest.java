package tests.HitaptaBuyukKucukHarf;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;

/****************************************************
 * Tarih: 2017-12-28
 * Proje: Türksat Functional Test Automation
 * Class: "Hitapta Büyük Küçük Harf" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class HitaptaBuyukKucukHarfTest extends BaseTest {

    EvrakOlusturPage evrakOlustur;

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        evrakOlustur = new EvrakOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2064: Tüzel kişi- Hitapta büyük/küçük harf kontrolü")
    public void TC2064() throws InterruptedException {

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
    @Test(enabled = true, description = "TC2156: Kurum- Hitapta büyük/küçük harf kontrolü")
    public void TC2156() throws InterruptedException {

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
    @Test(enabled = true, description = "TC2157: Birim- Hitapta büyük/küçük harf kontrolü")
    public void TC2157() throws InterruptedException {

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

    public void hitapKontrol(String geregiSecimTipi, String geregi, String beklenenHitap) throws InterruptedException {

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiDoldur(geregi);

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
