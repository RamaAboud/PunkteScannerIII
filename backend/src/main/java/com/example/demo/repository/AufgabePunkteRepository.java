package com.example.demo.repository;

import com.example.demo.model.AufgabePunkte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

    @Repository
    public interface AufgabePunkteRepository
            extends JpaRepository<AufgabePunkte, Long> {

        List<AufgabePunkte> findByErgebnisId(Long ergebnisId);
        List<AufgabePunkte> findByAufgabeId(Long aufgabeId);

        // Alle Aufgaben-Punkte einer Prüfung löschen (hängen über Aufgabe ODER Ergebnis dran).
        @Modifying
        @Query("DELETE FROM AufgabePunkte ap WHERE ap.aufgabe.pruefung.id = :pid OR ap.ergebnis.pruefung.id = :pid")
        void deleteByPruefungId(@Param("pid") Long pid);
    }

