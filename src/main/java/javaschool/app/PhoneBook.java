package javaschool.app;

import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import javaschool.app.personfactory.PersonFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook implements ShellDependent {

    private Map<Integer, Record> map = new HashMap<>();
    private Shell theShell;

    @Override
    public void cliSetShell(Shell theShell) {
        this.theShell = theShell;
    }

    @Command(description = "Add a new person")
    public void addPerson(String name) {
        add(new Person(name));
    }

    @Command(description = "Add a new note")
    public void addNote(String name) {
        add(new Note(name));
    }

    @Command(description = "Add a new reminder")
    public void addReminder(String name) {
        add(new Reminder(name));
    }

    @Command(description = "Edit a record by id")
    public void edit(Integer id) throws IOException {
        Record record = get(id);
        if (record == null) {
            System.out.printf("Record with id \"%d\" not found.\n", id);
        } else {
            ShellFactory.createSubshell(record.getName(), theShell, "Editing " + record.getName(), record).commandLoop();
        }
    }

    @Command
    public Record get(Integer id) {
        return map.get(id);
    }

    @Command
    public Collection<Record> list() {
        return map.values();
    }

    @Command
    public List<Record> list(String type) {
        type = type.toLowerCase();

        if (type.equals("note")) {
            return map.values().stream().filter(record -> record instanceof Note).collect(Collectors.toList());
        }

        if (type.equals("person")) {
            return map.values().stream().filter(record -> record instanceof Person).collect(Collectors.toList());
        }

        if (type.equals("reminder")) {
            return map.values().stream().filter(record -> record instanceof Reminder).collect(Collectors.toList());
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
        map.clear();
    }

    @Command
    public void getReminds() {
        // see what reminders already finished
    }

    @Command(description = "Search in records")
    public List<Record> search(String criteria) {
        return map.values().stream().filter((r) -> r.contains(criteria.toLowerCase())).collect(Collectors.toList());
    }

    @Command(abbrev = "gen")
    public void generate(Integer count) {
        List<Person> result = PersonFactory.generate(count, PersonFactory.API_RANDOM_USER);
        result.forEach(this::add);
        System.out.format("Created %s new users\n", result.size());
    }

    private void add(Record r) {
        map.put(r.getId(), r);
    }
}
