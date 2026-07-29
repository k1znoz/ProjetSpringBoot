package fr.cda.ecole.mapper;

import fr.cda.ecole.dto.NoteDto;
import fr.cda.ecole.entity.Note;

public class NoteMapper {

    public static NoteDto toDto(Note entity) {
        if (entity == null) {
            return null;
        }

        NoteDto dto = new NoteDto();
        dto.setIdNote(entity.getIdNote());
        dto.setValeur(entity.getValeur());
        dto.setDateNote(entity.getDateNote());
        dto.setCommentaire(entity.getCommentaire());
        dto.setTypeEvaluation(entity.getTypeEvaluation());
        return dto;
    }

    public static Note toEntity(NoteDto dto) {
        if (dto == null) {
            return null;
        }

        Note entity = new Note();
        entity.setIdNote(dto.getIdNote());
        entity.setValeur(dto.getValeur());
        entity.setDateNote(dto.getDateNote());
        entity.setCommentaire(dto.getCommentaire());
        entity.setTypeEvaluation(dto.getTypeEvaluation());
        return entity;
    }
}
