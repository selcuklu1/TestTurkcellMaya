package pages.pageComponents.tabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.SearchTable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class EkleriTab extends MainPage {

    final static String tabName = "Ekleri";
    protected SelenideElement tab;
    protected SelenideElement page;
    SearchTable searchTable;

    public EkleriTab(SelenideElement page) {
        this.page = page;
        this.tab = page.$("div[id$='evrakEkTabView']");
        searchTable = new SearchTable(page.$("div[id$='ekListesiDataTable']"));
    }

    @Step(tabName + " tabı aç")
    public EkleriTab openTab(boolean... clickIfOpen) {
        SelenideElement tab = page.$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='" + tabName + "']]");
        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        this.tab.shouldBe(visible);

        return this;
    }

    public AltTabs altTabs() {
        return new AltTabs(tab);
    }

    @Step("Ek Listesi bul")
    public SearchTable getEkListesiTablosu() {
        return searchTable;
    }
}
