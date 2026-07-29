package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.UtilisateurDto;
import fr.cda.ecole.entity.Role;
import fr.cda.ecole.entity.Utilisateur;

public class UtilisateurMapper {

    public static UtilisateurDto toDto(Utilisateur entity) {
        if (entity == null) {
            return null;
        }

        UtilisateurDto dto = new UtilisateurDto();
        dto.setIdUtilisateur(entity.getIdUtilisateur());
        dto.setUsername(entity.getUsername());
        dto.setPasswordHash(entity.getPasswordHash());
        dto.setActif(entity.getActif());
        dto.setRole(entity.getRole() != null ? entity.getRole().name() : null);
        return dto;
    }

    public static Utilisateur toEntity(UtilisateurDto dto) {
        if (dto == null) {
            return null;
        }

        Utilisateur entity = new Utilisateur();
        entity.setIdUtilisateur(dto.getIdUtilisateur());
        entity.setUsername(dto.getUsername());
        entity.setPasswordHash(dto.getPasswordHash());
        entity.setActif(dto.getActif());
        entity.setRole(dto.getRole() != null ? Role.valueOf(dto.getRole()) : null);
        return entity;
    }
}
