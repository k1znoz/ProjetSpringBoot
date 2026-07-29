package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Responsable;
import fr.cda.ecole.repository.ResponsableRepository;

@Service
public class ResponsableService {

    private final ResponsableRepository responsableRepository;

    public ResponsableService(ResponsableRepository responsableRepository) {
        this.responsableRepository = responsableRepository;
    }

    public List<Responsable> findAll() {
        return responsableRepository.findAll();
    }

    public Optional<Responsable> findById(Long id) {
        return responsableRepository.findById(id);
    }

    public Responsable save(Responsable responsable) {
        return responsableRepository.save(responsable);
    }

    public void deleteById(Long id) {
        responsableRepository.deleteById(id);
    }
}
