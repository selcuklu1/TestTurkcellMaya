package pages.pageComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UstMenu extends BaseLibrary {
    private String altMenuDialogId;

    @Step("\"{menuIsmi}\" ust menu aç")
    public void ustMenu(String menuIsmi) {

//        $(By.cssSelector("[id^='topMenuForm'][id$='altMenuDialog']")).waitUntil(Condition.exist, Configuration.timeout);

        $("#topMenuForm2").shouldBe(visible);

        System.out.println($x("//div[starts-with(@id,'topMenuForm') and contains(@id,'altMenuDialog')]" +
                "//span[starts-with(text(),'" + menuIsmi + "')]/parent::a").innerText());
        executeJavaScript("arguments[0].click();",
                $x("//div[starts-with(@id,'topMenuForm') and contains(@id,'altMenuDialog')]" +
                        "//span[starts-with(text(),'" + menuIsmi + "')]/parent::a"));

        /*executeJavaScript("arguments[0].click();",
                $(By.cssSelector("[id^='topMenuForm'][id$='altMenuDialog']"))
                        .find(By.xpath("//span[starts-with(text(),'" + menuIsmi + "')]/parent::a"))
        );*/
//      .find(By.xpath("//span[text()='" + menuIsmi + "']/parent::a")));
    }

    @Step("\"{ustMenuIsmi}\"->\"{altMenuIsmi}\" ust menu aç")
    public void ustMenu(String ustMenuIsmi, String altMenuIsmi) {
        openMenu(ustMenuIsmi, altMenuIsmi);
    }

    private void openMenu(String ustMenuIsmi, String altMenuIsmi) {
        SelenideElement u = $(By.xpath("//div[@id='layoutTopMenuContainer']//button[.='" + ustMenuIsmi + "']"));
        altMenuDialogId = (u.attr("id")).replace("ustMenuEleman", "altMenuDialog");
        u.click();
        $(By.id(altMenuDialogId)).$(By.linkText(altMenuIsmi)).click();
    }

    private void printMenuSection() {
        ElementsCollection ec = $$("[id^='topMenuForm2:ust:'][id$='ustMenuEleman']");
        for (SelenideElement e : ec) {
            String m = e.text();
            m = clearTurkishChars(m).replace(" ", "");
//            System.out.println("""\"" + e.text() + "\"");
        }
    }

}
