package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.Entity.Doprava;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DopravaRepository extends JpaRepository<Doprava,Long> {
    @EntityGraph(attributePaths = "Nakup")
    Optional<Doprava> findById(Long id);

    Doprava findByCena(Integer cena);

    void removeDopravaById(Long id);
}
