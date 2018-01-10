package pages.pageComponents.tabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class IlgileriTab {

    final static String tabName = "Ekleri";
    protected SelenideElement tab;
    protected SelenideElement page;

    public IlgileriTab(SelenideElement page) {
        this.page = page;
    }

    @Step(tabName + " tabı aç")
    public IlgileriTab openTab(boolean... clickIfOpen) {
        SelenideElement tab = page.$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='"+ tabName +"']]");

        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        this.tab = page.$("div[id$='evrakEkTabView']").shouldBe(visible);

        return this;
    }

    public AltTabs altTab() {
        return new AltTabs(tab);
    }

}
