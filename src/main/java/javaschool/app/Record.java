package javaschool.app;

import asg.cliche.Command;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class Record {
    private static Integer count = 0;

    private final Integer id;
    private String name = null;
    //    private final Instant created = Instant.now();
//    private final LocalDateTime createdDateTime = LocalDateTime.ofInstant(this.created, ZoneId.systemDefault());

    private final ZonedDateTime created = ZonedDateTime.now();
//    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("y.MM.dd HH:mm:ss");

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
        result += String.format("\n     created: %s", created.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
        return result;
    }

    protected String getType() {
        return "record";
    }
}
