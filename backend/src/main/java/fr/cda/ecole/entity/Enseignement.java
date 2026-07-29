package fr.cda.ecole.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "enseignement")
public class Enseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enseignement")
    private Long idEnseignement;

    @Column(name = "volume_horaire", nullable = false)
    @NotNull
    @Positive
    private Integer volumeHoraire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enseignant", nullable = false)
    private Enseignant enseignant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe", nullable = false)
    private Classe classe;

    public Enseignement() {
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

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
