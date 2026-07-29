package fr.cda.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Responsable;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {

    boolean existsByEmail(String email);
}
