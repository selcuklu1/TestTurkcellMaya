package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;

public abstract class BelgentCondition extends Condition {
    public BelgentCondition(String name) {
        super(name);
    }

    public static final Condition required = new Condition("required") {
        @Override
        public boolean apply(WebElement element) {
            return element.getAttribute("class").contains("required");
        }
    };
}
