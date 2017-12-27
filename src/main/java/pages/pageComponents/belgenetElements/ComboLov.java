package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.ElementFinder;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.By;

import java.io.IOException;

class ComboLov {

    class ClearLastSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.clearLastSelectedLov();
        }
    }

    class ClearAllSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.clearAllSelectedLov();
        }
    }

    class SelectLov implements Command<SelenideElement> {
        @Override
        public SelenideElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            if (args.length > 0) {
                By by = comboLovHelper.selectLov(args[0].toString());
                return ElementFinder.wrap(BelgenetElement.class, null, by, 0);
            }
            else
                return (BelgenetElement) proxy;
        }
    }

    class GetLastSelectedLovValue implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.getLastSelectedLovValue();
        }
    }

    class LastSelectedLovTitle implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.lastSelectedLovTitle();
        }
    }

    class LastSelectedLovDetail implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.lastSelectedLovDetail();
        }
    }

    class LastSelectedLovTitleText implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.lastSelectedLovTitleText();
        }
    }

    class LastSelectedLovDetailText implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.lastSelectedLovDetailText();
        }
    }

    class IsLovSelected implements Command<Boolean> {

        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.isLovSelected();
        }
    }

    class IsLovValueSelectable implements Command<Boolean> {

        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.isLovValueSelectable(args[0].toString());
        }
    }


    class OpenTree implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.openTree();
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
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.type(args[0].toString());
        }
    }

    class IsEmpty implements Command<Boolean> {
        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.isEmpty();
        }
    }

    class TitleItems implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.titleItems();
        }
    }

    class DetailItems implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.detailItems();
        }
    }

    class LastSelectedLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            By by = comboLovHelper.lastSelectedLov();
            return ElementFinder.wrap(BelgenetElement.class, null, by, 0);
        }
    }

    class CloseLovTreePanel implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            comboLovHelper.closeLovTreePanel();
            return (BelgenetElement) proxy;
        }
    }

    class AllSelectedLov implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.allSelectedLov();
        }
    }

    class SelectedTitles implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.selectedTitles();
        }
    }

    class SelectedDetails implements Command<ElementsCollection> {
        @Override
        public ElementsCollection execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            ComboLovHelper comboLovHelper = new ComboLovHelper();
            comboLovHelper.setLocators(proxy);
            return comboLovHelper.selectedDetails();
        }
    }

}
