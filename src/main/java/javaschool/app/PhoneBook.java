package javaschool.app;

import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        list.add(new Person(name));
    }

    @Command(description = "Add a new note")
    public void addNote(String name) {
        list.add(new Note(name));
    }

    @Command(description = "Edit a record by id")
    public void edit(Integer id) throws IOException {
        Optional<Record> result = lookup(id);
        if (result.isPresent()) {
            Record record = result.get();
            ShellFactory.createSubshell(record.getName(), theShell, "Editing " + record.getName(), result)
                    .commandLoop();
        } else {
            System.out.printf("Record with id \"%d\" not found.\n", id);
        }
    }

    @Command
    public <T extends Record> List<T> list() {
        return list(null);
    }

    @Command
    public <T extends Record> List<T> list(String type) {
        try {
            return this.listByRecordType(Class.forName(type).asSubclass(Record.class));
        } catch (ClassNotFoundException e) {
            return (List<T>) list;
        }
    }

    @Command
    public void clear() {
        list.clear();
    }

    @Command(description = "Search in records")
    public List<Record> search(String criteria) {
        return list.stream().filter(r -> r.contains(criteria.toLowerCase())).collect(Collectors.toList());
    }

    private Optional<Record> lookup(Integer id) {
        return list.stream().filter((r) -> r.getId().equals(id)).findFirst();
    }

    private <T extends Record> List<T> listByRecordType(Class<T> recordType) {
        return list.stream().filter(recordType::isInstance).map(recordType::cast).collect(Collectors.toList());
    }
}
