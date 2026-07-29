package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.EnseignantDto;
import fr.cda.ecole.mapper.EnseignantMapper;
import fr.cda.ecole.repository.EnseignantRepository;

@Service
public class EnseignantService {

    private final EnseignantRepository enseignantRepository;

    public EnseignantService(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    public List<EnseignantDto> findAll() {
        return enseignantRepository.findAll().stream()
                .map(EnseignantMapper::toDto)
                .toList();
    }

    public Optional<EnseignantDto> findById(Long id) {
        return enseignantRepository.findById(id)
                .map(EnseignantMapper::toDto);
    }

    public EnseignantDto save(EnseignantDto enseignantDto) {
        return EnseignantMapper.toDto(
                enseignantRepository.save(EnseignantMapper.toEntity(enseignantDto))
        );
    }

    public void deleteById(Long id) {
        enseignantRepository.deleteById(id);
    }
}
