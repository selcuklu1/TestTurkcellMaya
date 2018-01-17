package tests.KisayolTuslari;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.newPages.EvrakOlusturPage;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.EvrakPageButtons;
import pages.pageComponents.TextEditor;
import pages.pageComponents.UstMenu;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageComponents.tabs.EditorTab;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageData.UstMenuData.*;
//import pages.ustMenuPages.EvrakOlusturPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kısayol Tuşları" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class KisayolTuslariTest extends BaseTest {

    MainPage mainPage = new MainPage();
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");

    @BeforeMethod
    public void loginBeforeTest() throws Exception {
        login(user1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Olur Oluştur ekranını açma")
    public void TS1952a() throws InterruptedException {
        new EvrakOlusturPage().openPage();

        String sayfaAdi = EvrakIslemleri.OlurYazisiOlustur.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "o");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Kullanıcı Yönetimi ekranını açma")
    public void TS1952b() throws InterruptedException {
        new EvrakOlusturPage().openPage();

        String sayfaAdi = KullaniciIslemleri.KullaniciYonetimi.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "u");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Giden Evrak Kayıt ekranını açma")
    public void TS1952c() throws InterruptedException {
        new EvrakOlusturPage().openPage();

        String sayfaAdi = EvrakIslemleri.GidenEvrakKayit.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "ı");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Evrak Oluştur ekranını açma")
    public void TS1952e() throws InterruptedException {
        new OlurYazisiOlusturPage().openPage();

        String sayfaAdi = EvrakIslemleri.EvrakOlustur.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "e");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Evrak Arama ekranını açma")
    public void TS1952f() throws InterruptedException {

        new EvrakOlusturPage().openPage();

        String sayfaAdi = EvrakIslemleri.EvrakArama.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "a");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Genel Evrak Raporu ekranını açma")
    public void TS1952g() throws InterruptedException {

        new EvrakOlusturPage().openPage();

        String sayfaAdi = Raporlar.GenelEvrakRaporu.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "n");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Gelen Evrak Kayıt ekranını açma")
    public void TS1952h() throws InterruptedException {

        new EvrakOlusturPage().openPage();

        String sayfaAdi = EvrakIslemleri.GelenEvrakKayit.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "g");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1952: Kısayol tuşları kullanarak Karar Yazısı Oluştur ekranını açma")
    public void TS1952i() throws InterruptedException {
        new EvrakOlusturPage().openPage();

        String sayfaAdi = EvrakIslemleri.KararYazisiOlustur.getName();
        CharSequence kisayol = Keys.chord(Keys.LEFT_SHIFT, "k");

        step1(sayfaAdi, kisayol);
        step2(sayfaAdi, kisayol);
        step3(sayfaAdi, kisayol);
        step4(sayfaAdi, kisayol);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TS1955: Kısayol Tuşlarının Tooltipleri", enabled = true)
    public void TS1955() throws InterruptedException {
        ustMenuTooltip(EvrakIslemleri.EvrakOlustur.getGroupName(), EvrakIslemleri.EvrakOlustur.getName(), "(Shift + E)");
        ustMenuTooltip(EvrakIslemleri.GidenEvrakKayit.getGroupName(), EvrakIslemleri.GidenEvrakKayit.getName(), "(Shift + I)");
        ustMenuTooltip(EvrakIslemleri.GelenEvrakKayit.getGroupName(), EvrakIslemleri.GelenEvrakKayit.getName(), "(Shift + G)");
        ustMenuTooltip(EvrakIslemleri.EvrakArama.getGroupName(), EvrakIslemleri.EvrakArama.getName(), "(Shift + A)");
        ustMenuTooltip(EvrakIslemleri.OlurYazisiOlustur.getGroupName(), EvrakIslemleri.OlurYazisiOlustur.getName(), "(Shift + O)");
        ustMenuTooltip(EvrakIslemleri.KararYazisiOlustur.getGroupName(), EvrakIslemleri.KararYazisiOlustur.getName(), "(Shift + K)");
        ustMenuTooltip(KullaniciIslemleri.KullaniciYonetimi.getGroupName(), KullaniciIslemleri.KullaniciYonetimi.getName(), "(Shift + U)");
        ustMenuTooltip(Raporlar.GenelEvrakRaporu.getGroupName(), Raporlar.GenelEvrakRaporu.getName(), "(Shift + N)");
        ustMenuTooltip(Raporlar.PersonelveAcikEvrakIstatistigi.getGroupName(), Raporlar.PersonelveAcikEvrakIstatistigi.getName(), "(Shift + P)");
    }

    @Step("{menuButon} menü tooltip {beklenenTooltip} olmalı")
    private void ustMenuTooltip(String ustMenuGrup, String menuButon, String beklenenTooltip){
        SelenideElement menuGrupButton = $(By.xpath("//div[@id='layoutTopMenuContainer']//button[.='" + ustMenuGrup + "']"));
        String altMenuDialogId = (menuGrupButton.attr("id")).replace("ustMenuEleman", "altMenuDialog");

        SelenideElement menuButtonElement = $(By.id(altMenuDialogId)).$(By.linkText(menuButon));
        SelenideElement tooltipElement = $(By.id("tiptip_content"));

        if (menuButtonElement.is(Condition.not(Condition.visible)))
            menuGrupButton.click();

        menuButtonElement.hover();

        String tooltip = $(By.id("tiptip_content")).innerText();
        System.out.println(tooltip);
        tooltipElement.shouldHave(Condition.text(beklenenTooltip));
        //Assert.assertEquals(tooltip, beklenenTooltip);
    }

    @Step("Herhangi bir editör ekranını aç, editörü tıkla, \"Shift+O\" kısayolunu tıkla. Olur Oluştur ekranının açılmadığı görülür")
    public void step1(String sayfaAdi, CharSequence kisayol){
        new EditorTab().openTab().getEditor().editor().sendKeys(kisayol);
        Selenide.switchTo().window(0);
        Assert.assertFalse(mainPage.getFooterPageButton(sayfaAdi).exists(), sayfaAdi + " ekranının açılmadığı görülür");
    }

    @Step("Herhangi bir pdf ekranını aç, PDF i tıkla, \"Shift+O\" kısayolunu tıkla. Olur Oluştur ekranının açılmadığı görülür")
    public void step2(String sayfaAdi, CharSequence kisayol){
        new EvrakPageButtons().pdfOnizlemeTikla();
        WebDriver driver = Selenide.switchTo().window("htmlToPdfServlet");
        $("#print").sendKeys(kisayol);
        driver.close();
        Selenide.switchTo().window(0);
        Assert.assertFalse(mainPage.getFooterPageButton(sayfaAdi).exists(), sayfaAdi + " ekranının açılmadığı görülür");
    }

    @Step("Herhangi bir input alanını tıkla \"Shift+O\" kısayolunu tıkla. Olur Oluştur ekranının açılmadığı görülür")
    public void step3(String sayfaAdi, CharSequence kisayol){
        new BilgilerTab().openTab().getKonuKodu().sendKeys(kisayol);
        Assert.assertFalse(mainPage.getFooterPageButton(sayfaAdi).exists(), sayfaAdi + " ekranının açılmadığı görülür");
    }

    @Step("Editör, pdf veya input alanlarına focuslu olmadan \"Shift+O\" kısayolunu tıkla. Olur Oluştur ekranının AÇILDIĞI görülür")
    public void step4(String sayfaAdi, CharSequence kisayol){
        new BilgilerTab().getTabButton().sendKeys(kisayol);
        Assert.assertTrue(mainPage.getFooterPageButton(sayfaAdi).exists(), sayfaAdi + " ekranının açıldığı görülür");
    }



    
}