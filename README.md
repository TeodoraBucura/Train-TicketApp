# Train-TicketApp
# Proiect Java
Autor: Bucura Teodora

Facultatea de Inginerie Electrica si Stiinta Calculatoarelor

## Aplicatie CFR (cumparare bilete)
 CFR APP aplicatia care ruleaza in consola, interactioneaza cu utilizatorul
si faciliteaza cumpararea de bilete la un operator feroviar.

---
### Interactionare - Instructiuni de utilizare

    Bine ați venit la aplicația CFR de cumpărare a biletelor!

Se ruleaza codul. Acesta este primul mesaj care apare in consola la
rularea programului. Apoi se cere introducerea de la tastatura a locului 
de plecare, a destinatiei posibile din lista afisata in ordine alfabetica,
a trenului, a vagonului si a locului liber afisat in diagrama.

Dupa cumpararea biletului/biletelor se poate iesi din program tastand "n"
atunci cand apare mesajul " Mai doriti alt bilet? [y/n] ". In fisierul 
"ticket.txt" va aparea ce ati achizitionat.

exemplu:

    Introduceți locația de plecare: Sinaia
    Destinații disponibile:
    1. Azuga
    2. Brasov
    3. Bucuresti
    4. Busteni
    5. Campina
    6. Comarnic
    7. Ploiesti
    8. Predeal
    Introduceți numărul destinației: 2
    Trenuri disponibile:
    1. IRN472: 17:59 -> 18:58
    2. IR1630: 10:25 -> 11:24
      Introduceți numărul trenului: 2
      Introduceți numărul vagonului: 1

    Schema vagonului 1:
    /============================\
    |  1 |  2 |  3 |  4 |
    |  5 |  6 |  7 |  8 |
    |  9 | 10 | 11 | 12 |
    | 13 | 14 | 15 | 16 |
    | 17 | 18 | 19 | 20 |
    | 21 | 22 | 23 | 24 |
    | 25 | 26 | 27 | 28 |
    | 29 | 30 | 31 | 32 |
    | 33 | 34 | 35 | 36 |
    | 37 | 38 | 39 | 40 |
    \============================/
    Introduceți numărul locului: 1
    Biletul a fost generat cu succes! Verificați fișierul ticket.txt.
    Another bilet? [y/n]

---
### Functionare fisiere

Aplicatia se foloseste de 3 fisiere:

"routes.txt" - Contine pe randurile impare toate rutele existente
(un rand - o ruta, localitatiile de pe rand - locatiile in care opreste 
pe ruta respectiva), iar pe randurile pare distanta in minute dintre 
2 locatii.

"trains.txt" - Contine pe fiecare rand un tren avand ca valori citite: numarul
rutei, ID-ul trenului, numarul de vagoane, locul plecarii, ora plecarii.

"ticket.txt" - Se creeaza la finalul executiei codului, continand biletul / biletele
emise.

exemplu:

    BILET CFR
    Tren: IR1630
    Ruta: Sinaia -> Brasov
    Ora Plecare: 10:25 -> 11:24
    Durata: 59 minute
    Vagon: 1 Loc: 1

---
### Logica codului

Aplicatia se foloseste de clasele:

    "Main.java"
    "Route.java"
    "RouteManager.java"
    "Ticket.java"
    "Time.java"
    "Train.java"

In "Main.java" cream un scanner pentru a citi valori introduse 
de la tastatura si folosim un BufferedReader pentru a citi din fisiere.
Se introduce locatia de plecare apoi se citesc randurile impare din fisierul 
cu rute si se afiseaza in ordine alfabetica toate destinatiile gasite 
pe rutele in care se afla locatia aleasa de plecare. Apoi se alege destinatia,
codul verificand daca cifra de index aleasa este valida. Dupa ce avem locatia
de plecare si cea de sosire stim ruta pe care se va circula. Avand decat 4 rute
acestea sunt indexate de la 0 la 3.

Pentru afisarea trenurilor disponibile se 
citeste din fisierul "trains.txt" unde prima cifra de pe fiecare linie corespunde
rutei pe care circula acel tren. Se afiseaza doar trenurile de pe ruta care circula
in directia corespunzatoare, iar timpul de plecare este calculat cu cu ajutorul
valorii de plecare din capatul de linie la care se adauga toate valorile gasite
in fisierul cu rute pe randul par pana la locatia de plecare. Timpul de sosire este 
calculat prin adaugarea timpilor dintre locatii la timpul de plecare. Durata 
calatoriei se calculeaza in minute scazand ora de sosire din cea de plecare si 
transformand orele in minute. Clasa "Time.java" este dedicata acestor actiuni.

Se va alege vagonul si locul, iar apoi biletul va fi tiparit in fisier. Clasa 
"Ticket.java" se ocupa de acest lucru. Fisierul se golseste la fiecare rulare 
a codului. Codul este unul de tip "loop" deoarece poti cumpara mai multe bilete
efectuand aceeasi pasi pana la tastarea literei "n" pentru a opri rularea 
programului.

Clasele "Route.java" si "RouteManager.java" sunt esentiale rularii codului,
deoarece lucreaza atat cu locatiile de plecare si sosire, dar si cu timpii
de deplasare, folosindu-se de liste ordonate.
