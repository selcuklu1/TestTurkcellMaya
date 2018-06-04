package pages.ustMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class UcakBiletiPage extends MainPage {


    @Step("Uçak Bileti sayfasını aç")
    public UcakBiletiPage openPage() {
        ustMenu(UstMenuData.UcakIslemleri.UcakBileti);
        return this;
    }

}