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
    SelenideElement btnHavaleYap = $("[class='ui-button-icon-left ui-icon havaleEt']");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtHavaleYapOnaylanacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));
    BelgenetElement txtHavaleYapAciklama = comboLov(By.id("mainPreviewForm:havaleAciklama"));
    ElementsCollection txtHavaleYapDosyaEkle = $$(By.id("mainPreviewForm:fileUploadHavaleEk_input"));
    ElementsCollection txtHavaleYapIslemSuresi = $$(By.id("mainPreviewForm:fileUploadHavaleEk_input"));
    BelgenetElement txtHavaleYapBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] > tr[role='row']");
    SelenideElement tabHavale = $("[id='mainPreviewForm:topluHavaleOnizlemeTab']");
    BelgenetElement txtHavaleYapKullniciListesi = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtTebligEtKullniciListesi = comboLov(By.id("inboxItemInfoForm:kullaniciGrubuLov_id:LovText"));
    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection tabEvrakGecmisi = $$("[id$='evrakOnizlemeTab'] ul li");
    BelgenetElement cmbBirimeHavale = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    By cmbBirimeHavaleBy = By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText");
    SelenideElement formEvrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));


    SelenideElement evrakOnizlemeKontrol = $(By.id("mainPreviewForm:eastLayout"));
    SelenideElement btnOnizlemeIadeEt = $("button[id^='mainPreviewForm:onizlemeRightTab:uiRepeat'] span[class$='iadeEt']");
    ElementsCollection lblIadeEdilecekBirim = $$("table[id='mainPreviewForm:iadeBilgileriPanelGrid'] label");
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));
    SelenideElement btntopluHavale = $("[class='ui-button-icon-left ui-icon document-charge']");

    @Step("Teslim Alınanlar sayfası aç")
    public TeslimAlinanlarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TeslimAlinanlar);
        return this;
    }

    @Step("Toplu Havale Tıklanır")
    public TeslimAlinanlarPage topluHavale(){
        btntopluHavale.click();
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
       clickJs($("[id='mainPreviewForm:kullaniciGrubuDetayKullanViewDialog']"));
        return this;
    }

    @Step("Birim alanında {birim} adlı birim seçilir")
    public TeslimAlinanlarPage havaleYapBirimDoldur(String birim) {
        txtHavaleYapBirim.openTreePanel().closeTreePanel();
        txtHavaleYapBirim.selectLov(birim);
        //txtHavaleYapBirim.type(birim).getTitleItems().filterBy(text(birim)).first().click();
        return this;
    }

    @Step("Default gereği için gönder ifadesinin geldiği görülür.")
    public TeslimAlinanlarPage secilenBirimDefaultGeregiIcinGonderGorme(){
        boolean durum = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovSecilenTable:0:selectOneMenu")).getSelectedText().equals("GEREĞİ İÇİN GÖNDER");
        Assert.assertEquals(durum,true);
        return this;
    }

    @Step("Teslim Alınanlar sayfasında evrakın geldiği kontrolu ve seçme")
    public TeslimAlinanlarPage konuyaGoreEvrakIcerikGoster(String konu) {

        tblEvraklar
                .filterBy(text("Konu: " + konu))
                .first()
                .$("[id^='mainInboxForm:inboxDataTable'] [id$='detayGosterButton']").click();

        $(By.id("mainPreviewForm:eastLayout")).waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("\"{text}\" butonu tıklanır.")
    public TeslimAlinanlarPage btnTikla(String text) {
        SelenideElement btn = $(By.xpath("descendant::*[text()='" + text + "']/ancestor::tbody[1]//button"));
        btn.click();
        return this;
    }

    @Step("Kullanici Lisesi alanında \"{kullaniciListesi}\" seçilir. ")
    public TeslimAlinanlarPage tebligEtKullaniciListesiSec(String kullaniciListesi){
        txtTebligEtKullniciListesi.selectLov(kullaniciListesi);
        return this;
    }


    @Step("Kullanici Lisesi alanında \"{kullaniciListesi}\" seçilir. ")
    public TeslimAlinanlarPage havaleYapKullaniciListesiSec(String kullaniciListesi){
        txtHavaleYapKullniciListesi.selectLov(kullaniciListesi);
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

    @Step("Havale yap")
    public TeslimAlinanlarPage havaleYap() {
        btnHavaleYap.click();
        return this;
    }

    @Step("Havale yap ekranın gelidiği görülür")
    public TeslimAlinanlarPage havaleYapEkranGeldigiGorulur(){
        boolean durum = $$(By.id("mainPreviewForm:havaleDagitimLovPanel")).size() ==1;
        Assert.assertEquals(durum,true);
        return this;
    }

    @Step("Birim, kişi, kullanıcı listesi, onaylayacak kişi ,açıklama alanları ile dosya ekle butonunun, işlem süresi alanının geldiği görülür.")
    public TeslimAlinanlarPage havaleYapAlanlarGeldigiGorme(){
        boolean durum1 = txtHavaleYapBirim.isDisplayed()==true;
        boolean durum2 = txtHavaleYapKisi.isDisplayed()==true;
        boolean durum3 = txtHavaleYapKullaniciListesi.isDisplayed()==true;
        boolean durum4 = txtHavaleYapOnaylanacakKisi.isDisplayed()==true;
        boolean durum5 = txtHavaleYapAciklama.isDisplayed()==true;
        boolean durum6 = txtHavaleYapDosyaEkle.size()==1;
        boolean durum7 = txtHavaleYapIslemSuresi.size()==1;
        Assert.assertEquals(durum1,true);
        Assert.assertEquals(durum2,true);
        Assert.assertEquals(durum3,true);
        Assert.assertEquals(durum4,true);
        Assert.assertEquals(durum5,true);
        Assert.assertEquals(durum6,true);
        Assert.assertEquals(durum7,true);
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

    @Step("Konuya göre evrak seçilir : \"{konu}\" ")
    public TeslimAlinanlarPage konuyaGoreEvrakSec(String konu) {
        tblEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }


    @Step("Evrak Önizleme geldiği görülür. ")
    public TeslimAlinanlarPage evrakOnizlemeKontrolu() {
        formEvrakOnizleme.isDisplayed();
        return this;
    }

    @Step("Evrak Önizleme \"{btnText}\" buton geldiği görülür.")
    public TeslimAlinanlarPage evrakOnizlemeButonKontrolu(String btnText) {
        SelenideElement btnEvrakOnizleme = $(By.xpath("//span[text()='" + btnText + "']/../../..//button"));
        Assert.assertEquals(btnEvrakOnizleme.isDisplayed(), true);
        return this;
    }

    @Step("Evrak Önizleme buton kontrolü. Buton Name : \"{btnText}\", Ekranda bulunuyor mu : {shoulBeDisplay} ")
    public TeslimAlinanlarPage evrakOnizlemeButonKontrolu(String btnText, boolean shoulBeDisplay) {
        SelenideElement btnEvrakOnizleme = $(By.xpath("//form[@id='mainPreviewForm']//button[.='" + btnText + "']"));
        if (shoulBeDisplay)
            Assert.assertEquals(btnEvrakOnizleme.isDisplayed(), true);
        else
            Assert.assertEquals(btnEvrakOnizleme.isDisplayed(), false);
        return this;
    }

    @Step("Evrak Önizleme \"{btnText}\" buton tıklanır.")
    public TeslimAlinanlarPage evrakOnizlemeButonTikla(String btnText) {
        SelenideElement btnEvrakOnizleme = $(By.xpath("//span[text()='" + btnText + "']/../../..//button"));
        btnEvrakOnizleme.click();
        return this;
    }

    @Step("Evrak Onizleme Kontrolu")
    public TeslimAlinanlarPage evrakOnizlemeKontrol() {
        if (formEvrakOnizleme.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
        return this;
    }

    @Step("Iade Et buton kontrolü")
    public TeslimAlinanlarPage onizlemeIadeEtKontrol() {
        Assert.assertEquals(btnOnizlemeIadeEt.isDisplayed(),true,"Iade et buton kontrolü");
        Allure.addAttachment("Iade et buton kontrolü","");
        return this;
    }

    @Step("Teslim Alınan Evrakın Iade Edilmesi")
    public TeslimAlinanlarPage onizlemeIadeEt() {
        btnOnizlemeIadeEt.click();
        return this;
    }

    @Step("Iade Edilecek Birim Kontrolü")
    public TeslimAlinanlarPage onizlemeIadeEdilecekBirimKontrolu(String birim) {
        boolean durum = lblIadeEdilecekBirim.filterBy(Condition.text(birim)).size() == 1;
        Assert.assertEquals(durum,true,"Iade Edilecek Birim Kontrolü");
        Allure.addAttachment("Iade Edilecek Birim Kontrolü","");
        return this;
    }

    @Step("Teslim Alınan Evrakın Iade Edilmesi ve Iade Et Tıklanması")
    public TeslimAlinanlarPage iadeEtIadeEt() {
        btnIadeEtIadeEt.click();
        return this;
    }

    @Step("Evrak Adedi Kontrolu: \"{evrakNo}\" ")
    public TeslimAlinanlarPage evrakAdediKontrolu(String evrakNo) {
        int evrakSayisi = tblEvraklar.filterBy(Condition.text(evrakNo)).size();
        boolean durum = tblEvraklar.filterBy(Condition.text(evrakNo)).size() == 1;
        Assert.assertEquals(durum,true, "Evrak Adedi Kontrolü");
        Allure.addAttachment(evrakNo + " nolu evrak adedi:" + evrakSayisi,"");
        return this;
    }

    @Step("Evrak Geçmiş Tab kontrolü\n")
    public TeslimAlinanlarPage tabKontrol() {
        boolean durum = tabEvrakGecmisi.filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").isDisplayed();
        Assert.assertEquals(durum,true,"Evrak Geçmiş Tab kontrolü");
        Allure.addAttachment("Evrak Geçmiş Tab"," gösterilmektedir.");
        return this;
    }

//    @Step("Evrak adedi kontrol: \"{evrakNo}\" ")
//    public TeslimAlinanlarPage evrakAdediKontrol(String evrakNo) {
//        int dosyaAdedi = tblEvraklar
//                .filterBy(Condition.text(evrakNo))
//                .size();
//        Allure.addAttachment(evrakNo, Integer.valueOf(dosyaAdedi).toString());
//        return this;
//    }

    @Step("Evrak geçmişi alanına tıklanır")
    public TeslimAlinanlarPage secilenEvrakEvrakGecmisi() {
        tabEvrakGecmisi.filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
//        $$("[id$='evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
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
        Assert.assertEquals(durum, true,"Evrak Geçmişi Kontrol");
        Allure.addAttachment("Teslim Alinan:" + teslimAlinan + " İşlem Süreci:" + islemSureci + " Tarih:" +  tarih , "");
        takeScreenshot();
        return this;
    }

    @Step("Evrak seç")
    public TeslimAlinanlarPage evrakSec() {
        tblIlkEvrak.click();
        return this;
    }

    @Step("Evrak üzerindeki \"İçerik Göster\"e tıklanır")
    public TeslimAlinanlarPage ilkEvrakIcerikGoster(){
    $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton")).click();
        return this;
    }

    @Step("Teslim Alınanlar Evraklar listesinden İki evrak seçilir")
    public TeslimAlinanlarPage ilkIkiEvrakCheckBoxSec(){
        if (tblEvraklar.get(0).$$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']").size()==0)
            tblEvraklar.get(0).$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        if (tblEvraklar.get(1).$$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']").size()==0)
            tblEvraklar.get(1).$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }

    @Step("Birime havale alanında \"{birim}\" seçilir")
    public TeslimAlinanlarPage birimeHavaleDoldur(String birim) {
        cmbBirimeHavale.selectLov(birim);
        Allure.addAttachment("Birimin Sonuçlarda görüntülendiği görülür", "");
        return this;
    }

    @Step("Birime havale alanında girilen \"{description}\" 'ın görüntülenmeme kontrolu: {birim}")
    public TeslimAlinanlarPage birimeHavaleAlanindaGoruntulenmemeKontrolu(String birim, String description) {

        boolean selectable = comboLov(cmbBirimeHavaleBy).isLovValueSelectable(birim);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + birim + ": Birimin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + birim + ": Birimin görüntülenmediği görülür.");
        Allure.addAttachment("MyCombolov alanında " + birim + ": Birimin görüntülenmediği görülür.", "");
        return this;
    }
}
