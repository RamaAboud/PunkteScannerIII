package com.example.demo.repository;


import com.example.demo.model.Aufgabe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

    @Repository
    public interface AufgabeRepository
            extends JpaRepository<Aufgabe, Long> {

        List<Aufgabe> findByPruefungId(Long pruefungId);

        @Modifying
        @Query("DELETE FROM Aufgabe a WHERE a.pruefung.id = :pid")
        void deleteByPruefungId(@Param("pid") Long pid);
    }

