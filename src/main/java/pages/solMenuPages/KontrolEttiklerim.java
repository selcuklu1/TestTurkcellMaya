package pages.solMenuPages;

import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.EvrakOnizleme;
import pages.pageData.SolMenuData;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 8.03.2018
 * Açıklama:
 */
public class KontrolEttiklerim extends EvrakOnizleme {

    @Step("Kontrol Ettiklerim sayfası aç")
    public KontrolEttiklerim openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.KonrolEttiklerim);
        return this;
    }
}
