CREATE TABLE HAVI_JELENLETI_IV_ALLAPOT2
  (
    ID									INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    HONAP								DATE NOT NULL,
    SZERKESZTHETO						BOOLEAN NOT NULL
  )
/

ALTER TABLE HAVI_JELENLETI_IV_ALLAPOT2 ADD UNIQUE INDEX(HONAP)
/

INSERT INTO HAVI_JELENLETI_IV_ALLAPOT2 (ID, HONAP, SZERKESZTHETO) SELECT ID, STR_TO_DATE(CONCAT_WS(',', '1', HO, EV), '%e,%m,%Y'), SZERKESZTHETO FROM HAVI_JELENLETI_IV_ALLAPOT
/

DROP TABLE HAVI_JELENLETI_IV_ALLAPOT CASCADE
/

CREATE TABLE HAVI_JELENLETI_IV_ALLAPOT
  (
    ID									INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    HONAP								DATE NOT NULL,
    SZERKESZTHETO						BOOLEAN NOT NULL
  )
/

ALTER TABLE HAVI_JELENLETI_IV_ALLAPOT ADD UNIQUE INDEX(HONAP)
/

INSERT INTO HAVI_JELENLETI_IV_ALLAPOT (ID, HONAP, SZERKESZTHETO) SELECT ID, HONAP, SZERKESZTHETO FROM HAVI_JELENLETI_IV_ALLAPOT2
/

DROP TABLE HAVI_JELENLETI_IV_ALLAPOT2 CASCADE
/