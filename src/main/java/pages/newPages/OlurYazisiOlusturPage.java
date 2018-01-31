package pages.newPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
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
public class OlurYazisiOlusturPage extends MainPage {

//    private final static String pageLocator = "//*[@id='yeniOnayEvrakForm']/ancestor::div[contains(@class,'windowDialog')]";

    public final UstMenuData.EvrakIslemleri pageTitle = UstMenuData.EvrakIslemleri.OlurYazisiOlustur;
    /*@FindBy(xpath = pageLocator)
        private UstMenuPageHeader ustMenuPageHeader;

        @FindBy(xpath = pageLocator)
        private BilgilerTab bilgilerTab;*/
    private SelenideElement page = $("#yeniOnayEvrakForm");

    public SelenideElement getPage() {
        return page;
    }

    @Step("Onay İşlem Açıklama alanı bul")
    public SelenideElement getOnayIslemAciklama() {
        return page.$("textarea[id$=onayIslemiAciklama]");
    }

    @Step("Onay İşlem Açıklama alanı doldur")
    public OlurYazisiOlusturPage onayIslemAciklamaDoldur(String aciklama) {
        page.$("textarea[id$=onayIslemiAciklama]").setValue(aciklama);
        return this;
    }


    public OlurYazisiOlusturPage openPage() {
        ustMenu(pageTitle);
        pageHeader().getPageTitle().shouldHave(text(pageTitle.getName()));
        return this;
    }

    @Step("Sayfayı kapat")
    public void closePage(boolean save) {
        pageHeader().closePage();
        if (save)
            confirmDialog().confirmEvetTikla();
        else
            confirmDialog().confirmHayirTikla();
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

    /*public class EditorTab extends MainPage {
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
*/
}
