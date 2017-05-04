# Ajanhallintajärjestelmä

Toteutan ajanhallintajärjestelmän, jonka avulla voidaan mitata ajankäyttöä reaaliajassa eri projekteille. Projekteilla voi olla aliprojekteja tai tägejä, joiden ajankäyttöä myös mitataan. Ohjelmassa voi tarkastella raportteja projektejen ajankäytöstä.

Ohjelmasta voi ottaa ulos json-tiedoston ajankäytöstä jokaisesta projektista tai tagista.

Lisäksi toteutan ominaisuuden jossa käyttäjä voi halutessaan määrittää aiheestaan saaman tuntipalkan, joka saadaan aina kun tunti on mennyt.

Ajanhallintajärjestelmän tulee tukea Windows, Ubuntu ja Mac OS käyttöjärjestelmiä.

## Toiminnot:
Kaikilla käyttäjillä on samat toiminnot.
Kaikkien käyttäjien toiminnot:

 - Aiheen lisääminen
 - Tagin lisääminen aiheelle
 - Ajankäytön mittaamisen aloittaminen ja lopettaminen
 - Tuntipalkan määrittäminen
 - Raportin ulosotto

## Rakennekuvaus

Ohjelman tärkeimmät luokat on Calendar, Tracker ja SystemTimeSupplier, jotka hoitavat kaiken ajanlaskentaan tarvittavan ohjelmallisuuden. Collector luokka yhdistää tagit ja projektit UI:n käytettäväksi, ja pitää huolen että ajanlasku tapahtuu oikein. Report tarjoaa reportin projektista tai tagista. UI:n puoli on epä epäselvälvempi, mutta yksinkertaisuudessaan on olemassa kolme näkymää. Report näkymä tarjoaa kaiken reporteista. Project näkymä tarjoaa kaiken projekteista ja tag näkymä tarjoaa kaiken tageista. Tätä kaikkea ohjaa sidebar luokka, joka pitää huolen sivussa olevasta valikosta.

## Luokkakaavio
![Luokkakaavio](https://github.com/rovaniemi/vulpy/blob/master/dokumentaatio/luokkakaavio-27.04.2017.png)

## Sekvenssikaaviot

#### Ajankäytön mittaamisen aloittaminen ja lopettaminen

![Ajankäytön mittaamisen aloittaminen ja lopettaminen](https://github.com/rovaniemi/vulpy/blob/master/dokumentaatio/ajankaytto.png)

#### Aiheen lisääminen

![Aiheen lisääminen](https://github.com/rovaniemi/vulpy/blob/master/dokumentaatio/Aiheenlisaaminen.png)
