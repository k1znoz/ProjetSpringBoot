package fr.cda.ecole.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BulletinDto {

    private Long idBulletin;
    @NotNull
    @Min(1)
    @Max(3)
    private Short trimestre;
    private String appreciation;
    private BigDecimal moyenneGenerale;
    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{4}$")
    private String anneeScolaire;

    public BulletinDto() {
    }

    public Long getIdBulletin() {
        return idBulletin;
    }

    public void setIdBulletin(Long idBulletin) {
        this.idBulletin = idBulletin;
    }

    public Short getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Short trimestre) {
        this.trimestre = trimestre;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public BigDecimal getMoyenneGenerale() {
        return moyenneGenerale;
    }

    public void setMoyenneGenerale(BigDecimal moyenneGenerale) {
        this.moyenneGenerale = moyenneGenerale;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }
}
