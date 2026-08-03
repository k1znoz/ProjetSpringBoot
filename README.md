# Projet Spring Boot - Commandes utiles

Ce README regroupe les commandes essentielles pour compiler, exécuter, tester et packager le backend Spring Boot.

## Prérequis

- Java 17+ installé
- Utiliser le Maven Wrapper inclus (`mvnw.cmd`)
- Se placer à la racine du projet (`ProjetSpringBoot`) ou dans `backend` selon les exemples

---

## 1) Compiler le backend

Commande (depuis la racine):

```powershell
mvnw.cmd -f .\backend\pom.xml clean compile
```

Utilité:

- Vérifie que tout le code Java compile
- Régénère les classes dans `backend\target`
- Nettoie d'abord les anciens artefacts

---

## 2) Lancer l'application Spring Boot

Commande (depuis la racine):

```powershell
mvnw.cmd -f .\backend\pom.xml spring-boot:run
```

Alternative (si vous êtes déjà dans `backend`):

```powershell
.\mvnw.cmd spring-boot:run
```

Utilité:

- Démarre l'API en local
- Permet de tester les endpoints (Swagger, Postman, frontend)

---

## 3) Exécuter les tests

Commande:

```powershell
mvnw.cmd -f .\backend\pom.xml test
```

Utilité:

- Lance les tests automatiques
- Valide les régressions avant livraison

---

## 4) Build complet (JAR)

Commande:

```powershell
mvnw.cmd -f .\backend\pom.xml clean package
```

Utilité:

- Compile + teste + génère le JAR exécutable
- Sortie attendue dans `backend\target`

---

## 5) Lancer le JAR généré

Commande:

```powershell
java -jar .\backend\target\gestion-scolaire-api-0.0.1-SNAPSHOT.jar
```

Utilité:

- Lance l'application sans Maven
- Proche d'un lancement en environnement de déploiement

---

## 6) Nettoyer uniquement

Commande:

```powershell
mvnw.cmd -f .\backend\pom.xml clean
```

Utilité:

- Supprime le dossier `target`
- Repart d'un état propre en cas de build incohérent

---

## 7) Vérifier rapidement que l'API répond

Commande:

```powershell
Invoke-WebRequest http://localhost:8080/v3/api-docs
```

Utilité:

- Vérifie que le backend est démarré
- Vérifie que Swagger/OpenAPI répond

Liens utiles:

- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## Enchaînement rapide recommandé

Compile puis run:

```powershell
mvnw.cmd -f .\backend\pom.xml clean compile
mvnw.cmd -f .\backend\pom.xml spring-boot:run
```
