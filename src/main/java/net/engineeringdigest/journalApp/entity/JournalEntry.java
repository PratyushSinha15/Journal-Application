package net.engineeringdigest.journalApp.entity;

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
public class JournalEntry {
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Id // this annotation is used to specify that this field is the id of the document
    private ObjectId id;

    private String title;
    private String content;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private LocalDateTime date;
}
