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

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

@SuppressWarnings("unused")
public class OnayAkisYonetimiPage extends MainPage {
//    private UstMenu ustMenu;

    //Filtre
    BelgenetElement txtFiltreBirim = comboLov(By.id("onayAkisiYonetimiListingForm:filterPanel:birimLov:LovText"));
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
    private SelenideElement txtOnayAkisiAd = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiAkisAdiInput"));
    private SelenideElement txtOnayAkisiKullanicilar = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText"));
    private ElementsCollection cmbImzacıSon = $$("[id$='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable'] table tr select");
    private BelgenetElement txtOnayAkisiIslemleriKullanicilar = comboLov("[id='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText']");
    private SelenideElement btnOnayAkisiIslemleriKaydet = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiEditorKaydetId"));
    private SelenideElement txtAd = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiAkisAdiInput"));
    SelenideElement btnEkranKapat = $(By.cssSelector("[id='window1Dialog'] span[class='ui-icon ui-icon-closethick']"));

    SelenideElement durumAktif = $(By.cssSelector("[id^='onayAkisiYonetimiListingForm:rolDataTable'] [class='true']"));
    SelenideElement durumPasif = $(By.cssSelector("[id^='onayAkisiYonetimiListingForm:rolDataTable'] [class='false']"));
    ElementsCollection tblOnayAkisListesi = $$("[id='onayAkisiYonetimiListingForm:rolDataTable_data'] tr[role=row]");
    SelenideElement tblOnayAkisListesiSelenide = $(By.id("onayAkisiYonetimiListingForm:rolDataTable"));
    SelenideElement btnPasifYap = $(By.xpath("[id$='changeOnayAkisiStatusButton'] [class$='to-passive-status-icon']"));
    SelenideElement btnAktifYap = $(By.xpath("[id$='changeOnayAkisiStatusButton'] [class$='to-active-status-icon']"));

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

    @Step("Onay akışı işlemleri kullanıcılar alanı doldur")
    public OnayAkisYonetimiPage onayAkisiIslemlerKullanicilarDoldur(String kullanici) {
        txtOnayAkisiIslemleriKullanicilar.type(kullanici).titleItems().first().click();
        //selectLov(kullanici);
        return this;
    }

    @Step("İmzacı seç")
    public OnayAkisYonetimiPage imzacıSonSec(String value) {
        cmbImzacıSon.last().selectOption(value);
        return this;
    }

    @Step("Onay Akışı işlemleri ad doldur")
    public OnayAkisYonetimiPage onayAkisiIslemleriAdDoldur() {
        String random = createRandomNumber(7);
        txtAd.setValue(random);
        return this;
    }

    @Step("Ad alanı alınır")
    public String adCek() {
        String ad = txtAd.getValue();
        return ad;
    }

    @Step("Onay akışı yeni")
    public OnayAkisYonetimiPage onayAkisiYeni() {
        btnOnayAkisiYeni.click();
        return this;
    }

    public OnayAkisYonetimiPage varsayilanButonu() {
        btnAra.click();

        // if (isElementExist(varsayilanYapButton))
        btnVarsayilanYap.click();
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
        Assert.assertEquals(txtFiltreBirim.selectedTitles().filterBy(Condition.text(birim)).size(), 1);
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

    @Step("Kayıt görüntülenmeme kontrolu")
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

    public OnayAkisYonetimiPage ekraniKapat() {
        btnEkranKapat.click();
        islemPenceresiKapatmaOnayiPopup("Kapat");
        return this;
    }

    public OnayAkisYonetimiPage filtreAc() {
        filtreAcmaKapatma.click();
        return this;
    }

    public OnayAkisYonetimiPage evrakOlusturEkrani() {
        //   onayAkisAlani = By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:LovSecilen");
  /*      ustMenu = new UstMenu(driver);
        ustMenu.("Evrak Oluştur");
        if (driver.findElement(onayAkisAlani).getText() != "")
            System.out.println("ok");*/
        return this;

    }

    public OnayAkisYonetimiPage evrakOlusturEkraniPasif() {
        // onayAkisAlani = By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:LovSecilen");
     /*   ustMenu = new UstMenu(driver);
        ustMenu.altMenuButtonExpress("Evrak Oluştur");
        if (driver.findElement(onayAkisAlani).getText() == "")
            System.out.println("ok");*/
        return this;

    }

    public OnayAkisYonetimiPage olurYazisiOlusturEkrani() {
      /*  ustMenu = new UstMenu(driver);
        ustMenu.altMenuButtonExpress("Olur/Takrir Yazısı Oluştur");
        onayAkisAlani = By.id("yeniOnayEvrakForm:evrakBilgileriList:13:akisLov:j_idt126");

        if (driver.findElement(onayAkisAlani).getText() != "")
            System.out.println("ok");
*/
        return this;
    }

    public OnayAkisYonetimiPage olurYazisiOlusturEkraniPasif() {
      /*  ustMenu = new UstMenu(driver);
        ustMenu.altMenuButtonExpress("Olur/Takrir Yazısı Oluştur");
        onayAkisAlani = By.id("yeniOnayEvrakForm:evrakBilgileriList:13:akisLov:j_idt126");

        if (driver.findElement(onayAkisAlani).getText() == "")
            System.out.println("ok");
*/
        return this;
    }

    public OnayAkisYonetimiPage kararYazisiOlusturEkrani() {
   /*     ustMenu = new UstMenu(driver);
        ustMenu.altMenuButtonExpress("Karar Yazısı Oluştur");

        onayAkisAlani = By.id("yeniKararEvrakForm:evrakBilgileriList:6:akisLov:j_idt126");

        if (driver.findElement(onayAkisAlani).getText() != "")
            System.out.println("ok");*/

        return this;
    }


    public OnayAkisYonetimiPage kararYazisiOlusturEkraniPasif() {
   /*     ustMenu = new UstMenu(driver);
        ustMenu.altMenuButtonExpress("Karar Yazısı Oluştur");

        onayAkisAlani = By.id("yeniKararEvrakForm:evrakBilgileriList:6:akisLov:j_idt126");

        if (driver.findElement(onayAkisAlani).getText() == "")
            System.out.println("ok");
*/
        return this;
    }


    public OnayAkisYonetimiPage gelenEvrakCevapYaz() {
     /*   click(homePageButton);

        if (isElementExist(gelenEvrakTablo)) {
            click(gelenEvrakTablo);
            click(cevapYazButton);
            onayAkisAlani = By.id("windowCevapEvrakForm:evrakBilgileriList:14:akisLov:j_idt126");
            if (driver.findElement(onayAkisAlani).getText() != "")
                System.out.println("ok");
                   }
*/


        return this;
    }



}
