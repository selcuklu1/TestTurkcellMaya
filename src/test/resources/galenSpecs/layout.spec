@objects
	pages		xpath	.//*[@id='viewer']/div/div[2]
	tc_alan		xpath	.//*[@id='viewer']/div/div[2]/div[2]

= Desctop =
	@on screensize1
		tc_alan:
			inside pages 63 px top
			