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
        Optional<Record> record = lookup(id);
        if (record.isPresent()) {
            ShellFactory.createSubshell(record.get().getName(), theShell, "Editing " + record.get().getName(), record)
                    .commandLoop();
        } else {
            System.out.printf("Record with id \"%d\" not found.\n", id);
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

    private Optional<Record> lookup(Integer id) {
        return list.stream().filter((r) -> r.getId().equals(id)).findFirst();
    }
}
