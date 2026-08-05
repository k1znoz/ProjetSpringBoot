# Gestion Scolaire - Projet CDA

Application full-stack de gestion scolaire réalisée dans le cadre d'un projet CDA. Le dépôt regroupe une API Spring Boot sécurisée par JWT, un frontend React/Vite, une base PostgreSQL et les livrables d'analyse du projet dans le dossier [Analyses/](Analyses).

## Présentation du projet

L'application centralise les données principales d'un environnement scolaire dans une interface web protégée.

Le projet permet de gérer :

- les élèves ;
- les classes ;
- les enseignants ;
- les responsables ;
- les matières ;
- les notes ;
- les bulletins.

Des fonctionnalités complémentaires sont également présentes dans le code :

- authentification JWT ;
- upload et consultation de photos d'élèves ;
- export PDF de bulletin ;
- documentation Swagger / OpenAPI.

## Objectifs

Les objectifs couverts par le projet sont les suivants :

- proposer une base de gestion scolaire exploitable ;
- exposer une API REST sécurisée ;
- fournir une interface frontend protégée ;
- persister les données dans PostgreSQL ;
- livrer un projet CDA avec documentation et éléments d'analyse.

## Technologies utilisées

### Backend

- Java 17
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Security
- JWT avec `jjwt`
- Springdoc OpenAPI / Swagger UI

### Frontend

- React 19
- Vite 8
- React Router DOM
- Axios
- TailwindCSS
- ESLint

### Base de données et exécution

- PostgreSQL 16
- Docker Compose

## Architecture du projet

Le projet suit une séparation claire entre frontend, backend et base de données.

### Backend Spring Boot

Le backend se trouve dans [backend/](backend) et est organisé en couches classiques :

- `controller/` : endpoints REST ;
- `service/` : logique applicative ;
- `repository/` : accès aux données ;
- `entity/` : modèle JPA ;
- `dto/` : contrats d'échange ;
- `mapper/` : conversion entité / DTO ;
- `security/` : JWT, filtre et authentification ;
- `exception/` : gestion centralisée des erreurs.

### Frontend React/Vite

Le frontend se trouve dans [frontend/](frontend) et s'appuie sur :

- `pages/` : pages métier ;
- `components/` : formulaires, tableaux, layout ;
- `services/` : appels HTTP ;
- `routes/` : protection des routes et navigation ;
- `context/` : état d'authentification.

### Base de données PostgreSQL

La base PostgreSQL est définie dans [docker-compose.yml](docker-compose.yml). Le backend s'y connecte via [backend/src/main/resources/application.yml](backend/src/main/resources/application.yml).

## Arborescence principale

```text
ProjetSpringBoot/
├── Analyses/
├── backend/
│   ├── src/main/java/fr/cda/ecole/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── mapper/
│   │   ├── repository/
│   │   ├── security/
│   │   └── service/
│   └── src/main/resources/
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── context/
│   │   ├── pages/
│   │   ├── routes/
│   │   └── services/
├── API.md
├── PRESENTATION.md
├── README.md
└── docker-compose.yml
```

## Structure Backend / Frontend

### Backend

- API REST sous `/api/**`
- endpoint public d'authentification sous `/auth/login`
- documentation Swagger disponible au démarrage
- validation des DTO côté backend
- gestion des erreurs centralisée avec réponses JSON cohérentes

### Frontend

- page de connexion `/login`
- routes protégées pour les écrans métier
- appels API via Axios
- envoi automatique du JWT dans l'en-tête `Authorization`
- pages CRUD pour classes, élèves, enseignants, responsables, matières, notes et bulletins

## Prérequis

- Java 17
- Node.js 20 ou supérieur
- npm
- Docker Desktop si vous utilisez PostgreSQL via Docker Compose

## Installation

### 1. Préparer l'environnement

Copier [\.env.example](.env.example) vers `.env` à la racine du projet.

Variables utilisées dans le projet :

- `POSTGRES_DB`
- `POSTGRES_USER`
- `POSTGRES_PASSWORD`
- `POSTGRES_PORT`
- `SERVER_PORT`
- `APPLICATION_SECURITY_JWT_SECRET`
- `APPLICATION_SECURITY_JWT_EXPIRATION`

### 2. Installer le frontend

```powershell
cd .\frontend
npm install
```

### 3. Backend

Le projet utilise le Maven Wrapper déjà présent dans [backend/](backend).

Depuis la racine du projet :

```powershell
.\backend\mvnw.cmd -f .\backend\pom.xml clean compile
```

## Utilisation de Docker / PostgreSQL

Le projet fournit un service PostgreSQL dans [docker-compose.yml](docker-compose.yml).

Lancement :

```powershell
docker compose up -d
```

Arrêt :

```powershell
docker compose down
```

Informations observables dans le projet :

- image : `postgres:16-alpine`
- port exposé par défaut : `5433`
- volume Docker : `gestion_scolaire_postgres_data`

## Lancement du backend

Depuis la racine du projet :

```powershell
.\backend\mvnw.cmd -f .\backend\pom.xml spring-boot:run
```

Autres commandes utiles :

```powershell
.\backend\mvnw.cmd -f .\backend\pom.xml clean compile
.\backend\mvnw.cmd -f .\backend\pom.xml test
.\backend\mvnw.cmd -f .\backend\pom.xml clean package
java -jar .\backend\target\gestion-scolaire-api-0.0.1-SNAPSHOT.jar
```

Le backend démarre par défaut sur : `http://localhost:8080`

## Lancement du frontend

```powershell
cd .\frontend
npm run dev
```

Commandes utiles :

```powershell
npm run build
npm run lint
npm run preview
```

## Authentification

### Compte fonctionnel documenté dans le projet

Le projet documente l'utilisation du compte suivant pour tester l'authentification :

- username : `DOE`
- password : `Passw0rd!`

### Données présentes dans `data.sql`

Le fichier [backend/src/main/resources/data.sql](backend/src/main/resources/data.sql) initialise des données métier :

- 1 classe ;
- 1 enseignant ;
- 1 responsable ;
- 1 élève ;
- 1 matière ;
- 1 enseignement ;
- 1 inscription ;
- 1 note ;
- 1 bulletin ;
- 1 responsabilité.

Il contient aussi deux utilisateurs :

- `enseignant1`
- `responsable1`

Leurs mots de passe ne sont pas exploitables directement pour une démonstration car les valeurs présentes sont des hashes factices (`dummyhash...`).

## Principales fonctionnalités

- authentification avec JWT ;
- consultation des tableaux de bord et écrans métier ;
- CRUD des classes ;
- CRUD des élèves ;
- affichage et mise à jour de photo élève ;
- CRUD des enseignants ;
- CRUD des responsables ;
- CRUD des matières ;
- CRUD des notes ;
- CRUD des bulletins ;
- export PDF des bulletins.

## Endpoints principaux de l'API

Swagger et OpenAPI :

- Swagger UI : `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON : `http://localhost:8080/v3/api-docs`

Endpoints principaux réellement présents :

- `POST /auth/login`
- `GET|POST|PUT|DELETE /api/classes/...`
- `GET|POST|PUT|DELETE /api/eleves/...`
- `GET /api/eleves/{id}/photo`
- `POST /api/eleves/{id}/photo`
- `GET|POST|PUT|DELETE /api/enseignants/...`
- `GET|POST|PUT|DELETE /api/responsables/...`
- `GET|POST|PUT|DELETE /api/matieres/...`
- `GET|POST|PUT|DELETE /api/notes/...`
- `GET|POST|PUT|DELETE /api/bulletins/...`
- `GET /api/bulletins/{id}/pdf`
- `GET|POST|PUT|DELETE /api/inscriptions/...`
- `GET|POST|PUT|DELETE /api/enseignements/...`
- `GET|POST|PUT|DELETE /api/responsabilites/...`
- `GET|POST|PUT|DELETE /api/utilisateurs/...`

Pour la documentation détaillée endpoint par endpoint, voir [API.md](API.md).

## Captures à ajouter

Le dépôt contient déjà plusieurs images dans [Analyses/](Analyses), mais le README peut être complété avec des captures de soutenance plus ciblées.

Captures recommandées :

- `[A ajouter]` écran de connexion
- `[A ajouter]` tableau de bord
- `[A ajouter]` page Élèves avec photo
- `[A ajouter]` page Bulletins avec export PDF
- `[A ajouter]` Swagger UI

Exemples de ressources déjà présentes dans le projet :

- [Analyses/umlClasses.png](Analyses/umlClasses.png)
- [Analyses/MCD.jpg](Analyses/MCD.jpg)
- [Analyses/MLD.jpg](Analyses/MLD.jpg)

## Documentation complémentaire

- [API.md](API.md) : documentation des endpoints REST présents dans le code
- [PRESENTATION.md](PRESENTATION.md) : trame de soutenance CDA 10 à 15 minutes

## Pistes d'amélioration

Pistes cohérentes avec le projet existant, sans inventer de fonctionnalité déjà livrée :

- gestion plus fine des rôles et autorisations ;
- messages d'erreur frontend plus détaillés ;
- recherche, tri et pagination sur les listes ;
- amélioration du cycle de vie des fichiers photo ;
- couverture de tests plus large ;
- industrialisation plus poussée du déploiement.

## Vérification des commandes documentées

Les commandes documentées dans ce README correspondent aux scripts et outils réellement présents dans le dépôt :

- `.\backend\mvnw.cmd -f .\backend\pom.xml ...` : cohérent avec le Maven Wrapper backend et le `pom.xml` ;
- `npm install`, `npm run dev`, `npm run build`, `npm run lint`, `npm run preview` : cohérents avec [frontend/package.json](frontend/package.json) ;
- `docker compose up -d` : cohérent avec [docker-compose.yml](docker-compose.yml).