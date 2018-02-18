package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class HavaleEttiklerimPage extends MainPage {

    //SelenideElement pageTitle = $(By.cssSelector("#window1Dialog .ui-dialog-title"));
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt3011_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement btnKurumDisindanGelenBelge = $(By.id("mainInboxForm:inboxDataTable:0:j_idt765"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnHavaleYap = $("[class='ui-button-icon-left ui-icon havaleEt']");
    BelgenetElement txtHavaleYapKisi = comboLov("[id$='dagitimBilgileriKullaniciLov:LovText']");
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtIcerikGosterHavaleYapKullaniciListesi = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtIcerikGosterHavaleYapOnaylayacakKisi = comboLov(By.id("inboxItemInfoForm:onaylayacakKisiLov:LovText"));
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    @Step("Havale Ettiklerim sayfası aç")
    public HavaleEttiklerimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.HavaleEttiklerim);
        return this;
    }

    @Step("{evrakNo} adlı evrakın içerik göster tıklanır")
    public HavaleEttiklerimPage evrakNoIleEvrakIcerikGoster(String evrakNo){
        tblEvraklar.filterBy(Condition.text(evrakNo)).first().$("[id$='detayGosterButton']").click();
        return this;
    }

    @Step("{evrakNo} adlı evrak tıklanır")
    public HavaleEttiklerimPage evrakNoIleEvrakSec(String evrakNo){
        tblEvraklar.filterBy(Condition.text(evrakNo)).first().click();
        return this;
    }

    @Step("Evrak Geçmişi tıklanır")
    public HavaleEttiklerimPage evrakGecmisiSec(){
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).first().click();
        return this;
    }

    @Step("")
    public HavaleEttiklerimPage evrakGecmisiKisiVeMesajKontrol(String mesaj, String kisi){
        $$("[id$='hareketGecmisiDataTable_data'] > tr").filterBy(Condition.text(mesaj))
                .filterBy(Condition.text(kisi)).first().shouldHave(visible);
        return this;
    }

    @Step("Kisi doldur")
    public HavaleEttiklerimPage havaleYapKisiDoldur(String kisi) {
        txtHavaleYapKisi.type(kisi).getDetailItems().filterBy(Condition.text("BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR")).first().click();
        return this;
    }

    @Step("Kişi listesinde \"{kisi}\" seçmeye dene")
    public HavaleEttiklerimPage havaleYapKisiSecmeyeDene(String kisi) {
        txtHavaleYapKisi.type(kisi).getTitleItems().filterBy(Condition.text(kisi)).first().click();
        return this;
    }


    @Step("Kullanıcı listesi doldur")
    public HavaleEttiklerimPage havaleYapKullaniciListesiDoldur(String kullaniciListesi) {
//        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı listesinde \"{kisi}\" seçmeye dene")
    public HavaleEttiklerimPage havaleYapKullaniciyiSecmeyeDene(String kisi) {
        txtHavaleYapKullaniciListesi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }


    @Step("Filtrele alanını aç")
    public HavaleEttiklerimPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public HavaleEttiklerimPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public HavaleEttiklerimPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public HavaleEttiklerimPage baslangicTarihiDoldur(String tarih) {
        dateTxtBaslangicTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Bitiş Tarihi doldur")
    public HavaleEttiklerimPage bitisTarihiDoldur(String tarih) {
        dateTxtBitisTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public HavaleEttiklerimPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public HavaleEttiklerimPage gizlilikRaporSec(String konu, String yer, String tarih) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Havale Yap")
    public HavaleEttiklerimPage havaleYap() {
        btnHavaleYap.click();
        return this;
    }

    @Step("Kullanıcı Listesi alanını doldur: {kullaniciListesi}")
    public HavaleEttiklerimPage icerikGosterHavaleYapKullaniciListesiDoldur(String kullaniciListesi){
        txtIcerikGosterHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Onaylayacak Kişi alanında {onaylayacakKisi} seçilir")
    public HavaleEttiklerimPage icerikGosterHavaleYapOnaylayacakKisiDoldur(String onaylayacakKisi, String birim){
        txtIcerikGosterHavaleYapOnaylayacakKisi.openTreePanel().closeTreePanel();
        txtIcerikGosterHavaleYapOnaylayacakKisi.selectLov(onaylayacakKisi,birim);
        return this;
    }

    @Step("Havale Onayına Gönder")
    public HavaleEttiklerimPage icerikGosterHavaleYapHavaleOnayinaGonder(){
        $$("[id='inboxItemInfoForm'] button").filterBy(Condition.text("Havale Onayına Gönder")).first().click();
        return this;
    }
    
    @Step("Kullanıcı Listesi alanınında seçileni Gereği ifadesini bilgi için gönder olarak değiştir")
    public HavaleEttiklerimPage icerikGosterHavaleyapKullaniciListesiGeregiIcınBilgiIcinDegistir(){
        $("[id='inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovSecilenTable_data'] select").selectOption("BİLGİ İÇİN GÖNDER");
        return this;
    }

}
