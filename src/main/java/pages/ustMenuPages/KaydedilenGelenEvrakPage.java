package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KaydedilenGelenEvrakPage extends MainPage {

    SelenideElement cmbGeldigiYer = $(By.id("birimeGelenEvrakRaporuForm:evrakAramaGeldigiYer_id"));
    BelgenetElement cmbBirim = comboLov(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuBirimLovId:j_idt126"));
    SelenideElement txtEvrakKayitNo = $(By.id("birimeGelenEvrakRaporuForm:evrakNoId"));
    SelenideElement btnSorgula = $(By.id("birimeGelenEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnRaporAlExcel = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt19593"));
    SelenideElement btnRaporAlPdf = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable:j_idt22373"));
    SelenideElement tblKaydedilenGelenEvrak = $(By.id("birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data"));
    SelenideElement tbldene = $(By.xpath("//tbody[@id='birimeGelenEvrakRaporuForm:birimeGelenEvrakRaporuDataTable_data']/tr/td[2]/div"));

    public KaydedilenGelenEvrakPage openPage() {
        ustMenu("Raporlar", "Kaydedilen Gelen Evrak");
        return this;
    }

    @Step("Birim alanı doldur")
    public KaydedilenGelenEvrakPage birimDoldur(String birim) {
        cmbBirim.selectLov(birim);
        return this;
    }

    @Step("Geldiği yer seç")
    public KaydedilenGelenEvrakPage geldigiYerSec(String geldigiYer) {
        cmbGeldigiYer.selectOptionByValue(geldigiYer);
        return this;
    }

    @Step("Gelen Evrak no alanını doldur")
    public KaydedilenGelenEvrakPage gelenEvrakNoDoldur(String evrakNo) {
        txtEvrakKayitNo.sendKeys(evrakNo);
        return this;
    }

    @Step("Sorgula butununa bas")
    public KaydedilenGelenEvrakPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Rapor al Excel")
    public KaydedilenGelenEvrakPage raporAlExcel() throws IOException {
        boolean flag = false;

        File dir = new File("C:\\Users\\Emre_Sencan\\Downloads\\");
//        File[] dir_contents = dir.listFiles();
//        Pattern y = Pattern.compile("[~0-9]");


        File[] dir_contents = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String name =pathname.getName().toLowerCase();
                return name.startsWith("Rapor_")&&pathname.isFile();
            }
        });
        String s = null;
        String[] raporNumberFirst = new String[10];
        int max = 0;


//        Files.list(Paths.get("C:\\Users\\Emre_Sencan\\Downloads\\"))
//                .filter(Files::isRegularFile)
//                .forEach(System.out::println);


//        for(File fileOrn : dir.listFiles())
//        {
//            System.out.println(fileOrn.getName());
//        }
//
        for (int i = 0; i < dir_contents.length; i++) {
            String file = dir_contents[i].getName().toString();
//            s = "";
//            Matcher m = y.matcher(file);
//            while (m.find()) {
//                s = s + m.group();
//            }
//            raporNumberFirst[i] = s;
//            System.out.println(raporNumberFirst[i]);
        }

//        for (int j = 0; j < raporNumberFirst.length; j++) {
//            if (Integer.parseInt(raporNumberFirst[j]) > Integer.parseInt(raporNumberFirst[j + 1]))
//                max = Integer.parseInt(raporNumberFirst[j]);
//            else
//                max = Integer.parseInt(raporNumberFirst[j + 1]);
//        }
//        System.out.println(max);
        btnRaporAlExcel.click();


//        boolean flag = isFileDownloaded("C:\\Users\\Emre_Sencan\\Downloads\\","Rapor_.xls");
        return this;
    }

    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage raporAlPdf() {
        btnRaporAlPdf.click();
        confirm();
        return this;
    }

    @Step("Rapor al PDF")
    public KaydedilenGelenEvrakPage txtClear() {
        txtEvrakKayitNo.clear();
        return this;
    }

    public KaydedilenGelenEvrakPage tabloKontrolu(String evrakNo) {
//        WebElement columnId =  findElementOnTableByColumnInput(tblKaydedilenGelenEvrak,1,evrakNo);
        String text = tbldene.text();
        Assert.assertEquals(text, evrakNo);
        return this;
    }

}
