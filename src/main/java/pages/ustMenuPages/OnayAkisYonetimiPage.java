package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@SuppressWarnings("unused")
public class OnayAkisYonetimiPage extends BaseLibrary {
//    private UstMenu ustMenu;

    private SelenideElement btnVarsayilanYap = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:0:default"));
    private SelenideElement btnPasifYap = $(By.id("onayAkisiYonetimiListingForm:rolDataTable:0:default"));
    private SelenideElement onayAkisAlani;
    private SelenideElement tblGelenEvrak = $(By.xpath(".//*[@id='mainInboxForm:inboxDataTable_data']/tr[1]/td[2]/div"));
    private SelenideElement btnCevapYaz = $(By.xpath(".//*[@id='mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton']"));
    private SelenideElement btnAra = $(By
            .id("onayAkisiYonetimiListingForm:filterPanel:searchEntitiesButtonOnayAkisiYonetimiListing"));
    private SelenideElement homePageButton = $(By.id("j_idt325"));


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
