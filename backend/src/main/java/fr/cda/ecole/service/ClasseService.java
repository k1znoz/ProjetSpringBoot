package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Classe;
import fr.cda.ecole.repository.ClasseRepository;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<Classe> findAll() {
        return classeRepository.findAll();
    }

    public Optional<Classe> findById(Long id) {
        return classeRepository.findById(id);
    }

    public Classe save(Classe classe) {
        return classeRepository.save(classe);
    }

    public void deleteById(Long id) {
        classeRepository.deleteById(id);
    }
}
