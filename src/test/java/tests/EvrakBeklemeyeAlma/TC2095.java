package tests.EvrakBeklemeyeAlma;

import com.codeborne.selenide.Configuration;
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
import static com.codeborne.selenide.Selenide.*;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 23.12.2017
 * Açıklama:
 */

@Feature("Evrakı Beklemeye Alma")
public class TC2095 extends BaseTest {

//    User user1 = new User("user1", "123", "User1 TEST");
    User user1 = new User("optiim", "123", "Optiim TEST");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN");
    Filtreler filtrelerPage = new Filtreler();


    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TC2095: Evrakı beklemeye alma ve imzalama")
    public void TC2095_1_imza() {

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
        imzala();
        beklemeyeAlinanlarPage.islemMesaji().basariliOlmali();

        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage().openPage();
        dokumanBulunmali(konu);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC2095: Paraf, Gelen, Postalanacak bekleyen evarkalar \"Beklemeye Al\" butonun gelmediği görülür", enabled = true)
    public void TC2095_2() throws Exception {

        login(ztekin);

        new ParafBekleyenlerPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();

        new GelenEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();

        new PostalanacakEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC2095: Paraf bekleyen evarkalarda \"Beklemeye\" al butonun gelmediği görülür", enabled = true)
    public void TC2095_3_parafla() throws Exception {
        login(ztekin);
        new ParafBekleyenlerPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC2095: Gelen evarkalarda \"Beklemeye\" al butonun gelmediği görülür", enabled = true)
    public void TC2095_4_gelen() throws Exception {
        login(ztekin);
        new GelenEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "TC2095: Postalanacak evarkalarda \"Beklemeye\" al butonun gelmediği görülür", enabled = true)
    public void TC2095_5_postalanacak() throws Exception {
        login(ztekin);
        new PostalanacakEvraklarPage().openPage();
        ilkDokumaniSecBeklemeyeAlBulunmamali();
    }

    @Step("Seçilen evrakta \"Beklemeye Al\" butonun gelmediği görülür")
    public TC2095 ilkDokumaniSecBeklemeyeAlBulunmamali(){
        filtrelerPage.getSearchRows().shouldHave(sizeGreaterThan(0)).first().click();
        beklemeyeAlNotVisible();
        return this;
    }

    @Step("Dokumanı bulunmalı")
    public TC2095 dokumanBulunmali(String konu){
        filtrelerPage.findRowsWith(text(konu)).shouldHaveSize(1);
        return this;
    }

    @Step("Dokumanı ara ve seç")
    public TC2095 dokumaniAraVeSec(String konu){
        filtrelerPage.findRowsWith(text(konu)).shouldHaveSize(1).first().click();
        return this;
    }

    @Step("Dokumanı bulunamamalı")
    public TC2095 dokumanBulunmamali(String konu){
        filtrelerPage.findRowsWith(text(konu)).shouldHaveSize(0);
        return this;
    }

    @Step("Beklemeye Al butonu ara")
    public SelenideElement beklemeyeAlButton(){
        return $x("//*[text()='Beklemeye Al']/ancestor::tbody[1]//button");
    }

    @Step("Beklemeye Al butona tıkla")
    public TC2095 beklemeyeAlButonaTikla(){
        beklemeyeAlButton().click();
        return this;
    }

    @Step("Beklemeye Al butonu gelmediği görülür")
    public TC2095 beklemeyeAlNotVisible(){
        takeScreenshot();
        beklemeyeAlButton().shouldNotBe(visible);
        return this;
    }

    @Step("Uyarı Messajı: Evrakı beklemeye almak istediğinize emin misiniz? Evet")
    public TC2095 beklemeyeAlOnayMessage(boolean evet){
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
        String geregiBirim = "AFYON VALİLİĞİ";
        String akisAdim = "İmzalama";
        String kaldirilacakKlasorler = "Diğer";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String ekleriDosyaAciklama = "Açıklama";

        EvrakOlusturPage evrakOlustur = new EvrakOlusturPage();

        String konu = "TC2095_" + getSysDate();
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
                /*$("span[id$='miatCalendar'] button[class~='ui-datepicker-trigger']").click();
                String dayOfMonth =  String.valueOf(LocalDateTime.now().getDayOfMonth());
                $("div[id='ui-datepicker-div']").$(By.linkText(dayOfMonth)).click();*/
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiDoldur(geregiBirim)
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

    @Step("İmzala butonu ara")
    public SelenideElement imzalaButton(){
        return $x("//*[text()='İmzala']/ancestor::tbody[1]//button");
    }

    @Step("İmzala butona tıkla")
    public TC2095 imzalaButonaTikla(){
        imzalaButton().click();
        return this;
    }

    @Step("s-İmzla radio butonu ara")
    public SelenideElement sImzalaRadio(){
        return $("#imzalaForm\\:imzalaRadio .ui-radiobutton-box");
    }

    @Step("s-İmzla seç")
    public TC2095 sImzalaRadioSec(){
        sImzalaRadio().shouldBe(visible).click();
        return this;
    }

    @Step("İmzala")
    private void imzala() {
        imzalaButonaTikla();
        sImzalaRadioSec();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        for (int i = 0; i < Configuration.timeout/1000; i++) {
            sleep(1000);
            if ($("#imzalaForm\\:sayisalImzaConfirmDialogOpener").is(visible)){
                $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();
                clickJs($("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton"));
                break;
            }
            else{
                $("#imzalaForm\\:imzalaButton").click();
                break;
            }
        }
    }
}
