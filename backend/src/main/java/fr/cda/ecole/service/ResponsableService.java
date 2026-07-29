package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.ResponsableDto;
import fr.cda.ecole.mapper.ResponsableMapper;
import fr.cda.ecole.repository.ResponsableRepository;

@Service
public class ResponsableService {

    private final ResponsableRepository responsableRepository;

    public ResponsableService(ResponsableRepository responsableRepository) {
        this.responsableRepository = responsableRepository;
    }

    public List<ResponsableDto> findAll() {
        return responsableRepository.findAll().stream()
                .map(ResponsableMapper::toDto)
                .toList();
    }

    public Optional<ResponsableDto> findById(Long id) {
        return responsableRepository.findById(id)
                .map(ResponsableMapper::toDto);
    }

    public ResponsableDto save(ResponsableDto responsableDto) {
        return ResponsableMapper.toDto(
                responsableRepository.save(ResponsableMapper.toEntity(responsableDto))
        );
    }

    public void deleteById(Long id) {
        responsableRepository.deleteById(id);
    }
}
