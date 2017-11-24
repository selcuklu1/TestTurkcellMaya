package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;


public class EvrakOlusturPage extends MainPage {

    // dolar işareti $=findElement anlamına gelir, $$=findElements

    SelenideElement btnPDFOnizleme = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='pdfOnIzleme']");
    SelenideElement btnKaydet = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydet']");
    SelenideElement btnKaydetOnayaSun = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydetHavaleEt']");
    SelenideElement btnPaylas = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='evrakPaylas']");


    //Bilgileri tabı
    //SelenideElement txtKonuKodu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:LovText"));
    //BelgenetElement txtKonuKodu = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");
    BelgenetElement txtKonuKodu = comboLov(By.id("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']"));
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

    SelenideElement btnBilgiTree = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:14:bilgiLov:treeButton"));
    SelenideElement cmbBilgiSecimTipi = $(By.xpath("//select[starts-with(@id,'yeniGidenEvrakForm:evrakBilgileriList:15:j_idt')]"));
    BelgenetElement txtBilgi = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:bilgiLov:LovText"));

    SelenideElement cmbGeregiSecimTipi = $(By.xpath("//select[starts-with(@id,'yeniGidenEvrakForm:evrakBilgileriList:16:j_idt')]"));
    BelgenetElement txtGeregi = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText"));
    SelenideElement btnGeregiTree = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:geregiLov:treeButton"));
    SelenideElement cmbPostaTipi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:selectOneMenu"));
    By cmbGeregiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");


    SelenideElement chkDagitimiEkYap = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:dagitimEkYapCheckBoxId"));
    BelgenetElement txtOnayAkisi = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
    SelenideElement btnOnayAkisiTemizle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt134"));
    SelenideElement btnOnayAkisiEdit = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt135"));
    //SelenideElement btnOnayAkisiEkle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:onayAkisiEkle"));

    // Gereği - Dağıtım Hitap Düzenleme
    SelenideElement btnGeregiLovSecilemUpdate = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:j_idt123"));
    SelenideElement btnBilgiLovSecilemUpdate = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:bilgiLov:LovSecilenTable:0:j_idt123"));

    SelenideElement chkAdresHitaptaGorunsun = $(By.xpath("//label[normalize-space(text())='Adres Hitapta Görünsün']/../..//input"));
    SelenideElement chkAdresDagitimdaGorunsun = $(By.xpath("//label[normalize-space(text())='Adres Dağıtımda Görünsün']/../..//input"));
    SelenideElement btnDagitimHitapDuzenlemeKaydet = $(By.xpath("//*[@id='yeniGidenEvrakForm:pnlHitapDuzenle']//span[normalize-space(text())='Kaydet']/parent::button"));

    //Editör tabı
    SelenideElement yeniGidenEvrakForm = $(By.id("cke_yeniGidenEvrakForm:ckeditorInstance_window1"));
    SelenideElement editorHitapKismi = $(By.cssSelector("#yeniGidenEvrakForm\\:hitapInplace > span:nth-child(4)"));
    SelenideElement tblEditorlovSecilenTable = $(By.id("yeniGidenEvrakForm:geregiKurumLov:LovSecilenTable"));


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

    //İlgileri tabı - Sistemde kayıtlı evrak ekle
    SelenideElement dateIlgileriSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
    SelenideElement dateIlgileriSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbIlgileriSistemdeEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakAramaAranacakYerId"));
    SelenideElement txtIlgileriSistemdeEvrakArama = $(By.name("yeniGidenEvrakForm:ilgiIslemleriTabView:evrakAramaText"));
    SelenideElement btnIlgileriSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dokumanAraButton"));

    //İlgileri tabı - Arşivde Kayıtlı Evrak Ekle
    SelenideElement dateIlgileriArsivdeEvrakAraTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihBasId_input"));
    SelenideElement dateIlgileriArsivdeEvrakAraTarihiSon = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihSonId_input"));
    SelenideElement txtIlgileriArsivdeEvrakAraKonu = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraKonuInputTextId"));
    SelenideElement txtIlgileriArsivdeEvrakAraKullanici = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:kisiyeLov_id:LovText"));
    SelenideElement txtIlgileriArsivdeEvrakAraSayi = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraSayiInputTextId"));
    SelenideElement btnIlgileriArsivdenEvrakAra = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraButtonId"));

    //İlişkili Evraklar tabı - Dosya Ekle
    SelenideElement txtIliskiliDosyaIlgiMetni = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:dosyaAciklama"));
    SelenideElement btnIliskiliDosyaFileUpload = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:fileUploadButtonA"));
    SelenideElement btnIliskiliEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:ilisikEkleButton"));
    SelenideElement btnIliskiliTemizle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:ilisikTemizleButton"));

    //İlişkili Evraklar tab - Sistemde kayıtlı evrak ekle
    SelenideElement dateIliskiliSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihBasId_input"));
    SelenideElement dateIliskiliSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihSonId_input"));
    SelenideElement cmbIliskiliSistemdeEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakAramaAranacakYerId"));
    SelenideElement txtIliskiliSistemdeEvrakArama = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:evrakAramaText"));
    SelenideElement btnIliskiliSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:dokumanAraButton"));

    //İlişkili Evraklar tab - Tercüme Ekle
    SelenideElement txtIlisikIslemleriTabViewIlisikMetni = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeAciklama"));
    SelenideElement btnIlisikIslemleriTabViewDosyaEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:fileUploadButtonB"));
    SelenideElement btnIlisikIslemleriTabViewEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeEkleButton"));
    SelenideElement btnIlisikIslemleriTabViewTemizle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeTemizleButton"));

    //İlgileri tabı - Arşivde Kayıtlı Evrak Ekle
    SelenideElement dateIlisikIslemleriTabViewArsivdeEvrakAraTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihBasId_input"));
    SelenideElement dateIlisikIslemleriTabViewArsivdeEvrakAraTarihiSon = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihSonId_input"));
    SelenideElement txtIlisikIslemleriTabViewArsivdeEvrakAraKonu = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraKonuInputTextId"));
    SelenideElement txtIlisikIslemleriTabViewArsivdeEvrakAraKullanici = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:kisiyeLov_id:LovText"));
    SelenideElement txtIlisikIslemleriTabViewArsivdeEvrakAraSayi = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraSayiInputTextId"));
    SelenideElement btnIlisikIslemleriTabViewArsivdenEvrakDokumanAra = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraButtonId"));

    //Şablon İşlemleri tabı

    //Evrak Notları
    SelenideElement btnKisiselNotEkle = $(By.id("yeniGidenEvrakForm:kisiselNotEkleDataTableId:kisiselNotEkleId"));

    SelenideElement btnOnayAkisiEkle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:onayAkisiEkle"));
    //SelenideElement tableOnayAkisiEkleKullanicilar = $(By.xpath("//tbody[@id='yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable_data']/tr/td/div/table/tbody/tr/td"));
    SelenideElement tableOnayAkisiEkleKullanicilar = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable_data"));

    SelenideElement btnOnayAkisiKullaniciKullan = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:anlikAkisKullanButton"));
    BelgenetElement txtOnayAkisiKullanicilar = comboLov("[id$='akisAdimLov:LovText']");
    SelenideElement listOnayAkisikullanicilar = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:lovTree"));

    BelgenetElement txtcomboLovBilgi = comboLov(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']"));
    By cmbBilgiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']");

    SelenideElement btnOtomatikOnayAkisi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:otomatikOnayAkisiEkle"));

    public EvrakOlusturPage otomatikOnayAkisi() {
        btnOtomatikOnayAkisi.click();
        return this;
    }
    @Step("Otomatik onay akışı kontrol")
    public EvrakOlusturPage otomatikOnayAkisiGeldigiGorme(String ekranAdi) {
        $$(" [id='yeniGidenEvrakForm:hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId'] tbody tr")
                .filterBy(text(ekranAdi)).shouldHave(sizeGreaterThan(0)).get(0).click();
        System.out.println("Başarılı geçti " + ekranAdi);
        return this;
    }

    @Step("\"{0}\" text var olma kontorlu, beklenen: {1}")
    public EvrakOlusturPage otomatikOnayAkisiGelmedigiGorme(String ekranAdi, boolean vardir) {
        boolean t = $$(" [id='yeniGidenEvrakForm:hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId'] tbody tr")
                .filterBy(text(ekranAdi)).size() > 0;
        Assert.assertEquals(t, vardir, "kdkdkdkd");
        System.out.println("Başarılı geçti:"+ekranAdi);
        return this;
    }

    public EvrakOlusturPage openPage() {
        new UstMenu().ustMenu("Evrak Oluştur");
        return this;
    }

    @Step("\"{tabName}\" tab aç")
    public EvrakOlusturPage openTab(String tabName) {
        $(By.xpath("//div[@id='yeniGidenEvrakForm:leftTab:leftTab']//span[text()='" + tabName + "']//ancestor::tbody[1]//button")).click();
        //$(By.id("yeniGidenEvrakForm:evrakBilgileriList")).shouldBe(visible);
        return this;
    }

    @Step("\"{tabName}\" tab aç")
    public EvrakOlusturPage openTabJS(String tabName) {
        executeJavaScript("arguments[0].click();"
                , $("//div[@id='yeniGidenEvrakForm:leftTab:leftTab']//span[text()='" + tabName + "']//ancestor::tbody[1]//button"));
        return this;
    }

    @Step("Konu kodu doldur")
    public EvrakOlusturPage konuKoduDoldur(String konuKodu) {
        txtKonuKodu.selectComboLov(konuKodu);
        //shouldHave(Condition.text(konuKodu));

        System.out.println("title: " + txtKonuKodu.lastSelectedLovTitleText());
        System.out.println("detail: " + txtKonuKodu.lastSelectedLovDetailText());

        return this;
    }

    @Step("Konu doldur")
    public EvrakOlusturPage konuDoldur(String konu) {
        //sendKeys(txtKonu, konu, false); selenium
        txtKonu.sendKeys(konu); //selenide
        return this;
    }

    public EvrakOlusturPage kaldirilacakKlasorler(String klasor) {
        //TODO: Fonksiyon yazılacak.
        return this;
    }

    public EvrakOlusturPage evrakTuruSec(String value) {
        cmbEvrakTuru.selectOption(value);
        return this;
    }

    public EvrakOlusturPage evrakDiliSec(String value) {
        cmbEvrakTuru.selectOption(value);
        return this;
    }

    public EvrakOlusturPage evrakDerecesiSec(String value) {
        cmbGizlilikDerecesi.selectOption(value);
        return this;
    }

    public EvrakOlusturPage konuKapsamTipiSec() {
        btnKonuKoduTree.click();
        return this;
    }

    public EvrakOlusturPage aciklamaDoldur(String aciklama) {
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    public EvrakOlusturPage ivediSec(String value) {
        cmbIvedik.selectOption(value);
        return this;
    }

    public EvrakOlusturPage miatDoldur(String date) {
        dateMiat.sendKeys(date);
        return this;
    }

    @Step("Bilgi seçim tipi seç")
    public EvrakOlusturPage bilgiSecimTipiSec(String bilgi) {
        cmbBilgiSecimTipi.selectOptionByValue(bilgi);
        return this;
    }

    public EvrakOlusturPage bilgiDoldur(String bilgi) {
        txtcomboLovBilgi.selectComboLov(bilgi);
        //shouldHave(Condition.text(geregi));
        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenmediği kontrolu")
    public EvrakOlusturPage bilgiAlanindaGoruntulenmemeKontrolu(String bilgi) {
        boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(bilgi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + bilgi + ": Gerçek kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + bilgi + ": Gerçek kişinin görüntülenmediği görülür.");
        return this;
    }

    @Step("Gereği seçim tipi seç")
    public EvrakOlusturPage geregiSecimTipiSec(String value) {
        cmbGeregiSecimTipi.selectOptionByValue(value);
        return this;
    }

    @Step("Gereği doldur")
    public EvrakOlusturPage geregiDoldur(String geregi) throws InterruptedException {
        Thread.sleep(6000);
        txtGeregi.selectComboLov(geregi);
        //shouldHave(Condition.text(geregi));

        // System.out.println("title: " + txtGeregi.lastSelectedLovTitleText());
        // System.out.println("detail: " + txtGeregi.lastSelectedLovDetailText());

        return this;
    }

    @Step("Kişinin Geregi alanında görüntülenmediği kontrolu")
    public EvrakOlusturPage geregiAlanindaGoruntulenmemeKontrolu(String geregi) {
        boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(geregi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + geregi + ": Gerçek kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + geregi + ": Gerçek kişinin görüntülenmediği görülür.");
        return this;
    }

    public EvrakOlusturPage dagitimiEkYapSec(boolean secili) {
        chkDagitimiEkYap.setSelected(true);
        return this;
    }

    public EvrakOlusturPage onayAkisiDoldur(boolean onayAkisi) {
        //TODO: Fonksiyonu yazılacak.
        return this;
    }

    public EvrakOlusturPage onayAkisiEkle(String kullanici) {

        btnOnayAkisiEkle.click();
        txtOnayAkisiKullanicilar.selectComboLov(kullanici);

        return this;
    }

    @Step("Gereği alanı güncelle")
    public EvrakOlusturPage geregiAlaniGuncelle() {
        btnGeregiLovSecilemUpdate.click();
        return this;
    }

    @Step("Bilgi alanı güncelle")
    public EvrakOlusturPage bilgiAlaniGuncelle() {
        btnBilgiLovSecilemUpdate.click();
        return this;
    }


    public EvrakOlusturPage adresHitaptaGorunsunSec(boolean secim) {
        chkAdresHitaptaGorunsun.setSelected(secim);
        return this;
    }

    public EvrakOlusturPage adresDagitimdaGorunsunSec(boolean secim) {
        chkAdresDagitimdaGorunsun.setSelected(secim);
        return this;
    }

    public EvrakOlusturPage dagitimHitapDuzenlemeKaydet() {
        btnDagitimHitapDuzenlemeKaydet.click();
        return this;
    }


    public EvrakOlusturPage onayAkisiKullaniciKontrol(String _kullaniciAdi, String _kullaniciTipi) {
        tableOnayAkisiEkleKullanicilar.$(By.xpath("./tr[contains(., '" + _kullaniciAdi + "')]//select/option[@selected='selected' and contains(., '" + _kullaniciTipi + "')]")).shouldBe(Condition.exist);
        return this;
    }

    public EvrakOlusturPage onayAkisiKullaniciTipiSec(String _kullaniciAdi, String _kullaniciTipi) {
        tableOnayAkisiEkleKullanicilar.$(By.xpath("./tr[contains(., '" + _kullaniciAdi + "')]")).selectOptionContainingText(_kullaniciTipi);
        return this;
    }

    public EvrakOlusturPage onayAkisiKullaniciSec(String _kullaniciAdi) {
        txtOnayAkisiKullanicilar.setValue(_kullaniciAdi);
        listOnayAkisikullanicilar.$(By.xpath("./ul/li[contains(., '" + _kullaniciAdi + "')]")).click();
        return this;
    }

    public EvrakOlusturPage onayAkisiKullan() {
        btnOnayAkisiKullaniciKullan.click();
        return this;
    }

    @Step("Gereği alanı kontrolu başarılı")
    public EvrakOlusturPage geregiAlaniKontrol(String adSoyad, String unvan, String adres, String posta) {
        System.out.println("Gelen title:     " + txtGeregi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);
        System.out.println("Gelen detail:    " + txtGeregi.lastSelectedLovDetailText());
        System.out.println("Beklenen detail: " + unvan + " | " + adres);
        System.out.println("Gelen posta:     " + cmbPostaTipi.getSelectedValue());
        System.out.println("Beklenen posta:  " + posta);

        Assert.assertEquals(txtGeregi.lastSelectedLovTitleText().contains(adSoyad), true);
        Assert.assertEquals(txtGeregi.lastSelectedLovDetailText().contains(unvan + " | " + adres), true);
        Assert.assertEquals(cmbPostaTipi.getSelectedValue().contains(posta), true);

        return this;
    }

    @Step("Hitap Alanı: Hitap, Unvan, Ad, Soyad kontrolu")
    public EvrakOlusturPage hitapAlaniUnvanAdSoyadKontrol(String sayin, String unvan, String ad, String soyad) {
        String getHitapAlani = editorHitapKismi.shouldHave(Condition.visible).getText();
        String girilenHitapAlani = sayin + " " + unvan + " " + toUpperCaseFirst(ad) + " " + soyad.toUpperCase();
        System.out.println("Gelen Hitap Alanı:   " + getHitapAlani);
        System.out.println("Girilen Hitap Alanı: " + girilenHitapAlani);
        Assert.assertEquals(getHitapAlani.contains(girilenHitapAlani), true);

        return this;
    }

    @Step("Hitap Alanı: Adres, ilce, il kontrolu")
    public EvrakOlusturPage hitapAlaniAdresKontrol(String adres, String ilce, String il) {
        // Kuştepe Mahallesi ŞİŞLİ / İSTANBUL
        String getHitapAlani = editorHitapKismi.shouldHave(Condition.visible).getText();
        String girilenHitapAlaniAdres = adres + " " + ilce.toUpperCase() + " / " + il.toUpperCase();
        System.out.println("Girilen Hitap Alanı: " + girilenHitapAlaniAdres);
        System.out.println("Gelen Hitap Alanı:   " + getHitapAlani);
        Assert.assertEquals(getHitapAlani.contains(girilenHitapAlaniAdres), true);
        return this;
    }

    @Step("PDF Önizleme")
    public EvrakOlusturPage pdfOnIzleme() {
        btnPDFOnizleme.click();
        return this;
    }

    @Step("Gereği alanında adres gelmedigi, Bilgi alanında dagitim yerinin adresi ile geldigi gorulur")
    public EvrakOlusturPage geregiBilgiAlaniAdresPdfKontrol(String birinciKullaniciGeregiAdresi, String ikinciKullaniciBilgiAdresi) throws InterruptedException {

        //gereği: div[@id='viewer']/div[@class='page']//div[.='xrpisak Mahallesi ŞİŞLİ / İSTANBUL']
        //blgil : div[@id='viewer']/div[@class='page']//div[.='Gültepe Mahallesi KAĞITHANE / İSTANBUL']

        System.out.println(birinciKullaniciGeregiAdresi);
        System.out.println(ikinciKullaniciBilgiAdresi);

        SelenideElement geregiAdresAlaniPDF = $(By.xpath("div[@id='viewer']/div[@class='page']//div[.='" + birinciKullaniciGeregiAdresi + "']"));
        SelenideElement bilgiAdresAlaniPDF = $(By.xpath("div[@id='viewer']/div[@class='page']//div[.='" + ikinciKullaniciBilgiAdresi + "']"));

        Assert.assertEquals(geregiAdresAlaniPDF.isDisplayed(), false);
        Assert.assertEquals(bilgiAdresAlaniPDF.isDisplayed(), true);
        Assert.assertEquals(bilgiAdresAlaniPDF.getText(), ikinciKullaniciBilgiAdresi);

        return this;
    }
}
