package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

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

    private SearchTable searchTable = new SearchTable($("#birimSablonForm [id$='sablonDataTable']"));


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

            Assert.assertEquals(getRowsBirimSablonlari().size(), $$("[id$='sablonListesiDetayButton_id']").size(),
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


    @Step("Birim Şablonlarında şablonu bul")
    public SelenideElement sablonuBul(String sablonAdi){
        return searchTable.searchStartFromLastPage(true).findRows(text(sablonAdi)).shouldHaveSize(1).useFirstFoundRow().getFoundRow();
                /*.findRowsInAllPagesByTextFromLast(sablonAdi)
                .shouldHaveSize(1)
                .first();*/
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

    public void tumSablonlariSil() {
        int i = 0;
        while (true) {
            if (!$$(rowsBirimSablonlari).get(i).exists())
                break;

            $$(rowsBirimSablonlari).get(i).$("[id$='sablonListesiDetayButton_id']").click();
            btnSil.click();
            $("#sablonSilDialog button").click();
            boolean b = islemMesaji().isBasarili();
            if(!b)
                i++;
        }
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
        return searchTable.findRows().shouldHave(sizeGreaterThan(0)).useFirstFoundRow().getColumn(1).text();
//        return searchTable.getColumn(searchTable.getRows().shouldHave(sizeGreaterThan(0)).get(satir), 1).text();
    }

    @Step("Alt birimler görsün mü seç")
    public BirimIcerikSablonlarPage altBirimlerGorsunMuSec(String value) {
        lovKullanilacakBirimler.getSelectedItems().last().$(By.tagName("select")).selectOption(value);
        return this;
    }

    @Step("Alt Birimler Görsün mü değer kontol")
    public BirimIcerikSablonlarPage altBirimlerGorsunMuDegerKontrol(String value) {
        lovKullanilacakBirimler.getSelectedItems().last().$(By.tagName("select")).getSelectedOption()
                .shouldHave(text(value));
        return this;
    }

    @Step("Önizleme kontrolü")
    public BirimIcerikSablonlarPage pdfOnzilemeTextKontol(Condition... conditions) {
        btnEvrakOnizleme.click();

        WebDriver driver = switchTo().window("htmlToPdfServlet");
//        sleep(5000);
        $(".textLayer").shouldBe(visible);
        for (Condition condition : conditions) {
            for (int i = 0; i < 10; i++) {
                if (!$(".textLayer").text().isEmpty() || condition.equals(text("")))
                    break;

                if (!driver.getTitle().equals("htmlToPdfServlet"))
                    driver = switchTo().window("htmlToPdfServlet");

                System.out.println($(".textLayer").text());
                sleep(1000);
            }
            Allure.addAttachment("Tekst kontrol", $(".textLayer").text());
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

    @Step("Şablon bilgileri kontolü")
    public BirimIcerikSablonlarPage sablonBilgileriKontrolu(String sablonAdi, String kullanilacakBirimi, String altBirimlerGorsunMu, String editorText){
        getTxtSablonAdi().shouldHave(value(sablonAdi));
        SelenideElement birim = getLovKullanilacakBirimler().getSelectedItems().filterBy(text(kullanilacakBirimi)).shouldHaveSize(1).first();
        birim.$("select").getSelectedOption().shouldHave(text(altBirimlerGorsunMu));
        getEditor().editorShouldHave(text(editorText));
        return this;
    }

}
