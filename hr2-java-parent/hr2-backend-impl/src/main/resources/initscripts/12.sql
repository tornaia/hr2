CREATE TABLE EVES_SZABADSAG_ADAT
  (
    ID									INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    SZEMELYITORZS			 			INT NOT NULL,
    EV									INT NOT NULL,
    ALAPSZABADSAG		 				INT NOT NULL,
    GYERMEKEK_UTAN		 				INT NOT NULL,
    FIATALKORU		 					INT NOT NULL,
    VAK_SZEMELY		 					INT NOT NULL,
    EGYEB_MUNKAKOR		 				INT NOT NULL,
    KT_KA_POTSZABADSAG		 			INT NOT NULL,
    KT_KA_VEZETOI		 				INT NOT NULL,
    EGYEB_CSOKKENTO		 				INT NOT NULL,
    TANULMANYI		 					INT NOT NULL,
    MULT_EVI_SZABADSAG		 			INT NOT NULL,
    BSZ_JARANDOSAG_ADOTT_EVI		 	INT NOT NULL
  )
/

ALTER TABLE EVES_SZABADSAG_ADAT ADD UNIQUE INDEX(SZEMELYITORZS, EV)
/

ALTER TABLE EVES_SZABADSAG_ADAT ADD FOREIGN KEY (SZEMELYITORZS) REFERENCES SZEMELYITORZS(TSZ)
/