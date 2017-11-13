package pageComponents.belgenetElements;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;

import java.io.IOException;

import static pageComponents.belgenetElements.ComboLovHelper.*;

class ComboLov {

    class ClearComboLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return clearComboLov(proxy);
        }
    }

    class SelectLov implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            if (args == null || args.length == 0 || args[0]=="")
                return clearComboLov(proxy);
            else
                return selectLov(proxy,args[0].toString());
        }
    }

    class GetSelectedLovValue implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return getSelectedLovValue(proxy);
        }
    }

    class SelectedLovTitle implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return selectedLovTitle(proxy);
        }
    }

    class SelectedLovDetail implements Command<BelgenetElement> {
        @Override
        public BelgenetElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return selectedLovDetail(proxy);
        }
    }

    class GetSelectedLovTitle implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return getSelectedLovTitle(proxy);
        }
    }

    class GetSelectedLovDetail implements Command<String> {
        @Override
        public String execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return getSelectedLovDetail(proxy);
        }
    }

    class IsSelectedLov implements Command<Boolean>{

        @Override
        public Boolean execute(SelenideElement proxy, WebElementSource locator, Object[] args) throws IOException {
            return isSelectedLov(proxy);
        }
    }
}
