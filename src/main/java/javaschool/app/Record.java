package javaschool.app;

import asg.cliche.Command;

public abstract class Record {
    private static Integer count = 0;

    private Integer id;
    private String name = null;

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

    public abstract boolean match(String criteria);

    @Override
    @Command(abbrev = "i", name = "info", description = "Print user\'s information")
    public String toString() {
        return String.format("#%d — '%s'", getId(), getName());
    }
}
