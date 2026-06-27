package com.example.demo.repository;


import com.example.demo.model.Ergebnis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

    @Repository
    public interface ErgebnisRepository
            extends JpaRepository<Ergebnis, Long> {

        List<Ergebnis> findByPruefungId(Long pruefungId);
        List<Ergebnis> findByStudentId(Long studentId);

        @Modifying
        @Query("DELETE FROM Ergebnis e WHERE e.pruefung.id = :pid")
        void deleteByPruefungId(@Param("pid") Long pid);
    }

