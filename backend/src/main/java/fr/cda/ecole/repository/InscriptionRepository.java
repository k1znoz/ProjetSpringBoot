package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.ecole.entity.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    List<Inscription> findByEleveIdEleve(Long idEleve);

    List<Inscription> findByClasseIdClasse(Long idClasse);
}
