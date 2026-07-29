package fr.cda.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.cda.ecole.entity.Responsable;

public interface ResponsableRepository extends JpaRepository<Responsable, Long> {

    boolean existsByEmail(String email);
}
