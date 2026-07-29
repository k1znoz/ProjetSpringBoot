package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.ResponsableDto;
import fr.cda.ecole.entity.Responsable;

public class ResponsableMapper {

    public static ResponsableDto toDto(Responsable entity) {
        if (entity == null) {
            return null;
        }

        ResponsableDto dto = new ResponsableDto();
        dto.setIdResponsable(entity.getIdResponsable());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        return dto;
    }

    public static Responsable toEntity(ResponsableDto dto) {
        if (dto == null) {
            return null;
        }

        Responsable entity = new Responsable();
        entity.setIdResponsable(dto.getIdResponsable());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        return entity;
    }
}
