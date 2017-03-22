package javaschool.app;

import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private static Integer count = 0;

    private Integer id;

    private String name = null;
    private String address = null;
    private List<String> phones = new ArrayList<>();

    public Record() {
        this.id = ++Record.count;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @Command
    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhones() {
        return phones;
    }

    @Command
    public void addPhone(String phone) {
        this.phones.add(phone);
    }

    @Command
    public void removePhone(String phone) {
        this.phones.remove(phone);
    }

    @Command
    public void info() {
        System.out.println(this.toString());
        if (getAddress() != null) {
            System.out.printf("Address: %s\n", getAddress());
        }
        System.out.printf("Number of phones: %d\n", getPhones().size());
        if (getPhones().size() > 0) {
            System.out.println(getPhones().toString());
        }
    }

    @Override
    public String toString() {
        return String.format("#%d — '%s'", getId(), getName());
    }
}
