package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.ElementFinder;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.By;

import java.io.IOException;

class ComboBox {

    class SelectComboBox implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            if (args == null || args.length == 0)
                return (BelgenetElement) proxy;

            boolean[] jaArr = (boolean[]) args[1];
            boolean js = (jaArr.length <= 0) || jaArr[0];

            new ComboBoxHelper().selectComboBox(proxy, args[0].toString(), js);

            return (BelgenetElement) proxy;
        }
    }

    class GetComboBoxList implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {


            By ulLocator = new ComboBoxHelper().getComboBoxHtmlList(proxy);
            return ElementFinder.wrap(BelgenetElement.class, null, ulLocator, 0);
        }
    }

    class GetComboBoxValues implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return new ComboBoxHelper().getComboBoxValues(proxy);
        }
    }
}
