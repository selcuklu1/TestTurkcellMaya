package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class OlurYazisiOlusturPage extends MainPage {

    SelenideElement tabBilgiler = $("button[id^='yeniOnayEvrakForm'] span[class$='kullaniciBilgileri']");
    //SelenideElement tabBilgiler = $("button .kullaniciBilgileri");
    SelenideElement tabEditor = $("button .editor");
    //endregion
    //region Tabs local variables
    private BilgilerTab bilgilerTab = new BilgilerTab();
    private EditorTab editorTab = new EditorTab();

    @Step("Olur Yazısı Oluştur sayfasını aç")
    public OlurYazisiOlusturPage openPage() {
        ustMenu(UstMenuData.EvrakIslemleri.OlurYazisiOlustur);
        return this;
    }

    //region Tabs
    @Step("Bilgiler tab aç")
    public BilgilerTab bilgilerTabiAc() {
        return bilgilerTab.open();
    }

    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    public class BilgilerTab extends MainPage {

        SelenideElement divContainer = $("[id='yeniOnayEvrakForm' ] [id='evrakBilgileriContainerDiv']");

        // Onay Akışı Elementleri
        SelenideElement btnOnayAkisiEkle = $("#yeniOnayEvrakForm button[id*='onayAkisiEkle']");
        ElementsCollection trOnayAkisiEkleKullanicilar = $$("tbody[id*='akisAdimLov:LovSecilenTable_data'] tr[role='row']");
        BelgenetElement txtOnayAkisiKullanicilar = comboLov("[id$='akisAdimLov:LovText']");
        ElementsCollection listOnayAkisikullanicilar = $$("div[id*='akisAdimLov:lovTree'] ul li");
        SelenideElement txtOnayAkisiKullanicilarInput = $("input[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisAdimLov:LovText']");
        SelenideElement btnOnayAkisiPanelKapat = $("button[id^='yeniOnayEvrakForm:evrakBilgileriList:'][id$=':akisLov:lovTreePanelKapat']");
        BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
        BelgenetElement cmbKullanici = comboLov(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisAdimLov:LovText']"));
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
        SelenideElement cmbSelectOneMenu = $(By.id("yeniOnayEvrakForm:evrakBilgileriList:14:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement btnOnayAkisGuncelle = $(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList:14:akisLov:j_idt'] [class$='update-icon']"));
        SelenideElement btnKullan = $("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='anlikAkisKullanButton']");
        SelenideElement btnVekaletKaydet = $("[id^='yeniOnayEvrakForm:j_idt'] [class*='ui-button-text-only tipTip button-icon-borderless']");
        BelgenetElement cmbKonuKodu = comboLov("input[id$='konuKoduLov:LovText']");
        BelgenetElement txtKonuKodu = comboLov("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");
        SelenideElement txtKonu = $("textarea[id$='konuTextArea']");
        BelgenetElement cmbKaldiralacakKlasorler = comboLov("input[id$='eklenecekKlasorlerLov:LovText']");
        SelenideElement dateKayitTarihi = $("input[id$='kayitTarih_input']");
        SelenideElement cmbEvrakDili = $("select[id$=evrakDili]");
        SelenideElement cmbGizlilikDerecesi = $("select[id$=guvenlikKodu]");
        SelenideElement rdbKanunKapsamTipiNormal = $x("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Normal'])]");
        SelenideElement txtEvrakSayiEkMetni = $("input[id$='evrakSayiEkMetniInputText']");
        SelenideElement cmbIvedik = $("select[id$='ivedilik']");
        SelenideElement dateMiat = $("input[id$='miatCalendar_input']");
        SelenideElement cmbBilgiSecimTipi = $x("//form[@id='yeniOnayEvrakForm']//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select");
        BelgenetElement txtBilgi = comboLov("input[id$='bilgiLov:LovText']");
        SelenideElement cmbGeregiSecimTipi = $x("//form[@id='yeniOnayEvrakForm']//label[normalize-space(text())='Gereği Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select");
        BelgenetElement cmbGeregi = comboLov("[id^='yeniOnayEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");
        SelenideElement btnOnayAkisSil = $(By.cssSelector("[id^='yeniOnayEvrakForm:evrakBilgileriList:14:akisLov:j_idt'] [class$='delete-icon']"));

        //endregion

        private BilgilerTab open() {
            if (divContainer.is(not(visible)))
                tabBilgiler.click();

            //divContainer.shouldBe(visible);
            return this;
        }

        public boolean isOnTabPage() {
            return divContainer.is(visible);
        }

        @Step("Olur Yazısı Oluştur - Bilgiler Tabı tüm alan kontrolleri")
        public BilgilerTab bilgilerTabTumAlanKontrolleri() {

            if (cmbKonuKodu.isLovSelected()) {
                cmbKonuKodu.clearAllSelectedItems();
            }
            Assert.assertEquals( txtKonuKodu.isDisplayed(), true, "Konu kodu");
            Allure.addAttachment("Konu kodu alanı kontrolu başarılı", "");

            Assert.assertEquals( txtKonu.isDisplayed(), true, "Konu");
            Allure.addAttachment("Konu alanı kontrolu başarılı", "");

            if (cmbKaldiralacakKlasorler.isLovSelected()) {
                cmbKaldiralacakKlasorler.clearAllSelectedItems();
            }
            Assert.assertEquals( cmbKaldiralacakKlasorler.isDisplayed(), true, "Kaldıralacak Klasörler");
            Allure.addAttachment("Kaldıralacak Klasörler alanı kontrolu başarılı", "");

            Assert.assertEquals( dateKayitTarihi.isDisplayed(), true, "Kayit tarihi");
            Allure.addAttachment("Kayit tarihi alanı kontrolu başarılı", "");

            Assert.assertEquals( cmbEvrakDili.isDisplayed(), true, "Evrak Dili");
            Allure.addAttachment("Evrak Dili alanı kontrolu başarılı", "");

            Assert.assertEquals( cmbGizlilikDerecesi.isDisplayed(), true, "Gizlilik derecesi ");
            Allure.addAttachment("Gizlilik derecesi alanı kontrolu başarılı", "");

            Assert.assertEquals( rdbKanunKapsamTipiNormal.isDisplayed(), true, "Kanun Kapsam Tipi");
            Allure.addAttachment("Kanun Kapsam Tipi alanı kontrolu başarılı", "");

            Assert.assertEquals( txtEvrakSayiEkMetni.isDisplayed(), true, "Evrak Sayi Ek Metni");
            Allure.addAttachment("Evrak Sayi Ek Metni alanı kontrolu başarılı", "");

            Assert.assertEquals( cmbIvedik.isDisplayed(), true, "İvedik alanı");
            Allure.addAttachment("İvedik alanı kontrolu başarılı", "");

            Assert.assertEquals( dateMiat.isDisplayed(), true, "Miat alanı");
            Allure.addAttachment("Miat alanı kontrolu başarılı", "");

            Assert.assertEquals( cmbBilgiSecimTipi.isDisplayed(), true, "ilgi Seçim Tipi ");
            Allure.addAttachment("ilgi Seçim Tipi Balanı kontrolu başarılı", "");

            Assert.assertEquals( txtBilgi.isDisplayed(), true, "Bilgi alanı");
            Allure.addAttachment("Bilgi alanı kontrolu başarılı", "");

            Assert.assertEquals( cmbGeregiSecimTipi.isDisplayed(), true, "Gereği Seçim Tipi ");
            Allure.addAttachment("Gereği Seçim Tipi alanı kontrolu başarılı", "");

            Assert.assertEquals( cmbGeregi.isDisplayed(), true, "Gereği kodu ");
            Allure.addAttachment("Gereği kodu alanı kontrolu başarılı", "");

            if (cmbOnayAkisi.isLovSelected()) {
                cmbOnayAkisi.clearAllSelectedItems();
            }
            Assert.assertEquals( cmbOnayAkisi.isDisplayed(), true, "Onay Akışı");
            Allure.addAttachment("Onay Akışı alanı kontrolu başarılı", "");

            takeScreenshot();

            return this;
        }

        @Step("Onay Akışı Ekle")
        public BilgilerTab onayAkisiEkle() {
            btnOnayAkisiEkle.click();
            return this;
        }

        @Step("Onay akışında güncel gelen kullanıcıyı kontrolu")
        public BilgilerTab onayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .shouldHave(value(kullaniciTipi));
            return this;
        }

        @Step("Onay akışı kullanıcı adı ve koordine tipi kontrolu")
        public BilgilerTab onayAkisiKullaniciKoordineKontrol(String kullaniciAdi, String kullaniciTipi) {

            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$(("[id^='yeniOnayEvrakForm:evrakBilgileriList'] [class='lovItemDetail']"))
                    .text().contains(kullaniciTipi);

            return this;
        }

        @Step("Onay akışı listesinde listelenen kullanıcıyı kontrol et")
        public BilgilerTab onayAkisiTreeKullaniciKontrol(String kullaniciAdi, Boolean exist) {
            txtOnayAkisiKullanicilarInput.setValue(kullaniciAdi);
            if (exist == true)
                listOnayAkisikullanicilar
                        .filterBy(text(kullaniciAdi))
                        .get(0)
                        .shouldBe(Condition.exist);
            else
                listOnayAkisikullanicilar
                        .filterBy(text(kullaniciAdi))
                        .get(0)
                        .shouldBe(not(Condition.exist));

            if (btnOnayAkisiPanelKapat.isDisplayed())
                btnOnayAkisiPanelKapat.click();
            return this;
        }

        @Step("Onay akışı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisDoldur(String kullanici) {
            cmbOnayAkisi.selectLov(kullanici);
            return this;
        }

        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduDoldur(String konuKodu) {
            txtKonuKodu.selectLov(konuKodu);
            return this;
        }

        @Step("Konu doldur")
        public BilgilerTab konuDoldur(String konu) {
            txtKonu.clear();
            txtKonu.sendKeys(konu); //selenide
            return this;
        }

        @Step("Kaldiralacak Klasörler alanında \"{kaldirilacakKlasorler}\" seç")
        public BilgilerTab kaldiralacakKlasorlerSec(String kaldirilacakKlasorler) {
            cmbKaldiralacakKlasorler.selectLov(kaldirilacakKlasorler);
            return this;
        }

        @Step("Onay akışı doldur")
        public BilgilerTab onayAkisiDoldurWithoutKontrol(String onay) {
            if (cmbOnayAkisi.isLovSelected() == true) {
                cmbOnayAkisi.clearAllSelectedItems();
            }
            cmbOnayAkisi.type(onay).getDetailItems().first().click();
            return this;
        }

        @Step("Gizlilik Derecesi alanında {gizlilikDerecesi} seç")
        public BilgilerTab gizlilikDerecesiSec(String gizlilikDerecesi) {
            cmbGizlilikDerecesi.selectOption(gizlilikDerecesi);
            return this;
        }

        @Step("Gereği {description} doldur: | {geregi}")
        public BilgilerTab geregiDoldur(String geregi, String description) {
            cmbGeregi.selectLov(geregi);
            return this;
        }

        @Step("İvedik alanında \"{ivedilik}\" seç")
        public BilgilerTab ivedilikSec(String ivedilik) {
            cmbIvedik.selectOption(ivedilik);
            return this;
        }

        @Step("Gereği Seçim Tipi alanında \"{geregiSecimTipi}\" seç")
        public BilgilerTab geregiSecimTipiSecByText(String geregiSecimTipi) {
            cmbGeregiSecimTipi.shouldBe(visible);
            cmbGeregiSecimTipi.selectOption(geregiSecimTipi);
            return this;
        }

        @Step("Seçien onay akışını sil")
        public BilgilerTab secilenOnayAkisiSil() {

            if (cmbOnayAkisi.isLovSelected()) {
                cmbOnayAkisi.clearAllSelectedItems();
            }

            return this;
        }

        @Step("Onay akışı kullanıcı tipi seç")
        public BilgilerTab onayAkisiKullaniciTipiSec(String kullaniciAdi, String kullaniciTipi) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .selectOptionContainingText(kullaniciTipi);
            return this;
        }

        @Step("Onay akışı kullanıcı ekle")
        public BilgilerTab onayAkisiKullaniciEkle(String kullaniciAdi) {
            txtOnayAkisiKullanicilar.selectLov(kullaniciAdi);
            return this;
        }

        @Step("Onay akışı alanının dolduğu görülür kontrolu")
        public BilgilerTab onayAkisiDoluGeldigiKontrolu() {

            Assert.assertEquals(btnOnayAkisGuncelle.isDisplayed(), true);
            Assert.assertEquals(btnOnayAkisSil.isDisplayed(), true);
            return this;
        }

        @Step("Seçilen akışta vekaleti bulunan kişiler bulunmaktadır. Lütfen evrakın akışında kullanılacak kişileri seçiniz.")
        public BilgilerTab vekaletKaydet() {

            if(btnVekaletKaydet.isDisplayed()) {
                btnVekaletKaydet.click(); }
            return this;
        }

        @Step("Onay akışı kullanıcı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisiKullaniciDoldur(String kullanici) {
            cmbKullanici.shouldBe(visible);
            cmbKullanici.selectLov(kullanici);
            return this;
        }


        @Step("Seçilen onay akışı detail kontrolu: \"{onayAkisiDetail}\" ")
        public BilgilerTab onayAkisiDetailKontrol(String onayAkisiDetail) {
            /*System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovDetailText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovDetailText().contains(onayAkisiDetail), true);*/
            cmbOnayAkisi.getSelectedDetails().last().shouldHave(text(onayAkisiDetail));
            return this;
        }

        @Step("Seçilen onay akışı title kontrolu: \"{onayAkisiTitle}\" ")
        public BilgilerTab onayAkisiTitleKontrol(String onayAkisiTitle) {
            /*System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovTitleText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(onayAkisiTitle), true);*/
            cmbOnayAkisi.getSelectedTitles().last().shouldHave(text(onayAkisiTitle));
            return this;
        }

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            if (cmbOnayAkisi.isLovSelected() == true) {
                cmbOnayAkisi.clearAllSelectedItems();
            }

            comboLov(cmbOnayAkisiBy).type(onayAkisi).getTitleItems().filterBy(exactText(onayAkisi)).shouldHaveSize(0);
            comboLov(cmbOnayAkisiBy).closeTreePanel();
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }

        @Step("Onay akışı güncelle")
        public BilgilerTab onayAkisiGuncelle() {
            btnOnayAkisGuncelle.shouldBe(visible);
            clickJs(btnOnayAkisGuncelle);
            return this;
        }

        @Step("Kullaniciya kullanici tipi sec")
        public BilgilerTab kullaniciyaKullaniciTipiSec(String kullanici, String secimTipi) {

            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullanici))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .selectOptionByValue(secimTipi);

            return this;
        }

        @Step("Kullan")
        public BilgilerTab kullan() {
            clickJs(btnKullan);
            return this;
        }


    }

    public class EditorTab extends MainPage {
        private TextEditor editor = new TextEditor();

        public TextEditor getEditor() {
            return editor;
        }

        private EditorTab open() {
            tabEditor.click();
            return this;

        }
    }

}
