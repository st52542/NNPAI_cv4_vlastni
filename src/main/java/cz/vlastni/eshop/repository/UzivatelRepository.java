package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.Entity.Uzivatel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UzivatelRepository extends JpaRepository<Uzivatel,Long> {
    @EntityGraph(attributePaths = "Nakup")
    Optional<Uzivatel> findById(Long id);

    Uzivatel findByEmail(String email);

    void removeUzivatelById(Long id);
}