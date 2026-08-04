# Projet Spring Boot - Gestion Scolaire

Application full-stack de gestion scolaire réalisée dans le cadre du sujet CDA. Le projet couvre les entités principales du domaine scolaire avec un backend Spring Boot sécurisé, un frontend React modernisé avec TailwindCSS et les livrables d’analyse attendus dans le dossier `Analyses/`.

## Vue D'ensemble

Le projet permet de gérer :

- les élèves
- les classes
- les enseignants
- les responsables
- les matières
- les notes
- les bulletins

L’application inclut aussi l’authentification JWT, une interface protégée côté frontend, une documentation Swagger/OpenAPI et des scripts de test côté backend.

## Livrables Du Sujet

Les livrables attendus par le sujet sont regroupés dans ce dépôt :

- MCD Merise, dans `Analyses/`
- MLD Merise, dans `Analyses/`
- MPD PostgreSQL, dans `Analyses/mpd_postgresql.sql`
- Diagramme UML des classes, dans `Analyses/umlClasses.png`
- Code source complet backend et frontend
- Tests unitaires et d’intégration backend
- Docker Compose pour la base PostgreSQL
- Documentation projet, dans ce README

## Structure Du Dépôt

- `backend/` : API Spring Boot, sécurité, JPA, validation, Swagger et tests
- `frontend/` : interface React, routes protégées, CRUD et TailwindCSS
- `Analyses/` : livrables de conception et de modélisation
- `docker-compose.yml` : service PostgreSQL local
- `.env.example` : variables d’environnement pour Docker

## Prérequis

- Java 17
- Node.js 20 ou supérieur
- Maven Wrapper inclus dans le projet backend
- Docker Desktop si vous voulez lancer PostgreSQL via Docker Compose

## Configuration

1. Copier le fichier `.env.example` à la racine du projet et le renommer en `.env`.
2. Vérifier les valeurs de base de données si nécessaire.
3. Laisser le backend utiliser la configuration de `backend/src/main/resources/application.yml` si vous restez sur les valeurs par défaut.

Variables principales utilisées par le projet :

- `POSTGRES_DB`
- `POSTGRES_USER`
- `POSTGRES_PASSWORD`
- `POSTGRES_PORT`
- `SERVER_PORT`
- `APPLICATION_SECURITY_JWT_SECRET`

## Démarrage Rapide

### 1) Lancer PostgreSQL avec Docker

```powershell
docker compose up -d
```

Le service PostgreSQL est défini dans `docker-compose.yml` et expose le port configuré dans `.env`.

### 2) Lancer le backend

Depuis la racine du projet :

```powershell
mvnw.cmd -f .\backend\pom.xml spring-boot:run
```

Autres commandes utiles :

```powershell
mvnw.cmd -f .\backend\pom.xml clean compile
mvnw.cmd -f .\backend\pom.xml test
mvnw.cmd -f .\backend\pom.xml clean package
java -jar .\backend\target\gestion-scolaire-api-0.0.1-SNAPSHOT.jar
```

### 3) Lancer le frontend

```powershell
cd .\frontend
npm install
npm run dev
```

Commandes utiles côté frontend :

```powershell
npm run build
npm run lint
```

## Comptes Et Accès

Pour tester l’authentification dans Swagger, utiliser :

- username : `DOE`
- password : `Passw0rd!`

## Endpoints Et Documentation

- Swagger UI : http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON : http://localhost:8080/v3/api-docs

## Vérifications Disponibles

Les tests backend sont présents dans `backend/src/test/java/` et couvrent à la fois des services et un test d’intégration contrôleur.

Exécuter toute la suite backend :

```powershell
mvnw.cmd -f .\backend\pom.xml test
```

## Résumé Projet

Ce dépôt contient bien les éléments essentiels attendus pour une livraison de projet de gestion scolaire : modélisation, backend, frontend, sécurité, persistance, tests et déploiement local via Docker. Le README sert de point d’entrée unique pour comprendre le projet, lancer chaque brique et retrouver les livrables du sujet.