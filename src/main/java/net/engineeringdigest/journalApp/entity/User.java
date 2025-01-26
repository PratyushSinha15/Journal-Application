package net.engineeringdigest.journalApp.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)//this annotation is used to specify that this field should be unique
    @NonNull//this annotation is used to specify that this field should not be null it is from lombok
    private  String userName;
    @NonNull
    private String password;

    //dbref is used to specify that this field is a reference to another document it is craeting a relationship between the user and journal entry
    //so that we can get all the journal entries of a user by using this field
    @DBRef //this annotation is used  to specify that this field is a reference to another document
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
