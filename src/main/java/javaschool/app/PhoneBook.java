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

    public static void main(String[] args) {

    }

    @Override
    public void cliSetShell(Shell theShell) {
        this.theShell = theShell;
    }

    @Command
    public void add(String name) {
        Record record = new Record();
        record.setName(name);
        list.add(record);
    }

    @Command
    public void edit(String name) throws IOException {
        Record record = lookup(name);
        if (record == null) {
            System.out.printf("Record with name \"%s\" not found.\n", name);
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


    @Command
    public List<Record> search(String substring) {
        List<Record> list = new ArrayList<>();
        this.list.forEach((Record record) -> {
            if (record.getName().toLowerCase().contains(substring.toLowerCase())) {
                list.add(record);
            }
        });
        return list;
    }

    private Record lookup(Number id) {
        for (Record record : list) {
            if (record.getId().equals(id)) {
                return record;
            }
        }
        return null;
    }
}
