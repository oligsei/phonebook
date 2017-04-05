package javaschool.app;

import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import javaschool.app.personfactory.PersonFactory;

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

    @Command(description = "Add a new reminder")
    public void addReminder(String name) {
        list.add(new Reminder(name));
    }

    @Command(description = "Edit a record by id")
    public void edit(Integer id) throws IOException {
        Optional<Record> result = lookup(id);
        if (result.isPresent()) {
            Record record = result.get();
            ShellFactory.createSubshell(record.getName(), theShell, "Editing " + record.getName(), record).commandLoop();
        } else {
            System.out.printf("Record with id \"%d\" not found.\n", id);
        }
    }

    @Command
    public List<Record> list() {
        return list;
    }

    @Command
    public List<Record> list(String type) {
        type = type.toLowerCase();

        if (type.equals("note")) {
            return list.stream().filter(record -> record instanceof Note).collect(Collectors.toList());
        }

        if (type.equals("person")) {
            return list.stream().filter(record -> record instanceof Person).collect(Collectors.toList());
        }

        if (type.equals("reminder")) {
            return list.stream().filter(record -> record instanceof Reminder).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Command
    public List<Record> list(String type, String... types) {
        final List<Record> result = this.list(type);
        for (String otherType : types) {
            result.addAll(this.list(otherType));
        }
        return result;
    }

    @Command
    public void clear() {
        list.clear();
    }

    @Command
    public void getReminds() {
        // see what reminders already finished
    }

    @Command(description = "Search in records")
    public List<Record> search(String criteria) {
        return list.stream().filter((r) -> r.contains(criteria.toLowerCase())).collect(Collectors.toList());
    }

    @Command
    public void generate(Integer count) {
        List<Person> result = PersonFactory.generate(count, PersonFactory.API_RANDOM_USER);
        list.addAll(result);
        System.out.format("Created %s new users\n", result.size());
    }

    private Optional<Record> lookup(Integer id) {
        return list.stream().filter((r) -> r.getId().equals(id)).findFirst();
    }
}
