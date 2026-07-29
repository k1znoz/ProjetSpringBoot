package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Eleve;
import fr.cda.ecole.repository.EleveRepository;

@Service
public class EleveService {

    private final EleveRepository eleveRepository;

    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    public List<Eleve> findAll() {
        return eleveRepository.findAll();
    }

    public Optional<Eleve> findById(Long id) {
        return eleveRepository.findById(id);
    }

    public Eleve save(Eleve eleve) {
        return eleveRepository.save(eleve);
    }

    public void deleteById(Long id) {
        eleveRepository.deleteById(id);
    }
}
