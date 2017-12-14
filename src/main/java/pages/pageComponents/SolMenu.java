package pages.pageComponents;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pages.pageData.SolMenuData.*;

public class SolMenu extends BaseLibrary {

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

    private void openMenu(String groupId, String menuText, boolean useJS) {
        SelenideElement group = $(By.id(groupId));
        String groupText = group.$("h3").text();
//        WebElement menuLink = group.find(By.xpath("//span[starts-with(text(),'" + menuText + "')]"));
        SelenideElement menuLink = group.find(By.xpath("//span[starts-with(text(),'" + menuText + "')]")).waitUntil(exist, Configuration.timeout);

        if (useJS) {
            executeJavaScript("arguments[0].click();", menuLink);///parent::a
        } else {
            if (!menuLink.isDisplayed()) group.click();
            group.$(By.partialLinkText(menuText)).click();
        }

        Allure.addAttachment("NavigationMenu metnileri", "Grup metni: " + groupText
                + "\nNavigationMenu metni: " + menuLink.getText());
    }


}
