package pages.pageComponents.tabs.altTabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class TercumeEkleTab{
    private final static String tabName = "Tercüme Ekle";
    private SelenideElement altTab;

    private SelenideElement parentElement;
    public TercumeEkleTab(SelenideElement tab) {
        this.parentElement = tab;
    }

    @Step(tabName + " tabı aç")
    private TercumeEkleTab openTab() {
        parentElement.$("a[href$='tercumeEvragiEkleTab']").click();
        altTab = parentElement.$("div[id$='tercumeEvragiEkleTab']");
        return this;
    }
}
