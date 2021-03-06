package pages.ustMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.assertEquals;
import static pages.pageComponents.belgenetElements.Belgenet.*;


public class KullaniciEvrakDevretPage extends MainPage {

    BelgenetElement txtDevredecekKisi = comboLov(By.id("kullaniciEvrakDevretForm:kisidenLov_id:LovText"));
    SelenideElement btnListele = $(By.id("kullaniciEvrakDevretForm:listeleButton"));
    SelenideElement btnDevret = $(By.id("kullaniciEvrakDevretForm:devretButton"));
    ElementsCollection tableTaslakEvraklar = $$("tbody[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devirTaslakEvraklar_data'] > tr[role='row']");
    BelgenetElement txtDevralacakKisi = comboLov(By.id("devredilenKullaniciDialogForm:kisiyeLov_id:LovText"));
    SelenideElement txtAciklama = $(By.id("devredilenKullaniciDialogForm:evrakDevretAciklamaId"));
    SelenideElement btnDevretTamam = $(By.id("devredilenKullaniciDialogForm:evrakDevretTamamButtonId"));
    ElementsCollection tblGelenEvraklar = $$("[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devredilebilecekEvrakListesi_data'] tr[role='row']");
    SelenideElement btnDevretIptal = $(By.id("devredilenKullaniciDialogForm:evrakDevretIptalButtonId"));
    SelenideElement popUpDevredilemeyenEvraklar = $(By.id("devredilemeyenEvraklarRaporDialogId"));
    ElementsCollection tblDevredilemeyenEvraklar = $$("[id='devredilemeyenEvraklarDatatableId_data'] tr[role='row']");

    public static String clearTurkishChars(String str) {
        String ret = str;
        char[] turkishChars = new char[]{0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
        char[] englishChars = new char[]{'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i = 0; i < turkishChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
        }
        return ret;
    }

    public static String clearHorizantalTabChars(String str) {
        String ret = str;
        char[] horizantalTabChars = new char[]{0x9};
        char[] newChars = new char[]{' '};
        for (int i = 0; i < horizantalTabChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{horizantalTabChars[i]}), new String(new char[]{newChars[i]}));
        }
        return ret;
    }

    @Step("Kullanıcı Evrak Devret sayfası açılır.")
    public KullaniciEvrakDevretPage openPage() {
        ustMenu(UstMenuData.AmirIslemleri.KullaniciEvrakDevret);
        return this;
    }

    @Step("Devredecek Kişi seç: {devredecekKisi}")
    public KullaniciEvrakDevretPage devredecekKisiSec(String devredecekKisi) {
//        txtDevredecekKisi.setValue(devredecekKisi);
        //txtDevredecekKisi.waitUntil(visible, 5000);
        //Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", txtDevredecekKisi);
        txtDevredecekKisi.scrollIntoView(false);
        txtDevredecekKisi.selectLov(devredecekKisi);
        return this;
    }

    @Step("Panel aç: {panelAdi}")
    public KullaniciEvrakDevretPage panelAc(String panelAdi) {
        SelenideElement panelHeader = $x("//h3[.='" + panelAdi + "']");
        panelHeader.waitUntil(visible, 1200000);
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", panelHeader);
        panelHeader.click();
        return this;
    }

    @Step("Listele butonuna tıkla.")
    public KullaniciEvrakDevretPage listele() {
        btnListele.click();
        waitForLoadingJS(WebDriverRunner.getWebDriver(), 120000);
//        $("div[id='bekleyinizStatusDialog']").waitUntil(not(visible),120000);
        return this;
    }

    @Step("Taslak evrak seç")
    public KullaniciEvrakDevretPage taslakEvrakSec(String konu, String gidecegiYer) {

        Boolean isSelected = false;

        SelenideElement currentRow = tableTaslakEvraklar
                .filterBy(text("Konu: " + konu))
                .filterBy(text("Gideceği Yer: " + gidecegiYer))
                .first();

        SelenideElement currentRowCheckBox = currentRow.$(By.xpath(".//div[contains(@class, 'ui-chkbox ui-widget')]"));

        if (currentRowCheckBox.$(By.xpath(".//div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (isSelected == false)
            currentRowCheckBox.click();

        return this;
    }

    @Step("Devralacak Kişi seç: {devralacakKisi}")
    public KullaniciEvrakDevretPage devralacakKisiSec(String devralacakKisi) {
        txtDevralacakKisi.selectLov(devralacakKisi);
        return this;
    }

    @Step("Açıklama doldur: {aciklama}")
    public KullaniciEvrakDevretPage aciklamaDoldur(String aciklama) {
        txtAciklama.setValue(aciklama);
        return this;
    }

    @Step("Açıklama alanı temizlenir.")
    public KullaniciEvrakDevretPage aciklamaTemizle() {
        txtAciklama.clear();
        return this;
    }

    @Step("Devret butonuna tıkla")
    public KullaniciEvrakDevretPage devret() {
        btnDevret.click();
        return this;
    }

    @Step("Devret panelinde Tamam butonuna tıkla.")
    public KullaniciEvrakDevretPage devretTamam() {
        btnDevretTamam.click();
        $("div[id='bekleyinizStatusDialog']").waitUntil(not(visible), 120000);
        return this;
    }

    @Step("Ekran Alan kontrolleri")
    public KullaniciEvrakDevretPage ekranTabKontrolleri() {
        assertEquals($x("//h3[.='Gelen Evraklar']").isDisplayed(), true);
        assertEquals($x("//h3[.='Taslak Evraklar']").isDisplayed(), true);
        assertEquals($x("//h3[.='İmza Bekleyen Evraklar']").isDisplayed(), true);
        assertEquals($x("//h3[.='Paraf Bekleyen Evraklar']").isDisplayed(), true);
        assertEquals($x("//h3[.='Koordine Bekleyen Evraklar']").isDisplayed(), true);
        assertEquals($x("//h3[.='Kontrol Bekleyen Evraklar']").isDisplayed(), true);
        assertEquals($x("//h3[.='Havale Onayına Gelen Evraklar']").isDisplayed(), true);
        assertEquals($x("//h3[.='Teslim Aldıklarım']").isDisplayed(), true);
        assertEquals($x("//h3[.='Kapatma İmzasi Bekleyenler']").isDisplayed(), true);
        assertEquals($x("//h3[.='Kapatma Parafı Bekleyenler']").isDisplayed(), true);
        assertEquals(btnListele.isDisplayed(), true);
        assertEquals(btnDevret.is(disabled), true);
//        btnListele.shouldBe(visible);
//        btnDevret.shouldNotBe(enabled);

        Allure.addAttachment("Kullanıcı Evrak Devret Ekranı açılır. Ekranda şu alanlar görüntülenir.\n", "Devredecek Kişi alanı (\n" +
                "Listele butonu (aktif)\n" +
                "Devret Butonu (pasif)\n" +
                "Gelen Evraklar\n" +
                "Taslak Evraklar\n" +
                "İmza Bekleyen Evraklar\n" +
                "Paraf Bekleyen Evraklar\n" +
                "Koordine Bekleyen Evraklar\n" +
                "Kontrol Bekleyen Evraklar\n" +
                "Havale Onayına Gelen Evraklar\n" +
                "Teslim Aldıklarım\n" +
                "Kapatma İmzası Bekleyenler\n" +
                "Kapatma Parafı Bekleyenler");

        takeScreenshot();

//        Allure.addAttachment(tabGelenEvraklar.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabTaslakEvraklar.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabImzaBekleyenEvraklar.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabParafBekleyenEvraklar.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabKoordineBekleyenEvraklar.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabKontrolBekleyenEvraklar.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabHavaleOnayinaGelenEvraklar.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabTeslimAldiklarim.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabKapatmaImzasiBekleyenler.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(tabKapatmaParafiBekleyenler.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(btnListele.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(btnDevret.text(), "Ekran Kontrolü ok");


        return this;
    }

    @Step("Tabların kontrolleri")
    public KullaniciEvrakDevretPage tabloAlanKontrolleri() {
        String tabText = "";

        for (int i = 1; i < 11; i++) {
            SelenideElement tab = $x("//div[@id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId']/h3[" + i + "]/a");
            String tabName = tab.text();
            tabText = tab.text();
            tabText = tabText.replaceAll("\\s+", "");

            String txt = clearTurkishChars(tabText);
            System.out.println(txt);
            String kontrol = $x("//a[text()='" + tabName + "']/../../h3").getAttribute("aria-expanded");
            System.out.println(kontrol);
            if (kontrol.equals("false"))
                tab.click();
            ElementsCollection tblTab = $$("[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devir" + txt + "_data'] tr[data-ri]");
            int size = tblTab.size();
            if (size > 0) {
                for (int j = 0; j < size; j++) {
                    tblTab.get(j)
                            .$("div[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']")
                            .shouldBe(Condition.visible);
                    tblTab.get(j)
                            .$("button[id^='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devir" + txt + ":" + j + ":j_idt'")
                            .shouldBe(Condition.visible);
                }
                Allure.addAttachment(tabText, tabText + " listesinde kayıt bulundu. Buton ve checkbox kontrolleri yapıldı.");
                tab.click();
            } else {
                Allure.addAttachment(tabText, tabText + " listesi boş.");
                tab.click();
            }

        }

        SelenideElement tab = $x("//div[@id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId']/h3[1]/a");
        tab.click();
        return this;
    }

    @Step("Tablo Evrak Seçimi : {konu}")
    public KullaniciEvrakDevretPage tabloEvrakSecimi(String konu) {
        tblGelenEvraklar
                .filterBy(text(konu)).first()
                .$("div[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }

    @Step("Tablo Evrak Seçimi : {konu}")
    public KullaniciEvrakDevretPage tabloEvrakSecimi(String tabName, String konu) {

//        sleep(5000);
        SelenideElement tabGelenEvrak = $x("//div[@id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId']/h3[1]/a");
        tabGelenEvrak.click();
        sleep(2000);
        ElementsCollection tblTab;
        SelenideElement tab = $x("//a[text()='" + tabName + "']");
        String kontrol = $x("//a[text()='" + tabName + "']/../../h3").getAttribute("aria-expanded");
        System.out.println(kontrol);

        if (kontrol.equals("false"))
            tab.click();

        String tabText = tabName.replaceAll("\\s+", "");
        tabText = clearTurkishChars(tabText);
        System.out.println(tabText);

        if (tabText.equals("GelenEvraklar")) {
            tabText = "devredilebilecekEvrakListesi";
            tblTab = $$("[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:" + tabText + "_data'] tr[data-ri]");

        } else {
            tblTab = $$("[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devir" + tabText + "_data'] tr[data-ri]");

        }
        tblTab
                .filterBy(text(konu)).first()
                .$("div[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }

    @Step("Devralacak popup alan kontrolleri")
    public KullaniciEvrakDevretPage devralacakKisiAlanKontolu() {
        Assert.assertEquals(txtDevralacakKisi.isDisplayed(), true, "Devrelecak kişi texti visible.");
        Assert.assertEquals(txtAciklama.isDisplayed(), true, "Açıklama texti visible.");
        Assert.assertEquals(btnDevretTamam.isDisplayed(), true, "Devret Tamam butonu visible.");
        Assert.assertEquals(btnDevretIptal.isDisplayed(), true, "Devret Iptal butonu visible.");

        Allure.addAttachment($(By.xpath("//label[normalize-space(text())='Devralacak Kişi']")).text(), "Ekran Kontrolü ok");
        Allure.addAttachment($(By.xpath("//label[normalize-space(text())='Açıklama']")).text(), "Ekran Kontrolü ok");
        Allure.addAttachment(btnDevretTamam.text(), "Ekran Kontrolü ok");
        Allure.addAttachment(btnDevretIptal.text(), "Ekran Kontrolü ok");

        takeScreenshot();

        return this;
    }

    @Step("Devredilemeyen Evraklar popup kontrolü")
    public KullaniciEvrakDevretPage popUpDevredilemeyenEvraklarKontrol() {
//        sleep(5000);
        Assert.assertEquals(popUpDevredilemeyenEvraklar.isDisplayed(),true,"Devredilemeyen Evraklar popUp gelmeli.");
        return this;
    }

    @Step("Devredilemeyen Evraklar popup kontrolü")
    public KullaniciEvrakDevretPage devredelimeyenEvraklarEvrakKontrolu(String konu, String aciklama) {
        tblDevredilemeyenEvraklar
                .filterBy(text(konu))
                .filterBy(text(aciklama))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Devredilen Evrak tablo kontrolü : \"{konu}\" , \"{shouldBeExist}\" ")
    public KullaniciEvrakDevretPage tabloEvrakKontrolu(String konu, boolean shouldBeExist) {

        String tabText = "";

        for (int i = 1; i < 11; i++) {
            SelenideElement tab = $x("//div[@id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId']/h3[" + i + "]/a");
            tabText = tab.text();
            tabText = tabText.replaceAll("\\s+", "");

            String txt = clearTurkishChars(tabText);
            System.out.println(txt);
            tab.click();
            ElementsCollection tblTab = $$("[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devir" + txt + "_data'] tr[data-ri]");
            int size = tblTab.size();
            if (size > 0) {
                if (!shouldBeExist)
                    tblTab.filterBy(Condition.text(konu)).shouldHaveSize(0);
                else
                    Allure.addAttachment("Evrak Kontrolü", "Evrak devredilememiştir.");
            }
        }

        return this;
    }

    @Step("Sayfayı kapat")
    public KullaniciEvrakDevretPage panelKapat() {
        $(By.xpath("//span[@class='ui-dialog-title' and text()='Kullanıcı Evrak Devret']/..//span[@class='ui-icon ui-icon-closethick']")).click();
        $(By.id("kapatButton")).click();
        return this;
    }
}

