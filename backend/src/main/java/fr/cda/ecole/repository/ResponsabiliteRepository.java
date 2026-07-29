package fr.cda.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.ecole.entity.Responsabilite;
import fr.cda.ecole.entity.ResponsabiliteId;

public interface ResponsabiliteRepository extends JpaRepository<Responsabilite, ResponsabiliteId> {
}
