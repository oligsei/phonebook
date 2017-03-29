package javaschool.app;

import asg.cliche.Command;

public class Note extends Record {
    private String body = "";

    Note(String name) {
        super(name);
    }

    String getBody() {
        return body;
    }

    @Command
    public void setBody(String body) {
        this.body = body;
    }

    boolean hasBody() {
        return !body.equals("");
    }

    @Override
    public boolean contains(String criteria) {
        return super.contains(criteria) || this.getBody().toLowerCase().contains(criteria);
    }

    @Override
    @Command(abbrev = "i", name = "info", description = "Print note\'s information")
    public String toString() {
        String result = String.format("Record #%d (note) — '%s'", getId(), getName());
        if (hasBody()) {
            result += String.format("\n     body: '%s'", getBody());
        }
        return result;
    }


}
