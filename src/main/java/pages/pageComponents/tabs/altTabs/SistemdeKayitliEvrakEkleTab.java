package pages.pageComponents.tabs.altTabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class SistemdeKayitliEvrakEkleTab{
    private final static String tabName = "Sistemde Kayıtlı Evrak Ekle";
    private SelenideElement altTab;

    private SelenideElement parentElement;
    public SistemdeKayitliEvrakEkleTab(SelenideElement tab) {
        this.parentElement = tab;
    }

    @Step(tabName + " tabı aç")
    private SistemdeKayitliEvrakEkleTab openTab() {
        parentElement.$("a[href$='sistemdeKayitliEvragiEkleTab']").click();
        altTab = parentElement.$("div[id$='sistemdeKayitliEvragiEkleTab']");
        return this;
    }
}
