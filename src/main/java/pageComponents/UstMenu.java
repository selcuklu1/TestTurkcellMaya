package pageComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


public class UstMenu extends BaseLibrary {

    private String altMenuDialogId;

    public UstMenu(String menuIsmi) {
        executeJavaScript("arguments[0].click();",
                $(By.cssSelector("[id^='topMenuForm'][id$='altMenuDialog']"))
                        .find(By.xpath("//span[starts-with(text(),'" + menuIsmi + "')]/parent::a"))
        );
//      .find(By.xpath("//span[text()='" + menuIsmi + "']/parent::a")));
    }

    public UstMenu(String ustMenuIsmi, String altMenuIsmi) {
        topMenuButton(ustMenuIsmi).click();
        altMenuButton(altMenuIsmi).click();
    }

    @Step("{0} ust menu aç")
    public void ustMenuAc(String menuIsmi) {
        topMenuButton(menuIsmi).click();
    }

    private SelenideElement topMenuButton(String text) {
        SelenideElement b = $(By.xpath("//div[@id='layoutTopMenuContainer']//button[.='" + text + "']"));
        altMenuDialogId = b.getAttribute("id").replace("ustMenuEleman", "altMenuDialog");
        return b;
    }
    private SelenideElement altMenuButton(String menuName) {
        return $(By.id(altMenuDialogId)).$(By.linkText(menuName));
//        return $(By.xpath("//div[@id='" + altMenuDialogId + "']//span[text()='" + menuName + "']/ancestor::a"));
    }

    //<editor-fold desc="Depricated">
    @Step("{0}->{1} menu aç")
    private void menuAc(String ustMenuIsmi, String altMenuIsmi) {
        topMenuButton(ustMenuIsmi).click();
        altMenuButton(altMenuIsmi).click();
    }

    @Step("{0} menu aç")
    private void menuAc(String menuIsmi) {
        executeJavaScript("arguments[0].click();",
                $(By.cssSelector("[id^='topMenuForm'][id$='altMenuDialog']"))
                        .findAll(By.xpath("//span[starts-with(text(),'" + menuIsmi + "')]/parent::a")).get(0)
        );
//      .find(By.xpath("//span[text()='" + menuIsmi + "']/parent::a")));
    }
    //</editor-fold>

    private void printMenuSection(){
        ElementsCollection ec = $$("[id^='topMenuForm2:ust:'][id$='ustMenuEleman']");
        for (SelenideElement e:ec) {
            String m = e.text();
            m = clearTurkishChars(m).replace(" ","");
//            System.out.println("""\"" + e.text() + "\"");
        }
    }

}
