package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
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

    @Step("Kullanıcı Evrak Devret sayfasını aç")
    public KullaniciEvrakDevretPage openPage() {
        ustMenu(UstMenuData.AmirIslemleri.KullaniciEvrakDevret);
        return this;
    }

    @Step("Devredecek Kişi seç: {devredecekKisi}")
    public KullaniciEvrakDevretPage devredecekKisiSec(String devredecekKisi) {
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
        $("div[id='bekleyinizStatusDialog']").waitUntil(not(visible), 120000);
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
        SelenideElement tabGelenEvraklar = $x("//h3[.='Gelen Evraklar']").shouldBe(visible);
        SelenideElement tabTaslakEvraklar = $x("//h3[.='Taslak Evraklar']").shouldBe(visible);
        SelenideElement tabImzaBekleyenEvraklar = $x("//h3[.='İmza Bekleyen Evraklar']").shouldBe(visible);
        SelenideElement tabParafBekleyenEvraklar = $x("//h3[.='Paraf Bekleyen Evraklar']").shouldBe(visible);
        SelenideElement tabKoordineBekleyenEvraklar = $x("//h3[.='Koordine Bekleyen Evraklar']").shouldBe(visible);
        SelenideElement tabKontrolBekleyenEvraklar = $x("//h3[.='Kontrol Bekleyen Evraklar']").shouldBe(visible);
        SelenideElement tabHavaleOnayinaGelenEvraklar = $x("//h3[.='Havale Onayına Gelen Evraklar']").shouldBe(visible);
        SelenideElement tabTeslimAldiklarim = $x("//h3[.='Teslim Aldıklarım']").shouldBe(visible);
        SelenideElement tabKapatmaImzasiBekleyenler = $x("//h3[.='Kapatma İmzasi Bekleyenler']").shouldBe(visible);
        SelenideElement tabKapatmaParafiBekleyenler = $x("//h3[.='Kapatma Parafı Bekleyenler']").shouldBe(visible);
        btnListele.shouldBe(visible);
        btnDevret.shouldNotBe(enabled);

        Allure.addAttachment("Kullanıcı Evrak Devret Ekranı açılır. Ekranda şu alanlar görüntülenir.\n","Devredecek Kişi alanı (\n" +
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

//        ElementsCollection tblgenel = $$(By.id("'kullaniciEvrakDevretForm:evrakDevretAccordionPanelId'"));

        ElementsCollection tblgenel = $$(By.id("'kullaniciEvrakDevretForm:evrakDevretAccordionPanelId'"));

        for (int i = 2; i < 11; i++) {
            SelenideElement tab = $x("//div[@id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId']/h3[" + i + "]/a");
            tabText = tab.text();
            tabText = tabText.replaceAll("\\s+", "");

            String txt = clearTurkishChars(tabText);
            System.out.println(txt);
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
                Allure.addAttachment(tabText, tabText+" listesinde kayıt bulundu. Buton ve checkbox kontrolleri yapıldı.");
            } else
                Allure.addAttachment(tabText, tabText+" listesi boş.");
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

    @Step("Devralacak popup alan kontrolleri")
    public KullaniciEvrakDevretPage devralacakKisiAlanKontolu() {
        txtDevralacakKisi.shouldBe(visible);
        txtAciklama.shouldBe(visible);
        btnDevretTamam.shouldBe(visible);
        btnDevretIptal.shouldBe(visible);

        Allure.addAttachment($(By.xpath("//label[normalize-space(text())='Devralacak Kişi']")).text(),"Ekran Kontrolü ok");
        Allure.addAttachment($(By.xpath("//label[normalize-space(text())='Açıklama']")).text(),"Ekran Kontrolü ok");
        Allure.addAttachment(btnDevretTamam.text(),"Ekran Kontrolü ok");
        Allure.addAttachment(btnDevretIptal.text(),"Ekran Kontrolü ok");

        return this;
    }

    @Step("Devredilemeyen Evraklar popup kontrolü")
    public KullaniciEvrakDevretPage popUpDevredilemeyenEvraklarKontrol() {
        popUpDevredilemeyenEvraklar.shouldBe(visible);
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

    @Step("Sayfayı kapat")
    public KullaniciEvrakDevretPage panelKapat() {
        $(By.xpath("//span[@class='ui-dialog-title' and text()='Kullanıcı Evrak Devret']/..//span[@class='ui-icon ui-icon-closethick']")).click();
        $(By.id("kapatButton")).click();
        return this;
    }
}

