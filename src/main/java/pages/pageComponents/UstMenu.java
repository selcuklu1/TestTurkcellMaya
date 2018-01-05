package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UstMenu extends BaseLibrary {
    private String altMenuDialogId;

    @Step("\"{ustMenuData.groupName}\" -> \"{ustMenuData.name}\" üst menu aç")
    public void openMenu(Enum ustMenuData, boolean... useJS) {
        String groupName;
        String menuName;
        try {

            Method getGroupNameMethod = ustMenuData.getClass().getMethod("getGroupName");
            Method getNameMethod = ustMenuData.getClass().getMethod("getName");
            groupName = getGroupNameMethod.invoke(ustMenuData).toString();
            menuName = getNameMethod.invoke(ustMenuData).toString();

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("SolMenuData hatası: \n" + e.getMessage());
        }

        if ((useJS.length <= 0) || useJS[0])
            openMenu(menuName);
        else
            openMenu(groupName, menuName);
    }

    private void openMenu(String menuName) {
        SelenideElement menu = $x("//a[span[text()='" + menuName + "'] and @class='ui-commandlink boxMenuElem']");
        menu.waitUntil(exist, Configuration.timeout);
        executeJavaScript("arguments[0].click();", menu);
    }

    private void openMenu(String ustMenuIsmi, String altMenuIsmi) {
        SelenideElement u = $(By.xpath("//div[@id='layoutTopMenuContainer']//button[.='" + ustMenuIsmi + "']"));
        altMenuDialogId = (u.attr("id")).replace("ustMenuEleman", "altMenuDialog");
        u.click();
        $(By.id(altMenuDialogId)).$(By.linkText(altMenuIsmi)).click();
    }


    @Step("\"{menuIsmi}\" ust menu aç")
    private void ustMenu(String menuIsmi) {
//        $("#topMenuForm2").shouldBe(visible);
        SelenideElement menu = $x("//a[span[text()='" + menuIsmi + "'] and @class='ui-commandlink boxMenuElem']");
        menu.waitUntil(exist, Configuration.timeout);
        executeJavaScript("arguments[0].click();", menu);

        /*
        executeJavaScript("arguments[0].click();",
                $x("//div[starts-with(@id,'topMenuForm') and contains(@id,'altMenuDialog')]" +
                        "//span[starts-with(text(),'" + menuIsmi + "')]/parent::a"));
        */
    }

    @Step("\"{ustMenuIsmi}\"->\"{altMenuIsmi}\" ust menu aç")
    private void ustMenu(String ustMenuIsmi, String altMenuIsmi) {
        openMenu(ustMenuIsmi, altMenuIsmi);
    }

    private void printMenuSection() {
        ElementsCollection ec = $$("[id^='topMenuForm2:ust:'][id$='ustMenuEleman']");
        for (SelenideElement e : ec) {
            String m = e.text();
            m = clearTurkishChars(m).replace(" ", "");
//            System.out.println("""\"" + e.text() + "\"");
        }
    }
    private void printUstMenuAsEnum() throws Exception {
//        System.out.println("public enum UstMenuData {");
//        System.out.println("DUMP;");

        ElementsCollection sections = $$x("//fieldset[legend[span]]").filterBy(Condition.visible);
//        ElementsCollection sections = $$("legend").filterBy(Condition.visible);
//        System.out.println("public enum UstMenuGroup {");
        for (SelenideElement section:sections) {
            String sectionName = section.$("legend").text().trim();
            String sectionEnumName = clearTurkishChars(sectionName);
            sectionEnumName = sectionEnumName.replace(" ", "");
            sectionEnumName = sectionEnumName.replace("/", "");
//            System.out.println(sectionEnumName + "(\""+ sectionName +"\"),");
            System.out.println("public enum " + sectionEnumName + " {");

            String menuName="";
            String menuEnumName="";
//            ElementsCollection menus = $$x("//fieldset[legend[span]]//a/span").filterBy(Condition.visible);
            ElementsCollection menus = section.$$("a span").filterBy(Condition.visible);
            for (int i = 0; i < menus.size(); i++) {
                menuName = menus.get(i).text().trim();
                menuEnumName = clearTurkishChars(menuName).replace(" ", "");
                if (i == menus.size()-1)
                    System.out.println(menuEnumName + "(\""+ menuName +"\");");
                else
                    System.out.println(menuEnumName + "(\""+ menuName +"\"),");
            }

            System.out.println("private String name;\n" +
                    "    private String groupName;\n" +
                    "    "+ sectionEnumName +"(String name) {\n" +
                    "        this.name = name;\n" +
                    "        this.groupName = UstMenuGroup."+ sectionEnumName +".getName();\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getName() {\n" +
                    "        return name;\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getGroupNameName() {\n" +
                    "        return groupName;\n" +
                    "    }");

            System.out.println("}");
            System.out.println("");
        }

    }

}
