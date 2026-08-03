package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.BulletinDto;
import fr.cda.ecole.entity.Bulletin;
import fr.cda.ecole.entity.Eleve;

public class BulletinMapper {

    public static BulletinDto toDto(Bulletin entity) {
        if (entity == null) {
            return null;
        }

        BulletinDto dto = new BulletinDto();
        dto.setIdBulletin(entity.getIdBulletin());
        dto.setTrimestre(entity.getTrimestre());
        dto.setAppreciation(entity.getAppreciation());
        dto.setMoyenneGenerale(entity.getMoyenneGenerale());
        dto.setAnneeScolaire(entity.getAnneeScolaire());
        dto.setIdEleve(entity.getEleve() != null ? entity.getEleve().getIdEleve() : null);
        return dto;
    }

    public static Bulletin toEntity(BulletinDto dto) {
        if (dto == null) {
            return null;
        }

        Bulletin entity = new Bulletin();
        entity.setIdBulletin(dto.getIdBulletin());
        entity.setTrimestre(dto.getTrimestre());
        entity.setAppreciation(dto.getAppreciation());
        entity.setMoyenneGenerale(dto.getMoyenneGenerale());
        entity.setAnneeScolaire(dto.getAnneeScolaire());
        if (dto.getIdEleve() != null) {
            Eleve eleve = new Eleve();
            eleve.setIdEleve(dto.getIdEleve());
            entity.setEleve(eleve);
        }
        return entity;
    }
}
