package fr.cda.ecole.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.cda.ecole.dto.EleveDto;
import fr.cda.ecole.entity.Eleve;
import fr.cda.ecole.mapper.EleveMapper;
import fr.cda.ecole.repository.EleveRepository;

@Service
public class EleveService {

    private static final String UPLOAD_DIR = "uploads/eleves";

    private final EleveRepository eleveRepository;

    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    public List<EleveDto> findAll() {
        return eleveRepository.findAll().stream()
                .map(EleveMapper::toDto)
                .toList();
    }

    public Optional<EleveDto> findById(Long id) {
        return eleveRepository.findById(id)
                .map(EleveMapper::toDto);
    }

    public EleveDto save(EleveDto eleveDto) {
        return EleveMapper.toDto(
                eleveRepository.save(EleveMapper.toEntity(eleveDto))
        );
    }

    public void deleteById(Long id) {
        eleveRepository.deleteById(id);
    }

    public Optional<ElevePhotoResource> findPhotoByEleveId(Long id) {
        Optional<Eleve> optionalEleve = eleveRepository.findById(id);
        if (optionalEleve.isEmpty()) {
            return Optional.empty();
        }

        Eleve eleve = optionalEleve.get();
        String photoPath = eleve.getPhotoPath();
        if (photoPath == null || photoPath.isBlank()) {
            return Optional.empty();
        }

        Path filePath = resolveStoragePath(photoPath);
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            return Optional.empty();
        }

        Resource resource = new FileSystemResource(filePath);
        if (!resource.exists() || !resource.isReadable()) {
            return Optional.empty();
        }

        MediaType mediaType = detectMediaType(filePath);
        return Optional.of(new ElevePhotoResource(resource, mediaType));
    }

    public Optional<EleveDto> uploadPhoto(Long id, MultipartFile file) {
        Optional<Eleve> optionalEleve = eleveRepository.findById(id);
        if (optionalEleve.isEmpty()) {
            return Optional.empty();
        }

        Eleve eleve = optionalEleve.get();
        String storedPath = storePhoto(file, id);
        eleve.setPhotoPath(storedPath);

        Eleve saved = eleveRepository.save(eleve);
        return Optional.of(EleveMapper.toDto(saved));
    }

    private String storePhoto(MultipartFile file, Long eleveId) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadPath);

            String extension = extractExtension(file.getOriginalFilename());
            String fileName = "eleve-" + eleveId + "-" + UUID.randomUUID() + extension;
            Path destination = uploadPath.resolve(fileName).normalize();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            }

            return UPLOAD_DIR + "/" + fileName;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to store eleve photo", exception);
        }
    }

    private String extractExtension(String originalFilename) {
        if (originalFilename == null) {
            return "";
        }

        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex < 0 || lastDotIndex == originalFilename.length() - 1) {
            return "";
        }

        return originalFilename.substring(lastDotIndex);
    }

    private Path resolveStoragePath(String storedPath) {
        Path path = Paths.get(storedPath).normalize();
        if (path.isAbsolute()) {
            return path;
        }

        return Paths.get("").toAbsolutePath().resolve(path).normalize();
    }

    private MediaType detectMediaType(Path filePath) {
        try {
            String contentType = Files.probeContentType(filePath);
            if (contentType == null || contentType.isBlank()) {
                return MediaType.APPLICATION_OCTET_STREAM;
            }
            return MediaType.parseMediaType(contentType);
        } catch (IOException | InvalidMediaTypeException exception) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public record ElevePhotoResource(Resource resource, MediaType mediaType) {
    }
}
