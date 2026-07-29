package fr.cda.ecole.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EnseignantDto {

    private Long idEnseignant;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @Email
    private String email;
    private String telephone;

    public EnseignantDto() {
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
}
