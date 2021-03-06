package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import data.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static data.TestData.passwordOPTIIM;
import static data.TestData.usernameOPTIIM;

public class LoginPage extends MainPage {

    private SelenideElement txtUsername = $(By.id("eForm:txtEKullaniciAdi"));
    private SelenideElement txtPassword = $(By.id("eForm:loginESifre"));
    private SelenideElement btnLogin = $(By.id("eForm:egirisYapButton"));
    //private SelenideElement btnUsermenu = $(By.id("topMenuForm:userMenuButton_button"));
    private SelenideElement btnUsermenu = $("button#topMenuForm\\:userMenuButton_button");

    private LoginPage open() {
//        clearCookies();
        WebDriverRunner.clearBrowserCache();
        Selenide.open("");

        System.out.println("================================");
        System.out.println("Driver: " + getCapabilities().toString());
        System.out.println("================================");
        maximazeBrowser();
        return this;
    }

    @Step("Giriş yap")
    public LoginPage login() {
        open();
        txtUsername.sendKeys(usernameOPTIIM);
        txtPassword.sendKeys(passwordOPTIIM);
        btnLogin.shouldBe(Condition.visible).click();
        btnUsermenu.waitUntil(Condition.visible, 40000);
        return this;
    }

    @Step("\"{username}\" kullanıcısı ile giriş yap")
    public LoginPage login(String username, String password) {
        open();
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        btnUsermenu.waitUntil(Condition.visible, 40000);
        return this;
    }

    @Step("\"{username}\" kullanıcısı ile giriş yapmaya çalış. Bakımdan dolayı giriş yapamaz.")
    public LoginPage loginBakim(String username, String password) {
        open();

        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        btnUsermenu.waitUntil(Condition.visible, 40000);
        return this;
    }

    @Step("Login")
    public void login(User user) {
        login(user.getUsername(), user.getPassword());
        if (!user.getBirimAdi().isEmpty() && user.getBirimAdi() != null)
            birimSec(Condition.text(user.getBirimAdi()));
    }
}