# Alkalmazásfejlesztés beadandó

## Bevezetés

Célunk egy olyan webes applikáció fejlesztése, amely segíti az ajándékozás folyamatát, legyen szó születésnapról, karácsonyról vagy bármilyen más egyéb ünnepről. A programmal, regisztrálás és belépés után, kívánság listákat készíthetünk, amelyet a barátaink (barátnak felvett felhasználók) láthatnak, illetve módosításokat hajthatnak végre rajta, anélkül, hogy ezt a lista eredeti tulajdonosa láthatná.


## Funkcionális követelmények  
- az app használatához regisztráció/belépés szükséges
- kívánság listákat készíthetünk
  - elnevezhetjük a listát
  - feltölthetjük elemekkel
  - a listaelemek rendelkeznek: névvel, linkkel, árral
- más felhasználók felvétele barátként
  - a barátok láthatják egymás listáit
  - a barátok hozzáadhatnak az eredeti tulajdonos listájához elemeket, de ezt a tulaj nem láthatja
  - megjelölhetjük az adott ajándékot, hogy mi azt meg fogjuk vásárolni
  - a barátok számára látható, hogy ki vásárolta meg az adott elemet
  - a barátok kommentelhetnek a lista elemekhez
- felhasználók közötti keresés
- barát kérések küldése olyan felhasználók számára, akik még nem barátaink és még nem küldtünk nekik kérést


## Nem funkcionális követelmények
- A weboldal elérhető tetszőleges böngészőből
- Szerveroldal
  - Java Spring boot használata (Lombok, h2, JPA, HTML, CSS)
  - MVC modell
  - REST API
  - authorizált végpontok
  - A jelszavak mentése hasheléssel történik
- Kliensoldal
  - Angular framework használata
  - Typescript használata
  - a szerverrel AJAX kérésekkel történik a kommunkáció
  - csak hitelesítés után elérhető funckiók bevezetése (autentikáció)

## Adatbázis terv
![alt text](pictures/databasev2.png "Adatbázist model")

### Kapcsolatok:

- egy - sok kapcsolatok
  - USER - PRESENT
  - USER- WISHLIST
  - WISHLIST - PRESENT
  - USER - COMMENT
  - PRESENT - COMMENT
- sok - sok kapcsolat
  - USER - USER (FRIENDS kapcsolótáblával) 
  
##  Szerepkörök
- User: Saját listákat hozhat létre, kereshet a felhasználók között és barátok vehet fel. Továbbá hozzáadhat/kihúzhat elemeket a barátok listáiról és kommentelhet is hozzájuk.
- Admin: Belenyúlhat az adatbázisba, módosíthatja az ajándékokat, törölhet felhasználókat, listákat, ajándékokat, kommenteket stb.

## Fejlesztői környezet
- NetBeans/IntelliJ IDEA
- Maven
- Git verziókezelő használata
- GitHub repository klónozása lokális használatra
- A pom.xml-ben megtalálhatóak a Spring-Bootos függőségek
- Projekt elérése böngészőből: localhost:8080


## Könyvtárszerkezet
![alt text](pictures/konyvtarszerk.png "Könyvtár szerkezet")

## Végpontok
![alt text](pictures/mappings.png "Végpontok")

### Use case:
- User szemszögből:
![alt text](pictures/user_usecase.png "User use case")

## Készítették:

 * [Negrut Ákos](http://github.com/Zentrom)
 * [Jakab Gergely](http://github.com/gjakab/)