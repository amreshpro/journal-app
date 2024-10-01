package com.amreshpro.journal.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "journal_entries")
@Data
public class JournalEntity {
    @Id
    private ObjectId journalId;
    @NonNull
    private String content;
    @NonNull
    private String title;
    private LocalDateTime date;
}
