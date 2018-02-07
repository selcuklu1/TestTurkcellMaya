package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

@SuppressWarnings("unused")
public class OnayAkisYonetimiPage extends MainPage {
//    private UstMenu ustMenu;

    //Filtre
    BelgenetElement txtFiltreBirim = comboLov(By.id("onayAkisiYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement cmbFiltreDurum = $(By.id("onayAkisiYonetimiListingForm:filterPanel:durumSelectBoxOnayAkisiYonetimiListing"));
    SelenideElement txtFiltreAd = $(By.id("onayAkisiYonetimiListingForm:filterPanel:adFilterInputOnayAkisiYonetimiListing"));
    SelenideElement filtreAcmaKapatma = $(By.id("onayAkisiYonetimiListingForm:filterPanel"));
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
    By txtKullanicilar = By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText");
    //Değişecek burası
    SelenideElement btnBirim = $(By.xpath("//div[@id='onayAkisiYonetimiEditorForm:onayAkisiYonetimiAkisOlusturPanel_content']/table[@role='grid']/tbody/tr[3]//div[@type='button']"));
    SelenideElement txtKullanicilarSelenide = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText"));
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
    private SelenideElement chkVekilCheckbox = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable:1:onayAkisiYonetimiVekilBooleanCheckbox"));



    @Step("Onay akışı sayfası aç")
    public OnayAkisYonetimiPage openPage() {
        ustMenu(UstMenuData.KisiselIslemlerim.OnayAkisiYonetimi);
        return this;
    }

    @Step("Onay Akışı işlemler kaydet")
    public OnayAkisYonetimiPage onayAkisiIslemleriKaydet() {
        clickJs(btnOnayAkisiIslemleriKaydet);
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

    @Step("Filtrede durum seç")
    public OnayAkisYonetimiPage filtreDurumSec(String filtreDurumu) {
        cmbFiltreDurum.selectOptionByValue(filtreDurumu);
        return this;
    }

    //Title göre doldurur.
    @Step("Onay akışı işlemleri kullanıcılar alanı doldur")
    public OnayAkisYonetimiPage onayAkisiIslemlerKullaniciDoldur(String kullanici) {
        txtOnayAkisiIslemleriKullanicilar.type(kullanici).getTitleItems().first().click();
        //selectLov(kullanici);
        return this;
    }

    //Detaile göre doldurur.
    @Step("Onay akışı işlemleri kullanıcılar alanı doldur")
    public OnayAkisYonetimiPage onayAkisiIslemlerIstenilenDetaildeKullaniciDoldur(String kullanici) {
        txtOnayAkisiIslemleriKullanicilar.type(kullanici).getDetailItems().filterBy(text("Vekalet")).first().click();
        //selectLov(kullanici);
        return this;
    }

    @Step("Vekalet checkbox tikini kaldır")
    public OnayAkisYonetimiPage vekilCheckboxSec(boolean secim) {
        chkVekilCheckbox.setSelected(secim);
        return this;
    }


    @Step("Onay akışı işlemleri kullanıcılar alanında yazılan kullanıcıyı sil")
    public OnayAkisYonetimiPage onayAkisiIslemlerKullaniciAlaniniSil() {
        txtKullanicilarSelenide.click();
        txtKullanicilarSelenide.clear();
        return this;
    }


    @Step("İmzacı seç")
    public OnayAkisYonetimiPage imzacıSonSec(String imzaci) {
        cmbKullaniciBirimDataTable.last().selectOption(imzaci);
        return this;
    }

    @Step("Kontrolcu yoksa ekle")
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

    @Step("Kontrolcu sil")
    public OnayAkisYonetimiPage kontrolcuSil() {

        onayAkisListesiKontrolRow
                .$("[id$='removeOnayAkisiItem']").click();
        return this;
    }

    @Step("Onay Akışı işlemlerinde ad doldur")
    public OnayAkisYonetimiPage onayAkisiIslemleriAdDoldur(String ad) {
        txtOnayAkisiIslemleriAd.setValue(ad);
        return this;
    }

    @Step("Onay Akışı işlemlerinde ad sil")
    public OnayAkisYonetimiPage onayAkisiIslemleriAdSil() {
        txtOnayAkisiIslemleriAd.clear();
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

    @Step("Varsayılan yap")
    public OnayAkisYonetimiPage varsayilanYap() {
        btnVarsayilanYap.click();
        return this;
    }

    @Step("Pasif yap")
    public OnayAkisYonetimiPage pasifYap() {
        btnPasifYap.click();
        return this;
    }

    @Step("Aktif yap")
    public OnayAkisYonetimiPage aktifYap() {
        btnAktifYap.shouldBe(visible);
        clickJs(btnAktifYap);
        return this;
    }

    @Step("Koordineli seçeneğini seç")
    public OnayAkisYonetimiPage koordineliSec(boolean secim) {
        chkKoordineli.setSelected(secim);
        return this;
    }

    @Step("Koordineli seçimini kaldır")
    public OnayAkisYonetimiPage koordineliSecimiKaldir(boolean secim) {
        chkKoordineli.setSelected(secim);
        return this;
    }

    @Step("Vekalet checkboxını seç")
    public OnayAkisYonetimiPage vekaletSec(boolean secim) {
        chkVekalet.setSelected(secim);
        return this;
    }

    @Step("Ada göre pasif yap")
    public OnayAkisYonetimiPage adaGorePasifYap(String kullanici) {

        tblOnayAkisListesi
                .filterBy(Condition.text(kullanici)).shouldHaveSize(1)
                .first()
                .$("[id$='changeOnayAkisiStatusButton'] [class$='to-passive-status-icon']").click();

        return this;
    }

    @Step("Ada göre aktif yap")
    public OnayAkisYonetimiPage adaGoreAktifYap(String kullanici) {

        tblOnayAkisListesi
                .filterBy(Condition.text(kullanici)).shouldHaveSize(1)
                .first()
                .$("[id$='changeOnayAkisiStatusButton'] [class$='to-active-status-icon']").click();

        return this;
    }

    @Step("Kullanıcı birimin seçili gelme kontrolu")
    public OnayAkisYonetimiPage birimKontrol(String birim) {
        // Assert.assertEquals(txtFiltreBirim.selectedTitles().filterBy(Condition.text(birim)).size(), 1);
        txtFiltreBirim.getSelectedTitles().last().shouldHave(text(birim));

        return this;
    }

    @Step("Durum alanı kontrolu")
    public OnayAkisYonetimiPage durumKontrol(String filtreDurum) {
        Assert.assertEquals(cmbFiltreDurum.getText(), filtreDurum);
        return this;
    }

    @Step("Aktifler tüm liste kayıt kontrolu")
    public OnayAkisYonetimiPage aktiflerTumListeKayitKontrolu() throws InterruptedException {

        String formOnayAkisiYonetimi = "onayAkisiYonetimiListingForm";

        boolean status = findElementOnTableAllPages(formOnayAkisiYonetimi, durumPasif);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Kayıt görüntülenme kontrolu")
    public OnayAkisYonetimiPage kayitGoruntulenmeKontrolu(String ad) {

        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblOnayAkisListesiSelenide, 1, ad).isDisplayed();
        Assert.assertEquals(statusAd, true);

        return this;
    }


    @Step("Onay akışı pasif ise aktif yap")
    public OnayAkisYonetimiPage onayAkisiPasifIseAktifYap(String onayAkisi) {


        SelenideElement element = $x("//*[@id='onayAkisiYonetimiListingForm:rolDataTable_data']//tr[@role='row']" +
                "//*[normalize-space(text())='" + onayAkisi + "']/ancestor::tr[@data-ri and @role='row']");

        SelenideElement active = element.find("[id$='changeOnayAkisiStatusButton'] [class~='to-active-status-icon']");
        if (active.is(visible)) {
            active.click();
            islemOnayi("Evet");
            Allure.addAttachment("Onay akışı pasif olduğu için aktif yapıldı.", "");
        } else
            System.out.println("Onay akışı aktif zaten");

        /*if (tblOnayAkisListesi
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
*/
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

    @Step("Ekranı kapat")
    public OnayAkisYonetimiPage ekraniKapat() {
        btnEkranKapat.click();
        islemPenceresiKapatmaOnayiPopup("Kapat");
        return this;
    }

    @Step("Filtre aç")
    public OnayAkisYonetimiPage filtreAc() {
        filtreAcmaKapatma.click();
        return this;
    }

    @Step("Güncelle")
    public OnayAkisYonetimiPage guncelle() {
        btnGuncelle.click();
        return this;
    }

    @Step("Onay akışı sil")
    public OnayAkisYonetimiPage silOnayAkisiItem2() {
        if (btnSilOnayAkisiItem2.isDisplayed()) {
            btnSilOnayAkisiItem2.click();
        }
        return this;
    }

    @Step("Onay akışı işlemleri kullanıcı sil")
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

    @Step("Kullanici yerlerini değiştir")
    public OnayAkisYonetimiPage kullaniciYerleriDegistir(String kullanici1, String kullanici2) throws InterruptedException {

        Thread.sleep(1000);
        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici1))
                .get(0)
                .shouldBe(exist)
                .$("[class$='ui-icon-arrowthick-1-s']").click();

        Thread.sleep(1000);
        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici2))
                .get(0)
                .shouldBe(exist)
                .$("[class$='ui-icon-arrowthick-1-s']").click();

        return this;
    }

    @Step("Kullanici en alta getirme")
    public OnayAkisYonetimiPage onayAkisiKullaniciEnAlttaGetirme(String kullanici) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici))
                .get(0)
                .shouldBe(exist)
                .$("[class$='ui-icon-arrowthick-1-s']").click();

        return this;
    }

    @Step("Kullanıcıya kullanıcı tipi seç")
    public OnayAkisYonetimiPage kullaniciyaKullaniciTipiSec(String kullanici, String secimTipi) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullanici))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='onayAkisiYonetimiIslemTipi']")
                .selectOptionByValue(secimTipi);

        return this;
    }

    @Step("Onay akışı kullanıcı adı ve tipi kontrolu")
    public OnayAkisYonetimiPage onayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(kullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='onayAkisiYonetimiIslemTipi']")
                .shouldHave(value(kullaniciTipi));

        return this;
    }


    @Step("Onay akışı vekalet tarihi kontrolu")
    public OnayAkisYonetimiPage onayAkisiVekaletTarihiKontrol(String vekaletTarihi) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(vekaletTarihi))
                .get(0)
                .shouldBe(exist);

        return this;
    }


    @Step("Onay akışı vekil kullanıcı adı ve tipi kontrolu")
    public OnayAkisYonetimiPage onayAkisiVekilKullaniciKontrol(String vekilKullaniciAdi, String vekilKullaniciTipi) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(vekilKullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='onayAkisiYonetimiIslemTipi']")
                .shouldHave(value(vekilKullaniciTipi));

        return this;
    }


    @Step("Onay akışı asil kullanıcı adı ve tipi kontrolu")
    public OnayAkisYonetimiPage onayAkisiAsilKullaniciKontrol(String asilkullaniciAdi, String asilkullaniciTipi) {

        trOnayAkisiEkleKullanicilar
                .filterBy(text(asilkullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='onayAkisiYonetimiIslemTipi']")
                .shouldHave(value(asilkullaniciTipi));

        return this;
    }

    @Step("Onay Akışı İşlemlerinde birim dışı kullanıcı görüntülenmeme kontrolu")
    public OnayAkisYonetimiPage kullanicilarAlanindaGoruntulenmemeKontrolu(String kullanici) {

        comboLov(txtKullanicilar).type(kullanici).getTitleItems().filterBy(exactText(kullanici)).shouldHaveSize(0);
        System.out.println("Kullanıcılar alanında " + kullanici + ": Birim dışı kullanıcının görüntülenmediği görülür.");

        return this;
    }

    @Step("Birim seç")
    public OnayAkisYonetimiPage birimTikla() {
        btnBirim.shouldBe(visible);
        btnBirim.click();
        return this;
    }

    //İşlem mesajındaki yazılar tam yüklenmiyor hata veriyor. O yüzden 1 sn bekleniyor.
    public OnayAkisYonetimiPage islemMesajiBekle() {
        Selenide.sleep(1000);
        return this;
    }

    @Step("Onay Akışı data resetleme")
    public OnayAkisYonetimiPage onayAkisiDataResetleme(String yeniOnayAkisi, String eskiOnayAkisi, String basariMesaji) {

        filtredeAdDoldur(eskiOnayAkisi);
        ara();

        if (tblOnayAkisListesi
                .filterBy(text(eskiOnayAkisi)).size() == 1) {
            tblOnayAkisListesi
                    .filterBy(text(eskiOnayAkisi))
                    .first()
                    .shouldBe(exist)
                    .$("[id$='updateRolButton']").click();

            onayAkisiIslemleriAdDoldur(yeniOnayAkisi);
            onayAkisiIslemleriKaydet();
            islemMesaji().basariliOlmali(basariMesaji);
        } else {
            Allure.addAttachment("Data düzgün, resetleme yapılmadı.", "");
        }

        return this;
    }

    @Step("Onay Akışı alan kontrolleri")
    public OnayAkisYonetimiPage onayAkisiAlanKontrolleri() {

        Assert.assertEquals(chkKoordineli.isDisplayed(), true);
        Allure.addAttachment("Koordineli alan kontrolu başaralı.", "");

        Assert.assertEquals(btnBirim.isDisplayed(), true);
        Allure.addAttachment("Birim alan kontrolu başaralı.", "");

        return this;
    }
}
