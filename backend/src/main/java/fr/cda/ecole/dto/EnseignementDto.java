package fr.cda.ecole.dto;

public class EnseignementDto {

    private Long idEnseignement;
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
