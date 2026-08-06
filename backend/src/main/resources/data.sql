TRUNCATE TABLE
    utilisateur,
    responsabilite,
    bulletin,
    note,
    inscription,
    enseignement,
    matiere,
    eleve,
    responsable,
    enseignant,
    classe
RESTART IDENTITY CASCADE;

INSERT INTO classe (id_classe, nom_classe, niveau, annee_scolaire)
VALUES (1, '6A', '6eme', '2025-2026');

INSERT INTO enseignant (id_enseignant, nom, prenom, email, telephone)
VALUES (1, 'Martin', 'Paul', 'paul.martin@ecole.fr', '0102030405');

INSERT INTO responsable (id_responsable, nom, prenom, email, telephone)
VALUES (1, 'Dupont', 'Marie', 'marie.dupont@famille.fr', '0607080910');

INSERT INTO eleve (id_eleve, nom, prenom, date_naissance, adresse, email, telephone)
VALUES (1, 'Dupont', 'Leo', '2013-05-14', '12 rue des Ecoles', 'leo.dupont@eleve.fr', '0611223344');

INSERT INTO matiere (id_matiere, nom_matiere, coefficient)
VALUES (1, 'Mathematiques', 2.00);

INSERT INTO enseignement (id_enseignement, volume_horaire, id_enseignant, id_matiere, id_classe)
VALUES (1, 4, 1, 1, 1);

INSERT INTO inscription (id_inscription, date_inscription, statut, id_eleve, id_classe)
VALUES (1, '2025-09-01', 'ACTIVE', 1, 1);

INSERT INTO note (id_note, valeur, date_note, commentaire, type_evaluation, id_eleve, id_matiere)
VALUES (1, 15.50, '2025-10-10', 'Bon travail', 'Controle', 1, 1);

INSERT INTO bulletin (id_bulletin, trimestre, appreciation, moyenne_generale, annee_scolaire, id_eleve)
VALUES (1, 1, 'Trimestre satisfaisant', 15.50, '2025-2026', 1);

INSERT INTO responsabilite (id_responsable, id_eleve, lien_parente)
VALUES (1, 1, 'MERE');

INSERT INTO utilisateur (id_utilisateur, username, password_hash, actif, role, id_enseignant, id_responsable)
VALUES
    (1, 'DOE', '$2a$10$1rDbJznTj7IJibZtYcs1wuMbJ4JSmXngMnwva9irAx5/mpB5bYDO.', TRUE, 'ENSEIGNANT', 1, NULL),
    (2, 'responsable1', '$2a$10$1rDbJznTj7IJibZtYcs1wuMbJ4JSmXngMnwva9irAx5/mpB5bYDO.', TRUE, 'RESPONSABLE', NULL, 1);

SELECT setval(pg_get_serial_sequence('classe', 'id_classe'), COALESCE((SELECT MAX(id_classe) FROM classe), 1), true);
SELECT setval(pg_get_serial_sequence('enseignant', 'id_enseignant'), COALESCE((SELECT MAX(id_enseignant) FROM enseignant), 1), true);
SELECT setval(pg_get_serial_sequence('responsable', 'id_responsable'), COALESCE((SELECT MAX(id_responsable) FROM responsable), 1), true);
SELECT setval(pg_get_serial_sequence('eleve', 'id_eleve'), COALESCE((SELECT MAX(id_eleve) FROM eleve), 1), true);
SELECT setval(pg_get_serial_sequence('matiere', 'id_matiere'), COALESCE((SELECT MAX(id_matiere) FROM matiere), 1), true);
SELECT setval(pg_get_serial_sequence('enseignement', 'id_enseignement'), COALESCE((SELECT MAX(id_enseignement) FROM enseignement), 1), true);
SELECT setval(pg_get_serial_sequence('inscription', 'id_inscription'), COALESCE((SELECT MAX(id_inscription) FROM inscription), 1), true);
SELECT setval(pg_get_serial_sequence('note', 'id_note'), COALESCE((SELECT MAX(id_note) FROM note), 1), true);
SELECT setval(pg_get_serial_sequence('bulletin', 'id_bulletin'), COALESCE((SELECT MAX(id_bulletin) FROM bulletin), 1), true);
SELECT setval(pg_get_serial_sequence('utilisateur', 'id_utilisateur'), COALESCE((SELECT MAX(id_utilisateur) FROM utilisateur), 1), true);
