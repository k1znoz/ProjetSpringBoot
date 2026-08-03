package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.EnseignementDto;
import fr.cda.ecole.entity.Classe;
import fr.cda.ecole.entity.Enseignant;
import fr.cda.ecole.entity.Enseignement;
import fr.cda.ecole.entity.Matiere;

public class EnseignementMapper {

    public static EnseignementDto toDto(Enseignement entity) {
        if (entity == null) {
            return null;
        }

        EnseignementDto dto = new EnseignementDto();
        dto.setIdEnseignement(entity.getIdEnseignement());
        dto.setVolumeHoraire(entity.getVolumeHoraire());
        dto.setIdEnseignant(entity.getEnseignant() != null ? entity.getEnseignant().getIdEnseignant() : null);
        dto.setIdMatiere(entity.getMatiere() != null ? entity.getMatiere().getIdMatiere() : null);
        dto.setIdClasse(entity.getClasse() != null ? entity.getClasse().getIdClasse() : null);
        return dto;
    }

    public static Enseignement toEntity(EnseignementDto dto) {
        if (dto == null) {
            return null;
        }

        Enseignement entity = new Enseignement();
        entity.setIdEnseignement(dto.getIdEnseignement());
        entity.setVolumeHoraire(dto.getVolumeHoraire());
        if (dto.getIdEnseignant() != null) {
            Enseignant enseignant = new Enseignant();
            enseignant.setIdEnseignant(dto.getIdEnseignant());
            entity.setEnseignant(enseignant);
        }
        if (dto.getIdMatiere() != null) {
            Matiere matiere = new Matiere();
            matiere.setIdMatiere(dto.getIdMatiere());
            entity.setMatiere(matiere);
        }
        if (dto.getIdClasse() != null) {
            Classe classe = new Classe();
            classe.setIdClasse(dto.getIdClasse());
            entity.setClasse(classe);
        }
        return entity;
    }
}
