# Proiect_PAO
Bibliotecă online în Java

**Echipă** : 
Micu Diana-Roberta & Popescu Vasile-Alin

**Nume**: Cărturești

# Etapa 1

 **Tema proiectului**: implementarea unui sistem de gestiune al cărților dintr-o bibliotecă și vizualizarea datelor despre acestea 

* Class Carte: Avem o clasă în care am pus ID, titlul cărții, autorii, categoria din care face parte, data publicării, editura și dacă este împrumutată sau nu.

* Class Persoană: Avem o clasă părinte în care avem atributele: id, nume, prenume, data nașterii și gen.

* Class Autor: Este o clasă copil care moștenește clasa părinte cu atributele specificate mai sus și în plus avem o valoare pentru numărul de cărți scrise.

* Class Cititor: Clasă copil care moștenește clasa părinte și avem în plus numărul de cărți citite, adresa, și ce tip de abonament are.

* Class Categorie: Este o clasă în care avem id-ul și numele categoriei.

* Class Editură: Este o clasă în care avem id-ul și denumirea ei.

* Class Abonament: Este o clasă în care avem un id, tipul abonamentului, data când s-a creat și statusul.

* Class Adresă: Este o clasă în care avem id-ul și adresa.

Pe lângă acestea, programul mai conține:
* Controler: Cuprinde toate funcțiile utile pentru realizarea comenzilor din meniu
* ConsoleApplication: Implementează un meniu și rulează programul principal.

![diagrama](https://user-images.githubusercontent.com/93095672/227785146-81729979-385e-42b1-a295-940d93913aec.png)



