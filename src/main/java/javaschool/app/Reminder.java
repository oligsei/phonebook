package javaschool.app;

import asg.cliche.Command;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Reminder extends Note {
    private ZonedDateTime when = null;

    Reminder(String name) {
        super(name);
    }

    ZonedDateTime getWhen() {
        return when;
    }

    @Command
    public void setWhen(String when) {
        this.when = DateTimeParser.parse(when);
    }

    boolean hasWhen() {
        return when != null;
    }

    @Override
    @Command(abbrev = "i", name = "info", description = "Print reminder\'s information")
    public String toString() {
        String result = super.toString();
        if (hasWhen()) {
            result += String.format("\n     reminder set to: %s", when.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        }
        return result;
    }

    @Override
    protected String getType() {
        return "reminder";
    }
}
