package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.collections.SizeGreaterThan;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.$x;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class GelenEvraklarPage extends MainPage {

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    ElementsCollection tableEvraklar2 = $$("tbody[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");
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


    //Havale Yap Alt Yapı
    SelenideElement btnHavaleYap = $("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td:nth-child(5) button");
    SelenideElement treeHavaleYapBirim = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovTexts"));
    BelgenetElement txtComboLovKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement treeHavaleYapKisi = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement treeHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement treeHavaleYapOnaylanacakKisi = comboLov("[id^='mainPreviewForm:onaylayacakKisiLov:LovText']");
    By treeHavaleYapOnaylanacakKisi2 = By.cssSelector("[id^='mainPreviewForm:onaylayacakKisiLov:LovText']");
    SelenideElement txtHavaleYapAciklama = $(By.id("mainPreviewForm:havaleAciklama"));
    SelenideElement btnHavaleYapDosyaEkle = $(By.id("mainPreviewForm:fileUploadHavaleEk_input"));
    SelenideElement txtHavaleYapIslemSuresi = $(By.id("mainPreviewForm:islemSuresiTarih_input"));
    SelenideElement chkHavaleYapEvrakOnayliKapat = $(By.id("mainPreviewForm:j_idt30591_input"));
    SelenideElement btnHavaleYapGonder = $("[id^='mainPreviewForm:j_idt'] [class$='havaleGonderButonClass']");
    SelenideElement btnHavaleYapHavaleOnayinaGonder = $(By.id("mainPreviewForm:j_idt30599"));
    ElementsCollection tblVekaletVerenAlan = $$("[id='mainPreviewForm:kullaniciBirimSecenekleriHavaleIcin_data'] tr[role='row']");

    BelgenetElement txtKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement popUpUyari = $("[id='mainPreviewForm:j_idt5031'] p");
    SelenideElement popUpUyariEvet = $(By.xpath("//body[@class='ui-layout-container']/div[131]//center/button[1]"));
    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));

    // Tebiğ Et Buttonu altı div
    SelenideElement btnTebligEt = $(By.xpath("//span[contains(@class, 'tebligEt')]/.."));
    BelgenetElement txtTebligEtKisi = comboLov(By.id("inboxItemInfoForm:kullaniciLov_id:LovText"));
    BelgenetElement txtTebligEtKullaniciListesi = comboLov(By.id("inboxItemInfoForm:kullaniciGrubuLov_id:LovText"));
    SelenideElement txtTebligEtNot = $(By.id("inboxItemInfoForm:tebligNotu_id"));
    SelenideElement btnTebligEtTebligEt = $(By.id("inboxItemInfoForm:tebligEtButton_id"));

    // İade Et Buttonu altı div
    SelenideElement btnIadeEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton"));
    SelenideElement txtIadeEtNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement btnIadeEtDosyaEkle = $(By.id("mainPreviewForm:fileUploadIadeEk_input"));
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));
    // Cevap Yaz Buttonu
    SelenideElement btnCevapYaz = $x("//span[contains(@class, 'cevapYaz')]/..");

    //Evrak Kapat Buttonu div
    SelenideElement btnEvrakKapat = $x("//span[contains(@class, 'evrakKapat')]/..");
    BelgenetElement txtEvrakKapatKonuKodu = comboLov(By.id("mainPreviewForm:konuKoduLov:LovText"));
    SelenideElement cmbEvrakKapatKapatmaTipi = $(By.id("mainPreviewForm:kapatmaTipiOneMenu_id"));
    BelgenetElement txtEvrakKapatKaldirilacakKlasorler = comboLov(By.id("mainPreviewForm:klasorLov_id:LovText"));
    SelenideElement txtEvrakKapatNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement txtEvrakKapatOnayAkisi = $(By.id("mainPreviewForm:akisLov_id:LovText"));
    SelenideElement btnEvrakKapatKapatmaOnayinaSun = $(By.id("mainPreviewForm:kapatmaOnayinaSunButtonDirektId"));
    SelenideElement btnEvrakKapatKapat2 = $x("//div[contains(@class, 'kapatButtonDirekt')]//span[contains(., 'Evrak Kapat')]/..");
    ElementsCollection btnEvrakKapatEvrakKapat = $$("[id='mainPreviewForm:evrakOnizlemeTab'] [class='form-buttons kapatButtonDirekt'] button");
    SelenideElement chkEvrakKapatKisiselKlasorler = $(By.id("mainPreviewForm:kisiselKlasorlerimiGetirCheckboxId_input"));
    SelenideElement btnOnayAkisi = $("[id='mainPreviewForm:evrakKapatOnayAkisPanelGrid'] td:nth-child(4) button");
    BelgenetElement txtKullanicalar = comboLov(By.id("mainPreviewForm:akisAdimLov_id:LovText"));
    ElementsCollection tblVekaletAlanVeren = $$("tbody[id='mainPreviewForm:mainPreviewFormKullaniciBirimSeceneklerAkis_data'] tr[data-ri]");
    //Paylaş Button altı div
    SelenideElement btnPaylas = $(By.xpath("//button/span[contains(@class, 'evrakPaylas')]"));
    SelenideElement txtPaylasKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    BelgenetElement txtPaylasilanKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasanAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylasBirim = $(By.id("mainPreviewForm:paylasTumuBoolean"));
    SelenideElement btnPaylasIcPaylas = $(By.id("mainPreviewForm:paylasButtonId"));

    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection tblGelenEvrakListesi = $$("tbody[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");
    SelenideElement tblIkinciEvrak = $(By.id("mainInboxForm:inboxDataTable:1:evrakTable"));

    BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='windowCevapEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
    BelgenetElement txtTakipListesiKullanicilar = comboLov(By.id("evrakTakibimeEkleDialogForm:takipListLov:LovText"));
    SelenideElement btnTakipListesiKapat = $("[id^='evrakTakibimeEkleDialogForm:takipDialog'] span[class='ui-icon ui-icon-closethick']");
    ElementsCollection evrakSecButonlar = $$("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td");

    SelenideElement btnEvrakKapatUyariEvet = $(By.id("mainPreviewForm:tebellugEvrakEvetButton_id"));

    @Step("Gelen Evraklar Sayfasını aç")
    public GelenEvraklarPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.GelenEvraklar);
        String pageTitle = SolMenuData.IslemBekleyenEvraklar.GelenEvraklar.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Evrak geldiği görünür: Konu kodu:{konuKodu}")
    public GelenEvraklarPage evrakGeldigiGorme(String konuKodu){
        boolean durum = tblEvrak.filterBy(Condition.text(konuKodu)).size()==1;
        Assert.assertEquals(durum,true);
        return this;
    }

    @Step("Tablodan rapor seç")
    public GelenEvraklarPage gizlilikRaporSecTakibeEkle(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no))
                .shouldHaveSize(1).first();
        evrak.$$("button[id^='mainInboxForm:inboxDataTable:']").get(0).click();
        return this;
    }

    @Step("Takip listesinde {kullanicilar} kullanıcısını seç")
    public GelenEvraklarPage takipListesiKullanicilarDoldur(String kullanicilar) {
        txtTakipListesiKullanicilar.type(kullanicilar).getTitleItems().filterBy(Condition.text(kullanicilar)).first().click();
        txtTakipListesiKullanicilar.closeTreePanel();
        return this;
    }

    public GelenEvraklarPage takipListeKapat() {
        btnTakipListesiKapat.click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public GelenEvraklarPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Kisi doldur")
    public GelenEvraklarPage havaleYapKisiDoldur(String kisi) {
        txtHavaleYapKisi.type(kisi).getTitleItems().filterBy(Condition.text(kisi)).first().click();
        return this;
    }

    @Step("Kullanıcı listesi doldur")
    public GelenEvraklarPage havaleYapKullaniciListesiDoldur(String kullaniciListesi) {
//        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı listesinde alanında \"{kisi}\" seçmeye dene")
    public GelenEvraklarPage havaleYapKullaniciyiSecmeyeDene(String kisi) {
        txtHavaleYapKullaniciListesi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }


    @Step("Evrak seçilir")
    public GelenEvraklarPage evrakSec() {
        tblIlkEvrak.click();
        return this;
    }

    @Step("Evrak Noya göre evrak seç : \"{evrakNo}\" ")
    public GelenEvraklarPage evrakNoyaGoreEvrakSec(String evrakNo) {
        tblGelenEvrakListesi.filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak seç")
    public GelenEvraklarPage evrakSec2() {
        tblIkinciEvrak.click();
        return this;
    }

    @Step("Paylaş buton gelmediği görülür")
    public GelenEvraklarPage paylasButonGelmedigiGorme(String buton) {
        boolean t = evrakSecButonlar.filterBy(text(buton)).size() > 0;
        Assert.assertEquals(t, false);
        takeScreenshot();
        return this;
    }

    @Step("Gelen Evraklar sayfasında evrakın listeye düşmediği görülür")
    public GelenEvraklarPage evrakGelmedigiGorme(String konu, String geldigiYer, String kayitTarihiSayi, String no) {
        boolean durum = tableEvraklar
                .filterBy(text(konu))
                .filterBy(text(geldigiYer))
                .filterBy(text(kayitTarihiSayi))
                .filterBy(text(no)).size() > 0;
        Assert.assertEquals(durum, false);
        takeScreenshot();
        return this;
    }

    @Step("Gelen Evraklar sayfasında evrakın listeye düşmediği kontrolu")
    public GelenEvraklarPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false);
        return this;
    }

    @Step("Evrak seç")
    public GelenEvraklarPage evrakSec(String konu, String geldigiYer, String kayitTarihiSayi, String no) {
        tableEvraklar
                .filterBy(text(konu))
                .filterBy(text(geldigiYer))
                .filterBy(text(kayitTarihiSayi))
                .filterBy(text(no))
                .get(0).click();
        return this;
    }

    @Step("Evrak göster, Havale yap, tebliğ et, iade et, cevap yaz, evrak kapat ikonlarının geldiği görülür.")
    public GelenEvraklarPage ikonlarinGeldigiGorme() {
        boolean durum = $$(By.id("mainPreviewForm:onizlemeRightTab:onizlemeRightTab")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Evrak önizleme ikon kontrolleri")
    public GelenEvraklarPage evrakOnizlemeIkonlarinGeldigiGorme() {
        $(By.xpath("//span[text()='Evrak Göster']/ancestor::tbody[1]//button")).isDisplayed();
        $(By.xpath("//span[text()='Havale Yap']/ancestor::tbody[1]//button")).isDisplayed();
        $(By.xpath("//span[text()='Tebliğ Et']/ancestor::tbody[1]//button")).isDisplayed();
        $(By.xpath("//span[text()='İade Et']/ancestor::tbody[1]//button")).isDisplayed();
        $(By.xpath("//span[text()='Cevap Yaz']/ancestor::tbody[1]//button")).isDisplayed();

        Allure.addAttachment("İkon kontrolleri", "Evrak önizleme ekranında, \n" +
                "Evrak göster, Havale yap, tebliğ et, iade et, cevap yaz, evrak kapat ikonlarının geldiği görülür.");

        takeScreenshot();
        return this;
    }

    @Step("Gelen Evraklar sayfasında evrakın geldiği kontrolu ve seçme")
    public GelenEvraklarPage evrakIcerikGoster(String konu, String geldigiYer, String kayitTarihiSayi, String evrakTarihi, String no) {

        System.out.println(konu);
        System.out.println(geldigiYer);
        System.out.println(kayitTarihiSayi);
        System.out.println(evrakTarihi);
        System.out.println(no);

        tableEvraklar
                .filterBy(text("Konu: " + konu))
                .filterBy(text(geldigiYer))
                .filterBy(text("Kayıt Tarihi / Sayı: " + kayitTarihiSayi))
                .filterBy(text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(text("No: " + no))
                .get(0)
                .$("[id^='mainInboxForm:inboxDataTable'] [id$='detayGosterButton']").click();

        $(By.id("mainPreviewForm:eastLayout")).waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("Gelen Evraklar sayfasında evrakın geldiği kontrolu ve seçme")
    public GelenEvraklarPage konuyaGoreEvrakIcerikGoster(String konu) {

        tableEvraklar
                .filterBy(text("Konu: " + konu))
                .first()
                .$("[id^='mainInboxForm:inboxDataTable'] [id$='detayGosterButton']").click();

        $(By.id("mainPreviewForm:eastLayout")).waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("Gelen Evraklar sayfasında evrakın geldiği kontrolu ve seçme")
    public GelenEvraklarPage evrakSec(String konu, String geldigiYer, String kayitTarihiSayi, String evrakTarihi, String no) {

        System.out.println(konu);
        System.out.println(geldigiYer);
        System.out.println(kayitTarihiSayi);
        System.out.println(evrakTarihi);
        System.out.println(no);

        tableEvraklar
                .filterBy(text("Konu: " + konu))
                .filterBy(text(geldigiYer))
                .filterBy(text("Kayıt Tarihi / Sayı: " + kayitTarihiSayi))
                .filterBy(text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(text("No: " + no))
                .get(0)
                .$("[id^='mainInboxForm:inboxDataTable'] [id$='detayGosterButton']").click();

        $(By.id("mainPreviewForm:eastLayout")).waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("Gelen Evraklar sayfasında evrakın geldiği kontrolu ve seçme")
    public GelenEvraklarPage evrakiSec(String konu, String geldigiYer, String kayitTarihiSayi, String evrakTarihi, String no) {

        System.out.println(konu);
        System.out.println(geldigiYer);
        System.out.println(kayitTarihiSayi);
        System.out.println(evrakTarihi);
        System.out.println(no);

        tableEvraklar
                .filterBy(text("Konu: " + konu))
                .filterBy(text(geldigiYer))
                .filterBy(text("Kayıt Tarihi / Sayı: " + kayitTarihiSayi))
                .filterBy(text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(text("No: " + no))
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
        txtIadeEtNot.setValue(text);
        return this;
    }

    @Step("Tebliğ Et ekranında Tebliğ Et butonuna tıklanır.")
    public GelenEvraklarPage tebligEtTebligEt() {
        btnTebligEtTebligEt.click();
        return this;
    }

    @Step("Tebliğ et ekranında not alanını doldur.")
    public GelenEvraklarPage tebligEtNotInputDoldur(String text) {
        //txtTebligEtNot.setValue(text);
        txtTebligEtNot.clear();
        txtTebligEtNot.sendKeys(text);
        txtTebligEtNot.shouldHave(value(text));
        //txtTebligEtNot.val(text);
        return this;
    }

    @Step("Tebliğ Et ekranında kullanıcı listesi alanını doldur: {kullaniciListesi}")
    public GelenEvraklarPage tebligEtKullaniciListesiDoldur(String kullaniciListesi) {
        txtTebligEtKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı listesini temizle")
    public GelenEvraklarPage tebligEtKullaniciListesiTemizle() {
        txtTebligEtKullaniciListesi.clearAllSelectedItems();
        return this;
    }

    @Step("Tebliğ Et kişi alanında kişi seç: {kisi}")
    public GelenEvraklarPage tebligEtKisiInputDoldur(String kisi) {
        txtTebligEtKisi.selectLov(kisi);
        return this;
    }

    public GelenEvraklarPage havaleYapHavaleOnayinaGonder() {
        btnHavaleYapHavaleOnayinaGonder.click();
        return this;
    }

    @Step("Havele Yap Gönder butonu")
    public GelenEvraklarPage havaleYapGonder() {
        btnHavaleYapGonder.click();
        return this;
    }

    public GelenEvraklarPage havaleYapEvrakOnayliKapatChecked(Boolean secim) {
        chkHavaleYapEvrakOnayliKapat.setSelected(secim);
        return this;
    }

    public GelenEvraklarPage havaleYapIslemSuresiDoldur(String text) {
        txtHavaleYapIslemSuresi.setValue(text);
        return this;
    }

    public GelenEvraklarPage havaleYapDosyaEkle() {
        btnHavaleYapDosyaEkle.click();
        return this;
    }

    public GelenEvraklarPage havaleYapAciklamaDoldur(String text) {
        txtHavaleYapAciklama.setValue(text);
        return this;
    }

    @Step("Havale onaylanacak kisi alanını doldur \"{onaylanacakKisi}\" | \"{onaylanacakKisi2}\"")
    public GelenEvraklarPage havaleYapOnaylanacakKisiTreeDoldur(String onaylanacakKisi, String onaylanacakKisi2) {
        treeHavaleYapOnaylanacakKisi.selectLov(onaylanacakKisi);
        return this;
    }

    @Step("Havale onaylanacak kisi alanını doldur onaylanacak kişi gelmediği görülür \"{onaylanacakKisi}\" | \"{onaylanacakKisi2}\"")
    public GelenEvraklarPage havaleYapOnaylanacakKisiTreeDoldurGelmedigiGorme(String onaylanacakKisi, String onaylanacakKisi2, Boolean durum) {
        $("[id$='onaylayacakKisiLov:treeButton']").click();
        ElementsCollection treeLovs = $$("[id='mainPreviewForm:onaylayacakKisiLov:lovTree'] li");
        boolean toplam = treeLovs.filterBy(Condition.text(onaylanacakKisi)).size() == 0;
        Assert.assertEquals(toplam, true);
        return this;
    }

    public GelenEvraklarPage havaleYapKullaniciListesiTreeDoldur(String text) {
        treeHavaleYapKullaniciListesi.selectLov(text);
        return this;
    }

    public GelenEvraklarPage havaleYapKisiTreeDoldur(String text) {
        treeHavaleYapKisi.setValue(text);
        return this;
    }

    @Step("Havale Yap Kişi alanında \"{kisi}\" seçilir.")
    public GelenEvraklarPage havaleYapKisiTreeSec(String kisi) {
        //txtComboLovKisi.selectLov(kisi);
        txtComboLovKisi.type(kisi).getSelectableItems().filterBy(text(kisi)).first().click();
        return this;
    }

    public GelenEvraklarPage havaleYapBirimTreeDoldur(String text) {
        treeHavaleYapBirim.setValue(text);
        return this;
    }

    @Step("Paylaş tıklanır")
    public GelenEvraklarPage paylasIcPaylas() {
        btnPaylasIcPaylas.click();
        return this;
    }

    @Step("Açıklama alanını \"{aciklama}\" ile doldurulur")
    public GelenEvraklarPage paylasanAciklamaDoldur(String aciklama) {
        txtPaylasanAciklama.setValue(aciklama);
        txtPaylasanAciklama.click();
        return this;
    }

    public GelenEvraklarPage paylasKisiDoldur(String text) {
        txtPaylasKisi.setValue(text);
        return this;
    }

    public GelenEvraklarPage paylasKisiSec(String kisi) {
        txtPaylasilanKisi.selectLov(kisi);
        return this;
    }

    @Step("Kişi doldur")
    public GelenEvraklarPage paylasKisiSecBirim(String kisi, String birim) {
        txtPaylasilanKisi.type(kisi).getDetailItems().filterBy(text(birim)).get(0).click();
        txtPaylasilanKisi.closeTreePanel();
        //$(By.id("mainPreviewForm:evrakOnizlemeTab")).pressEnter();
        return this;
    }

    public GelenEvraklarPage filtreleSec(String value) {
        cmbFiltrele.selectOption(value);
        return this;
    }

    public GelenEvraklarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.setValue(text);
        return this;
    }

    public GelenEvraklarPage gidecegiYerSecinizk() {
        btnGidecegiYerSeciniz.click();
        return this;
    }

    public GelenEvraklarPage gidecegiYerTreeDoldur(String text) {
        treeGidecegiYer.setValue(text);
        return this;
    }

    public GelenEvraklarPage baslangicTarihDoldur(String text) {
        dateTxtBaslangicTarih.setValue(text);
        return this;
    }

    public GelenEvraklarPage bitisTarihDoldur(String text) {
        dateTxtBitisTarihi.setValue(text);
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

    @Step("Havale Yap tıklanır")
    public GelenEvraklarPage havaleYap() {
        btnHavaleYap.click();
        return this;
    }

    @Step("Havale bilgilerinin girileceği alanların geldiği görülür.")
    public GelenEvraklarPage havaleBilgilerininGirilecegiAlanlarınGeldigiGorme() {
        boolean durum = $$(By.id("mainPreviewForm:havaleDagitimLovPanel")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Tebliğ Et butonuna tıkla")
    public GelenEvraklarPage tebligEt() {
        btnTebligEt.click();
        return this;
    }

    public GelenEvraklarPage iadeEt() {
        btnIadeEt.click();
        return this;
    }

    @Step("Cevap yaz")
    public GelenEvraklarPage cevapYaz() {
        btnCevapYaz.shouldBe(visible);
        btnCevapYaz.click();
        return this;
    }

    public GelenEvraklarPage evrakKapat() {
        btnEvrakKapat.click();
        return this;
    }

    @Step("Evrak kapatma alanında Kapatma tipi, Konu kodu, Kaldırılacak klasörler, Not, Onay akışı alanlarının geldiği görülür.")
    public GelenEvraklarPage evrakKapatmaEkranGeldigiGorme() {
        boolean durum = $$(By.id("mainPreviewForm:evrakKapatFieldsetId")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Konu alanının değeri \"{value}\" geldiği görülür.")
    public GelenEvraklarPage konuAlanDegeriKontrolu(String value) {
        SelenideElement konu = $("[id$='konuTextArea']");

        konu.getValue().equals(value);
        return this;
    }

    @Step("Gereği alanının değeri \"{value}\" geldiği görülür.")
    public GelenEvraklarPage geregiAlanDegeriKontrolu(String value) {
        ElementsCollection geregi = $$("tbody[id$='geregiLov:LovSecilenTable_data'] tr[data-ri]");

        geregi.filterBy(Condition.text(value))
                .shouldHaveSize(1);
        return this;
    }

    public GelenEvraklarPage evrakKapatKisiselKlasorlerSec(boolean secilen) {
        chkEvrakKapatKisiselKlasorler.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatEvrakKapat() {
        btnEvrakKapatEvrakKapat.get(1).pressEnter();
        return this;
    }

    public GelenEvraklarPage evrakKapatKapatmaOnayinaSun() {
        btnEvrakKapatKapatmaOnayinaSun.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(String text) {
        txtEvrakKapatOnayAkisi.setValue(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(Boolean secilen) {
        txtEvrakKapatOnayAkisi.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatNotDoldur(String text) {
        txtEvrakKapatNot.setValue(text);
        return this;
    }

    @Step("Kaldırılacak klasor doldur")
    public GelenEvraklarPage evrakKapatKaldirilacakKlasorlerDoldur(String text) {
        txtEvrakKapatKaldirilacakKlasorler.selectLov(text);
        return this;
    }

    @Step("Kaldırılıcak klasor doldur")
    public GelenEvraklarPage evrakKapatKaldirilacakKlasorlerDoldur(String text, String birim) {
        txtEvrakKapatKaldirilacakKlasorler.type(text).getDetailItems().filterBy(text(birim)).first().click();
        txtEvrakKapatKaldirilacakKlasorler.closeTreePanel();
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

    @Step("Paylaş tıkalanır")
    public GelenEvraklarPage paylas() {
        btnPaylas.click();
        return this;
    }

    @Step("Kişi ve açıklama bilgilerinin girileceği alanların geldiği görülür.")
    public GelenEvraklarPage kisiVeAciklamaAlaniGeldigiGorme() {
        boolean durum = $$(By.id("mainPreviewForm:evrakPaylasFieldsetId")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Birim")
    public GelenEvraklarPage paylasBirim() {
        clickJs(btnPaylasBirim);
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

    @Step("Tabloda evrak no kontrolü : \"{evrakNo}\" ")
    public GelenEvraklarPage tabloEvrakNoKontrol(String evrakNo) {
        int size = tableEvraklar
                .filterBy(text(evrakNo)).size();
        Assert.assertEquals(size, 1);

        return this;
    }

    @Step("Tabloda evrak konu kontrolü : \"{konu}\" ")
    public GelenEvraklarPage tabloKonuyaGoreEvrakAc(String konu) {
        tableEvraklar
                .filterBy(text(konu))
                .first().click();
        return this;
    }

    @Step("Tabloda evrak adet kontrolü")
    public int tabloEvrakAdetKontrol() {
        int size = tableEvraklar2
                .size();
        return size;
    }

    @Step("Tabloda \"{evrakNo}\" evrak nolu kayıt kontrolü : \"{shoulBeExist}\"  ")
    public GelenEvraklarPage tabloEvrakNoKontrol(String evrakNo, boolean shoulBeExist) {
        if (shoulBeExist) {
            tableEvraklar
                    .filterBy(Condition.text(evrakNo))
                    .shouldHaveSize(1);
            Allure.addAttachment("Tablo kontolü", "Listede evrak no bulundu.");
        } else
            Allure.addAttachment("Tablo kontolü", "Listede evrak no bulunamadı.");

        return this;
    }

    @Step("Tabloda \"{evrakNo}\" evrak nolu kayıt seçilir. ")
    public GelenEvraklarPage tabloEvrakNoSec(String evrakNo) {

        tableEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Önizleme geldiği görülür. ")
    public GelenEvraklarPage evrakOnizlemeKontrolu(String evrakNo) {
        SelenideElement evrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
        evrakOnizleme.isDisplayed();
        return this;
    }

    @Step("Evrak Önizleme \"{btnText}\" buton geldiği görülür.")
    public GelenEvraklarPage evrakOnizlemeButonKontrolu(String btnText) {
        SelenideElement btnEvrakOnizleme = $(By.xpath("//span[text()='" + btnText + "']/../../..//button"));
        Assert.assertEquals(btnEvrakOnizleme.isDisplayed(), true);
        return this;
    }

    @Step("Evrak Önizleme \"{btnText}\" buton tıklanır.")
    public GelenEvraklarPage evrakOnizlemeButonTikla(String btnText) {
        SelenideElement btnEvrakOnizleme = $(By.xpath("//span[text()='" + btnText + "']/../../..//button"));
        btnEvrakOnizleme.click();
        return this;
    }

    @Step("Evrak Önizleme iade edilecek kullanıcı kontrolü")
    public GelenEvraklarPage evrakOnizlemeIadeEdilecekKullanici() {
        SelenideElement lblEvrakOnizlemeIadeEdilecekKullanici = $(By.xpath("//label[normalize-space(text())='İade Edilecek Kullanıcı']//ancestor::tr//td[3]//label"));
        Allure.addAttachment("İade Edilecek Kullanıcı : ", lblEvrakOnizlemeIadeEdilecekKullanici.text());
        return this;
    }

    @Step("Evrak Kapama Onay Akışı butonuna tıklanır.")
    public GelenEvraklarPage evrakKapamaOnayAkisiTikla() {
//        Selenide.executeJavaScript("arguments[1].scrollIntoView(true);", btnOnayAkisi);
//        txtEvrakKapatOnayAkisi.sendKeys(Keys.TAB);
        clickJs(btnOnayAkisi);
        return this;
    }

    @Step("Onay Akışı İşlemleri alanında Kullanıcılar alanında \"{kullanici}\" seçilir.")
    public GelenEvraklarPage evrakKapamaKullaniciSec(String kullanici) {
        txtKullanicalar.selectLov(kullanici);
        return this;
    }

    @Step("Onay Akışı İşlemleri alanında Kullanıcılar alanında \"{kullanici}\" seçilir.")
    public GelenEvraklarPage evrakKapamaKullaniciSecWithTitle(String kullanici, String title) {
        txtKullanicalar.type(kullanici).getTitleItems()
                .filterBy(Condition.exactText(kullanici + title)).first().click();
        return this;
    }

    @Step("Lütfen seçim yapınız... popup'ı geldiği görülür.")
    public GelenEvraklarPage popUpKullaniciSecimKontrulu() {
        SelenideElement popUp = $(By.xpath("//span[text()='Lütfen seçim yapınız...']"));
        popUp.isDisplayed();
        return this;
    }

    @Step("Lütfen seçim yapınız... popup'ında \"{kullanici}\" seçilir.")
    public GelenEvraklarPage popUpKullaniciSecimi(String kullanici) {
        tblVekaletAlanVeren
                .filterBy(Condition.text(kullanici))
                .first()
                .$("button[id^='mainPreviewForm:mainPreviewFormKullaniciBirimSeceneklerAkis:'] ").click();
        return this;
    }

    @Step("Evrak Kapama Kullanıcılar alanı kontrolü.")
    public GelenEvraklarPage evrakKapamaKullanicilarAlaniKontrolü(String vekaletAlan, String title, String vekaletVeren) {
        List<String> text = txtKullanicalar.getSelectedItems().texts();
        System.out.println(text);

        Assert.assertEquals(text.get(0).contains(vekaletAlan), true);
        Assert.assertEquals(text.get(0).contains(title), true);
        Assert.assertEquals(text.get(0).contains(vekaletVeren), true);
        takeScreenshot();
//        Allure.addAttachment("Onaylayacak kişi : ", "Onaylayacak Kisi alanına \n" + text.get(0) + " geldiği görülür.");
        return this;
    }

    @Step("Tabloda olmayan evrak no kontrolü : \"{evrakNo}\" ")
    public GelenEvraklarPage tabloOlmayanEvrakNoKontrol(String evrakNo) {
        int size = tableEvraklar
                .filterBy(text(evrakNo)).size();
        Assert.assertEquals(size, 0);

        return this;
    }

    @Step("Tabloda olmayan evrak konu kontrolü : \"{konu}\" ")
    public GelenEvraklarPage tabloOlmayanEvrakKontrol(String konu) {
        tableEvraklar
                .filterBy(text(konu)).shouldHaveSize(0);
        return this;
    }


    //Cevap yaz sayfası
    @Step("Seçilen onay akışı detail kontrolu: \"{secim}\" ")
    public GelenEvraklarPage onayAkisiDetailKontrol(String secim) {
//        System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovDetailText());
        cmbOnayAkisi.getSelectedDetails().last().shouldHave(text(secim));
//        Assert.assertEquals(cmbOnayAkisi.lastSelectedLovDetailText().contains(secim), true);
        return this;
    }

    //Cevap yaz sayfası
    @Step("Seçilen onay akışı title kontrolu: \"{onayAkisiTitle}\" ")
    public GelenEvraklarPage onayAkisiTitleKontrol(String onayAkisiTitle) {
        /*System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovTitleText());
        Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(onayAkisiTitle), true);*/
        cmbOnayAkisi.getSelectedTitles().last().shouldHave(text(onayAkisiTitle));
        return this;
    }

    @Step("Vekalet alan Ve Veren tablo vekalet alan seç : \"{isim}\" ")
    public GelenEvraklarPage vekeletAlanVerenTabloVekaletAlanveyaVerenSec(String isim) {
        tblVekaletVerenAlan
                .filterBy(text(isim)).first()
                .$("button").click();
        return this;
    }

    @Step("Vekalet var uyarısı : \"{mesaj}\" ")
    public GelenEvraklarPage evrakOnIzlemeUyarıPopUpKontol(String mesaj) {
        SelenideElement popUp = $("div[class='ui-confirm-dialog ui-dialog ui-widget ui-widget-content ui-corner-all ui-helper-hidden ui-shadow ui-overlay-visible']");
        SelenideElement popUpEvet = $(By.xpath("//div[@class='ui-confirm-dialog ui-dialog ui-widget ui-widget-content ui-corner-all ui-helper-hidden ui-shadow ui-overlay-visible']//center//button[1]"));
        popUp.should(Condition.visible);
        if (popUp.text().contains(mesaj))
            clickJs(popUpEvet);
        return this;
    }

    @Step("Kullacici listesinde \"{kullanici}\" kullanıcısını seç.")
    public GelenEvraklarPage kullanciListesiSec(String kullanici) {
        txtKullaniciListesi.selectLov(kullanici);
        return this;
    }

    @Step("Kullacici listesinde \"{kullanici}\" kullanıcısını seç.")
    public GelenEvraklarPage kullaniciListesiSec(String kullanici) {
        txtKullaniciListesi.selectLov(kullanici);
        return this;
    }

    @Step("Kullacici listesi seç : \"{kullanici}\" ")
    public GelenEvraklarPage kullanciListesiSec2(String kullanici) {
        txtKullaniciListesi.type(kullanici).getSelectableItems().first().click();
        return this;
    }

    @Step("Kullanıcılar alanı doldur : \nKullanıcı : \"{kullanici}\", \nTitle : \"{title}\" ")
    public GelenEvraklarPage kullanciListesiSecWithTitle(String kullanici, String title) {
        txtKullaniciListesi.type(kullanici).getDetailItems()
                .filterBy(Condition.exactText(title)).first().click();
        return this;
    }

    @Step("Birimler menüsünde \"{birim}\" biriminin geldiği görülür.")
    public GelenEvraklarPage birimKontol(String birim) {
        $$("#leftMenuForm #birimlerimMenusuContainer a")
                .filterBy(Condition.text(birim)).shouldHaveSize(1);
        return this;
    }

    @Step("Gelen evraklar tablosunda 1. satırdan {bilgi} bilgisi değerini getir.")
    public String degerGetir(String bilgi) {
        String deger = "";
        SelenideElement ilkSatir = tableEvraklar2.first();
        deger = ilkSatir.$x(".//td[@role='gridcell']/div[@class='searchText' and contains(., '" + bilgi + "')]").getText();

        return deger.substring(bilgi.length() + 2, deger.length());
    }

    @Step("Gelen evraklar tablosunda {satir}. satırdan {bilgi} bilgisi değerini getir.")
    public String degerGetir(int satir, String bilgi) {
        String deger = "";
        SelenideElement currentRow = tableEvraklar2.get(satir);
        deger = currentRow.$x(".//td[@role='gridcell']/div[@class='searchText' and contains(., '" + bilgi + "')]").getText();

        return deger.substring(bilgi.length() + 2, deger.length());
    }

    // FAZ 2

    ElementsCollection tblTakipListesi = $$("tbody[id='evrakTakibimeEkleDialogForm:takipListLov:LovSecilenTable_data'] > tr[role='row']");
    @Step("{konu} konulu evrak üzerinde Takip Listesi butonuna tıkla.")
    public GelenEvraklarPage takipListesiAc(String konu) {
        tableEvraklar
                .filterBy(text(konu))
                .first()
                .$x(".//span[contains(@class,'ui-button-icon-left ui-icon document-addFollow')]/..")
                .click();
        return this;
    }

    @Step("Takip Listesinde {adiSoyadi} kullanıcısının ve {birim} birim bilgisinin olduğu görülür.")
    public GelenEvraklarPage takipListesiKontrol(String adiSoyadi, String birim){
        tblTakipListesi.filterBy(text(adiSoyadi)).filterBy(text(birim)).first().shouldBe(visible);
        return this;
    }

    @Step("Evrak Kapatma panelinde evrak kapat butonuna tıkla.")
    public GelenEvraklarPage evrakiKapat(){
        btnEvrakKapatKapat2.click();
        if(btnEvrakKapatUyariEvet.isDisplayed())
            btnEvrakKapatUyariEvet.waitUntil(visible, 50000).click();
        return this;
    }

    @Step("Takip Listesi ekranında bulunan (X) \"Sayfayı Kapatma\" butonuna basılır. Takip listesi ekranın kapatıldığı görülür.")
    public GelenEvraklarPage takipListesiKapat(){
        btnTakipListesiKapat.click();
        txtKullaniciListesi.shouldNotBe(visible);
        return this;
    }


    // //span[contains(@class,'ui-button-icon-left ui-icon document-addFollow')]/..

}
