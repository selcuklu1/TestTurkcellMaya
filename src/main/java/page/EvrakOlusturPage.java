package page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class EvrakOlusturPage extends BaseLibrary {

    // dolar işareti $=findElement anlamına gelir, $$=findElements

    //Bilgileri tabı
    //SelenideElement txtKonuKodu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:LovText"));
    SelenideElement txtKonuKodu = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");
    SelenideElement btnKonuKoduTree = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:treeButton"));
    SelenideElement txtKonu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:3:konuTextArea"));
    SelenideElement txtKaldiralacakKlasorler = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:LovText"));
    SelenideElement btnKaldiralacakKlasorlerTree = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:treeButton"));
    SelenideElement cmbEvrakTuru = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:5:evrakTuruCombo"));
    SelenideElement dateKayitTarihi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:7:kayitTarih_input"));
    SelenideElement cmbEvrakDili = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:8:evrakDili"));
    SelenideElement cmbGizlilikDerecesi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:9:guvenlikKodu"));
    SelenideElement rdbNormal = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:0"));
    SelenideElement rdbBilgiEdinmeKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:1"));
    SelenideElement rdbKisiselVerilerinKorunmasiKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:2"));
    SelenideElement txtAciklama = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:11:j_idt8443"));
    SelenideElement cmbIvedik = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:12:ivedilik"));
    SelenideElement dateMiat = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:13:miatCalendar_input"));
    SelenideElement cmbBilgiSecimTipi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:14:j_idt8516"));
    SelenideElement txtBilgi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:14:bilgiLov:LovText"));
    SelenideElement btnBilgiTree = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:14:bilgiLov:treeButton"));
    SelenideElement cmbGeregiSecimTipi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:j_idt8525"));
    SelenideElement txtGeregi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:geregiLov:LovText"));
    SelenideElement btnGeregiTree = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:geregiLov:treeButton"));
    SelenideElement chkDagitimiEkYap = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:dagitimEkYapCheckBoxId"));
    SelenideElement txtOnayAkisi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:LovText"));
    SelenideElement btnOnayAkisiTemizle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt134"));
    SelenideElement btnOnayAkisiEdit = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt135"));
    SelenideElement btnOnayAkisiEkle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:onayAkisiEkle"));

    //Editör tabı
    SelenideElement yeniGidenEvrakForm = $(By.id("cke_yeniGidenEvrakForm:ckeditorInstance_window1"));

    //Ekleri tabı - Dosya Ekle
    SelenideElement txtEkleriDosyaAciklama = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaAciklama"));
    SelenideElement btnEkleriDosyaFileUpload = $(By.id("yeniGidenEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
    SelenideElement cmbEkleriDosyaGuvenlikKodu = $(By.id("yeniGidenEvrakForm:evrakEkTabView:guvenlikKodu"));
    SelenideElement btnEkleriDosyaEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaEkleButton"));
    SelenideElement btnEkleriDosyaTemizle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaTemizleButton"));
    SelenideElement chkEkListesiniEkYap = $(By.id("yeniGidenEvrakForm:j_idt30306"));

    //Ekleri tabı - Fiziksel Ekle
    SelenideElement txtEkleriFizikselMetni = $(By.id("yeniGidenEvrakForm:evrakEkTabView:aciklamaTextArea"));
    SelenideElement cmbEkleriFizikselGizlilikDerecesi = $(By.id("yeniGidenEvrakForm:evrakEkTabView:guvenlikKoduAciklama"));
    SelenideElement btnEkleriFizikselEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:aciklamaEkleButton"));

    //Ekleri tabı - Sistemde Kayıtlı Evrak Ekle
    SelenideElement dateSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihBasId_input"));
    SelenideElement dateSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakAramaAranacakYerId"));
    SelenideElement btnSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dokumanAraButton"));

    //Ekleri tabı - Arşivde Kayıtlı Evrak Ekle
    SelenideElement dateArsivdeEvrakAraTarihiBaslangic = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihBasId_input"));
    SelenideElement dateArsivdeEvrakAraTarihiSon = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihSonId_input"));
    SelenideElement txtArsivdeEvrakAraKonu = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraKonuInputTextId"));
    SelenideElement txtArsivdeEvrakAraKullanici = $(By.id("yeniGidenEvrakForm:evrakEkTabView:kisiyeLov_id:LovText"));
    SelenideElement txtArsivdeEvrakAraSayi = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraSayiInputTextId"));
    SelenideElement btnArsivdenEvrakAra = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraButtonId"));

    //İlgileri tabı - Dosya Ekle
    SelenideElement txtIlgileriDosyaIlgiMetni = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dosyaAciklama"));
    SelenideElement btnIlgileriDosyaFileUpload = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:fileUploadButtonA_input"));
    SelenideElement btnIlgileriDosyaEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dosyaEkleButton"));

    //İlgileri tabı - Metin Ekle
    SelenideElement txtIlgileriMetinIlgiMetni = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:aciklamaTextArea"));
    SelenideElement btnIlgileriMetinEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:aciklamaEkleButton"));


    public void openTab(String tabName) {
        $(By.xpath("//div[@id='yeniGidenEvrakForm:leftTab:leftTab']//span[text()='" + tabName + "']//ancestor::tbody[1]//button")).click();
        $(By.id("yeniGidenEvrakForm:evrakBilgileriList")).shouldBe(visible);
    }

    public void openTabJS(String tabName) {
        executeJavaScript("arguments[0].click();"
                , $("//div[@id='yeniGidenEvrakForm:leftTab:leftTab']//span[text()='" + tabName + "']//ancestor::tbody[1]//button"));
    }

    public EvrakOlusturPage konuKoduDoldur(String konuKodu) {
        String cssFirstPart = "*[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='konuKoduLov:";
        By input = By.cssSelector(cssFirstPart + "LovText']");
        By treeButton = By.cssSelector(cssFirstPart + "treeButton']");
        By lovTree = By.cssSelector(cssFirstPart + "lovTree']");
        By lovTreeList = By.cssSelector(cssFirstPart + "lovTree'] li");
        By lovSecilen = By.cssSelector(cssFirstPart + "LovSecilen']");
        By lovSecilenItemTitle = By.cssSelector(cssFirstPart + "LovSecilen'] .lovItemTitle");
        By lovSecilenItemDetail = By.cssSelector(cssFirstPart + "LovSecilen'] .lovItemDetail");
        By lovSecilenItemDetailClear = By.cssSelector(cssFirstPart + "LovSecilen'] button");

        if ($(lovSecilenItemDetailClear).exists()) {
            $(lovSecilenItemDetailClear).click();
            $(input).waitUntil(visible, Configuration.timeout);
        }

        $(input).sendKeys(konuKodu);
        $$(lovTreeList).shouldHaveSize(1).get(0).click();


        // waitUntil(ExpectedConditions.invisibilityOfElementLocated(lovTree));
        // assertThat(ExpectedConditions.visibilityOfElementLocated(lovSecilen));
        return this;
    }

    public EvrakOlusturPage konuDoldur(String konu) throws InterruptedException {
        //sendKeys(txtKonu, konu, false); selenium
        txtKonu.sendKeys(konu); //selenide
        return this;
    }

    public EvrakOlusturPage kaldirilacakKlasorler(String klasor) {
        //TODO: Fonksiyon yazılacak.
        return this;
    }

    public EvrakOlusturPage evrakTuruSec(String value) throws InterruptedException {
        cmbEvrakTuru.selectOption(value);
        return this;
    }

    public EvrakOlusturPage evrakDiliSec(String value) throws InterruptedException {
        cmbEvrakTuru.selectOption(value);
        return this;
    }

    public EvrakOlusturPage evrakDerecesiSec(String value) throws InterruptedException {
        cmbGizlilikDerecesi.selectOption(value);
        return this;
    }

    public EvrakOlusturPage konuKapsamTipiSec() throws InterruptedException {
        btnKonuKoduTree.click();
        return this;
    }

    public EvrakOlusturPage aciklamaDoldur(String aciklama) throws InterruptedException {
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    public EvrakOlusturPage ivediSec(String value) throws InterruptedException {
        cmbIvedik.selectOption(value);
        return this;
    }

    public EvrakOlusturPage miatDoldur(String date) throws InterruptedException {
        dateMiat.sendKeys(date);
        return this;
    }

    public EvrakOlusturPage bilgiSecimTipiSec(String value) throws InterruptedException {
        cmbBilgiSecimTipi.selectOption(value);
        return this;
    }

    public EvrakOlusturPage bilgiDoldur(String bilgi) throws InterruptedException {
        txtBilgi.sendKeys(bilgi);
        return this;
    }

    public EvrakOlusturPage geregiSecimTipiSec(String value) throws InterruptedException {
        cmbGeregiSecimTipi.selectOption(value);
        return this;
    }

    public EvrakOlusturPage geregiDoldur(String geregi) throws InterruptedException {
        txtGeregi.sendKeys(geregi);
        return this;
    }

    public EvrakOlusturPage dagitimiEkYapSec(boolean secili) throws InterruptedException {
        chkDagitimiEkYap.setSelected(true);
        return this;
    }

    public EvrakOlusturPage onayAkisiDoldur(boolean onayAkisi) throws InterruptedException {
        //TODO: Fonksiyonu yazılacak.
        return this;
    }
}
