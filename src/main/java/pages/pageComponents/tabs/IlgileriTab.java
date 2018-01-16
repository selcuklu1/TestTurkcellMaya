package pages.pageComponents.tabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.SearchTable;

import static com.codeborne.selenide.Condition.visible;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class IlgileriTab extends MainPage {

    final static String tabName = "İlgileri";
    protected SelenideElement tab;
    protected SelenideElement page;
    SearchTable searchTable;

    public IlgileriTab(SelenideElement page) {
        this.page = page;
        this.tab = page.$("div[id$='ilgiIslemleriTabView']");
        searchTable = new SearchTable(page.$("div[id$='ilgiListesiDataTable']"));
    }

    @Step(tabName + " tabı aç")
    public IlgileriTab openTab(boolean... clickIfOpen) {
        SelenideElement tab = page.$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='" + tabName + "']]");

        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        this.tab.shouldBe(visible);

        return this;
    }

    public AltTabs altTabs() {
        return new AltTabs(tab);
    }

    @Step("İlgileri Listesi bul")
    public SearchTable getIlgliliListesiTablosu() {
        return searchTable;
    }
}
