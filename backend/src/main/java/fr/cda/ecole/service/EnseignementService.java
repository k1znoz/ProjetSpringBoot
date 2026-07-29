package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.EnseignementDto;
import fr.cda.ecole.mapper.EnseignementMapper;
import fr.cda.ecole.repository.EnseignementRepository;

@Service
public class EnseignementService {

    private final EnseignementRepository enseignementRepository;

    public EnseignementService(EnseignementRepository enseignementRepository) {
        this.enseignementRepository = enseignementRepository;
    }

    public List<EnseignementDto> findAll() {
        return enseignementRepository.findAll().stream()
                .map(EnseignementMapper::toDto)
                .toList();
    }

    public Optional<EnseignementDto> findById(Long id) {
        return enseignementRepository.findById(id)
                .map(EnseignementMapper::toDto);
    }

    public EnseignementDto save(EnseignementDto enseignementDto) {
        return EnseignementMapper.toDto(
                enseignementRepository.save(EnseignementMapper.toEntity(enseignementDto))
        );
    }

    public void deleteById(Long id) {
        enseignementRepository.deleteById(id);
    }
}
