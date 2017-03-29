package javaschool.app;

import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook implements ShellDependent {

    private List<Record> list = new ArrayList<>();
    private Shell theShell;

    @Override
    public void cliSetShell(Shell theShell) {
        this.theShell = theShell;
    }

    @Command(description = "Add a new person")
    public void addPerson(String name) {
        Record record = new Person(name);
        list.add(record);
    }

    @Command(description = "Add a new note")
    public void addNote(String name) {
        Record record = new Note(name);
        list.add(record);
    }

    @Command(description = "Edit a record by id")
    public void edit(Integer id) throws IOException {
        Record record = lookup(id);
        if (record == null) {
            System.out.printf("Record with id \"%d\" not found.\n", id);
        } else {
            ShellFactory.createSubshell(record.getName(), theShell, "Editing " + record.getName(), record)
                    .commandLoop();
        }
    }

    @Command
    public List<Record> list() {
        return list;
    }

    @Command
    public void clear() {
        list.clear();
    }


    @Command(description = "Search in records")
    public List<Record> search(String criteria) {
        return list.stream().filter((r) -> r.contains(criteria.toLowerCase())).collect(Collectors.toList());
    }

    private Record lookup(Integer id) {
        for (Record record : list) {
            if (record.getId().equals(id)) {
                return record;
            }
        }
        return null;
    }
}
