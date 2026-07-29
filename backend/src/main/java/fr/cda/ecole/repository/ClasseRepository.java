package fr.cda.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.ecole.entity.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

    Optional<Classe> findByNomClasse(String nomClasse);
}
