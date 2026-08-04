package fr.cda.ecole.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.ecole.entity.Bulletin;

public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

    List<Bulletin> findByEleveIdEleve(Long idEleve);

    @EntityGraph(attributePaths = "eleve")
    Optional<Bulletin> findWithEleveByIdBulletin(Long idBulletin);
}
