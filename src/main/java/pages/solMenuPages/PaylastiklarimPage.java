package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class PaylastiklarimPage extends MainPage {

    // dolar işareti $=findElement anlamına gelir, $$=findElements

    /*
    SelenideElement btnAddNewPul = $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton"));
    SelenideElement cmbPostaTipi = $(By.id("pulYonetimiEditorForm:postaTipiSelectbox"));
    SelenideElement txtGramaj = $(By.id("pulYonetimiEditorForm:agirlikInput"));
    SelenideElement txtTutar = $(By.id("pulYonetimiEditorForm:tutarInput"));
    SelenideElement chkYurtDisi = $(By.id("pulYonetimiEditorForm:yurtDisiCheckbox_input"));
    SelenideElement txtNum = $(By.id("pulYonetimiEditorForm:numInput"));
    SelenideElement btnSavePul = $(By.id("pulYonetimiEditorForm:savePulButton"));
    */

    ElementsCollection paylastiklarimList = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPaylasTab = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));
    SelenideElement txtKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    //SelenideElement lblIlkisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:lovTree:0"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    SelenideElement btnTreeKapat = $(By.id("mainPreviewForm:evrakPaylasKisiLov:lovTreePanelKapat"));

    @Step("Paylaştıklarım sayfası aç")
    public PaylastiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Paylastiklarim);
        return this;
    }

    @Step("Satır seç")
    public PaylastiklarimPage satirSec(int _satirIndex) {
        paylastiklarimList.get(_satirIndex).click();
        return this;
    }
    @Step("Evrak Önizleme Seç")
    public PaylastiklarimPage evrakOnizlemeTabSec(String _tabAdi) {
        List<WebElement> tabs = $("ul[role='tablist']").findElements(By.partialLinkText(_tabAdi));
        if (tabs.size() > 0) {
            //$("ul[role='tablist']").findElements(By.partialLinkText(_tabAdi);
            $("ul[role='tablist']").$$(By.partialLinkText(_tabAdi)).get(0).click();
        }
        return this;
    }
    @Step("Paylaş tab")
    public PaylastiklarimPage paylasTabTikla() {
        btnPaylasTab.click();
        return this;
    }
    @Step("Kişiler seçilir")
    public PaylastiklarimPage kisilerSec(String _kisiAdi, int[] _Sira) {
        txtKisi.setValue(_kisiAdi);

        for (int i = 0; i < _Sira.length; i++) {
            $(By.id("mainPreviewForm:evrakPaylasKisiLov:lovTree:" + i)).click();
        }

        btnTreeKapat.click();
        //lblIlkisi.click();
        return this;
    }
    @Step("Kişi seçilir")
    public PaylastiklarimPage kisiSec(String _kisiAdi) {
        txtKisi.setValue(_kisiAdi);
        $(By.id("mainPreviewForm:evrakPaylasKisiLov:lovTree:0")).click();
        return this;
    }

    @Step("Açıklama yazılır.")
    public PaylastiklarimPage aciklamaYaz(String _aciklama) {
        txtAciklama.sendKeys(_aciklama);
        return this;
    }
    @Step("Paylaş")
    public PaylastiklarimPage paylas() {
        btnPaylas.click();
        return this;
    }
    @Step("Paylaşım Kontrolu")
    public PaylastiklarimPage paylasimKontrol(String[] _paylasilanKisiler) {
        String checkXpath = "";
        for (int i = 0; i < _paylasilanKisiler.length; i++) {
            checkXpath += _paylasilanKisiler[i] + " / ";
        }
        checkXpath = checkXpath.substring(0, checkXpath.length() - 3);
        $(By.xpath(".//*[@class='searchText' and contains(., '" + checkXpath + "')]")).shouldBe(Condition.exist);
        return this;
    }
    @Step("Paylaşım seç")
    public PaylastiklarimPage paylasimSec(String _paylasilanlar) {
        $(By.xpath(".//div[@class='searchText' and contains(., '" + _paylasilanlar + "') and position() = 1]")).click();
        return this;
    }
    @Step("Açıklama Seç")
    public PaylastiklarimPage aciklamaKontrol(String _aciklama) {
        $(By.xpath("//div[@class='ui-dt-c' and contains(., '" + _aciklama + "')]")).shouldBe(Condition.exist);
        return this;
    }
    @Step("Paylaşılanlar kontrol")
    public PaylastiklarimPage paylasilanlarKontrol(String[] _paylasilanlar) {
        // /html/body/div[11]/div[4]/div/form/div/div[2]/div/div/div[5]/div/table/tbody/tr[1]/td[1]/div
        ElementsCollection rows = $$(By.xpath("//*[@id='mainPreviewForm:j_idt11557:j_idt12058_data']/tr"));
        Assert.assertEquals(_paylasilanlar.length, rows.size());
        for (int i = 0; i < _paylasilanlar.length; i++) {
            $(By.xpath("//*[@id='mainPreviewForm:j_idt11557:j_idt12058_data']/tr/td[contains(., '" + _paylasilanlar[i] + "')]/../td[contains(., 'Paylaşımda')]")).shouldBe(Condition.exist);
        }

        return this;
    }

    /*
    @Step("Yeni Pul ekle")
    public PulYonetimiPage yeniPulEkle() {
        takeScreenshotOnFail();
        btnAddNewPul.click();
        return this;
    }

    @Step("Posta tipi seç")
    public PulYonetimiPage postaTipiSec(String value) {
        cmbPostaTipi.selectOption(value);
        return this;
    }

    @Step("Gramajı doldur")
    public PulYonetimiPage gramajiDoldur(String gramaj) {
        txtGramaj.sendKeys(gramaj);
        return this;
    }

    @Step("Tutarı doldur")
    public PulYonetimiPage tutariDoldur(String tutar) {
        txtTutar.sendKeys(tutar);
        return this;
    }

    @Step("Yurt dışı checkbox değeri seç")
    public PulYonetimiPage yurtDisiSec(boolean secili) {
        Allure.addAttachment("My attachment", "My attachment content");
        Allure.addDescription("My yeniiiiiii dasad");
        takeScreenshotOnFail();
        chkYurtDisi.setSelected(secili);
        return this;
    }

    @Step("Kaydet")
    public PulYonetimiPage kaydet() {
        btnSavePul.click();
        return this;
    }
    */
}
