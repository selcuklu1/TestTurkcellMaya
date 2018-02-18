package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class TeslimAlinanlarPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt10493_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtHavaleYapOnaylanacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));
    BelgenetElement txtHavaleYapBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] > tr[role='row']");

    @Step("Teslim Alınanlar sayfası aç")
    public TeslimAlinanlarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TeslimAlinanlar);
        return this;
    }

    @Step("Evrakın listelendiği görülür.")
    public TeslimAlinanlarPage evrakGeldigiGorme(String konu, String yer, String tarih) {
        boolean durum = tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinanlarPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Evrak seçilir")
    public TeslimAlinanlarPage evrakSec(String konu, String yer, String tarih, String no) {
        tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no)).get(0).click();
        return this;
    }

    @Step("Filtrele alanını aç")
    public TeslimAlinanlarPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public TeslimAlinanlarPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public TeslimAlinanlarPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Tarihi doldur")
    public TeslimAlinanlarPage tarihiDoldur(String tarih, Keys... keys) {
        dateTxtTarih.sendKeys(tarih);
        for (Keys k : keys) {
            dateTxtTarih.sendKeys(keys);
        }
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinanlarPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Kisi doldur")
    public TeslimAlinanlarPage havaleYapKisiDoldur(String kisi) {
        txtHavaleYapKisi.selectLov(kisi);
        txtHavaleYapKisi.selectLov(kisi);
        txtHavaleYapKisi.selectLov(kisi);
        return this;
    }

    @Step("Kisi doldur")
    public TeslimAlinanlarPage havaleYapKisiDoldur(String kisi, String birim) {
        txtHavaleYapKisi.sendKeys(kisi);
        txtHavaleYapKisi.pressEnter();
        $$("[id='mainPreviewForm:dagitimBilgileriKullaniciLov:lovTree'] li").filterBy(Condition.text(kisi)).filterBy(Condition.text(birim)).first().doubleClick();
        return this;
    }

    @Step("Kisi alanında \"{kisi}\" seçmeye dene")
    public TeslimAlinanlarPage havaleYapKisiSecmeyeDene(String kisi) {
        txtHavaleYapKisi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Kullanıcı listesi doldur")
    public TeslimAlinanlarPage havaleYapKullaniciListesiDoldur(String kullaniciListesi) {
        //txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı listesi seçmeye dene")
    public TeslimAlinanlarPage havaleYapKullaniciListesiSecmeyeDene(String kullaniciListesi) {
        txtHavaleYapKullaniciListesi.type(kullaniciListesi).getTitleItems().filterBy(text(kullaniciListesi)).first().click();
        return this;
    }

    @Step("Onaylanacak Kişi seçilir {onaylanacakKisi} | {birim}")
    public TeslimAlinanlarPage havaleYapOnaylanacakKisiDoldur(String onaylanacakKisi, String birim) {
        txtHavaleYapOnaylanacakKisi.selectLov(onaylanacakKisi, birim);
        return this;
    }

    @Step("Kişi listesinde seçilenin üzerindeki kalem ikonunu tıklanır")
    public TeslimAlinanlarPage kisiListesiSecilenGuncelle() {
        $("[id='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovSecilenTable_data'] [class='ui-button-icon-left ui-icon update-icon']").click();
        return this;
    }

    @Step("Kullanıcı grup detay pop upının açıldığı, kullanıcı listesinde kayıtlı kullanıcıların listelendiği ve her kayıt yanındaki checkboxın işaretli olduğu görülür.")
    public TeslimAlinanlarPage kisiListesiSecilenGrupDetaySeciliGeldigiGorme() {
        boolean durum = $$("[id='mainPreviewForm:kullaniciGrubuDetay_data'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']").size() > 0;
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Kullanıcılardan birinin işaretini kaldır")
    public TeslimAlinanlarPage grupDetayKullaniciIsaretKaldir() {
        $$("[id='mainPreviewForm:kullaniciGrubuDetay_data'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']").last().click();
        return this;
    }

    @Step("Kullan butonunu tıkla")
    public TeslimAlinanlarPage kullaniciGrupDetayKullan() {
        $$("[id='mainPreviewForm:kullaniciGrubuDetayViewDialog'] button").filterBy(Condition.text("Kullan")).first().click();
        return this;
    }

    @Step("Birim alanında {birim} adlı birim seçilir")
    public TeslimAlinanlarPage havaleYapBirimDoldur(String birim) {
        txtHavaleYapBirim.openTreePanel().closeTreePanel();
        txtHavaleYapBirim.selectLov(birim);
        //txtHavaleYapBirim.type(birim).getTitleItems().filterBy(text(birim)).first().click();
        return this;
    }


    @Step("Gönder tıklanır")
    public TeslimAlinanlarPage havaleYapGonder() {
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] button").filterBy(Condition.text("Gönder")).first().click();
        return this;
    }

    @Step("Havale Onayına Gönder tıklanır")
    public TeslimAlinanlarPage havaleYaphavaleOnayinaGonder() {
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] button").filterBy(Condition.text("Havale Onayına Gönder")).first().click();
        return this;
    }

    @Step("Havale yap butonana bas")
    public TeslimAlinanlarPage havaleYap() {
        btnHavaleYap.click();
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public TeslimAlinanlarPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak adedi kontrol: \"{evrakNo}\" ")
    public TeslimAlinanlarPage evrakAdediKontrol(String evrakNo) {
        int dosyaAdedi = tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .size();
        Allure.addAttachment(evrakNo, Integer.valueOf(dosyaAdedi).toString());
        return this;
    }

    @Step("Evrak geçmişi alanına tıklanır")
    public TeslimAlinanlarPage secilenEvrakEvrakGecmisi() {
        $$("[id$='evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
        return this;
    }

    @Step("Evrak Geçmişi Kontrol")
    public TeslimAlinanlarPage evrakGecmisi(String teslimAlinan, String islemSureci) {
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text(islemSureci)).filter(Condition.text(teslimAlinan)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Evrak Geçmişi Kontrol: \"{teslimAlinan}\" \"{islemSureci}\" \"{tarih}\"")
    public TeslimAlinanlarPage evrakGecmisi(String teslimAlinan, String islemSureci, String tarih) {
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text(islemSureci)).filter(Condition.text(teslimAlinan))
                .filterBy(Condition.text(tarih)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }
}
