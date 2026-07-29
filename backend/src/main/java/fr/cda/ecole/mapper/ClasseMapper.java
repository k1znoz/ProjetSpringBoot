package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.ClasseDto;
import fr.cda.ecole.entity.Classe;

public class ClasseMapper {

    public static ClasseDto toDto(Classe entity) {
        if (entity == null) {
            return null;
        }

        ClasseDto dto = new ClasseDto();
        dto.setIdClasse(entity.getIdClasse());
        dto.setNomClasse(entity.getNomClasse());
        dto.setNiveau(entity.getNiveau());
        dto.setAnneeScolaire(entity.getAnneeScolaire());
        return dto;
    }

    public static Classe toEntity(ClasseDto dto) {
        if (dto == null) {
            return null;
        }

        Classe entity = new Classe();
        entity.setIdClasse(dto.getIdClasse());
        entity.setNomClasse(dto.getNomClasse());
        entity.setNiveau(dto.getNiveau());
        entity.setAnneeScolaire(dto.getAnneeScolaire());
        return entity;
    }
}
