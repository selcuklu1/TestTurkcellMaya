package pageComponents.belgenetElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface BelgenetElement extends SelenideElement {

    //region ComboLov
    BelgenetElement selectComboLov(String value);
    BelgenetElement clearComboLov();
    BelgenetElement getComboLovTitle();
    BelgenetElement getComboLovDetail();
    String getComboLovTitleText();
    String getComboLovDetailText();
    Boolean isComboLovSelected();
    //endregion

    //region ComboBox
    BelgenetElement selectComboBox(String text, boolean... js);
    List<String> getComboBoxValues();
//    BelgenetElement getComboBoxHtmlList();
    //endregion


}

