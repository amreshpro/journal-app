package com.amreshpro.journal.service;

import com.amreshpro.journal.entity.JournalEntity;
import com.amreshpro.journal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public Boolean saveJournal(JournalEntity journalEntity) {
        journalEntity.setDate(LocalDateTime.now());
        journalRepository.save(journalEntity);
        return true;

    }

    public List<JournalEntity> getAllJournal() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntity> getJournalById(String id) {
        return journalRepository.findById(id);
    }

    public Boolean updateJournalById(JournalEntity journalEntity) {
        Boolean isJournalExist = journalRepository.existsById(journalEntity.getJournalId());

        if (isJournalExist) {
            journalEntity.setDate(LocalDateTime.now());
            journalRepository.save(journalEntity);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteJournalById(String id) {
        Boolean isJournalExist = journalRepository.existsById(id);
        if (isJournalExist) {
            journalRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
