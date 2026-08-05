package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.EleveDto;
import fr.cda.ecole.entity.Eleve;

public class EleveMapper {

    public static EleveDto toDto(Eleve entity) {
        if (entity == null) {
            return null;
        }

        EleveDto dto = new EleveDto();
        dto.setIdEleve(entity.getIdEleve());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setAdresse(entity.getAdresse());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        dto.setPhotoPath(entity.getPhotoPath());
        return dto;
    }

    public static Eleve toEntity(EleveDto dto) {
        if (dto == null) {
            return null;
        }

        Eleve entity = new Eleve();
        entity.setIdEleve(dto.getIdEleve());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setAdresse(dto.getAdresse());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        entity.setPhotoPath(dto.getPhotoPath());
        return entity;
    }
}
