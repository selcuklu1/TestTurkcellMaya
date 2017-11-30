package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;

import java.io.IOException;

import static pages.pageComponents.belgenetElements.ComboLovHelper.*;

class ComboLov {

    class ClearLastSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return clearLastSelectedLov();
        }
    }

    class ClearAllSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return clearAllSelectedLov();
        }
    }

    class SelectLov implements Command<SelenideElement> {
        @Override
        public SelenideElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return selectLov(args[0].toString());
        }
    }

    class GetLastSelectedLovValue implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return getLastSelectedLovValue();
        }
    }

    class LastSelectedLovTitle implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return lastSelectedLovTitle();
        }
    }

    class LastSelectedLovDetail implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return lastSelectedLovDetail();
        }
    }

    class LastSelectedLovTitleText implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return lastSelectedLovTitleText();
        }
    }

    class LastSelectedLovDetailText implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return lastSelectedLovDetailText();
        }
    }

    class IsLovSelected implements Command<Boolean> {

        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return isLovSelected();
        }
    }

    class IsLovValueSelectable implements Command<Boolean> {

        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return isLovValueSelectable(args[0].toString());
        }
    }


    class OpenTree implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return openTree();
        }
    }
    /*class ClearLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return clearLov();
        }
    }*/
    class Type implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return type(args[0].toString());
        }
    }
    class IsEmpty implements Command<Boolean> {
        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return isEmpty();
        }
    }
    class TitleItems implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return titleItems();
        }
    }
    class DetailItems implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return detailItems();
        }
    }
    class LastSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            setLocators(proxy);
            return lastSelectedLov();
        }
    }
}
