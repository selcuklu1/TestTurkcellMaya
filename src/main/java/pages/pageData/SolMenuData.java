package pages.pageData;

public class SolMenuData {

    private static String groupIdPrefix = "leftMenuForm:leftMenu";

    public enum IslemBekleyenEvraklar implements SolMenuDataInterface {
        GelenEvraklar("Gelen Evraklar"),
        ImzaBekleyenler("İmza Bekleyenler"),
        BeklemeyeAlinanlar("Beklemeye Alınanlar"),
        ParafBekleyenler("Paraf Bekleyenler"),
        BenimlePaylasilanlar("Benimle Paylaşılanlar"),
        KoordineBekleyenler("Koordine Bekleyenler"),
        KontrolBekleyenler("Kontrol Bekleyenler"),
        YanParafBekleyenler("Yan Paraf Bekleyenler"),
        TakibimdekiEvraklar("Takibimdeki Evraklar"),
        TaslakEvraklar("Taslak Evraklar"),
        Onaylar("Onaylar"),
        VekaletOnaylari("Vekalet Onayları");

        private String groupId = groupIdPrefix + "IslemBekleyenEvraklar";
        private String groupText = "İşlem Bekleyen Evraklar";
        private String menuText;

        IslemBekleyenEvraklar(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public enum IslemYaptiklarim implements SolMenuDataInterface {
        Imzaladiklarim("İmzaladıklarım"),
        Parafladiklarim("Parafladıklarım"),
        OnaySureciTamamlanmayanlar("Onay Süreci Tamamlanmayanlar"),
        Cevapladiklarim("Cevapladıklarım"),
        Paylastiklarim("Paylaştıklarım"),
        IadeEttiklerim("İade Ettiklerim"),
        HavaleEttiklerim("Havale Ettiklerim"),
        Klasorekaldirdiklarim("Klasöre Kaldırdıklarım"),
        YanParafladiklarim("Yan Parafladıklarım"),
        KonrolEttiklerim("Kontrol Ettiklerim"),
        KoordineParafladiklarim("Koordine Parafladıklarım"),
        TebligEttiklerim("Tebliğ Ettiklerim"),
        TebellugEttiklerim("Tebellüğ Ettiklerim"),
        Hazirladiklarim("Hazırladıklarım");


        private String groupId = groupIdPrefix + "IslemYaptiklarim";
        private String groupText = "İşlem Yaptıklarım";
        private String menuText;

        IslemYaptiklarim(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public enum BirimEvraklari implements SolMenuDataInterface {
        HavaleOnayinaGelenler("Havale Onayına Gelenler"),
        HavaleOnayiVerdiklerim("Havale Onayı Verdiklerim"),
        HavaleOnayinaSunduklarim("Havale Onayına Sunduklarım"),
        KaydedilenGelenEvraklar("Kaydedilen Gelen Evraklar"),
        KaydedilenGidenEvraklar("Kaydedilen Giden Evraklar"),
        PostalanacakEvraklar("Postalanacak Evraklar"),
        TopluPostaladiklarim("Toplu Postaladıklarım"),
        Postalananlar("Postalananlar"),
        KEPGelenkutusu("KEP Gelen Kutusu"),
        KEPGidenkutusu("KEP Giden Kutusu"),
        MedasileGonderilecekler("Medas ile Gönderilecekler"),
        KEPilePostalanacaklar("KEP ile Postalanacaklar"),
        TopluPostalanacakEvraklar("Toplu Postalanacak Evraklar"),
        TeslimAlinmayiBekleyenler("Teslim Alınmayı Bekleyenler"),
        TeslimAlinanlar("Teslim Alınanlar"),
        AskidakiEvraklar("Askıdaki Evraklar"),
        BirimHavaleEdilenler("Birim Havale Edilenler"),
        BirimeIadeEdilenler("Birime İade Edilenler"),
        PostaListesi("Posta Listesi"),
        EpostaKutusu("E-posta Kutusu");

        private String groupId = groupIdPrefix + "BirimEvraklari";
        private String groupText = "Birim Evrakları";
        private String menuText;

        BirimEvraklari(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public enum KapatmaIslemleri implements SolMenuDataInterface {
        CevapliKapattiklarim("Cevaplı Kapattıklarım"),
        ImzaBekleyenler("İmza Bekleyenler"),
        Imzaladiklarim("İmzaladıklarım"),
        Kapattiklarim("Kapattıklarım"),
        OnayaSunduklarim("Onaya Sunduklarım"),
        ParafBekleyenler("Paraf Bekleyenler"),
        Parafladiklarim("Parafladıklarım"),
        SureliKapattiklarim("Süreli Kapattıklarım");

        private String groupId = groupIdPrefix + "KapatmaIslemleri";
        private String groupText = "Kapatma İşlemleri";
        private String menuText;

        KapatmaIslemleri(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public enum Bildirimler implements SolMenuDataInterface {
        Tebligler("Tebliğler"),
        Mesajlar("Mesajlar");

        public String groupId = groupIdPrefix + "Bildirimler";
        private String groupText = "ildirimler ";
        private String menuText;

        Bildirimler(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public enum ArsivIslemleri implements SolMenuDataInterface {
        FizikselArsiveKaldirilacaklar("Fiziksel Arşive Kaldırılacaklar"),
        FizikselArsivdekiler("Fiziksel Arşivdekiler"),
        FizikselArsivdenTalepEdilenler("Fiziksel Arşivden Talep Edilenler"),
        FizikselArsivdenPersoneleVerilenler("Fiziksel Arşivden Personele Verilenler");

        private String groupId = groupIdPrefix + "ArsivIslemleri";
        private String groupText = "Fiziksel Arşiv İşlemleri";
        private String menuText;

        ArsivIslemleri(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public enum YoneticiIslemleri implements SolMenuDataInterface {

        dummy("");

        private String groupId = groupIdPrefix + "YoneticiIslemleri";
        private String groupText = "Yönetici İşlemleri";
        private String menuText;

        YoneticiIslemleri(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public enum KurulIslemleri implements SolMenuDataInterface {
        KararIzleme("Karar İzleme"),
        GundemIzleme("Gündem İzleme");

        private String groupId = groupIdPrefix + "KurulIslemleri";
        private String groupText = "Kurul İşlemleri";
        private String menuText;

        KurulIslemleri(String menuText) {
            this.menuText = menuText;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getMenuText() {
            return menuText;
        }
    }

    public interface SolMenuDataInterface {
        public String getGroupId();

        public String getMenuText();
    }
}

