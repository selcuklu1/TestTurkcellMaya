package pages.newPages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.*;
import pages.pageComponents.tabs.*;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static pages.pageComponents.belgenetElements.Belgenet.$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class EvrakDetayiPage extends MainPage {
    public final UstMenuData.EvrakIslemleri pageTitle = UstMenuData.EvrakIslemleri.EvrakOlustur;
    private SelenideElement page = $("#inboxItemInfoForm");

    /*public EvrakDetayiPage openPage() {
        ustMenu(pageTitle);
        pageHeader().getPageTitle().shouldHave(text(pageTitle.getName()));
        return this;
    }*/

    @Step("Sayfayı kapat")
    public EvrakDetayiPage closePage() {
        pageHeader().closePage();
        confirmDialog().buttonClick("Kapat");
        return this;
    }

    @Step("Evrak Notları tabı açılır")
    public EvrakDetayiPage evrakNotlariTabiAc(boolean... clickIfOpen) {
        SelenideElement tab = page.$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='Evrak Notları']]");
        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();
        page.$(Selectors.byText("Evrak Notları")).shouldBe(visible);
        return this;
    }

    public UstYazi.EvrakNot evrakNotlari(){
        return new UstYazi().new EvrakNot($(By.id("inboxItemInfoForm:kisiselNotEkleDataTableId")));
    }


    public SelenideElement getPage() {
        return page;
    }

    public EvrakPageButtons pageButtons() {
        return new EvrakPageButtons(page);
    }

    public BilgilerTab bilgileriTab() {
        return new BilgilerTab(page);
//        return bilgilerTab;
    }

    public UstMenuPageHeader pageHeader() {
        return new UstMenuPageHeader(page);
    }

    public EditorTab editorTab() {
        return new EditorTab(page);
    }

    public EkleriTab ekleriTab() {
        return new EkleriTab(page);
    }

    public IlgileriTab ilgileriTab() {
        return new IlgileriTab(page);
    }

    public IliskiliEvraklar iliskiliEvraklarTab() {
        return new IliskiliEvraklar(page);
    }

    public DagitimHitapDuzenle dagitimHitapDuzenle() {
        return new DagitimHitapDuzenle(page);
    }
}
