package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.EnseignementDto;
import fr.cda.ecole.entity.Enseignement;

public class EnseignementMapper {

    public static EnseignementDto toDto(Enseignement entity) {
        if (entity == null) {
            return null;
        }

        EnseignementDto dto = new EnseignementDto();
        dto.setIdEnseignement(entity.getIdEnseignement());
        dto.setVolumeHoraire(entity.getVolumeHoraire());
        return dto;
    }

    public static Enseignement toEntity(EnseignementDto dto) {
        if (dto == null) {
            return null;
        }

        Enseignement entity = new Enseignement();
        entity.setIdEnseignement(dto.getIdEnseignement());
        entity.setVolumeHoraire(dto.getVolumeHoraire());
        return entity;
    }
}
