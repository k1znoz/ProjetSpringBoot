package fr.cda.ecole.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EnseignementDto {

    private Long idEnseignement;
    @NotNull
    @Positive
    private Integer volumeHoraire;

    public EnseignementDto() {
    }

    public Long getIdEnseignement() {
        return idEnseignement;
    }

    public void setIdEnseignement(Long idEnseignement) {
        this.idEnseignement = idEnseignement;
    }

    public Integer getVolumeHoraire() {
        return volumeHoraire;
    }

    public void setVolumeHoraire(Integer volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }
}
