package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.Entity.Platba;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatbaRepository extends JpaRepository<Platba,Long> {
    @EntityGraph(attributePaths = "Nakup")
    Optional<Platba> findById(Long id);

    Platba findByPrevod(Double prevod);

    void removePlatbaById(Long id);
}
