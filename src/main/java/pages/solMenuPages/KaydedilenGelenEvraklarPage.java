package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
    static final Logger logger = LogManager.getLogger("KaydedilenGelenEvraklarPage");

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
    SelenideElement icerikHavaleYap = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:5:cmdbutton"));
    SelenideElement btnOnizlemeHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    BelgenetElement cmbHavaleIslemleriOnaylayacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));
    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement btnHavaleOnayinaGonder = $("[id^='mainPreviewForm:j_idt'] [class^='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only havaleIslemleriGonder']");

    SelenideElement btnIcerikHavaleOnayinaGonder = $("[id^='inboxItemInfoForm:j_idt'] [class^='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only havaleIslemleriGonder']");

    SelenideElement icerikHavaleOnayinaGonder = $("[id^='inboxItemInfoForm:j_idt'] [class^='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only havaleGonderButonClass']");
    SelenideElement btnGonder = $("[id^='mainPreviewForm:j_idt'] [class^='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only havaleGonderButonClass']");

    //otomatik havale kontrol
    SelenideElement otomatikHavaleCheckbox = $("[id='mainPreviewForm:havaleDagitimLovPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    SelenideElement birimKontrol = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement kisiKontrol = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement kullanıcıListeKontrol = $(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement aciklamaKontrol = $(By.id("mainPreviewForm:havaleAciklama"));
    SelenideElement dosyaEkleKontrol = $(By.id("mainPreviewForm:fileUploadHavaleEk"));
    SelenideElement islemSureKontrol = $(By.id("mainPreviewForm:islemSuresiTarih_input"));

//    //otomatik havale  kontrol içerikten delete those if not needed
//    SelenideElement icerikOtomatikHavaleCheckbox = $("[id='inboxItemInfoForm:havaleDagitimLovPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
//    SelenideElement txtIcerikBirimKontrol = $(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov:LovText"));
//    SelenideElement icerikKisiKontrol = $(By.id("inboxItemInfoForm:dagitimBilgileriKullaniciLov:LovText"));
//    SelenideElement icerikKullanıcıListeKontrol = $(By.id("inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovText"));
//    SelenideElement icerikAciklamaKontrol = $(By.id("inboxItemInfoForm:havaleAciklama"));
//    SelenideElement icerikDosyaEkleKontrol = $(By.id("inboxItemInfoForm:fileUploadHavaleEk"));
//    SelenideElement icerikIslemSureKontrol = $(By.id("inboxItemInfoForm:islemSuresiTarih_input"));
//  otomatik havale  kontrol içerikten
    SelenideElement cboxIcerikOtomatikHavaleCheckbox = $("[id='inboxItemInfoForm:havaleDagitimLovPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    SelenideElement txtIcerikBirimKontrol = $(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement txtIcerikKisiKontrol = $(By.id("inboxItemInfoForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtIcerikKullanıcıListeKontrol = $(By.id("inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement txtIcerikAciklamaKontrol = $(By.id("inboxItemInfoForm:havaleAciklama"));
    SelenideElement btnIcerikDosyaEkleKontrol = $(By.id("inboxItemInfoForm:fileUploadHavaleEk"));
    SelenideElement txtIcerikIslemSureKontrol = $(By.id("inboxItemInfoForm:islemSuresiTarih_input"));

    BelgenetElement txtHavaleIslemleriKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtIcerikHavaleIslemleriKisi = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtEvrakBilgileriAciklama = $(By.id("mainPreviewForm:havaleAciklama"));
    SelenideElement dagitimBilgileriKisiOpsiyon = $("select[id^='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable']");
    SelenideElement dosyaPath = $(By.xpath("//input[@id='mainPreviewForm:fileUploadHavaleEk_input']"));
    SelenideElement icerikDosyaPath = $(By.xpath("//input[@id='inboxItemInfoForm:fileUploadHavaleEk_input']"));

    SelenideElement btnKaydet = $("[id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton']");

    SelenideElement ustYaziveHavaleYeriYokpopUp = $("[id='ustYaziveHavaleYeriYokConfirmDialog']");
    SelenideElement ustYaziYokEvet = $("[id='evetDugmesi']");
    SelenideElement ustYaziYokpopUp = $("[id='ustYaziYokConfirmDialog'][class$='ui-overlay-visible']");
    SelenideElement popUpEvet = $("[id='evetDugmesiUstYaziHavaleYer']");
    SelenideElement popUpEvet2 = $("[id='evetButtonGonderilsinMi']");

    SelenideElement mukerrerPopUpEvet = $("[id='evetButtonBenzerKaydet']");
    SelenideElement mukerrerPopUp = $("[id='benzerEvrakKayitConfirmDialog'][class$='ui-overlay-visible']");
    SelenideElement btnUstYaziDegistirmeHayır = $(By.id("evrakBilgileriForm:ustyaziDegistirmeButton"));
    SelenideElement btnUstYaziDegistirEvet = $(By.id("evrakBilgileriForm:ustyaziDegistirButton"));
    SelenideElement popUpUstYaziDegistirilme = $(By.id("evrakBilgileriForm:ustyaziDegistirisilMiDialog"));
    SelenideElement basariliPopUpKapat = $("[id='evrakKaydetBasariliDialogForm:vazgecButton']");
    SelenideElement basariliPopUp = $("[id='evrakKaydetBasariliDialog'][class$='ui-overlay-visible']");

    SelenideElement popUphavaleYeriSecmediniz = $(By.id("havaleYeriSecmedinizConfirmDialog"));
    SelenideElement btnHavaleYeriSecmedinizEvet = $(By.id("evetButtonBos"));
    SelenideElement popUphavaleOnayGonderilsinmi = $(By.id("havaleOnayinaGonderilsinmiConfirmDialog"));
    SelenideElement btnUstYaziveHavaleYeriSecmedinizEvet = $("[id='evetButtonBos']");
    SelenideElement btnUstYaziveHavaleYeriSecmedinizHayır = $(By.id("hayirButtonBos"));
    SelenideElement btnHavaleYeriSecmedinizHayır = $(By.id("hayirDugmesiUstYaziHavaleYer"));
    ElementsCollection visibleEvrakBasarili = $$("[id='evrakKaydetBasariliDialog']");

    BelgenetElement cmbIcerikHavaleIslemleriOnaylayacakKisi = comboLov(By.id("inboxItemInfoForm:onaylayacakKisiLov:LovText"));
    BelgenetElement cmbIcerikHavaleIslemleriBirim = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov:LovText"));

    SelenideElement btnEvrakDetayiKaydetUyarisi = $(By.id("kaydetConfirmForm:kaydetEvetButton"));

    SelenideElement lblSayfa = $("[class='ui-inbox-header-title']");
    ElementsCollection btnTopluHavale = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-charge']");
    SelenideElement tabHavale = $("[id='mainPreviewForm:evrakOnizlemeTab']");

    SelenideElement tabEvrakDetayi = $("[id='inboxItemInfoForm']");

    SelenideElement txtEklenenBirim = $("div[id^='mainPreviewForm:dagitimBilgileriBirimLov:LovSecilenTable:0:j_idt']");
    SelenideElement txtIcerikEklenenBirim = $("div[id^='inboxItemInfoForm:dagitimBilgileriBirimLov:LovSecilenTable:0:j_idt']");
    SelenideElement txtIcerikEklenenKisi = $("div[id^='inboxItemInfoForm:dagitimBilgileriKullaniciLov:LovSecilenTable:0:j_idt']");
    SelenideElement txtEklenenKisi = $("div[id^='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable:0:j_idt']");
    SelenideElement txtIcerikOnaylayanKisi = $("div[id^='inboxItemInfoForm:onaylayacakKisiLov:j_idt'][class='lovItemTitle']");
    SelenideElement txtOnaylayanKisi = $("div[id^='mainPreviewForm:onaylayacakKisiLov:LovSecilen']");

    SelenideElement btnIcerikDosyaDeleteIcon = $("button[id^='inboxItemInfoForm:j_idt'] span[class='ui-button-icon-left ui-icon delete-icon']");

    BelgenetElement txtComboLovKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));

    @Step("Kaydedilen gelen evraklar sayfası aç")
    public KaydedilenGelenEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
        return this;
    }

    @Step("Orta alanda \"{sayfa}\" ekranı açılır\n")
    public KaydedilenGelenEvraklarPage sayfaKontrol(String sayfa) {
        Assert.assertEquals(lblSayfa.getText().equals(sayfa), true, sayfa);
        Allure.addAttachment(sayfa, "açılmaktadır");
        return this;
    }


    @Step("Evrak Sec Toplu ve Toplu Havale Yap")
    public KaydedilenGelenEvraklarPage evraklariSecTopluHavaleYap(String konu1, String konu2, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        btnTopluHavale.get(0).click();

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
        if (evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
        return this;
    }

    @Step("Onizleme Evrak Havale Yap Butonu Tıklandı")
    public KaydedilenGelenEvraklarPage onizlemeHavaleYap() {
        btnOnizlemeHavaleYap.click();
        return this;
    }

    @Step("Onizleme Evrak Havale Yap Butonu Tıklandı")
    public KaydedilenGelenEvraklarPage onizlemeHavaleButtonKontrol() {
        Assert.assertEquals(btnOnizlemeHavaleYap.isDisplayed(), true, "Evrak Havale Yap butonu bulunmaktadır");
        Allure.addAttachment("Evrak Havale Yap butonu bulunmaktadır", "");
        return this;
    }

    @Step("İçerik Evrak Havale Yap Butonu Tıklandı")
    public KaydedilenGelenEvraklarPage icerikHavaleYap() {
        icerikHavaleYap.click();
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public KaydedilenGelenEvraklarPage dagitimBilgileriOnaylayanWithDetails(String onaylayan, String details) {
        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan, details);
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public KaydedilenGelenEvraklarPage icerikDagitimBilgileriOnaylayanWithDetails(String onaylayan, String details) {
        cmbIcerikHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan, details);
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public KaydedilenGelenEvraklarPage dagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
//        cmbHavaleIslemleriBirim.type(birim).getDetailItems()
//                .filterBy(Condition.exactText(details)).first().click();
//        cmbHavaleIslemleriBirim.closeTreePanel();
        cmbHavaleIslemleriBirim.selectLov(birim, details);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public KaydedilenGelenEvraklarPage eklenenBirimKontrolu(String birim) {
        Assert.assertEquals(txtEklenenBirim.isDisplayed(), true, "Birim Eklendi");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }


    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public KaydedilenGelenEvraklarPage eklenenIcerikBirimKontrolu(String birim) {
        Assert.assertEquals(txtIcerikEklenenBirim.isDisplayed(), true, "Birim Eklendi");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }


    @Step("Havale İşlemleri Kisi alanında eklenen \"{kisi}\" kontrolü")
    public KaydedilenGelenEvraklarPage eklenenKisiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenKisi.isDisplayed(), true, "Kisi Eklendi");
        Allure.addAttachment("Kisi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Kisi alanında eklenen \"{kisi}\" kontrolü")
    public KaydedilenGelenEvraklarPage eklenenIcerikKisiKontrolu(String kisi) {
        Assert.assertEquals(txtIcerikEklenenKisi.isDisplayed(), true, "Kisi Eklendi");
        Allure.addAttachment("Kisi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Onaylayan alanında eklenen \"{kisi}\" kontrolü")
    public KaydedilenGelenEvraklarPage eklenenIcerikOnaylayanKontrolu(String kisi) {
        Assert.assertEquals(txtIcerikOnaylayanKisi.isDisplayed(), true, "Onaylayan Kisi Eklendi");
        Allure.addAttachment("Onaylayan Kisi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Onaylayan alanında eklenen \"{kisi}\" kontrolü")
    public KaydedilenGelenEvraklarPage eklenenOnaylayanKontrolu(String kisi) {
        Assert.assertEquals(txtOnaylayanKisi.isDisplayed(), true, "Onaylayan Kisi Eklendi");
        Allure.addAttachment("Onaylayan Kisi Eklendi:", kisi);
        return this;
    }


    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public KaydedilenGelenEvraklarPage icerikDagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
        cmbIcerikHavaleIslemleriBirim.selectLov(birim,details);
//        icerikHavaleIslemleriBirim.type(birim).getDetailItems()
//                .filterBy(Condition.exactText(details)).first().click();
//        icerikHavaleIslemleriBirim.closeTreePanel();
//        icerikHavaleIslemleriBirim.selectLov(birim, details);
        return this;
    }

    @Step("Havale Onayına Gönder")
    public KaydedilenGelenEvraklarPage havaleOnayinaGonder() {
        btnHavaleOnayinaGonder.click();
        return this;
    }

    @Step("İçerikten Havale Onayına Gönder")
    public KaydedilenGelenEvraklarPage icerikHavaleOnayinaGonder() {
        icerikHavaleOnayinaGonder.click();
        return this;
    }

    @Step("İçerikten Havale Onayına Gönder")
    public KaydedilenGelenEvraklarPage icerikHavaleOnayinaGonder2() {
            btnIcerikHavaleOnayinaGonder.click();
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

    @Step("Evrak listede gözükmemektedir : \"{evrakNo}\" ")
    public KaydedilenGelenEvraklarPage tabloEvrakNoileEvrakGelmediKontrolu(String evrakNo) {
        boolean durum = tblEvraklar.filterBy(Condition.text(evrakNo)).size() == 0;
        Assert.assertEquals(durum, true, "Evrak Listelenmemektedir");
        Allure.addAttachment("Evrak Listelenmemektedir", evrakNo);
        return this;
    }


    @Step("Havale İşlemleri Alanındaki Kontroller")
    public KaydedilenGelenEvraklarPage havaleAlanKontrolleri() {
        String text = "";
        if (otomatikHavaleCheckbox.isDisplayed()) {
            text += "Otomatik Havale Checkbox,";
            Assert.assertEquals(otomatikHavaleCheckbox.isDisplayed(), true, "Otomatik Havale Checkbox Alanı Görüntülendi");
            Allure.addAttachment("Otomatik Havale Checkbox Alanı Görüntülendi : ", "");
        }
        if (birimKontrol.isDisplayed()) {
            text += "Birim Kontrol,";
            Assert.assertEquals(birimKontrol.isDisplayed(), true, "Birim Alanı Görüntülendi");
            Allure.addAttachment("Birim Kontrol Alanı Görüntülendi : ", "");
        }
        if (kisiKontrol.isDisplayed()) {
            text += "Kisi Kontrol, ";
            Assert.assertEquals(kisiKontrol.isDisplayed(), true, "Kisi Alanı Görüntülendi");
            Allure.addAttachment("Kisi Alanı Görüntülendi : ", "");
        }
        if (kullanıcıListeKontrol.isDisplayed()) {
            text += "Kullanıcı Liste,";
            Assert.assertEquals(kullanıcıListeKontrol.isDisplayed(), true, "Kullanıcı Liste Alanı Görüntülendi");
            Allure.addAttachment("Kullanıcı Liste Alanı Görüntülendi : ", "");
        }
        if (aciklamaKontrol.isDisplayed()) {
            text += "Aciklama,";
            Assert.assertEquals(aciklamaKontrol.isDisplayed(), true, "Aciklama Alanı Görüntülendi");
            Allure.addAttachment("Aciklama Alanı Görüntülendi : ", "");
        }
        if (dosyaEkleKontrol.isDisplayed()) {
            text += "Dosya Ekle,";
            Assert.assertEquals(dosyaEkleKontrol.isDisplayed(), true, "Dosya Ekle Alanı Görüntülendi");
            Allure.addAttachment("Dosya Ekle Alanı Görüntülendi : ", "");
        }
        if (islemSureKontrol.isDisplayed()) {
            text += "İslem Sure alanları gösterilmektedir.";
            Assert.assertEquals(islemSureKontrol.isDisplayed(), true, "İşlem Süre Alanı Görüntülendi");
            Allure.addAttachment("İslem Sure Alanı Görüntülendi : ", "");
        }
        Allure.addAttachment("Alan Kontrolleri : ", text);
        takeScreenshot();
        return this;
    }

    @Step("Havale İşlemleri Alanındaki Kontroller")
    public KaydedilenGelenEvraklarPage icerikHavaleAlanKontrolleri() {
        String text = "";
        if (cboxIcerikOtomatikHavaleCheckbox.isDisplayed()) {
            text += "Otomatik Havale Checkbox,";
            Assert.assertEquals(cboxIcerikOtomatikHavaleCheckbox.isDisplayed(), true, "Otomatik Havale Checkbox Alanı Görüntülendi");
            Allure.addAttachment("Otomatik Havale Checkbox Alanı Görüntülendi : ", "");
        }
        if(txtIcerikBirimKontrol.isDisplayed()) {
            text += "Birim Kontrol,";
            Assert.assertEquals(txtIcerikBirimKontrol.isDisplayed(),true,"Birim Alanı Görüntülendi");
            Allure.addAttachment("Birim Kontrol Alanı Görüntülendi : ","");
        }
        if (txtIcerikKisiKontrol.isDisplayed()) {
            text += "Kisi Kontrol, ";
            Assert.assertEquals(txtIcerikKisiKontrol.isDisplayed(), true, "Kisi Alanı Görüntülendi");
            Allure.addAttachment("Kisi Alanı Görüntülendi : ", "");
        }
        if (txtIcerikKullanıcıListeKontrol.isDisplayed()) {
            text += "Kullanıcı Liste,";
            Assert.assertEquals(txtIcerikKullanıcıListeKontrol.isDisplayed(), true, "Kullanıcı Liste Alanı Görüntülendi");
            Allure.addAttachment("Kullanıcı Liste Alanı Görüntülendi : ", "");
        }
        if (txtIcerikAciklamaKontrol.isDisplayed()) {
            text += "Aciklama,";
            Assert.assertEquals(txtIcerikAciklamaKontrol.isDisplayed(), true, "Aciklama Alanı Görüntülendi");
            Allure.addAttachment("Aciklama Alanı Görüntülendi : ", "");
        }
        if (btnIcerikDosyaEkleKontrol.isDisplayed()) {
            text += "Dosya Ekle,";
            Assert.assertEquals(btnIcerikDosyaEkleKontrol.isDisplayed(), true, "Dosya Ekle Alanı Görüntülendi");
            Allure.addAttachment("Dosya Ekle Alanı Görüntülendi : ", "");
        }
        if (txtIcerikIslemSureKontrol.isDisplayed()) {
            text += "İslem Sure alanları gösterilmektedir.";
            Assert.assertEquals(txtIcerikIslemSureKontrol.isDisplayed(), true, "İşlem Süre Alanı Görüntülendi");
            Allure.addAttachment("İslem Sure Alanı Görüntülendi : ", "");
        }
        Allure.addAttachment("Alan Kontrolleri : ", text);
        return this;
    }



    @Step("Havale İşlemleminde Dosya Ekle")
    public KaydedilenGelenEvraklarPage dosyaEkle() {
        dosyaEkleKontrol.click();
        return this;
    }

    @Step("Havale İşlemleminde Dosya Ekle")
    public KaydedilenGelenEvraklarPage icerikDosyaEkle() {
        btnIcerikDosyaEkleKontrol.click();
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public KaydedilenGelenEvraklarPage havaleIslemleriKisiDoldur(String kisi) {
        txtHavaleIslemleriKisi.selectLov(kisi);
        return this;
    }

    @Step("İçerik Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public KaydedilenGelenEvraklarPage icerikHavaleIslemleriKisiDoldur(String kisi) {
        txtIcerikHavaleIslemleriKisi.selectLov(kisi);
        return this;
    }

    @Step("İçerik Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public KaydedilenGelenEvraklarPage icerikHavaleIslemleriKisiDoldur(String kisi,String details) {
        txtIcerikHavaleIslemleriKisi.selectLov(kisi, details);
        return this;
    }

    @Step("Havale İşlemleri Açıklama Alanını Doldur")
    public KaydedilenGelenEvraklarPage aciklamaAlaniDoldur(String aciklama) {
        txtEvrakBilgileriAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{opsiyon}\" seçilir")
    public KaydedilenGelenEvraklarPage dagitimBilgileriKisiOpsiyon(String opsiyon) {
//        dagitimBilgileriKisiOpsiyon.selectOptionByValue(opsiyon);
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        if (opsiyon.equals(gerek))
            dagitimBilgileriKisiOpsiyon.selectOptionByValue("G");
        else if (opsiyon.equals(bilgi))
            dagitimBilgileriKisiOpsiyon.selectOptionByValue("B");
        else if (opsiyon.equals(koordinasyon))
            dagitimBilgileriKisiOpsiyon.selectOptionByValue("S");

        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme : \"{pathToFile}\" ")
    public KaydedilenGelenEvraklarPage havaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPath, pathToFile);
        Thread.sleep(4000);
//        WebDriverRunner.getWebDriver()
//                .findElement(dosyaPath)
//                .sendKeys(pathToFile);
        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme : \"{pathToFile}\" ")
    public KaydedilenGelenEvraklarPage icerikHavaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(icerikDosyaPath, pathToFile);
        Thread.sleep(4000);
//        WebDriverRunner.getWebDriver()
//                .findElement(dosyaPath)
//                .sendKeys(pathToFile);
        return this;
    }


    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public KaydedilenGelenEvraklarPage havaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public KaydedilenGelenEvraklarPage icerikHavaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public KaydedilenGelenEvraklarPage icerikHavaleDosyaEkleDosyaAdiKontrol(String dosyaAdi,boolean durum) {
        Assert.assertEquals($(byText(dosyaAdi)).isDisplayed(),durum,"Dosya kaldırıldi:" +dosyaAdi);
        Allure.addAttachment("Eklenmiş olan dosya, kaldırılmıştır:",dosyaAdi);
        return this;
    }

    @Step("Kaydedilen Gelen Evrak Gönder")
    public KaydedilenGelenEvraklarPage buttonGonder() {
        btnGonder.click();
        return this;
    }

    @Step("Kaydet butonu")
    public KaydedilenGelenEvraklarPage kaydet() {
        btnKaydet.click();
        return this;
    }


    @Step("Evrak Detayi Kaydet PopUp Close")
    public KaydedilenGelenEvraklarPage evrakDetayiKaydetPopUpClose() {
        btnEvrakDetayiKaydetUyarisi.shouldBe(Condition.visible);
        btnEvrakDetayiKaydetUyarisi.click();
        return this;
    }

    @Step("PopUp kontrolleri v2")
    public String popUpsv2() {
        Selenide.sleep(5000);

        if (ustYaziYokpopUp.isDisplayed()) {
            clickJs(ustYaziYokEvet);
            Allure.addAttachment("Üst Yazı Seçmediniz PopUp'ı", "Üst Yazı gelmemiştir PopUp'ı kapatılır.");
        }
        if (ustYaziveHavaleYeriYokpopUp.isDisplayed()) {
            clickJs(popUpEvet);
            Allure.addAttachment("Üst Yazı ve Havale Yeri yok PopUp'ı", "Üst Yazı ve Havale Yeri yok PopUp'ı kapatılır.");
        }
        if (popUphavaleYeriSecmediniz.isDisplayed()) {
            String mesaj2 = "Havale yeri seçmediniz. Evrak kaydedildiğinde Kaydedilen Gelen Evraklar kutusuna düşecektir. İşleme devam etmek istiyor musunuz?";
            popUphavaleYeriSecmediniz.getText().equals(mesaj2);
            clickJs(btnHavaleYeriSecmedinizEvet);
            Allure.addAttachment("Havale Yeri Seçmediniz PopUp'ı", mesaj2);
        }

        if (popUphavaleOnayGonderilsinmi.isDisplayed()) {
            String mesaj3 = "Evrakı havale onayina göndermek istiyor musunuz?";
            popUphavaleOnayGonderilsinmi.getText().equals(mesaj3);
            clickJs(popUphavaleOnayGonderilsinmi);
            Allure.addAttachment("Havale Onay Gonderilsin mi PopUp'ı", mesaj3);
        }
        if (mukerrerPopUp.isDisplayed()) {
            clickJs(mukerrerPopUpEvet);
            Allure.addAttachment("Mükerrer İşlem PopUp'ı", "Mükerrer İşlem PopUp'ı kapatılır.");
        }
        basariliPopUp.shouldBe(Condition.visible);
        String mesaj4 = "Evrak başarıyla kaydedilmiştir.";
        basariliPopUp.getText().contains(mesaj4);
        Allure.addAttachment("İşlem başarılı PopUp'ı", mesaj4);

        SelenideElement vEvrakBasarili = visibleEvrakBasarili.filterBy(Condition.visible).get(0);
        String evrakNo = getIntegerInText(vEvrakBasarili.getText());
        clickJs(basariliPopUpKapat);

        return evrakNo;
    }

    @Step("Havale Etme ekranı açılır\n")
    public KaydedilenGelenEvraklarPage ekranKontrol() {
        Assert.assertEquals(tabHavale.isDisplayed(), true, "Havale Etme sayfası");
        Allure.addAttachment("Havale Etme sayfası", "açılmaktadır");
        return this;
    }

    @Step("Evrak Detay ekranı açılır\n")
    public KaydedilenGelenEvraklarPage ekranKontrolEvrakDetayi() {
        Assert.assertEquals(tabEvrakDetayi.isDisplayed(), true, "Evrak Detay sayfası");
        Allure.addAttachment("Evrak Detay sayfası", "açılmaktadır");
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" kontrol")
    public KaydedilenGelenEvraklarPage havaleIslemleriKisiKontrol(String kisi) {
        boolean durum = txtIcerikHavaleIslemleriKisi.isLovValueSelectable(kisi);
        Assert.assertEquals(durum,false,"Kişi Bulundu:" + kisi);
        Allure.addAttachment(kisi, " seçilemedi");
        txtIcerikHavaleIslemleriKisi.closeTreePanel();
        return this;
    }

    @Step("Havale İşlemleri Onaylayacak Kişi alanında \"{kisi}\" kontrol")
    public KaydedilenGelenEvraklarPage havaleIslemleriOnaylayacakKisiKontrol(String kisi) {
        boolean durum = cmbIcerikHavaleIslemleriOnaylayacakKisi.openTreePanel().getSelectableItems().filterBy(Condition.text(kisi)).shouldHaveSize(0).size()==0;
        Assert.assertEquals(durum,true,"Havale İşlemleri Onaylayacak Kişi bulunamamıştır:" + kisi) ;
        Allure.addAttachment("Havale İşlemleri Onaylayacak Kişi bulunamamıştır:",kisi);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{birim}\" kontrol")
    public KaydedilenGelenEvraklarPage havaleIslemleriBirimKontrol(String birim) {
        boolean durum = cmbIcerikHavaleIslemleriBirim.isLovValueSelectable(birim);
        Assert.assertEquals(durum,false,"Birim Bulundu:" + birim);
        Allure.addAttachment(birim, " seçilemedi");
        cmbIcerikHavaleIslemleriBirim.closeTreePanel();
        return this;
    }

    @Step("Evrak Detayı ekranında eklenen dosyayı iptal etme\n")
    public KaydedilenGelenEvraklarPage icerikDosyaDeleteIcon() {
        btnIcerikDosyaDeleteIcon.click();
        return this;
    }

}
