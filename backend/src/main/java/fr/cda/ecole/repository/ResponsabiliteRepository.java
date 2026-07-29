package fr.cda.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Responsabilite;
import fr.cda.ecole.entity.ResponsabiliteId;

@Repository
public interface ResponsabiliteRepository extends JpaRepository<Responsabilite, ResponsabiliteId> {
}
