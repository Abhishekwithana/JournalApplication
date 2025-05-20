package net.engineeringabhishek.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document("config_journal_app")
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;

}
