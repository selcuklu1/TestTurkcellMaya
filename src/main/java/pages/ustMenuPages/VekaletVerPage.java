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
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class VekaletVerPage extends MainPage {

    BelgenetElement txtVekaletVerenCombolov = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText"));
    SelenideElement btnVekaletVerenCombolov = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    BelgenetElement txtVekaletAlanCombolov = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText"));
    SelenideElement chkTumu = $(By.id("vekaletVerForm:vekaletLayout:j_idt5302_input"));
    SelenideElement txtBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBasTarihi_input"));
    SelenideElement txtBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBitTarihi_input"));
    SelenideElement chkEvraktaVelaketeSonEkiGorunsun = $(By.id("vekaletVerForm:vekaletLayout:j_idt5317_input"));
    //    SelenideElement chkOzelUnvanKullan = $(By.id("vekaletVerForm:vekaletLayout:j_idt5320_input"));
    SelenideElement txtAciklama = $(By.id("vekaletVerForm:vekaletLayout:aciklamaTextArea"));
    SelenideElement btnUygula = $(By.id("vekaletVerForm:vekaletLayout:onayaSunButton"));
    SelenideElement btnEvrakEkle = $("[id$='onayEvrakiDialogButton']");
    ElementsCollection tblDevredilecekEvrakklar = $$("tbody[id='vekaletVerForm:vekaletLayout:devredileceklerTabView:vekaletDataTable_data'] tr[role='row'][data-rk]");
    SelenideElement tabVekaletListesi = $("a[href='#vekaletVerForm:vekaletLayout:vekaletSorgulaField']");
    SelenideElement tabYeniVekalet = $("a[href='#vekaletVerForm:vekaletLayout:yeniVekaletTab']");

    SelenideElement btnVekalelVerenTemizle = $("[id^='vekaletVerForm:vekaletLayout:vekaletVerenLov:'] [class='ui-button-icon-left ui-icon delete-icon']");
    By txtVekaletVeren = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText']");
    By txtVekaletAlan = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText']");
    BelgenetElement txtOnaylayacakKisi = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletOnaylayacakKisiLov:LovText"));
    SelenideElement chkEvrakSonEkiGorunsunmu = $x("//table[@id='vekaletVerForm:vekaletLayout:vekaletOnEkiPanelGrid']//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']");

    SelenideElement popUpAktifVekaletUyarı = $(By.id("aktifVekaletinizVarUyariMesajiDialog"));
    SelenideElement btnTamam = $(By.id("aktifVekaletinizVarUyariMesajiDialogEvetBtn"));
    // Evrak Arama

    SelenideElement txtEvrakArama = $("[id$='evrakAramaText']");
    SelenideElement btnDokumanAra = $(By.id("vekaletOnayEvrakDialogForm:dokumanAraButton"));
    ElementsCollection tblEvrakListesi = $$("tbody[id='vekaletOnayEvrakDialogForm:sistemdeKayitliEvrakListesiDataTableId_data'] tr[data-ri]");

    //Vekalet Listesi Tabı
    SelenideElement btnSorgula = $(By.id("vekaletVerForm:vekaletLayout:vekaletSorgula_Id"));
    ElementsCollection tblVekaletListesi = $$("[id='vekaletVerForm:vekaletLayout:bulunanVekaletlerPanel'] tbody tr[data-ri]");
    SelenideElement tblVekaletListesi2 = $(By.xpath("//div[@id='vekaletVerForm:vekaletLayout:bulunanVekaletlerPanel']//div[starts-with(@id,'vekaletVerForm:vekaletLayout:j_idt')]"));

    SelenideElement dateTxtVekaletListesiBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletListeBasTarih_input"));
    SelenideElement dateTxtVekaletListesiBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletListeBitTarih_input"));
//    BelgenetElement cmbDurum = comboBox (By.xpath("//table[@id='vekaletVerForm:vekaletLayout:vekaletSorgulaPanelGrid']//select"));

    SelenideElement cmbDurum = $(By.xpath("//table[@id='vekaletVerForm:vekaletLayout:vekaletSorgulaPanelGrid']//select"));
    SelenideElement chkOzelUnvanKullan = $("table[id='vekaletVerForm:vekaletLayout:vekaletOzelUnvanPanelGrid'] div:nth-child(2)");
    SelenideElement txtOzelUnvan = $(By.id("vekaletVerForm:vekaletLayout:vekaletOzelUnvanText"));

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

    @Step("Vekalet Alan alanı seçilebilirlik kontrolü.Seçilebilir mi? : {secilebilmeli} ")
    public VekaletVerPage vekaletAlanAlanKontrolu(Boolean secilebilmeli) {
        if (secilebilmeli)
            Assert.assertEquals(txtVekaletAlanCombolov.isEnabled(), true, "Vekalet Alan alanı enable");
        else
            Assert.assertEquals(txtVekaletAlanCombolov.isEnabled(), false, "Vekalet Alan alanı disable");
        return this;
    }

    @Step("Vekalet veren alanını farklı doldur \"{vekaletVeren}\" ")
    public VekaletVerPage vekaletVerenFarkliDoldur(String vekaletVeren) {
        clickJs(btnVekalelVerenTemizle);
        txtVekaletVerenCombolov.selectLov(vekaletVeren);
        return this;
    }

    @Step("Onay verecek alanına kullanıcı doldur : \"{onayVerecekKullanici}\" ")
    public VekaletVerPage onayVerecekDoldur(String onayVerecekKullanici) {
        txtOnaylayacakKisi.selectLov(onayVerecekKullanici);
        return this;
    }


    @Step("Onay verecek kişi alanı seçilebilirlik kontrolü. Seçilebilir mi? : {shouldBeSelectable} ")
    public VekaletVerPage onayVerecekAlanKontrolu(boolean shouldBeSelectable) {
        if (shouldBeSelectable)
            Assert.assertEquals(txtOnaylayacakKisi.isEnabled(), true, "Onayalacak Kişi alanı enable");
        else
            Assert.assertEquals(txtOnaylayacakKisi.isEnabled(), false, "Onayalacak Kişi alanı disable");
        return this;
    }

    @Step("Vekalet veren alanını doldur : \"{vekaletVeren}\" ")
    public VekaletVerPage vekaletVerenDoldur(String vekaletVeren) {
        txtVekaletVerenCombolov.selectLov(vekaletVeren);
        return this;
    }


    @Step("Vekalet veren alanını silinir.")
    public VekaletVerPage vekaletVerenSil() {
        txtVekaletVerenCombolov.clearAllSelectedItems();
        return this;
    }

    @Step("Vekalet veren alanını kontrolü : \"{vekaletVeren}\" , {shouldBeExist} ")
    public VekaletVerPage vekaletVerenKontrolu(String vekaletVeren, boolean shouldBeExist) {

        if (shouldBeExist) {
            txtVekaletVerenCombolov.type(vekaletVeren).getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(1);
            txtVekaletVerenCombolov.closeTreePanel();
            //            txtVekaletVerenCombolov.openTreePanel().getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(1);

        } else {
            txtVekaletVerenCombolov.type(vekaletVeren).getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(0);
            txtVekaletVerenCombolov.closeTreePanel();
            //            txtVekaletVerenCombolov.openTreePanel().getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(0);

        }
        txtVekaletVerenCombolov.clear();
        return this;
    }

    @Step("Vekalet veren alanını kontrolü : \"{vekaletVeren}\" , {shouldBeExist} ")
    public VekaletVerPage vekaletAlanKontrolu(String vekaletAlan, boolean shouldBeExist) {

        if (shouldBeExist) {
            txtVekaletAlanCombolov.type(vekaletAlan).getSelectableItems().filterBy(Condition.text(vekaletAlan)).shouldHaveSize(1);
            txtVekaletAlanCombolov.closeTreePanel();
            //            txtVekaletVerenCombolov.openTreePanel().getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(1);

        } else {
            txtVekaletAlanCombolov.type(vekaletAlan).getSelectableItems().filterBy(Condition.text(vekaletAlan)).shouldHaveSize(0);
            txtVekaletAlanCombolov.closeTreePanel();
            //            txtVekaletVerenCombolov.openTreePanel().getSelectableItems().filterBy(Condition.text(vekaletVeren)).shouldHaveSize(0);

        }
        txtVekaletAlanCombolov.clear();
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
    @Step("Evrakta Velakete Son Eki Gorunsun mu alan kontrolu. Seçilebilir mi? : {shouldBeSelectable}")
    public VekaletVerPage evraktaVelaketeSonEkiGorunsunSeçilebilirlikKontrolu(boolean shouldBeSelectable) {
        if (shouldBeSelectable)
            Assert.assertEquals(chkEvrakSonEkiGorunsunmu.isEnabled(), true, "Alan enable");
        else
            Assert.assertEquals(chkEvrakSonEkiGorunsunmu.isEnabled(), false, "Alan disable");
        return this;
    }

    @Step("Bitiş Tarihi alanına \"{text}\" yazılır.")
    public VekaletVerPage bitisTarihiDoldur(String text) {
        txtBitisTarihi.setValue(text);
        return this;
    }

    @Step("Başlangıç Tarihi alanına \"{text}\" yazılır.")
    public VekaletVerPage baslangicTarihDoldur(String text) {
        txtBaslangicTarihi.setValue(text);
        return this;
    }

    @Step("Özel Ünvan checkBoxı tıklanır.")
    public VekaletVerPage ozelUnvanSec() {
        chkOzelUnvanKullan.click();
        return this;
    }

    @Step("Özel Ünvan alanı kontolü. Giriş Yapılabilir mi? : {shouldBeEditable} ")
    public VekaletVerPage ozelUnvanGirilebilecekAlanKontrolu(boolean shouldBeEditable) {
        if (shouldBeEditable)
            Assert.assertEquals(txtOzelUnvan.isEnabled(), true, "Özel ünvan alanı enable");
        else
            Assert.assertEquals(txtOzelUnvan.isEnabled(), false, "Özel ünvan  alanı disable");
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
                .$("[id$='ekleButton']").click();
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

            if (size == 1)
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
