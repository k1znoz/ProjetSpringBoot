package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.entity.Bulletin;
import fr.cda.ecole.repository.BulletinRepository;

@Service
public class BulletinService {

    private final BulletinRepository bulletinRepository;

    public BulletinService(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }

    public List<Bulletin> findAll() {
        return bulletinRepository.findAll();
    }

    public Optional<Bulletin> findById(Long id) {
        return bulletinRepository.findById(id);
    }

    public Bulletin save(Bulletin bulletin) {
        return bulletinRepository.save(bulletin);
    }

    public void deleteById(Long id) {
        bulletinRepository.deleteById(id);
    }
}
