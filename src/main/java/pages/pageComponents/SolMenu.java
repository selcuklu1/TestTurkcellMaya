package pages.pageComponents;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.pageData.SolMenuData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pages.pageData.SolMenuData.*;

public class SolMenu extends BaseLibrary {

    @Step("\"{solMenuData.groupText}\" -> \"{solMenuData.menuText}\" sol menu aç")
    public void openMenu(Enum solMenuData, boolean... useJS) {
        String groupId;
        String menuText;
        try {
            Method getGroupIdMethod = solMenuData.getClass().getMethod("getGroupId");
            Method getMenuTextMethod = solMenuData.getClass().getMethod("getMenuText");
            groupId = getGroupIdMethod.invoke(solMenuData).toString();
            menuText = getMenuTextMethod.invoke(solMenuData).toString();

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("SolMenuData hatası: \n" + e.getMessage());
        }

        openMenu(groupId, menuText, ((useJS.length <= 0) || useJS[0]));
    }

    @Step("\"{groupId}\" -> \"{menuText}\" sol menu, use JS {useJS}")
    private void openMenu(String groupId, String menuText, boolean useJS) {
        SelenideElement pageTitle = $("label.ui-inbox-header-title");

        SelenideElement group = $(By.id(groupId));
        String groupText = group.$("h3").text();
        SelenideElement menuLink = group.find(By.xpath("//span[starts-with(text(),'" + menuText + "')]")).waitUntil(exist, Configuration.timeout);

        if (useJS)
            executeJavaScript("arguments[0].click();", menuLink);///parent::a
        else {
            if (!menuLink.isDisplayed()) group.click();
            group.$(By.partialLinkText(menuText)).click();
        }

        pageTitle.shouldHave(text(menuText));

        Allure.addAttachment("NavigationMenu metnileri", "Grup metni: " + groupText
                + "\nNavigationMenu metni: " + menuLink.getText());
    }


    //region Class init
    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(IslemBekleyenEvraklar menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(BirimEvraklari menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(KapatmaIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(Bildirimler menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(ArsivIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(YoneticiIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(KurulIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    @Step("\"{menu.groupText}\" -> \"{menu.menuText}\" sol menu aç")
    public void solMenu(IslemYaptiklarim menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }
    //endregion


}
