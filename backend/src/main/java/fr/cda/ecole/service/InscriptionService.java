package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Inscription;
import fr.cda.ecole.repository.InscriptionRepository;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findById(Long id) {
        return inscriptionRepository.findById(id);
    }

    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public void deleteById(Long id) {
        inscriptionRepository.deleteById(id);
    }
}
