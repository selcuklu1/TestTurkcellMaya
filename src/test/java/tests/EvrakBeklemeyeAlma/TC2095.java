package tests.EvrakBeklemeyeAlma;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import common.BaseTest;
import data.User;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 23.12.2017
 * Açıklama:
 */
public class TC2095 extends BaseTest {
    EvrakOlusturPage evrakOlustur;
    User user1 = new User("user1", "123", "User1 TEST");
    User user2 = new User("ztekin", "123");

    @Test(description = "TC2095: Evrak Beklemeye Alma")
    public void TC2095_imza() {
        String konu = evrakOlustur();
        System.out.println(konu);
        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage().openPage();
        imzaBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first().click();
//        SelenideElement beklemeyeAlButton = $x("//*[text()='Beklemeye Al']/ancestor::tbody[1]//button");
        $x("//*[text()='Beklemeye Al']/ancestor::tbody[1]//button").click();
//        SelenideElement beklemeyeAlEvetButton = $("#mainInboxForm\\:beklemeyeAlEvetButton");
        clickJs($("#mainInboxForm\\:beklemeyeAlEvetButton"));
//        $$("#mainInboxForm\\:beklemeyeAlEvetButton").shouldHave(sizeGreaterThan(0))
//                .filterBy(visible).last().click();
        imzaBekleyenlerPage.islemMesaji().basariliOlmali();
        imzaBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(0);

        BeklemeyeAlinanlarPage beklemeyeAlinanlarPage = new BeklemeyeAlinanlarPage().openPage();
        beklemeyeAlinanlarPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first().click();
        $x("//*[text()='Beklemeye Al']/ancestor::tbody[1]//button").shouldNotBe(exist);
        imzala();
        beklemeyeAlinanlarPage.islemMesaji().basariliOlmali();

        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage().openPage();
        imzaladiklarimPage.filter().findRowsWith(text(konu)).shouldHaveSize(1);
    }

    @Test(description = "TC2095: Paraf bekleyen evarkalarda \"Beklemeye\" al butonun gelmediği görülür")
    public void TC2095_parafla() throws Exception {
        login(user1);
        ParafBekleyenlerPage page = new ParafBekleyenlerPage().openPage();
        page.filter().getTableRows().shouldHave(sizeGreaterThan(0)).first().click();
        $("#mainInboxForm\\:beklemeyeAlEvetButton").shouldNotBe(visible);
    }

    @Test(description = "TC2095: Gelen evarkalarda \"Beklemeye\" al butonun gelmediği görülür")
    public void TC2095_gelen() throws Exception {
        login(user2);
        GelenEvraklarPage page = new GelenEvraklarPage().openPage();
        page.filter().getTableRows().shouldHave(sizeGreaterThan(0)).first().click();
        $("#mainInboxForm\\:beklemeyeAlEvetButton").shouldNotBe(visible);
    }

    @Test(description = "TC2095: Postalanacak evarkalarda \"Beklemeye\" al butonun gelmediği görülür")
    public void TC2095_postalanacak() throws Exception {
        login(user1);
        PostalanacakEvraklarPage page = new PostalanacakEvraklarPage().openPage();
        page.filter().getTableRows().shouldHave(sizeGreaterThan(0)).first().click();
        $("#mainInboxForm\\:beklemeyeAlEvetButton").shouldNotBe(visible);
    }

    @Step("Evrak Oluştur, imzala tıkla imzalamadan çık")
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

        String konu = "TC2095_" + getSysDate();
        login(user1);
        evrakOlustur = new EvrakOlusturPage().openPage();
        EvrakOlusturPage.BilgilerTab tab = evrakOlustur.bilgilerTabiAc()
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
//                .onayAkisiKullaniciTipiSec("User1 TEST", akisAdim)
                .akisAdimSec(akisAdim)
//                .akisAdimSec(akisAdim)
                .onayAkisiKullan()
                .miatDoldur(miat);


        evrakOlustur.editorTabAc()
                .editorIcerikDoldur(editorIcerik)
                .imzala()
                .popupImzalaVeEvrakKapatma();
        return konu;
    }

    @Step("İmzala")
    private void imzala() {
        $x("//*[text()='İmzala']/ancestor::tbody[1]//button").click();
        $("div[id='imzalaForm:imzalaRadio']").shouldBe(visible).click();
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
