package page;

import common.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseLibrary {

    @FindBy(id = "topMenuForm:userMenuButton_button")
    private WebElement userMenuButton;

    public Boolean isOnTheHomePage() {
//        assertThat(visibilityOf(userMenuButton));
        return userMenuButton.isDisplayed();
    }

}
