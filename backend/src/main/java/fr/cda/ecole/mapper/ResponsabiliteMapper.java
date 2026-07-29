package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.ResponsabiliteDto;
import fr.cda.ecole.entity.Responsabilite;
import fr.cda.ecole.entity.ResponsabiliteId;

public class ResponsabiliteMapper {

    public static ResponsabiliteDto toDto(Responsabilite entity) {
        if (entity == null) {
            return null;
        }

        ResponsabiliteDto dto = new ResponsabiliteDto();
        if (entity.getId() != null) {
            dto.setIdResponsable(entity.getId().getIdResponsable());
            dto.setIdEleve(entity.getId().getIdEleve());
        }
        dto.setLienParente(entity.getLienParente());
        return dto;
    }

    public static Responsabilite toEntity(ResponsabiliteDto dto) {
        if (dto == null) {
            return null;
        }

        Responsabilite entity = new Responsabilite();
        entity.setId(new ResponsabiliteId(dto.getIdResponsable(), dto.getIdEleve()));
        entity.setLienParente(dto.getLienParente());
        return entity;
    }
}
