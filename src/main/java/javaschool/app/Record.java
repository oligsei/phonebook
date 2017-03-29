package javaschool.app;

import asg.cliche.Command;

public abstract class Record {
    private static Integer count = 0;

    private Integer id;
    private String name = null;
    private String type = "record";

    Record(String name) {
        this.id = ++Record.count;
        this.name = name;
    }

    Integer getId() {
        return id;
    }

    String getName() {
        return name;
    }

    @Command
    void setName(String name) {
        this.name = name;
    }

    public boolean contains(String criteria) {
        return this.getName().toLowerCase().contains(criteria);
    }

    @Override
    @Command(abbrev = "i", name = "info", description = "Print record\'s information")
    public String toString() {
        return String.format("Record #%d (%s) — '%s'", getId(), this.type, getName());
    }
}
