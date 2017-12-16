package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GelenEvraklarPage extends MainPage {

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    ElementsCollection tableEvraklar2 = $$("tbody[id'vekaletVerForm:vekaletLayout:devredileceklerTabView:vekaletDataTable_data']>tr");
    SelenideElement tblEvraklar = $("table[id='mainInboxForm:inboxDataTable:0:evrakTable'] tr:nth-child(3)");
    SelenideElement cmbFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement btnGidecegiYerSeciniz = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:geldigiYerFilterOpenDialogButton"));
    SelenideElement treeGidecegiYer = $(By.id("inboxFiltreDialogForm:geldigiYerFilterLovId:LovText"));
    SelenideElement dateTxtBaslangicTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement btnTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt651_button"));
    SelenideElement btnTopluHavale = $(By.id("mainInboxForm:inboxDataTable:j_idt664"));
    SelenideElement btnTopluKlasorKaldir = $(By.id("mainInboxForm:inboxDataTable:j_idt665"));
    SelenideElement btnRaporAl = $(By.id("mainInboxForm:inboxDataTable:j_idt682"));
    SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));
    SelenideElement btnTabHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement popUpUyari = $(By.xpath("//body[@class='ui-layout-container']/div[104]//p"));
    SelenideElement popUpUyariEvet = $(By.xpath("//body[@class='ui-layout-container']/div[104]//center/button[1]/span[@class='ui-button-text']"));
    //Havale Yap Alt Yapı
    SelenideElement btnHavaleYap = $("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td:nth-child(5) button");
    SelenideElement treeHavaleYapBirim = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovTexts"));
    BelgenetElement txtComboLovKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement treeHavaleYapKisi = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement treeHavaleYapKullaniciListesi = comboLov (By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement treeHavaleYapOnaylanacakKisi = comboLov("[id^='mainPreviewForm:onaylayacakKisiLov:LovText']");
    SelenideElement txtHavaleYapAciklama = $(By.id("mainPreviewForm:havaleAciklama"));
    SelenideElement btnHavaleYapDosyaEkle = $(By.id("mainPreviewForm:fileUploadHavaleEk_input"));
    SelenideElement txtHavaleYapIslemSuresi = $(By.id("mainPreviewForm:islemSuresiTarih_input"));
    SelenideElement chkHavaleYapEvrakOnayliKapat = $(By.id("mainPreviewForm:j_idt30591_input"));
    SelenideElement btnHavaleYapGonder = $("[id^='mainPreviewForm:j_idt'] [class$='havaleGonderButonClass']");
    SelenideElement btnHavaleYapHavaleOnayinaGonder = $(By.id("mainPreviewForm:j_idt30599"));
    ElementsCollection tblVekaletVerenAlan =$$("[id='mainPreviewForm:kullaniciBirimSecenekleriHavaleIcin_data'] tr[role='row']");
    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));

    // Tebiğ Et Buttonu altı div
    SelenideElement btnTebligEt = $(By.xpath("//span[contains(@class, 'tebligEt')]/.."));
    BelgenetElement txtTebligEtKisi = comboLov(By.id("mainPreviewForm:kullaniciLov_id:LovText"));
    BelgenetElement txtTebligEtKullaniciListesi = comboLov(By.id("mainPreviewForm:kullaniciGrubuLov_id:LovText"));
    SelenideElement txtTebligEtNot = $(By.id("mainPreviewForm:tebligNotu_id"));
    SelenideElement btnTebligEtTebligEt = $(By.id("mainPreviewForm:tebligEtButton_id"));

    // İade Et Buttonu altı div
    SelenideElement btnIadeEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton"));
    SelenideElement txtIadeEtNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement btnIadeEtDosyaEkle = $(By.id("mainPreviewForm:fileUploadIadeEk_input"));
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));
    // Cevap Yaz Buttonu
    SelenideElement btnCevapYaz = $("button[id^='mainPreviewForm:onizlemeRightTab:uiRepeat'] span[class$='cevapYaz']");

    //Evrak Kapat Buttonu div
    SelenideElement btnEvrakKapat = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:8:cmdbutton"));
    BelgenetElement txtEvrakKapatKonuKodu = comboLov(By.id("mainPreviewForm:konuKoduLov:LovText"));
    SelenideElement cmbEvrakKapatKapatmaTipi = $(By.id("mainPreviewForm:kapatmaTipiOneMenu_id"));
    BelgenetElement txtEvrakKapatKaldirilacakKlasorler = comboLov(By.id("mainPreviewForm:klasorLov_id:LovText"));
    SelenideElement txtEvrakKapatNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement txtEvrakKapatOnayAkisi = $(By.id("mainPreviewForm:akisLov_id:LovText"));
    SelenideElement btnEvrakKapatKapatmaOnayinaSun = $(By.id("mainPreviewForm:kapatmaOnayinaSunButtonDirektId"));
    SelenideElement btnEvrakKapatEvrakKapat = $(By.id("mainPreviewForm:j_idt17934"));
    SelenideElement chkEvrakKapatKisiselKlasorler = $(By.id("mainPreviewForm:kisiselKlasorlerimiGetirCheckboxId_input"));


    //Paylaş Button altı div
    SelenideElement btnPaylas = $(By.xpath("//button/span[contains(@class, 'evrakPaylas')]"));
    SelenideElement txtPaylasKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    BelgenetElement txtPaylasilanKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasanAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylasIcPaylas = $(By.id("mainPreviewForm:paylasButtonId"));

    SelenideElement evrakSec = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));

    BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='windowCevapEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
    BelgenetElement txtTakipListesiKullanicilar = comboLov(By.id("evrakTakibimeEkleDialogForm:takipListLov:LovText"));
    SelenideElement btnTakipListesiKapat =$("[id^='evrakTakibimeEkleDialogForm:takipDialog'] span[class='ui-icon ui-icon-closethick']");

    public GelenEvraklarPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.GelenEvraklar);
        return this;
    }

    @Step("Tablodan rapor seç")
    public GelenEvraklarPage gizlilikRaporSecTakibeEkle(String konu, String yer, String tarih, String no) {
        tblEvrak.filterBy(Condition.text(konu)).filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no)).get(0)
                .$$("button[id^='mainInboxForm:inboxDataTable:']").get(0).click();
        return this;
    }

    @Step("Kullancılar doldur")
    public GelenEvraklarPage takipListesiKullanicilarDoldur(String kullanicilar){
        txtTakipListesiKullanicilar.selectLov(kullanicilar);
        return this;
    }
    public GelenEvraklarPage takipListeKapat(){
        btnTakipListesiKapat.click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public GelenEvraklarPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        tblEvrak.filterBy(Condition.text(konu)).filterBy(Condition.text(yer)).filterBy(Condition.text(tarih)).filterBy(Condition.text(no)).get(0).click();
        return this;
    }

    @Step("Kisi doldur")
    public GelenEvraklarPage havaleYapKisiDoldur(String kisi){
        txtHavaleYapKisi.selectLov(kisi);
        return this;
    }

    @Step("Kullanıcı listesi doldur")
    public GelenEvraklarPage havaleYapKullaniciListesiDoldur(String kullaniciListesi){
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Evrak seç")
    public GelenEvraklarPage evrakSec() {
        evrakSec.click();
        return this;
    }

    public GelenEvraklarPage evrakSec(String konu, String geldigiYer, String kayitTarihiSayi, String evrakTarihi, String no) {
        tableEvraklar
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Geldiği Yer: " + geldigiYer))
                .filterBy(Condition.text("Kayıt Tarihi / Sayı: " + kayitTarihiSayi))
                .filterBy(Condition.text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(Condition.text("No: " + no))
                .get(0)
                .click();
        return this;
    }

    @Step("Havale yap")
    public GelenEvraklarPage tabHavaleYap() {
        btnTabHavaleYap.click();
        return this;
    }

    public GelenEvraklarPage iadeEtIadeEt() {
        btnIadeEtIadeEt.click();
        return this;
    }

    public GelenEvraklarPage iadeEtDosyaEkle() {
        btnIadeEtDosyaEkle.click();
        return this;
    }

    public GelenEvraklarPage iadeEtNotInputDoldur(String text) {
        txtIadeEtNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtTebligEt() {
        btnTebligEtTebligEt.click();
        return this;
    }

    public GelenEvraklarPage tebligEtNotInputDoldur(String text) {
        txtTebligEtNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtKullaniciListesiDoldur(String kullaniciListesi) {
        txtTebligEtKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    public GelenEvraklarPage tebligEtKisiInputDoldur(String kisi) {
        txtTebligEtKisi.selectLov(kisi);
        return this;
    }

    public GelenEvraklarPage havaleYapHavaleOnayinaGonder() {
        btnHavaleYapHavaleOnayinaGonder.click();
        return this;
    }

    public GelenEvraklarPage havaleYapGonder() {
        btnHavaleYapGonder.click();
        return this;
    }

    public GelenEvraklarPage havaleYapEvrakOnayliKapatChecked(Boolean secim) {
        chkHavaleYapEvrakOnayliKapat.setSelected(secim);
        return this;
    }

    public GelenEvraklarPage havaleYapIslemSuresiDoldur(String text) {
        txtHavaleYapIslemSuresi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapDosyaEkle() {
        btnHavaleYapDosyaEkle.click();
        return this;
    }

    public GelenEvraklarPage havaleYapAciklamaDoldur(String text) {
        txtHavaleYapAciklama.sendKeys(text);
        return this;
    }

    @Step("Havale onaylanacak kisi doldur")
    public GelenEvraklarPage havaleYapOnaylanacakKisiTreeDoldur(String text) {
        treeHavaleYapOnaylanacakKisi.selectLov(text);
        System.out.println("Başarı Bu selectlı geçmiştir");
        return this;
    }

    public GelenEvraklarPage havaleYapKullaniciListesiTreeDoldur(String text) {
        treeHavaleYapKullaniciListesi.selectLov(text);
        return this;
    }

    public GelenEvraklarPage havaleYapKisiTreeDoldur(String text) {
        treeHavaleYapKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapKisiTreeSec(String kisi) {
        txtComboLovKisi.selectLov(kisi);
        return this;
    }

    public GelenEvraklarPage havaleYapBirimTreeDoldur(String text) {
        treeHavaleYapBirim.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasIcPaylas() {
        btnPaylasIcPaylas.click();
        return this;
    }

    public GelenEvraklarPage paylasanAciklamaDoldur(String text) {
        txtPaylasanAciklama.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasKisiDoldur(String text) {
        txtPaylasKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasKisiSec(String kisi) {
        txtPaylasilanKisi.selectLov(kisi);
        return this;
    }

    public GelenEvraklarPage filtreleSec(String value) {
        cmbFiltrele.selectOption(value);
        return this;
    }

    public GelenEvraklarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage gidecegiYerSecinizk() {
        btnGidecegiYerSeciniz.click();
        return this;
    }

    public GelenEvraklarPage gidecegiYerTreeDoldur(String text) {
        treeGidecegiYer.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage baslangicTarihDoldur(String text) {
        dateTxtBaslangicTarih.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage bitisTarihDoldur(String text) {
        dateTxtBitisTarihi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage topluSecim() {
        btnTopluSecim.click();
        return this;
    }

    public GelenEvraklarPage topluHavale() {
        btnTopluHavale.click();
        return this;
    }

    public GelenEvraklarPage topluKlasorKaldir() {
        btnTopluKlasorKaldir.click();
        return this;
    }

    public GelenEvraklarPage raporAl() {
        btnRaporAl.click();
        return this;
    }

    public GelenEvraklarPage evrakGoster() {
        btnEvrakGoster.click();
        return this;
    }

    public GelenEvraklarPage havaleYap() {
        btnHavaleYap.click();
        return this;
    }

    public GelenEvraklarPage tebligEt() {
        btnTebligEt.click();
        return this;
    }

    public GelenEvraklarPage iadeEt() {
        btnIadeEt.click();
        return this;
    }

    public GelenEvraklarPage cevapYaz() {
        btnCevapYaz.click();
        return this;
    }

    public GelenEvraklarPage evrakKapat() {
        btnEvrakKapat.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatKisiselKlasorlerSec(boolean secilen) {
        chkEvrakKapatKisiselKlasorler.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatEvrakKapat() {
        btnEvrakKapatEvrakKapat.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatKapatmaOnayinaSun() {
        btnEvrakKapatKapatmaOnayinaSun.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(String text) {
        txtEvrakKapatOnayAkisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(Boolean secilen) {
        txtEvrakKapatOnayAkisi.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatNotDoldur(String text) {
        txtEvrakKapatNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatKaldirilacakKlasorlerDoldur(String text) {
        txtEvrakKapatKaldirilacakKlasorler.selectLov(text);
        return this;
    }

    @Step("Evrak Kapat konu kodu doldur")
    public GelenEvraklarPage evrakKapatKonuKodu(String konuKodu) {
        txtEvrakKapatKonuKodu.selectLov(konuKodu);
        return this;
    }

    public GelenEvraklarPage evrakKapatKapatmaTipiSec(String value) {
        cmbEvrakKapatKapatmaTipi.selectOption(value);
        return this;
    }

    public GelenEvraklarPage paylas() {
        btnPaylas.click();
        return this;
    }

    @Step("Tablodan istenilen sayıda evrak no al")
    public String[] tablodanEvrakNoAl(int adet) {
        String text = "";
        SelenideElement tblEvraklar = $("table[id='mainInboxForm:inboxDataTable:" + 0 + ":evrakTable'] tr:nth-child(3)");
        String[] evrakNo = new String[adet];
        for (int i = 0; i < adet; i++) {

            text = $("table[id='mainInboxForm:inboxDataTable:" + i + ":evrakTable'] tr:nth-child(3)").getText();
            text = text.split("/")[2];
            String number = getIntegerInText(text);
            evrakNo[i] = number;
        }
//        String text = tblEvraklar.getText();
        System.out.println(text);

        return evrakNo;
    }

    @Step("Tabloda evrak no kontrolü")
    public GelenEvraklarPage tabloEvrakNoKontrol(String evrakNo) {
        int size = tableEvraklar
                .filterBy(Condition.text(evrakNo)).size();
        Assert.assertEquals(size, 1);

        return this;
    }

    @Step("Tabloda olmayan evrak no kontrolü")
    public GelenEvraklarPage tabloOlmayanEvrakNoKontrol(String evrakNo) {
        int size = tableEvraklar
                .filterBy(Condition.text(evrakNo)).size();
        Assert.assertEquals(size, 0);

        return this;
    }

    //Cevap yaz sayfası
    @Step("Seçilen onay akışı detail kontrolu: \"{secim}\" ")
    public GelenEvraklarPage onayAkisiDetailKontrol(String secim) {
        System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovDetailText());
        Assert.assertEquals(cmbOnayAkisi.lastSelectedLovDetailText().contains(secim), true);
        return this;
    }

    //Cevap yaz sayfası
    @Step("Seçilen onay akışı title kontrolu: \"{secim}\" ")
    public GelenEvraklarPage onayAkisiTitleKontrol(String secim) {
        System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovTitleText());
        Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(secim), true);
        return this;
    }

    @Step("Vekalet alan Ve Veren tablo vekalet alan seç")
    public GelenEvraklarPage vekeletAlanVerenTabloVekaletAlanveyaVerenSec(String isim) {
        tblVekaletVerenAlan
                .filterBy(Condition.text(isim)).first()
                .$("[id='mainPreviewForm:kullaniciBirimSecenekleriHavaleIcin_data'] td:nth-child(4) button").click();
        return this;
    }
    @Step("Vekalet var uyarısı ")
    public GelenEvraklarPage evrakOnIzlemeUyarıPopUpKontol() {
       if(popUpUyari.isDisplayed())
           popUpUyariEvet.click();
        return this;
    }
}
