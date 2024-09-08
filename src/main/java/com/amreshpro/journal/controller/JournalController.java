package com.amreshpro.journal.controller;

import com.amreshpro.journal.entity.JournalEntity;
import com.amreshpro.journal.service.JournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bson.types.ObjectId;
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
    private JournalService journalService;

    @Operation(summary = "Save a new journal", description = "Create a new journal entry in the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Journal successfully created"), @ApiResponse(responseCode = "400", description = "Invalid request body")})
    @PostMapping
    public ResponseEntity<Boolean> saveJournal(@RequestBody JournalEntity journalEntity) {
        boolean isSaved = journalService.saveJournal(journalEntity);
        return new ResponseEntity<>(isSaved, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all journals", description = "Retrieve all journal entries from the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved all journals"), @ApiResponse(responseCode = "204", description = "No journals found")})
    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAllJournals() {
        List<JournalEntity> journalEntities = journalService.getAllJournal();
        return journalEntities.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(journalEntities, HttpStatus.OK);
    }

    @Operation(summary = "Get journal by ID", description = "Retrieve a single journal entry by its ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Journal successfully retrieved"), @ApiResponse(responseCode = "404", description = "Journal not found")})
    @GetMapping("/{id}")
    public ResponseEntity<Optional<JournalEntity>> getJournalById(@Parameter(description = "ID of the journal entry to retrieve") @PathVariable ObjectId id) {

        Optional<JournalEntity> journalEntity = journalService.getJournalById(id);
        return journalEntity.map(entity -> new ResponseEntity<>(Optional.of(entity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update a journal", description = "Update an existing journal entry by its ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Journal successfully updated"), @ApiResponse(responseCode = "404", description = "Journal not found")})
    @PatchMapping
    public ResponseEntity<Boolean> updateJournalById(@RequestBody JournalEntity journalEntity) {
        boolean isUpdated = journalService.updateJournalById(journalEntity);
        return isUpdated ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete a journal", description = "Delete a journal entry by its ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Journal successfully deleted"), @ApiResponse(responseCode = "404", description = "Journal not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteJournalById(@Parameter(description = "ID of the journal entry to delete") @PathVariable ObjectId id) {

        boolean isDeleted = journalService.deleteJournalById(id);
        return isDeleted ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
