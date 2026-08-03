package fr.cda.ecole.mapper;

import java.util.Locale;

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
        if (dto.getRole() != null) {
            try {
                entity.setRole(Role.valueOf(dto.getRole().trim().toUpperCase(Locale.ROOT)));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException(
                        "Invalid role '" + dto.getRole() + "'. Allowed values: ADMIN, ENSEIGNANT, RESPONSABLE"
                );
            }
        } else {
            entity.setRole(null);
        }
        return entity;
    }
}
