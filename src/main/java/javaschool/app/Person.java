package javaschool.app;

import asg.cliche.Command;

import java.util.ArrayList;
import java.util.List;

public class Person extends Record {
    private String address = null;
    private String email = null;
    private List<String> phones = new ArrayList<>();

    Person(String name) {
        super(name);
    }

    String getEmail() {
        return email;
    }

    @Command
    public void setEmail(String email) {
        this.email = email;
    }

    boolean hasEmail() {
        return email != null;
    }

    String getAddress() {
        return address;
    }

    @Command
    public void setAddress(String address) {
        this.address = address;
    }

    boolean hasAddress() {
        return address != null;
    }

    @Command
    public void addPhone(String phone) {
        this.phones.add(phone);
    }

    @Command
    public void removePhone(String phone) {
        this.phones.remove(phone);
    }

    List<String> getPhones() {
        return phones;
    }

    @Command
    public void clearPhones() {
        this.phones.clear();
    }

    @Override
    @Command(abbrev = "i", name = "info", description = "Print person\'s information")
    public String toString() {
        String result = String.format("Record #%d (person) — '%s'", getId(), getName());
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

    @Override
    public boolean contains(String criteria) {
        if (super.contains(criteria)
                || (this.hasAddress() && this.getAddress().toLowerCase().contains(criteria))
                || (this.hasEmail() && this.getEmail().toLowerCase().contains(criteria))) {
            return true;
        }
        for (String phone : this.getPhones()) {
            if (phone.toLowerCase().contains(criteria)) {
                return true;
            }
        }
        return false;
    }
}
