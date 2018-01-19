@import TS0577_objects.spec

= Documant =
    #Head
        editorAntetBaslik:
            inside container  52px top
            centered horizontally inside container

        kurumHeaderSatir1:
            below editorAntetBaslik ~2px
            centered horizontally inside container

        kurumHeaderSatir2:
            height 100% of kurumHeaderSatir1/height
            below kurumHeaderSatir1 ~2px
            centered horizontally inside container

        kurumHeaderSatir3:
            height 100% of kurumHeaderSatir2/height
            below kurumHeaderSatir2 ~2px
            centered horizontally inside container

        birim:
            height 100% of kurumHeaderSatir3/height
            below kurumHeaderSatir3 ~2px
            centered horizontally inside container

    #Sayi
        sayiLabel:
            inside container  71px left
            below birim ~26px

        sayiDots:
            aligned horizontally all sayiLabel
            right-of sayiLabel ~2px

        sayi:
            aligned horizontally all sayiLabel
            right-of sayiDots ~2px

    #Konu
        konuLabel:
            width 100% of sayiLabel/width
            height 100% of sayiLabel/height
            inside container  71px left
            aligned vertically all sayiLabel
            below sayiLabel ~2px

        konuDots:
            aligned horizontally all konuLabel
            right-of konuLabel ~2px

        konu:
            height 100% of konuLabel/height
            aligned horizontally all konuLabel
            right-of konuDots ~2px

    #Hitap
        hitapMetinAlani:
            below konu 40px
            inside container ~380px left

        hitap:
            below hitapMetinAlani ~2px
            centered horizontally on hitapMetinAlani ~2px

    #Ilgi
        ilgiLabel:
            inside container 70px left
            below hitap 24 to 30px

        ilgiDots:
            height 100% of ilgiLabel/height
            aligned horizontally all ilgiLabel
            right-of ilgiLabel 0px

        ilgi_a:
            height 50% of ilgiLabel/height
            aligned horizontally top ilgiLabel
            right-of ilgiDots 0px

        ilgi_b:
            width 100% of ilgi_a/width
            height 50% of ilgiLabel/height
            height 100% of ilgi_a/height
            aligned horizontally bottom ilgiLabel
            aligned vertically all ilgi_a
            right-of ilgiDots ~2px
            below ilgi_a 0px

    #remoteta alamniyor
    #Editor
        #editor:
            #below ilgi_b 12 to 18px
            #centered horizontally inside container

    #Imzaci 1
        imzaci1MetinAlani:
            inside container  814 to 836px top, 90 to 100px right
            #below editor 23 to 30px

        imzaci1Isim:
            width 100% of imzaci1MetinAlani/width
            aligned vertically all imzaci1MetinAlani
            below imzaci1MetinAlani 0px

        imzaci1Gorev:
            width 100% of imzaci1Isim/width
            aligned vertically all imzaci1Isim
            below imzaci1Isim 0px

    #Olur Yazisi
        olurYazisi:
            centered horizontally inside container ~2px
            below imzaci1Gorev ~14px

        olurYazisiTarih:
            below olurYazisi 0 to 5px
            centered horizontally on olurYazisi

    #Imzaci 2
        imzaci2MetinAlani:
            below olurYazisiTarih 12 to 18px
            centered horizontally on olurYazisiTarih

        imzaci2Isim:
            aligned vertically all imzaci2MetinAlani
            below imzaci2MetinAlani 0px
            centered horizontally inside container ~2px

        imzaci2Gorev:
            width 100% of imzaci2MetinAlani/width
            width 100% of imzaci2Isim/width
            aligned vertically all imzaci2Isim
            below imzaci2Isim 0px
            centered horizontally on olurYazisi

    #Ek
        ekLabel:
            inside container ~80px left
            below imzaci2Gorev 12 to 15px

        ek:
            aligned horizontally all ekLabel ~2px
            right-of ekLabel 0px
            inside container 75 to 85px bottom