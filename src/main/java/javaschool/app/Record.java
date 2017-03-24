package javaschool.app;

import asg.cliche.Command;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private static Integer count = 0;

    private Integer id;

    private String name = null;
    private String address = null;
    private String email = null;
    private List<String> phones = new ArrayList<>();

    Record() {
        this.id = ++Record.count;
    }

    Integer getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    boolean hasEmail() {
        return email != null;
    }

    String getAddress() {
        return address;
    }

    boolean hasAddress() {
        return address != null;
    }

    List<String> getPhones() {
        return phones;
    }

    @Command
    void setName(String name) {
        this.name = name;
    }

    @Command
    public void setEmail(String email) {
        this.email = email;
    }

    @Command
    public void setAddress(String address) {
        this.address = address;
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
    public void clearPhones() {
        this.phones.clear();
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
        String result = String.format("#%d — '%s'", getId(), getName());
        if (hasAddress()) {
            result += String.format("\n     address: '%s'", getAddress());
        }
        if (hasEmail()) {
            result += String.format("\n     email: '%s'", getEmail());
        }
        if (phones.size() > 0) {
            result += String.format("\n     phones: %s", phones);
        }
        return result;
    }

}
