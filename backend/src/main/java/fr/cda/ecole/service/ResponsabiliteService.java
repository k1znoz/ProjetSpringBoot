package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.ResponsabiliteDto;
import fr.cda.ecole.entity.ResponsabiliteId;
import fr.cda.ecole.mapper.ResponsabiliteMapper;
import fr.cda.ecole.repository.ResponsabiliteRepository;

@Service
public class ResponsabiliteService {

    private final ResponsabiliteRepository responsabiliteRepository;

    public ResponsabiliteService(ResponsabiliteRepository responsabiliteRepository) {
        this.responsabiliteRepository = responsabiliteRepository;
    }

    public List<ResponsabiliteDto> findAll() {
        return responsabiliteRepository.findAll().stream()
                .map(ResponsabiliteMapper::toDto)
                .toList();
    }

    public Optional<ResponsabiliteDto> findById(ResponsabiliteId id) {
        return responsabiliteRepository.findById(id)
                .map(ResponsabiliteMapper::toDto);
    }

    public ResponsabiliteDto save(ResponsabiliteDto responsabiliteDto) {
        return ResponsabiliteMapper.toDto(
                responsabiliteRepository.save(ResponsabiliteMapper.toEntity(responsabiliteDto))
        );
    }

    public void deleteById(ResponsabiliteId id) {
        responsabiliteRepository.deleteById(id);
    }
}
