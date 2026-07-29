package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.EnseignantDto;
import fr.cda.ecole.entity.Enseignant;

public class EnseignantMapper {

    public static EnseignantDto toDto(Enseignant entity) {
        if (entity == null) {
            return null;
        }

        EnseignantDto dto = new EnseignantDto();
        dto.setIdEnseignant(entity.getIdEnseignant());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        return dto;
    }

    public static Enseignant toEntity(EnseignantDto dto) {
        if (dto == null) {
            return null;
        }

        Enseignant entity = new Enseignant();
        entity.setIdEnseignant(dto.getIdEnseignant());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        return entity;
    }
}
