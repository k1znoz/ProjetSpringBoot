package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.ecole.entity.Bulletin;

public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

    List<Bulletin> findByEleveIdEleve(Long idEleve);
}
