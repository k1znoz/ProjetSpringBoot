# PRESENTATION.md

## Contexte

Ce projet est une application de gestion scolaire realisee dans le cadre d un sujet CDA. Le depot regroupe a la fois :

- un backend Spring Boot securise ;
- un frontend React/Vite ;
- une base PostgreSQL ;
- des livrables d analyse dans le dossier `Analyses/` ;
- une documentation technique (`README.md`, `API.md`) ;
- des tests backend.

L objectif est de fournir une application full-stack capable de couvrir les besoins de base d un environnement scolaire, avec une API REST securisee et une interface web protegee.

## Besoin auquel repond l application

L application repond au besoin de centraliser la gestion des donnees principales d un etablissement scolaire dans une interface unique.

Le projet permet de gerer :

- les eleves ;
- les classes ;
- les enseignants ;
- les responsables ;
- les matieres ;
- les notes ;
- les bulletins.

Au-dela du simple CRUD, le projet couvre aussi deux besoins complementaires visibles dans le code :

- la consultation et l upload de photos pour les eleves ;
- l export PDF d un bulletin.

Enfin, l acces a l application est protege par authentification JWT, ce qui repond a un besoin de securisation des donnees scolaires.

## Architecture generale

Le projet suit une architecture separee frontend/backend.

### Backend

Le backend se trouve dans `backend/` et repose sur Spring Boot.

Organisation principale observee dans le code :

- `controller/` : expose les endpoints REST ;
- `service/` : porte la logique applicative ;
- `repository/` : acces aux donnees avec Spring Data JPA ;
- `entity/` : modeles persistants ;
- `dto/` : objets d echange utilises par l API ;
- `mapper/` : conversion entre entites et DTO ;
- `security/` : JWT, filtre d authentification et user details service ;
- `exception/` : gestion centralisee des erreurs.

### Frontend

Le frontend se trouve dans `frontend/` et repose sur React avec Vite.

Organisation principale :

- `pages/` : pages CRUD par domaine ;
- `components/` : formulaires, tableaux et layout ;
- `services/` : appels HTTP via Axios ;
- `routes/` : routage React Router avec protection des pages ;
- `context/` : gestion de l authentification cote frontend.

### Base de donnees

La base PostgreSQL est lancee via `docker-compose.yml`.

Le backend s y connecte grace a `application.yml` avec JPA/Hibernate. La configuration montre un `ddl-auto: update`, ce qui permet d adapter le schema au demarrage pendant le developpement.

## Choix techniques

## Spring Boot

Spring Boot a ete choisi pour construire rapidement une API REST structuree, avec une separation claire entre controllers, services, repositories et entites.

Ce choix apporte dans le projet :

- la creation rapide d endpoints CRUD ;
- la gestion native des validations ;
- l integration simple de Spring Security ;
- l integration de JPA et PostgreSQL ;
- l ecosysteme de test et la documentation OpenAPI.

## Spring Data JPA

JPA est utilise pour la persistance des entites metier.

Cela permet :

- de manipuler les donnees via des repositories ;
- de limiter le SQL manuel ;
- de garder une architecture classique et lisible pour un projet CDA.

## PostgreSQL

PostgreSQL est le SGBD utilise par le projet.

C est coherent avec un contexte de gestion scolaire car :

- il gere correctement les relations entre entites ;
- il est adapte a une application transactionnelle classique ;
- il s integre facilement avec Spring Boot et Docker.

## JWT

Le projet utilise JSON Web Token pour proteger l API.

Dans le code, on observe :

- un endpoint public `POST /auth/login` ;
- un `JwtService` qui genere et verifie le token ;
- un `JwtAuthenticationFilter` qui lit le header `Authorization` ;
- une configuration Spring Security qui autorise `/auth/**` et protege tous les endpoints `/api/**`.

Ce choix est adapte a une architecture frontend React + backend API REST stateless.

## React + Vite

Le frontend utilise React et Vite.

Ce choix permet :

- une interface dynamique et modulaire ;
- un developpement rapide ;
- un routage cote client ;
- un build simple ;
- une separation claire entre pages, composants et services.

## Axios

Axios est utilise pour centraliser les appels HTTP.

Le projet s en sert notamment pour :

- appliquer automatiquement le token JWT via un interceptor ;
- appeler l ensemble des endpoints CRUD ;
- gerer aussi les reponses binaires pour les PDF et les images.

## TailwindCSS

Le frontend utilise TailwindCSS pour la mise en forme.

Le projet reste sobre visuellement, mais ce choix permet de :

- construire rapidement les interfaces ;
- garder des composants simples ;
- adapter ponctuellement certains elements comme le bouton PDF ou les apercus photo.

## Swagger / OpenAPI

Le backend expose Swagger UI et OpenAPI.

Cela sert a :

- tester rapidement les endpoints ;
- verifier les routes ;
- documenter le contrat de l API.

## Demonstration conseillee etape par etape

Pour une soutenance de 10 a 15 minutes, la demonstration doit aller du general au concret.

### 1. Introduire le besoin et le perimetre

Montrer rapidement que l application couvre les briques principales de gestion scolaire :

- eleves ;
- classes ;
- enseignants ;
- responsables ;
- matieres ;
- notes ;
- bulletins.

### 2. Montrer la structure du depot

Presenter rapidement :

- `backend/` ;
- `frontend/` ;
- `Analyses/` ;
- `docker-compose.yml`.

Cela permet de montrer que le projet ne se limite pas au code, mais inclut aussi les livrables d analyse attendus.

### 3. Montrer l authentification

Demonstration conseillee :

- ouvrir la page de connexion ;
- se connecter avec `DOE / Passw0rd!` ;
- expliquer que le frontend stocke le token et que les routes sont protegees ;
- rappeler que tous les endpoints `/api/**` exigent un JWT valide.

### 4. Montrer un CRUD simple

Choisir un domaine simple pour montrer la logique generale, par exemple :

- les classes ;
- ou les matieres.

Montrer :

- affichage de la liste ;
- ajout ;
- modification ;
- suppression.

L idee est de presenter le fonctionnement standard de l application.

### 5. Mettre en avant la gestion des eleves

Montrer ensuite la page Eleves, car elle illustre mieux la richesse du projet.

A faire pendant la demo :

- afficher la liste ;
- montrer la miniature photo si elle existe ;
- montrer le placeholder si aucune photo n existe ;
- ouvrir un eleve en modification ;
- montrer l apercu photo dans le formulaire.

C est une bonne partie de demonstration car elle montre :

- le CRUD ;
- la securisation ;
- le chargement d image via endpoint dedie ;
- l integration frontend/backend.

### 6. Montrer les notes et bulletins

Ensuite, presenter rapidement :

- la gestion des notes ;
- la gestion des bulletins ;
- le lien entre eleve, note, matiere et bulletin.

### 7. Montrer l export PDF

Sur la page des bulletins :

- utiliser le bouton de telechargement PDF ;
- expliquer que le backend genere le document et renvoie un flux `application/pdf`.

C est un bon point fort pour conclure la partie demonstration fonctionnelle.

### 8. Montrer Swagger

Finir par Swagger UI pour montrer :

- que l API est bien exposee ;
- que le backend est documente ;
- que les endpoints peuvent etre testes de maniere independante du frontend.

## Difficultes rencontrees

Plusieurs difficultes reelles ont ete mises en evidence pendant le travail sur le projet.

### Securisation JWT et expiration du token

Le fonctionnement stateless apporte de la robustesse, mais impose une bonne gestion du token cote frontend.

On a notamment constate dans les logs :

- des erreurs de token expire ;
- la necessite de repartir d une session authentifiee valide pour les tests navigateur.

### Cohabitation frontend / backend / CORS

Le projet repose sur deux serveurs differents en developpement :

- Vite cote frontend ;
- Spring Boot cote backend.

Cela rend les tests sensibles a :

- la disponibilite du backend ;
- les ports utilises ;
- la gestion correcte des appels API authentifies.

### Chargement des photos eleves

La fonctionnalite photo a demande une attention particuliere parce qu elle combine :

- upload multipart ;
- stockage de chemin en base ;
- restitution binaire via endpoint dedie ;
- affichage frontend sans exposer le chemin local.

Le point sensible etait de s assurer que le frontend utilise bien `GET /api/eleves/{id}/photo` au lieu d afficher directement `photoPath`.

### Export PDF

L export PDF a egalement demande une verification specifique :

- generation du contenu binaire ;
- bon type MIME ;
- telechargement correct cote frontend.

## Solutions mises en oeuvre

## Pour l authentification

La solution mise en oeuvre repose sur :

- un endpoint `/auth/login` ;
- la generation d un JWT par `JwtService` ;
- l injection automatique du header `Authorization` grace a Axios ;
- des routes frontend protegees avec `ProtectedRoute`.

## Pour la gestion des photos eleves

La solution retenue est la suivante :

- le backend stocke un chemin logique en base (`photoPath`) ;
- le frontend n affiche jamais ce chemin ;
- le frontend recupere l image via `GET /api/eleves/{id}/photo` ;
- si aucune photo n existe, un placeholder texte est affiche ;
- l apercu est visible a la fois dans la liste et dans le formulaire d edition.

## Pour le PDF

La solution retenue :

- un endpoint dedie `GET /api/bulletins/{id}/pdf` ;
- une generation backend ;
- une recuperation frontend sous forme de blob ;
- un telechargement force cote navigateur.

## Pour la qualite du projet

Le projet a ete valide a plusieurs reprises avec :

- compilation Maven ;
- build frontend ;
- lint frontend ;
- tests backend ;
- verification navigateur sur les flux critiques.

## Ameliorations possibles

Ces pistes sont coherentes avec le projet reel, mais ne sont pas implementees dans l etat actuel.

### Gestion plus fine des roles

Le JWT existe deja, mais une evolution naturelle serait de :

- restreindre certains CRUD selon le role ;
- afficher des ecrans ou actions differents selon `ADMIN`, `ENSEIGNANT` ou `RESPONSABLE`.

### Amelioration de la gestion des erreurs cote frontend

Aujourd hui, les messages utilisateur existent deja, mais ils pourraient etre enrichis avec :

- des messages plus precis selon le cas ;
- une gestion plus uniforme des erreurs HTTP ;
- une meilleure distinction entre erreur reseau, validation et conflit de donnees.

### Recherche, tri et pagination

Les listes actuelles sont fonctionnelles, mais une evolution logique serait d ajouter :

- un moteur de recherche ;
- des tris par colonnes ;
- une pagination cote API ou frontend.

### Gestion de fichiers plus poussee

Pour les photos, on pourrait envisager :

- suppression automatique de l ancienne image physique lors d un remplacement ;
- contraintes plus strictes sur la taille et le type de fichier ;
- stockage externe si le projet change d echelle.

### Renforcement de la couverture de tests

Le backend contient deja des tests, mais une suite plus complete pourrait ajouter :

- plus de cas d integration ;
- des tests de securite ;
- des tests end-to-end frontend/backend automatises.

### Industrialisation du deploiement

Le projet fournit deja Docker Compose pour PostgreSQL. Une suite logique serait :

- conteneuriser aussi le backend et le frontend ;
- preparer un pipeline CI/CD ;
- separer davantage la configuration dev, recette et production.

## Conclusion de soutenance

Pour conclure oralement, l angle le plus pertinent est le suivant :

- le projet repond a un besoin clair de gestion scolaire ;
- il s appuie sur une architecture full-stack classique et professionnelle ;
- il couvre les principaux objets metier attendus ;
- il integre de vrais sujets transverses : securite JWT, persistance relationnelle, documentation API, upload photo et export PDF ;
- il reste evolutif pour de futures ameliorations.

En 10 a 15 minutes, la soutenance peut donc montrer a la fois :

- la comprehension du besoin ;
- la maitrise de l architecture ;
- la demonstration des fonctionnalites principales ;
- la capacite a expliquer les choix techniques et les limites actuelles du projet.
