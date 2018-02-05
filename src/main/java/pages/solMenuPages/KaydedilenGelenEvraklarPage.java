package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class KaydedilenGelenEvraklarPage extends MainPage {
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_label"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement chkKaydettiklerim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:kaydettiklerimCheckbox"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection tblKaydedilenGelenEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");
    SelenideElement tblIlkRapor = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    ElementsCollection tblKaydedilenGelenEvraklar2 = $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[role='row']");


//    Evrak Detayları Sayfası

    SelenideElement txtEvrakBilgileriListKonuKodu = $("[id$='konuKoduLov:LovText']");
    SelenideElement txtEvrakBilgileriListKonu = $("[id$='konuTextArea']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id$='evrakDili']");

    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSag = $("[id$='evrakSayiTextAreaSag']");
    BelgenetElement cmbEvrakBilgileriListGeldigiKisi = comboLov("[id$='geldigiGercekKisiLov:LovText']");
    SelenideElement cmbEvrakBilgileriListEvrakGelisTipi = $("[id$='evrakGelisTipi']");
    SelenideElement cmbEvrakBilgileriListIvedilik = $("[id$='ivedilik']");
    SelenideElement txtEvrakBilgileriListMiat = $("[id$=miatCalendar_input");
    SelenideElement txtEvrakBilgileriListAciklama = $(By.id("inboxItemInfoForm:evrakBilgileriList:14:j_idt12226"));

    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] > tr[role='row']");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement evrakOnizlemeKontrol = $(By.id("mainPreviewForm:eastLayout"));
    SelenideElement onizlemeHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    BelgenetElement cmbHavaleIslemleriOnaylayacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));
    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement btnHavaleOnayinaGonder = $("[id^='mainPreviewForm:j_idt'] [class^='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only havaleIslemleriGonder']");

    //otomatik havale checkboxı
    SelenideElement otomatikHavaleCheckbox = $("[id='mainPreviewForm:havaleDagitimLovPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    SelenideElement birimKontrol = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement kisiKontrol = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement kullanıcıListeKontrol = $(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement aciklamaKontrol = $(By.id("mainPreviewForm:havaleAciklama"));
    SelenideElement dosyaEkleKontrol = $(By.id("mainPreviewForm:fileUploadHavaleEk_input"));
    SelenideElement islemSureKontrol = $(By.id("mainPreviewForm:islemSuresiTarih_input"));

    BelgenetElement txtHavaleIslemleriKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtEvrakBilgileriAciklama = $(By.id("mainPreviewForm:havaleAciklama"));

    @Step("Kaydedilen gelen evraklar sayfası aç")
    public KaydedilenGelenEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
        return this;
    }

    @Step("Filtrele alanını aç")
    public KaydedilenGelenEvraklarPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public KaydedilenGelenEvraklarPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public KaydedilenGelenEvraklarPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public KaydedilenGelenEvraklarPage tarihDoldur(String tarih) {
        dateTxtTarih.sendKeys(tarih);
        dateTxtTarih.pressEnter();
        return this;
    }

    @Step("Tablodan rapor seç")
    public KaydedilenGelenEvraklarPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Tabloda evrak no kontrolu")
    public KaydedilenGelenEvraklarPage tabloKontrolu(String evrakNo) {
        tblKaydedilenGelenEvraklar2.filterBy(text(evrakNo)).shouldHaveSize(1);
//        System.out.println(row);
//        Assert.assertEquals(row, 1);
        return this;
    }

    @Step("Tabloda ilk evrak içerik tıklama")
    public KaydedilenGelenEvraklarPage tabloRaporIcerik(String evrakNo) {
        tblIlkRapor.click();
        return this;
    }

    @Step("Guncelleme Kontrolleri")
    public KaydedilenGelenEvraklarPage guncellenenAlanKontrolleri(String evrakTarihi, String evrakTuru, String gizlilikDerecesi) {
        String txtEvrakTarihi = dateTxtEvrakBilgileriListEvrakTarihi.getValue();
        String txtEvrakTuru = cmbEvrakBilgileriListEvrakTuru.getSelectedText();
        String txtGizlilikDerecesi = cmbEvrakBilgileriListGizlilikDerecesi.getSelectedText();

        Assert.assertEquals(txtEvrakTarihi, evrakTarihi);
        Assert.assertEquals(txtEvrakTuru, evrakTuru);
        Assert.assertEquals(txtGizlilikDerecesi, gizlilikDerecesi);

        return this;
    }

    @Step("Tabloda evrak noya göre İçerik tıklama : \"{evrakNo}\" ")
    public KaydedilenGelenEvraklarPage tabloEvrakNoileIcerikSec(String evrakNo) throws InterruptedException {

        Thread.sleep(2000);
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .$("[id$='detayGosterButton']").click();
        return this;
    }

    @Step("Tabloda konuya göre evrak kontrolu : {evrakNo}")
    public KaydedilenGelenEvraklarPage tabloEvrakNoileEvrakKontrolu(String evrakNo) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Evrak geçmişi alanına tıklanır")
    public KaydedilenGelenEvraklarPage secilenEvrakEvrakGecmisi() {
        $$("[id$='evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
        return this;
    }

    @Step("Evrak Onizleme Kontrolu")
    public KaydedilenGelenEvraklarPage evrakOnizlemeKontrol() {
        if(evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
        return this;
    }

    @Step("Onizleme Evrak Havale Yap Butonu Tıklandı")
    public KaydedilenGelenEvraklarPage onizlemeHavaleYap() {
        onizlemeHavaleYap.click();
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public KaydedilenGelenEvraklarPage dagitimBilgileriOnaylayanWithDetails(String onaylayan, String details) {
        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan, details);
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public KaydedilenGelenEvraklarPage dagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
        cmbHavaleIslemleriBirim.type(birim).getDetailItems()
                .filterBy(Condition.exactText(details)).first().click();
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }

    @Step("Havale Onayına Gönder")
    public KaydedilenGelenEvraklarPage havaleOnayinaGonder() {
        btnHavaleOnayinaGonder.click();
        return this;
    }



    @Step("Tabloda konuya göre evrak kontrolu : {konu}")
    public KaydedilenGelenEvraklarPage tabloKonuyaGoreEvrakKontrolu(String konu) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(konu))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Evrak Geçmişi Kontrol")
    public KaydedilenGelenEvraklarPage evrakGecmisi(String teslimAlinan, String islemSureci) {
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text(islemSureci)).filter(Condition.text(teslimAlinan)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Tabloda konuya göre evrak İcerik tıklama : {konu}")
    public KaydedilenGelenEvraklarPage tabloKonuyaGoreIcerikSec(String konu) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .$("[id$='detayGosterButton']").click();
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public KaydedilenGelenEvraklarPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Havale İşlemleri Alanındaki Kontroller")
    public KaydedilenGelenEvraklarPage havaleAlanKontrolleri() {
        String text = "";
        if(otomatikHavaleCheckbox.isDisplayed()) {
            text += "Otomatik Havale Checkbox,";
        }
        if(birimKontrol.isDisplayed()) {
            text += "Birim Kontrol,";
        }
        if(kisiKontrol.isDisplayed()) {
            text += "Kisi Kontrol, ";
        }
        if(kullanıcıListeKontrol.isDisplayed()) {
            text += "Kullanıcı Liste,";
        }
        if(aciklamaKontrol.isDisplayed()) {
            text += "Aciklama,";
        }
        if(dosyaEkleKontrol.isDisplayed()) {
            text += "Dosya Ekle,";
        }
        if(islemSureKontrol.isDisplayed()) {
            text += "İslem Sure alanları gösterilmektedir.";
        }
        Allure.addAttachment("Alan Kontrolleri : ", text);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public KaydedilenGelenEvraklarPage havaleIslemleriKisiDoldur(String kisi) {
        txtHavaleIslemleriKisi.selectLov(kisi);
        return this;
    }

    @Step("Havale İşlemleri Açıklama Alanını Doldur")
    public KaydedilenGelenEvraklarPage aciklamaAlaniDoldur(String aciklama) {
        txtEvrakBilgileriAciklama.sendKeys(aciklama);
        return this;
    }
}
