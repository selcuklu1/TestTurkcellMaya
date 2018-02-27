package pages.ustMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class VekaletVerPage extends MainPage {

    BelgenetElement txtVekaletVerenCombolov = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText"));
    SelenideElement btnVekaletVerenCombolov = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    BelgenetElement txtVekaletAlanCombolov = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText"));
    SelenideElement chkTumu = $(By.id("vekaletVerForm:vekaletLayout:j_idt5302_input"));
    SelenideElement txtBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBasTarihi_input"));
    SelenideElement txtBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBitTarihi_input"));
    SelenideElement chkEvraktaVelaketeSonEkiGorunsun = $(By.id("vekaletVerForm:vekaletLayout:j_idt5317_input"));
    SelenideElement chkOzelUnvanKullan = $(By.id("vekaletVerForm:vekaletLayout:j_idt5320_input"));
    SelenideElement txtAciklama = $(By.id("vekaletVerForm:vekaletLayout:aciklamaTextArea"));
    SelenideElement btnUygula = $(By.id("vekaletVerForm:vekaletLayout:onayaSunButton"));
    SelenideElement btnEvrakEkle = $("[id$='onayEvrakiDialogButton']");
    ElementsCollection tblDevredilecekEvrakklar = $$("tbody[id='vekaletVerForm:vekaletLayout:devredileceklerTabView:vekaletDataTable_data'] tr[role='row'][data-rk]");
    SelenideElement tabVekaletListesi = $("a[href='#vekaletVerForm:vekaletLayout:vekaletSorgulaField']");
    SelenideElement tabYeniVekalet = $("a[href='#vekaletVerForm:vekaletLayout:yeniVekaletTab']");

    SelenideElement btnVekalelVerenTemizle = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    By txtVekaletVeren = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText']");
    By txtVekaletAlan = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText']");
    BelgenetElement txtOnaylayacakKisi = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletOnaylayacakKisiLov:LovText"));


    SelenideElement popUpAktifVekaletUyarı = $(By.id("aktifVekaletinizVarUyariMesajiDialog"));
    SelenideElement btnTamam = $(By.id("aktifVekaletinizVarUyariMesajiDialogEvetBtn"));
    // Evrak Arama

    SelenideElement txtEvrakArama = $("[id$='evrakAramaText']");
    SelenideElement btnDokumanAra = $(By.id("vekaletOnayEvrakDialogForm:dokumanAraButton"));
    ElementsCollection tblEvrakListesi = $$("tbody[id='vekaletOnayEvrakDialogForm:sistemdeKayitliEvrakListesiDataTableId_data']");

    //Vekalet Listesi Tabı
    SelenideElement btnSorgula = $(By.id("vekaletVerForm:vekaletLayout:vekaletSorgula_Id"));
    ElementsCollection tblVekaletListesi = $$("[id='vekaletVerForm:vekaletLayout:bulunanVekaletlerPanel'] tbody tr[data-ri]");
    SelenideElement tblVekaletListesi2 = $(By.xpath("//div[@id='vekaletVerForm:vekaletLayout:bulunanVekaletlerPanel']//div[starts-with(@id,'vekaletVerForm:vekaletLayout:j_idt')]"));

    SelenideElement dateTxtVekaletListesiBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletListeBasTarih_input"));
    SelenideElement dateTxtVekaletListesiBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletListeBitTarih_input"));
//    BelgenetElement cmbDurum = comboBox (By.xpath("//table[@id='vekaletVerForm:vekaletLayout:vekaletSorgulaPanelGrid']//select"));

    SelenideElement cmbDurum = $(By.xpath("//table[@id='vekaletVerForm:vekaletLayout:vekaletSorgulaPanelGrid']//select"));

    @Step("Vekalet Ver sayfasını aç")
    public VekaletVerPage openPage() {
        ustMenu(UstMenuData.AmirIslemleri.VekaletVer);
        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenmediği kontrolu")
    public VekaletVerPage vekaletVerenAlanınaGoruntulenmemeKontrolu(String bilgi, Boolean secilebilmeli) {
        Assert.assertEquals(secilebilmeli, comboLov(txtVekaletVeren).isLovValueSelectable(bilgi));
        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenmediği kontrolu")
    public VekaletVerPage vekaletAlanAlanınaGoruntulenmemeKontrolu(String bilgi, Boolean secilebilmeli) {
        Assert.assertEquals(secilebilmeli, comboLov(txtVekaletAlan).isLovValueSelectable(bilgi));
        System.out.println("Vekalet alan alanı başarılı");
        return this;
    }

    @Step("Vekalet veren alanını farklı doldur \"{vekaletVeren}\" ")
    public VekaletVerPage vekaletVerenFarkliDoldur(String vekaletVeren) {
        btnVekalelVerenTemizle.click();
        txtVekaletVerenCombolov.selectLov(vekaletVeren);
        return this;
    }

    @Step("Onay verecek alanına kullanıcı doldur : \"{onayVerecekKullanici}\" ")
    public VekaletVerPage onayVerecekDoldur(String onayVerecekKullanici) {
        txtOnaylayacakKisi.selectLov(onayVerecekKullanici);
        return this;
    }

    @Step("Vekalet veren alanını doldur : \"{vekaletVeren}\" ")
    public VekaletVerPage vekaletVerenDoldur(String vekaletVeren) {
        txtVekaletVerenCombolov.selectLov(vekaletVeren);
        return this;
    }

    @Step("Vekalet veren alanını kontrolü : \"{vekaletVeren}\" , {shouldBeExist} ")
    public VekaletVerPage vekaletVerenKontrolu(String vekaletVeren, boolean shouldBeExist) {
        if (shouldBeExist)
            txtVekaletVerenCombolov.openTreePanel().getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(1);
        else
            txtVekaletVerenCombolov.openTreePanel().getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(0);
        return this;
    }

    @Step("Vekalet alan alanını doldur : \"{vekaletAlan}\" ")
    public VekaletVerPage vekaletAlanDoldur(String vekaletAlan) {
        txtVekaletAlanCombolov.selectLov(vekaletAlan);
        return this;
    }


    @Step("Uygula butonu")
    public VekaletVerPage uygula() {
        btnUygula.click();
        return this;
    }

    @Step("Açıklama alanı doldur : \"{aciklama}\" ")
    public VekaletVerPage aciklamaDoldur(String aciklama) {
        txtAciklama.setValue(aciklama);
        return this;
    }

    public VekaletVerPage ozelUnvanKullanSec(boolean secim) {
        chkOzelUnvanKullan.setSelected(secim);
        return this;
    }

    public VekaletVerPage evraktaVelaketeSonEkiGorunsunSec(boolean secim) {
        chkEvraktaVelaketeSonEkiGorunsun.setSelected(secim);
        return this;
    }

    public VekaletVerPage bitisTarihiDoldur(String text) {
        txtBitisTarihi.setValue(text);
        return this;
    }

    public VekaletVerPage baslangicTarihDoldur(String text) {
        txtBaslangicTarihi.setValue(text);
        return this;
    }

    public VekaletVerPage tumuSec(boolean secim) {
        chkTumu.setSelected(secim);
        return this;
    }

    @Step("Evrak Ekle butonu")
    public VekaletVerPage evrakEkle() {
        clickJs(btnEvrakEkle);
        return this;
    }

    @Step("Evrak arama alanı doldur : \"{evrakNo}\" ")
    public VekaletVerPage evrakAramaDoldur(String evrakNo) {
        txtEvrakArama.sendKeys(evrakNo);
        return this;
    }

    @Step("Evrak no'ya göre tablo Kontrolü ve seçim : \"{evrakNo}\" ")
    public VekaletVerPage evrakAramaTabloKontrolveSecim(String evrakNo) {
        tblEvrakListesi
                .filterBy(Condition.text(evrakNo)).shouldHaveSize(1)
                .first()
                .$("[id^='vekaletOnayEvrakDialogForm:sistemdeKayitliEvrakListesiDataTableId'][id$='ekleButton']").click();
        return this;
    }

    @Step("Devredilecek Evraklar kontrolü")
    public VekaletVerPage devredilecekEvraklarKontrolu() {
        int size = tblDevredilecekEvrakklar.size();
        Assert.assertNotEquals(size, 0);
        return this;
    }

    @Step("Devredilecek Evrak no seç : \"{evrakNo}\" ")
    public VekaletVerPage devredilecekEvrakSec(String evrakNo) {
        tblDevredilecekEvrakklar
                .filterBy(Condition.text(evrakNo)).first()
                .$("[class='ui-chkbox ui-widget']").click();
        return this;
    }

    @Step("Dokuman ara")
    public VekaletVerPage dokumanAra() {
        btnDokumanAra.click();
        return this;
    }

    @Step("Vekalet Listesi Tab aç")
    public VekaletVerPage veklatListesiTabAc() {
        tabVekaletListesi.click();
        return this;
    }

    @Step("Yeni vekalet Tab aç")
    public VekaletVerPage yeniVekaletTabAc() {
        tabYeniVekalet.click();
        return this;
    }

    @Step("Sorgula butonu")
    public VekaletVerPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Vekalet Listesi statü tablo kontrolü : \"{statu}\" ")
    public VekaletVerPage vekaletListesiTabloKontrol(int column, String statu) {

        ElementsCollection kisiselPages = $$("td[id^='vekaletVerForm:vekaletLayout:'][id$='paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();
            int size = tblVekaletListesi
                    .filterBy(Condition.text(statu))
                    .size();

            if(size==1)
                break;
            //            for (int j = 0; j<tblVekaletListesi.size();j++) {
//                tblVekaletListesi.get(j)
//                        .$("button[id$=':kullaniciGrubuGuncelle_id']").shouldBe(Condition.visible);
//                tblVekaletListesi.get(j)
//                        .$("button[id$=':kullaniciGrubuAktif_id']").shouldBe(Condition.visible);
//            }
        }
        return this;

//        boolean status = findElementOnTableByColumnInputInAllPages(tblVekaletListesi2, column, statu).isDisplayed();
//
//        Assert.assertEquals(status, true);
//        return this;
    }

    @Step("Vekalet Listesi Tablo Kontrol")
    public VekaletVerPage vekaletListesiTabloKontrol() {
        tblVekaletListesi.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Vekalet Listesi Tablo Kontrol : \"{vekaletveren}\" ")
    public VekaletVerPage vekaletListesiVekaletIptal(String vekaletveren) {
        Selenide.sleep(3000); // tablo yavaş geldiğinden sleep koyuldu.
        ElementsCollection rows = tblVekaletListesi
                .filterBy(Condition.text(vekaletveren));

        for (SelenideElement row : rows) {
            row.$("textarea").sendKeys("İptal");
            row.$("button").click();
            SelenideElement popUp = $("[id='vekaletUyariDaialog']");
            popUp.shouldBe(Condition.visible);
            $(By.xpath("//div[@id= 'vekaletUyariDaialog']//button[span[text()='Evet']]")).click();
        }
        vekaletIslemleriSayfasıKapat();
        return this;
    }


    @Step("Vekalet İşlemleri sayfası kapat")
    public VekaletVerPage vekaletIslemleriSayfasıKapat() {
        $(By.xpath("//div[@id='window1Dialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        islemPenceresiKapatmaOnayiPopup("Kapat");
        return this;
    }

    @Step("Vekalet Listesi bitiş tarihi doldur : \"{bitisTarihi}\" ")
    public VekaletVerPage vekaletListesiBitisTarihiDoldur(String bitisTarihi) {
        dateTxtVekaletListesiBitisTarihi.setValue(bitisTarihi);
        return this;
    }

    @Step("Vekalet Listesi başlangıç tarihi doldur : \"{baslangiTarihi}\" ")
    public VekaletVerPage vekaletListesiBaslangicTarihiDoldur(String baslangiTarihi) {
        dateTxtVekaletListesiBaslangicTarihi.setValue(baslangiTarihi);
        return this;
    }

    @Step("Vekalet Listesi Tablo Kontrol")
    public VekaletVerPage vekaletListesiTabloAlanKontrolleri(String onayMakami, String onayDurumu) {
        tblVekaletListesi.filterBy(Condition.matchesText(onayMakami));
        tblVekaletListesi.filterBy(Condition.text(onayDurumu));
        return this;
    }

    @Step("Vekalet var uyarı popup")
    public VekaletVerPage vekaletVarUyariPopUp() {
        popUpAktifVekaletUyarı.exists();
        btnTamam.click();
        return this;
    }

    @Step("Durum Seç : \"{durum}\" ")
    public VekaletVerPage durumSec(String durum) {
        cmbDurum.selectOptionByValue(durum);
        return this;
    }
}
