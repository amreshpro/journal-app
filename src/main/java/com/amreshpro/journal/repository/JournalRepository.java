package com.amreshpro.journal.repository;

import com.amreshpro.journal.entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntity,String> {


}
