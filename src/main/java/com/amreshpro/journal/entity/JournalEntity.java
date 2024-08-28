package com.amreshpro.journal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "journal_entries")
@Data
public class JournalEntity {
    @Id
    private String journalId;
    private String content;
    private String title;
    private LocalDateTime date;
    }
