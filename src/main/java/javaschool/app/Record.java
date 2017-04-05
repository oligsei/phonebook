package javaschool.app;

import asg.cliche.Command;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Record {
    private static Integer count = 0;

    private Integer id;
    private String name = null;
    private String type = "record";
    private Date created = new Date();

    private SimpleDateFormat createdFormat = new SimpleDateFormat("y.MM.dd HH:mm:ss");

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

        String result = String.format("Record #%d (%s) — '%s'", getId(), getType(), getName());
        result += String.format("\n     created: %s", createdFormat.format(created));
        return result;
    }

    protected String getType() {
        return type;
    }
}
