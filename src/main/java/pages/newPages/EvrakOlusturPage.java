package pages.newPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageComponents.EvrakPageButtons;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.tabs.*;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static pages.pageComponents.belgenetElements.Belgenet.$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class EvrakOlusturPage extends MainPage {
    public final UstMenuData.EvrakIslemleri pageTitle = UstMenuData.EvrakIslemleri.EvrakOlustur;
    private SelenideElement page = $("#yeniGidenEvrakForm");

    public EvrakOlusturPage openPage() {
        ustMenu(pageTitle);
        pageHeader().getPageTitle().shouldHave(text(pageTitle.getName()));
        return this;
    }

    @Step("Sayfayı kapat")
    public EvrakOlusturPage closePage(boolean save) {
        pageHeader().closePage();
        if (save)
            confirmDialog().confirmEvetTikla();
        else
            confirmDialog().confirmHayirTikla();
        return this;
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

    public DogrulamaTab dogrulamaTab() {
        return new DogrulamaTab(page);
    }
}
