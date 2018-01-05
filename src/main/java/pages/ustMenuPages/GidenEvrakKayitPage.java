/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Giden Evrak Kayit" sayfasındaki metotları içerir
 * Yazan: Sezai Çelik
 ****************************************************/

package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GidenEvrakKayitPage extends MainPage {

    //region Elements

    // gidenEvrakDefterKaydiForm:evrakBilgileriList:11:j_idt14590
    SelenideElement cmbGeregiSecimTipi = $(By.xpath("//select[starts-with(@id,'gidenEvrakDefterKaydiForm:evrakBilgileriList:11:j_idt')]"));
    BelgenetElement cmbGeregi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    SelenideElement cmbBilgiSecimTipi = $(By.xpath("//select[starts-with(@id,'gidenEvrakDefterKaydiForm:evrakBilgileriList:12:j_idt')]"));
    BelgenetElement cmbBilgi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='bilgiLov:LovText']");
    By cmbGeregiBy = By.cssSelector("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    By cmbBilgiBy = By.cssSelector("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='bilgiLov:LovText']");
    SelenideElement btnGeregiDelete = $("button[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList:11:geregiLov:LovSecilenTable'] span[class$='delete-icon']");

    BelgenetElement comboBilgi = comboLov("[id$='bilgiLov:LovText']");
    BelgenetElement comboKaldiralacakKlasörler = comboLov("[id$='kaldirilacakKlasorlerLov:LovText']");
    BelgenetElement comboKonuKodu = comboLov("[id$='konuKoduLov:LovText']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo2']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id$='evrakDili']");
    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    SelenideElement ustYazi = $(By.xpath("//input[@class='ustYaziUploadClass']"));
    SelenideElement lblEklenenPdfUstYazi = $("[id$='eklendiYazisi'] label");
    SelenideElement cmbEvrakBilgileriListIvedilik = $("[id$='ivedilik']");
    SelenideElement dateTxtMiat = $("[id$='miatCalendar_input']");

    //Evrak Ekleri tabi
    SelenideElement btnEvrakEkleri = $(By.id("gidenEvrakDefterKaydiForm:gedkEvrakEkleriListesiPanel_header"));
    SelenideElement btnFizikselEkEkle = $("a[href='#gidenEvrakDefterKaydiForm:evrakEkTabView:aciklamaEkleTab']");
    SelenideElement txtEvrakFizikselEkTabViewEkMetni = $(By.id("gidenEvrakDefterKaydiForm:evrakEkTabView:aciklamaTextArea"));
    SelenideElement btnEvrakFizikselEkEkle = $(By.id("gidenEvrakDefterKaydiForm:evrakEkTabView:aciklamaEkleButton"));

    //İlgili Bilgileri
    SelenideElement btnİlgiEkleri = $(By.id("gidenEvrakDefterKaydiForm:gedkIlgiBilgileriPanel_header"));
    SelenideElement btnMetinEkEkle = $("a[href='#gidenEvrakDefterKaydiForm:ilgiIslemleriTabView:aciklamaEkleTab']");
    SelenideElement btnIlgiEkleriEkle = $(By.id("gidenEvrakDefterKaydiForm:ilgiIslemleriTabView:aciklamaEkleButton"));
    SelenideElement txtIlgiEkleriMetinEkTabViewEkMetni = $(By.id("gidenEvrakDefterKaydiForm:ilgiIslemleriTabView:aciklamaTextArea"));

    SelenideElement btnKaydet = $(By.id("gedkKaydetButton"));

    SelenideElement popUpKaydetEvet = $(By.id("kaydetConfirmForm:kaydetEvetButton"));
    SelenideElement popUpEvrakDefterBasarili = $(By.id("gidenEvrakDefterKaydiBasariliDialogId"));
    SelenideElement popUpEvrakDefterBasariliKapat = $(By.id("gidenEvrakDefterKaydiBasarili:vazgecButton"));
    //endregion

    @Step("Giden Evrak Kayit sayfasını aç")
    public GidenEvrakKayitPage openPage(){
        ustMenu(UstMenuData.EvrakIslemleri.GidenEvrakKayit);
        return this;
    }

    @Step("Gereği seçim tipi seç")
    public GidenEvrakKayitPage geregiSecimTipiSec(String geregi) {
        cmbGeregiSecimTipi.sendKeys(Keys.SHIFT);
        cmbGeregiSecimTipi.selectOption(geregi);
        return this;
    }

    @Step("Gereği seçim tipi alanında \"{kisiKurum}\" seç")
    public GidenEvrakKayitPage geregiSecimTipiSecByText(String kisiKurum) {
        cmbGeregiSecimTipi.selectOption(kisiKurum);
        return this;
    }


    @Step("Gereği {description} doldur: | {geregi}")
    public GidenEvrakKayitPage geregiDoldur(String geregi, String description) {

        cmbGeregi.selectLov(geregi);

        /*System.out.println("title: " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeregi.lastSelectedLovDetailText());*/
        return this;
    }

    @Step("Gereği doldur")
    public GidenEvrakKayitPage geregiDoldur(String geregiAdSoyad, Boolean clearAfter) {

        cmbGeregi.selectLov(geregiAdSoyad);

        /*System.out.println("title: " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeregi.lastSelectedLovDetailText());*/

        cmbGeregi.clearAllSelectedItems();
        return this;
    }

    @Step("Kişinin Geregi alanında görüntülenmeme kontrolu")
    public GidenEvrakKayitPage geregiAlanindaGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");
        return this;
    }

    @Step("Kurumun Geregi alanında görüntüleme kontrolu")
    public GidenEvrakKayitPage geregiAlanindaDegerKontrolu(String aranacakDeger, Boolean shouldBeExist) {

        Assert.assertEquals(comboLov(cmbGeregiBy).isLovValueSelectable(aranacakDeger), shouldBeExist);
        return this;
    }

    @Step("Kişinin Geregi alanında görüntülenme kontrolu")
    public GidenEvrakKayitPage geregiAlanindaGoruntulenmeKontrolu(String adSoyad) {

        cmbGeregi.selectLov(adSoyad);
        /*System.out.println("Gelen title:     " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);
        Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);*/
        cmbGeregi.getSelectedTitles().last().shouldHave(text(adSoyad));
        return this;
    }

    @Step("Bilgi seçim tipi alanında \"{bilgiSecimTipi}\" seç")
    public GidenEvrakKayitPage bilgiSecimTipiSec(String bilgiSecimTipi) {
        cmbBilgiSecimTipi.sendKeys(Keys.SHIFT);
        cmbBilgiSecimTipi.selectOption(bilgiSecimTipi);
        return this;
    }

    @Step("Bilgi seçim tipi alanında \"{bilgiSecimTipi}\" seç")
    public GidenEvrakKayitPage bilgiSecimTipiSecByText(String bilgiSecimTipi) {
        cmbBilgiSecimTipi.selectOption(bilgiSecimTipi);
        return this;
    }

    @Step("Bilgi doldur")
    public GidenEvrakKayitPage bilgiDoldur(String geregiAdSoyad) {

        cmbBilgi.selectLov(geregiAdSoyad);
        /*System.out.println("title: " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbBilgi.lastSelectedLovDetailText());*/

        return this;
    }

    @Step("Bilgi doldur")
    public GidenEvrakKayitPage bilgiDoldur(String geregiAdSoyad, Boolean clearAfter) {

        cmbBilgi.selectLov(geregiAdSoyad);
        /*System.out.println("title: " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbBilgi.lastSelectedLovDetailText());*/

        cmbBilgi.clearAllSelectedItems();

        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenmeme kontrolu")
    public GidenEvrakKayitPage bilgiAlanindaGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");

        return this;
    }

    //Ad, soyad diye ayrı girilmesi gerekir. Çünkü soyad büyük harfle geliyor.
    @Step("Kişinin Bilgi alanında görüntülenme kontrolu")
    public GidenEvrakKayitPage bilgiAlanindaGoruntulenmeKontrolu(String ad, String soyad) {

        String adSoyad = ad + " " + soyad.toUpperCase();
        cmbBilgi.selectLov(adSoyad);
        /*System.out.println("Gelen title:     " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);*/
        /*Assert.assertEquals(cmbBilgi.lastSelectedLovTitleText().contains(adSoyad), true);*/

        cmbBilgi.getSelectedTitles().last().shouldHave(text(adSoyad));
        return this;
    }


    @Step("Kurumun Geregi alanında görüntüleme kontrolu")
    public GidenEvrakKayitPage bilgiAlanindaDegerKontrolu(String aranacakDeger, Boolean shouldBeExist) {

        Assert.assertEquals(cmbBilgi.isLovValueSelectable(aranacakDeger), shouldBeExist);
        return this;
    }

    @Step("Seçilen gereği sil")
    public GidenEvrakKayitPage secilenGeregiSil() {
        btnGeregiDelete.shouldBe(Condition.visible);
        clickJs(btnGeregiDelete);
        return this;
    }

    @Step("Panel kapat")
    public GidenEvrakKayitPage panelKapat(Boolean kaydet) {
        $(By.xpath("//div[@id='mainTaskBar']//span[text()='[Giden Evrak Kayıt]']"))
                .contextClick();

        if (kaydet)
            $(By.id("kapatKaydetEvetButton")).click();
        else
            $(By.id("kapatKaydetHayirButton")).click();

        return this;
    }

    public GidenEvrakKayitPage konuKoduDoldur(String konuKodu) throws InterruptedException {
        comboKonuKodu.selectLov(konuKodu);
        return this;
    }

    public GidenEvrakKayitPage evrakTuruSec(String evrakTuru) {
        cmbEvrakBilgileriListEvrakTuru.selectOption(evrakTuru);
        return this;
    }

    public GidenEvrakKayitPage evrakDiliSec(String evrakDili) {
        cmbEvrakBilgileriListEvrakDili.selectOption(evrakDili);
        return this;
    }

    @Step("Evrak Tarihi doldur")
    public GidenEvrakKayitPage evrakTarihiDoldur(String evrakTarihi) {
        dateTxtEvrakBilgileriListEvrakTarihi.clear();
        dateTxtEvrakBilgileriListEvrakTarihi.sendKeys(evrakTarihi);
        return this;
    }

    public GidenEvrakKayitPage gizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakBilgileriListGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    @Step("Kişi kurum seç")
    public GidenEvrakKayitPage kisiKurumSec(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOptionByValue(kisiKurum);
        return this;
    }

    public GidenEvrakKayitPage evrakBilgileriUstYaziEkle(String path) throws InterruptedException {
        uploadFile(ustYazi, path);
        //ustYaziUploadFile(path);
        return this;
    }

    @Step("PDF Ust Yazi adi kontrol : \"{ustYaziAdi}\" ")
    public GidenEvrakKayitPage ustYaziPdfAdiKontrol(String ustYaziAdi) {
        String text = lblEklenenPdfUstYazi.text();
        System.out.println(text);
        Assert.assertEquals(text.contains(ustYaziAdi), true);
        return this;
    }

    @Step("İvedilik alanından \"{ivedilik}\" Seç")
    public GidenEvrakKayitPage ivedilikSec(String ivedilik) {
        cmbEvrakBilgileriListIvedilik.selectOption(ivedilik);
        return this;
    }

    @Step("Miat alnına \"{miatTarihi}\" girilir")
    public GidenEvrakKayitPage miatDoldur(String miatTarihi) {
        dateTxtMiat.clear();
        dateTxtMiat.sendKeys(miatTarihi);
        return this;
    }

    @Step("Kaldıralacak Klasör alanından \"{kaldirilacakKlasor}\" seçilir")
    public GidenEvrakKayitPage kaldiralacakKlasorDoldur(String kaldirilacakKlasor) throws InterruptedException {
        comboKaldiralacakKlasörler.selectLov(kaldirilacakKlasor);
        return this;
    }

    @Step("Evrak Ekleri filtre ac")
    public GidenEvrakKayitPage ekBilgiFiltreAc() throws InterruptedException {
        clickJs(btnEvrakEkleri);
        return this;
    }

    @Step("Fiziksel Ek metin alanınıa \"{evrakEkTabViewEkMetni}\" girilir")
    public GidenEvrakKayitPage evrakEkTabFizikselEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtEvrakFizikselEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    @Step("Fiziksel Ek Ekle buton tıkla")
    public GidenEvrakKayitPage fizikselEkTabViewAciklamaEkle() {
        btnEvrakFizikselEkEkle.click();
        return this;
    }

    @Step("Fiziksel Ek Ekle buton tıkla")
    public GidenEvrakKayitPage ekBilgiFizikselEkEkle() throws InterruptedException {
        clickJs(btnFizikselEkEkle);
        return this;
    }

    @Step("İlgi Ekleri filtre ac")
    public GidenEvrakKayitPage ilgiEkleriFiltreAc() throws InterruptedException {
        clickJs(btnİlgiEkleri);
        return this;
    }

    @Step("İlgi Ekleri Ek Metin alanına \"{evrakEkTabViewEkMetni}\" girilir")
    public GidenEvrakKayitPage ilgiEkleriMetinEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtIlgiEkleriMetinEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    @Step("Metin Ekle tabı açılır")
    public GidenEvrakKayitPage ilgiEkleriMetinTabAc() {
        btnMetinEkEkle.click();
        return this;
    }

    @Step("Metin Ekle buton")
    public GidenEvrakKayitPage ilgiEkleriMetinEkle() throws InterruptedException {
        clickJs(btnIlgiEkleriEkle);
        return this;
    }

    @Step("Kaydet buton")
    public GidenEvrakKayitPage kaydet() throws InterruptedException {
        btnKaydet.click();
        return this;
    }

    @Step("Pop up kontrol")
    public GidenEvrakKayitPage popUpkaydetEvet() throws InterruptedException {
        if (popUpKaydetEvet.isDisplayed())
            popUpKaydetEvet.click();
        return this;
    }

    @Step("Başarılı Pop up kontrol")
    public String popUpBasariliKapat() throws InterruptedException {
        popUpEvrakDefterBasarili.shouldBe(Condition.visible);
        String mesaj4 = "Evrak başarıyla kaydedilmiştir.";
        popUpEvrakDefterBasarili.getText().contains(mesaj4);

        String evrakNo = getIntegerInText(By.id("gidenEvrakDefterKaydiBasariliDialogId"));
        popUpEvrakDefterBasariliKapat.click();
        return evrakNo;
    }

    @Step("Konu alnına \"{konu}\" girilir")
    public GidenEvrakKayitPage konuDoldur(String konu){
        $("[id$='konuTextArea']").setValue(konu);
        return this;
    }


}