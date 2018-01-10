package pages.newPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageComponents.tabs.EkleriTab;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class OlurYazisiOlusturPage extends MainPage {
    private final static String pageLocator = "//*[@id='yeniOnayEvrakForm']/ancestor::div[contains(@class,'windowDialog')]";

    @FindBy(xpath = pageLocator)
    public UstMenuPageHeader ustMenuPageHeader;

    @FindBy(xpath = pageLocator)
    private BilgilerTab bilgilerTab;

    private SelenideElement page = $("#yeniOnayEvrakForm");

    public OlurYazisiOlusturPage openPage() {
        ustMenu(UstMenuData.EvrakIslemleri.OlurYazisiOlustur);
        ustMenuPageHeader.getPageTitle().shouldHave(text(UstMenuData.EvrakIslemleri.OlurYazisiOlustur.getName()));
        return this;
    }

    @Step("Sayfayı kapat")
    public void closePage(boolean save) {
        ustMenuPageHeader.closePageButton.click();
        if (save)
            confirmDialog().button("Evet").shouldBe(visible).click();
        else
            confirmDialog().button("Hayır").shouldBe(visible).click();
    }

    public BilgilerTab bilgileriTabiAc() {
        return bilgilerTab.openTab();
    }

    @Step("Editör tabını aç")
    public EditorTab editorTabAc() {
        return new EditorTab().openTab();
    }

    public class EditorTab extends MainPage {
        private TextEditor editor = new TextEditor();

        public TextEditor getEditor() {
            return editor;
        }

        private EditorTab openTab() {
            if ($("[id*=allPanels_content]").is(not(visible)))
                openTab("Editör");
            $$("#DOnayDivToolbar span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
            return this;
        }
    }

    @Step("")
    public EkleriTab ekleriTabAc(){
        return new EkleriTab($("[id='yeniOnayEvrakForm']")).openTab();
    }

}
