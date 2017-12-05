package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KararYazisiOlusturPage extends MainPage {

    //region Tabs local variables
    private BilgilerTab bilgilerTab = new BilgilerTab();
    //endregion

    //region Elements
    SelenideElement tabBilgiler = $("button[id^='yeniKararEvrakForm:kararEvrakLeftTab:uiRepeat'] span[class$='kullaniciBilgileri']");
    //endregion

    @Step("Karar yazısı oluştur sayfası aç")
    public KararYazisiOlusturPage openPage(){
        ustMenu("Karar Yazısı Oluştur");
        return this;
    }

    //region Tabs
    @Step("Bilgiler tab aç")
    public BilgilerTab bilgilerTabiAc() {
        return bilgilerTab.open();
    }


    public class BilgilerTab extends MainPage {

        SelenideElement divContainer = $("#evrakBilgileriContainerDiv");

        //Bilgiler tab
        SelenideElement btnKaydetveOnaySun = $(By.id("yeniKararEvrakForm:kararEvrakRightTab:uiRepeat:2:cmdbutton"));
        BelgenetElement txtKonuKodu = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:0:konuKoduLov:LovText"));
        SelenideElement txtKonu = $(By.id("yeniKararEvrakForm:evrakBilgileriList:2:konuTextArea"));
        SelenideElement cmbIvedilik = $(By.id("yeniKararEvrakForm:evrakBilgileriList:4:ivedilik"));
        SelenideElement dateMiat = $(By.id("yeniKararEvrakForm:evrakBilgileriList:5:miatCalendar_input"));
        BelgenetElement txtKaldirilacakKlasorler = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:7:eklenecekKlasorlerLov:LovText"));
        SelenideElement txtToplantiNo = $(By.id("yeniKararEvrakForm:evrakBilgileriList:8:toplantiNo"));
        SelenideElement dateToplantiTarihi = $(By.id("yeniKararEvrakForm:evrakBilgileriList:9:toplantiTarih_input"));
        SelenideElement txtKararNo = $(By.id("yeniKararEvrakForm:evrakBilgileriList:10:kararNo"));
        SelenideElement btnOnayAkisiEkle = $(By.id("yeniKararEvrakForm:evrakBilgileriList:6:onayAkisiEkle"));
        SelenideElement btnKullanicilarKullan = $(By.id("yeniKararEvrakForm:evrakBilgileriList:6:anlikAkisKullanButton"));
        BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniKararEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniKararEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
        SelenideElement cmbSelectOneMenu = $(By.id("yeniKararEvrakForm:evrakBilgileriList:14:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement btnEkranKapat = $(By.cssSelector("[id='window4Dialog'] span[class='ui-icon ui-icon-closethick']"));

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


        @Step("Kaydet ve onay sun")
        public BilgilerTab kaydetveOnaySun() {
            btnKaydetveOnaySun.click();
            return this;
        }

        @Step("Kullan")
        public BilgilerTab kullan() {
            btnKullanicilarKullan.click();
            return this;
        }

        @Step("Karar no doldur")
        public BilgilerTab kararNoDoldur(String kararNo) {
            txtKararNo.setValue(kararNo);
            return this;
        }

        @Step("Toplantı tarih seç")
        public BilgilerTab toplantiTarihDoldur(String tarih) {
            dateToplantiTarihi.setValue(tarih);
            return this;
        }

        @Step("Toplanti no doldur")
        public BilgilerTab toplantiNoDoldur(String toplantiNo) {
            txtToplantiNo.setValue(toplantiNo);
            return this;
        }

        @Step("Kaldirilacak klasörler doldur")
        public BilgilerTab kaldirilacakKlasorlerDoldur(String kaldirilacakKlasorler) {
            txtKaldirilacakKlasorler.selectLov(kaldirilacakKlasorler);
            return this;
        }

        @Step("Onay akışı doldurma ve kontrolu")
        public BilgilerTab onayAkisiDoldur(String onayAkisi) {
            cmbOnayAkisi.selectLov(onayAkisi);
            return this;
        }

        @Step("Miat Doldur")
        public BilgilerTab miatDoldur(String miat) {
            dateMiat.setValue(miat);
            return this;
        }

        @Step("İvedilik seç")
        public BilgilerTab ivedilikSec(String secilen) {
            cmbIvedilik.selectOption(secilen);
            return this;
        }

        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduDoldur(String konuKodu) {
            txtKonuKodu.selectLov(konuKodu);
            return this;
        }

        @Step("Konu doldur")
        public BilgilerTab konuDoldur(String konuKodu) {
            txtKonu.setValue(konuKodu);
            return this;
        }

        @Step("Onay akışı ekle")
        public BilgilerTab onayAkisiEkle() {
            btnOnayAkisiEkle.click();
            return this;
        }

        @Step("Ekrani kapat")
        public BilgilerTab ekraniKapat() {
            btnEkranKapat.click();
            return this;
        }

        @Step("Parafçı, Kontrolcü, Koordineci ve İmzacı combo kontrolu")
        public BilgilerTab onayAkisiKullaniciComboKontrol() {

            onayAkisiEkle();

            if (cmbSelectOneMenu.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Allure.addAttachment("Parafçı, Kontrolcü, Koordineci ve İmzacı combo gelmedi.", "");
                log.info("Parafçı, Kontrolcü, Koordineci ve İmzacı combo gelmedi.");
                Assert.assertTrue(false);
            }
            return this;
        }

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            boolean selectable = comboLov(cmbOnayAkisiBy).isLovValueSelectable(onayAkisi);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }

    }
}
