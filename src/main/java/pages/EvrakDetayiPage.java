package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class EvrakDetayiPage extends MainPage {

    SelenideElement pageTitle = $(By.xpath("//span[. = 'Evrak Detayı' and @class = 'ui-dialog-title']"));
    SelenideElement btnTebellugEt = $("button .tebellugEt");

    SelenideElement btnPanelEvet = $(By.id("mainInboxForm:tebellugEtEvetButton"));
    SelenideElement btnPanelHayir = $(By.id("mainInboxForm:tebellugEtHayirButton"));


    @Step("Sayfa açıldı mı kontrolü")
    public EvrakDetayiPage sayfaAcilmali() {
        pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Tebliğ geçmişi tab aç")
    public TebligGecmisiTab tebligGecmisiTabAc() {
        return new TebligGecmisiTab().open();
    }

    public class TebligGecmisiTab extends MainPage {

        SelenideElement tabTebligGecmisi = $(By.xpath("//span[. = 'Tebliğ Geçmişi']/../../..//button"));
        ElementsCollection tableTebligGecmisi = $$("tbody[id='inboxItemInfoForm:tebligDataTable_data'] > tr[role='row']");

        private TebligGecmisiTab open() {
            tabTebligGecmisi.click();
            return this;
        }

        @Step("Teblig geçmişi kontrol et")
        public TebligGecmisiTab tebligGecmisiKontrol(String tebligEdenveTarih, String[] kullanicilar) {

            $x("//span[contains(text(), 'Mehmet BOZDEMİR - (18.12.2017)')]").waitUntil(Condition.visible, 5000);

            SelenideElement currentRow = tableTebligGecmisi
                    .filterBy(Condition.text(tebligEdenveTarih))
                    .last()
                    .waitUntil(Condition.visible, 5000);

            if (currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).isDisplayed()) {
                currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).click();
            }

            ElementsCollection tableTebligEdilen = $$(By.xpath("//span[. = '" + tebligEdenveTarih + "']/../..//tbody/tr[@role='row']"));

            for (int i = 0; i < kullanicilar.length; i++) {

                tableTebligEdilen
                        .filterBy(Condition.text(kullanicilar[i]))
                        .first()
                        .shouldBe(Condition.visible);
            }


            return this;
        }

        @Step("Teblig geçmişinde tebellüğ olanları kontrol et")
        public TebligGecmisiTab tebligGecmisiKontrol(String tebligEdenveTarih, String[] kullanicilar, String[] tebellugTarih) {

            Selenide.sleep(5000);
            SelenideElement currentRow = tableTebligGecmisi
                    .filterBy(Condition.text(tebligEdenveTarih))
                    .last();

            if (currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).isDisplayed()) {
                currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).click();
            }

            ElementsCollection tableTebligEdilen = $$(By.xpath("//span[. = '" + tebligEdenveTarih + "']/../..//tbody/tr[@role='row']"));

            for (int i = 0; i < kullanicilar.length; i++) {

                tableTebligEdilen
                        .filterBy(Condition.text(kullanicilar[i]))
                        .filterBy(Condition.text(tebellugTarih[i]))
                        .first()
                        .shouldBe(Condition.visible);
            }


            return this;
        }

    }


    @Step("Tebellüğ Et butonuna tıkla.")
    public EvrakDetayiPage tebellugEt(boolean onay) {
        Selenide.sleep(5000);
        btnTebellugEt.waitUntil(Condition.visible, 5000);
        btnTebellugEt.click();

        if (onay == true)
            btnPanelEvet.click();
        else
            btnPanelEvet.click();


        return this;
    }


}
