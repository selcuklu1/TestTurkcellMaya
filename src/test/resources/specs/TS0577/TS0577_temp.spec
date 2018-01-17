@objects
    container               css     div[id='yeniOnayEvrakForm:allPanels_content']
    firstPageHeader         css     div[class=firstPageHeader]

    editorAntetBaslik       xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//button[span[.='T.C.']]
    kurumHeaderSatir1       xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//span[.='GENEL MÜDÜRLÜK MAKAMI']
    kurumHeaderSatir2       xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//span[.='BİLİŞİM HİZMETLERİ GENEL MÜDÜR YARDIMCISI']
    kurumHeaderSatir3       xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//span[.='YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ']
    birim                   xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//span[.='${birim}']

    sayiLabel               xpath   //span[@id='yeniOnayEvrakForm:editorTarihKonuSayi']//tbody/tr[1]/td[.='Sayı']
    sayiDots                xpath   //span[@id='yeniOnayEvrakForm:editorTarihKonuSayi']//tbody/tr[1]/td[.=':']
    sayi                    xpath   //span[@id='yeniOnayEvrakForm:editorTarihKonuSayi']//tbody/tr[1]/td[.='${sayi}']
    konuLabel               xpath   //span[@id='yeniOnayEvrakForm:editorTarihKonuSayi']//tbody/tr[2]/td[.='Konu']
    konuDots                xpath   //span[@id='yeniOnayEvrakForm:editorTarihKonuSayi']//tbody/tr[2]/td[.=':']
    konu                    xpath   //span[@id='yeniOnayEvrakForm:editorTarihKonuSayi']//tbody/tr[2]/td[.='${konu}']

    hitapMetinAlani         xpath   (//div[@id='yeniOnayEvrakForm:hitapInplace']/button)[1]
    hitap                   xpath   //div[@id='yeniOnayEvrakForm:hitapInplace']//button[span[.='... Makamına']]

    ilgiLabel               xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//td[normalize-space(.)='İlgi']
    ilgiDots                xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//td[normalize-space(.)='İlgi']/following-sibling::td[.=':']
    ilgi_a                  xpath   //div[@id='yeniOnayEvrakForm:ilgiOutPanel']/descendant-or-self::tr[contains(normalize-space(.),'${ilgi_a}')]
    ilgi_b                  xpath   //div[@id='yeniOnayEvrakForm:ilgiOutPanel']/descendant-or-self::tr[contains(normalize-space(.),'${ilgi_b}')]

    editor                  css     .ui-edys-editor

    imzaci1MetinAlani       xpath   (//table[contains(@id,'imzaciGridPanel')])[1]//tr[td/button[contains(@onclick,'imzaciAdinaInplaceDialog')]]
    imzaci1Isim             xpath   (//table[contains(@id,'imzaciGridPanel')])[1]//tr[td/span[normalize-space(.)='${imzaci1Isim}']]
    imzaci1Gorev            xpath   (//table[contains(@id,'imzaciGridPanel')])[1]//tr[descendant::button[normalize-space(.)='${imzaci1Gorev}']]

    olurYazisi              xpath   (//table[contains(@id,'imzaciGridPanel')])//span[normalize-space(.)='OLUR']
    olurYazisiTarih         xpath   (//table[contains(@id,'imzaciGridPanel')])//span[normalize-space(.)='../../....']

    imzaci2MetinAlani       xpath   (//table[contains(@id,'imzaciGridPanel')])[2]//tr[td/button[contains(@onclick,'imzaciAdinaInplaceDialog')]]
    imzaci2Isim             xpath   (//table[contains(@id,'imzaciGridPanel')])[2]//tr[td/span[normalize-space(.)='${imzaci2Isim}']]
    imzaci2Gorev            xpath   (//table[contains(@id,'imzaciGridPanel')])[2]//tr[descendant::button[normalize-space(.)='${imzaci2Gorev}']]

    ekLabel                 xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//td[normalize-space(.)='Ek :']
    ek                      xpath   //div[@id='yeniOnayEvrakForm:allPanels_content']//span[.='${ek}']