package fr.cda.ecole.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "classe")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classe")
    private Long idClasse;

    @Column(name = "nom_classe", nullable = false, length = 50)
    private String nomClasse;

    @Column(name = "niveau", nullable = false, length = 30)
    private String niveau;

    @Column(name = "annee_scolaire", nullable = false, length = 9)
    private String anneeScolaire;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    private List<Enseignement> enseignements = new ArrayList<>();

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    private List<Inscription> inscriptions = new ArrayList<>();

    public Classe() {
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public List<Enseignement> getEnseignements() {
        return enseignements;
    }

    public void setEnseignements(List<Enseignement> enseignements) {
        this.enseignements = enseignements;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
