package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDatetime(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userService.saveEntry(user);
        }catch(Exception e) {
            System.out.println("An Error has Occurred");
            throw new RuntimeException("Error occurred data has not been saved", e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId objectId) {
        return journalEntryRepository.findById(objectId);
    }

    @Transactional
    public boolean deleteEntryById(ObjectId id, String userName) {
        try {
            User user = userService.findByUserName(userName);
            boolean found = user.getJournalEntryList().removeIf(k -> k.getId().equals(id));
            if (found) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
                return true;
            }
        }catch(Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting entry");
        }
        return false;
    }
}
