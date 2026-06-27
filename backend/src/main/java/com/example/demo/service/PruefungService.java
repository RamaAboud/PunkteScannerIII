package com.example.demo.service;
import com.example.demo.model.Pruefung;
import com.example.demo.repository.PruefungRepository;
import com.example.demo.repository.AufgabePunkteRepository;
import com.example.demo.repository.AufgabeRepository;
import com.example.demo.repository.ErgebnisRepository;
import com.example.demo.repository.NotenschluesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

    @Service
    public class PruefungService {

        @Autowired
        private PruefungRepository pruefungRepository;

        @Autowired
        private AufgabePunkteRepository aufgabePunkteRepository;
        @Autowired
        private AufgabeRepository aufgabeRepository;
        @Autowired
        private ErgebnisRepository ergebnisRepository;
        @Autowired
        private NotenschluesselRepository notenschluesselRepository;

        // Alle Prüfungen laden
        public List<Pruefung> getAllPruefungen() {
            return pruefungRepository.findAll();
        }

        // Eine Prüfung laden
        public Optional<Pruefung> getPruefungById(Long id) {
            return pruefungRepository.findById(id);
        }

        // Prüfungen eines Professors laden
        public List<Pruefung> getPruefungenByProfessor(Long professorId) {
            return pruefungRepository.findByProfessorId(professorId);
        }

        // Neue Prüfung erstellen
        public Pruefung createPruefung(Pruefung pruefung) {
            pruefung.setStatus("ENTWURF");
            Pruefung gespeichert = pruefungRepository.save(pruefung);
            return pruefungRepository.findById(gespeichert.getId()).orElseThrow();
        }

        // Prüfung bearbeiten
        public Pruefung updatePruefung(Long id, Pruefung pruefung) {
            pruefung.setId(id);
            return pruefungRepository.save(pruefung);
        }

        // Prüfung löschen – inkl. aller abhängigen Datensätze (sonst blockiert die
        // Fremdschlüssel-Constraint von aufgabe/ergebnis/... das Löschen). Reihenfolge:
        // tiefste Kinder zuerst, dann die Prüfung. Alles in einer Transaktion.
        @Transactional
        public void deletePruefung(Long id) {
            aufgabePunkteRepository.deleteByPruefungId(id);
            aufgabeRepository.deleteByPruefungId(id);
            ergebnisRepository.deleteByPruefungId(id);
            notenschluesselRepository.deleteByPruefungId(id);
            pruefungRepository.deleteById(id);
        }
    }

