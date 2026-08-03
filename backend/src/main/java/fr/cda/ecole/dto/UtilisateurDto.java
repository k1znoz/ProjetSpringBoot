package fr.cda.ecole.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UtilisateurDto {

    private Long idUtilisateur;
    @NotBlank
    private String username;
    @NotBlank
    private String passwordHash;
    @NotNull
    private Boolean actif;
    @NotNull
        @Pattern(
            regexp = "ADMIN|ENSEIGNANT|RESPONSABLE",
            message = "role must be one of: ADMIN, ENSEIGNANT, RESPONSABLE"
        )
    private String role;

    public UtilisateurDto() {
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
