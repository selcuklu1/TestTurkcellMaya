package common;

import data.User;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.ustMenuPages.BirimYonetimiPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.TuzelKisiYonetimiPage;

import java.util.List;

public class ReusableSteps extends BaseLibrary{

    @Step("Beklemeye Alınanlar evrak Oluştur.")
    public void beklemeyeAlinanlarEvrakOlustur(String konu,String geregiSecimTipi, String geregi, String onayAkisiKullanici1Turu,String onayAkisiKullanici2,String onayAkisiKullaniciBirimi,String onayAkisiKullanici2turu,String user,String password) {

        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        LoginPage loginPage = new LoginPage();
        ImzaBekleyenlerPage imzaBekleyenler = new ImzaBekleyenlerPage();

        evrakOlusturPage.evrakOlusturParafla(konu,geregiSecimTipi,geregi,onayAkisiKullanici1Turu,onayAkisiKullanici2,onayAkisiKullaniciBirimi,onayAkisiKullanici2turu);
        loginPage.login(user,password);
        imzaBekleyenler.imzaBekleyenlerEvrakSecBeklemeyeAl(konu);
    }

    @Step("Beklemeye Alınanlar evrak Oluştur.")
    public void beklemeyeAlinanlarEvrakOlustur() {

    }

    @Step("Teslim Alınmayı Bekleyenler sayfasında evrak oluştur.")
    public void teslimAlinmayiBekleyenlerEvrakOlustur(String konu,String kurum,String birim) {

        GelenEvrakKayitPage gelenEvrakKayitPage =  new GelenEvrakKayitPage();

        gelenEvrakKayitPage
                .gelenEvrakKayitBirimHavaleEt(konu,kurum,birim);
    }

    @Step("Gelen Evraklar sayfasında evrak oluştur.")
    public void gelenEvraklarEvrakOlustur(String konu,String kurum,String birim) {

        GelenEvrakKayitPage gelenEvrakKayitPage =  new GelenEvrakKayitPage();

        gelenEvrakKayitPage
                .gelenEvrakKayitKullaniciHavaleEt(konu,kurum,birim);
    }

    @Step("Beklemeye Alınanlar evrak Oluştur.")
    public void beklemeyeAlinanlarEvrakOlustur(String konu, String geregiSecimTipi, String geregi, User imzaci) {
        new EvrakOlusturPage()
                .evrakOlusturParafla(konu,geregiSecimTipi,geregi,"Parafla",imzaci.getFullname(), imzaci.getBirimAdi(), "İzmala");
        new LoginPage().login(imzaci);
        new ImzaBekleyenlerPage().imzaBekleyenlerEvrakSecBeklemeyeAl(konu);
    }

    @Step("Medya şirketi tipinde tüzel kişi ekleme")
    public List<String> medyaSirketiTuzelKisiEkleme() {
        return new TuzelKisiYonetimiPage().medyaSirketiTuzelKisiEkleme();
    }

    @Step("Evrak Oluştur kaydet ve parafla")
    public void evrakOlusturVeParafla(String konu, GeregiSecimTipi geregiSecimTipi, String geregi, User parafci, User imzaci){
        pages.newPages.EvrakOlusturPage page = new pages.newPages.EvrakOlusturPage().openPage();
        page.bilgileriTab().alanlariDoldur(konu, geregiSecimTipi, geregi, parafci, imzaci);
        page.editorTab().openTab().getEditor().type(konu);
        //page.evrakPageButtons().evrakKaydet().islemMesaji().basariliOlmali();
        page.evrakParafla().islemMesaji().basariliOlmali();
    }

    @Step("Yeni birim kayıt")
    public List<String> yeniBirimKayit() {
        return new BirimYonetimiPage().yeniBirimKayit();
    }
}
