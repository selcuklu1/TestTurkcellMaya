package common;

import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Condition.visible;

public class PreCondition extends BaseLibrary {

    GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
    EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
    BaseTest baseTest = new BaseTest();

    public static class BakimaAlinanlarEvrakOlustur{

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        BaseTest baseTest = new BaseTest();

    @Step("Beklemeye Alınanlar evrak oluşturmaktadır")
    @Test(enabled = true)
    public void bakimaAlinanlarEvrakOlusturA(String konuKodu,String konu,String kaldirilacakKlasor,String geregiSecimTipi,String geregi,String kullanici1,String OnayAkisiKullanici1Turu,String kullanici2,String kullaniciBirim, String OnayAkisiKullanici2Turu,String icerik) {

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .onayAkisiEkleIlkSelectSec(OnayAkisiKullanici1Turu)
                .kullanicilarDoldur(kullanici2,kullaniciBirim)
                .kullaniciylaSecimTipiSec(kullanici2,OnayAkisiKullanici2Turu)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);
        evrakOlusturPage
                .parafla();
    }
    @Step("Beklemeye Alınanlar evrak oluşturmaktadır")
    @Test(enabled = true)
    public void bakimaAlinanlarEvrakOlusturB(String konuKodu,String konu,String kaldirilacakKlasor,String geregiSecimTipi,String geregi,String kullanici1,String OnayAkisiKullanici1Turu,String kullanici2,String kullaniciBirim, String OnayAkisiKullanici2Turu,String icerik) {

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .onayAkisiEkleIlkSelectSec(OnayAkisiKullanici1Turu)
                .kullanicilarDoldur(kullanici2,kullaniciBirim)
                .kullaniciylaSecimTipiSec(kullanici2,OnayAkisiKullanici2Turu)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);
        evrakOlusturPage
                .parafla();
    }
    }

}
