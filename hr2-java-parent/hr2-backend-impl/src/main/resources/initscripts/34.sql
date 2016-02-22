CREATE TABLE EMAIL_TEMPLATE
  (
	ID									INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	TYPE								VARCHAR(60) NOT NULL,
	CONTENT								LONGTEXT NOT NULL,
	CONSTRAINT UC_EMAIL_TEMPLATE_TYPE UNIQUE(TYPE)
  )
/

INSERT INTO EMAIL_TEMPLATE (TYPE, CONTENT) VALUES (
'IDENTIFICATION_QUALIFICATION_EXPIRES',
'Kedves Kolléga!

#if (${isSzemelyiIgazolvanyLejart})
A személyi igazolványod ${szemelyiIgazolvanyLejarat}-kor le fog járni.
#end
#if (${isUtlevelSzamLejart})
Az útleveled ${utlevelSzamLejarat}-kor le fog járni.
#end
#if (${isJogositvanySzamLejart})
A jogosítványod ${jogositvanySzamLejarat}-kor le fog járni.
#end
#foreach ($kepzettseg in $kepzettsegek)
A(z) ${kepzettseg.megnevezes} képzettséged ${kepzettseg.ervenyessegVege}-kor le fog járni.
#end
Kérlek intézkedj az igazolvány aktualizálásáról.

Üdv,
HR osztály
#if (!${isSzemelyitorzsEmailFilledOut})
A személyitörzsnek NINCS email címe!
#end')
/

INSERT INTO EMAIL_TEMPLATE (TYPE, CONTENT) VALUES (
'EMPLOYMENT_RELATION_EXPIRES',
'#foreach ($szemelyitorzs in $szemelyitorzsek)
A(z) ${szemelyitorzs.teljesNev} dolgozó munkaviszonya ${szemelyitorzs.jogviszonyVege}-kor le fog járni.
#end')
/

INSERT INTO EMAIL_TEMPLATE (TYPE, CONTENT) VALUES (
'MEDICAL_EXAMINATION_EXPIRES',
'Kedves Kolléga!

Üzemorvosi vizsgálatod érvényessége ${kovetkezoOrvosiVizsgalatIdopontja}-n lejár. Időpont egyeztetésért keresd Doktor Bubót a 06201234567-os számon.
A vizsgálatra az Super Medical rendelőjében fog sor kerülni. Az időpont előtt 2 órával már ne egyél, viszont igyál sokat. A vizsgálat alatt sor kerül vércukor- és koleszterinszint mérésre, valamint lehetőséged van szemészeti ellenőrzést is kérni (ezt kérlek külön jelezd az időpont egyeztetésnél).
Fizikai dolgozóknál légzésfunkció vizsgálatot is végeznek.

A vizsgálat elvégzéséhez szükséged van egy "beutalóra", amit a HR kordinátortól tudsz elkérni.
Kérlek, hogy a kiállított alkalmassági igazolásodat juttasd el hozzánk.

Köszönettel,
HR kordinátor
#if (!${isSzemelyitorzsEmailFilledOut})
A személyitörzsnek NINCS email címe!
#end')
/

INSERT INTO EMAIL_TEMPLATE (TYPE, CONTENT) VALUES (
'PROBATIONARY_PERIOD_EXPIRES',
'#foreach ($szemelyitorzs in $szemelyitorzsek)
A(z) ${szemelyitorzs.teljesNev} dolgozó próbaideje ${szemelyitorzs.probaidoVege}-kor le fog járni.
#end')
/