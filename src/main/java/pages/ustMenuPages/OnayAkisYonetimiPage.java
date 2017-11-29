package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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

    private SelenideElement btnVarsayilanYap = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:0:default"));
    private SelenideElement btnPasifYap = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:0:default"));
    private SelenideElement onayAkisAlani;
    private SelenideElement tblGelenEvrak = $(By.xpath(".//*[@id='mainInboxForm:inboxDataTable_data']/tr[1]/td[2]/div"));
    private SelenideElement btnCevapYaz = $(By.xpath(".//*[@id='mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton']"));
    private SelenideElement btnAra = $(By
            .id("onayAkisiYonetimiListingForm:filterPanel:searchEntitiesButtonOnayAkisiYonetimiListing"));
    private SelenideElement homePageButton = $(By.id("j_idt325"));
    private SelenideElement btnOnayAkisiYeni = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:addNewRolButton"));

    private SelenideElement txtOnayAkisiAd =$(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiAkisAdiInput"));
    private SelenideElement txtOnayAkisiKullanicilar = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText"));
    private ElementsCollection cmbImzacıSon = $$("[id$='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimDataTable'] table tr select");
    private BelgenetElement txtOnayAkisiIslemleriKullanicilar = comboLov("[id='onayAkisiYonetimiEditorForm:onayAkisiYonetimiKullaniciBirimLov:LovText']");
    private SelenideElement btnOnayAkisiIslemleriKaydet = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiEditorKaydetId"));
    private SelenideElement txtAd = $(By.id("onayAkisiYonetimiEditorForm:onayAkisiYonetimiAkisAdiInput"));
    @Step("Onay akışı sayfası aç")
    public OnayAkisYonetimiPage openPage(){
        ustMenu("Onay Akışı Yönetimi");
        return this;
    }
    @Step("Onay Akışı işlemler kaydet")
    public OnayAkisYonetimiPage onayAkisiIslemleriKaydet(){
        btnOnayAkisiIslemleriKaydet.click();
        return this;
    }
    @Step("Onay akışı işlemleri kullanıcılar alanı doldur")
    public OnayAkisYonetimiPage onayAkisiIslemlerKullanicilarDoldur(String kullanici){
        txtOnayAkisiIslemleriKullanicilar.type(kullanici).titleItems().first().click();
                //selectLov(kullanici);
        return this;
    }
    @Step("İmzacı seç")
    public  OnayAkisYonetimiPage imzacıSonSec(String value) {
        cmbImzacıSon.last().selectOption(value);
        return this;
    }
    @Step("Onay Akışı işlemleri ad doldur")
    public OnayAkisYonetimiPage onayAkisiIslemleriAdDoldur(){
        String random = createRandomNumber(7);
        txtAd.setValue(random);
        return this;
    }
    @Step("Ad alanı alınır")
    public String adCek(){
       String ad = txtAd.getValue();
        return ad;
    }
    @Step("Onay akışı yeni")
    public OnayAkisYonetimiPage onayAkisiYeni(){
        btnOnayAkisiYeni.click();
        return this;
    }

    public OnayAkisYonetimiPage varsayilanButonu() {
        btnAra.click();

        // if (isElementExist(varsayilanYapButton))
        btnVarsayilanYap.click();
        return this;
    }

    public OnayAkisYonetimiPage pasifYapButonu() {
        btnAra.click();

//        if (isElementExist(pasifYapButton))
        btnPasifYap.click();
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
