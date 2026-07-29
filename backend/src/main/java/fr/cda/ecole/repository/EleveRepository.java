package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Eleve;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {

    boolean existsByEmail(String email);

    List<Eleve> findByNomContainingIgnoreCase(String nom);
}
