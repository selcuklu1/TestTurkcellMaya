package pages.pageData.alanlar;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 22.02.2018
 * Açıklama:
 */
public enum DagitimElemanlariTipi {
        /*<option value="K">Kullanıcı</option>
        <option value="B" selected="selected">Birim</option>
        <option value="G">Gerçek Kişi</option>
        <option value="T">Tüzel Kişi</option>
        <option value="D">Kurum</option>*/

    KULLANICI("Kullanıcı", "K"),
    BIRIM("Birim", "B"),
    GERCEK_KISI("Gerçek Kişi", "G"),
    TUZEL_KISI("Tüzel Kişi", "T"),
    KURUM("Kurum", "D");

    private String optionValue;
    private String optionText;

    DagitimElemanlariTipi(String optionText, String optionValue) {
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
