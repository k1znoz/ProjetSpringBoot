package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    List<Inscription> findByEleveIdEleve(Long idEleve);

    List<Inscription> findByClasseIdClasse(Long idClasse);
}
