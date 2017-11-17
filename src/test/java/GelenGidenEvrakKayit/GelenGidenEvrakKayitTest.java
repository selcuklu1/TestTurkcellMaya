package GelenGidenEvrakKayit;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.solMenuPages.KaydedilenGelenEvrakPage;
import page.solMenuPages.KaydedilenGelenEvraklarPage;
import page.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import page.ustMenuPages.GelenEvrakKayitPage;
import pageComponents.BasePage;
import pageData.MesajTipi;
import pageData.SolMenuData;

public class GelenGidenEvrakKayitTest extends BaseTest {
    BasePage page;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    KaydedilenGelenEvrakPage kaydedilenGelenEvrakPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;

    String konuKodu = "010.01";
    String evrakTuru = "R";
    String evrakDili = "917";
    String evrakTarihi = "16.11.2017";
    String gizlilikDerecesi = "N";
    String kisiKurum = "D";
    String geldigiKurum = "Esk Kurum 071216 2";
    String evrakSayiSag = "12365";
    String evrakGelisTipi = "P";
    String ivedilik= "N";
    String ekMetni = "test otomasyon";

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        kaydedilenGelenEvrakPage = new KaydedilenGelenEvrakPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void TC0321() throws InterruptedException {

        page.ustMenu("Gelen Evrak Kayıt");
        gelenEvrakKayitPage
//                .evrakBilgileriUstYaziEkle("C:/Users/Emre_Sencan/Pictures/pdf2.pdf")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldur(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur(evrakSayiSag)
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                //Excel eklenecek
                .evrakBilgileriEkBilgiFiltreAc()
                .evrakBilgileriEkBilgiFizikselEkEkle()
                .evrakEkTabViewFizikselEkMetniDoldur(ekMetni)
                .evrakFizikselEkTabViewAciklamaEkle()
                .kaydet()
                .popUps();
//        page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
        page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
//        TODO  tabloda oluşturulan evrak bulunacak....
        kaydedilenGelenEvraklarPage
        .raporSec();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Gelen evrak kayıt ekranından havale")
    public void TC0328 () throws InterruptedException{
        String birim = "OPTİİM BİRİM11";
        page.ustMenu("Gelen Evrak Kayıt");
        gelenEvrakKayitPage
//                .evrakBilgileriUstYaziEkle("C:/Users/Emre_Sencan/Pictures/pdf2.pdf")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldur(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur(evrakSayiSag)
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldur("OPTİİM")
                .kaydet()
                .popUps();
        page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
        page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
//        TODO  tabloda oluşturulan evrak bulunacak....
        teslimAlinmayiBekleyenlerPage
                .raporSec();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Kaydedilen Gelen Evrak raporu")
    public void  TC1401 () throws InterruptedException{

        page.ustMenu("Raporlar","Kaydedilen Gelen Evrak");
        String evrakNo = "4940";
        String evrakNo1 = "4941";
        String geldigiYer = "D";
        kaydedilenGelenEvrakPage
                .gelenEvrakNoDoldur(evrakNo)
                .sorgula()
//                .raporAlExcel()
                .txtClear()
                .gelenEvrakNoDoldur(evrakNo1)
                .sorgula()
                .geldigiYerSec(geldigiYer)
                .sorgula();
//                .raporAlExcel();  pop upta ok butonuna basılacak
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Gelen evrak kaydederken yeni gerçek ve tüzel kişi tanımlama")
    public void  TC1136 () throws InterruptedException {
        page.ustMenu("Gelen Evrak Kayıt");
        gelenEvrakKayitPage
                .evrakBilgileriListKisiKurumSec("G")
                .evrakBilgileriGeldigiKisiEkle()
                .IletisimBilgisiTCKNEkle()
                .IletisimBilgisiTCKNAra()
                .IletisimBilgisikaydet();
//        Gerçek kişi yönetimi ekranında yeni kaydı kontrol et

    }
}
