package common;

import data.User;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.ustMenuPages.EvrakOlusturPage;

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

}
