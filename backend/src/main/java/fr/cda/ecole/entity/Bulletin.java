package fr.cda.ecole.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

@Entity
@Table(
        name = "bulletin",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "id_eleve",
                                "annee_scolaire",
                                "trimestre"
                        }
                )
        }
)
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bulletin")
    private Long idBulletin;

    @Column(name = "trimestre", nullable = false)
    @NotNull
    @Min(1)
    @Max(3)
    private Short trimestre;

    @Column(name = "appreciation", columnDefinition = "TEXT")
    private String appreciation;

    @Column(name = "moyenne_generale", precision = 5, scale = 2)
    private BigDecimal moyenneGenerale;

    @Column(name = "annee_scolaire", nullable = false, length = 9)
    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{4}$")
    private String anneeScolaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve", nullable = false)
    private Eleve eleve;

    public Bulletin() {
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

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
}