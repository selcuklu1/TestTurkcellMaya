package pages.solMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;


import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;

public class TopluPostalanacakEvraklarPage extends MainPage {

    ElementsCollection tableGidecegiYer = $$("tbody[id='mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarGidecegiYerDataTable_data'] > tr[role='row']");
    SelenideElement txtBaslangic = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarIlkTarihTemizleButtonId_input"));
    SelenideElement txtBitis = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarSonTarihTemizleButtonId_input"));
    SelenideElement lblPostaTipiSeciniz = $x("//label[contains(., 'Posta Tipi Seçiniz ...')]");
    ElementsCollection listPostaTipleri = $$(By.xpath("//label[.='Adi Posta']/../../li"));
    SelenideElement btnSorgula = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarFiltrele"));
    SelenideElement btnPostaListesineAktar = $x("//span[text() = 'Posta Listesine Aktar']/..");
    SelenideElement btnPostaListesiDropDown = $x("//fieldset[@id='mainPreviewForm:postaListesiAktarimFieldsetId']//div[contains(@class, 'ui-corner-right')]/span");
    ElementsCollection listPostaListesi = $$("div[id='mainPreviewForm:tpbeSelectOneMenuId_panel'] > ul > li");


    @Step("Toplu postalanacak evraklar sayfasını aç")
    public TopluPostalanacakEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TopluPostalanacakEvraklar);
        return this;
    }

<<<<<<< HEAD
=======

>>>>>>> 56accfa394ebf93fa1cbce3eaac30bd98db2467f
    @Step("Gideceği yer alanından {0} seç")
    public TopluPostalanacakEvraklarPage gidecegiYerSec(String gidecegiYer, boolean secim) {

        Boolean isSelected = false;

        SelenideElement currentRow = tableGidecegiYer
                .filterBy(Condition.text(gidecegiYer))
                .first();

        SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

        if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (secim == true) {
            if (isSelected == false)
                chkBox.click();
        } else {
            if (isSelected == true)
                chkBox.click();
        }

        return this;
    }

    @Step("Gideceği yerler seç")
    public TopluPostalanacakEvraklarPage gidecegiYerSec(String[] gidecegiYerler, boolean secim){

        for(int i = 0; i < gidecegiYerler.length; i++){
            Boolean isSelected = false;

            SelenideElement currentRow = tableGidecegiYer
                    .filterBy(Condition.text(gidecegiYerler[i]))
                    .first();

            SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

            if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                isSelected = true;

            if(secim == true){
                if(isSelected == false)
                    chkBox.click();
            } else {
                if(isSelected == true)
                    chkBox.click();
            }

        }

        return this;
    }

    @Step("Gideceği yer listesinde alfabetik kontrol")
    public TopluPostalanacakEvraklarPage gidecegiYerListesiAlfabetikSiraKontrolu() {

        String[] gidecegiYerList = new String[tableGidecegiYer.size()];

        for (int i = 0; i < gidecegiYerList.length; i++) {
            gidecegiYerList[i] = tableGidecegiYer.get(i).$(By.xpath("./td[2]")).innerText();
        }

        String[] gidecegiYerListOrdered = gidecegiYerList;
        Arrays.sort(gidecegiYerListOrdered);

        Assert.assertEquals(gidecegiYerList, gidecegiYerListOrdered);


        return this;
    }

    @Step("Tarih aralığını seç. Başlangıç: {0} - Bitiş:")
    public TopluPostalanacakEvraklarPage tarihAraligiSec(String baslangicTarihi, String bitisTarihi){

        txtBaslangic.setValue(baslangicTarihi);
        txtBitis.setValue(bitisTarihi);

        return this;
    }

    @Step("Posta tipi seç.")
    public TopluPostalanacakEvraklarPage postaTipiSec(String[] postaTipleri){
        lblPostaTipiSeciniz.click();
        for(int i = 0; i < postaTipleri.length; i++){

            SelenideElement currentRow = listPostaTipleri
                    .filterBy(Condition.text(postaTipleri[i]))
                    .first();

            SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

            boolean isSelected = false;
            if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                isSelected = true;

            if(isSelected == false) {
                Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", chkBox);
                chkBox.click();
            }


        }
        $x("//tbody").click();

        return this;
    }

    @Step("Sorgula butonuna tıkla.")
    public TopluPostalanacakEvraklarPage sorgula(){
        btnSorgula.click();
        return this;
    }

    @Step("Posta Listesine aktar butonuna tıkla.")
    public TopluPostalanacakEvraklarPage postaListesineAktar(){
        btnPostaListesineAktar.click();
        return this;
    }

    @Step("Posta listesinden {postaListesi} sec")
    public TopluPostalanacakEvraklarPage postaListesiSec(String postaListesi){
        btnPostaListesiDropDown.click();
        listPostaListesi
                .filterBy(Condition.text(postaListesi))
                .first()
                .click();
        return this;
    }



}

