package pages.pageComponents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserMenu {
    SelenideElement userMenuButton = $(By.id("topMenuForm:userMenuButton_button"));

}
