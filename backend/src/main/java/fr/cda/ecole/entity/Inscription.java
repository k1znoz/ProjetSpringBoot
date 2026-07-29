package fr.cda.ecole.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscription")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription")
    private Long idInscription;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    @Column(name = "statut", nullable = false, length = 20)
    private String statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve", nullable = false)
    private Eleve eleve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe", nullable = false)
    private Classe classe;

    public Inscription() {
    }

    public Long getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Long idInscription) {
        this.idInscription = idInscription;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
