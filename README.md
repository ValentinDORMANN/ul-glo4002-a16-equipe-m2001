# EasyFlyCheckin

**Table des matières**

- [1 Equipe](#1-equipe)
- [2 Liste des users'story](#2-liste-des-users'story)
- [3 Résumé](#3-résumé)
- [4 Notes](#4-notes)

## 1 Equipe

|nom     | prenom    | pseudo          | mail|
| ------ | ----------|---------------- |-----|
 AGBOTON | Charly | chagb	|	charly.agboton.1@ulaval.ca|
 DORMANN | Valentin | ValentinDORMANN | valentin.dormann@gmail.com|
 KHALLOUD | Siham	| sikha3 | siham.khalloud.1@ulaval.ca|
 TCHOUTOUO | Hypolyte | hypson |	thypson2012@hotmail.ca|
 VOILLOT | Virgil | lloyddsure | voillot.virgil@gmail.com|

## 2 Liste des users'story

|numéro  | nom							 | status|
| ------ | ---------------- | ----------------------------- |
 Story1 |  Heartbeat | 					 	   terminé|
 Story2 | Réservation: Événement - nouvelle réservation |  	   terminé|
 Story3 | Réservation: Obtenir l'information sur une réservation |  terminé|
 Story4 | Checkin: Enregistrement par un agent | 		   terminé|
 Story5 | Checkin: Enregistrement en ligne | 		   terminé|
 Story6 | Siège: Assignation du siège d'un passager (aléatoire) | 		   terminé|
 Story7 | Bagage enregistré: Classe économique | 		   terminé|
 Story8 | Bagages: Obtenir la liste par passager | 		   terminé|
 Story9 | Siège: Assigner le siège le moins cher | 		   terminé|
 Story10 | Siège: Assigner le siège en fonction du dégagement | 		   terminé|

## 3 Résumé

3.1 Comment utiliser l'application
```
mvn clean install
mvn exec:java -Dreservation.port=8888 -Dboarding.port=9999 app -pl
```
3.2 Comment éxécuter les tests
```
mvn test
```
## 4 Notes

4.1 Violation de principes

Nous avons introduit les DTO dans le constructeur des objets dans le module de Réservation. Ce module étant en situation de CRUD, il n'y a pas de domaine d'affaire, ainsi on s'est autorisé de le faire.

4.2 Décisions à communiquer

-Dans la story 6, lorsqu'on assigne un siège à un passager, on intègre pas le prix du siège au passager.
-Dans la story 7, les bagages peuvent être enregistré par n'importe quel passager. Les modifications seront ajoutées ultérieurement (pour la prochaine remise).

4.3 Demandes spéciales autorisées par le corps enseignant
