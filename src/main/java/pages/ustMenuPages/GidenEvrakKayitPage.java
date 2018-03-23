/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Giden Evrak Kayit" sayfasındaki metotları içerir
 * Yazan: Sezai Çelik
 ****************************************************/

package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class GidenEvrakKayitPage extends MainPage {

    //region Elements

    // gidenEvrakDefterKaydiForm:evrakBilgileriList:11:j_idt14590
    SelenideElement cmbGeregiSecimTipi = $(By.xpath("//form[@id='gidenEvrakDefterKaydiForm']//label[normalize-space(text())='Gereği Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));
    BelgenetElement cmbGeregi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    SelenideElement cmbBilgiSecimTipi = $(By.xpath("//form[@id='gidenEvrakDefterKaydiForm']//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));
    BelgenetElement cmbBilgi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='bilgiLov:LovText']");
    By cmbGeregiBy = By.cssSelector("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    By cmbBilgiBy = By.cssSelector("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='bilgiLov:LovText']");
    SelenideElement btnGeregiDelete = $("button[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList:11:geregiLov:LovSecilenTable'] span[class$='delete-icon']");
    BelgenetElement cmbGeregiDoldur = comboLov(By.id("gidenEvrakDefterKaydiForm:evrakBilgileriList:11:geregiLov:LovText"));
    BelgenetElement cmbBilgiDoldur =  comboLov(By.id("gidenEvrakDefterKaydiForm:evrakBilgileriList:12:bilgiLov:LovText"));

    BelgenetElement comboBilgi = comboLov("[id$='bilgiLov:LovText']");
    BelgenetElement comboKaldiralacakKlasörler = comboLov("[id$='kaldirilacakKlasorlerLov:LovText']");
    BelgenetElement comboKonuKodu = comboLov("[id$='konuKoduLov:LovText']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo2']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id$='evrakDili']");
    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    SelenideElement ustYazi = $(By.xpath("//input[@id='gidenEvrakDefterKaydiForm:ustYaziForm:gedkUploadButton_input']"));
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

    public static String clearHorizantalTabChars(String str) {
        String ret = str;
        char[] horizantalTabChars = new char[]{0x9};
        char[] newChars = new char[]{' ', ' '};
        for (int i = 0; i < horizantalTabChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{horizantalTabChars[i]}), new String(new char[]{newChars[i]}));
        }
        return ret;
    }

    @Step("Giden Evrak Kayit sayfasını aç")
    public GidenEvrakKayitPage openPage() {
        ustMenu(UstMenuData.EvrakIslemleri.GidenEvrakKayit);
        return this;
    }

    @Step("Bilgi alanında Birimin güncel bilgileriyle geldiği görülür.")
    public GidenEvrakKayitPage bilgiGeldigiGorme(String birimAdi) {
        boolean durum = cmbBilgiDoldur.type(birimAdi).getTitleItems().filterBy(Condition.text(birimAdi)).size()==1;
        Assert.assertEquals(durum,true);
        cmbBilgiDoldur.closeTreePanel();
        return this;
    }
    @Step("Gereği alanında  Birimin güncel bilgileriyle geldiği görülür.")
    public GidenEvrakKayitPage geregiGeldigiGorme(String birimAdi) {
        boolean durum = cmbGeregiDoldur.type(birimAdi).getTitleItems().filterBy(Condition.text(birimAdi)).size()==1;
        Assert.assertEquals(durum,true);
        cmbGeregiDoldur.closeTreePanel();
        return this;
    }

    @Step("Gereği seçim tipi seç")
    public GidenEvrakKayitPage geregiSecimTipiSec(String geregi) {
        cmbGeregiSecimTipi.sendKeys(Keys.SHIFT);
        cmbGeregiSecimTipi.selectOption(geregi);
        return this;
    }

    @Step("Gereği seçim tipi alanında \"{geregiSecimTipi}\" seç")
    public GidenEvrakKayitPage geregiSecimTipiSecByText(String geregiSecimTipi) {
        cmbGeregiSecimTipi.selectOption(geregiSecimTipi);
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

        cmbGeregi
                .type(geregiAdSoyad)
                .getTitleItems()
                .first()
                .click();

        cmbGeregi.closeTreePanel();

        cmbGeregi.clearAllSelectedItems();
        return this;
    }

    @Step("Gereği alanında girilen \"{description}\" 'ın görüntülenmeme kontrolu: {geregi}")
    public GidenEvrakKayitPage geregiAlanindaGoruntulenmemeKontrolu(String geregi, String description) {

        boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(geregi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + geregi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + geregi + ": Kişinin görüntülenmediği görülür.");
        Allure.addAttachment("MyCombolov alanında " + geregi + ": görüntülenmediği görülür.", "");
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
    public GidenEvrakKayitPage bilgiDoldur(String bilgiAdSoyad) {

        cmbBilgi.selectLov(bilgiAdSoyad);
        /*System.out.println("title: " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbBilgi.lastSelectedLovDetailText());*/

        return this;
    }

    @Step("Bilgi {description} doldur: | {bilgi}")
    public GidenEvrakKayitPage bilgiDoldur(String bilgi, String description) {

        cmbGeregi.selectLov(bilgi);

        /*System.out.println("title: " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeregi.lastSelectedLovDetailText());*/
        return this;
    }

    @Step("Bilgi doldur")
    public GidenEvrakKayitPage bilgiDoldur(String geregiAdSoyad, Boolean clearAfter) {

        cmbBilgi
                .type(geregiAdSoyad)
                .getTitleItems()
                .first()
                .click();

        cmbBilgi.closeTreePanel();
        cmbBilgi.clearAllSelectedItems();

        return this;
    }

    @Step("Bilgi temizle")
    public GidenEvrakKayitPage bilgiTemizle() {
        cmbBilgi.clearAllSelectedItems();

        return this;
    }

    @Step("Bilgi alanında girilen \"{description}\" 'ın görüntülenmeme kontrolu: {bilgi}")
    public GidenEvrakKayitPage bilgiAlanindaGoruntulenmemeKontrolu(String bilgi, String description) {

        boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(bilgi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + bilgi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + bilgi + ": görüntülenmediği görülür.");

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
        //$(By.xpath("//div[@id='mainTaskBar']//span[text()='[Giden Evrak Kayıt]']")).contextClick();

        SelenideElement closeButton = $$(By.xpath("//span[@class='ui-dialog-title' and text()='Giden Evrak Kayıt']/..//span[@class='ui-icon ui-icon-closethick']")).last().waitUntil(visible, 5000);
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", closeButton);
        closeButton.click();

        if (kaydet)
            $(By.id("kapatKaydetEvetButton")).click();
        else
            $(By.id("kapatKaydetHayirButton")).click();

        return this;
    }

    public GidenEvrakKayitPage konuKoduDoldur(String konuKodu) {
        comboKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("Evrak Turu \"{evrakTuru}\" seçilir")
    public GidenEvrakKayitPage evrakTuruSec(String evrakTuru) {
        cmbEvrakBilgileriListEvrakTuru.selectOption(evrakTuru);
        return this;
    }

    @Step("Evrak Turu alanında \"{icerik}\" olduğu görülür.")
    public GidenEvrakKayitPage evrakTuruIcerikKontrolu(String icerik) {
        boolean sonuc = cmbEvrakBilgileriListEvrakTuru.innerText().contains(icerik);
        Assert.assertEquals(true, sonuc);
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

    @Step("Gizlilik Derecesi alanında \"{gizlilikDerecesi}\" seçilir.")
    public GidenEvrakKayitPage gizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakBilgileriListGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    @Step("Gizlilik Derecesi içerik kontrol.")
    public GidenEvrakKayitPage gizlilikDerecesiIcerikKontrol() {
        String icerik = cmbEvrakBilgileriListGizlilikDerecesi.innerText();
        String text = clearHorizantalTabChars(icerik);
        System.out.println(text);
        Allure.addAttachment("İvedilik alanı", text);
        return this;
    }

    @Step("Kişi kurum seç")
    public GidenEvrakKayitPage kisiKurumSec(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOptionByValue(kisiKurum);
        return this;
    }

    @Step("Üst yazi \"{path}\" ekle")
    public GidenEvrakKayitPage evrakBilgileriUstYaziEkle(String path) {
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

    @Step("İvedilik alanı içerik kontrolü")
    public GidenEvrakKayitPage ivedilikIcerikKontrol() {
        String icerik = cmbEvrakBilgileriListIvedilik.innerText();
        String text = clearHorizantalTabChars(icerik);
        System.out.println(text);
        Allure.addAttachment("İvedilik alanı", text);
        return this;
    }

    @Step("Miat alnına \"{miatTarihi}\" girilir")
    public GidenEvrakKayitPage miatDoldur(String miatTarihi) {
        dateTxtMiat.clear();
        dateTxtMiat.sendKeys(miatTarihi);
        return this;
    }

    @Step("Kaldıralacak Klasör alanından \"{kaldirilacakKlasor}\" seçilir")
    public GidenEvrakKayitPage kaldiralacakKlasorDoldur(String kaldirilacakKlasor) {
        comboKaldiralacakKlasörler.selectLov(kaldirilacakKlasor);
        return this;
    }

    @Step("Evrak Ekleri filtre ac")
    public GidenEvrakKayitPage ekBilgiFiltreAc() {
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
    public GidenEvrakKayitPage ekBilgiFizikselEkEkle() {
        clickJs(btnFizikselEkEkle);
        return this;
    }

    @Step("İlgi Ekleri filtre ac")
    public GidenEvrakKayitPage ilgiEkleriFiltreAc() {
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
    public GidenEvrakKayitPage ilgiEkleriMetinEkle() {
        clickJs(btnIlgiEkleriEkle);
        return this;
    }

    @Step("Kaydet buton")
    public GidenEvrakKayitPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Pop up kontrol")
    public GidenEvrakKayitPage popUpkaydetEvet() {
        if (popUpKaydetEvet.isDisplayed())
            popUpKaydetEvet.click();
        return this;
    }

    @Step("Gereği alanını temizle.")
    public GidenEvrakKayitPage geregiTemizle() {
        cmbGeregi.clearAllSelectedItems();
        return this;
    }

    @Step("Başarılı Pop up kontrol")
    public String popUpBasariliKapat() {
        popUpEvrakDefterBasarili.shouldBe(Condition.visible);
        String mesaj4 = "Evrak başarıyla kaydedilmiştir.";
        popUpEvrakDefterBasarili.getText().contains(mesaj4);

        String evrakNo = getNumberFromText(By.id("gidenEvrakDefterKaydiBasariliDialogId"));
        popUpEvrakDefterBasariliKapat.click();
        return evrakNo;
    }

    @Step("Konu alnına \"{konu}\" girilir")
    public GidenEvrakKayitPage konuDoldur(String konu) {
        $("[id$='konuTextArea']").setValue(konu);
        return this;
    }

    @Step("Gereği alanında Birimin geldiği ve seçilemediği kontrolu - {description} : {birim}")
    public GidenEvrakKayitPage geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(String birim, String description) {

        int gorunurSecilemezBirimSize = comboLov(cmbGeregiBy).type(birim).getSelectableItems().size();
        Assert.assertEquals(gorunurSecilemezBirimSize == 0, true, "Birimin geldiği ve seçilemediği görülür: " + birim);
        comboLov(cmbGeregiBy).closeTreePanel();
        System.out.println("Birimin geldiği ve seçilemediği görülür: " + birim);
        Allure.addAttachment("Birimin geldiği ve seçilemediği görülür: " + birim, "");

        return this;
    }

    @Step("Bilgi alanında Birimin geldiği ve seçilemediği kontrolu - {description} : {birim}")
    public GidenEvrakKayitPage bilgiAlanindaBiriminGeldigiVeSecilemedigiKontrolu(String birim, String description) {

        int gorunurSecilemezBirimSize = comboLov(cmbBilgiBy).type(birim).getSelectableItems().size();
        Assert.assertEquals(gorunurSecilemezBirimSize == 0, true, "Birimin geldiği ve seçilemediği görülür: " + birim);
        comboLov(cmbBilgiBy).closeTreePanel();
        System.out.println("Birimin geldiği ve seçilemediği görülür: " + birim);
        Allure.addAttachment("Birimin geldiği ve seçilemediği görülür: " + birim, "");

        return this;
    }

    @Step("Gereği alanında Birimin geldiği ve seçilebildiği kontrolu - {description} : {birim}")
    public GidenEvrakKayitPage geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(String birim, String description) {

        cmbGeregi.selectLov(birim);

        System.out.println("Birimin geldiği ve seçilebildiği görülür: " + birim);
        Allure.addAttachment("Birimin geldiği ve seçilebildiği görülür: " + birim, "");

        return this;
    }

    @Step("Bilgi alanında Birimin geldiği ve seçilebildiği kontrolu - {description} : {birim}")
    public GidenEvrakKayitPage bilgiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(String birim, String description) {

        cmbBilgi.selectLov(birim);

        System.out.println("Birimin geldiği ve seçilebildiği görülür: " + birim);
        Allure.addAttachment("Birimin geldiği ve seçilebildiği görülür: " + birim, "");

        return this;
    }
}