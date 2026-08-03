package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.cda.ecole.dto.UtilisateurDto;
import fr.cda.ecole.mapper.UtilisateurMapper;
import fr.cda.ecole.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurMapper::toDto)
                .toList();
    }

    public Optional<UtilisateurDto> findById(Long id) {
        return utilisateurRepository.findById(id)
                .map(UtilisateurMapper::toDto);
    }

    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        utilisateurDto.setPasswordHash(passwordEncoder.encode(utilisateurDto.getPasswordHash()));
        return UtilisateurMapper.toDto(
                utilisateurRepository.save(UtilisateurMapper.toEntity(utilisateurDto))
        );
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
