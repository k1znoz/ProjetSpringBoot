package fr.cda.ecole.dto;

import jakarta.validation.constraints.NotBlank;

public class ResponsabiliteDto {

    private Long idResponsable;
    private Long idEleve;
    @NotBlank
    private String lienParente;

    public ResponsabiliteDto() {
    }

    public Long getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Long getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(Long idEleve) {
        this.idEleve = idEleve;
    }

    public String getLienParente() {
        return lienParente;
    }

    public void setLienParente(String lienParente) {
        this.lienParente = lienParente;
    }
}
