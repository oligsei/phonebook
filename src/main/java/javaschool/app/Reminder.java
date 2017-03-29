package javaschool.app;

import asg.cliche.Command;

import java.util.Date;

public class Reminder extends Note {
    private Date time = null;
    protected String type = "reminder";

    Reminder(String name) {
        super(name);
    }

    Date getTime() {
        return time;
    }

    @Command
    public void setTime(Date time) {
        this.time = time;
    }

    boolean hasTime() {
        return time != null;
    }

    @Override
    @Command(abbrev = "i", name = "info", description = "Print note\'s information")
    public String toString() {
        return super.toString();
    }


}
