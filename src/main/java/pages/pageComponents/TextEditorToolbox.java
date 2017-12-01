package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.12.2017
 * Açıklama:
 */
public class TextEditorToolbox extends ElementsContainer {

    private SelenideElement getToolboxElement(String name) {

        //<editor-fold desc="Description">
        //        String elementType = element.attr("class").contains("cke_combo_button") ? "combo" : "button" ;
//        return $inframe(By.xpath("//*[@class='cke_toolbox']//a/span[normalize-space(text())='"+ name +"']/.."));

//        String labelId = elementInFrame("//*[contains(@class,'cke') and contains(@class,'label') and normalize-space(text())='" + name + "']")
//                .attr("id");
//
//        switchTo().defaultContent();
//        return elementInFrame("a[aria-labelledby="+ labelId +"]");
        //</editor-fold>


        return $("a[href*='\'"+ name +"\'']");
    }

    public TextEditorToolbox clickButton(String name) {
        getToolboxElement(name).click();

        switchTo().defaultContent();
        return this;
    }

    public TextEditorToolbox selectCombo(String name, String value) {
        getToolboxElement(name).click();
        $(".cke_panel_block a[title='" + value + "']").click();

        //<editor-fold desc="Detailed selectors. Not in use">
        /*
        //"Etiket Ekle" title "undefined" olduğu için
        String blockName = p.equals("Etiket Ekle") ? "undefined" : p;
        String selector = "div[class='cke_panel_block'][title='"+ blockName +"']";
        selector = selector + " a[title='"+ p +"']";
        //String selector = "//div[@class='cke_panel_block' and @title='"+ blockName +"']";
        //selector = selector + "//li/a[normalize-space(text())='"+ p +"']";
        */
        //</editor-fold>

        switchTo().defaultContent();
        return this;
    }

    private SelenideElement getEditor(){
        //birden fazla editor içiren varsa visible olanı al
        switchTo().frame($$("div[id^='cke'][id$='contents'] iframe").filterBy(visible).first());

        //body[class='cke_editable cke_editable_themed cke_contents_ltr']
        return $("body[class*='cke_contents_ltr']");
    }

    private SelenideElement elementInFrame(String locator){
        switchTo().defaultContent();
        if ($(locator).exists())
            return $(locator);

        ElementsCollection iframes = $$(".cke_panel_frame");
        for (SelenideElement iframe:iframes) {
            switchTo().frame(iframe);
            if ($(locator).exists())// && $(locator).is(visible))
                break;
        }

        return $(locator);
    }

}
