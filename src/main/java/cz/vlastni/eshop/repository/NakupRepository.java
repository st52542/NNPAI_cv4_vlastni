package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.Entity.Nakup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NakupRepository extends JpaRepository<Nakup,Long> {
    @EntityGraph(attributePaths = {"Doprava","NakoupenaPolozka","Platba","Uzivatel"})
    Optional<Nakup> findById(Long id);

    Nakup findByObjednavka(Integer objednavka);

    void removeNakupById(Long id);
}
