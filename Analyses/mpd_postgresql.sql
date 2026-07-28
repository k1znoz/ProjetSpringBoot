BEGIN;

CREATE TABLE enseignant (
  id_enseignant BIGSERIAL PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE,
  telephone VARCHAR(30)
);

CREATE TABLE responsable (
  id_responsable BIGSERIAL PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE,
  telephone VARCHAR(30)
);

CREATE TABLE eleve (
  id_eleve BIGSERIAL PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  date_naissance DATE NOT NULL,
  adresse VARCHAR(255),
  email VARCHAR(150) UNIQUE,
  telephone VARCHAR(30)
);

CREATE TABLE classe (
  id_classe BIGSERIAL PRIMARY KEY,
  nom_classe VARCHAR(50) NOT NULL,
  niveau VARCHAR(30) NOT NULL,
  annee_scolaire VARCHAR(9) NOT NULL,
  CONSTRAINT ck_classe_annee CHECK (annee_scolaire ~ '^[0-9]{4}-[0-9]{4}$')
);

CREATE TABLE matiere (
  id_matiere BIGSERIAL PRIMARY KEY,
  nom_matiere VARCHAR(100) NOT NULL UNIQUE,
  coefficient NUMERIC(4,2) NOT NULL CHECK (coefficient > 0)
);

CREATE TABLE utilisateur (
  id_utilisateur BIGSERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  actif BOOLEAN NOT NULL DEFAULT TRUE,
  role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN','ENSEIGNANT','RESPONSABLE')),
  id_enseignant BIGINT UNIQUE,
  id_responsable BIGINT UNIQUE,
  CONSTRAINT fk_user_ens FOREIGN KEY (id_enseignant) REFERENCES enseignant(id_enseignant) ON DELETE SET NULL,
  CONSTRAINT fk_user_resp FOREIGN KEY (id_responsable) REFERENCES responsable(id_responsable) ON DELETE SET NULL
);

CREATE TABLE inscription (
  id_inscription BIGSERIAL PRIMARY KEY,
  date_inscription DATE NOT NULL DEFAULT CURRENT_DATE,
  statut VARCHAR(20) NOT NULL CHECK (statut IN ('EN_ATTENTE','VALIDEE','ANNULEE')),
  id_eleve BIGINT NOT NULL REFERENCES eleve(id_eleve),
  id_classe BIGINT NOT NULL REFERENCES classe(id_classe)
);

CREATE TABLE enseignement (
  id_enseignement BIGSERIAL PRIMARY KEY,
  volume_horaire INT NOT NULL CHECK (volume_horaire > 0),
  id_enseignant BIGINT NOT NULL REFERENCES enseignant(id_enseignant),
  id_matiere BIGINT NOT NULL REFERENCES matiere(id_matiere),
  id_classe BIGINT NOT NULL REFERENCES classe(id_classe)
);

CREATE TABLE note (
  id_note BIGSERIAL PRIMARY KEY,
  valeur NUMERIC(5,2) NOT NULL CHECK (valeur >= 0 AND valeur <= 20),
  date_note DATE NOT NULL,
  commentaire VARCHAR(255),
  type_evaluation VARCHAR(50) NOT NULL,
  id_eleve BIGINT NOT NULL REFERENCES eleve(id_eleve),
  id_matiere BIGINT NOT NULL REFERENCES matiere(id_matiere)
);

CREATE TABLE bulletin (
  id_bulletin BIGSERIAL PRIMARY KEY,
  trimestre SMALLINT NOT NULL CHECK (trimestre IN (1,2,3)),
  appreciation TEXT,
  moyenne_generale NUMERIC(5,2) CHECK (moyenne_generale >= 0 AND moyenne_generale <= 20),
  annee_scolaire VARCHAR(9) NOT NULL CHECK (annee_scolaire ~ '^[0-9]{4}-[0-9]{4}$'),
  id_eleve BIGINT NOT NULL REFERENCES eleve(id_eleve),
  CONSTRAINT uq_bulletin UNIQUE (id_eleve, annee_scolaire, trimestre)
);

CREATE TABLE responsabilite (
  id_responsable BIGINT NOT NULL REFERENCES responsable(id_responsable) ON DELETE CASCADE,
  id_eleve BIGINT NOT NULL REFERENCES eleve(id_eleve) ON DELETE CASCADE,
  lien_parente VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_responsable, id_eleve)
);

COMMIT;