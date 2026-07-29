package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.ClasseDto;
import fr.cda.ecole.mapper.ClasseMapper;
import fr.cda.ecole.repository.ClasseRepository;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<ClasseDto> findAll() {
        return classeRepository.findAll().stream()
                .map(ClasseMapper::toDto)
                .toList();
    }

    public Optional<ClasseDto> findById(Long id) {
        return classeRepository.findById(id)
                .map(ClasseMapper::toDto);
    }

    public ClasseDto save(ClasseDto classeDto) {
        return ClasseMapper.toDto(
                classeRepository.save(ClasseMapper.toEntity(classeDto))
        );
    }

    public void deleteById(Long id) {
        classeRepository.deleteById(id);
    }
}
