package pageComponents;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pageData.SolMenuData.*;

public class SolMenu extends BaseLibrary {

    //region Class init
    public SolMenu(IslemBekleyenEvraklar menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getValue(), useJS);
    }

    public SolMenu(BirimEvraklari menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getValue(), useJS);
    }

    public SolMenu(KapatmaIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getValue(), useJS);
    }

    public SolMenu(Bildirimler menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getValue(), useJS);
    }

    public SolMenu(ArsivIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    public SolMenu(YoneticiIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    public SolMenu(KurulIslemleri menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getMenuText(), useJS);
    }

    public SolMenu(IslemYaptiklarim menu, boolean useJS) {
        openMenu(menu.getGroupId(), menu.getValue(), useJS);
    }
    //endregion
    private void openMenu(String groupId, String menuText, boolean useJS){
        SelenideElement group = $(By.id(groupId));
        String groupText = group.$("h3").text();
        WebElement menuLink = group.find(By.xpath("//span[starts-with(text(),'" + menuText + "')]"));

        if (useJS) {
            executeJavaScript("arguments[0].click();", menuLink);///parent::a
        }
        else{
            if (!menuLink.isDisplayed()) group.click();
            group.$(By.partialLinkText(menuText)).click();
        }

        Allure.addAttachment("Menu metnileri", "Grup metni: " + groupText
                + "\nMenu metni: " + menuLink.getText());
    }

    //Depricated
    private void openMenuJS(String groupId, String menuText){
        executeJavaScript("arguments[0].click();",
                $(By.cssSelector("[id='" + groupId + "']"))
                        .find(By.xpath("//span[text()='" + menuText + "']")));///parent::a

    }
    private WebElement getMenu(String grupId, String menuIsmi) {
        return $(By.cssSelector("[id='" + grupId + "']"))
                .find(By.partialLinkText(menuIsmi));
    }

    //region Depricated
    /*
    @Step("{menu.groupText}->{menu.menuText} sol menu aç")
    public void menuAc(IslemBekleyenEvraklar menu, boolean... useJS) {
        boolean b = true;
        if (useJS.length > 0) b = useJS[0];
        if (b)
            openMenu(menu.getGroupId(), menu.getMenuText());
        else
            openMenuJS(menu.getGroupId(), menu.getMenuText());
    }

    @Step("{menu.groupText}->{menu.menuText} sol menu aç")
    public void menuAc(BirimEvraklari menu, boolean... useJS) {
        if (useJS[0])
            openMenu(menu.getGroupId(), menu.getMenuText());
        else
            openMenuJS(menu.getGroupId(), menu.getMenuText());
    }

    @Step("{menu.groupText}->{menu.menuText} sol menu aç")
    public void menuAc(KapatmaIslemleri menu, boolean... useJS) {
        if (useJS[0])
            openMenu(menu.getGroupId(), menu.getMenuText());
        else
            openMenuJS(menu.getGroupId(), menu.getMenuText());
    }

    @Step("{menu.groupText}->{menu.menuText} sol menu aç")
    public void menuAc(Bildirimler menu, boolean... useJS) {
        if (useJS[0])
            openMenu(menu.getGroupId(), menu.getMenuText());
        else
            openMenuJS(menu.getGroupId(), menu.getMenuText());
    }

    @Step("{menu.groupText}->{menu.menuText} sol menu aç")
    public void menuAc(ArsivIslemleri menu, boolean... useJS) {
        if (useJS[0])
            openMenu(menu.getGroupId(), menu.getMenuText());
        else
            openMenuJS(menu.getGroupId(), menu.getMenuText());
    }

    @Step("{menu.groupText}->{menu.menuText} sol menu aç")
    public void menuAc(YoneticiIslemleri menu, boolean... useJS) {
        if (useJS[0])
            openMenu(menu.getGroupId(), menu.getMenuText());
        else
            openMenuJS(menu.getGroupId(), menu.getMenuText());
    }

    @Step("{menu.groupText}->{menu.menuText} sol menu aç")
    public void menuAc(KurulIslemleri menu, boolean... useJS) {
        if (useJS[0])
            openMenu(menu.getGroupId(), menu.getMenuText());
        else
            openMenuJS(menu.getGroupId(), menu.getMenuText());
    }
*/
    //endregion

}
