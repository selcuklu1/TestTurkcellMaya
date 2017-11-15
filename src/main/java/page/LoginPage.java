package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BaseLibrary {

    private SelenideElement txtUsername = $(By.id("eForm:txtEKullaniciAdi"));
    private SelenideElement txtPassword = $(By.id("eForm:loginESifre"));
    private SelenideElement btnLogin = $(By.id("eForm:egirisYapButton"));

    private LoginPage open() {
        Selenide.open("");
//        Selenide.open("http://www.belgenet.com.tr:8282/edys-web/sistemeGiris.xhtml");
//        username.shouldBe(visible);
        return this;
    }

    @Step("Giriş yap")
    public LoginPage login() {
        open();
        txtUsername.sendKeys("Yakyol");
        txtPassword.sendKeys("123");
        btnLogin.click();
//        $(By.id("topMenuForm:userMenuButton_button")).shouldBe(visible);
        return this;
    }


}