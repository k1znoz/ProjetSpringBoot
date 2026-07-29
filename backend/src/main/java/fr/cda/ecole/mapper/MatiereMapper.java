package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.MatiereDto;
import fr.cda.ecole.entity.Matiere;

public class MatiereMapper {

    public static MatiereDto toDto(Matiere entity) {
        if (entity == null) {
            return null;
        }

        MatiereDto dto = new MatiereDto();
        dto.setIdMatiere(entity.getIdMatiere());
        dto.setNomMatiere(entity.getNomMatiere());
        dto.setCoefficient(entity.getCoefficient());
        return dto;
    }

    public static Matiere toEntity(MatiereDto dto) {
        if (dto == null) {
            return null;
        }

        Matiere entity = new Matiere();
        entity.setIdMatiere(dto.getIdMatiere());
        entity.setNomMatiere(dto.getNomMatiere());
        entity.setCoefficient(dto.getCoefficient());
        return entity;
    }
}
