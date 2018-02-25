package pages.pageComponents;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.*;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 12.02.2018
 * Açıklama:
 */
public class PDFOnizleme extends MainPage{

    ElementsCollection pages = $$("#viewer .page");
    SelenideElement viewer = $("#viewer");
    SelenideElement scaleSelect = $("#scaleSelect");

    public PDFOnizleme() {
    }

    public PDFOnizleme(int windowIndex) {
        switchTo().window(windowIndex);
        waitForLoadingJS(WebDriverRunner.getWebDriver());
    }

    public PDFOnizleme(String title) {
        switchTo().window(title);
        waitForLoadingJS(WebDriverRunner.getWebDriver());
    }

    @Step("Set 100% scale")
    public PDFOnizleme setScale100() {
        scaleSelect.selectOption("100%");
        //byvalue: page-actual
        //byvalue: 1
        return this;
    }

    @Step("")
    public ElementsCollection getPages() {
        return pages;
    }

    @Step("")
    public SelenideElement getPage() {
        return pages.first();
    }

    @Step("")
    public SelenideElement getPage(int pageNumber) {
        return pages.get(pageNumber);
    }

    @Step("")
    public SelenideElement getElement(int pageNumber, Condition... conditions) {
        ElementsCollection div = getPage(pageNumber).$$(".textLayer div");
        for (Condition condition : conditions) {
            div = div.filterBy(condition);
        }
        return div.shouldHave(CollectionCondition.sizeGreaterThan(0)).first();
    }

    @Step("PDF Önizleme tekst kontrolü")
    public PDFOnizleme checkText(int pageNumber, Condition... conditions) {
        SelenideElement page = getPage(pageNumber);
        for (Condition condition : conditions) {
            page = page.shouldHave(condition);
        }
        return this;
    }

    @Step("PDF Önizleme tekst kontrolü")
    public PDFOnizleme checkText(Condition... conditions) {
        SelenideElement page = getPage(0);
        for (Condition condition : conditions) {
            //page.shouldHave(condition);
            page.waitUntil(condition, 30000);
        }
        setScale100();
        takeScreenshot();
        return this;
    }

    @Step("PDF Önizleme tekst kontrolü")
    public PDFOnizleme checkTextInAllPages(Condition... conditions) {
        for (Condition condition : conditions) {
            viewer.shouldHave(condition).scrollIntoView(true);
        }
        return this;
    }
}
