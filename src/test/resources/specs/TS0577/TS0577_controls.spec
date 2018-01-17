@import TS0577_objects.spec

= Documant =
    #Head
        editorAntetBaslik:
            inside container  52px top
            centered horizontally inside container 2px

        kurumHeaderSatir1:
            below editorAntetBaslik ~3px
            centered horizontally inside container

        kurumHeaderSatir2:
            height 100% of kurumHeaderSatir1/height
            below kurumHeaderSatir1 ~3px
            centered horizontally inside container

        kurumHeaderSatir3:
            height 100% of kurumHeaderSatir2/height
            below kurumHeaderSatir2 2px
            centered horizontally inside container

        birim:
            height 100% of kurumHeaderSatir3/height
            below kurumHeaderSatir3 2px
            centered horizontally inside container

    #Sayi
        sayiLabel:
            inside container  ~71px left
            below birim ~27px

        sayiDots:
            aligned horizontally all sayiLabel
            right-of sayiLabel 0px

        sayi:
            aligned horizontally all sayiLabel
            right-of sayiLabel 7px
            right-of sayiDots 0px

    #Konu
        konuLabel:
            width 100% of sayiLabel/width
            height 100% of sayiLabel/height
            inside container  ~71px left
            aligned vertically all sayiLabel
            below sayiLabel 0px

        konuDots:
            height 100% of konuLabel/height
            inside container  118px left
            aligned horizontally all konuLabel
            right-of konuLabel 0px

        konu:
            height 100% of konuLabel/height
            height 100% of konuDots/height
            inside container  207px top, 125px left
            aligned horizontally all konuLabel
            right-of konuLabel 7px
            right-of konuDots 0px

    #Hitap
        hitapMetinAlani:
            inside container  265px top, 378px left, 383px right, 844px bottom
            below konu 40px

        hitap:
            inside container  291px top
            below hitapMetinAlani 2px
            centered horizontally on hitapMetinAlani 3px
            centered horizontally inside container 2px

    #Ilgi
        ilgiLabel:
            inside container  350px top, 70px left
            below hitap 24px

        ilgiDots:
            height 100% of ilgiLabel/height
            inside container  350px top, 120px left, 668px right
            aligned horizontally all ilgiLabel
            right-of ilgiLabel 0px

        ilgi_a:
            inside container  350px top, 125px left, 70px right, 765px bottom
            height 50% of ilgiLabel/height
            aligned horizontally top ilgiLabel
            right-of ilgiLabel 5px
            right-of ilgiDots 0px

        ilgi_b:
            width 100% of ilgi_a/width
            height 50% of ilgiLabel/height
            height 100% of ilgi_a/height
            inside container  368px top, 125px left, 70px right
            aligned horizontally bottom ilgiLabel
            aligned vertically all ilgi_a
            right-of ilgiLabel 5px
            right-of ilgiDots 0px
            below ilgi_a 0px

    #Editor
        editor:
            below ilgi_b 12px
            inside container  398px top, 70px left, 70px right, 343px bottom
            centered horizontally inside container

    #Imzaci 1
        imzaci1MetinAlani:
            inside container  814px top, 92px right
            below editor 24px

        imzaci1Isim:
            width 100% of imzaci1MetinAlani/width
            inside container  840px top, 92px right
            aligned vertically all imzaci1MetinAlani
            below imzaci1MetinAlani 0px

        imzaci1Gorev:
            width 100% of imzaci1Isim/width
            inside container  92px right
            aligned vertically all imzaci1Isim
            below imzaci1Isim 0px

    #Olur Yazisi
        olurYazisi:
            inside container  905px top, 381px left, 381px right
            centered horizontally inside container

        olurYazisiTarih:
            aligned vertically left olurYazisi 4px
            below olurYazisi 0px
            centered horizontally inside container 2px
            centered horizontally on olurYazisi 2px

    #Imzaci 2
        imzaci2MetinAlani:
            below olurYazisiTarih 13px
            centered horizontally on olurYazisiTarih
            centered horizontally inside container 2px
            inside container  308px left, 306px right, 160px bottom

        imzaci2Isim:
            width 100% of imzaci2MetinAlani/width
            aligned vertically all imzaci2MetinAlani
            below imzaci2MetinAlani 0px
            centered horizontally inside container 2px

        imzaci2Gorev:
            width 100% of imzaci2MetinAlani/width
            width 100% of imzaci2Isim/width
            aligned vertically all imzaci2Isim
            below imzaci2Isim 0px
            centered horizontally inside container 2px

    #Ek
        ekLabel:
            inside container  1038px top, 80px left, 77px bottom
            below imzaci2Gorev 12px

        ek:
            inside container  1038px top, 114px left
            aligned horizontally all ekLabel ~1px
            right-of ekLabel 0px