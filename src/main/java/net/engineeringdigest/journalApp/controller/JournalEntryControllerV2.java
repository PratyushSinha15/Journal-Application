package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    //this is an instance of the journalEntryService class which will be used to call the methods of the service class to interact with the database
    //journalentryService ka ek instance banadiya hoga spring boot ne aur hmne usko autowired kar diya hai
    //this is how we inject journalEntryService into the controller
    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public ResponseEntity<JournalEntry> getAll(){
        //this method will return all the journal entries from the database
        //this is how we call the method of the service class to get the journal entries from the database
        List<JournalEntry> all= journalEntryService.getAllEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity(all, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(all, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try{
            journalEntryService.saveEntry(myEntry);//this method will save the journal entry in the database
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById( @PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<Boolean> deleteJournalEntryById( @PathVariable ObjectId myId){
        journalEntryService.deleteEntryById(myId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntryById( @PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        JournalEntry old = journalEntryService.getEntryById(myId).orElse(null);
        if(old != null){
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("")?myEntry.getTitle():old.getTitle());
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("")?myEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
