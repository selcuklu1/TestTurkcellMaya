package pages.pageComponents.tabs.altTabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class WebAdresiniEkleTab{
    private final static String tabName = "Web Adresini Ekle";
    private SelenideElement altTab;

    private SelenideElement parentElement;
    public WebAdresiniEkleTab(SelenideElement tab) {
        this.parentElement = tab;
    }

    @Step(tabName + " tabı aç")
    private WebAdresiniEkleTab openTab() {
        parentElement.$("a[href$='webAdresindenEkEkle']").click();
        altTab = parentElement.$("div[id$='webAdresindenEkEkle']");
        return this;
    }
}
