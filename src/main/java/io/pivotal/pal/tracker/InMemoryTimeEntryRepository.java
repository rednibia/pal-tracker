package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> repo = new HashMap();

    Long id_counter = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(++id_counter, timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());
        repo.put(id_counter, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return repo.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {

        List<TimeEntry> entryList = new ArrayList<>();

        entryList.addAll(repo.values());

        return entryList;


    }

    @Override
    public TimeEntry update(long id, TimeEntry fields) {
        TimeEntry timeEntry = repo.get(id);

        if (timeEntry != null) {
            TimeEntry newTimeEntry = new TimeEntry(id, fields.getProjectId(),
                    fields.getUserId(),fields.getDate(), fields.getHours());

            repo.put(id, newTimeEntry);

            timeEntry = newTimeEntry;
        }

        return timeEntry;
    }

    @Override
    public boolean delete(long timeEntryId) {
        TimeEntry timeEntry = repo.get(timeEntryId);
        return repo.remove(timeEntryId, timeEntry);
    }
}
