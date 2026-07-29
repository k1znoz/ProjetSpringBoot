package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Enseignement;
import fr.cda.ecole.repository.EnseignementRepository;

@Service
public class EnseignementService {

    private final EnseignementRepository enseignementRepository;

    public EnseignementService(EnseignementRepository enseignementRepository) {
        this.enseignementRepository = enseignementRepository;
    }

    public List<Enseignement> findAll() {
        return enseignementRepository.findAll();
    }

    public Optional<Enseignement> findById(Long id) {
        return enseignementRepository.findById(id);
    }

    public Enseignement save(Enseignement enseignement) {
        return enseignementRepository.save(enseignement);
    }

    public void deleteById(Long id) {
        enseignementRepository.deleteById(id);
    }
}
