package fr.cda.ecole.dto;

import java.math.BigDecimal;

public class BulletinDto {

    private Long idBulletin;
    private Short trimestre;
    private String appreciation;
    private BigDecimal moyenneGenerale;
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
