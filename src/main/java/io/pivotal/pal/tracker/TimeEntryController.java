package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    public final TimeEntryRepository timeEntryRepository;


    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry fields) {
        TimeEntry timeEntry = timeEntryRepository.create(fields);
        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        HttpStatus status = HttpStatus.OK;
        if(timeEntry == null)
        {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntry, status);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry fields) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, fields);
        HttpStatus status = HttpStatus.OK;
        if (timeEntry == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntry, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(timeEntry, HttpStatus.NO_CONTENT);

    }
}
