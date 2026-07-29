package fr.cda.ecole.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NoteDto {

    private Long idNote;
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("20.00")
    private BigDecimal valeur;
    @NotNull
    private LocalDate dateNote;
    private String commentaire;
    @NotBlank
    private String typeEvaluation;

    public NoteDto() {
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
}
