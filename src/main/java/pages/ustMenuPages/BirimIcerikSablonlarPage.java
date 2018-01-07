package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class BirimIcerikSablonlarPage extends MainPage {

    private SelenideElement txtSablonAdi = $(By.id("birimSablonForm:sablonAdiText_id"));
    private BelgenetElement lovKullanilacakBirimler = comboLov(By.id("birimSablonForm:sablonLov_id:LovText"));
    private SelenideElement selEvrakTipi = $x("//label[normalize-space(text())='Evrak Tipi']/ancestor::tr[1]//select");
    //$("#birimSablonForm select:first-child")
    private SelenideElement btnYeniSablonOlustur = $(By.id("birimSablonForm:sablonIslemYeniButton_Id"));
    private SelenideElement btnKaydet = $(By.id("birimSablonForm:sablonIslemKaydetButton_id"));
    private SelenideElement btnSil = $(By.id("birimSablonForm:sablonIslemSilButton_id"));
    private SelenideElement btnEvrakOnizleme = $(By.id("birimSablonForm:sablonOnizlemeButton_id"));

    private SelenideElement tblBirimSablonlari = $("[id^='birimSablonForm'][id$='sablonDataTable']");
    private SelenideElement btnBirimSablonlariNext = $("[id^='birimSablonForm'][id$='sablonDataTable'] thead span[class~='ui-paginator-next']");
    private SelenideElement btnBirimSablonlariPrev = $("[id^='birimSablonForm'][id$='sablonDataTable'] thead span[class~='ui-paginator-prev']");
    private SelenideElement btnBirimSablonlariLast = $("[id^='birimSablonForm'][id$='sablonDataTable'] thead span[class~='ui-icon-seek-end']");
    //Detay butonları row sayısına eşit olmalı
    private By rowsBirimSablonlari = By.cssSelector("[id$='sablonDataTable'] tbody tr[role='row']");
    private By rowsBirimSablonlariSablonAdi = By.cssSelector("[id$='sablonDataTable'] tbody tr[role='row'] td:nth-child(2)");
    private ElementsCollection btnDetayInEachRow = $$("[id$='sablonListesiDetayButton_id']");
    private TextEditor editor = new TextEditor();

    public ElementsCollection getRowsBirimSablonlariSablonAdi() {
        return $$(rowsBirimSablonlariSablonAdi);
    }

    public SelenideElement getTxtSablonAdi() {
        return txtSablonAdi;
    }

    public BelgenetElement getLovKullanilacakBirimler() {
        return lovKullanilacakBirimler;
    }

    public SelenideElement getSelEvrakTipi() {
        return selEvrakTipi;
    }

    public SelenideElement getBtnYeniSablonOlustur() {
        return btnYeniSablonOlustur;
    }

    public SelenideElement getBtnKaydet() {
        return btnKaydet;
    }

    public SelenideElement getBtnSil() {
        return btnSil;
    }

    public SelenideElement getBtnEvrakOnizleme() {
        return btnEvrakOnizleme;
    }

    public SelenideElement getTblBirimSablonlari() {
        return tblBirimSablonlari;
    }

    public ElementsCollection getRowsBirimSablonlari() {
        return $$(rowsBirimSablonlari);
    }

    public ElementsCollection getBtnDetayInEachRow() {
        return btnDetayInEachRow;
    }

    public TextEditor getEditor() {
        return editor;
    }

    public BirimIcerikSablonlarPage openPage() {
        ustMenu(UstMenuData.YonetimSayfalari.BirimIcerikSablonlari);
        return this;
    }

    public BirimIcerikSablonlarPage yeniSablonOlustur(String sablonAdi, String birim, boolean altBirimlerGorsun, String text) {

        String altBirimler = altBirimlerGorsun ? "ALT BİRİMLER GÖRSÜN" : "ALT BİRİMLER GÖRMESİN";

        btnYeniSablonOlustur.click();

        txtSablonAdi.setValue(sablonAdi);

        lovKullanilacakBirimler.type(birim).getTitleItems()
                .filterBy(exactText(birim))
                .first()
                .click();

        lovKullanilacakBirimler.closeTreePanel()
                .getSelectedItems().last()
                .$(By.tagName("select")).selectOption(altBirimler);

        getEditor()
                .type(text).type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Birim");

        getBtnEvrakOnizleme().click();

        switchTo().window(1);

        String baslik = "T.C."
                + "\nGENEL MÜDÜRLÜK MAKAMI"
                + "\nBİLİŞİM HİZMETLERİ GENEL MÜDÜR YARDIMCISI"
                + "\nYAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ"
//                + "\nOptiim Birim"
                ;
        try {
            $(".textLayer").shouldHave(textCaseSensitive(baslik));
            $(".textLayer").shouldHave(textCaseSensitive(text));
            $(".textLayer").shouldHave(textCaseSensitive("(@BIRIM)"));
        } catch (Exception e) {
        } finally {
            switchTo().window(1).close();
        }

        switchTo().window(0);

        btnKaydet.click();
        return this;
    }

    public boolean sablonExistInTable(String sablonAdi) {
        while (true) {
            $$(rowsBirimSablonlari).last().shouldBe(visible);
            if ($$(rowsBirimSablonlariSablonAdi).filterBy(and("Filter by visible and text"
                    , visible
                    , exactText(sablonAdi))).size() == 1) {
                return true;
            }

            if (btnBirimSablonlariNext.has(cssClass("ui-state-disabled")))
                return false;

            btnBirimSablonlariNext.click();
        }
    }

    @Step("Her şablonun Detay butonunun bulunmalı")
    public BirimIcerikSablonlarPage editButtonInAllRows() {
        while (true) {

            Assert.assertEquals(getRowsBirimSablonlari().size(), getBtnDetayInEachRow().size(),
                    "Birim Şablonlar tablosunda her satırda Detay butonu olması");

            if (btnBirimSablonlariNext.has(cssClass("ui-state-disabled")))
                break;

            btnBirimSablonlariNext.click();
        }
        return this;
    }

    public int sablonExistCountInTable(String sablonAdi) {
        int count = 0;
        while (true) {
            if ($$(rowsBirimSablonlariSablonAdi).filterBy(and("Filter by visible and text"
                    , visible
                    , exactText(sablonAdi))).size() == 1) {
                count++;
            }

            if (btnBirimSablonlariNext.has(cssClass("ui-state-disabled")))
                break;

            btnBirimSablonlariNext.click();
        }

        return count;
    }

    @Step("Şablonu bul")
    public SelenideElement findSablonRowInTable(String sablonAdi) {
        $$(rowsBirimSablonlari).first().shouldBe(visible);
        ElementsCollection rows = $$(rowsBirimSablonlari).filterBy(and("Filter by visible and text"
                , visible
                , text(sablonAdi)));
        if (rows.size() == 1) {
            return rows.first();
        }

        btnBirimSablonlariLast.click();
        while (true) {
            $$(rowsBirimSablonlari).last().shouldBe(visible);
            rows = $$(rowsBirimSablonlari).filterBy(and("Filter by visible and text"
                    , visible
                    , text(sablonAdi)));
            if (rows.size() == 1)
                return rows.first();

            if (btnBirimSablonlariPrev.has(cssClass("ui-state-disabled")))
                throw new NotFoundException(sablonAdi + " şablon bulunamadı");

            btnBirimSablonlariPrev.click();
        }


        /*while (true) {
            $$(rowsBirimSablonlari).first().shouldBe(visible);
            ElementsCollection rows = $$(rowsBirimSablonlari).filterBy(and("Filter by visible and text"
                    , visible
                    , text(sablonAdi)));
            if (rows.size() == 1) {
                return rows.first();
            }

            if (btnBirimSablonlariNext.has(cssClass("ui-state-disabled")))
                throw new NotFoundException(sablonAdi + " şablon bulunamadı");

            btnBirimSablonlariNext.click();
        }*/
    }


    private boolean birimSablonlardaAra(String sablonAdi) {
        return findInTable(sablonAdi) != null;
    }

    private SelenideElement findInTable(String sablonAdi) {
        while (true) {
            List<SelenideElement> col = $$(rowsBirimSablonlariSablonAdi);
            for (SelenideElement row : col) {
                row.shouldBe(visible);
                if (row.text().trim().equals(sablonAdi))
                    return row.parent();
            }
            if (!btnBirimSablonlariNext.has(cssClass("ui-state-disabled")))
                btnBirimSablonlariNext.click();
            else
                return null;
        }
    }

    @Step("Şablon sil")
    public void sablonuSil(String sablonAdi) {
        SelenideElement row = findSablonRowInTable(sablonAdi);
        row.shouldBe(visible).$("[id$='sablonListesiDetayButton_id']").click();
        btnSil.click();
        $("#sablonSilDialog button").click();
    }

    public SelenideElement sablonuSilD(String sablonAdi) {

        while (true) {
            $$(rowsBirimSablonlari).get(2).$("[id$='sablonListesiDetayButton_id']").click();
            btnSil.click();
            $("#sablonSilDialog button").click();
        }
//        while (true) {
//            for (SelenideElement row : $$(rowsBirimSablonlari)) {
//                row.shouldBe(visible);
//                if (!row.text().trim().equals(sablonAdi)) {
//                    row.$("button").click();
//                    btnSil.click();
//                    $("#j_idt4716").click();
//                }
////                    return row.parent();
//            }
//            if (!btnBirimSablonlariNext.has(cssClass("ui-state-disabled")))
//                btnBirimSablonlariNext.click();
//            else
//                return null;
//        }
    }

    @Step("Yeni şablon oluştur butona tıkla")
    public BirimIcerikSablonlarPage yeniSablonOlustur() {
        btnYeniSablonOlustur.click();
        return this;
    }

    @Step("Şablon adı doldur")
    public BirimIcerikSablonlarPage sablonAdiDoldur(String sablonAdi) {
        txtSablonAdi.setValue(sablonAdi);
        return this;
    }

    @Step("Kullanılack Birimi seç")
    public BirimIcerikSablonlarPage kullanilacakBirimiSec(String birim) {
        lovKullanilacakBirimler.selectLov(birim);
        return this;
    }

    @Step("Kullanılack Birimi seç")
    public BirimIcerikSablonlarPage kullanilacakBirimiSec(String birim, Condition condition) {
        lovKullanilacakBirimler.type(birim).getTitleItems().filterBy(condition).first().click();
        lovKullanilacakBirimler.closeTreePanel();
        return this;
    }

    @Step("Kaydet")
    public BirimIcerikSablonlarPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Editöre yaz")
    public BirimIcerikSablonlarPage editoreYaz(CharSequence... keysToSend) {
        getEditor().type(keysToSend);
        return this;
    }

    @Step("Var olan şablonun adını al")
    public String sablonAdiAl(int satir) {
        return getRowsBirimSablonlariSablonAdi().filterBy(visible).get(satir).text();
    }

    @Step("Alt birimler görsün mü seç")
    public BirimIcerikSablonlarPage altBirimlerGorsunMu(String value) {
        lovKullanilacakBirimler.getSelectedItems().last()
                .$(By.tagName("select")).selectOption(value);
        return this;
    }

    @Step("Önizleme kontrolü")
    public BirimIcerikSablonlarPage pdfOnzileme(Condition... conditions) {
        $("#birimSablonForm\\:sablonOnizlemeButton_id").click();
        WebDriver driver = switchTo().window("htmlToPdfServlet");
        for (Condition condition : conditions) {
            $(".textLayer").shouldHave(condition);
        }
        takeScreenshot();
        driver.close();
        switchTo().window(0);

        return this;
    }

    @Step("Evrak Tipi seç")
    public BirimIcerikSablonlarPage evrakTipiSec(String option) {
        selEvrakTipi.selectOption(option);
        return this;
    }

    @Step("Şablonda Detay butona tıkla")
    public BirimIcerikSablonlarPage detayButonaTikla(SelenideElement row) {
        try {
            row.$("[id$='sablonListesiDetayButton_id']").sendKeys("\n");
        } catch (Exception e) {
        }
        row.$("[id$='sablonListesiDetayButton_id']").click();
        return this;
    }
}
