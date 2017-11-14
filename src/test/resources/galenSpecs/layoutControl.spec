@set
    header1_text        "T.C."
    header2_text        "GENEL MÜDÜRLÜK MAKAMI"
    header3_text        "BİLİŞİM HİZMETLERİ GENEL MÜDÜR YARDIMCISI"
    header4_text        "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ"
    header5_text        "OPTİİM BİRİM"
    body_OPTIIM_text    "OPTİİM"

@objects
	page	        css	    #viewer > div > div.textLayer
	header1         xpath   //*[@id="viewer"]/div/div[2]/div[4]
	header2         xpath   //*[@id="viewer"]/div/div[2]/div[5]
	header3         xpath   //*[@id="viewer"]/div/div[2]/div[6]
	header4         xpath   //*[@id="viewer"]/div/div[2]/div[7]
	header5         xpath   //*[@id="viewer"]/div/div[2]/div[8]

	body_OPTIIM     xpath   //*[@id="viewer"]/div/div[2]/div[9]
    body_DATE       xpath   //*[@id="viewer"]/div/div[2]/div[11]

= Documant =
    @on screensize1
        header1:
            text is ${header1_text}
            centered horizontally inside page 10px 
            inside page ~ 63 px top
            
        header2:
             text is ${header2_text}
             aligned vertically centered header1 5px
             #inside page 82px top
             below header1 0 px 

        body_OPTIIM:
            text is ${body_OPTIIM_text}
            inside page ~ 72px left

        body_DATE:
            right-of body_OPTIIM
            aligned horizontally all body_OPTIIM

    @on screensize2
        header1:
            text is ${header1_text}
            centered horizontally inside page 10px 
            inside page ~ 48 px top
            
        header2:
             text is ${header2_text}
             aligned vertically centered header1 5px
             #inside page 82px top
             below header1 0 px 

        body_OPTIIM:
            text is ${body_OPTIIM_text}
            inside page ~ 55px left

        body_DATE:
            right-of body_OPTIIM
            aligned horizontally all body_OPTIIM
