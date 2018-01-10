package pages.pageData.alanlar;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 7.01.2018
 * Açıklama:
 */
public enum GeregiSecimTipi {
    /*<option value="B" selected="selected">Birim</option>
	<option value="K">Kullanıcı</option>
	<option value="P">Dağıtım Planları</option>*/
    Birim("Birim", "B"),
    Kullanici("Kullanıcı", "K"),
    DagitimPlanlari("Dağıtım Planları", "P");

    private String optionValue;
    private String optionText;

    GeregiSecimTipi(String optionText, String optionValue) {
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
