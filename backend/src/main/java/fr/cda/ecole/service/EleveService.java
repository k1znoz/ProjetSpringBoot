package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.EleveDto;
import fr.cda.ecole.mapper.EleveMapper;
import fr.cda.ecole.repository.EleveRepository;

@Service
public class EleveService {

    private final EleveRepository eleveRepository;

    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    public List<EleveDto> findAll() {
        return eleveRepository.findAll().stream()
                .map(EleveMapper::toDto)
                .toList();
    }

    public Optional<EleveDto> findById(Long id) {
        return eleveRepository.findById(id)
                .map(EleveMapper::toDto);
    }

    public EleveDto save(EleveDto eleveDto) {
        return EleveMapper.toDto(
                eleveRepository.save(EleveMapper.toEntity(eleveDto))
        );
    }

    public void deleteById(Long id) {
        eleveRepository.deleteById(id);
    }
}
