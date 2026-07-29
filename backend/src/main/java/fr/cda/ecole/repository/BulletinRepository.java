package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Bulletin;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

    List<Bulletin> findByEleveIdEleve(Long idEleve);
}
