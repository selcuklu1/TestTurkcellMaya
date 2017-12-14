package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

@SuppressWarnings("unused")
public class OnayAkisYonetimiPage extends MainPage {
//    private UstMenu ustMenu;

    //Filtre
    BelgenetElement
            txtFiltreBirim = comboLov(By.id("onayAkisiYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement cmbFiltreDurum = $(By.id("onayAkisiYonetimiListingForm:filterPanel:durumSelectBoxOnayAkisiYonetimiListing"));
    SelenideElement txtFiltreAd = $(By.id("onayAkisiYonetimiListingForm:filterPanel:adFilterInputOnayAkisiYonetimiListing"));
    SelenideElement filtreAcmaKapatma = $(By.id("onayAkisiYonetimiListingForm:filterPanel"));

    private SelenideElement btnVarsayilanYap = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:0:default"));
    //private SelenideElement btnPasifYap = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:0:changeOnayAkisiStatusButton"));
    private SelenideElement onayAkisAlani;
    private SelenideElement tblGelenEvrak = $(By.xpath(".//*[@id='mainInboxForm:inboxDataTable_data']/tr[1]/td[2]/div"));
    private SelenideElement btnCevapYaz = $(By.xpath(".//*[@id='mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton']"));
    private SelenideElement btnAra = $(By.id("onayAkisiYonetimiListingForm:filterPanel:searchEntitiesButtonOnayAkisiYonetimiListing"));
    private SelenideElement homePageButton = $(By.id("j_idt325"));
    private SelenideElement btnOnayAkisiYeni = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:addNewRolButton"));
    private SelenideElement txtOnayAkisiKullanicilar = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText"));
    private ElementsCollection cmbKullaniciBirimDataTable = $$("[id$='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable'] table tr select");
    private BelgenetElement txtOnayAkisiIslemleriKullanicilar = comboLov("[id='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText']");
    private SelenideElement btnOnayAkisiIslemleriKaydet = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiEditorKaydetId"));
    private SelenideElement txtOnayAkisiIslemleriAd = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiAkisAdiInput"));
    SelenideElement btnEkranKapat = $(By.cssSelector("[id='window1Dialog'] span[class='ui-icon ui-icon-closethick']"));

    SelenideElement durumAktif = $(By.cssSelector("[id^='onayAkisiYonetimiListingForm:rolDataTable'] [class='true']"));
    SelenideElement durumPasif = $(By.cssSelector("[id^='onayAkisiYonetimiListingForm:rolDataTable'] [class='false']"));
    ElementsCollection tblOnayAkisListesi = $$("[id='onayAkisiYonetimiListingForm:rolDataTable_data'] tr[role=row]");
    SelenideElement tblOnayAkisListesiSelenide = $(By.id("onayAkisiYonetimiListingForm:rolDataTable"));
    SelenideElement btnPasifYap = $(By.cssSelector("[id$='changeOnayAkisiStatusButton'] [class$='to-passive-status-icon']"));
    SelenideElement btnAktifYap = $(By.cssSelector("[id$='changeOnayAkisiStatusButton'] [class$='to-active-status-icon']"));
    SelenideElement btnGuncelle = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:0:updateRolButton"));

    SelenideElement btnSilOnayAkisiItem2 = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable:1:removeOnayAkisiItem"));
    SelenideElement cmbOnayAkisiYonetimIslemTipi = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable:1:onayAkisiYonetimiIslemTipi"));
    SelenideElement btnKontrolUp = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable:1:j_idt1768"));
    SelenideElement onayAkisListesiKontrolRow = $x("//tbody[@id='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable_data']/tr//select/option[@selected and @value='KONTROL']//ancestor::tr[1]");

    ElementsCollection trOnayAkisiEkleKullanicilar = $$("tbody[id*='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable_data'] tr[role='row']");
    SelenideElement chkKoordineli = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKoordineliBooleanCheckbox"));
    SelenideElement chkVekalet = $(By.id("    onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable:1:onayAkisiYonetimiVekilBooleanCheckbox"));


    @Step("Onay akışı sayfası aç")
    public OnayAkisYonetimiPage openPage() {
        ustMenu("Onay Akışı Yönetimi");
        return this;
    }

    @Step("Onay Akışı işlemler kaydet")
    public OnayAkisYonetimiPage onayAkisiIslemleriKaydet() {
        btnOnayAkisiIslemleriKaydet.click();
        return this;
    }

    @Step("Onay Akışı işlemler kaydet")
    public OnayAkisYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Onay Akışı işlemleri ad doldur")
    public OnayAkisYonetimiPage filtredeAdDoldur(String ad) {
        txtFiltreAd.setValue(ad);
        return this;
    }

    public OnayAkisYonetimiPage filtreDurumSec(String secim) {
        cmbFiltreDurum.selectOptionByValue(secim);
        return this;
    }

    //Title göre doldurur.
    @Step("Onay akışı işlemleri kullanıcılar alanı doldur")
    public OnayAkisYonetimiPage onayAkisiIslemlerKullaniciDoldur(String kullanici) {
        txtOnayAkisiIslemleriKullanicilar.type(kullanici).titleItems().first().click();
        //selectLov(kullanici);
        return this;
    }

    //Detaile göre doldurur.
    @Step("Onay akışı işlemleri kullanıcılar alanı doldur")
    public OnayAkisYonetimiPage onayAkisiIslemlerIstenilenDetaildeKullaniciDoldur(String kullanici) {
        txtOnayAkisiIslemleriKullanicilar.type(kullanici).detailItems().filterBy(text("Vekalet")).first().click();
        //selectLov(kullanici);
        return this;
    }


    @Step("İmzacı seç")
    public OnayAkisYonetimiPage imzacıSonSec(String value) {
        cmbKullaniciBirimDataTable.last().selectOption(value);
        return this;
    }

    public OnayAkisYonetimiPage kontrolcuYoksaEkle(String kullanici) {

        if (onayAkisListesiKontrolRow.isDisplayed() == false) {
            onayAkisiIslemlerKullaniciDoldur(kullanici);
            imzacıSonSec("Kontrol");

            onayAkisListesiKontrolRow
                    .$("[class$='ui-icon-arrowthick-1-n']").click();

            onayAkisListesiKontrolRow
                    .$("[class$='ui-icon-arrowthick-1-n']").click();
        }
        return this;
    }


    public OnayAkisYonetimiPage kontrolcuSil() {

        onayAkisListesiKontrolRow
                .$("[id$='removeOnayAkisiItem']").click();
        return this;
    }

    @Step("Onay Akışı işlemleri ad doldur")
    public OnayAkisYonetimiPage onayAkisiIslemleriAdDoldur(String ad) {
        txtOnayAkisiIslemleriAd.setValue(ad);
        return this;
    }

    @Step("Ad alanı alınır")
    public String adCek() {
        String ad = txtOnayAkisiIslemleriAd.getValue();
        return ad;
    }

    @Step("Onay akışı yeni")
    public OnayAkisYonetimiPage yeniOnayAkisiEkle() {
        btnOnayAkisiYeni.click();
        return this;
    }

    public OnayAkisYonetimiPage varsayilanYap() {
        btnVarsayilanYap.click();
        return this;
    }

    public OnayAkisYonetimiPage pasifYap() {
        btnPasifYap.click();
        return this;
    }

    public OnayAkisYonetimiPage aktifYap() {
        btnAktifYap.click();
        return this;
    }

    @Step("Koordine checkboxını seç")
    public OnayAkisYonetimiPage koordineliSec(boolean secim) {
        chkKoordineli.setSelected(secim);
        return this;
    }

    @Step("Vekalet checkboxını seç")
    public OnayAkisYonetimiPage vekaletSec(boolean secim) {
        chkVekalet.setSelected(secim);
        return this;
    }

    @Step("Pasif yap")
    public OnayAkisYonetimiPage adaGorePasifYap(String kullanici) {

        tblOnayAkisListesi
                .filterBy(Condition.text(kullanici)).shouldHaveSize(1)
                .first()
                .$("[id$='changeOnayAkisiStatusButton'] [class$='to-passive-status-icon']").click();

        return this;
    }

    @Step("Aktif yap")
    public OnayAkisYonetimiPage adaGoreAktifYap(String kullanici) {

        tblOnayAkisListesi
                .filterBy(Condition.text(kullanici)).shouldHaveSize(1)
                .first()
                .$("[id$='changeOnayAkisiStatusButton'] [class$='to-active-status-icon']").click();

        return this;
    }

    @Step("Kullanıcı birimin seçili geldiği kontrolu")
    public OnayAkisYonetimiPage birimKontrol(String birim) {
        // Assert.assertEquals(txtFiltreBirim.selectedTitles().filterBy(Condition.text(birim)).size(), 1);
        txtFiltreBirim.lastSelectedLovTitle().shouldHave(text(birim));

        return this;
    }

    @Step("Durum alanı kontrolu")
    public OnayAkisYonetimiPage durumKontrol(String aktifler) {
        Assert.assertEquals(cmbFiltreDurum.getText(), aktifler);
        return this;
    }

    @Step("Tüm liste kayıt kontrolu")
    public OnayAkisYonetimiPage aktiflerTumListeKayitKontrolu() {

        boolean status = findElementOnTableAllPages(durumPasif);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Kayıt görüntülenme kontrolu")
    public OnayAkisYonetimiPage kayitGoruntulenmeKontrolu(String ad) {

        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblOnayAkisListesiSelenide, 1, ad).isDisplayed();
        Assert.assertEquals(statusAd, true);

        return this;
    }


    @Step("Onay akışı Pasif ise Aktif Yap")
    public OnayAkisYonetimiPage onayAkisiPasifIseAktifYap(String onayAkisi) {

        if (tblOnayAkisListesi
                .filterBy(text(onayAkisi)).shouldHaveSize(1)
                .first()
                .$(By.cssSelector("[id$='changeOnayAkisiStatusButton'] [class$='to-active-status-icon']")).isDisplayed()) {
            tblOnayAkisListesi
                    .filterBy(text(onayAkisi)).shouldHaveSize(1)
                    .first()
                    .$(By.cssSelector("[id$='changeOnayAkisiStatusButton'] [class$='to-active-status-icon']")).click();

            islemOnayi("Evet");
            Allure.addAttachment("Onay akışı pasif olduğu için aktif yapıldı.", "");

        } else {
            System.out.println("Onay akışı aktif zaten");
        }

        return this;
    }

    @Step("Onay akışı Aktif ise Pasif Yap")
    public OnayAkisYonetimiPage onayAkisiAktifIsePasifYap(String onayAkisi) {

        if (tblOnayAkisListesi
                .filterBy(text(onayAkisi)).shouldHaveSize(1)
                .first()
                .$(By.cssSelector("[id$='changeOnayAkisiStatusButton'] [class$='to-passive-status-icon']")).isDisplayed()) {
            tblOnayAkisListesi
                    .filterBy(text(onayAkisi)).shouldHaveSize(1)
                    .first()
                    .$(By.cssSelector("[id$='changeOnayAkisiStatusButton'] [class$='to-passive-status-icon']")).click();

            islemOnayi("Evet");
            Allure.addAttachment("Onay akışı aktif olduğu için pasif yapıldı.", "");

        } else {
            System.out.println("Onay akışı pasif zaten");
        }

        return this;
    }

    public OnayAkisYonetimiPage ekraniKapat() {
        btnEkranKapat.click();
        islemPenceresiKapatmaOnayiPopup("Kapat");
        return this;
    }

    public OnayAkisYonetimiPage filtreAc() {
        filtreAcmaKapatma.click();
        return this;
    }

    public OnayAkisYonetimiPage guncelle() {
        btnGuncelle.click();
        return this;
    }

    public OnayAkisYonetimiPage silOnayAkisiItem2() {
        if (btnSilOnayAkisiItem2.isDisplayed()) {
            btnSilOnayAkisiItem2.click();
        }
        return this;
    }


    public OnayAkisYonetimiPage onayAkisiIslemleriKullaniciSil(String kullanici) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici))
                .get(0)
                .shouldBe(exist)
                .$("[class$='delete-icon']").click();
        return this;
    }

    @Step("Kullanici varsa sil")
    public OnayAkisYonetimiPage kullaniciVarsaSil(String kullanici) {

        if (trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici)).size() == 1) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullanici))
                    .first()
                    .shouldBe(exist)
                    .$("[class$='delete-icon']").click();
        }

        return this;
    }

    public OnayAkisYonetimiPage kullaniciYerleriDegistir(String kullanici1, String kullanici2) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici1))
                .get(0)
                .shouldBe(exist)
                .$("[class$='ui-icon-arrowthick-1-s']").click();

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici2))
                .get(0)
                .shouldBe(exist)
                .$("[class$='ui-icon-arrowthick-1-s']").click();

        return this;
    }

    public OnayAkisYonetimiPage onayAkisiKullaniciEnAlttaGetirme(String kullanici) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici))
                .get(0)
                .shouldBe(exist)
                .$("[class$='ui-icon-arrowthick-1-s']").click();

        return this;
    }

    public OnayAkisYonetimiPage kullaniciyaKullaniciTipiSec(String kullanici, String secim) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='onayAkisiYonetimiIslemTipi']")
                .selectOptionByValue(secim);

        return this;
    }

    @Step("Onay akışı kullanıcı adı ve tipi kontrol et")
    public OnayAkisYonetimiPage onayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='onayAkisiYonetimiIslemTipi']")
                .shouldHave(value(kullaniciTipi));

        return this;
    }

}
