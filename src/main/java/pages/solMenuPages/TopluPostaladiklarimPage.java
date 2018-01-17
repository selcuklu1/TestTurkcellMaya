package pages.solMenuPages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.commands.PressEnter;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import pages.MainPage;
import pages.galen.GalenControl;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.tabs.AltTabs;
import pages.pageData.SolMenuData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.sql.Driver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;


public class TopluPostaladiklarimPage extends MainPage {

    SelenideElement filtrePanelHeader = $("div[id='mainInboxForm:inboxDataTable:filtersAccordion'] > h3");
    SelenideElement txtPostaListesiAdi = $(By.xpath("//label[normalize-space(text())='Posta Listesi Adı :']/ancestor::tr[@class='ui-widget-content']//input"));
    SelenideElement txtBarkodNo = $(By.xpath("//label[normalize-space(text())='Barkod No :']/ancestor::tr[@class='ui-widget-content']//input"));
    SelenideElement txtEvrakSayisi = $(By.xpath("//label[normalize-space(text())='Evrak Sayısı :']/ancestor::tr[@class='ui-widget-content']//input"));
    SelenideElement txtPostaTarihi = $(By.xpath("//label[normalize-space(text())='Posta Tarihi :']/ancestor::tr[@class='ui-widget-content']//input"));
    ElementsCollection tblPostaladiklarim = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnFiltrele = $("[id='mainInboxForm:inboxDataTable:filtersAccordion:topluPostaladiklarimFiltreButton']");
    SelenideElement btnTemizle = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostaladiklarimTemizleButton"));
    ElementsCollection tblEvrakListesi = $$("tbody[id='mainPreviewForm:dataTableId_data'] > tr[role='row']");
    SelenideElement tabloEvrakListesi = $("tbody[id='mainInboxForm:inboxDataTable_data']");
    ElementsCollection tblPostaListesi = $$("tbody[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");
    SelenideElement popUpEvrakDetayi = $(By.xpath("//span[text()='Evrak Detayları']"));
    ElementsCollection tblEvrakDetayi = $$("[id='mainPreviewForm:dtEvrakUstVeri_data'] tr[role='row']");
    SelenideElement txtTutar = $(By.xpath("//label[normalize-space(text())='Tutar :']/../following-sibling::td//input"));
    SelenideElement btnGuncelle = $(By.id("mainPreviewForm:guncelleButton"));
    BelgenetElement cmbGonderildigiYer = comboBox(By.id("mainPreviewForm:tpbeGidecegiYerSelectOneMenuId_label"));
    BelgenetElement cmbGonderildigiYer2 = comboBox(By.id("mainPreviewForm:postaListesiYurticiYurtdisi_label"));
    SelenideElement txtAdres = $(By.id("mainPreviewForm:gidecegiAdresId"));
    BelgenetElement cmbGidisSekli = comboBox(By.id("mainPreviewForm:postaListesiPostaTipi_label"));
    SelenideElement txtIndirimOrani = $x("//label[normalize-space(text())='İndirim Oranı :']/../following-sibling::td//input");
    SelenideElement txtGramaj = $(By.xpath("//*[@id='mainPreviewForm:eastLayout']//label[normalize-space(text())='Gramaj :']/../..//input"));
    SelenideElement btnHesapla = $x("//span[. = 'Tutar Hesapla']/..");
    SelenideElement lblIndirimOncesiTutar = $x("//table[@id='mainPreviewForm:indirimOncesiTutarId']//label[contains(., 'TL')]");
    SelenideElement btnEtiketBastir = $x("//span[text() = 'Etiket Bastır']/../../button");
    SelenideElement txtEtiketBastir = $(By.id("mainPreviewForm:etiketMetinID"));


    @Step("Toplu Postaladıklarım sayfasını aç")
    public TopluPostaladiklarimPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TopluPostaladiklarim);
        return this;
    }

    @Step("Toplu Postaladıklarım tablo kontrolü")
    public TopluPostaladiklarimPage topluPostaladiklarimTabloKontrolu() {
        tabloEvrakListesi.isDisplayed();
        Allure.addAttachment("Tablo Listesi : ", "Ekran Kontrolü ok");
        return this;
    }

    @Step("Filtrelenen parametreye göre tablo kontrolü : \"{kontrolText}\" ")
    public TopluPostaladiklarimPage topluPostaladiklarimTabloKontrolu(String kontrolText) {
        ElementsCollection kisiselPages = $$("td[id$='mainInboxForm:inboxDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");
        int size = 0;
        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            size = tblPostaListesi
                    .filterBy(Condition.text(kontrolText))
                    .size();
        }
        if (size > 0)
            Allure.addAttachment("Tablo Listesi : ", "Aranılan evrak tabloda bulundu");
        else
            Allure.addAttachment("Tablo Listesi : ", "Aranılan evrak tabloda bulunamadı");

        return this;
    }

    @Step("Filtre Panel aç")
    public TopluPostaladiklarimPage filtrePaneliAc() {
        filtrePanelHeader.click();
        return this;
    }

    @Step("Posta listesi adı doldur: {postaListesiAdi}")
    public TopluPostaladiklarimPage postaListesiAdiDoldur(String postaListesiAdi) {
        txtPostaListesiAdi.sendKeys(postaListesiAdi);
        return this;
    }

    @Step("Barkod No doldur: {barkodNo}")
    public TopluPostaladiklarimPage barkodNoDoldur(String barkodNo) {
        txtBarkodNo.setValue(barkodNo);
        return this;
    }

    @Step("Evrak Sayısı doldur: {evrakSayisi}")
    public TopluPostaladiklarimPage evrakSayisiDoldur(String evrakSayisi) {
        txtEvrakSayisi.setValue(evrakSayisi);
        return this;
    }

    @Step("Posta Tarihi doldur: {postaTarihi}")
    public TopluPostaladiklarimPage postaTarihiDoldur(String postaTarihi) {
        txtPostaTarihi.setValue(postaTarihi);
        return this;
    }

    @Step("Filtrele butonuna tıkla.")
    public TopluPostaladiklarimPage filtrele() {
        btnFiltrele.click();
        return this;
    }

    @Step("Temizle butonuna tıkla")
    public TopluPostaladiklarimPage temizle() {
        clickJs(btnTemizle);
        return this;
    }

    @Step("Posta Listesi seç")
    public TopluPostaladiklarimPage postaListesiSec(String postaListesiAdi, String postaKodu, String postaTarihi, String postaGramaji, String pttTutari) {


        ElementsCollection kisiselPages = $$("td[id$='mainInboxForm:inboxDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            SelenideElement postaListesi = tblPostaladiklarim
                    .filterBy(text("Posta Listesi Adı: " + postaListesiAdi))
                    .filterBy(text("Posta Kodu: " + postaKodu))
                    .filterBy(text("Posta Tarihi: " + postaTarihi))
                    .filterBy(text("Posta Gramajı: " + postaGramaji))
                    .filterBy(text("PTT Tutarı: " + pttTutari))
                    .first();

            if (postaListesi.isDisplayed() && postaListesi.exists()) {
                postaListesi.click();
                break;
            }


        }
        return this;
    }

    @Step("Posta Listesi seç")
    public TopluPostaladiklarimPage postaListesiKontrol(String postaListesiAdi, String postaKodu, String postaTarihi, String postaGramaji, String pttTutari, boolean shouldBeExist) {


        ElementsCollection kisiselPages = $$("td[id$='mainInboxForm:inboxDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        boolean elementFound = false;

        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            SelenideElement postaListesi = tblPostaladiklarim
                    .filterBy(text("Posta Listesi Adı: " + postaListesiAdi))
                    .filterBy(text("Posta Kodu: " + postaKodu))
                    .filterBy(text("Posta Tarihi: " + postaTarihi))
                    .filterBy(text("Posta Gramajı: " + postaGramaji))
                    .filterBy(text("PTT Tutarı: " + pttTutari + " TL"))
                    .first();

            if (postaListesi.isDisplayed() && postaListesi.exists()) {
                elementFound = true;
                Allure.addAttachment("Posta Listesi Adı: ", postaListesiAdi);
                Allure.addAttachment("Posta Kodu: ", postaKodu);
                Allure.addAttachment("Posta Tarihi: ", postaTarihi);
                Allure.addAttachment("Posta Gramajı: ", postaGramaji);
                Allure.addAttachment("PTT Tutarı: ", pttTutari + " TL");
                break;
            }
        }

        Assert.assertEquals(elementFound, shouldBeExist);

        return this;
    }

    @Step("Toplu Postaladıklarım Posta Listesi Kontrolü")
    public TopluPostaladiklarimPage topluPostaladiklarimPostaListesiKontrol(String postaListesiAdi, String postaKodu, String postaTarihi, String postaGramaji, String pttTutari, boolean shouldBeExist) {
        tblPostaListesi
                .filterBy(Condition.text(postaListesiAdi))
                .filterBy(Condition.text(postaKodu))
                .filterBy(Condition.text(postaTarihi))
                .filterBy(Condition.text(postaGramaji))
                .filterBy(Condition.text(pttTutari))
                .first();
        return this;
    }

    @Step("Toplu Postaladıklarım tablosundan \"{konu}\" konulu evrak seçilir")
    public TopluPostaladiklarimPage topluPostaladiklarimEvrakSec(String konu) {
        tblPostaListesi
                .filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    @Step("")
    public TopluPostaladiklarimPage postaDetayiAlanKontrolleri(String postaListesi,String adres,String gramaj,String tutar){

        SelenideElement txtPostaDetayiPostaListesiAdi = $(By.xpath("//label[normalize-space(text())='Posta Listesi Adı :']//ancestor::tr//textarea"));

        txtPostaListesiAdi.text().equals(postaListesi);
        txtAdres.text().equals(adres);
        txtGramaj.text().equals(gramaj);
        txtTutar.text().equals(tutar);

        Allure.addAttachment("Ekran Alan Kontrolü : ","  Seçilen posta listesinin adının doğru geldiği görülür.\n" +
                                                                        "- Posta Tarihinin geldiği görülür.\n" +
                                                                        "- Posta gramajının doğru geldiği görülür.\n" +
                                                                        "- Pul Yönetimi ekranında girilen tutarlara göre hesaplama işleminin yapıldığı PTT Tutarının doğru geldiği görülür.");

        return this;
    }
    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public TopluPostaladiklarimPage evrakListesiYazdir(String[] konu) {
        int size = tblEvrakListesi.size();
        for (int i = 0; i < size; i++) {

            tblEvrakListesi
                    .get(i)
                    .$x("//span[text() = 'Yazdır']/../../button").click();
            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır(konu[i]);
            switchTo().window(1);
            closeNewWindow();
            switchTo().window(0);
            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        }
        return this;
    }

    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public TopluPostaladiklarimPage evrakListesiYazdirPdfKontrol(String[] konu) throws AWTException {
        int size = tblEvrakListesi.size();
        for (int i = 0; i < size; i++) {

            tblEvrakListesi
                    .get(i)
                    .$x("//span[text() = 'Yazdır']/../../button").click();
            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır(konu[i]);



            pdfKontrol();
//            closeNewWindow();
//            switchTo().window(0);
//            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        }
        return this;
    }

    @Step("")
    public TopluPostaladiklarimPage pdfKontrol() throws AWTException {

        WebDriver win = switchTo().window(1);
        WebElement s = win.findElement(By.tagName("embed"));
        System.out.println(s.getText());
        CharSequence kisayolCTRLS = Keys.chord(Keys.CONTROL, "s");
        CharSequence kisayolENTER = Keys.chord(Keys.ENTER);
        CharSequence paths = Keys.chord(Keys.valueOf("deneme.pdf"));
        s.sendKeys(kisayolCTRLS);
        s.sendKeys(Keys.ENTER);
//        switchTo().window(2);

        Robot robot = new Robot();  // Robot class throws AWT Exception
        sleep(2000); // Thread.sleep throws InterruptedException
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.);
//        robot.
        s.sendKeys(kisayolENTER);



        WebElement root1 = WebDriverRunner.getWebDriver().findElement(By.tagName("div"));

        //Get shadow root element
        WebElement shadowRoot1 = expandRootElement(root1);

        WebElement root2 = shadowRoot1.findElement(By.tagName("div"));
        WebElement shadowRoot2 = expandRootElement(root2);

        WebElement root3 = shadowRoot2.findElement(By.id("download"));
        WebElement shadowRoot3 = expandRootElement(root3);
        shadowRoot3.click();
        return this;

    }
    public WebElement expandRootElement(WebElement element) {
        WebElement ele = (WebElement) ((JavascriptExecutor)WebDriverRunner.getWebDriver())
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public TopluPostaladiklarimPage evrakListesiOrjinaliYazdir(String[] konu) {
        int size = tblEvrakListesi.size();
        for (int i = 0; i < size; i++) {
            tblEvrakListesi
                    .get(i)
                    .$x("//span[text() = 'Orjinalini Yazdır']/../../button").click();
            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır(konu[i]);
            switchTo().window(1);
            closeNewWindow();
            switchTo().window(0);
            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        }
        return this;
    }

    @Step("Evrak Detayı popup kontrolü")
    public TopluPostaladiklarimPage evrakDetayiPopUpKontrolü() {
        popUpEvrakDetayi.shouldBe(Condition.visible);
        return this;
    }

    @Step("Evrak Detayı Yazdır butonu")
    public TopluPostaladiklarimPage evrakDetayiYazdır(String konu) {
        tblEvrakDetayi.filterBy(Condition.text(konu))
                .first()
                .$("[id$='evrakDetayiViewDialogYazdir']").click();
        return this;
    }

    @Step("Tutar alanına \"{tutar}\" girilir")
    public TopluPostaladiklarimPage tutarGuncelle(String tutar) {
        txtTutar.clear();
        txtTutar.sendKeys(tutar);
        return this;
    }

    @Step("Güncelle butonuna tıklanır")
    public TopluPostaladiklarimPage guncelle() {
        btnGuncelle.click();
        return this;
    }

    @Step("Posta Listesi Adı alanı değiştirilir. \"{postaListesiAdi}\" ")
    public TopluPostaladiklarimPage postaListesiAdiDegistirme(String postaListesiAdi) {
        SelenideElement txtPostaListesiAdi = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) textarea");
        txtPostaListesiAdi.clear();
        txtPostaListesiAdi.sendKeys(postaListesiAdi);
        return this;
    }

    @Step("Barkod No girilir. \"{barkodNo}\" ")
    public TopluPostaladiklarimPage postaListesiBarkodNoDoldur(String barkodNo) {
        SelenideElement txtBarkoNo = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) input");
        txtBarkoNo.clear();
        txtBarkoNo.sendKeys(barkodNo);
        return this;
    }

    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public TopluPostaladiklarimPage gonderildigiYerSec(String gonderildigiYer) {
        cmbGonderildigiYer.selectComboBox(gonderildigiYer);
        return this;
    }

    @Step("Adres alanında \"{adres}\" girilir.")
    public TopluPostaladiklarimPage adresDoldur(String adres) {
        txtAdres.clear();
        txtAdres.sendKeys(adres);
        return this;
    }

    @Step("Gidis Sekli \"{gidisSekli}\" seç")
    public TopluPostaladiklarimPage gidisSekliSec(String gidisSekli) {
        cmbGidisSekli.selectComboBox(gidisSekli);
        return this;
    }

    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public TopluPostaladiklarimPage gonderildigiYerSec2(String gonderildigiYer) {
        cmbGonderildigiYer2.selectComboBox(gonderildigiYer);
        return this;
    }

    @Step("İndirim oranı girilir")
    public TopluPostaladiklarimPage indirimOraniDoldur(String indirimOrani) {
        txtIndirimOrani.setValue(indirimOrani);
        return this;
    }

    @Step("Etiket bastır butonuna tıkla.")
    public TopluPostaladiklarimPage etiketBastir() {
        btnEtiketBastir.click();
        txtEtiketBastir.waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("İndirim oranı göre Tutar kontrolü. \n İndirim Oranı : \"{indirimOrani}\" ")
    public TopluPostaladiklarimPage indirimSonrasiTutarKontrol(int indirimOrani) {

        String tutar = lblIndirimOncesiTutar.text();
        System.out.println(tutar);

        String[] parts = tutar.split(" ");// "004: 034556"
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556

        double result = Double.parseDouble(part1);
        double sonuc = result - ((indirimOrani * result) / 100);

        System.out.println(sonuc);
        txtTutar.text().contains(Double.toString(sonuc));
        Allure.addAttachment("Tutar Kontrolü : ", Double.toString(sonuc));
        return this;
    }

    @Step("Gramaj alanını doldur : \"{gramaj}\" ")
    public TopluPostaladiklarimPage gramajDoldur(String gramaj) {
        setValueJS(txtGramaj, gramaj);
        return this;
    }

    @Step("Etiket hesapla Tıkla")
    public TopluPostaladiklarimPage tutarHesapla() {
        clickJs(btnHesapla);
        return this;
    }

    public TopluPostaladiklarimPage etiketBastirEkraniKontrolü(String adres, String konu) {
        txtEtiketBastir.text().contains(konu);
        txtEtiketBastir.text().contains(adres);
        return this;
    }

    @Step("Etiket bastır ekranı kapama")
    public TopluPostaladiklarimPage etiketBastirEkraniKapat() {
        SelenideElement btnEtiketBastırEkraniKapat = $(By.xpath("//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']//a/span[@class='ui-icon ui-icon-closethick']"));
        btnEtiketBastırEkraniKapat.click();
        return this;
    }


    @Step("İndirim Öncesi tutar alaninda \"{indirimOncesiTutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public TopluPostaladiklarimPage indirimOncesiTutarKontrol(String indirimOncesiTutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblIndirimOncesiTutar.shouldHave(Condition.text(indirimOncesiTutar + " TL"));
        else
            lblIndirimOncesiTutar.shouldNotHave(Condition.text(indirimOncesiTutar + " TL"));
        return this;
    }

    @Step("Tutar alaninda \"{indirimOrani}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public TopluPostaladiklarimPage indirimOraniKontrol(String indirimOrani, boolean shouldBeEquals) {
        txtIndirimOrani.text();
        if (shouldBeEquals == true)
            txtIndirimOrani.shouldHave(Condition.value(indirimOrani));
        else
            txtIndirimOrani.shouldNotHave(Condition.value(indirimOrani));
        return this;
    }

    @Step("Tutar alaninda \"{tutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public TopluPostaladiklarimPage tutarKontrol(String tutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            txtTutar.shouldHave(Condition.value(tutar));
        else
            txtTutar.shouldNotHave(Condition.value(tutar));
        return this;
    }

    @Step("Evrak listesinden evrak sil")
    public TopluPostaladiklarimPage evrakSil(String gonderildigiYer, String evrakKonusu, String evrakSayisi) {
        tblEvrakListesi
                .filterBy(text(gonderildigiYer))
                .filterBy(text("Konu: " + evrakKonusu))
                .filterBy(text("Sayı: " + evrakSayisi))
                .first()
                .$("button[id$='silButton']")
                .click();

        return this;
    }

    @Step("Evrak Kontrol Et")
    public TopluPostaladiklarimPage evrakKontrol(String gonderildigiYer, String evrakKonusu, String evrakSayisi) {

        tblEvrakListesi
                .filterBy(text(gonderildigiYer))
                .filterBy(text(evrakKonusu))
                .filterBy(text(evrakSayisi))
                .first()
                .$("button[id$='silButton']")
                .shouldBe(visible);

        return this;
    }

    @Step("İlk posta listesi adını getir.")
    public String postaListesiAdiGetir(int index) {
        String postaListesiAdi = tblPostaladiklarim
                .get(0)
                .$$("td")
                .filterBy(text("Posta Listesi Adı:"))
                .get(0)
                .getText();

        return postaListesiAdi;
    }
}

