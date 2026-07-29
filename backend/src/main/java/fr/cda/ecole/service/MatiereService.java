package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Matiere;
import fr.cda.ecole.repository.MatiereRepository;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public List<Matiere> findAll() {
        return matiereRepository.findAll();
    }

    public Optional<Matiere> findById(Long id) {
        return matiereRepository.findById(id);
    }

    public Matiere save(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public void deleteById(Long id) {
        matiereRepository.deleteById(id);
    }
}
