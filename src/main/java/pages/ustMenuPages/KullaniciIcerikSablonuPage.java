package pages.ustMenuPages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageData.UstMenuData;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class KullaniciIcerikSablonuPage extends MainPage
{

    SelenideElement btnYeniSablonOlustur = $(By.id("kullaniciSablonForm:sablonIslemYeniButton_Id"));
    SelenideElement txtSablonAdi = $(By.id("kullaniciSablonForm:sablonAdiText_id"));
    SelenideElement editor = $(By.id("cke_kullaniciSablonForm:kullaniciIcerikCkEditor"));
    SelenideElement btnKaydet = $(By.id("kullaniciSablonForm:sablonIslemKaydetButton_id"));
    SelenideElement btnSil = $(By.id("kullaniciSablonForm:sablonIslemSilButton_id"));
    SelenideElement btnEvrakOnizleme = $(By.id("kullaniciSablonForm:sablonOnizlemeButton_id"));
    private SearchTable searchTable = new SearchTable($("#birimSablonForm [id$='sablonDataTable']"));


    @Step("Kullanici Icerik Sablonu Sayfasi Acilir")
    public KullaniciIcerikSablonuPage openPage()
    {
        ustMenu(UstMenuData.KullaniciIslemleri.KullaniciIcerikSablonlari);
        return this;
    }

    @Step("Yeni Şablon oluşturu butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage yeniSablonOlusturGeldigiGorme()
    {
        boolean durum = btnYeniSablonOlustur.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Sablon adi geldigi gorulur")
    public KullaniciIcerikSablonuPage sablonAdiGeldigiGorme()
    {
        boolean durum = txtSablonAdi.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Kaydet butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage kaydetGeldigiGorme()
    {
        boolean durum = btnKaydet.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Kaydet tıklanır")
    public KullaniciIcerikSablonuPage kaydet(){
        btnKaydet.pressEnter();
        return this;
    }

    @Step("Sil butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage silGeldigiGorme()
    {
        boolean durum = btnSil.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Evrak Önizleme butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage evrakOnizlemeGeldigiGorme()
    {
        boolean durum = btnEvrakOnizleme.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Evrak Önizleme tıklanır")
    public KullaniciIcerikSablonuPage evrakOnizleme(){
        btnEvrakOnizleme.click();
        int i =0;
        while (i<200){
            sleep(i); i++;
        }
        return this;
    }

    @Step("Şablon PDF formatında geldiği görülür. Doğru bilgiler ile geldiği gorülür: İçerik: {sablon}")
    public KullaniciIcerikSablonuPage sablonPDFGeldigiGormeDogruBilgiGeldigiGorme(String sablon){
        switchTo().window(1);
        boolean durum = $$(By.id("viewerContainer")).filterBy(Condition.text(sablon)).size()==1;
        Assert.assertEquals(durum,true);
        closeNewWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Şablon PDF formatında geldiği görülür. Doğru bilgiler ile geldiği gorülür: İçerik: {sablon},{icerik}")
    public KullaniciIcerikSablonuPage sablonPDFGeldigiGormeDogruBilgiGeldigiGorme(String sablon,String icerik){
        switchTo().window(1);
        boolean durum = $$(By.id("viewerContainer")).filterBy(Condition.text(sablon)).size()==1;
        boolean durum2 = $$(By.id("viewerContainer")).filterBy(Condition.text(icerik)).size()==1;
        Assert.assertEquals(durum,true);
        Assert.assertEquals(durum,durum2);
        return this;
    }

    @Step("Yeni Şablon oluştur tıklanır")
    public KullaniciIcerikSablonuPage yeniSablonOlustur(){
        btnYeniSablonOlustur.click();
        return this;
    }

    @Step("Şablon adı alanının ve editörün aktif hale geldiği görülür.")
    public KullaniciIcerikSablonuPage sablonAdiVeEditorAktifHaleGeldigiGorme(boolean durum, boolean durum2){
        boolean sablonAdiDurum = txtSablonAdi.isDisplayed();
        Assert.assertEquals(sablonAdiDurum,durum);
        boolean editorDurum = editor.isDisplayed();
        Assert.assertEquals(editorDurum,durum2);
        return this;
    }

    @Step("Şablon adı alanını doldur: {sablonAdi}")
    public KullaniciIcerikSablonuPage sablonAdiDoldur(String sablonAdi){
    txtSablonAdi.setValue(sablonAdi);
        return this;
    }

    @Step("Listeden kayıt üzerinde detay butonunu tıklanır: Şablon Adı:{sablonAdi}")
    public KullaniciIcerikSablonuPage sablonAdiGoreDetaySec(String sablonAdi){
        int i=1;
        while (i>0){
            boolean durum = $$("[id='kullaniciSablonForm'] table tr[role='row']")
                    .filterBy(Condition.text(sablonAdi)).size()==1;
            if (durum==false){
                $$("[id='kullaniciSablonForm'] table [class='ui-paginator-next ui-state-default ui-corner-all']")
                        .first().click();
            }
            else {
                $$("[id='kullaniciSablonForm'] table tr[role='row']")
                        .filterBy(Condition.text(sablonAdi)).first().$("button [class='ui-button-icon-left ui-icon details-icon']").click();
                break;
            }
        }
        return this;
    }
    
    @Step("Şablon detayının geldiği görülür.")
    public KullaniciIcerikSablonuPage sablonDetayGeldigiGorme(String icerik,String icerikDetay){
        Assert.assertEquals(icerik,icerikDetay);
        return this;
    }

    @Step("Şablon bilgilerinin geldiği görülür.")
    public KullaniciIcerikSablonuPage sablonBilgilerininGeldigiGorme(String icerik,String icerikDetay){
        Assert.assertEquals(icerik,icerikDetay);
        return this;
    }
    
    @Step("Kaydedilen şablonun liseye düştüğü görülür.")
    public KullaniciIcerikSablonuPage kaydedilenSablonListeyeDustuguGorme(String sablonAdi){
        int i=1;
        while (i>0){
            boolean durum = $$("[id='kullaniciSablonForm'] table tr[role='row']")
                    .filterBy(Condition.text(sablonAdi)).size()==1;
            if (durum==false){
              $$("[id='kullaniciSablonForm'] table [class='ui-paginator-next ui-state-default ui-corner-all']")
              .first().click();
            }
            else {
                break;
            }

        }
        return this;
    }

    @Step("Editöre yazılan bilgilerin doğru geldiği görülür.")
    public KullaniciIcerikSablonuPage detaySecilenEditorKarsilastirma(String detayIcerik,String icerik){
        Assert.assertEquals(detayIcerik,icerik);
        return this;
    }

    @Step("Listeden kaydı yapılan şablon üzerinde Detay butonunu tıklanır: Şablon adı:{sablonAdi}")
    public KullaniciIcerikSablonuPage kaydedilenSablonListesindenSablonDetaySec(String sablonAdi){
        $$("[id='kullaniciSablonForm'] table tr[role='row']")
                .filterBy(Condition.text(sablonAdi)).first().$("button [class='ui-button-icon-left ui-icon details-icon']").click();
        return this;
    }
}