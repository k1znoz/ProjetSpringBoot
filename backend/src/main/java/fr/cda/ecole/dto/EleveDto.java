package fr.cda.ecole.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EleveDto {

    private Long idEleve;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotNull
    private LocalDate dateNaissance;
    private String adresse;
    @Email
    private String email;
    private String telephone;

    public EleveDto() {
    }

    public Long getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(Long idEleve) {
        this.idEleve = idEleve;
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
