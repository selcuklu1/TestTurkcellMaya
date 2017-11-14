package pageComponents.belgenetElements;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.ElementFinder;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageComponents.belgenetElements.ComboBoxHelper.*;

public class ComboBox {

    class SelectComboBox implements Command<BelgenetElement>{
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            if (args == null || args.length == 0)
                return (BelgenetElement) proxy;

            boolean[] jaArr = (boolean[]) args[1];
            boolean js = (jaArr.length == 1) ? jaArr[0] : true;

            selectComboBox(proxy, args[0].toString(), js);

            return (BelgenetElement) proxy;
        }
    }

    class GetComboBoxList implements Command<BelgenetElement>{
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            By ulLocator = getComboBoxHtmlList(proxy);
            return (BelgenetElement)ElementFinder.wrap(BelgenetElement.class, null, ulLocator, 0);
        }
    }

    class GetComboBoxValues implements Command<List<String>>{
        @Override
        public List<String> execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return getComboBoxValues(proxy);
        }
    }
}
