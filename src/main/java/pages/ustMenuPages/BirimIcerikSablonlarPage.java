package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class BirimIcerikSablonlarPage extends MainPage {

    private SelenideElement txtSablonAdi = $(By.id("birimSablonForm:sablonAdiText_id"));
    private BelgenetElement lovKullanilacakBirimler = comboLov(By.id("birimSablonForm:sablonLov_id:LovText"));
    private SelenideElement selEvrakTipi = $x("//label[normalize-space(text())='Evrak Tipi']/ancestor::tr[last()]//select[starts-with(@id,'birimSablonForm')]");
    //$("#birimSablonForm select:first-child")
    private SelenideElement btnYeniSablonOlustur = $(By.id("birimSablonForm:sablonIslemYeniButton_Id"));
    private SelenideElement btnKaydet = $(By.id("birimSablonForm:sablonIslemKaydetButton_id"));
    private SelenideElement btnSil = $(By.id("birimSablonForm:sablonIslemSilButton_id"));
    private SelenideElement btnEvrakOnizleme = $(By.id("birimSablonForm:sablonOnizlemeButton_id"));


    private SelenideElement tblBirimSablonlari = $("[id^='birimSablonForm'][id$='sablonDataTable']");
    private SelenideElement btnBirimSablonlariNext = $("[id^='birimSablonForm'][id$='sablonDataTable'] thead span[class~='ui-paginator-next']");
    //Detay butonları row sayısına eşit olmalı
    private ElementsCollection rowsBirimSablonlari = $$("[id$='sablonDataTable'] tbody tr[role='row']");
    private ElementsCollection btnDetayInEachRow = $$("[id$='sablonListesiDetayButton_id']");

    private TextEditor editor = new TextEditor();

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
        return rowsBirimSablonlari;
    }

    public ElementsCollection getBtnDetayInEachRow() {
        return btnDetayInEachRow;
    }

    public TextEditor getEditor() {
        return editor;
    }

//    public SelenideElement editor = $(By.id("cke_birimSablonForm:birimIcerikCkEditor"));

    public BirimIcerikSablonlarPage openPage() {
        new UstMenu().ustMenu("Birim İçerik Şablonları");
        return this;
    }

    public BirimIcerikSablonlarPage yeniSablonOlustur(String sablonAdi, String birim, boolean altBirimlerGorsun) {

        String altBirimler = altBirimlerGorsun ? "ALT BİRİMLER GÖRSÜN" : "ALT BİRİMLER GÖRMESİN";
        String text = "My test text";

        btnYeniSablonOlustur.click();

        txtSablonAdi.setValue(sablonAdi);

        lovKullanilacakBirimler.type(birim).titleItems()
                .filterBy(exactText(birim))
                .first()
                .click();

        lovKullanilacakBirimler.closeLovTreePanel()
                .lastSelectedLov()
                .$(By.tagName("select")).selectOption(altBirimler);

        getEditor()
                .type(text).type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Birim");

        getBtnEvrakOnizleme().click();

        switchTo().window(1);

        SelenideElement textLayer = $(".textLayer");

        String baslik = "T.C."
                + "\nGENEL MÜDÜRLÜK MAKAMI"
                + "\nBİLİŞİM HİZMETLERİ GENEL MÜDÜR YARDIMCISI"
                + "\nYAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ"
//                + "\nOptiim Birim"
                ;
        textLayer.shouldHave(textCaseSensitive(baslik));

        textLayer.shouldHave(textCaseSensitive(text));
        textLayer.shouldHave(textCaseSensitive("(@BIRIM)"));

        switchTo().window(1).close();
        switchTo().window(0);

        btnKaydet.click();
        return this;
    }

    //    @Step("Birim Şablonlarda \"{sablonAdi}\" şablon bulunmalı")
    public boolean birimSablonlardaAra(String sablonAdi) {
        return findInTable(sablonAdi) != null;
    }

    private SelenideElement findInTable(String sablonAdi) {
        while (btnBirimSablonlariNext.isEnabled()) {
            for (SelenideElement row : rowsBirimSablonlari) {
                row.shouldBe(visible);
                if (row.text().contains(sablonAdi)) {
                    return row;
                }
            }
            btnBirimSablonlariNext.click();
        }
        return null;
    }

}
