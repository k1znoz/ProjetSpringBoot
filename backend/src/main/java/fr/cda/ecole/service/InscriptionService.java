package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.InscriptionDto;
import fr.cda.ecole.mapper.InscriptionMapper;
import fr.cda.ecole.repository.InscriptionRepository;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public List<InscriptionDto> findAll() {
        return inscriptionRepository.findAll().stream()
                .map(InscriptionMapper::toDto)
                .toList();
    }

    public Optional<InscriptionDto> findById(Long id) {
        return inscriptionRepository.findById(id)
                .map(InscriptionMapper::toDto);
    }

    public InscriptionDto save(InscriptionDto inscriptionDto) {
        return InscriptionMapper.toDto(
                inscriptionRepository.save(InscriptionMapper.toEntity(inscriptionDto))
        );
    }

    public void deleteById(Long id) {
        inscriptionRepository.deleteById(id);
    }
}
