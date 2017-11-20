package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;
import static pages.pageComponents.belgenetElements.BelgentCondition.not;
import static pages.pageComponents.belgenetElements.BelgentCondition.required;

///////////////////////////////////////////////////////////////////////////
// Ilyas Bayraktar
///////////////////////////////////////////////////////////////////////////
public class EvrakOlusturPage {

    //region Elements
    SelenideElement tabEditor = $("button .editor");
    SelenideElement tabEkleri = $("button .kullaniciEkleri");
    SelenideElement tabIlgileri = $("button .kullaniciIlgileri");
    SelenideElement tabIliskiliEvraklar = $("button .kullaniciIliskileri");
    SelenideElement tabSablonIslemleri = $("button .sablonOlustur");
    SelenideElement tabEvrakNotlari = $("button .evrakNot");
    SelenideElement tabEvrakDogrulama = $("button .evrakDogrulamaAktarimIslemleri");

    SelenideElement btnPDFOnizleme = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='pdfOnIzleme']");
    SelenideElement btnKaydet = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydet']");
    SelenideElement btnKaydetOnayaSun = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydetHavaleEt']");
    SelenideElement btnPaylas = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='evrakPaylas']");
    //endregion

    public EvrakOlusturPage open() {
        new UstMenu().ustMenu("Evrak Oluştur");
        $("#yeniGidenEvrakForm").shouldBe(visible);
        return this;
    }

    private BilgilerTab goToBilgilerTab() {
        BilgilerTab bilgilerTab = new BilgilerTab();
        Assert.assertTrue(bilgilerTab.isOnTabPage(), "Bilgiler tabı açılmadı");
        return bilgilerTab;

    }

    public class BilgilerTab {

        //region Elements
        SelenideElement tabBilgiler = $("button .kullaniciBilgileri");
        SelenideElement divContainer = $("#evrakBilgileriContainerDiv");

        //label[normalize-space(text())='Konu Kodu']/../..//input

        BelgenetElement cmlKonuKodu = comboLov("input[id$='konuKoduLov:LovText']");
        SelenideElement btnKonuKoduTree = $("button[id$='konuKoduLov:treeButton']");
        SelenideElement txtKonu = $("textarea[id$='konuTextArea']");
        BelgenetElement cmbKaldiralacakKlasorler = comboLov("input[id$='eklenecekKlasorlerLov:LovText']");
        SelenideElement btnKaldiralacakKlasorlerTree = $("button[id$='eklenecekKlasorlerLov:treeButton']");

        SelenideElement cmbEvrakTuru = $("select[id$='evrakTuruCombo']");

        SelenideElement dateKayitTarihi = $("input[id$='kayitTarih_input']");
        SelenideElement cmbEvrakDili = $("select[id$=evrakDili]");
        SelenideElement cmbGizlilikDerecesi = $("select[id$=guvenlikKodu]");

        //Kanun Kapsam Tipi
        SelenideElement rdbKanunKapsamTipiNormal = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Normal'])]");
        SelenideElement rdbKanunKapsamTipiBilgiEdinmeKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Bilgi Edinme Kanunu'])]");
        SelenideElement rdbKanunKapsamTipiKisiselVerilerinKorunmasiKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Kişisel Verilerin Korunması Kanunu'])]");

        SelenideElement txtEvrakSayiEkMetni = $("input[id$='evrakSayiEkMetniInputText']");

        SelenideElement txtAciklama = $(By.xpath("//form[@id='yeniGidenEvrakForm']//label[normalize-space(text())='Açıklama']/ancestor::tr[@class='ui-datagrid-row']//textarea"));
        SelenideElement cmbIvedik = $("select[id$='ivedilik']");
        SelenideElement dateMiat = $("input[id$='miatCalendar_input']");

        SelenideElement cmbBilgiSecimTipi = $(By.xpath("//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));
        BelgenetElement txtBilgi = comboLov("input[id$='bilgiLov:LovText']");
        SelenideElement btnBilgiTree = $("button[id$='bilgiLov:treeButton']");

        SelenideElement cmbGeregiSecimTipi = $(By.xpath("//label[normalize-space(text())='Gereği Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));
        BelgenetElement txtGeregi = comboLov("input[id$='geregiLov:LovText']");
        SelenideElement btnGeregiTree = $("button[id$='geregiLov:treeButton']");

        SelenideElement chkDagitimiEkYap = $("input[id$='dagitimEkYapCheckBoxId_input']");
        BelgenetElement cmbOnayAkisi = comboLov("input[id$='akisLov:LovText']");
        //endregion

        public boolean isOnTabPage() {
            return divContainer.is(visible);
        }

        @Step("Konu Kodu alanında \"{0}\" seç")
        public BilgilerTab konuKoduSec(String value) {
            cmlKonuKodu.selectComboLov(value);
            return this;
        }

        @Step("Konu Kodu alanı zorunluluk kontrol")
        public boolean konuKoduZorunlukKontrol(boolean zorunlu) {
            return cmlKonuKodu.is(required);
        }

        @Step("Konu alanında \"{0}\" seç")
        public BilgilerTab konuSec(String value) {
            txtKonu.setValue(value);
            return this;
        }

        @Step("Konu alanı \"{0}\" ile doldur")
        public BilgilerTab zorunluKodu() {
            txtKonu.is(required);
            return this;
        }

        @Step("Kaldiralacak Klasörler alanında \"{0}\" seç")
        public BilgilerTab kaldiralacakKlasorlerSec(String value) {
            cmbKaldiralacakKlasorler.selectComboLov(value);
            return this;
        }

        @Step("Evrak Türü alanında \"{0}\" seç")
        public BilgilerTab evrakTuruSec(String text) {
//            if (!cmbEvrakTuru.getSelectedOption().equals(text))
            cmbEvrakTuru.selectOption(text);
            return this;
        }

        @Step("Kayıt Tarih alanında \"{0}\" seç")
        public BilgilerTab dateKayitTarihiSec(String dateText) {
            dateKayitTarihi.setValue(dateText);
            return this;
        }

        @Step("Evrak Dili alanında \"{0}\" seç")
        public BilgilerTab evrakDiliSec(String text) {
            cmbEvrakDili.selectOption(text);
//            if (cmbEvrakTuru.getSelectedOption().equals(text))
//                throw new RuntimeException("Alan seçilemedi");
            return this;
        }

        @Step("Gizlilik Derecesi alanında \"{0}\" seç")
        public BilgilerTab gizlilikDerecesiSec(String text) {
            cmbGizlilikDerecesi.selectOption(text);
            return this;
        }

        @Step("Kanun Kapsam Tipi normal seç")
        public BilgilerTab kanunKapsamTipiNormalSec() {
            rdbKanunKapsamTipiNormal.click();
            return this;
        }

        @Step("Kanun Kapsam Tipi Bilgi Edinme Kanunu seç")
        public BilgilerTab kanunKapsamTipiBilgiEdinmeKanunuSec() {
            rdbKanunKapsamTipiBilgiEdinmeKanunu.click();
            return this;
        }

        @Step("Kanun Kapsam Tipi Kisisel Verilerin Korunmasi Kanunu seç")
        public BilgilerTab kanunKapsamTipiKisiselVerilerinKorunmasiKanunuSec() {
            rdbKanunKapsamTipiKisiselVerilerinKorunmasiKanunu.click();
            return this;
        }

        @Step("Evrak Sayi Ek Metni alanında \"{0}\" seç")
        public BilgilerTab evrakSayiEkMetniSec(String text) {
            txtEvrakSayiEkMetni.setValue(text);
            return this;
        }

        @Step("Açıklama gir")
        public BilgilerTab aciklamaSec(String text) {
            txtAciklama.setValue(text);
            return this;
        }

        @Step("İvedik seç")
        public BilgilerTab ivedikSec(String text) {
            cmbIvedik.selectOption(text);
            return this;
        }


        public BilgilerTab openPage() {
            if (divContainer.is(not(visible)))
                tabBilgiler.click();

            divContainer.shouldBe(visible);
            return this;

        }

        public BelgenetElement getKonuKodu() {
            return cmlKonuKodu;
        }

        public SelenideElement getKonu() {
            return txtKonu;
        }
    }


}
