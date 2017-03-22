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
        try {
            Record record = lookup(name);
            ShellFactory.createSubshell(record.getName(), theShell, "Editing " + record.getName(), record)
                    .commandLoop();
        } catch (NullPointerException e) {
            System.out.printf("Record with name \"%s\" not found.\n", name);
        }
    }

    @Command
    public void list() {
        if (this.list.size() > 0) {
            System.out.println(this.list.toString());
        } else {
            System.out.println("No records");
        }
    }

    @Command
    public void clear() {
        list.clear();
    }

    private Record lookup(String name) throws NullPointerException {
        for (Record record: list) {
            if (name.equals(record.getName())) {
                return record;
            }
        }
        throw new NullPointerException();
    }
}
