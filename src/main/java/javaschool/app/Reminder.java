package javaschool.app;

import asg.cliche.Command;

import java.time.LocalDateTime;

public class Reminder extends Note {
    private LocalDateTime when = null;

    Reminder(String name) {
        super(name);
    }

    LocalDateTime getWhen() {
        return when;
    }

    @Command
    public void setWhen(String when) {
        this.when = LocalDateTime.parse(when);
    }

    boolean hasWhen() {
        return when != null;
    }

    @Override
    @Command(abbrev = "i", name = "info", description = "Print reminder\'s information")
    public String toString() {
        String result = super.toString();
        if (hasWhen()) {
            result += String.format("\n     reminder set to: %s", when.format(this.getDateTimeFormat()));
        }
        return result;
    }

    @Override
    protected String getType() {
        return "reminder";
    }
}
