CREATE TABLE PDF_SABLON
  (
	TIPUS								VARCHAR(30) PRIMARY KEY NOT NULL,
	TARTALOM							LONGTEXT NOT NULL
  )
/

INSERT INTO PDF_SABLON (TIPUS, TARTALOM) VALUES (
'JELENLETI_IV',
'<?xml version="1.0" encoding="UTF-8"?>  
<html>  
<head>  
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<style type="text/css">
<!--
body {
	font-family: Arial;
	font-size:12px;
}
-->
</style>
</head>   
<body>  
<center><h1>Jelenléti ív</h1></center>  
<div>
	<strong>Szervezeti egység:</strong> ${szervezetiEgyseg}
	<br />
	<br />
	<strong>Tsz:</strong> ${tsz}
	<br />
	<br />
	<strong>Név:</strong> ${teljesNev} <strong>Hónap:</strong> ${honap}</div>
	<br />
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">  
		<tr>
		  <td width="60" rowspan="2"><div align="center"><strong>Nap</strong></div></td>
			<td colspan="2"><div align="center"><strong>Munkavégzés</strong></div></td>
		  <td width="60" rowspan="2"><div align="center"><strong>Ledolgozott óra </strong></div></td>
			<td colspan="3"><div align="center"><strong>Pótlékok</strong></div></td>
			<td rowspan="2"><div align="center"><strong>Aláírás</strong></div></td>
		</tr>
		<tr>
		  <td width="60"><div align="center"><strong>-tól</strong></div></td>
		  <td width="60"><div align="center"><strong>-ig</strong></div></td>
		  <td width="60"><div align="center"><strong>30%</strong></div></td>
		  <td width="60"><div align="center"><strong>Tó 50%</strong></div></td>
		  <td width="60"><div align="center"><strong>Tó 100%</strong></div></td>
		</tr>
		${tablazatTorzsHtml}
		<tr>
			<td colspan="3"><strong>Összesen:</strong></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>  
	<br />
	<table width="100%">
		<tr>
			<td width="50%">
				<table width="240" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">
					<tr>
					  <td width="120"><div align="center"><strong>Összesítés</strong></div></td>
						<td width="60"><div align="center"><strong>Óra</strong></div></td>
					</tr>
					<tr>
						<td><strong>Szabadság</strong></td>
						<td></td>
					</tr>
					<tr>
						<td><strong>Betegszabadság</strong></td>
						<td></td>
					</tr>
					<tr>
						<td><strong>Igazolatlan</strong></td>
						<td></td>
					</tr>
					<tr>
						<td><strong>Egyéb nem fiz.táv.</strong></td>
						<td></td>
					</tr>
					<tr>
						<td><strong>Műszak pótlék 30%</strong></td>
						<td></td>
					</tr>
					<tr>
						<td><strong>Túlóra 50%</strong></td>
						<td></td>
					</tr>
					<tr>
						<td><strong>Túlóra 100%</strong></td>
						<td></td>
					</tr>
				</table>
			</td>
			<td width="50%">
				<table width="360" align="center">
		            <tr>
						<td><div align="center">...........................................................</div></td>
		            </tr>
		            <tr>
					  <td width="360"><div align="center">Ellenőrizte</div></td>
		            </tr>
					<tr>
					  <td height="40"><div align="center"></div></td>
					</tr>
					<tr>
						<td><div align="center">...........................................................</div></td>
					</tr>
					<tr>
						<td><div align="center">Dolgozó aláírása</div></td>
					</tr>
				</table>
				<div align="center"></div>
			</td>
		</tr>
	</table>
</body>  
</html>')
/