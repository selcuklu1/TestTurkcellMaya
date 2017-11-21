package pages.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.SolMenu;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.assertNoJavascriptErrors;

public class KepIlePostalanacaklarPage extends MainPage{

    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnEvrakPostala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement cmbGonderici = $(By.id("mainPreviewForm:dataTableId:0:fromKepAdresId"));

    public KepIlePostalanacaklarPage gondericiKontrol(String gonderici){

        String gonderen =   cmbGonderici.getText();
        Assert.assertEquals(gonderici,gonderen);
        System.out.println("Metin değer: " +gonderen+ " Metin değer: "+ gonderici);
        return this;
    }

    public KepIlePostalanacaklarPage evrakPostala(){
        btnEvrakPostala.click();
        return this;
    }

    public String gondericiCek(){
        String secilen = cmbGonderici.getSelectedText();
        return secilen;
    }

    public KepIlePostalanacaklarPage ilkEvrakTikla(){
        tblIlkEvrak.click();
        return this;
    }

    public KepIlePostalanacaklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.KEPilePostalanacaklar);
        return this;
    }

}
