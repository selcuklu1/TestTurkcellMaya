package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;


import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$$;

public class TopluPostalanacakEvraklarPage extends MainPage {

    ElementsCollection tableGidecegiYer = $$("tbody[id='mainInboxForm:inboxDataTable:filtersAccordion:topluPostalanacakEvraklarGidecegiYerDataTable_data'] > tr[role='row']");

    @Step("Toplu postalanacak evraklar sayfasını aç")
    public TopluPostalanacakEvraklarPage openPage(){
        solMenu(SolMenuData.BirimEvraklari.TopluPostalanacakEvraklar);
        return this;
    }



    @Step("Gideceği yer alanından {0} seç")
    public TopluPostalanacakEvraklarPage gidecegiYerSec(String gidecegiYer, boolean secim){

        Boolean isSelected = false;

        SelenideElement currentRow = tableGidecegiYer
                .filterBy(Condition.text(gidecegiYer))
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



        /*



        Boolean isSelected = false;
        if (chkOkunmamisTebligleriHatirlat.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if(secim == true){
            if(isSelected == false)
                chkOkunmamisTebligleriHatirlat.click();
        } else {
            if(isSelected == true)
                chkOkunmamisTebligleriHatirlat.click();
        }

        return this;



         */


        return this;
    }

    @Step("Gideceği yer listesinde alfabetik kontrol")
    public TopluPostalanacakEvraklarPage gidecegiYerListesiAlfabetikSiraKontrolu(){

        String[] gidecegiYerList = new String[tableGidecegiYer.size()];

        for(int i = 0; i < gidecegiYerList.length; i++){
            gidecegiYerList[i] = tableGidecegiYer.get(i).$(By.xpath("./td[2]")).innerText();
        }

        String[] gidecegiYerListOrdered = gidecegiYerList;
        Arrays.sort(gidecegiYerListOrdered);

        Assert.assertEquals(gidecegiYerList, gidecegiYerListOrdered);


        return this;
    }

}

