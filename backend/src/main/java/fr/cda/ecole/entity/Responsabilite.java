package fr.cda.ecole.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsabilite")
public class Responsabilite {

    @EmbeddedId
    private ResponsabiliteId id;

    @Column(name = "lien_parente", nullable = false, length = 30)
    private String lienParente;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idResponsable")
    @JoinColumn(name = "id_responsable", nullable = false)
    private Responsable responsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEleve")
    @JoinColumn(name = "id_eleve", nullable = false)
    private Eleve eleve;

    public Responsabilite() {
    }

    public ResponsabiliteId getId() {
        return id;
    }

    public void setId(ResponsabiliteId id) {
        this.id = id;
    }

    public String getLienParente() {
        return lienParente;
    }

    public void setLienParente(String lienParente) {
        this.lienParente = lienParente;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
}
