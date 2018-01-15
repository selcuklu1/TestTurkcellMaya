package tests.EvrakBeklemeyeAlma;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.pageComponents.Filtreler;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 23.12.2017
 * Açıklama:
 */

@Feature("Evrakı Beklemeye Alma")
public class TS2095 extends BaseTest {

    //    data.User user1 = new data.User("user1", "123", "User1 TEST");
    User user1 = new User("optiim", "123", "Optiim TEST");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN");
    Filtreler filtrelerPage = new Filtreler();


    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TS2095: Evrakı beklemeye alma ve imzalama")
    public void TS2095a() {

        String konu = evrakOlustur();

        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage().openPage();
        dokumaniAraVeSec(konu);
        beklemeyeAlButonaTikla();
        beklemeyeAlOnayMessage(true);
        imzaBekleyenlerPage.islemMesaji().basariliOlmali();
        dokumanBulunmamali(konu);

        BeklemeyeAlinanlarPage beklemeyeAlinanlarPage = new BeklemeyeAlinanlarPage().openPage();
        dokumaniAraVeSec(konu);
        beklemeyeAlNotVisible();
        beklemeyeAlinanlarPage.evrakImzala();
        beklemeyeAlinanlarPage.islemMesaji().basariliOlmali();

        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage().openPage();
        dokumanBulunmali(konu);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "TS2095: Paraf, Gelen, Postalanacak bekleyen evarklarda \"Beklemeye Al\" butonun gelmediği görülür", enabled = true)
    public void TS2095b() {

        login(ztekin);

        new ParafBekleyenlerPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();

        new GelenEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();

        new PostalanacakEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }


    //TS2095_2 birleştirilmiştir, müşterinin isteği!
    @Severity(SeverityLevel.MINOR)
    @Test(description = "TS2095: Paraf bekleyen evarkalarda \"Beklemeye\" al butonun gelmediği görülür", enabled = false)
    public void TS2095c() {
        login(ztekin);
        new ParafBekleyenlerPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "TS2095: Gelen evarkalarda \"Beklemeye\" al butonun gelmediği görülür", enabled = false)
    public void TS2095d() {
        login(ztekin);
        new GelenEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "TS2095: Postalanacak evarkalarda \"Beklemeye\" al butonun gelmediği görülür", enabled = false)
    public void TS2095e() {
        login(ztekin);
        new PostalanacakEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }


    @Step("Seçilen evrakta \"Beklemeye Al\" butonun gelmediği görülür")
    public TS2095 ilkDokumaniSecBeklemeyeAlBulunmamali() {
        filtrelerPage.getSearchRows().shouldHave(sizeGreaterThan(0)).first().click();
        beklemeyeAlNotVisible();
        return this;
    }

    @Step("Dokumanı bulunmalı")
    public TS2095 dokumanBulunmali(String konu) {
        filtrelerPage.findRowsWith(text(konu)).shouldHaveSize(1);
        return this;
    }

    @Step("Dokumanı ara ve seç")
    public TS2095 dokumaniAraVeSec(String konu) {
        filtrelerPage.findRowsWith(text(konu)).shouldHaveSize(1).first().click();
        return this;
    }

    @Step("Dokumanı bulunamamalı")
    public TS2095 dokumanBulunmamali(String konu) {
        filtrelerPage.findRowsWith(text(konu)).shouldHaveSize(0);
        return this;
    }

    @Step("Beklemeye Al butonu ara")
    public SelenideElement beklemeyeAlButton() {
        return $x("//*[text()='Beklemeye Al']/ancestor::tbody[1]//button");
    }

    @Step("Beklemeye Al butona tıkla")
    public TS2095 beklemeyeAlButonaTikla() {
        beklemeyeAlButton().click();
        return this;
    }

    @Step("Beklemeye Al butonu gelmediği görülür")
    public TS2095 beklemeyeAlNotVisible() {
        $("#mainPreviewForm").shouldBe(visible)
                .$$(".buttonMenuTextDefault").filterBy(text("Beklemeye Al"))
                .shouldHaveSize(0);
        takeScreenshot();
//        beklemeyeAlButton().shouldNotBe(visible);
        return this;
    }

    @Step("Uyarı Messajı: Evrakı beklemeye almak istediğinize emin misiniz? Evet")
    public TS2095 beklemeyeAlOnayMessage(boolean evet) {
        if (evet) {
            $("#mainInboxForm\\:beklemeyeAlEvetButton").shouldBe(visible);
            $("#mainInboxForm\\:beklemeyeAlEvetButton").pressEnter();
        }
        return this;
    }

    @Step("Evrak oluştur, imzala tıkla ve imzalamadan çık")
    public String evrakOlustur() {
        String konuKodu = "Gelen-Giden Evrak";
        String evrakTuru = "Resmi Yazışma";
        String evrakDerecesi = "Hizmete Özel";
        String evrakSayiEkMetni = "A1";
        String ivedilik = "Günlü";
        String miat = getSysDateForKis();
        String geregiSecimBirim = "Birim";
        String geregiBirim = "Optiim Birim";//"AFYON VALİLİĞİ";
        String akisAdim = "İmzalama";
        String kaldirilacakKlasorler = "Diğer";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String ekleriDosyaAciklama = "Açıklama";

        EvrakOlusturPage evrakOlustur = new EvrakOlusturPage();

        String konu = "TS2095_" + getSysDate();
        login(user1);
        EvrakOlusturPage.BilgilerTab tab = evrakOlustur
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .kaldirilacakKlasorler(kaldirilacakKlasorler)
                .evrakTuruSec(evrakTuru)
                .evrakDerecesiSec(evrakDerecesi)
                .evrakSayiEkMetniDoldur(evrakSayiEkMetni)
                .ivediSec(ivedilik)
                /*$("span[id$='miaTSalendar'] button[class~='ui-datepicker-trigger']").click();
                String dayOfMonth =  String.valueOf(LocalDateTime.now().getDayOfMonth());
                $("div[id='ui-datepicker-div']").$(By.linkText(dayOfMonth)).click();*/
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiDoldur(geregiBirim, "Birim")
//                .onayAkisiKullanicilariTemizle()
//                .onayAkisiEkle("User2 TEST")
//                .onayAkisiKullaniciTipiSec("User2 TEST", "Paraflama")
//                .onayAkisiEkle("User1 TEST")
//                .onayAkisiKullaniciTipiSec("User1 TEST", "İmzalama")
                .onayAkisiEkle()
//                .onayAkisiKullaniciTipiSec(user1.getName(), akisAdim)
                .akisAdimSec(akisAdim)
//                .akisAdimSec(akisAdim)
                .onayAkisiKullan()
                .miatDoldur(miat);

        evrakOlustur.editorTabAc()
                .editorIcerikDoldur(editorIcerik)
                .imzala()
                .popupImzalaVeEvrakKapatma();

        log.info("Oluşturan dokümanın konu: " + konu);
        return konu;
    }
}
