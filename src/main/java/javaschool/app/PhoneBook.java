package javaschool.app;

import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements ShellDependent {

    private List<Record> list = new ArrayList<>();
    private Shell theShell;

    @Override
    public void cliSetShell(Shell theShell) {
        this.theShell = theShell;
    }

    @Command(description = "Add a new user")
    public void add(String name) {
        Record record = new Record();
        record.setName(name);
        list.add(record);
    }

    @Command(description = "Edit the user by id")
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


    @Command(description = "Search in user\'s address, email and phone numbers")
    public List<Record> search(String userInput) {
        List<Record> entries = new ArrayList<>();
        final String substring = userInput.toLowerCase();

        for (Record record : list) {
            if (record.getName().toLowerCase().contains(substring)
                    || (record.hasAddress() && record.getAddress().toLowerCase().contains(substring))
                    || (record.hasEmail() && record.getEmail().toLowerCase().contains(substring))) {
                entries.add(record);
            }
            record.getPhones().forEach((phone) -> {
                if (phone.contains(substring) && !entries.contains(record)) {
                    entries.add(record);
                }
            });
        }

        return entries;
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
