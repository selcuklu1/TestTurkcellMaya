package pages.pageComponents.tabs.altTabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class ArsivdeKayitliEvrakEkleTab {
    private final static String tabName = "Arşivde Kayıtlı Evrak Ekle";
    private SelenideElement altTab;

    private SelenideElement parentElement;
    public ArsivdeKayitliEvrakEkleTab(SelenideElement tab) {
        this.parentElement = tab;
    }

    @Step(tabName + " tabı aç")
    private ArsivdeKayitliEvrakEkleTab openTab() {
        parentElement.$("a[href$='arsivdenEkEkleTabId']").click();
        altTab = parentElement.$("div[id$='arsivdenEkEkleTabId']");
        return this;
    }
}