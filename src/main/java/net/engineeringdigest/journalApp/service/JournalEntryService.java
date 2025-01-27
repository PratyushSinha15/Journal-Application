package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {
    // this class will have the business logic

    @Autowired// this annotation is used to inject the repository into the service(Dependency Injection)
    private JournalEntryRepository journalEntryRepository; //this is an interface that extends MongoRepository and it will be used to interact with the database
    //its implementation will be provided by spring boot at runtime
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        //this method will be used to save the journal entry in the database
        try{
            User user= userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }catch (Exception e){
            log.error("Error while saving the journal entry", e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        //this method will be used to save the journal entry in the database
        try{
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
        }catch (Exception e){
            log.error("Error while saving the journal entry", e);
        }
    }

    //here we will create another service to get all the journal entries from the database
    public List<JournalEntry> getAllEntries(){
        //this method will return all the journal entries from the database
        return journalEntryRepository.findAll();
    }

    //here we will create another service to get a journal entry by id
    public Optional<JournalEntry> getEntryById(ObjectId id){
        //this method will return the journal entry with the given id
        return journalEntryRepository.findById(id);
    }

    //method to delete a journal entry by id
    public void deleteEntryById(ObjectId id, String userName){
        User user= userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id)); //remove the journal entry from the user's journal entries list
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}

//controller calls the service ---> service calls the repository ---> repository interacts with the database