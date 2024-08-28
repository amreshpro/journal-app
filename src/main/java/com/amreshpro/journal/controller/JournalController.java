package com.amreshpro.journal.controller;


import com.amreshpro.journal.entity.JournalEntity;
import com.amreshpro.journal.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/journal")
public class JournalController {

    @Autowired
    JournalService journalService;

    @PostMapping
    public ResponseEntity<Boolean> saveJournal(@RequestBody JournalEntity journalEntity) {

        return new ResponseEntity<>(journalService.saveJournal(journalEntity), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAllJournals() {
        List<JournalEntity> journalEntity = journalService.getAllJournal();
        if (!journalEntity.isEmpty()) {
            return new ResponseEntity<>(journalEntity, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<JournalEntity>> getJournalById(@PathVariable String id) {

        Optional<JournalEntity> journalEntity = journalService.getJournalById(id);
        if (!journalEntity.isEmpty()) {
            return new ResponseEntity<>(journalEntity, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }

    @PatchMapping
    public ResponseEntity<Boolean> updateJournalById(@RequestBody JournalEntity journalEntity) {

        if (journalService.updateJournalById(journalEntity)) {
            return new ResponseEntity<>(true, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);

        }


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteJournalById(@PathVariable String id) {
        Boolean isSuccessfullyDeleted = journalService.deleteJournalById(id);
        if(isSuccessfullyDeleted){
            return new ResponseEntity<>(true, HttpStatus.OK);

        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);

    }


}
