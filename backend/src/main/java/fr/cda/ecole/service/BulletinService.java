package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.BulletinDto;
import fr.cda.ecole.entity.Bulletin;
import fr.cda.ecole.mapper.BulletinMapper;
import fr.cda.ecole.repository.BulletinRepository;

@Service
public class BulletinService {

    private final BulletinRepository bulletinRepository;

    public BulletinService(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }

    public List<BulletinDto> findAll() {
        return bulletinRepository.findAll().stream()
                .map(BulletinMapper::toDto)
                .toList();
    }

    public Optional<BulletinDto> findById(Long id) {
        return bulletinRepository.findById(id)
                .map(BulletinMapper::toDto);
    }

    public Optional<Bulletin> findEntityById(Long id) {
        return bulletinRepository.findWithEleveByIdBulletin(id);
    }

    public BulletinDto save(BulletinDto bulletinDto) {
        return BulletinMapper.toDto(
                bulletinRepository.save(BulletinMapper.toEntity(bulletinDto))
        );
    }

    public void deleteById(Long id) {
        bulletinRepository.deleteById(id);
    }
}
