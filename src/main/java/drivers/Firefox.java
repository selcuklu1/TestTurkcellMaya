package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Firefox implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        //<editor-fold desc="ProdilesIni can be used to set "default" or pre created profile">
//        ProfilesIni profilesIni = new ProfilesIni();
//        FirefoxProfile profile = profilesIni.getProfile("default");
        //</editor-fold>

        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions()
                .setProfile(profile)
                .setAcceptInsecureCerts(true)
                .addPreference("security.insecure_field_warning.contextual.enabled", false);

        return new FirefoxDriver(options);
    }
}
