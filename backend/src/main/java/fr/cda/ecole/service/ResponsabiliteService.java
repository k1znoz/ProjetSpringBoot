package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Responsabilite;
import fr.cda.ecole.entity.ResponsabiliteId;
import fr.cda.ecole.repository.ResponsabiliteRepository;

@Service
public class ResponsabiliteService {

    private final ResponsabiliteRepository responsabiliteRepository;

    public ResponsabiliteService(ResponsabiliteRepository responsabiliteRepository) {
        this.responsabiliteRepository = responsabiliteRepository;
    }

    public List<Responsabilite> findAll() {
        return responsabiliteRepository.findAll();
    }

    public Optional<Responsabilite> findById(ResponsabiliteId id) {
        return responsabiliteRepository.findById(id);
    }

    public Responsabilite save(Responsabilite responsabilite) {
        return responsabiliteRepository.save(responsabilite);
    }

    public void deleteById(ResponsabiliteId id) {
        responsabiliteRepository.deleteById(id);
    }
}
