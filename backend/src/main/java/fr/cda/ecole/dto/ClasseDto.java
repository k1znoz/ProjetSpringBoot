package fr.cda.ecole.dto;

public class ClasseDto {

    private Long idClasse;
    private String nomClasse;
    private String niveau;
    private String anneeScolaire;

    public ClasseDto() {
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }
}
