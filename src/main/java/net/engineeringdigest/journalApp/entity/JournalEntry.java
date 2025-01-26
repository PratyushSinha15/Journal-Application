package net.engineeringdigest.journalApp.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

// this class is used to represent the journal entry
// it will have the fields like id, title, content
// we will use this class to store the journal entries in the database
@Document(collection = "journal_entries")
// this annotation is used to specify that this class is a document that will be stored in the database
//journalEntry ka instance banega toh ek document banega
@Getter
@Setter
public class JournalEntry {


    @Id // this annotation is used to specify that this field is the id of the document
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
