package pages.pageData.alanlar;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 7.01.2018
 * Açıklama:
 */
public enum BilgiSecimTipi {
    /*<option value="B">Birim</option>
     <option value="K">Kullanıcı</option>
     <option value="G">Gerçek Kişi</option>
     <option value="T">Tüzel Kişi</option>
     <option value="D" selected="selected">Kurum</option>
     <option value="P">Dağıtım Planları</option>*/
    BIRIM("Birim", "B"),
    KULLANICI("Kullanıcı", "K"),
    GERCEK_KISI("Gerçek Kişi", "G"),
    TUZEL_KISI("Tüzel Kişi", "T"),
    KURUM("Kurum", "D"),
    DAGITIM_PLANLARI("Dağıtım Planları", "P");

    private String optionValue;
    private String optionText;

    BilgiSecimTipi(String optionText, String optionValue) {
        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public String getOptionText() {
        return optionText;
    }
}
