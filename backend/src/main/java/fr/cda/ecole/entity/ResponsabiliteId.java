package fr.cda.ecole.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ResponsabiliteId implements Serializable {

    @Column(name = "id_responsable")
    private Long idResponsable;

    @Column(name = "id_eleve")
    private Long idEleve;

    public ResponsabiliteId() {
    }

    public ResponsabiliteId(Long idResponsable, Long idEleve) {
        this.idResponsable = idResponsable;
        this.idEleve = idEleve;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponsabiliteId that = (ResponsabiliteId) o;
        return Objects.equals(idResponsable, that.idResponsable)
                && Objects.equals(idEleve, that.idEleve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idResponsable, idEleve);
    }
}
