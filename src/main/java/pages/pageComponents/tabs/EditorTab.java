package pages.pageComponents.tabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 10.01.2018
 * Açıklama:
 */
public class EditorTab extends MainPage {
    final static String tabName = "Editör";
    protected SelenideElement page;

    public EditorTab() {
        this.page = $("html");
    }

    public EditorTab(SelenideElement page) {
        this.page = page;
    }

    public TextEditor getEditor() {
        return new TextEditor(page);
    }

    @Step(tabName + " tabı aç")
    public EditorTab openTab(boolean... clickIfOpen) {
        SelenideElement tab = page.$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='" + tabName + "']]");
        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        page.$("[id$=allPanels_content]").shouldBe(visible);
        page.$$("span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
//        page.$$("#DOnayDivToolbar span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Ön tanımlı dialog bul")
    public SelenideElement getOnTanimliDialog(){
        return page.$("div[id*='icerikSablonDialog']");
    }

    @Step("Ön tanımlı şablonu bul ve seç")
    public EditorTab onTanimliSablonuSec(String sablonAdi) {
        //container.$("div[id*='icerikSablonDialog']").shouldBe(visible);
        BelgenetElement cmbSablon = comboBox(getOnTanimliDialog(),"label[id$='_label']");
        cmbSablon.shouldBe(visible);
        cmbSablon.selectComboBox(sablonAdi);
        return this;
    }

    @Step("Ön tanımlı şablonun gelmediği görülür")
    public EditorTab onTanimliSablonuOlmadigi(String sablonAdi) {
        BelgenetElement cmbSablon = comboBox(getOnTanimliDialog(),"label[id$='_label']");
        cmbSablon.shouldBe(visible);
        ElementsCollection s = cmbSablon.getComboBoxValues();
        s.filterBy(text(sablonAdi)).shouldHaveSize(0);
        Allure.addAttachment("Şablonlar", (s.size() > 0) ? s.texts().toString() : "");
        return this;
    }

    @Step("Editör teksti kontrol et")
    public EditorTab onTanimliSablonuOnizlemeKontrol(Condition... conditions) {
        SelenideElement element = getOnTanimliDialog().$("[id$='onizlemeField']").shouldBe(visible);
//        sleep(5000);
//        switchTo().frame($("[id='yeniGidenEvrakForm:onizlemeField'] iframe"));
        switchTo().frame($(element.$("iframe")));
        //String actualText = $("body").text();
        for (Condition condition:conditions) {
            $("body").shouldBe(visible).shouldHave(condition);
        }
        switchTo().defaultContent();
        return this;
    }

    @Step("Ön tanımlı şablonu uygula")
    public EditorTab onTanimliSablonuUygula() {
        getOnTanimliDialog().$("button[type=submit]").click();
        //.$x("descenbutton/span[text()='Uygula']/..").click();
        return this;
    }
}
