package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class TopluPostalanacakEvraklarPage extends MainPage {

    ElementsCollection tableGidecegiYer = $$("tbody[id='mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarGidecegiYerDataTable_data'] > tr[role='row']");
    SelenideElement txtBaslangic = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarIlkTarihTemizleButtonId_input"));
    SelenideElement txtBitis = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarSonTarihTemizleButtonId_input"));
    SelenideElement lblPostaTipiSeciniz = $x("//label[contains(., 'Posta Tipi Seçiniz ...')]");
    ElementsCollection listPostaTipleri = $$(By.xpath("//label[.='Adi Posta']/../../../ul/li"));
    SelenideElement btnSorgula = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarFiltrele"));
    SelenideElement btnPostaListesineAktar = $x("//span[text() = 'Posta Listesine Aktar']/..");
    SelenideElement btnPostaListesiDropDown = $x("//fieldset[@id='mainPreviewForm:postaListesiAktarimFieldsetId']//div[contains(@class, 'ui-corner-right')]/span");
    ElementsCollection listPostaListesi = $$("div[id='mainPreviewForm:tpbeSelectOneMenuId_panel'] > ul > li");
    SelenideElement btnListeyeEkle = $x("//span[text() = 'Listeye Ekle']/..");
    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnEvrakGoster = $x("//span[text() = 'Evrak Göster']/../../..//button");
    SelenideElement txtListeAdi = $(By.id("mainPreviewForm:tpbeListeAdiInputTextId"));
    SelenideElement btnListeOlustur = $(By.id("mainPreviewForm:listeOlusturButtonId"));
    SelenideElement lblSecilenTuzelKisi = $("div[id='mainPreviewForm:tpbeGonderildigiTuzelKisiLovId:LovSecilen'] div[id^='mainPreviewForm:tpbeGonderildigiTuzelKisiLovId']");
    SelenideElement lblSecilenGercekKisi = $("div[id='mainPreviewForm:tpbeGonderildigiGercekKisiLovId:LovSecilen'] div[id^='mainPreviewForm:tpbeGonderildigiGercekKisiLovId:']");
    SelenideElement lblSecilenKurum = $("div[id='mainPreviewForm:tpbeGonderildigiKurumLovId:LovSecilen'] div[id^='mainPreviewForm:tpbeGonderildigiKurumLovId']");

    SelenideElement btnSeciliTuzelKisiKaldir = $("div[id='mainPreviewForm:tpbeGonderildigiTuzelKisiLovId:LovSecilen'] button[id^='mainPreviewForm:tpbeGonderildigiTuzelKisiLovId']");
    SelenideElement btnSeciliGercekKisiKaldir = $("div[id='mainPreviewForm:tpbeGonderildigiGercekKisiLovId:LovSecilen'] button[id^='mainPreviewForm:tpbeGonderildigiGercekKisiLovId']");
    SelenideElement btnSeciliKurumKaldir = $("div[id='mainPreviewForm:tpbeGonderildigiKurumLovId:LovSecilen'] button[id^='mainPreviewForm:tpbeGonderildigiKurumLovId']");


    BelgenetElement txtGonderildigiTuzelKisi = comboLov(By.id("mainPreviewForm:tpbeGonderildigiTuzelKisiLovId:LovText"));
    BelgenetElement txtGonderildigiGercekKisi = comboLov(By.id("mainPreviewForm:tpbeGonderildigiGercekKisiLovId:LovText"));
    BelgenetElement txtKurum = comboLov(By.id("mainPreviewForm:tpbeGonderildigiKurumLovId:LovText"));

    SelenideElement txtFiltreGidecegiYer = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarGidecegiYerDataTable:topluPostalanacakYerFilterText"));

    @Step("Toplu postalanacak evraklar sayfasını aç")
    public TopluPostalanacakEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TopluPostalanacakEvraklar);
        return this;
    }

    @Step("Gönderildiği Yer alanından \"{gonderildigiYer}\" seç")
    public TopluPostalanacakEvraklarPage gonderilecegiYer(String gonderildigiYer) {
        BelgenetElement cmbGonderildigiYer = comboBox("mainPreviewForm:tpbeGidecegiYerSelectOneMenuId_label");
        cmbGonderildigiYer.selectComboBox(gonderildigiYer);
        return this;
    }


    @Step("Gideceği yer alanından {0} seç")
    public TopluPostalanacakEvraklarPage gidecegiYerSec(String gidecegiYer, boolean secim) {

        Boolean isSelected = false;

        SelenideElement currentRow = tableGidecegiYer
                .filterBy(Condition.text(gidecegiYer))
                .first();

        SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

        if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (secim == true) {
            if (isSelected == false)
                chkBox.click();
        } else {
            if (isSelected == true)
                chkBox.click();
        }

        return this;
    }

    @Step("Gideceği yerler seç")
    public TopluPostalanacakEvraklarPage gidecegiYerSec(String[] gidecegiYerler, boolean secim) {

        for (int i = 0; i < gidecegiYerler.length; i++) {
            Boolean isSelected = false;

            filtreGidecegiYer(gidecegiYerler[i]);

            SelenideElement currentRow = tableGidecegiYer
                    .filterBy(Condition.text(gidecegiYerler[i]))
                    .first();

            SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

            if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                isSelected = true;

            if (secim == true) {
                if (isSelected == false)
                    chkBox.click();
            } else {
                if (isSelected == true)
                    chkBox.click();
            }


        }
        filtreGidecegiYer("");

        return this;
    }

    @Step("Gideceği yerler alanından tümünü işaretle/işaretini kaldır")
    public TopluPostalanacakEvraklarPage gidecegiYerTumunuIsaretle(boolean isaretle) {

        if (isaretle == true) {

            for (int i = 0; i < tableGidecegiYer.size(); i++) {
                Boolean isSelected = false;

                SelenideElement currentRow = tableGidecegiYer.get(i);

                SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

                if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                    isSelected = true;

                if (isSelected == false)
                    chkBox.click();

            }


        } else {


            for (int i = 0; i < tableGidecegiYer.size(); i++) {
                Boolean isSelected = false;

                SelenideElement currentRow = tableGidecegiYer.get(i);

                SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

                if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                    isSelected = true;

                if (isSelected == true)
                    chkBox.click();

            }

        }


        return this;
    }

    @Step("Gideceği yerler seç")
    public TopluPostalanacakEvraklarPage gidecegiYerSec(String[] gidecegiYerler, boolean secim, boolean ignoreIfNotExists) {

        for (int i = 0; i < gidecegiYerler.length; i++) {
            Boolean isSelected = false;

            filtreGidecegiYer(gidecegiYerler[i]);

            SelenideElement currentRow = tableGidecegiYer
                    .filterBy(Condition.text(gidecegiYerler[i]))
                    .first();

            if (currentRow.exists()) {
                SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

                if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                    isSelected = true;

                if (secim == true) {
                    if (isSelected == false)
                        chkBox.click();
                } else {
                    if (isSelected == true)
                        chkBox.click();
                }
            }

        }
        filtreGidecegiYer("");

        return this;
    }

    @Step("Gideceği yer listesinde alfabetik kontrol")
    public TopluPostalanacakEvraklarPage gidecegiYerListesiAlfabetikSiraKontrolu() {

        String[] gidecegiYerList = new String[tableGidecegiYer.size()];

        for (int i = 0; i < gidecegiYerList.length; i++) {
            gidecegiYerList[i] = tableGidecegiYer.get(i).$(By.xpath("./td[2]")).innerText();
        }

        String[] gidecegiYerListOrdered = gidecegiYerList;
        Arrays.sort(gidecegiYerListOrdered);

        Assert.assertEquals(gidecegiYerList, gidecegiYerListOrdered);


        return this;
    }

    @Step("Tarih aralığını seç. Başlangıç: {0} - Bitiş:")
    public TopluPostalanacakEvraklarPage tarihAraligiSec(String baslangicTarihi, String bitisTarihi) {

        txtBaslangic.setValue(baslangicTarihi);
        txtBitis.setValue(bitisTarihi);

        return this;
    }

    @Step("Posta tipi seç.")
    public TopluPostalanacakEvraklarPage postaTipiSec(String[] postaTipleri) {
        lblPostaTipiSeciniz.click();
        ElementsCollection currentListElement = $$(By.xpath("//label[.='Adi Posta']/../../../ul")).last().$$("li");
        for (int i = 0; i < postaTipleri.length; i++) {

            SelenideElement currentRow = currentListElement
                    .filterBy(Condition.text(postaTipleri[i]))
                    .first();

            SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

            boolean isSelected = false;
            if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                isSelected = true;

            if (isSelected == false) {
                Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", chkBox);
                chkBox.click();
            }


        }
        $x("//tbody").click();

        return this;
    }

    @Step("Sorgula butonuna tıkla.")
    public TopluPostalanacakEvraklarPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Posta Listesine aktar butonuna tıkla.")
    public TopluPostalanacakEvraklarPage postaListesineAktar() {
        btnPostaListesineAktar.click();
        return this;
    }

    @Step("Posta listesinden {postaListesi} sec")
    public TopluPostalanacakEvraklarPage postaListesiSec(String postaListesi) {
        btnPostaListesiDropDown.click();

        SelenideElement currentItem = listPostaListesi
                .filterBy(Condition.text(postaListesi))
                .first();

        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", currentItem);

        currentItem.click();

        return this;
    }

    @Step("Listeye Ekle butonuna tıkla")
    public TopluPostalanacakEvraklarPage listeyeEkle() {
        btnListeyeEkle.click();
        return this;
    }

    @Step("Evrak tiki seç.")
    public TopluPostalanacakEvraklarPage evrakTikSec(String kayitTarihiSayi, String gidecegiYer, String konu, String hazirlayanBirim, String postTipi, boolean secim) {

        Boolean isSelected = false;

        SelenideElement currentRow = tableEvraklar
                .filterBy(Condition.text(kayitTarihiSayi))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                .filterBy(Condition.text("Posta Tipi: " + postTipi))
                .first();

        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", currentRow);

        SelenideElement currentRowCheckBox = currentRow.$(By.xpath(".//div[contains(@class, 'ui-chkbox ui-widget')]"));

        if (currentRowCheckBox.$(By.xpath(".//div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (secim == true) {
            if (isSelected == false)
                currentRowCheckBox.click();
        } else {
            if (isSelected == true)
                currentRowCheckBox.click();
        }


        return this;
    }

    @Step("Evrak seç.")
    public TopluPostalanacakEvraklarPage evrakSec(String kayitTarihiSayi, String gidecegiYer, String konu, String hazirlayanBirim, String postTipi) {


        SelenideElement currentRow = tableEvraklar
                .filterBy(Condition.text(kayitTarihiSayi))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                .filterBy(Condition.text("Posta Tipi: " + postTipi))
                .first();

        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", currentRow);

        currentRow.click();


        return this;
    }

    @Step("Evrak seç.")
    public String evrakSayiGetir(String kayitTarihiSayi, String gidecegiYer, String konu, String hazirlayanBirim, String postTipi) {


        String currentText = tableEvraklar
                .filterBy(Condition.text(kayitTarihiSayi))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                .filterBy(Condition.text("Posta Tipi: " + postTipi))
                .first()
                .$("td[class='ui-inbox-satir1'] > div")
                .innerText();

        currentText = currentText.substring(currentText.lastIndexOf("/ ") + 2, currentText.length());

        return currentText;

    }

    @Step("Konuya göre Evrak seç. \"{konu}\" ")
    public TopluPostalanacakEvraklarPage evrakSec(String konu, boolean secim) {
        Boolean isSelected = false;

        SelenideElement currentRow = tableEvraklar
                .filterBy(Condition.text(konu))
                .first();

        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", currentRow);

        SelenideElement currentRowCheckBox = currentRow.$(By.xpath(".//div[contains(@class, 'ui-chkbox ui-widget')]"));

        if (currentRowCheckBox.$(By.xpath(".//div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (secim == true) {
            if (isSelected == false)
                currentRowCheckBox.click();
        } else {
            if (isSelected == true)
                currentRowCheckBox.click();
        }


        return this;
    }

    @Step("Tüm evrakları seç. ")
    public TopluPostalanacakEvraklarPage evrakTumunuSec(boolean secim) {
        Boolean isSelected = false;

        SelenideElement currentRowCheckBox = $("div[id='mainInboxForm:inboxDataTable'] table tr:nth-child(3)  div[id^='mainInboxForm:inboxDataTable:j_idt']");

        if (currentRowCheckBox.$(By.xpath(".//div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (secim == true) {
            if (isSelected == false)
                currentRowCheckBox.click();
        } else {
            if (isSelected == true)
                currentRowCheckBox.click();
        }
        return this;
    }


    @Step("Evrak seç.")
    public TopluPostalanacakEvraklarPage evrakKontrol(String kayitTarihiSayi, String gidecegiYer, String konu, String hazirlayanBirim, String postTipi, boolean shouldBeExist) {

        if (shouldBeExist == true) {

            tableEvraklar
                    .filterBy(Condition.text(kayitTarihiSayi))
                    .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                    .filterBy(Condition.text("Posta Tipi: " + postTipi))
                    .first()
                    .shouldBe(Condition.exist)
                    .shouldBe(Condition.visible);

        } else {

            tableEvraklar
                    .filterBy(Condition.text(kayitTarihiSayi))
                    .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                    .filterBy(Condition.text("Posta Tipi: " + postTipi))
                    .first()
                    .shouldNotBe(Condition.exist)
                    .shouldNotBe(Condition.visible);

        }
        return this;
    }

    @Step("konuya gore Evrak kontrolu : \"{konu}\", \"{shouldBeExist}\" ")
    public TopluPostalanacakEvraklarPage konuyaGoreEvrakKontrolu(String konu, boolean shouldBeExist) {

        if (shouldBeExist == true) {
            tableEvraklar
                    .filterBy(Condition.text(konu))
                    .first()
                    .shouldBe(Condition.exist)
                    .shouldBe(Condition.visible);
        } else {
            tableEvraklar
                    .filterBy(Condition.text(konu))
                    .first()
                    .shouldNotBe(Condition.exist)
                    .shouldNotBe(Condition.visible);

        }
        return this;
    }


    @Step("Gideceği yerler seç")
    public TopluPostalanacakEvraklarPage gidecegiYerKontrol(String[] gidecegiYerler, boolean shouldBeExist) {

        if (shouldBeExist == true) {

            for (int i = 0; i < gidecegiYerler.length; i++) {

                SelenideElement currentRow = tableGidecegiYer
                        .filterBy(Condition.text(gidecegiYerler[i]))
                        .first()
                        .shouldBe(Condition.exist)
                        .shouldBe(Condition.visible);

            }

        } else {

            for (int i = 0; i < gidecegiYerler.length; i++) {

                SelenideElement currentRow = tableGidecegiYer
                        .filterBy(Condition.text(gidecegiYerler[i]))
                        .first()
                        .shouldNotBe(Condition.exist)
                        .shouldNotBe(Condition.visible);

            }

        }


        return this;
    }

    @Step("Evrak göster butonuna tıkla")
    public TopluPostalanacakEvraklarPage evrakGoster() {
        btnEvrakGoster.click();
        return this;
    }

    @Step("Yeni liste oluştur ekranında liste adı gir: {listeAdi}")
    public TopluPostalanacakEvraklarPage listeAdiDoldur(String listeAdi) {
        txtListeAdi.setValue(listeAdi);
        return this;
    }

    @Step("Liste oluştur butnonuna tıkla")
    public TopluPostalanacakEvraklarPage listeOlustur() {
        btnListeOlustur.click();
        return this;
    }

    @Step("Seçilen tüzel kişi: {tuzelKisi} olmalı")
    public TopluPostalanacakEvraklarPage tuzelKisiKontrolet(String tuzelKisi) {
        Assert.assertEquals(tuzelKisi, lblSecilenTuzelKisi.innerText());
        return this;
    }

    @Step("Seçili tüzel kişiyi kaldır")
    public TopluPostalanacakEvraklarPage seciliTuzelKisiKaldir() {
        btnSeciliTuzelKisiKaldir.click();
        return this;
    }

    @Step("Seçili kurumu kaldır")
    public TopluPostalanacakEvraklarPage seciliKurumKaldir() {
        btnSeciliKurumKaldir.click();
        return this;
    }

    @Step("Gönderildiği tüzel kişi seç: {tuzelKisi}")
    public TopluPostalanacakEvraklarPage gonderildigiTuzelKisiSec(String tuzelKisi) {
        txtGonderildigiTuzelKisi.selectLov(tuzelKisi);
        return this;
    }

    @Step("Gönderildiği kurum seç: {kurum}")
    public TopluPostalanacakEvraklarPage gonderildigiKurumSec(String kurum) {
        txtKurum.selectLov(kurum);
        return this;
    }

    @Step("Seçilen gerçek kişi: {gercekKisi} olmalı")
    public TopluPostalanacakEvraklarPage gerceklKisiKontrolet(String gercekKisi) {
        if (lblSecilenGercekKisi.innerText().contains(gercekKisi))
            Assert.assertEquals(true, true);
        else
            Assert.assertEquals(false, true);

        return this;
    }

    @Step("Seçili gerçek kişiyi kaldır")
    public TopluPostalanacakEvraklarPage seciliGercekKisiKaldir() {
        btnSeciliGercekKisiKaldir.click();
        return this;
    }

    @Step("Gönderildiği gerçek kişi seç: {gercekKkisi}")
    public TopluPostalanacakEvraklarPage gonderildigiGercekKisiSec(String gercekKisi) {
        txtGonderildigiGercekKisi.selectLov(gercekKisi);
        return this;
    }

    @Step("Seçilen kurum: {kurum} olmalı")
    public TopluPostalanacakEvraklarPage kurumKontrolet(String kurum) {
        Assert.assertEquals(kurum, lblSecilenKurum.innerText());
        return this;
    }

    @Step("İlk posta listesi adını getir.")
    public String postaListesiAdiGetir() {
        btnPostaListesiDropDown.click();
        if (listPostaListesi.size() > 0)
            return listPostaListesi.first().getText();
        else
            Assert.fail("Posta Listesi Yok");

        return "POSTA LISTESI BULUNAMADI!";
    }

    @Step("Filtre Panelinde Gideceği Yer doldur: {gidecegiYer}")
    public TopluPostalanacakEvraklarPage filtreGidecegiYer(String gidecegiYer){
        txtFiltreGidecegiYer.setValue(gidecegiYer);
        txtFiltreGidecegiYer.pressEnter();
        return this;
    }

}

