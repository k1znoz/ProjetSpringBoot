package fr.cda.ecole.entity;

import java.math.BigDecimal;
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
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note")
    private Long idNote;

    @Column(name = "valeur", nullable = false, precision = 5, scale = 2)
    private BigDecimal valeur;

    @Column(name = "date_note", nullable = false)
    private LocalDate dateNote;

    @Column(name = "commentaire", length = 255)
    private String commentaire;

    @Column(name = "type_evaluation", nullable = false, length = 50)
    private String typeEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve", nullable = false)
    private Eleve eleve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    public Note() {
    }

    public Long getIdNote() {
        return idNote;
    }

    public void setIdNote(Long idNote) {
        this.idNote = idNote;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public LocalDate getDateNote() {
        return dateNote;
    }

    public void setDateNote(LocalDate dateNote) {
        this.dateNote = dateNote;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(String typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
}