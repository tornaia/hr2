CREATE TABLE IDOZITETT_FUTTATAS_TMP
  (
	ID									INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	AZONOSITO							VARCHAR(255) NOT NULL,
    INDITAS								DATETIME NOT NULL,
	BEFEJEZES							DATETIME,
	EREDMENY							LONGTEXT
  )
/

INSERT INTO IDOZITETT_FUTTATAS_TMP (ID, AZONOSITO, INDITAS, BEFEJEZES, EREDMENY) SELECT ID, AZONOSITO, STR_TO_DATE(CONCAT_WS(',', INDITAS_MASODPERC, INDITAS_PERC, INDITAS_ORA, INDITAS_NAP, INDITAS_HO, INDITAS_EV), '%s,%i,%H,%e,%m,%Y'), STR_TO_DATE(CONCAT_WS(',', BEFEJEZES_MASODPERC, BEFEJEZES_PERC, BEFEJEZES_ORA, BEFEJEZES_NAP, BEFEJEZES_HO, BEFEJEZES_EV), '%s,%i,%H,%e,%m,%Y'), EREDMENY FROM IDOZITETT_FUTTATAS
/

DROP TABLE IDOZITETT_FUTTATAS
/

CREATE TABLE IDOZITETT_FUTTATAS
  (
	ID									INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	AZONOSITO							VARCHAR(255) NOT NULL,
    INDITAS								DATETIME NOT NULL,
	BEFEJEZES							DATETIME,
	EREDMENY							LONGTEXT
  )
/

INSERT INTO IDOZITETT_FUTTATAS (ID, AZONOSITO, INDITAS, BEFEJEZES, EREDMENY) SELECT ID, AZONOSITO, INDITAS, BEFEJEZES, EREDMENY FROM IDOZITETT_FUTTATAS_TMP
/

DROP TABLE IDOZITETT_FUTTATAS_TMP
/

ALTER TABLE IDOZITETT_FUTTATAS ADD UNIQUE(AZONOSITO, INDITAS)
/