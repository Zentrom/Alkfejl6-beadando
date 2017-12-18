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
  - USER - COMMENTS
  - PRESENT - COMMENTS
  - USER - FRIENDREQUEST
- sok - sok kapcsolat
  - USER - USER (FRIENDS kapcsolótáblával) 
  
##  Szerepkörök
- User: Saját listákat hozhat létre, kereshet a felhasználók között és barátok vehet fel. Továbbá hozzáadhat/kihúzhat elemeket a barátok listáiról és kommentelhet is hozzájuk.
- Admin: Belenyúlhat az adatbázisba, módosíthatja az ajándékokat, törölhet felhasználókat, listákat, ajándékokat, kommenteket stb.

## Fejlesztői környezet
- NetBeans/IntelliJ IDEA
- Visual Studio Code
- Postman
- Maven
- Git verziókezelő használata
- GitHub repository klónozása lokális használatra
- A pom.xml-ben megtalálhatóak a Spring-Bootos függőségek
- Adatbázis elérése böngészőből: localhost:8080/h2
- Applikáció elérése böngészőből: localhost:4200


## Könyvtárszerkezet
![alt text](pictures/konyvtarszerk.png "Könyvtár szerkezet")

- config: Itt találhatóak meg az autentikációhoz és a jelszótitkosításhoz szükséges osztályok.
- controller: Végpontok definiálása és felosztása logikailag elkülöníthetó osztályokra, itt állítjuk be a szükséges jogosultságokat is.
- model: Az applikációban szükséges entitások itt találhatóak.
- model/dto: Az entitásokat helyettesítő Data Transfer Objectek, hogy ne küldjünk a kliens felé kényes (jelszó) illetve felesleges információkat.
- repository: Az entitásokhoz kapcsolódó táblákat ezekkel az osztályokkal érhetjük el.
- service: A táblák, entitásokhoz kapcsoló üzleti logikát itt határozzuk meg.
- service/annotations: A jogosultságok meghatározásához szükséges osztály itt található.
- service/exceptions: Általunk készített kivételek, jelen esetben csak a UserNotValidException.
- egyéb: ResourceConstants: A végpontokat meghatározó statikus, final stringek egy osztályba gyűjtve.

## Végpontok
![alt text](pictures/mappings.png "Végpontok")

## Spring boot dependecyk-k
- spring-boot-starter-data-jpa
- spring-boot-devtools
- h2, lombok
- spring-boot-starter-test
- spring-boot-starter-actuator
- spring-security-crypto
- spring-boot-test-autoconfigure

### Use case:
- User szemszögből:
![alt text](pictures/user_usecase.png "User use case")

# Felhasználói dokumentáció

## Webes felhasználói felület

## Index
A be nem jelentkezett/regisztrált felhasználót kezdetben egy index/beköszöntő oldal fogadja, ahol megtalálható néhány fontos információt az oldal használhatával kapcsolat.
Röviden leírjuk itt még a bejelentkezés/regisztrálás utáni funkciókat. Ekkor csak az Index, Login, Register menüpontok érhetőek el.

## Register
Itt egy az adatbázisban még nem szereplő felhasználónevet, emailt és néhány egyéb nem feltételenül egyedi adatot (jelszó, keresztnév, vezetéknév) megadva regisztrálhatunk az applikációba. Regisztráció után a rendszer beléptet és átirányít minket az Index oldalra, ami már a nevünk alapján köszönt minket.
Ekkor már több menüpont is elérhető, amiket később részletünk: My Wishlists, My Friends, Add Friends, Incoming requests, Logout.

## Login 
Ha már korábban regisztáltunk akkor ezen az oldalon lehetőségünk van a felhasználónevünk/jelszavunk megadásával belépni az oldalra. Hasonlóan mint regisztrálásnál itt is több menüpont fog minket fogadni.


## My Wishlists
Az oldalon létrehozhatunk kívánságlistákat azok nevének a megadásával. A név nem lehet üres, illetve nem állhat csak whitespacekből. Továbbá itt böngészhetünk a már meglévő listáink között is, amiket módosíthatunk egy felugró dialog segítségével, törölhetjük őket (megerősítés után) illetve megnyithatjuk a listákat, hogy megtekintsük a benne felvett ajándékokat.
Ha megnyitottuk a listát, akkor a listában szereplő ajándékainkat managelő oldalra kerülünk. Itt hozzáadhatunk új ajándékot, amit szeretnénk, ehhez szükséges a név (nem üres, nem csak whitespace), az ár (nem negatív szám), link (opcionális) megadása. A már meglévő ajándékok adatait módosíthatjuk, törölhetjük az ajándékokat, illetve megnyithatjuk a hozzájuk tartozó linket ha van.

## My Friends
Itt listázhatjuk a már korábban felvett barátainkat (más userek) vezetéknév/keresztnév alapján illetve akár meg is jeleníthetjük az összeset egy gombnyomással. Lehetőség van a barátok törlésére és a listáik megnyitására. Ha megnyitottuk egy barát listáit, akkor ott kiválaszthatunk és megnyithatunk egy konkrét listát.
Ha ezt megtettük, akkor látható, hogy milyen termékeket szeretne kapni az adott felhasználó az adott eseményre. Hozzáadhatunk az adott listához anélkül, hogy a lista tulajdonosa azt láthatná, megjelölhetjük, hogy melyik ajándékot vásárolnánk meg neki (ezeket az egyéb barátok is látják, ha már megvettük akkor más nem veheti meg adott terméket), kommenteket fűzhetünk az adott ajándékhoz.
Módosítani/törölni csak a barátok által hozzáadott ajándékokat tudjuk.

## Add Friends
Itt kereshetünk olyan felhasználókat (vezetéknév/keresztnév alapján), akik még nem a barátaink és még nem is küldtünk nekik barát kérést.

## Incoming Requests
Listázhatjuk a bejövő barát kéréseinket, amiket akár szűrhetünk is vezetéknév/keresztnév alapján. A kéréseket elfogadhatjuk vagy elutasíthatjuk. Elfogadás után az adott user a barátaink közé fog tartozni és ha mi is küldtünk neki requestet akkor az törlődni fog
a rendszerből. Elutasításkor pedig az adott felhasználó nem lesz a barátunk de küldhetünk később megint nekünk requestet.

## Logout
A felhasználó ezzel a menüponttal kiléphet az oldalról.

## Webes admin felület

## Index
A fogadó oldal, röviden leírja az admin funkciókat.

## Users
Az adatbázisbeli felhasználók között kereshetünk vezetéknév/keresztnév alapján. A usereket törölhetjük illetve megnyithatjuk a listáikat. Ha megnyitottuk a listáikat, akkor lehetőségünk van azok módosításárára/törlésére úgy mintha mi magunk lennénk a lista tulajdonosai.
Ezeken felül bele is léphetünk a listába. Ekkor láthatjuk az itt szereplő ajándékokat, törölhetjük/módosíthatjuk azokat. Kitörölhetjük azt is, hogy ki vásárolta meg az adott ajándékot és láthatjuk, hogy a lista tulaja vagy egy barát adta-e hozzá a terméket a listához.
Megtekinthetjük még a kommenteket is ahol ugyancsak lehetőségünk van törlésre.

## Logout
Az admin ezzel a menüponttal kiléphet az oldalról.








## Készítették:

 * [Negrut Ákos](http://github.com/Zentrom)
 * [Jakab Gergely](http://github.com/gjakab/)