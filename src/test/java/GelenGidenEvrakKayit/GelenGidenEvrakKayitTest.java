package GelenGidenEvrakKayit;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageComponents.IslemMesajlari;
import pages.pageComponents.SolMenu;
import pages.pageData.SolMenuData;
import pages.solMenuPages.KaydedilenGelenEvrakPage;
import pages.solMenuPages.KaydedilenGelenEvraklarPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

public class GelenGidenEvrakKayitTest extends BaseTest {
    MainPage mainPage;
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
        mainPage = new MainPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        kaydedilenGelenEvrakPage = new KaydedilenGelenEvrakPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void TC0321() throws InterruptedException {

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\pdf2.pdf")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldurLovText(geldigiKurum)
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
//        pages.islemMesaji().beklenenMesajTipi(MessageTitle.BASARILI);


        kaydedilenGelenEvraklarPage
                .openPage()
//        TODO  tabloda oluşturulan evrak bulunacak....
                .raporSec();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Gelen evrak kayıt ekranından havale")
    public void TC0328 () throws InterruptedException{

        String birim = "OPTİİM BİRİM11";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\pdf2.pdf")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldurLovText(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur(evrakSayiSag)
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldur("OPTİİM")
                .kaydet()
                .popUps();
       // page.islemMesaji().beklenenMesajTipi(IslemMesajlari.MessageTitle.BASARILI);
       // page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
//        TODO  tabloda oluşturulan evrak bulunacak....
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .raporSec();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Kaydedilen Gelen Evrak raporu")
    public void  TC1401 () throws InterruptedException{


        String evrakNo = "4940";
        String evrakNo1 = "4941";
        String geldigiYer = "D";
        kaydedilenGelenEvrakPage
                .openPage()
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
    @Test(enabled = false, description = "Gelen evrak kaydederken yeni gerçek ve tüzel kişi tanımlama")
    public void  TC1136 () throws InterruptedException {

        String TCKN = "51091330934";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriListKisiKurumSec("G")
                .evrakBilgileriGeldigiKisiEkle()
                .IletisimBilgisiTCKNEkle(TCKN)
                .IletisimBilgisiTCKNAra()
//                .IletisimBilgisiAdDoldur("Test")
//                .IletisimBilgisiSoyadDoldur("Otomasyon")
                .IletisimBilgisikaydet();
//        Gerçek kişi yönetimi ekranında yeni kaydı kontrol et

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Gelen maillerin evrak olarak sisteme dahil edilmesi")
    public void TC0394 () throws InterruptedException{

        String birim = "OPTİİM BİRİM11";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\mailt.msg")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldurLovText(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur(evrakSayiSag)
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .evrakBilgileriEkBilgiFiltreAc()
//                .evrakEkTabViewEkle() excel eklenecek
                .kaydet()
                .popUps();
     //   page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
      //  page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
//        TODO  tabloda oluşturulan evrak bulunacak....
        teslimAlinmayiBekleyenlerPage
                .raporSec();
    }
}
