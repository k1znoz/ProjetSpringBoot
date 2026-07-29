package fr.cda.ecole.dto;

import java.math.BigDecimal;

public class MatiereDto {

    private Long idMatiere;
    private String nomMatiere;
    private BigDecimal coefficient;

    public MatiereDto() {
    }

    public Long getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
