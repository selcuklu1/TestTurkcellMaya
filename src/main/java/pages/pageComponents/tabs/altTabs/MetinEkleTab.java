package pages.pageComponents.tabs.altTabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class MetinEkleTab{
    private final static String tabName = "Metin Ekle";
    private SelenideElement altTab;

    private SelenideElement parentElement;
    public MetinEkleTab(SelenideElement tab) {
        this.parentElement = tab;
    }

    @Step(tabName + " tabı aç")
    private MetinEkleTab openTab() {
        parentElement.$("a[href$='aciklamaEkleTab']").click();
        altTab = parentElement.$("div[id$='aciklamaEkleTab']");
        return this;
    }
}
