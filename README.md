# EasyFlyCheckin

**Table des matières**

- [1 Equipe](#1-equipe)
- [2 Liste des users'story](#2-liste-des-users'story)
- [3 Résumé](#3-résumé)
- [4 Notes](#4-notes)

## 1 Equipe

**nom     | prenom    | pseudo          | mail**
**AGBOTON   Charly      chagb		charly.agboton.1@ulaval.ca**
**DORMANN   Valentin    ValentinDORMANN   valentin.dormann@gmail.com**
**KHALLOUD  Siham	      sikha3		siham.khalloud.1@ulaval.ca**
**TCHOUTOUO Hypolyte    hypson		thypson2012@hotmail.ca**
**VOILLOT   Virgil      lloyddsure	voillot.virgil@gmail.com**

## 2 Liste des users'story

**no    ¦ nom							 ¦ status**
**Story1:  Heartbeat : 					 	   terminé**
**Story2:  Réservation: Événement - nouvelle réservation :  	   terminé**
**Story3:  Réservation: Obtenir l'information sur une réservation :  en cours (encore en test)**
**Story4:  Checkin: Enregistrement par un agent : 		   en cours (encore en test)**

## 3 Résumé

3.1 Comment utiliser l'application
```
mvn clean install
mvn exex:java -Dreservation.port=8888 -Dboarding.port=9999 app -pl
```
3.2 Comment éxécuter les tests
```
mvn test
```
## 4 Notes

4.1 Violation de principes

Nous avons initialement utilisé une base de donnée XML ce qui a impliqué une perte de temps .

4.2 Décisions à communiquer


4.3 Demandes spéciales autorisées par le corps enseignant

Nous avons utilisé le format JSON pour les liaisons HTTP plutôt que les DTO.
Nous avons utilisé une architecture trois couches : Resource ( la couche APIRest), Domain (la couche qui contient les modèles et le service), Infrastructure (la couche qui contient toutes les données sous forme de HashMap et les fonctions qui leurs sont associées)
Une couche ne connait qu'une autre couche de façon unidirrectionelle, la Resource peut appeler le Service qui lui appele le Domain.
