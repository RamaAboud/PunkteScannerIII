package com.example.demo.repository;

import com.example.demo.model.Notenschluessel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

    @Repository
    public interface NotenschluesselRepository
            extends JpaRepository<Notenschluessel, Long> {

        Optional<Notenschluessel> findByPruefungId(Long pruefungId);
        Optional<Notenschluessel> findByIstStandardTrue();

        @Modifying
        @Query("DELETE FROM Notenschluessel n WHERE n.pruefung.id = :pid")
        void deleteByPruefungId(@Param("pid") Long pid);
    }

