package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.InscriptionDto;
import fr.cda.ecole.entity.Classe;
import fr.cda.ecole.entity.Eleve;
import fr.cda.ecole.entity.Inscription;

public class InscriptionMapper {

    public static InscriptionDto toDto(Inscription entity) {
        if (entity == null) {
            return null;
        }

        InscriptionDto dto = new InscriptionDto();
        dto.setIdInscription(entity.getIdInscription());
        dto.setDateInscription(entity.getDateInscription());
        dto.setStatut(entity.getStatut());
        dto.setIdEleve(entity.getEleve() != null ? entity.getEleve().getIdEleve() : null);
        dto.setIdClasse(entity.getClasse() != null ? entity.getClasse().getIdClasse() : null);
        return dto;
    }

    public static Inscription toEntity(InscriptionDto dto) {
        if (dto == null) {
            return null;
        }

        Inscription entity = new Inscription();
        entity.setIdInscription(dto.getIdInscription());
        entity.setDateInscription(dto.getDateInscription());
        entity.setStatut(dto.getStatut());
        if (dto.getIdEleve() != null) {
            Eleve eleve = new Eleve();
            eleve.setIdEleve(dto.getIdEleve());
            entity.setEleve(eleve);
        }
        if (dto.getIdClasse() != null) {
            Classe classe = new Classe();
            classe.setIdClasse(dto.getIdClasse());
            entity.setClasse(classe);
        }
        return entity;
    }
}
