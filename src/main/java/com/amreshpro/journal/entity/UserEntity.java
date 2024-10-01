package com.amreshpro.journal.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Document(collection = "journal_users")
@Data
public class UserEntity {
  @Id
  private ObjectId userId;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String password;
    private LocalDateTime date;
    @DBRef
    private ArrayList<JournalEntity> journals = new ArrayList<>();

}
