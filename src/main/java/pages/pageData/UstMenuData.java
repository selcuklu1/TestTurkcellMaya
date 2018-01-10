package pages.pageData;

import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.OlurYazisiOlusturPage;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class UstMenuData {

    public interface UstMenuDataInterface {
        public String getName();
        public String getGroupName();
    }

    private enum UstMenuGroup {
        EvrakIslemleri("Evrak İşlemleri"),
        TeskilatKisiTanimlari("Teşkilat/Kişi Tanımları"),
        KlasorIslemleri("Klasör İşlemleri"),
        KullaniciIslemleri("Kullanıcı İşlemleri"),
        YonetimSayfalari("Yönetim Sayfaları"),
        KisiselIslemlerim("Kişisel İşlemlerim"),
        Raporlar("Raporlar"),
        AmirIslemleri("Amir İşlemleri");

        private String name;

        UstMenuGroup(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum EvrakIslemleri implements UstMenuDataInterface {
        EvrakOlustur("Evrak Oluştur"),
        OlurYazisiOlustur("Olur Yazısı Oluştur"),
        GidenEvrakKayit("Giden Evrak Kayıt"),
        GelenEvrakKayit("Gelen Evrak Kayıt"),
        KararYazisiOlustur("Karar Yazısı Oluştur"),
        ArsivGidenEvrakKayit("Arşiv Giden Evrak Kayıt"),
        ArsivGelenEvrakKayit("Arşiv Gelen Evrak Kayıt"),
        FizikselArsivArama("Fiziksel Arşiv Arama"),
        EvrakArama("Evrak Arama");

        private String name;
        private String groupName = UstMenuGroup.EvrakIslemleri.getName();

        EvrakIslemleri(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public enum TeskilatKisiTanimlari implements UstMenuDataInterface {
        TuzelKisiYonetimi("Tüzel Kişi Yönetimi"),
        GercekKisiYonetimi("Gerçek Kişi Yönetimi"),
        KurumYonetimi("Kurum Yönetimi"),
        LogoYonetimi("Logo Yönetimi"),
        BirimYonetimi("Birim Yönetimi"),
        NumaratorYonetimi("Numaratör Yönetimi"),
        BirimSenkronizasyonu("Birim Senkronizasyonu");

        private String name;
        private String groupName = UstMenuGroup.TeskilatKisiTanimlari.getName();

        TeskilatKisiTanimlari(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public enum KlasorIslemleri implements UstMenuDataInterface {
        KlasorYonetimi("Klasör Yönetimi"),
        KonuKoduYonetimi("Konu Kodu Yönetimi"),
        KlasorEvrakIslemleri("Klasör Evrak İşlemleri"),
        SaklamaPlaniYonetimi("Saklama Planı Yönetimi"),
        KlasorSablonYonetimi("Klasör Şablon Yönetimi"),
        ErisimYonetimi("Erişim Yönetimi");

        private String name;
        private String groupName = UstMenuGroup.KlasorIslemleri.getName();

        KlasorIslemleri(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public enum KullaniciIslemleri implements UstMenuDataInterface {
        YonetimHavuzuYonetimi("Yönetim Havuzu Yönetimi"),
        KullaniciYonetimi("Kullanıcı Yönetimi"),
        KullaniciListesiYonetimi("Kullanıcı Listesi Yönetimi"),
        RolYonetimi("Rol Yönetimi");

        private String name;
        private String groupName = UstMenuGroup.KullaniciIslemleri.getName();

        KullaniciIslemleri(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public enum YonetimSayfalari implements UstMenuDataInterface {
        BakimaAl("Bakıma Al"),
        EImzaDenetimi("E-İmza Denetimi"),
        EvrakDogrulamaAktarim("Evrak Doğrulama Aktarım"),
        ManuelIndex("Manuel Index"),
        ZamanDamgaliSistemLoglari("Zaman Damgalı Sistem Logları"),
        OfflineZamanDamgasi("Offline Zaman Damgası"),
        EYPEvrakGonderimi("EYP Evrak Gönderimi"),
        DagitimPlaniYonetimi("Dağıtım Planı Yönetimi"),
        DuyuruYonetimi("Duyuru Yönetimi"),
        MenuYonetimi("Menü Yönetimi"),
        ParametreYonetimi("Parametre Yönetimi"),
        BelgenetHataLOG("Belgenet Hata LOG"),
        SistemParametreleri("Sistem Parametreleri"),
        SistemSabitleri("Sistem Sabitleri"),
        YazismaKurallariYonetimi("Yazışma Kuralları Yönetimi"),
        PulYonetimi("Pul Yönetimi"),
        EvrakMetadataYonetimi("Evrak Metadata Yönetimi"),
        EvrakHavaleKurallariYonetimi("Evrak Havale Kuralları Yönetimi"),
        OnerilenKonfigurasyonlar("Önerilen Konfigurasyonlar"),
        BirimIcerikSablonlari("Birim İçerik Şablonları"),
        FormSablonYonetimi("Form Şablon Yönetimi");

        private String name;
        private String groupName = UstMenuGroup.YonetimSayfalari.getName();

        YonetimSayfalari(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public enum KisiselIslemlerim implements UstMenuDataInterface {
        KullaniciIcerikSablonlari("Kullanıcı İçerik Şablonları"),
        SikKullanilanlar("Sık Kullanılanlar"),
        KullaniciVarsayilanYonetimi("Kullanıcı Varsayılan Yönetimi"),
        SifreDegistirme("Şifre Değiştirme"),
        OnayAkisiYonetimi("Onay Akışı Yönetimi");

        private String name;
        private String groupName = UstMenuGroup.KisiselIslemlerim.getName();

        KisiselIslemlerim(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public enum Raporlar implements UstMenuDataInterface {
        EvrakBeklemeSuresiRaporu("Evrak Bekleme Süresi Raporu"),
        IptalEdilenEvraklarRaporu("İptal Edilen Evraklar Raporu"),
        VekaletRaporu("Vekalet Raporu"),
        HavaleEdilenEvrakRaporu("Havale Edilen Evrak Raporu"),
        BelgeDogrulama("Belge Doğrulama"),
        ToplamDokumanRaporu("Toplam Doküman Raporu"),
        BirimKlasorleriRaporu("Birim Klasörleri Raporu"),
        CevaplananEvrakRaporu("Cevaplanan Evrak Raporu"),
        IslemSuresiGecenEvrakRaporu("İşlem Süresi Geçen Evrak Raporu"),
        GenelEvrakRaporu("Genel Evrak Raporu"),
        PersonelveAcikEvrakIstatistigi("Personel ve Açık Evrak İstatistiği"),
        BirimlerdekiKisilerRaporu("Birimlerdeki Kişiler Raporu"),
        SistemLoglari("Sistem Logları"),
        KlasorAcmaKapamaRaporu("Klasor Açma Kapama Raporu"),
        KaydedilenGelenEvrak("Kaydedilen Gelen Evrak"),
        KullaniciIstatistikleri("Kullanıcı İstatistikleri"),
        EvrakIstatistikleri("Evrak İstatistikleri"),
        KullaniciveEvrakIstatistikleri("Kullanıcı ve Evrak İstatistikleri"),
        BirimEvraklariRaporu("Birim Evrakları Raporu"),
        PttRaporu("Ptt Raporu"),
        GelenEvrakZimmetRaporu("Gelen Evrak Zimmet Raporu"),
        PostalananEvrakRaporu("Postalanan Evrak Raporu");

        private String name;
        private String groupName = UstMenuGroup.Raporlar.getName();

        Raporlar(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    public enum AmirIslemleri implements UstMenuDataInterface {
        KullaniciEvrakDevret("Kullanıcı Evrak Devret"),
        VekaletVer("Vekalet Ver");

        private String name;
        private String groupName = UstMenuGroup.AmirIslemleri.getName();

        AmirIslemleri(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getGroupName() {
            return groupName;
        }
    }

}