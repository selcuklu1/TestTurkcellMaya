package pageComponents.belgenetElements;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;

import java.io.IOException;

import static pageComponents.belgenetElements.ComboLovHelper.*;

class ComboLov {

    class ClearLastSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return clearLastSelectedLov(proxy);
        }
    }

    class ClearAllSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return clearAllSelectedLov(proxy);
        }
    }

    class SelectLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
//            if (args == null || args.length == 0 || args[0]=="")
//                return clearLastSelectedLov(proxy);
//            else
            return selectLov(proxy, args[0].toString());
        }
    }

    class GetLastSelectedLovValue implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return getLastSelectedLovValue(proxy);
        }
    }

    class LastSelectedLovTitle implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return lastSelectedLovTitle(proxy);
        }
    }

    class LastSelectedLovDetail implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return lastSelectedLovDetail(proxy);
        }
    }

    class LastSelectedLovTitleText implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return lastSelectedLovTitleText(proxy);
        }
    }

    class LastSelectedLovDetailText implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return lastSelectedLovDetailText(proxy);
        }
    }

    class IsLovSelected implements Command<Boolean> {

        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return isLovSelected(proxy);
        }
    }
}
