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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "enseignant")
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enseignant")
    private Long idEnseignant;

    @Column(name = "nom", nullable = false, length = 100)
    @NotBlank
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    @NotBlank
    private String prenom;

    @Column(name = "email", unique = true, length = 150)
    @Email
    private String email;

    @Column(name = "telephone", length = 30)
    private String telephone;

    @OneToOne(mappedBy = "enseignant", fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "enseignant", fetch = FetchType.LAZY)
    private List<Enseignement> enseignements = new ArrayList<>();

    public Enseignant() {
    }

    public Long getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Long idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Enseignement> getEnseignements() {
        return enseignements;
    }

    public void setEnseignements(List<Enseignement> enseignements) {
        this.enseignements = enseignements;
    }
}
