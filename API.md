# API.md

## Vue d'ensemble

- Base URL par defaut: `http://localhost:8080`
- Authentification: JWT Bearer dans l'en-tete `Authorization: Bearer <token>`
- Endpoint public: `POST /auth/login`
- Tous les endpoints `/api/**` exigent un JWT valide

## Format d'erreur commun

Les erreurs gerees par `GlobalExceptionHandler` renvoient un JSON de cette forme:

```json
{
  "timestamp": "2026-08-05T00:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/eleves/1"
}
```

## Authentification

### POST /auth/login

- Methode HTTP: `POST`
- URL: `/auth/login`
- Authentification requise: non
- Parametres: aucun
- Corps de requete:

```json
{
  "username": "DOE",
  "password": "Passw0rd!"
}
```

- Corps de reponse:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

- Codes HTTP possibles:
  - `200 OK`: authentification reussie
  - `401 Unauthorized`: identifiants invalides
  - `500 Internal Server Error`: erreur non geree
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"DOE","password":"Passw0rd!"}'
```

- Exemple de reponse:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## Classes

### GET /api/classes/

- Methode HTTP: `GET`
- URL: `/api/classes/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idClasse": 1,
    "nomClasse": "6A",
    "niveau": "6eme",
    "anneeScolaire": "2025-2026"
  }
]
```

- Codes HTTP possibles:
  - `200 OK`
  - `401 Unauthorized`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl http://localhost:8080/api/classes/ \
  -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
[
  {
    "idClasse": 1,
    "nomClasse": "6A",
    "niveau": "6eme",
    "anneeScolaire": "2025-2026"
  }
]
```

### GET /api/classes/{id}

- Methode HTTP: `GET`
- URL: `/api/classes/{id}`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse:

```json
{
  "idClasse": 1,
  "nomClasse": "6A",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

- Codes HTTP possibles:
  - `200 OK`
  - `404 Not Found`
  - `401 Unauthorized`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl http://localhost:8080/api/classes/1 \
  -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idClasse": 1,
  "nomClasse": "6A",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

### POST /api/classes/

- Methode HTTP: `POST`
- URL: `/api/classes/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "nomClasse": "6A",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

- Corps de reponse:

```json
{
  "idClasse": 1,
  "nomClasse": "6A",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

- Codes HTTP possibles:
  - `201 Created`
  - `400 Bad Request`
  - `401 Unauthorized`
  - `409 Conflict`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/classes/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nomClasse":"6A","niveau":"6eme","anneeScolaire":"2025-2026"}'
```

- Exemple de reponse:

```json
{
  "idClasse": 1,
  "nomClasse": "6A",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

### PUT /api/classes/{id}

- Methode HTTP: `PUT`
- URL: `/api/classes/{id}`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
- Corps de requete:

```json
{
  "nomClasse": "6B",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

- Corps de reponse:

```json
{
  "idClasse": 1,
  "nomClasse": "6B",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

- Codes HTTP possibles:
  - `200 OK`
  - `400 Bad Request`
  - `404 Not Found`
  - `401 Unauthorized`
  - `409 Conflict`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/classes/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nomClasse":"6B","niveau":"6eme","anneeScolaire":"2025-2026"}'
```

- Exemple de reponse:

```json
{
  "idClasse": 1,
  "nomClasse": "6B",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

### DELETE /api/classes/{id}

- Methode HTTP: `DELETE`
- URL: `/api/classes/{id}`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles:
  - `204 No Content`
  - `401 Unauthorized`
  - `409 Conflict`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/classes/1 \
  -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Eleves

### GET /api/eleves/

- Methode HTTP: `GET`
- URL: `/api/eleves/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idEleve": 1,
    "nom": "Dupont",
    "prenom": "Leo",
    "dateNaissance": "2012-01-10",
    "adresse": "10 rue Test",
    "email": "leo@ecole.fr",
    "telephone": "0601020304",
    "photoPath": "uploads/eleves/eleve-1-uuid.png"
  }
]
```

- Codes HTTP possibles:
  - `200 OK`
  - `401 Unauthorized`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl http://localhost:8080/api/eleves/ \
  -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/eleves/{id}

- Methode HTTP: `GET`
- URL: `/api/eleves/{id}`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse:

```json
{
  "idEleve": 1,
  "nom": "Dupont",
  "prenom": "Leo",
  "dateNaissance": "2012-01-10",
  "adresse": "10 rue Test",
  "email": "leo@ecole.fr",
  "telephone": "0601020304",
  "photoPath": "uploads/eleves/eleve-1-uuid.png"
}
```

- Codes HTTP possibles:
  - `200 OK`
  - `404 Not Found`
  - `401 Unauthorized`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl http://localhost:8080/api/eleves/1 \
  -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/eleves/{id}/photo

- Methode HTTP: `GET`
- URL: `/api/eleves/{id}/photo`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: binaire `image/*` detecte depuis le fichier stocke
- Codes HTTP possibles:
  - `200 OK`
  - `404 Not Found`
  - `401 Unauthorized`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl http://localhost:8080/api/eleves/1/photo \
  -H "Authorization: Bearer <token>" \
  --output eleve-1.png
```

- Exemple de reponse:

```text
HTTP/1.1 200 OK
Content-Type: image/png
(binary body)
```

### POST /api/eleves/

- Methode HTTP: `POST`
- URL: `/api/eleves/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "nom": "Dupont",
  "prenom": "Leo",
  "dateNaissance": "2012-01-10",
  "adresse": "10 rue Test",
  "email": "leo@ecole.fr",
  "telephone": "0601020304",
  "photoPath": null
}
```

- Corps de reponse:

```json
{
  "idEleve": 1,
  "nom": "Dupont",
  "prenom": "Leo",
  "dateNaissance": "2012-01-10",
  "adresse": "10 rue Test",
  "email": "leo@ecole.fr",
  "telephone": "0601020304",
  "photoPath": null
}
```

- Codes HTTP possibles:
  - `201 Created`
  - `400 Bad Request`
  - `401 Unauthorized`
  - `409 Conflict`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/eleves/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Dupont","prenom":"Leo","dateNaissance":"2012-01-10","adresse":"10 rue Test","email":"leo@ecole.fr","telephone":"0601020304","photoPath":null}'
```

- Exemple de reponse: voir ci-dessus

### PUT /api/eleves/{id}

- Methode HTTP: `PUT`
- URL: `/api/eleves/{id}`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
- Corps de requete:

```json
{
  "nom": "Dupont",
  "prenom": "Leo",
  "dateNaissance": "2012-01-10",
  "adresse": "12 rue Test",
  "email": "leo@ecole.fr",
  "telephone": "0601020304",
  "photoPath": "uploads/eleves/eleve-1-uuid.png"
}
```

- Corps de reponse:

```json
{
  "idEleve": 1,
  "nom": "Dupont",
  "prenom": "Leo",
  "dateNaissance": "2012-01-10",
  "adresse": "12 rue Test",
  "email": "leo@ecole.fr",
  "telephone": "0601020304",
  "photoPath": "uploads/eleves/eleve-1-uuid.png"
}
```

- Codes HTTP possibles:
  - `200 OK`
  - `400 Bad Request`
  - `404 Not Found`
  - `401 Unauthorized`
  - `409 Conflict`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/eleves/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Dupont","prenom":"Leo","dateNaissance":"2012-01-10","adresse":"12 rue Test","email":"leo@ecole.fr","telephone":"0601020304","photoPath":"uploads/eleves/eleve-1-uuid.png"}'
```

- Exemple de reponse: voir ci-dessus

### DELETE /api/eleves/{id}

- Methode HTTP: `DELETE`
- URL: `/api/eleves/{id}`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles:
  - `204 No Content`
  - `401 Unauthorized`
  - `409 Conflict`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/eleves/1 \
  -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

### POST /api/eleves/{id}/photo

- Methode HTTP: `POST`
- URL: `/api/eleves/{id}/photo`
- Authentification requise: oui
- Parametres:
  - `id` (path, `Long`)
  - `file` (form-data, `MultipartFile`)
- Corps de requete: `multipart/form-data`
- Corps de reponse:

```json
{
  "idEleve": 1,
  "nom": "Dupont",
  "prenom": "Leo",
  "dateNaissance": "2012-01-10",
  "adresse": "10 rue Test",
  "email": "leo@ecole.fr",
  "telephone": "0601020304",
  "photoPath": "uploads/eleves/eleve-1-uuid.png"
}
```

- Codes HTTP possibles:
  - `200 OK`
  - `400 Bad Request` (fichier vide)
  - `404 Not Found`
  - `401 Unauthorized`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/eleves/1/photo \
  -H "Authorization: Bearer <token>" \
  -F "file=@./photo.png"
```

- Exemple de reponse: voir ci-dessus

## Enseignants

### GET /api/enseignants/

- Methode HTTP: `GET`
- URL: `/api/enseignants/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idEnseignant": 1,
    "nom": "Doe",
    "prenom": "John",
    "email": "john.doe@ecole.fr",
    "telephone": "0601020304"
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/enseignants/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/enseignants/{id}

- Methode HTTP: `GET`
- URL: `/api/enseignants/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme structure que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/enseignants/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idEnseignant": 1,
  "nom": "Doe",
  "prenom": "John",
  "email": "john.doe@ecole.fr",
  "telephone": "0601020304"
}
```

### POST /api/enseignants/

- Methode HTTP: `POST`
- URL: `/api/enseignants/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "nom": "Doe",
  "prenom": "John",
  "email": "john.doe@ecole.fr",
  "telephone": "0601020304"
}
```

- Corps de reponse: meme structure avec `idEnseignant`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/enseignants/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Doe","prenom":"John","email":"john.doe@ecole.fr","telephone":"0601020304"}'
```

- Exemple de reponse:

```json
{
  "idEnseignant": 1,
  "nom": "Doe",
  "prenom": "John",
  "email": "john.doe@ecole.fr",
  "telephone": "0601020304"
}
```

### PUT /api/enseignants/{id}

- Methode HTTP: `PUT`
- URL: `/api/enseignants/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idEnseignant`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/enseignants/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Doe","prenom":"Jane","email":"jane.doe@ecole.fr","telephone":"0601020304"}'
```

- Exemple de reponse:

```json
{
  "idEnseignant": 1,
  "nom": "Doe",
  "prenom": "Jane",
  "email": "jane.doe@ecole.fr",
  "telephone": "0601020304"
}
```

### DELETE /api/enseignants/{id}

- Methode HTTP: `DELETE`
- URL: `/api/enseignants/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/enseignants/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Responsables

### GET /api/responsables/

- Methode HTTP: `GET`
- URL: `/api/responsables/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idResponsable": 1,
    "nom": "Martin",
    "prenom": "Anne",
    "email": "anne.martin@ecole.fr",
    "telephone": "0601020304"
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/responsables/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/responsables/{id}

- Methode HTTP: `GET`
- URL: `/api/responsables/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/responsables/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idResponsable": 1,
  "nom": "Martin",
  "prenom": "Anne",
  "email": "anne.martin@ecole.fr",
  "telephone": "0601020304"
}
```

### POST /api/responsables/

- Methode HTTP: `POST`
- URL: `/api/responsables/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "nom": "Martin",
  "prenom": "Anne",
  "email": "anne.martin@ecole.fr",
  "telephone": "0601020304"
}
```

- Corps de reponse: meme DTO avec `idResponsable`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/responsables/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Martin","prenom":"Anne","email":"anne.martin@ecole.fr","telephone":"0601020304"}'
```

- Exemple de reponse:

```json
{
  "idResponsable": 1,
  "nom": "Martin",
  "prenom": "Anne",
  "email": "anne.martin@ecole.fr",
  "telephone": "0601020304"
}
```

### PUT /api/responsables/{id}

- Methode HTTP: `PUT`
- URL: `/api/responsables/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idResponsable`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/responsables/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Martin","prenom":"Anne","email":"anne.martin@ecole.fr","telephone":"0601020304"}'
```

- Exemple de reponse:

```json
{
  "idResponsable": 1,
  "nom": "Martin",
  "prenom": "Anne",
  "email": "anne.martin@ecole.fr",
  "telephone": "0601020304"
}
```

### DELETE /api/responsables/{id}

- Methode HTTP: `DELETE`
- URL: `/api/responsables/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/responsables/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Matieres

### GET /api/matieres/

- Methode HTTP: `GET`
- URL: `/api/matieres/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idMatiere": 1,
    "nomMatiere": "Mathematiques",
    "coefficient": 2.0
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/matieres/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/matieres/{id}

- Methode HTTP: `GET`
- URL: `/api/matieres/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/matieres/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idMatiere": 1,
  "nomMatiere": "Mathematiques",
  "coefficient": 2.0
}
```

### POST /api/matieres/

- Methode HTTP: `POST`
- URL: `/api/matieres/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "nomMatiere": "Mathematiques",
  "coefficient": 2.0
}
```

- Corps de reponse: meme DTO avec `idMatiere`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/matieres/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nomMatiere":"Mathematiques","coefficient":2.0}'
```

- Exemple de reponse:

```json
{
  "idMatiere": 1,
  "nomMatiere": "Mathematiques",
  "coefficient": 2.0
}
```

### PUT /api/matieres/{id}

- Methode HTTP: `PUT`
- URL: `/api/matieres/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idMatiere`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/matieres/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"nomMatiere":"Mathematiques","coefficient":3.0}'
```

- Exemple de reponse:

```json
{
  "idMatiere": 1,
  "nomMatiere": "Mathematiques",
  "coefficient": 3.0
}
```

### DELETE /api/matieres/{id}

- Methode HTTP: `DELETE`
- URL: `/api/matieres/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/matieres/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Notes

### GET /api/notes/

- Methode HTTP: `GET`
- URL: `/api/notes/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idNote": 1,
    "valeur": 15.5,
    "dateNote": "2026-01-15",
    "commentaire": "Bon travail",
    "typeEvaluation": "Controle",
    "idEleve": 1,
    "idMatiere": 1
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/notes/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/notes/{id}

- Methode HTTP: `GET`
- URL: `/api/notes/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/notes/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idNote": 1,
  "valeur": 15.5,
  "dateNote": "2026-01-15",
  "commentaire": "Bon travail",
  "typeEvaluation": "Controle",
  "idEleve": 1,
  "idMatiere": 1
}
```

### POST /api/notes/

- Methode HTTP: `POST`
- URL: `/api/notes/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "valeur": 15.5,
  "dateNote": "2026-01-15",
  "commentaire": "Bon travail",
  "typeEvaluation": "Controle",
  "idEleve": 1,
  "idMatiere": 1
}
```

- Corps de reponse: meme DTO avec `idNote`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/notes/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"valeur":15.5,"dateNote":"2026-01-15","commentaire":"Bon travail","typeEvaluation":"Controle","idEleve":1,"idMatiere":1}'
```

- Exemple de reponse:

```json
{
  "idNote": 1,
  "valeur": 15.5,
  "dateNote": "2026-01-15",
  "commentaire": "Bon travail",
  "typeEvaluation": "Controle",
  "idEleve": 1,
  "idMatiere": 1
}
```

### PUT /api/notes/{id}

- Methode HTTP: `PUT`
- URL: `/api/notes/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idNote`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/notes/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"valeur":16.0,"dateNote":"2026-01-15","commentaire":"Tres bon travail","typeEvaluation":"Controle","idEleve":1,"idMatiere":1}'
```

- Exemple de reponse:

```json
{
  "idNote": 1,
  "valeur": 16.0,
  "dateNote": "2026-01-15",
  "commentaire": "Tres bon travail",
  "typeEvaluation": "Controle",
  "idEleve": 1,
  "idMatiere": 1
}
```

### DELETE /api/notes/{id}

- Methode HTTP: `DELETE`
- URL: `/api/notes/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/notes/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Bulletins

### GET /api/bulletins/

- Methode HTTP: `GET`
- URL: `/api/bulletins/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idBulletin": 1,
    "trimestre": 1,
    "appreciation": "Bon trimestre",
    "moyenneGenerale": 14.5,
    "anneeScolaire": "2025-2026",
    "idEleve": 1
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/bulletins/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/bulletins/{id}

- Methode HTTP: `GET`
- URL: `/api/bulletins/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/bulletins/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idBulletin": 1,
  "trimestre": 1,
  "appreciation": "Bon trimestre",
  "moyenneGenerale": 14.5,
  "anneeScolaire": "2025-2026",
  "idEleve": 1
}
```

### POST /api/bulletins/

- Methode HTTP: `POST`
- URL: `/api/bulletins/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "trimestre": 1,
  "appreciation": "Bon trimestre",
  "moyenneGenerale": 14.5,
  "anneeScolaire": "2025-2026",
  "idEleve": 1
}
```

- Corps de reponse: meme DTO avec `idBulletin`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/bulletins/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"trimestre":1,"appreciation":"Bon trimestre","moyenneGenerale":14.5,"anneeScolaire":"2025-2026","idEleve":1}'
```

- Exemple de reponse:

```json
{
  "idBulletin": 1,
  "trimestre": 1,
  "appreciation": "Bon trimestre",
  "moyenneGenerale": 14.5,
  "anneeScolaire": "2025-2026",
  "idEleve": 1
}
```

### PUT /api/bulletins/{id}

- Methode HTTP: `PUT`
- URL: `/api/bulletins/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idBulletin`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/bulletins/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"trimestre":2,"appreciation":"Progression","moyenneGenerale":15.0,"anneeScolaire":"2025-2026","idEleve":1}'
```

- Exemple de reponse:

```json
{
  "idBulletin": 1,
  "trimestre": 2,
  "appreciation": "Progression",
  "moyenneGenerale": 15.0,
  "anneeScolaire": "2025-2026",
  "idEleve": 1
}
```

### DELETE /api/bulletins/{id}

- Methode HTTP: `DELETE`
- URL: `/api/bulletins/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/bulletins/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

### GET /api/bulletins/{id}/pdf

- Methode HTTP: `GET`
- URL: `/api/bulletins/{id}/pdf`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: binaire `application/pdf`
- Codes HTTP possibles:
  - `200 OK`
  - `404 Not Found`
  - `401 Unauthorized`
  - `500 Internal Server Error`
- Exemple de requete:

```bash
curl http://localhost:8080/api/bulletins/1/pdf \
  -H "Authorization: Bearer <token>" \
  --output bulletin_1.pdf
```

- Exemple de reponse:

```text
HTTP/1.1 200 OK
Content-Type: application/pdf
Content-Disposition: attachment; filename="bulletin_1.pdf"
(binary body)
```

## Inscriptions

### GET /api/inscriptions/

- Methode HTTP: `GET`
- URL: `/api/inscriptions/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idInscription": 1,
    "dateInscription": "2025-09-01",
    "statut": "ACTIVE",
    "idEleve": 1,
    "idClasse": 1
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/inscriptions/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/inscriptions/{id}

- Methode HTTP: `GET`
- URL: `/api/inscriptions/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/inscriptions/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idInscription": 1,
  "dateInscription": "2025-09-01",
  "statut": "ACTIVE",
  "idEleve": 1,
  "idClasse": 1
}
```

### POST /api/inscriptions/

- Methode HTTP: `POST`
- URL: `/api/inscriptions/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "dateInscription": "2025-09-01",
  "statut": "ACTIVE",
  "idEleve": 1,
  "idClasse": 1
}
```

- Corps de reponse: meme DTO avec `idInscription`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/inscriptions/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"dateInscription":"2025-09-01","statut":"ACTIVE","idEleve":1,"idClasse":1}'
```

- Exemple de reponse:

```json
{
  "idInscription": 1,
  "dateInscription": "2025-09-01",
  "statut": "ACTIVE",
  "idEleve": 1,
  "idClasse": 1
}
```

### PUT /api/inscriptions/{id}

- Methode HTTP: `PUT`
- URL: `/api/inscriptions/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idInscription`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/inscriptions/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"dateInscription":"2025-09-01","statut":"INACTIVE","idEleve":1,"idClasse":1}'
```

- Exemple de reponse:

```json
{
  "idInscription": 1,
  "dateInscription": "2025-09-01",
  "statut": "INACTIVE",
  "idEleve": 1,
  "idClasse": 1
}
```

### DELETE /api/inscriptions/{id}

- Methode HTTP: `DELETE`
- URL: `/api/inscriptions/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/inscriptions/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Enseignements

### GET /api/enseignements/

- Methode HTTP: `GET`
- URL: `/api/enseignements/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idEnseignement": 1,
    "volumeHoraire": 36,
    "idEnseignant": 1,
    "idMatiere": 1,
    "idClasse": 1
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/enseignements/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/enseignements/{id}

- Methode HTTP: `GET`
- URL: `/api/enseignements/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/enseignements/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idEnseignement": 1,
  "volumeHoraire": 36,
  "idEnseignant": 1,
  "idMatiere": 1,
  "idClasse": 1
}
```

### POST /api/enseignements/

- Methode HTTP: `POST`
- URL: `/api/enseignements/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "volumeHoraire": 36,
  "idEnseignant": 1,
  "idMatiere": 1,
  "idClasse": 1
}
```

- Corps de reponse: meme DTO avec `idEnseignement`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/enseignements/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"volumeHoraire":36,"idEnseignant":1,"idMatiere":1,"idClasse":1}'
```

- Exemple de reponse:

```json
{
  "idEnseignement": 1,
  "volumeHoraire": 36,
  "idEnseignant": 1,
  "idMatiere": 1,
  "idClasse": 1
}
```

### PUT /api/enseignements/{id}

- Methode HTTP: `PUT`
- URL: `/api/enseignements/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idEnseignement`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/enseignements/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"volumeHoraire":40,"idEnseignant":1,"idMatiere":1,"idClasse":1}'
```

- Exemple de reponse:

```json
{
  "idEnseignement": 1,
  "volumeHoraire": 40,
  "idEnseignant": 1,
  "idMatiere": 1,
  "idClasse": 1
}
```

### DELETE /api/enseignements/{id}

- Methode HTTP: `DELETE`
- URL: `/api/enseignements/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/enseignements/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Responsabilites

### GET /api/responsabilites/

- Methode HTTP: `GET`
- URL: `/api/responsabilites/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idResponsable": 1,
    "idEleve": 1,
    "lienParente": "Pere"
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/responsabilites/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/responsabilites/{idResponsable}/{idEleve}

- Methode HTTP: `GET`
- URL: `/api/responsabilites/{idResponsable}/{idEleve}`
- Authentification requise: oui
- Parametres:
  - `idResponsable` (path, `Long`)
  - `idEleve` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/responsabilites/1/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idResponsable": 1,
  "idEleve": 1,
  "lienParente": "Pere"
}
```

### POST /api/responsabilites/

- Methode HTTP: `POST`
- URL: `/api/responsabilites/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "idResponsable": 1,
  "idEleve": 1,
  "lienParente": "Pere"
}
```

- Corps de reponse: meme DTO
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/responsabilites/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"idResponsable":1,"idEleve":1,"lienParente":"Pere"}'
```

- Exemple de reponse:

```json
{
  "idResponsable": 1,
  "idEleve": 1,
  "lienParente": "Pere"
}
```

### PUT /api/responsabilites/{idResponsable}/{idEleve}

- Methode HTTP: `PUT`
- URL: `/api/responsabilites/{idResponsable}/{idEleve}`
- Authentification requise: oui
- Parametres:
  - `idResponsable` (path, `Long`)
  - `idEleve` (path, `Long`)
- Corps de requete:

```json
{
  "lienParente": "Mere"
}
```

- Corps de reponse:

```json
{
  "idResponsable": 1,
  "idEleve": 1,
  "lienParente": "Mere"
}
```

- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/responsabilites/1/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"lienParente":"Mere"}'
```

- Exemple de reponse: voir ci-dessus

### DELETE /api/responsabilites/{idResponsable}/{idEleve}

- Methode HTTP: `DELETE`
- URL: `/api/responsabilites/{idResponsable}/{idEleve}`
- Authentification requise: oui
- Parametres:
  - `idResponsable` (path, `Long`)
  - `idEleve` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/responsabilites/1/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Utilisateurs

### GET /api/utilisateurs/

- Methode HTTP: `GET`
- URL: `/api/utilisateurs/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete: aucun
- Corps de reponse:

```json
[
  {
    "idUtilisateur": 1,
    "username": "DOE",
    "passwordHash": "$2a$10$...",
    "actif": true,
    "role": "ENSEIGNANT"
  }
]
```

- Codes HTTP possibles: `200`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/utilisateurs/ -H "Authorization: Bearer <token>"
```

- Exemple de reponse: voir ci-dessus

### GET /api/utilisateurs/{id}

- Methode HTTP: `GET`
- URL: `/api/utilisateurs/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: meme DTO que ci-dessus
- Codes HTTP possibles: `200`, `404`, `401`, `500`
- Exemple de requete:

```bash
curl http://localhost:8080/api/utilisateurs/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```json
{
  "idUtilisateur": 1,
  "username": "DOE",
  "passwordHash": "$2a$10$...",
  "actif": true,
  "role": "ENSEIGNANT"
}
```

### POST /api/utilisateurs/

- Methode HTTP: `POST`
- URL: `/api/utilisateurs/`
- Authentification requise: oui
- Parametres: aucun
- Corps de requete:

```json
{
  "username": "DOE",
  "passwordHash": "$2a$10$...",
  "actif": true,
  "role": "ENSEIGNANT"
}
```

- Corps de reponse: meme DTO avec `idUtilisateur`
- Codes HTTP possibles: `201`, `400`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X POST http://localhost:8080/api/utilisateurs/ \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"username":"DOE","passwordHash":"$2a$10$...","actif":true,"role":"ENSEIGNANT"}'
```

- Exemple de reponse:

```json
{
  "idUtilisateur": 1,
  "username": "DOE",
  "passwordHash": "$2a$10$...",
  "actif": true,
  "role": "ENSEIGNANT"
}
```

### PUT /api/utilisateurs/{id}

- Methode HTTP: `PUT`
- URL: `/api/utilisateurs/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: meme DTO que POST
- Corps de reponse: meme DTO avec `idUtilisateur`
- Codes HTTP possibles: `200`, `400`, `404`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X PUT http://localhost:8080/api/utilisateurs/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"username":"DOE","passwordHash":"$2a$10$...","actif":true,"role":"ADMIN"}'
```

- Exemple de reponse:

```json
{
  "idUtilisateur": 1,
  "username": "DOE",
  "passwordHash": "$2a$10$...",
  "actif": true,
  "role": "ADMIN"
}
```

### DELETE /api/utilisateurs/{id}

- Methode HTTP: `DELETE`
- URL: `/api/utilisateurs/{id}`
- Authentification requise: oui
- Parametres: `id` (path, `Long`)
- Corps de requete: aucun
- Corps de reponse: aucun
- Codes HTTP possibles: `204`, `401`, `409`, `500`
- Exemple de requete:

```bash
curl -X DELETE http://localhost:8080/api/utilisateurs/1 -H "Authorization: Bearer <token>"
```

- Exemple de reponse:

```text
HTTP/1.1 204 No Content
```

## Schema des DTO utilises

### ClasseDto

```json
{
  "idClasse": 1,
  "nomClasse": "6A",
  "niveau": "6eme",
  "anneeScolaire": "2025-2026"
}
```

Contraintes:
- `nomClasse`: obligatoire
- `niveau`: obligatoire
- `anneeScolaire`: obligatoire, format `YYYY-YYYY`

### EleveDto

```json
{
  "idEleve": 1,
  "nom": "Dupont",
  "prenom": "Leo",
  "dateNaissance": "2012-01-10",
  "adresse": "10 rue Test",
  "email": "leo@ecole.fr",
  "telephone": "0601020304",
  "photoPath": "uploads/eleves/eleve-1-uuid.png"
}
```

Contraintes:
- `nom`: obligatoire
- `prenom`: obligatoire
- `dateNaissance`: obligatoire
- `email`: format email si renseigne

### EnseignantDto

```json
{
  "idEnseignant": 1,
  "nom": "Doe",
  "prenom": "John",
  "email": "john.doe@ecole.fr",
  "telephone": "0601020304"
}
```

Contraintes:
- `nom`: obligatoire
- `prenom`: obligatoire
- `email`: format email si renseigne

### EnseignementDto

```json
{
  "idEnseignement": 1,
  "volumeHoraire": 36,
  "idEnseignant": 1,
  "idMatiere": 1,
  "idClasse": 1
}
```

Contraintes:
- `volumeHoraire`: obligatoire, strictement positif

### InscriptionDto

```json
{
  "idInscription": 1,
  "dateInscription": "2025-09-01",
  "statut": "ACTIVE",
  "idEleve": 1,
  "idClasse": 1
}
```

Contraintes:
- `dateInscription`: obligatoire
- `statut`: obligatoire

### MatiereDto

```json
{
  "idMatiere": 1,
  "nomMatiere": "Mathematiques",
  "coefficient": 2.0
}
```

Contraintes:
- `nomMatiere`: obligatoire
- `coefficient`: obligatoire, strictement positif

### NoteDto

```json
{
  "idNote": 1,
  "valeur": 15.5,
  "dateNote": "2026-01-15",
  "commentaire": "Bon travail",
  "typeEvaluation": "Controle",
  "idEleve": 1,
  "idMatiere": 1
}
```

Contraintes:
- `valeur`: obligatoire, entre `0.00` et `20.00`
- `dateNote`: obligatoire
- `typeEvaluation`: obligatoire

### BulletinDto

```json
{
  "idBulletin": 1,
  "trimestre": 1,
  "appreciation": "Bon trimestre",
  "moyenneGenerale": 14.5,
  "anneeScolaire": "2025-2026",
  "idEleve": 1
}
```

Contraintes:
- `trimestre`: obligatoire, entre `1` et `3`
- `anneeScolaire`: obligatoire, format `YYYY-YYYY`

### ResponsabiliteDto

```json
{
  "idResponsable": 1,
  "idEleve": 1,
  "lienParente": "Pere"
}
```

Contraintes:
- `lienParente`: obligatoire

### ResponsableDto

```json
{
  "idResponsable": 1,
  "nom": "Martin",
  "prenom": "Anne",
  "email": "anne.martin@ecole.fr",
  "telephone": "0601020304"
}
```

Contraintes:
- `nom`: obligatoire
- `prenom`: obligatoire
- `email`: format email si renseigne

### UtilisateurDto

```json
{
  "idUtilisateur": 1,
  "username": "DOE",
  "passwordHash": "$2a$10$...",
  "actif": true,
  "role": "ENSEIGNANT"
}
```

Contraintes:
- `username`: obligatoire
- `passwordHash`: obligatoire
- `actif`: obligatoire
- `role`: obligatoire, une des valeurs `ADMIN`, `ENSEIGNANT`, `RESPONSABLE`
