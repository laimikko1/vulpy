## Testidokumentti

Testien rivikattavuus on noin 90%, ja mutaatiokattavuus noin 70%. Automaattinen testaaminen keskittyy lähinnä ajankäytönmittaamiseen.

#### Asioita mitä en testannut automaattisesti, mutta testasin käsin.

Yksi tracker-olio pystyy mittaamaan aikaa vain sen verran kuin vuorokautta on jäljellä.
Ohjelmassani oli bugi, jossa ajanmittaaminen loppui kello 00.00. Korjasin bugin hax-ratkaisulla, lisäämällä
toiseen thrediin koodin joka tarkastaa kokoajan onko vuorokausi vaihtunut. Tämä ei ole järkevä tapa, mutta ainoa
tapa minkä ehdin tehdä palautukseen.

#### Bugeja

Löysin bugeja oikeastaan vain UI:n puolelta. En validoi kaikkia kenttiä tarpeeksi tarkkaan. Tämä olisi ollut nopea korjata, ja aionkin tehdä sen siinä vaiheessa kun vaihdan frontin pois java maailmasta.
