package fr.cda.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    Optional<Classe> findByNomClasse(String nomClasse);
}
