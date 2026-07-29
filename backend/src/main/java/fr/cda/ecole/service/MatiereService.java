package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.MatiereDto;
import fr.cda.ecole.mapper.MatiereMapper;
import fr.cda.ecole.repository.MatiereRepository;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public List<MatiereDto> findAll() {
        return matiereRepository.findAll().stream()
                .map(MatiereMapper::toDto)
                .toList();
    }

    public Optional<MatiereDto> findById(Long id) {
        return matiereRepository.findById(id)
                .map(MatiereMapper::toDto);
    }

    public MatiereDto save(MatiereDto matiereDto) {
        return MatiereMapper.toDto(
                matiereRepository.save(MatiereMapper.toEntity(matiereDto))
        );
    }

    public void deleteById(Long id) {
        matiereRepository.deleteById(id);
    }
}
